package br.com.geekfox.apps.DataMinions.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.geekfox.apps.DataMinions.R;
import br.com.geekfox.apps.DataMinions.json.DataMinionData;
import br.com.geekfox.apps.DataMinions.utils.AppUtils;

import java.util.List;

/**
 * Created by RafaelBr on 07/05/2015.
 */
public class LibraryListAdapter extends ArrayAdapter<DataMinionData> {

    int resourceId;
    List<DataMinionData> list;

    public LibraryListAdapter(Context context, int resource, List<DataMinionData> objects) {
        super(context, resource, objects);
        list = objects;
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(resourceId, null);
        DataMinionData minion = list.get(position);
        TextView txtName = (TextView) v.findViewById(R.id.txtMinionName);
        TextView txtType = (TextView) v.findViewById(R.id.txtMinionType);

        ImageView imgView = (ImageView) v.findViewById(R.id.imgMinion);

        txtName.setText(minion.getName());
        txtType.setText(""+minion.getType());

        if (!minion.getType().equals("????")) {
            String imgName = "dm";
            if (minion.getId() < 10) {
                imgName += "00" + minion.getId();
            }
            else if (minion.getId() < 100) {
                imgName += "0" + minion.getId();
            }
            imgView.setImageResource(parent.getResources().getIdentifier(imgName, "drawable", parent.getContext().getPackageName()));
        }
        else {
            imgView.setImageResource(R.drawable.no_pic);
        }
        ViewGroup group = (ViewGroup) v.findViewById(R.id.librarylistLayout);
        AppUtils.setFont(getContext(), group);
        return v;
    }
}
