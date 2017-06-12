package br.com.geekfox.apps.DataMinions.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.geekfox.apps.DataMinions.R;
import br.com.geekfox.apps.DataMinions.data.DataSource;
import br.com.geekfox.apps.DataMinions.domain.UserMinion;
import br.com.geekfox.apps.DataMinions.utils.AppUtils;
import org.w3c.dom.Text;

import java.sql.SQLException;

/**
 * Created by RafaelBr on 07/05/2015.
 */
public class MinionDataActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miniondata);

        ViewGroup v = (ViewGroup) findViewById(R.id.mainLayout);
        AppUtils.setFont(this, v);

        //get dataminion info
        int id = getIntent().getExtras().getInt("dmID");

        try {
            DataSource ds = new DataSource(this);
            ds.open();
            UserMinion minion = ds.retrieveUserMinion(id);

            TextView txtName = (TextView) findViewById(R.id.txtMinionName);
            TextView txtLevel = (TextView) findViewById(R.id.txtMinionLevel);
            TextView txtType = (TextView) findViewById(R.id.txtMinionType);
            TextView txtStatus = (TextView) findViewById(R.id.txtMinionStatus);

            TextView txtAttack1 = (TextView) findViewById(R.id.txtAttack1);
            TextView txtAttack2 = (TextView) findViewById(R.id.txtAttack2);
            TextView txtAttack3 = (TextView) findViewById(R.id.txtAttack3);
            TextView txtAttack4 = (TextView) findViewById(R.id.txtAttack4);

            ImageView imgView = (ImageView) findViewById(R.id.imgMinion);

            String imgName = "dm";
            if (minion.getDataminionId() < 10) {
                imgName += "00" +minion.getDataminionId();
            }
            else if (minion.getDataminionId() < 100) {
                imgName += "0" + minion.getDataminionId();
            }
            imgView.setImageResource(getResources().getIdentifier(imgName, "drawable", getPackageName()));

            txtName.setText(minion.getNickname());
            txtLevel.setText("" + minion.getLevel());
            txtStatus.setText(minion.getStatus());
            txtType.setText(ds.retrieveDataMinion(minion.getDataminionId()).getType());

            if (minion.getAtk1Id() != 0)
                txtAttack1.setText(ds.getAttackById(minion.getAtk1Id()).getName());
            if (minion.getAtk2Id() != 0)
                txtAttack2.setText(ds.getAttackById(minion.getAtk2Id()).getName());
            if (minion.getAtk3Id() != 0)
                txtAttack3.setText(ds.getAttackById(minion.getAtk3Id()).getName());
            if (minion.getAtk4Id() != 0)
                txtAttack4.setText(ds.getAttackById(minion.getAtk4Id()).getName());

            ds.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}