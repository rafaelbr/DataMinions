package br.com.geekfox.apps.DataMinions.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.geekfox.apps.DataMinions.R;
import br.com.geekfox.apps.DataMinions.helpers.PreferencesHelper;
import br.com.geekfox.apps.DataMinions.json.UserData;
import br.com.geekfox.apps.DataMinions.utils.AppUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by RafaelBr on 05/05/2015.
 */
public class MainActivity extends Activity {

    UserData userData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup v = (ViewGroup) findViewById(R.id.MainLayout);
        AppUtils.setFont(this, v);

        updateUserData();
    }

    public void updateUserData() {
        //Init retrieval data
        userData = PreferencesHelper.loadUserData(this);
        TextView txtNickname = (TextView) findViewById(R.id.txtProfileName);
        TextView txtLicense = (TextView) findViewById(R.id.txtProfileTitle);
        ImageView imgProfile = (ImageView) findViewById(R.id.imgAvatar);

        txtNickname.setText(userData.getNickname());

        String levelLicense;
        if (userData.getLevel() < 10) {
            levelLicense = "Data Explorer";
        }
        else if (userData.getLevel() < 20) {
            levelLicense = "Data Researcher";
        }
        else {
            levelLicense = "Data Scientist";
        }

        txtLicense.setText(levelLicense);

        try {
            byte[] rawImage = Base64.decode(userData.getImage64(), Base64.DEFAULT);
            ByteArrayInputStream stream = new ByteArrayInputStream(rawImage);
            imgProfile.setImageBitmap(BitmapFactory.decodeStream(stream));
            stream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onClickHelp(View v) {
        Intent i = new Intent(this, HelpActivity.class);
        startActivity(i);
    }

    public void onClickConfig(View v) {
        Intent i = new Intent(this, ConfigActivity.class);
        startActivity(i);
    }

    public void onClickInfo(View v) {
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    public void onClickLibrary(View v) {
        Intent i = new Intent(this, LibraryActivity.class);
        startActivity(i);
    }

    public void onClickMinions(View v) {
        Intent i = new Intent(this, MinionsActivity.class);
        startActivity(i);
    }

    public void onClickMessages(View v) {
        Intent i = new Intent(this, MessagesActivity.class);
        startActivity(i);
    }

    public void onClickRadar(View v) {
        Intent i = new Intent(this, RadarActivity.class);
        startActivity(i);
    }
}