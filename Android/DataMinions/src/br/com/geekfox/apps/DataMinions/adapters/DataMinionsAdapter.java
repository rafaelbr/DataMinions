package br.com.geekfox.apps.DataMinions.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.geekfox.apps.DataMinions.R;
import br.com.geekfox.apps.DataMinions.domain.UserMinion;
import br.com.geekfox.apps.DataMinions.utils.AppUtils;

import java.util.List;

/**
 * Created by rafaelbrasileiro on 24/04/14.
 */
public class DataMinionsAdapter extends ArrayAdapter<UserMinion> {

    int resourceId;
    List<UserMinion> list;

    public DataMinionsAdapter(Context context, int resource, List<UserMinion> objects) {
        super(context, resource, objects);
        list = objects;
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(resourceId, null);
        UserMinion minion = list.get(position);
        TextView txtName = (TextView) v.findViewById(R.id.txtMinionName);
        TextView txtLevel = (TextView) v.findViewById(R.id.txtMinionLevel);
        TextView txtStatus = (TextView) v.findViewById(R.id.txtMinionStatus);
        ImageView imgView = (ImageView) v.findViewById(R.id.imgMinion);



        txtName.setText(minion.getNickname());
        txtLevel.setText(""+minion.getLevel());
        txtStatus.setText(minion.getStatus());
        String imgName = "dm";
        if (minion.getDataminionId() < 10) {
            imgName += "00" + minion.getDataminionId();
        }
        else if (minion.getId() < 100) {
            imgName += "0" + minion.getDataminionId();
        }
        imgView.setImageResource(parent.getResources().getIdentifier(imgName, "drawable", parent.getContext().getPackageName()));

        ViewGroup group = (ViewGroup) v.findViewById(R.id.dmlistLayout);
        AppUtils.setFont(getContext(), group);

        return v;
    }
}
