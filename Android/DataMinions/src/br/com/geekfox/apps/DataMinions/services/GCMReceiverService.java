package br.com.geekfox.apps.DataMinions.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import br.com.geekfox.apps.DataMinions.data.DataSource;
import br.com.geekfox.apps.DataMinions.helpers.PreferencesHelper;
import br.com.geekfox.apps.DataMinions.json.DataHubData;
import br.com.geekfox.apps.DataMinions.json.DataMinionData;
import br.com.geekfox.apps.DataMinions.json.NotificationParcel;
import br.com.geekfox.apps.DataMinions.json.UserData;
import br.com.geekfox.apps.DataMinions.webservice.DataService;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by rafaelbrasileiro on 13/05/14.
 */
public class GCMReceiverService extends IntentService {

    public GCMReceiverService() {
        super("GCMReceiverService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        Gson gson = new Gson();
        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) {
            //check message based on messageType
            if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                //gcm should send a NotificationParcel json, decode it
                String data = extras.getString("response");
                NotificationParcel parcelData = gson.fromJson(data, NotificationParcel.class);

                if (parcelData.getType().equals(NotificationParcel.REQUEST_HUB_UPDATE)) {
                    try {
                        List<DataHubData> list = DataService.retrieveAllHubs();
                        DataSource ds = new DataSource(this);
                        ds.open();
                        ds.clearHubData();
                        ds.populateHubs(list);
                        ds.close();
                        //Intent i = new Intent(MainActivity.HUB_UPDATE_ACTION);
                        //sendBroadcast(i);
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                else if (parcelData.getType().equals(NotificationParcel.UPDATE_USERDATA)) {
                    try {
                        UserData userData = DataService.retrieveUserData(PreferencesHelper.loadUserData(this));
                        PreferencesHelper.saveUserData(userData, this);
                        List<DataMinionData> minions = DataService.retrieveUserMinions(userData);
                        DataSource ds = new DataSource(this);
                        ds.open();
                        ds.clearUserMinions();
                        ds.populateUserMinions(minions);
                        ds.close();
                        //Intent i = new Intent(MainActivity.RELOAD_USER_ACTION);
                        //sendBroadcast(i);
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else if (parcelData.getType().equals(NotificationParcel.TELL_MINION_LVLUP)) {
                    //Intent i = new Intent(MainActivity.MINION_LVL_UP);
                    //sendBroadcast(i);
                }
                else if (parcelData.getType().equals(NotificationParcel.TELL_USER_LVLUP)) {
                    //Intent i = new Intent(MainActivity.PLAYER_LVL_UP);
                    //sendBroadcast(i);
                }
            }
        }

        //AppBroadcastReceiver.completeWakefulIntent(intent);
    }
}
