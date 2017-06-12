package br.com.geekfox.apps.DataMinions.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by RafaelBr on 04/05/2015.
 */
public class AppUtils {
    public static void setFont(Context context, ViewGroup v) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/OCRA.ttf");
        for(int i = 0; i < v.getChildCount(); i++) {
            View view = v.getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTypeface(tf);
            }
            //traverse recursively
            else if (view instanceof ViewGroup) {
                setFont(context, (ViewGroup) view);
            }
        }
    }

    public static String computeMD5Hash(String password)
    {

        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer MD5Hash = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
            {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                MD5Hash.append(h);
            }

            return MD5Hash.toString();

        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return null;

    }

    private static String convertToHex(byte[] data) throws java.io.IOException
    {


        StringBuffer sb = new StringBuffer();
        String hex=null;

        hex= Base64.encodeToString(data, 0, data.length, 0);

        sb.append(hex);

        return sb.toString();
    }
}
