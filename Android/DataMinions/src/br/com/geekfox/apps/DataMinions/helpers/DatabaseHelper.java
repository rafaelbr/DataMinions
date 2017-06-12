package br.com.geekfox.apps.DataMinions.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rafaelbrasileiro on 24/04/14.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "datascanner.db";
    private static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlDataMinion = "CREATE TABLE dataminions (" +
                "dataminion_id INT PRIMARY KEY," +
                "type TEXT," +
                "name TEXT," +
                "description TEXT," +
                "atk_base INT," +
                "def_base INT," +
                "agi_base INT," +
                "sta_base INT" +
                ");";
        String sqlUserMinions = "CREATE TABLE userminions (" +
                "userminion_id INT PRIMARY KEY," +
                "dataminion_id INT," +
                "nickname TEXT," +
                "level INT," +
                "xp INT," +
                "status TEXT," +
                "health INT," +
                "atk1 INT," +
                "atk2 INT," +
                "atk3 INT," +
                "atk4 INT" +
                ");";
        String sqlAttacks = "CREATE TABLE attacks (" +
                "attack_id INT PRIMARY KEY," +
                "type TEXT," +
                "name TEXT," +
                "description TEXT," +
                "level INT," +
                "atk_power INT," +
                "def_bonus INT," +
                "agi_bonus INT," +
                "atk_bonus INT," +
                "sta_bonus INT," +
                "def_damage INT," +
                "agi_damage INT," +
                "atk_damage INT," +
                "sta_damage INT," +
                "status_apply TEXT," +
                "status_turns INT" +
                ")";
        String sqlLibrary = "CREATE TABLE library (" +
                "dataminion_id INT PRIMARY KEY," +
                "location TEXT," +
                "locationName TEXT" +
                ")";

        String sqlHubs = "CREATE TABLE datahubs (" +
                "hub_id INT PRIMARY KEY," +
                "name TEXT," +
                "type TEXT," +
                "location TEXT" +
                ")";

        String sqlMinionHub = "CREATE TABLE minionshub (" +
                "hub_id INT," +
                "minion_id INT," +
                "location TEXT" +
                ")";

        sqLiteDatabase.execSQL(sqlDataMinion);
        sqLiteDatabase.execSQL(sqlAttacks);
        sqLiteDatabase.execSQL(sqlLibrary);
        sqLiteDatabase.execSQL(sqlUserMinions);
        sqLiteDatabase.execSQL(sqlHubs);
        sqLiteDatabase.execSQL(sqlMinionHub);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
