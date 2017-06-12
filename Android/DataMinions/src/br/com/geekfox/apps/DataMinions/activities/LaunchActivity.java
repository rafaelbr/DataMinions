package br.com.geekfox.apps.DataMinions.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.geekfox.apps.DataMinions.R;
import br.com.geekfox.apps.DataMinions.converters.UserMinionConverter;
import br.com.geekfox.apps.DataMinions.data.DataSource;
import br.com.geekfox.apps.DataMinions.domain.UserMinion;
import br.com.geekfox.apps.DataMinions.helpers.PreferencesHelper;
import br.com.geekfox.apps.DataMinions.json.*;
import br.com.geekfox.apps.DataMinions.utils.AppUtils;
import br.com.geekfox.apps.DataMinions.webservice.DataService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by RafaelBr on 04/05/2015.
 */
public class LaunchActivity extends Activity {

    TextView messageText;

    private Handler handler = new Handler();
    private Runnable loadMainTask = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ViewGroup v = (ViewGroup) findViewById(R.id.mainLayout);
        AppUtils.setFont(this, v);
        messageText = (TextView) findViewById(R.id.loadingMsg);
        final String hash = PreferencesHelper.retrieveGCMHash(this);
        if (hash.isEmpty()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        new AsyncTask<Void, Void, Void>() {
            UserData userData;
            @Override
            protected Void doInBackground(Void... voids) {
                DeviceData data = new DeviceData();
                data.setHash(hash);
                userData = DataService.loginDevice(data);
                return null;
            }

            @Override
            protected void onPreExecute() {
                messageText.setText(getResources().getString(R.string.deviceregister_message));
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (userData.getResult().getCode() == DataService.RESULT_OK) {
                    PreferencesHelper.saveUserData(userData, LaunchActivity.this);
                    //proceed with loading
                    processDatabase();
                }
                else if (userData.getResult().getCode() == DataService.DEVICE_NOTREGISTER) {
                    Intent intent = new Intent(LaunchActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                super.onPostExecute(aVoid);
            }
        }.execute();
    }

    private void processDatabase() {
        messageText.setText(getResources().getString(R.string.loading_message));
        //load data if needed, for now UserData must be updated here
        retrieveDataMinions();
    }

    public void retrieveDataMinions() {
        new AsyncTask<Void, Void, Void>() {
            List<DataMinionData> list;
            @Override
            protected Void doInBackground(Void... voids) {
                list = DataService.retrieveAllDataMinions();
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(Void aVoid) {

                if (list != null) {
                    //save list into device
                    try {
                        DataSource ds = new DataSource(LaunchActivity.this);
                        ds.open();
                        ds.clearDataMinions();
                        ds.populateDataMinions(list);
                        ds.close();
                        retrieveAttacks();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                        messageText.setText(getResources().getString(R.string.error_message));
                    }

                }
                else {
                    messageText.setText(getResources().getString(R.string.error_message));
                }
                super.onPostExecute(aVoid);
            }
        }.execute();
    }

    public void retrieveAttacks() {
        messageText.setText(getResources().getString(R.string.minionload_message));
        new AsyncTask<Void, Void, Void>() {
            List<AttackData> list;
            @Override
            protected Void doInBackground(Void... voids) {
                list = DataService.retrieveAllAttacks();
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (list != null) {
                    //save list into device
                    try {
                        DataSource ds = new DataSource(LaunchActivity.this);
                        ds.open();
                        ds.clearAttacks();
                        ds.populateAttacks(list);
                        ds.close();
                        retrieveHubs();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                        messageText.setText(getResources().getString(R.string.error_message));
                    }

                }
                else {
                    messageText.setText(getResources().getString(R.string.error_message));
                }
                super.onPostExecute(aVoid);
            }
        }.execute();
    }

    public void retrieveHubs() {
        messageText.setText(getResources().getString(R.string.hubload_message));
        new AsyncTask<Void, Void, Void>() {
            List<DataHubData> list;
            @Override
            protected Void doInBackground(Void... voids) {
                list = DataService.retrieveAllHubs();
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (list != null) {
                    //save list into device
                    try {
                        DataSource ds = new DataSource(LaunchActivity.this);
                        ds.open();
                        ds.clearHubData();
                        ds.populateHubs(list);
                        ds.close();
                        retrieveUserMinions();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                        messageText.setText(getResources().getString(R.string.error_message));
                    }

                }
                else {
                    messageText.setText(getResources().getString(R.string.error_message));
                }
                super.onPostExecute(aVoid);
            }
        }.execute();
    }

    public void retrieveUserMinions() {
        messageText.setText(getResources().getString(R.string.userminion_message));
        new AsyncTask<Void, Void, Void>() {
            List<DataMinionData> list;
            @Override
            protected Void doInBackground(Void... voids) {
                list = DataService.retrieveUserMinions(PreferencesHelper.loadUserData(LaunchActivity.this));
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (list != null) {
                    //save list into device
                    try {
                        DataSource ds = new DataSource(LaunchActivity.this);
                        ds.open();
                        //clear userminions and reset to default state
                        ds.clearUserMinions();
                        int i = 1;
                        for(DataMinionData minionData : list) {
                            UserMinion minion = UserMinionConverter.jsonToDomain(minionData);
                            //reset health
                            minion.setHealth(20 + (minionData.getStaBase() + minion.getLevel() - 1) * minion.getLevel());
                            ds.saveUserMinion(minion);

                            //set this minion for a slot if possible
                            /*if (i <= 5) {
                                if (PreferencesHelper.getSlotMinion(i, LaunchActivity.this) == 0) {
                                    PreferencesHelper.setSlotMinion(i, list.get(i-1).getId(), LaunchActivity.this);
                                }
                                i++;
                            }*/
                        }
                        ds.close();

                        retrieveLibrary();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                        messageText.setText(getResources().getString(R.string.error_message));
                    }

                }
                else {
                    messageText.setText(getResources().getString(R.string.error_message));
                }
                super.onPostExecute(aVoid);
            }
        }.execute();
    }

    public void retrieveLibrary() {
        messageText.setText(getResources().getString(R.string.libraryload_message));
        new AsyncTask<Void, Void, Void>() {
            List<LibraryEntryData> list;
            @Override
            protected Void doInBackground(Void... voids) {
                list = DataService.retrieveLibrary(PreferencesHelper.loadUserData(LaunchActivity.this));

                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (list != null) {
                    //save list into device
                    try {
                        DataSource ds = new DataSource(LaunchActivity.this);
                        ds.open();
                        //clear userminions and reset to default state
                        ds.clearLibrary();
                        ds.populateLibrary(list);
                        ds.close();
                        messageText.setText(getResources().getString(R.string.finishload_message));
                        handler.postDelayed(loadMainTask, 2000);
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                        messageText.setText(getResources().getString(R.string.error_message));
                    }

                }
                else {
                    messageText.setText(getResources().getString(R.string.error_message));
                }
                super.onPostExecute(aVoid);
            }
        }.execute();


    }
}