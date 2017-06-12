package br.com.geekfox.apps.DataMinions.activities;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import br.com.geekfox.apps.DataMinions.R;
import br.com.geekfox.apps.DataMinions.adapters.DataMinionsAdapter;
import br.com.geekfox.apps.DataMinions.data.DataSource;
import br.com.geekfox.apps.DataMinions.domain.UserMinion;
import br.com.geekfox.apps.DataMinions.helpers.PreferencesHelper;
import br.com.geekfox.apps.DataMinions.json.LibraryEntryData;
import br.com.geekfox.apps.DataMinions.utils.AppUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by RafaelBr on 06/05/2015.
 */
public class MinionsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minions);
        ViewGroup v = (ViewGroup) findViewById(R.id.mainLayout);
        AppUtils.setFont(this, v);

        try {
            DataSource ds = new DataSource(this);
            ds.open();
            final List<UserMinion> list = ds.retrieveUserMinions();

            ListView listView = (ListView) findViewById(R.id.minionsList);
            listView.setAdapter(new DataMinionsAdapter(this, R.layout.userminionlist_layout, list));
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    ClipData clipData = ClipData.newPlainText("minionId", "" + list.get(i).getId());
                    ImageView img = new ImageView(MinionsActivity.this);
                    String imgName = "dm";
                    int id = list.get(i).getDataminionId();
                    if (id < 10) {
                        imgName += "00" + id;
                    }
                    else if (id < 100) {
                        imgName += "0" + id;
                    }
                    img.setImageResource(getResources().getIdentifier(imgName, "drawable", getPackageName()));
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    view.startDrag(clipData, shadowBuilder, view, 0);

                    return true;
                }
            });
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MinionsActivity.this, MinionDataActivity.class);
                    intent.putExtra("dmID", list.get(i).getId());
                    startActivity(intent);
                }
            });

            ds.close();

            reloadSlots();

            final Drawable slot = getResources().getDrawable(R.drawable.slot);
            final Drawable slotselect = getResources().getDrawable(R.drawable.slotselected);

            //setup slots
            ViewGroup frame = (ViewGroup) findViewById(R.id.dmslot1);

            frame.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View view, DragEvent dragEvent) {
                    int action = dragEvent.getAction();
                    switch(action) {
                        case DragEvent.ACTION_DRAG_STARTED:
                            break;
                        case DragEvent.ACTION_DRAG_ENTERED:
                            view.setBackground(slotselect);
                            break;
                        case DragEvent.ACTION_DRAG_EXITED:
                            view.setBackground(slot);
                            break;
                        case DragEvent.ACTION_DROP:
                            //change slot prefs
                            int id = Integer.parseInt(dragEvent.getClipData().getItemAt(0).getText().toString());
                            PreferencesHelper.setSlotMinion(1, id, MinionsActivity.this);
                            view.setBackground(slot);
                            reloadSlots();
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });

            frame = (ViewGroup) findViewById(R.id.dmslot2);

            frame.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View view, DragEvent dragEvent) {
                    int action = dragEvent.getAction();
                    switch(action) {
                        case DragEvent.ACTION_DRAG_STARTED:
                            break;
                        case DragEvent.ACTION_DRAG_ENTERED:
                            view.setBackground(slotselect);
                            break;
                        case DragEvent.ACTION_DRAG_EXITED:
                            view.setBackground(slot);
                            break;
                        case DragEvent.ACTION_DROP:
                            //change slot prefs
                            int id = Integer.parseInt(dragEvent.getClipData().getItemAt(0).getText().toString());
                            PreferencesHelper.setSlotMinion(2, id, MinionsActivity.this);
                            view.setBackground(slot);
                            reloadSlots();
                            break;
                        default:
                            break;


                    }
                    return true;
                }
            });

            frame = (ViewGroup) findViewById(R.id.dmslot3);

            frame.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View view, DragEvent dragEvent) {
                    int action = dragEvent.getAction();
                    switch(action) {
                        case DragEvent.ACTION_DRAG_STARTED:
                            break;
                        case DragEvent.ACTION_DRAG_ENTERED:
                            view.setBackground(slotselect);
                            break;
                        case DragEvent.ACTION_DRAG_EXITED:
                            view.setBackground(slot);
                            break;
                        case DragEvent.ACTION_DROP:
                            //change slot prefs
                            int id = Integer.parseInt(dragEvent.getClipData().getItemAt(0).getText().toString());
                            PreferencesHelper.setSlotMinion(3, id, MinionsActivity.this);
                            view.setBackground(slot);
                            reloadSlots();
                            break;
                        default:
                            break;

                    }
                    return true;
                }
            });

            frame = (ViewGroup) findViewById(R.id.dmslot4);

            frame.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View view, DragEvent dragEvent) {
                    int action = dragEvent.getAction();
                    switch(action) {
                        case DragEvent.ACTION_DRAG_STARTED:
                            break;
                        case DragEvent.ACTION_DRAG_ENTERED:
                            view.setBackground(slotselect);
                            break;
                        case DragEvent.ACTION_DRAG_EXITED:
                            view.setBackground(slot);
                            break;
                        case DragEvent.ACTION_DROP:
                            //change slot prefs
                            int id = Integer.parseInt(dragEvent.getClipData().getItemAt(0).getText().toString());
                            PreferencesHelper.setSlotMinion(4, id, MinionsActivity.this);
                            view.setBackground(slot);
                            reloadSlots();
                            break;
                        default:
                            break;


                    }
                    return true;
                }
            });

            frame = (ViewGroup) findViewById(R.id.dmslot5);

            frame.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View view, DragEvent dragEvent) {
                    int action = dragEvent.getAction();
                    switch(action) {
                        case DragEvent.ACTION_DRAG_STARTED:
                            break;
                        case DragEvent.ACTION_DRAG_ENTERED:
                            view.setBackground(slotselect);
                            break;
                        case DragEvent.ACTION_DRAG_EXITED:
                            view.setBackground(slot);
                            break;
                        case DragEvent.ACTION_DROP:
                            //change slot prefs
                            int id = Integer.parseInt(dragEvent.getClipData().getItemAt(0).getText().toString());
                            PreferencesHelper.setSlotMinion(5, id, MinionsActivity.this);
                            view.setBackground(slot);
                            reloadSlots();
                            break;
                        default:
                            break;


                    }
                    return true;
                }
            });

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void reloadSlots() {
        DataSource ds = new DataSource(this);
        try {
            ds.open();
            ImageView imgSlot = (ImageView) findViewById(R.id.imgDMSlot1);
            String imgName = "dm";
            int id = PreferencesHelper.getSlotMinion(1, this);
            if (id > 0) {
                id = ds.retrieveUserMinion(id).getDataminionId();
                if (id < 10) {
                    imgName += "00" + id;
                } else if (id < 100) {
                    imgName += "0" + id;
                }
                imgSlot.setImageResource(getResources().getIdentifier(imgName, "drawable", getPackageName()));


            }
            else {
                imgSlot.setImageResource(0);
            }

            imgSlot = (ImageView) findViewById(R.id.imgDMSlot2);
            imgName = "dm";
            id = PreferencesHelper.getSlotMinion(2, this);
            if (id > 0) {
                id = ds.retrieveUserMinion(id).getDataminionId();
                if (id < 10) {
                    imgName += "00" + id;
                } else if (id < 100) {
                    imgName += "0" + id;
                }
                imgSlot.setImageResource(getResources().getIdentifier(imgName, "drawable", getPackageName()));


            }
            else {
                imgSlot.setImageResource(0);
            }

            imgSlot = (ImageView) findViewById(R.id.imgDMSlot3);
            imgName = "dm";
            id = PreferencesHelper.getSlotMinion(3, this);
            if (id > 0) {
                id = ds.retrieveUserMinion(id).getDataminionId();
                if (id < 10) {
                    imgName += "00" + id;
                } else if (id < 100) {
                    imgName += "0" + id;
                }
                imgSlot.setImageResource(getResources().getIdentifier(imgName, "drawable", getPackageName()));


            }
            else {
                imgSlot.setImageResource(0);
            }

            imgSlot = (ImageView) findViewById(R.id.imgDMSlot4);
            imgName = "dm";
            id = PreferencesHelper.getSlotMinion(4, this);
            if (id > 0) {
                id = ds.retrieveUserMinion(id).getDataminionId();
                if (id < 10) {
                    imgName += "00" + id;
                } else if (id < 100) {
                    imgName += "0" + id;
                }
                imgSlot.setImageResource(getResources().getIdentifier(imgName, "drawable", getPackageName()));


            }
            else {
                imgSlot.setImageResource(0);
            }

            imgSlot = (ImageView) findViewById(R.id.imgDMSlot5);
            imgName = "dm";
            id = PreferencesHelper.getSlotMinion(5, this);
            if (id > 0) {
                id = ds.retrieveUserMinion(id).getDataminionId();
                if (id < 10) {
                    imgName += "00" + id;
                } else if (id < 100) {
                    imgName += "0" + id;
                }
                imgSlot.setImageResource(getResources().getIdentifier(imgName, "drawable", getPackageName()));


            }
            else {
                imgSlot.setImageResource(0);
            }

            ds.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}