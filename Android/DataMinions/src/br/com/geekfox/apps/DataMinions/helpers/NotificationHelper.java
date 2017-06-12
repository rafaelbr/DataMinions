package br.com.geekfox.apps.DataMinions.helpers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import br.com.geekfox.apps.DataMinions.R;

/**
 * Created by rafaelbrasileiro on 20/05/14.
 */
public class NotificationHelper {

    public static void SendNotification(Context context, String title, String msg, Class<?> cls) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent it = new Intent(context, cls);
        it.putExtra("title", title);
        it.putExtra("msg", msg);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, it, PendingIntent.FLAG_ONE_SHOT);

        Notification n =  new Notification.Builder(context)
                .setContentTitle(title)
                .setContentText(msg)
                //.setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setTicker(msg)
                .setLights(0xff1000ff, 1000, 1000)
                .build();

        nm.notify(0, n);
    }
}
