package br.com.geekfox.apps.DataMinions.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.geekfox.apps.DataMinions.domain.HubMinion;
import br.com.geekfox.apps.DataMinions.domain.UserMinion;
import br.com.geekfox.apps.DataMinions.helpers.DatabaseHelper;
import br.com.geekfox.apps.DataMinions.json.AttackData;
import br.com.geekfox.apps.DataMinions.json.DataHubData;
import br.com.geekfox.apps.DataMinions.json.DataMinionData;
import br.com.geekfox.apps.DataMinions.json.LibraryEntryData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafaelbrasileiro on 24/04/14.
 */
public class DataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public DataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void populateDataMinions(List<DataMinionData> dataMinions) {

        for(DataMinionData minionData : dataMinions) {
            ContentValues values = new ContentValues();
            values.put("dataminion_id", minionData.getId());
            values.put("type", minionData.getType());
            values.put("name", minionData.getName());
            values.put("description", minionData.getDescription());
            values.put("atk_base", minionData.getAtkBase());
            values.put("agi_base", minionData.getAgiBase());
            values.put("def_base", minionData.getDefBase());
            values.put("sta_base", minionData.getStaBase());
            database.insert("dataminions", null, values);
        }
    }

    public void clearDataMinions() {
        database.execSQL("delete from dataminions");
    }

    public void populateAttacks(List<AttackData> attacks) {

        for(AttackData attack : attacks) {
            ContentValues values = new ContentValues();

            values.put("attack_id", attack.getId());
            values.put("type", attack.getType());
            values.put("name", attack.getName());
            values.put("description", attack.getDescription());
            values.put("level", attack.getLevel());
            values.put("atk_power", attack.getAtkPower());
            values.put("def_bonus", attack.getDefBonus());
            values.put("agi_bonus", attack.getAgiBonus());
            values.put("atk_bonus", attack.getAtkBonus());
            values.put("sta_bonus", attack.getStaBonus());
            values.put("def_damage", attack.getDefDamage());
            values.put("agi_damage", attack.getAgiDamage());
            values.put("atk_damage", attack.getAtkDamage());
            values.put("sta_damage", attack.getStaDamage());
            values.put("status_apply", attack.getStatusApply());
            values.put("status_turns", attack.getStatusTurns());
            database.insert("attacks", null, values);
        }
    }

    public void clearAttacks() {
        database.execSQL("delete from attacks");
    }

    public List<DataMinionData> retrieveDataMinions() {
        List<DataMinionData> list = new ArrayList<DataMinionData>();
        Cursor cursor = database.rawQuery("select * from dataminions", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DataMinionData minion = new DataMinionData();
            minion.setId(cursor.getInt(0));
            minion.setType(cursor.getString(1));
            minion.setName(cursor.getString(2));
            minion.setDescription(cursor.getString(3));
            minion.setAtkBase(cursor.getInt(4));
            minion.setDefBase(cursor.getInt(5));
            minion.setStaBase(cursor.getInt(6));
            minion.setAgiBase(cursor.getInt(7));
            list.add(minion);
            cursor.moveToNext();
        }
        return list;
    }

    public List<DataMinionData> retrieveDataMinionsBasedOnLibrary() {
        int size = retrieveDataMinions().size();
        List<DataMinionData> list = new ArrayList<DataMinionData>();
        String sql = "select d.dataminion_id, d.type, d.name, d.description, l.locationName " +
                "from dataminions d left outer join library l on l.dataminion_id = d.dataminion_id";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DataMinionData minion = new DataMinionData();
            minion.setId(cursor.getInt(0));
            if (cursor.getString(4) == null || cursor.getString(4).isEmpty()) {
                minion.setType("????");
                minion.setName("????");
            }
            else {
                minion.setType(cursor.getString(1));
                minion.setName(cursor.getString(2));
                minion.setDescription(cursor.getString(3));
            }


            list.add(minion);
            cursor.moveToNext();
        }
        return list;
    }

    public LibraryEntryData retrieveLibraryEntry(int id) {
        Cursor cursor = database.rawQuery("select * from library where dataminion_id = " + id, null);
        cursor.moveToFirst();
        LibraryEntryData data = null;
        while (!cursor.isAfterLast()) {
            data = new LibraryEntryData();
            data.setDataMinionId(cursor.getInt(0));
            data.setLocation(cursor.getString(1));
            data.setLocationName(cursor.getString(2));
            cursor.moveToNext();
        }
        return data;
    }

    public DataMinionData retrieveDataMinion(int id) {
        Cursor cursor = database.rawQuery("select * from dataminions where dataminion_id = " + id, null);
        cursor.moveToFirst();
        DataMinionData dataMinionData = null;
        while (!cursor.isAfterLast()) {
            DataMinionData minion = new DataMinionData();
            minion.setId(cursor.getInt(0));
            minion.setType(cursor.getString(1));
            minion.setName(cursor.getString(2));
            minion.setDescription(cursor.getString(3));
            minion.setAtkBase(cursor.getInt(4));
            minion.setDefBase(cursor.getInt(5));
            dataMinionData = minion;
            cursor.moveToNext();
        }
        return dataMinionData;
    }

    public List<LibraryEntryData> retrieveLibraryEntries() {
        List<LibraryEntryData> list = new ArrayList<LibraryEntryData>();
        Cursor cursor = database.rawQuery("select l.dataminion_id, l.location, l.locationName, d.name from library l inner join dataminions d on d.dataminion_id = l.dataminion_id", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            LibraryEntryData entry = new LibraryEntryData();
            entry.setDataMinionId(cursor.getInt(0));
            entry.setLocation(cursor.getString(1));
            entry.setLocationName(cursor.getString(2));
            entry.setDataMinionName(cursor.getString(3));
            list.add(entry);
            cursor.moveToNext();
        }
        return list;
    }

    public void populateLibrary(List<LibraryEntryData> entries) {

        for(LibraryEntryData entry : entries) {
            ContentValues values = new ContentValues();
            values.put("dataminion_id", entry.getDataMinionId());
            values.put("location", entry.getLocation());
            values.put("locationName", entry.getLocationName());
            database.insert("library", null, values);
        }
    }

    public void clearLibrary() {
        database.execSQL("delete from library");
    }

    public void saveLibraryEntry(LibraryEntryData entry) {
        Cursor cursor = database.rawQuery("select * from library where dataminion_id = " + entry.getDataMinionId(), null);
        if (cursor.getCount() >= 0) {
            ContentValues values = new ContentValues();
            values.put("dataminion_id", entry.getDataMinionId());
            values.put("location", entry.getLocation());
            values.put("locationName", entry.getLocationName());
            database.insert("library", null, values);
        }
    }

    public void saveUserMinion(UserMinion minion) {
        //check if minion is new
        Cursor cursor = database.rawQuery("select * from userminions where userminion_id = " + minion.getId(), null);
        ContentValues values = new ContentValues();
        values.put("userminion_id", minion.getId());
        values.put("dataminion_id", minion.getDataminionId());
        values.put("nickname", minion.getNickname());
        values.put("level", minion.getLevel());
        values.put("xp", minion.getXp());
        values.put("status", minion.getStatus());
        values.put("health", minion.getHealth());
        values.put("atk1", minion.getAtk1Id());
        values.put("atk2", minion.getAtk2Id());
        values.put("atk3", minion.getAtk3Id());
        values.put("atk4", minion.getAtk4Id());
        if (cursor.getCount() <= 0) {
            database.insert("userminions", null, values);
        }
        else {
            database.update("userminions", values, "userminion_id = ?", new String[] {""+minion.getId()});
        }
    }

    public List<UserMinion> retrieveUserMinions() {
        List<UserMinion> list = new ArrayList<UserMinion>();
        Cursor cursor = database.rawQuery("select * from userminions", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            UserMinion minion = new UserMinion();
            minion.setId(cursor.getInt(0));
            minion.setDataminionId(cursor.getInt(1));
            minion.setNickname(cursor.getString(2));
            minion.setLevel(cursor.getInt(3));
            minion.setXp(cursor.getInt(4));
            minion.setStatus(cursor.getString(5));
            minion.setHealth(cursor.getInt(6));
            minion.setAtk1Id(cursor.getInt(7));
            minion.setAtk2Id(cursor.getInt(8));
            minion.setAtk3Id(cursor.getInt(9));
            minion.setAtk4Id(cursor.getInt(10));
            list.add(minion);
            cursor.moveToNext();
        }

        return list;
    }

    public int getMeanLevelFromMinions() {
        Cursor cursor = database.rawQuery("select sum(level), count(userminion_id) from userminions", null);
        int sum = 0;
        int count = 0;
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            sum = cursor.getInt(0);
            count = cursor.getInt(1);
            cursor.moveToNext();
        }
        if (count > 0)
            return sum/count;
        else
            return 0;

    }

    public UserMinion retrieveUserMinion(int id) {
        Cursor cursor = database.rawQuery("select * from userminions where userminion_id = " + id, null);
        cursor.moveToFirst();
        UserMinion userMinion = null;
        while (!cursor.isAfterLast()) {
            UserMinion minion = new UserMinion();
            minion.setId(cursor.getInt(0));
            minion.setDataminionId(cursor.getInt(1));
            minion.setNickname(cursor.getString(2));
            minion.setLevel(cursor.getInt(3));
            minion.setXp(cursor.getInt(4));
            minion.setStatus(cursor.getString(5));
            minion.setHealth(cursor.getInt(6));
            minion.setAtk1Id(cursor.getInt(7));
            minion.setAtk2Id(cursor.getInt(8));
            minion.setAtk3Id(cursor.getInt(9));
            minion.setAtk4Id(cursor.getInt(10));
            userMinion = minion;
            cursor.moveToNext();
        }

        return userMinion;
    }

    public void clearUserMinions() {
        database.execSQL("delete from userminions");
    }

    public void populateUserMinions(List<DataMinionData> list) {

        for(DataMinionData minionData : list) {
            UserMinion minion = new UserMinion();
            if (minionData.getNickname() == null || minionData.getNickname().isEmpty()) {
                minion.setNickname(minionData.getName());
            }
            else {
                minion.setNickname(minionData.getNickname());
            }
            minion.setId(minionData.getId());
            minion.setDataminionId(minionData.getMinionId());
            minion.setHealth(10 + (minionData.getStaBase() + minionData.getLevel() - 1) * minionData.getLevel());
            minion.setLevel(minionData.getLevel());
            minion.setXp(minionData.getXp());
            minion.setAtk1Id(minionData.getSlot1AtkId());
            minion.setAtk2Id(minionData.getSlot2AtkId());
            minion.setAtk3Id(minionData.getSlot3AtkId());
            minion.setAtk4Id(minionData.getSlot4AtkId());
            minion.setStatus("OK");

            ContentValues values = new ContentValues();
            values.put("userminion_id", minion.getId());
            values.put("dataminion_id", minion.getDataminionId());
            values.put("nickname", minion.getNickname());
            values.put("level", minion.getLevel());
            values.put("xp", minion.getXp());
            values.put("status", minion.getStatus());
            values.put("health", minion.getHealth());
            values.put("atk1", minion.getAtk1Id());
            values.put("atk2", minion.getAtk2Id());
            values.put("atk3", minion.getAtk3Id());
            values.put("atk4", minion.getAtk4Id());

            database.insert("userminions", null, values);

        }

    }

    public AttackData getAttackById(int id) {
        if (id == 0) return null;

        AttackData atk = new AttackData();
        Cursor cursor = database.rawQuery("select * from attacks where attack_id = " + id, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            atk.setId(cursor.getInt(0));
            atk.setType(cursor.getString(1));
            atk.setName(cursor.getString(2));
            atk.setDescription(cursor.getString(3));
            atk.setLevel(cursor.getInt(4));
            atk.setAtkPower(cursor.getInt(5));
            atk.setDefBonus(cursor.getInt(6));
            atk.setAgiBonus(cursor.getInt(7));
            atk.setStaBonus(cursor.getInt(8));
            atk.setDefDamage(cursor.getInt(9));
            atk.setAgiDamage(cursor.getInt(10));
            atk.setAtkDamage(cursor.getInt(11));
            atk.setStaDamage(cursor.getInt(12));
            atk.setStatusApply(cursor.getString(13));
            atk.setStatusTurns(cursor.getInt(14));
        }

        return atk;
    }

    public List<AttackData> getAttacksByLevelAndType(int level, String type) {
        List<AttackData> list = new ArrayList<AttackData>();
        Cursor cursor = database.rawQuery("select * from attacks where level <= " + level + " and type = '" + type + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            AttackData atk = new AttackData();

            atk.setId(cursor.getInt(0));
            atk.setType(cursor.getString(1));
            atk.setName(cursor.getString(2));
            atk.setDescription(cursor.getString(3));
            atk.setLevel(cursor.getInt(4));
            atk.setAtkPower(cursor.getInt(5));
            atk.setDefBonus(cursor.getInt(6));
            atk.setAgiBonus(cursor.getInt(7));
            atk.setStaBonus(cursor.getInt(8));
            atk.setDefDamage(cursor.getInt(9));
            atk.setAgiDamage(cursor.getInt(10));
            atk.setAtkDamage(cursor.getInt(11));
            atk.setStaDamage(cursor.getInt(12));
            atk.setStatusApply(cursor.getString(13));
            atk.setStatusTurns(cursor.getInt(14));
            list.add(atk);
            cursor.moveToNext();
        }

        return list;
    }

    public void clearHubData() {
        database.execSQL("delete from minionshub");
        database.execSQL("delete from datahubs");
    }

    public void populateHubs(List<DataHubData> list) {
        for(DataHubData hubData : list) {
            ContentValues values = new ContentValues();
            values.put("hub_id", hubData.getId());
            values.put("type", hubData.getType());
            values.put("location", hubData.getLocation());
            values.put("name", hubData.getName());
            database.insert("datahubs", null, values);
            for (String data : hubData.getMinionsData()) {
                String[] sdata = data.split(";");
                ContentValues dataValues = new ContentValues();
                dataValues.put("hub_id", hubData.getId());
                dataValues.put("minion_id", sdata[0]);
                dataValues.put("location", sdata[1] + ";" + sdata[2]);
                database.insert("minionshub", null, dataValues);
            }
        }
    }

    public List<DataHubData> retrieveHubs() {
        List<DataHubData> list = new ArrayList<DataHubData>();
        Cursor cursor = database.rawQuery("select * from datahubs", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DataHubData hub = new DataHubData();
            hub.setId(cursor.getInt(0));
            hub.setName(cursor.getString(1));
            hub.setType(cursor.getString(2));
            hub.setLocation(cursor.getString(3));
            list.add(hub);
            cursor.moveToNext();
        }

        return list;
    }

    public List<HubMinion> retrieveHubMinions(int id) {
        List<HubMinion> list = new ArrayList<HubMinion>();
        String sql = "select d.dataminion_id, d.type, d.name, d.description, d.atk_base, d.def_base, " +
                "d.agi_base, d.sta_base, m.location from dataminions d " +
                "inner join minionshub m on d.dataminion_id = m.minion_id " +
                "where m.hub_id = " + id;
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DataMinionData minion = new DataMinionData();
            minion.setId(cursor.getInt(0));
            minion.setType(cursor.getString(1));
            minion.setName(cursor.getString(2));
            minion.setDescription(cursor.getString(3));
            minion.setAtkBase(cursor.getInt(4));
            minion.setDefBase(cursor.getInt(5));
            minion.setAgiBase(cursor.getInt(6));
            minion.setStaBase(cursor.getInt(7));
            HubMinion hubMinion = new HubMinion();
            hubMinion.setHub_id(id);
            hubMinion.setMinion_id(cursor.getInt(0));
            hubMinion.setMinionData(minion);
            hubMinion.setLocation(cursor.getString(8));
            list.add(hubMinion);
            cursor.moveToNext();
        }
        return list;
    }
}
