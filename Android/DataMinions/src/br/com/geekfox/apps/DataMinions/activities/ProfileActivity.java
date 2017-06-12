package br.com.geekfox.apps.DataMinions.activities;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.geekfox.apps.DataMinions.R;
import br.com.geekfox.apps.DataMinions.data.DataSource;
import br.com.geekfox.apps.DataMinions.domain.UserMinion;
import br.com.geekfox.apps.DataMinions.helpers.PreferencesHelper;
import br.com.geekfox.apps.DataMinions.json.UserData;
import br.com.geekfox.apps.DataMinions.utils.AppUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by RafaelBr on 06/05/2015.
 */
public class ProfileActivity extends Activity {

    UserData userData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ViewGroup v = (ViewGroup) findViewById(R.id.mainLayout);
        AppUtils.setFont(this, v);

        loadUserData();

    }

    public void loadUserData() {

        userData = PreferencesHelper.loadUserData(this);

        TextView txtName = (TextView) findViewById(R.id.txtProfileName);
        TextView txtTitle = (TextView) findViewById(R.id.txtProfileTitle);
        TextView txtLevel = (TextView) findViewById(R.id.txtLevel);
        TextView txtXP = (TextView) findViewById(R.id.txtXP);
        TextView txtGold = (TextView) findViewById(R.id.txtGold);
        TextView txtMoney = (TextView) findViewById(R.id.txtMoney);
        TextView txtMinionQt = (TextView) findViewById(R.id.txtMinionsQt);

        ImageView imgProfile = (ImageView) findViewById(R.id.imgAvatar);

        txtName.setText(userData.getNickname());
        txtLevel.setText("" + userData.getLevel());
        txtMoney.setText("" + userData.getMoney());
        txtGold.setText("" + userData.getGold());
        txtXP.setText("" + userData.getXp());

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

        txtTitle.setText(levelLicense);

        try {
            byte[] rawImage = Base64.decode(userData.getImage64(), Base64.DEFAULT);
            ByteArrayInputStream stream = new ByteArrayInputStream(rawImage);
            imgProfile.setImageBitmap(BitmapFactory.decodeStream(stream));
            stream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Oops, something went wrong, please restart the system!", Toast.LENGTH_LONG).show();
        }

        try {
            DataSource ds = new DataSource(this);
            ds.open();
            List<UserMinion> list = ds.retrieveUserMinions();
            ds.close();

            txtMinionQt.setText("" + list.size());
        }
        catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "Oops, something went wrong, please restart the system!", Toast.LENGTH_LONG).show();
        }
    }
}