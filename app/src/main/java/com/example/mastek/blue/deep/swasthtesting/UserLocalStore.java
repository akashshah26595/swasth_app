package com.example.mastek.blue.deep.swasthtesting;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by akash on 16/3/16.
 */


    import android.content.Context;
    import android.content.SharedPreferences;

    /**
     * Created by akash on 26/1/16.
     */
    public class UserLocalStore {
        public static final String SP_NAME = "userDetails";
        SharedPreferences userLocalDatabase;

        public UserLocalStore(Context context){
            userLocalDatabase = context.getSharedPreferences(SP_NAME,0);
        }
        public void storeUserData(){
            SharedPreferences.Editor spEditor = userLocalDatabase.edit();
            spEditor.putString("username","");
            spEditor.putString("password","");
            spEditor.putString("age","");
            spEditor.putString("name","");
            spEditor.commit();
        }
        public void getLoggedInUser(){
            String username = userLocalDatabase.getString("username", "");
            String password = userLocalDatabase.getString("password","");
            String age = userLocalDatabase.getString("age","");
            String name = userLocalDatabase.getString("name","");

           // User storedUser = new User(name,username,password,age);

            //return  storedUser;
        }

        public void setLoggedInUser(Boolean loggedInUser){
            SharedPreferences.Editor spEditor = userLocalDatabase.edit();
            spEditor.putBoolean("loggedIn",loggedInUser);
            spEditor.commit();
        }

        public boolean getLoginStatus(){
            if(userLocalDatabase.getBoolean("loggedIn",false)==true){
                return true;
            }
            else{
                return false;
            }
        }
        public void clearUserData(){
            SharedPreferences.Editor spEditor = userLocalDatabase.edit();
            spEditor.clear();
            spEditor.commit();
        }

    }


