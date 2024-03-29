package com.eebbk.coolweather.app.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {

    /**
     * Province 表建表语言  省份
     */
    public static final String CREATE_PROVINCE = "create table Province("
       +"id integer primary key autoincrement, "
       +"province_name text, " 
       +"province_code text)";
    /**
     * City 表建表语言  城市
     */
    public static final String CREATE_CITY = "create table City("
        +"id integer primary key autoincrement, "
        +"city_name text, " 
        +"city_code text, "
        +"province_id integer)";
    /**
     * County 表建表语言  县
     */
    public static final String CREATE_COUNTY = "create table County("
        +"id integer primary key autoincrement, "
        +"county_name text, "   
        +"county_code text, "
        +"city_id integer)";
 
    
    public CoolWeatherOpenHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_PROVINCE); //创建Province 省份表
            db.execSQL(CREATE_CITY); //创建City 城市表
            db.execSQL(CREATE_COUNTY);  //创建County 县级表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}
