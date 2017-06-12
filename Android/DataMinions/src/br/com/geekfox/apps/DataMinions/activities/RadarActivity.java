package br.com.geekfox.apps.DataMinions.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import br.com.geekfox.apps.DataMinions.R;
import br.com.geekfox.apps.DataMinions.utils.AppUtils;

/**
 * Created by RafaelBr on 06/05/2015.
 */
public class RadarActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);

        ViewGroup v = (ViewGroup) findViewById(R.id.mainLayout);
        AppUtils.setFont(this, v);
    }
}