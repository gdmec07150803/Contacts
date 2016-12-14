package cn.edu.gdmec.w07150837.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.Vector;

/**
 * Created by weiruibo on 12/12/16.
 */

public class ContactsTable {

    private final static String TABLENAME = "contactsTable";

    private MyDB db;

    public ContactsTable(Context context) {

        db = new MyDB(context);

        if (!db.isTableExists(TABLENAME)) {

            String createTableSql = "create table if not exists " + TABLENAME + "(id_DB integer primary key autoincrement," +
                    User.NAME + " varchar not null," +
                    User.MOBILE + " varchar not null," +
                    User.QQ + " varchar not null," +
                    User.COMPANY + " varchar not null," +
                    User.ADDRESS + " varchar not null)";
            db.createTable(createTableSql);

        }
    }

    public boolean addData(User user) {

        ContentValues values = new ContentValues();

        values.put(User.NAME, user.getName());
        values.put(User.MOBILE, user.getMobile());
        values.put(User.COMPANY, user.getCompany());
        values.put(User.QQ, user.getQq());
        values.put(User.ADDRESS, user.getAddress());

        return db.save(TABLENAME, values);

    }

    public User[] getAllUser() {
        Vector<User> v = new Vector<User>();
        Cursor cursor = null;
        try {
            cursor = db.find("select * from " + TABLENAME, null);
            while (cursor.moveToNext()) {
                User temp = new User();

                temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
                temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
                temp.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
                temp.setMobile(cursor.getString(cursor.getColumnIndex(User.MOBILE)));
                temp.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));
                temp.setCompany(cursor.getString(cursor.getColumnIndex(User.COMPANY)));

                v.add(temp);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.closeConnection();
        }
        if (v.size() > 0) {
            return v.toArray(new User[]{});
        } else {
            User[] users = new User[1];
            User user = new User();
            user.setName("木有结果");
            users[0] = user;
            return users;
        }
    }

    public User getUserById(int id) {
        Cursor cursor = null;
        User temp = new User();
        try {
            cursor = db.find("select * from " + TABLENAME + " where " +
                    " id_DB=?", new String[]{id + ""});
            cursor.moveToNext();
            temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
            temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
            temp.setMobile(cursor.getString(cursor.getColumnIndex(User.MOBILE)));
            temp.setCompany(cursor.getString(cursor.getColumnIndex(User.COMPANY)));
            temp.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
            temp.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));
            Log.d("test", temp.getName());
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.closeConnection();
        }
        return null;
    }

    public boolean updataUser(User user) {
        ContentValues values = new ContentValues();
        values.put(User.NAME, user.getName());
        values.put(User.MOBILE, user.getMobile());
        values.put(User.COMPANY, user.getCompany());
        values.put(User.QQ, user.getQq());
        values.put(User.ADDRESS, user.getAddress());
        return db.update(TABLENAME, values, " id_DB=?", new String[]{user.getId_DB() + ""});

    }

    public User[] findUserByKey(String key) {
        Vector<User> v = new Vector<User>();
        Cursor cursor = null;

        try {
            cursor = db.find("select * from " + TABLENAME + " where " +
                    User.NAME + " like '%" + key + "%' " +
                    " or " + User.MOBILE + " like '%" + key + "%' " +
                    " or " + User.QQ + " like '%" + key + "%' ", null);
            while (cursor.moveToNext()) {
                User temp = new User();
                temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
                temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
                temp.setMobile(cursor.getString(cursor.getColumnIndex(User.MOBILE)));
                temp.setCompany(cursor.getString(cursor.getColumnIndex(User.COMPANY)));
                temp.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
                temp.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));

                v.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.closeConnection();
        }
        if (v.size() > 0) {
            return v.toArray(new User[]{});
        } else {
            User[] users = new User[1];
            User user = new User();
            user.setName("无结果");
            users[0] = user;
            return users;
        }
    }

    public boolean deleteByUser(User user) {
        return db.delete(TABLENAME, " id_DB=?", new String[]{user.getId_DB() + ""});
    }
}
