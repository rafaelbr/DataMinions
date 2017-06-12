package br.com.geekfox.apps.DataMinions.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import br.com.geekfox.apps.DataMinions.json.UserData;

/**
 * Created by rafaelbrasileiro on 23/04/14.
 */
public class PreferencesHelper {

    private static final String FACEBOOKID_PREF = "facebook_id";
    private static final String DEVICEHASH_PREF = "device_hash";
    private static final String USERID_PREF = "userid";
    private static final String NICKNAME_PREF = "nickname";
    private static final String EMAIL_PREF = "email";
    private static final String IMAGE_PREF = "image64";
    private static final String LEVEL_PREF = "level";
    private static final String XP_PREF = "xp";
    private static final String MONEY_PREF = "money";
    private static final String GOLD_PREF = "gold";

    private static final String SLOT1_PREF = "minionslot1";
    private static final String SLOT2_PREF = "minionslot2";
    private static final String SLOT3_PREF = "minionslot3";
    private static final String SLOT4_PREF = "minionslot4";
    private static final String SLOT5_PREF = "minionslot5";

    public static String getValue(String key, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(key, "");
    }

    public static void setValue(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString(key, value);
        edit.apply();
    }

    public static void saveGCMHash(String hash, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString(DEVICEHASH_PREF, hash);
        edit.apply();
    }

    public static String retrieveGCMHash(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(DEVICEHASH_PREF, "");
    }

    public static void saveUserData(UserData data, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putInt(USERID_PREF, data.getId());
        edit.putString(FACEBOOKID_PREF, data.getFacebookId());
        edit.putString(NICKNAME_PREF, data.getNickname());
        edit.putString(EMAIL_PREF, data.getEmail());
        edit.putString(IMAGE_PREF, data.getImage64());
        edit.putInt(LEVEL_PREF, data.getLevel());
        edit.putInt(XP_PREF, data.getXp());
        edit.putInt(MONEY_PREF, data.getMoney());
        edit.putInt(GOLD_PREF, data.getGold());
        edit.apply();
    }

    public static UserData loadUserData(Context context) {
        UserData data = new UserData();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        data.setFacebookId(prefs.getString(FACEBOOKID_PREF, ""));
        data.setId(prefs.getInt(USERID_PREF, 0));
        data.setNickname(prefs.getString(NICKNAME_PREF, ""));
        data.setEmail(prefs.getString(EMAIL_PREF, ""));
        data.setImage64(prefs.getString(IMAGE_PREF, ""));
        data.setLevel(prefs.getInt(LEVEL_PREF, 0));
        data.setXp(prefs.getInt(XP_PREF, 0));
        data.setMoney(prefs.getInt(MONEY_PREF, 0));
        data.setGold(prefs.getInt(GOLD_PREF, 0));
        return data;
    }

    public static int getSlotMinion(int slot, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        switch(slot) {
            case 1: return prefs.getInt(SLOT1_PREF, 0);
            case 2: return prefs.getInt(SLOT2_PREF, 0);
            case 3: return prefs.getInt(SLOT3_PREF, 0);
            case 4: return prefs.getInt(SLOT4_PREF, 0);
            case 5: return prefs.getInt(SLOT5_PREF, 0);


        }
        return 0;
    }

    public static void setSlotMinion(int slot, int minionId, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = prefs.edit();
        switch(slot) {
            case 1:
                edit.putInt(SLOT1_PREF, minionId);
                if (prefs.getInt(SLOT2_PREF, 0) == minionId) {
                    edit.putInt(SLOT2_PREF, 0);
                }
                if (prefs.getInt(SLOT3_PREF, 0) == minionId) {
                    edit.putInt(SLOT3_PREF, 0);
                }
                if (prefs.getInt(SLOT4_PREF, 0) == minionId) {
                    edit.putInt(SLOT4_PREF, 0);
                }
                if (prefs.getInt(SLOT5_PREF, 0) == minionId) {
                    edit.putInt(SLOT5_PREF, 0);
                }
                break;
            case 2:
                edit.putInt(SLOT2_PREF, minionId);
                if (prefs.getInt(SLOT1_PREF, 0) == minionId) {
                    edit.putInt(SLOT1_PREF, 0);
                }
                if (prefs.getInt(SLOT3_PREF, 0) == minionId) {
                    edit.putInt(SLOT3_PREF, 0);
                }
                if (prefs.getInt(SLOT4_PREF, 0) == minionId) {
                    edit.putInt(SLOT4_PREF, 0);
                }
                if (prefs.getInt(SLOT5_PREF, 0) == minionId) {
                    edit.putInt(SLOT5_PREF, 0);
                }
                break;
            case 3:
                edit.putInt(SLOT3_PREF, minionId);
                if (prefs.getInt(SLOT2_PREF, 0) == minionId) {
                    edit.putInt(SLOT2_PREF, 0);
                }
                if (prefs.getInt(SLOT1_PREF, 0) == minionId) {
                    edit.putInt(SLOT1_PREF, 0);
                }
                if (prefs.getInt(SLOT4_PREF, 0) == minionId) {
                    edit.putInt(SLOT4_PREF, 0);
                }
                if (prefs.getInt(SLOT5_PREF, 0) == minionId) {
                    edit.putInt(SLOT5_PREF, 0);
                }
                break;
            case 4:
                edit.putInt(SLOT4_PREF, minionId);
                if (prefs.getInt(SLOT2_PREF, 0) == minionId) {
                    edit.putInt(SLOT2_PREF, 0);
                }
                if (prefs.getInt(SLOT3_PREF, 0) == minionId) {
                    edit.putInt(SLOT3_PREF, 0);
                }
                if (prefs.getInt(SLOT1_PREF, 0) == minionId) {
                    edit.putInt(SLOT1_PREF, 0);
                }
                if (prefs.getInt(SLOT5_PREF, 0) == minionId) {
                    edit.putInt(SLOT5_PREF, 0);
                }
                break;
            case 5:
                edit.putInt(SLOT5_PREF, minionId);
                if (prefs.getInt(SLOT2_PREF, 0) == minionId) {
                    edit.putInt(SLOT2_PREF, 0);
                }
                if (prefs.getInt(SLOT3_PREF, 0) == minionId) {
                    edit.putInt(SLOT3_PREF, 0);
                }
                if (prefs.getInt(SLOT4_PREF, 0) == minionId) {
                    edit.putInt(SLOT4_PREF, 0);
                }
                if (prefs.getInt(SLOT1_PREF, 0) == minionId) {
                    edit.putInt(SLOT1_PREF, 0);
                }
                break;

        }
        edit.apply();
    }



}
