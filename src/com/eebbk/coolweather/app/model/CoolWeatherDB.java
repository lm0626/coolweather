package com.eebbk.coolweather.app.model;

import java.util.ArrayList;
import java.util.List;

import com.eebbk.coolweather.app.db.CoolWeatherOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB {
    
    /**
     * 数据库名
     */
    public static final String DB_NAME = "cool_weather";
    
    /**
     * 数据库版本号
     */
    public static final int VERSION = 1;

    
    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;
    
    /**
     * 将构造方法私有化
     */
    private CoolWeatherDB(Context context){
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }
    
    /**
     * 获取CoolWeatherDB 的实例
     */
    public synchronized static CoolWeatherDB getInstance(Context context){
        if(null != coolWeatherDB){
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }
    
    /**
     * 将Province实例存储到数据库中
     */
    public void saveProvince(Province province){
        if(null != province ){
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            db.insert("Province", null, values);
        }
    }
    
    /**
     * 从数据库中读取全国所有的省份信息
     */
    public List<Province> loadProvinces(){
        List<Province> mList = new ArrayList<Province>();
        Cursor mCursor = db.query("Province", null, null, null, null, null, null);
        if(mCursor.moveToFirst()){
            do{
               Province mProvince = new Province();
               mProvince.setId(mCursor.getInt(mCursor.getColumnIndex("id")));
               mProvince.setProvinceName(mCursor.getString(mCursor.getColumnIndex("province_name")));
               mProvince.setProvinceCode(mCursor.getString(mCursor.getColumnIndex("province_code")));
               mList.add(mProvince);
            }while(mCursor.moveToNext());
        }
        return mList;
    }
    
    /**
     * 将City实例存储到数据库中
     */
    public void saveCity(City city){
        if(null != city ){
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityCode());
            values.put("province_id", city.getProvinceId());
            db.insert("City", null, values);
        }
    }
    
    /**
     * 从数据库中读取某省下所有的城市信息
     */
    public List<City> loadCities(int provinceId){
        List<City> mList = new ArrayList<City>();
        Cursor mCursor = db.query("City", null, "province_id = ?", new String[]{String.valueOf(provinceId)}, null, null, null);
        if(mCursor.moveToFirst()){
            do{
               City mCity = new City();
               mCity.setId(mCursor.getInt(mCursor.getColumnIndex("id")));
               mCity.setCityName(mCursor.getString(mCursor.getColumnIndex("city_name")));
               mCity.setCityCode(mCursor.getString(mCursor.getColumnIndex("city_code")));
               mCity.setProvinceId(provinceId);
               mList.add(mCity);
            }while(mCursor.moveToNext());
        }
        return mList;
    }
    
    
    /**
     * 将County实例存储到数据库中
     */
    public void saveCounty(County county){
        if(null != county ){
            ContentValues values = new ContentValues();
            values.put("county_name", county.getCountyName());
            values.put("county_code", county.getCountyCode());
            values.put("city_id", county.getCityId());
            db.insert("County", null, values);
        }
    }
    
    /**
     * 从数据库中读取某城市下所有的县级信息
     */
    public List<County> loadCounties(int cityId){
        List<County> mList = new ArrayList<County>();
        Cursor mCursor = db.query("County", null, "city_id = ?", new String[]{String.valueOf(cityId)}, null, null, null);
        if(mCursor.moveToFirst()){
            do{
               County mCounty = new County();
               mCounty.setId(mCursor.getInt(mCursor.getColumnIndex("id")));
               mCounty.setCountyName(mCursor.getString(mCursor.getColumnIndex("County_name")));
               mCounty.setCountyCode(mCursor.getString(mCursor.getColumnIndex("County_code")));
               mCounty.setCityId(cityId);
               mList.add(mCounty);
            }while(mCursor.moveToNext());
        }
        return mList;
    }
}
