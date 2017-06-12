package br.com.geekfox.apps.DataMinions.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.geekfox.apps.DataMinions.R;
import br.com.geekfox.apps.DataMinions.data.DataSource;
import br.com.geekfox.apps.DataMinions.json.DataMinionData;
import br.com.geekfox.apps.DataMinions.json.LibraryEntryData;
import br.com.geekfox.apps.DataMinions.utils.AppUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by RafaelBr on 07/05/2015.
 */
public class DataMinionInfoActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataminion);

        ViewGroup v = (ViewGroup) findViewById(R.id.dminfoLayout);
        AppUtils.setFont(this, v);

        //get dataminion info
        int id = getIntent().getExtras().getInt("dmID");

        try {
            DataSource ds = new DataSource(this);
            ds.open();
            DataMinionData dm = ds.retrieveDataMinion(id);

            TextView txtName = (TextView) findViewById(R.id.txtDMName);
            TextView txtType = (TextView) findViewById(R.id.txtDMType);
            TextView txtDesc = (TextView) findViewById(R.id.txtDesc);
            ImageView imgView = (ImageView) findViewById(R.id.imgMinion);

            txtName.setText(dm.getName());
            txtType.setText(dm.getType());
            txtDesc.setText(dm.getDescription());

            String imgName = "dm";
            if (dm.getId() < 10) {
                imgName += "00" + dm.getId();
            }
            else if (dm.getId() < 100) {
                imgName += "0" + dm.getId();
            }
            imgView.setImageResource(getResources().getIdentifier(imgName, "drawable", getPackageName()));

            List<LibraryEntryData> entries = ds.retrieveLibraryEntries();
            LibraryEntryData correctEntry = null;
            for(LibraryEntryData entry : entries) {
                if (entry.getDataMinionId() == dm.getId()) {
                    correctEntry = entry;
                    break;
                }
            }

            TextView txtLocation = (TextView) findViewById(R.id.txtLocation);
            txtLocation.setText((correctEntry == null) ? "" : correctEntry.getLocation());

            ds.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}