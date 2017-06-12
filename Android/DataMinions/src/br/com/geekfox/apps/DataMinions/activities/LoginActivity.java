package br.com.geekfox.apps.DataMinions.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import br.com.geekfox.apps.DataMinions.R;
import br.com.geekfox.apps.DataMinions.helpers.PreferencesHelper;
import br.com.geekfox.apps.DataMinions.json.DeviceData;
import br.com.geekfox.apps.DataMinions.json.UserData;
import br.com.geekfox.apps.DataMinions.utils.AppUtils;
import br.com.geekfox.apps.DataMinions.webservice.DataService;

/**
 * Created by RafaelBr on 05/05/2015.
 */
public class LoginActivity extends Activity {

    ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ViewGroup v = (ViewGroup) findViewById(R.id.mainLayout);
        AppUtils.setFont(this, v);
    }

    public void OnClickRegister(View v) {
        UserData data = new UserData();
        data.setEmail(((TextView) findViewById(R.id.userfield)).getText().toString());
        String pwd = ((TextView) findViewById(R.id.pwdfield)).getText().toString();
        data.setPassword(AppUtils.computeMD5Hash(pwd));
        login(data);
    }

    public void login(final UserData userdata) {
        new AsyncTask<Void, Void, Void>() {
            UserData data;
            @Override
            protected Void doInBackground(Void... voids) {

                data = DataService.login(userdata);
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = new ProgressDialog(LoginActivity.this);
                dialog.setMessage("Registering device....");
                dialog.show();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dialog.dismiss();
                if (data != null) {
                    if (data.getResult().getCode() == DataService.USER_NOTFOUND) {
                        Toast.makeText(LoginActivity.this, "You are not registered in our network. Please register first.", Toast.LENGTH_LONG).show();
                    }
                    else if (data.getResult().getCode() == DataService.USER_OK) {
                        PreferencesHelper.saveUserData(data, LoginActivity.this);
                        Toast.makeText(LoginActivity.this, "User found!", Toast.LENGTH_LONG).show();
                        //register device
                        registerDevice();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Login incorrect, please try again.", Toast.LENGTH_LONG).show();
                    }

                }
                else {
                    Toast.makeText(LoginActivity.this, "Error while retrieving user. Try again.", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        }.execute();
    }

    public void registerDevice() {
        new AsyncTask<Void, Void, Void>() {
            DeviceData data;
            @Override
            protected Void doInBackground(Void... voids) {
                data = new DeviceData();
                UserData userData = PreferencesHelper.loadUserData(LoginActivity.this);
                data.setUserId(userData.getId());
                data = DataService.registerOnGCM(LoginActivity.this, data);
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = new ProgressDialog(LoginActivity.this);
                dialog.setMessage("Registering device....");
                dialog.show();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (data != null) {
                    if (data.getResult().getCode() == DataService.DEVICE_REGISTERED) {
                        Intent intent = new Intent(LoginActivity.this, LaunchActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else if (data.getResult().getCode() == DataService.DEVICE_EXIST) {
                        //load user data with hash and save it
                        hashLogin(data.getHash());
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Problem while registering device, try again later.", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "Service unavailable, try again later!", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        }.execute();
    }

    public void hashLogin(final String hash) {
        new AsyncTask<Void, Void, Void>() {
            ProgressDialog dialog;
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
                dialog = new ProgressDialog(LoginActivity.this);
                dialog.setMessage("Device registered, loading data...");
                dialog.show();
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (userData != null)
                    if (userData.getResult().getCode() == DataService.RESULT_OK) {
                        PreferencesHelper.saveUserData(userData, LoginActivity.this);
                        Intent intent = new Intent(LoginActivity.this, LaunchActivity.class);
                        startActivity(intent);
                    }
                    else if (userData.getResult().getCode() == DataService.DEVICE_NOTREGISTER) {
                        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                super.onPostExecute(aVoid);
            }
        }.execute();
    }
}

