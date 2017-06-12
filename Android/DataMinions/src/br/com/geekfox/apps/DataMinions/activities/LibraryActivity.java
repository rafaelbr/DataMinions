package br.com.geekfox.apps.DataMinions.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import br.com.geekfox.apps.DataMinions.R;
import br.com.geekfox.apps.DataMinions.adapters.LibraryListAdapter;
import br.com.geekfox.apps.DataMinions.data.DataSource;
import br.com.geekfox.apps.DataMinions.json.DataMinionData;
import br.com.geekfox.apps.DataMinions.utils.AppUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by RafaelBr on 06/05/2015.
 */
public class LibraryActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        ViewGroup v = (ViewGroup) findViewById(R.id.mainLayout);
        AppUtils.setFont(this, v);

        retrieveLibraryData();
    }

    public void retrieveLibraryData() {
        try {
            DataSource ds = new DataSource(this);
            ds.open();
            final List<DataMinionData> dataList = ds.retrieveDataMinionsBasedOnLibrary();

            ds.close();




            LibraryListAdapter adapter = new LibraryListAdapter(this, R.layout.dmlist_layout, dataList);
            ListView listView = (ListView) findViewById(R.id.minionList);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    DataMinionData data = dataList.get(i);
                    if (!data.getType().equals("????")) {
                        Intent intent = new Intent(LibraryActivity.this, DataMinionInfoActivity.class);
                        intent.putExtra("dmID", data.getId());
                        startActivity(intent);
                    }
                }
            });

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}