package com.example.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefHelper {

    private static final String PREFNAME = "userdata_student";
    Context context;

    public SharedPrefHelper(Context context){
        this.context = context;
    }


    public void storeStudentData(String name, String email, String photo, String gender, String phone, int[] status, String type){

        SharedPreferences sharedPref = context.getSharedPreferences(PREFNAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("photo", photo);
        editor.putString("gender", gender);
        editor.putString("phone", phone);
        editor.putString("type", type);
        editor.putInt("status1",status[0]);
        editor.putInt("status2",status[1]);
        editor.putInt("status3",status[2]);
        editor.putInt("status4",status[3]);
        editor.putInt("status5",status[4]);
        editor.putInt("status6",status[5]);

        editor.apply();

    }


    public void clearData(){

        SharedPreferences sharedPref = context.getSharedPreferences(PREFNAME, Context.MODE_PRIVATE);
        sharedPref.edit().clear().apply();

    }


    public String getUserData(String property){

        SharedPreferences sharedPref = context.getSharedPreferences(PREFNAME, Context.MODE_PRIVATE);
        return sharedPref.getString(property, null);

    }

    public int getUserData(String property, boolean flag){

        SharedPreferences sharedPref = context.getSharedPreferences(PREFNAME, Context.MODE_PRIVATE);
        return sharedPref.getInt(property, 0);
    }

}
