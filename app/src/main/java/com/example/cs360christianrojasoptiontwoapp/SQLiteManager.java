package com.example.cs360christianrojasoptiontwoapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

    public class SQLiteManager extends SQLiteOpenHelper
    {
        private static SQLiteManager sqLiteManager;

        private static  final String TAG = "SQLiteManager";

        private static final String DATABASE_NAME = "EventDB";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_NAME = "Event";
        private static final String COUNTER = "Counter";

        private static final String ID_FIELD = "id";
        private static final String NAME_FIELD = "name";
        private static final String DESC_FIELD = "desc";
        private static final String DELETED_FIELD = "deleted";

        @SuppressLint("SimpleDateFormat")
        private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

        public SQLiteManager(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public static SQLiteManager instanceOfDatabase(Context context)
        {
            if(sqLiteManager == null)
                sqLiteManager = new SQLiteManager(context);

            return sqLiteManager;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase)
        {
            //define table structure
            sqLiteDatabase.execSQL("Create table if not exists User (id integer primary key autoincrement, email text, password text)");
            StringBuilder sql;
            sql = new StringBuilder()
                    .append("CREATE TABLE ")
                    .append(TABLE_NAME)
                    .append("(")
                    .append(COUNTER)
                    .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                    .append(ID_FIELD)
                    .append(" INT, ")
                    .append(NAME_FIELD)
                    .append(" TEXT, ")
                    .append(DESC_FIELD)
                    .append(" TEXT, ")
                    .append(DELETED_FIELD)
                    .append(" TEXT)");

            sqLiteDatabase.execSQL(sql.toString());
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            sqLiteDatabase.execSQL("drop table if exists User");
            onCreate(sqLiteDatabase);
        }

        //registered user data
//        public void registerUser(LoginData data) {
//            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("email", data.getEmail());
//            contentValues.put("password", data.getPassword());
//            long user = sqLiteDatabase.insert("User", null, contentValues);
//
//            if(user != -1) {
//                Log.e(TAG, "registerUser: User Registered successfully");
//            }
//            else{
//                Log.e(TAG, "registerUser: error");
//            }
//        }

        //login user data
//        public void loginUser(LoginData data) {
//            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//            Cursor cursor = sqLiteDatabase.rawQuery("select * from User", null, null);
//
//            while(cursor.moveToNext()) {
//                String email = cursor.getString(1);
//                String password = cursor.getString(2);
//                if(email.equals(data.getEmail()) && password.equals(data.getPassword())){
//                    Log.e(TAG, "loginUser: User Login Successful");
//                }
//                else {
//                    Log.e(TAG, "LoginUser: login error");
//                }
//            }
//        }

        public void addEventToDatabase(Event event)
        {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

            //write event data to its respective field
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID_FIELD, event.getId());
            contentValues.put(NAME_FIELD, event.getName());
            contentValues.put(DESC_FIELD, event.getDescription());
            contentValues.put(DELETED_FIELD, getStringFromDate(event.getDeleted()));

            sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        }

        public void populateEventListArray()
        {
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

            try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null))
            {
                if(result.getCount() != 0)
                {
                    while (result.moveToNext())
                    {
                        int id = result.getInt(1);
                        String title = result.getString(2);
                        String desc = result.getString(3);
                        String stringDeleted = result.getString(4);
                        Date deleted = getDateFromString(stringDeleted);
                        Event event = new Event(id,title,desc,deleted);
                        Event.eventArrayList.add(event);
                    }
                }
            }
        }

        //method to update an events data when the user makes a change
        public void updateEventInDB(Event event)
        {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID_FIELD, event.getId());
            contentValues.put(NAME_FIELD, event.getName());
            contentValues.put(DESC_FIELD, event.getDescription());
            contentValues.put(DELETED_FIELD, getStringFromDate(event.getDeleted()));

            sqLiteDatabase.update(TABLE_NAME, contentValues, ID_FIELD + " =? ", new String[]{String.valueOf(event.getId())});
        }

        private String getStringFromDate(Date date)
        {
            if(date == null)
                return null;
            return dateFormat.format(date);
        }

        private Date getDateFromString(String string)
        {
            try
            {
                return dateFormat.parse(string);
            }
            catch (ParseException | NullPointerException e)
            {
                return null;
            }
        }
    }

