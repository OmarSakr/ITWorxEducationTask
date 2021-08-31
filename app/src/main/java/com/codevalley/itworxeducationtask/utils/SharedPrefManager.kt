package com.codevalley.itworxeducationtask.utils

import android.content.Context

class SharedPrefManager(var mContext: Context) {
    private val SHARED_PREF_NAME = "masari_shared"
    private val FIRST_TIME = "masari_shared_first_time"


    fun isFirstTime(): Boolean {
        val sharedPreferences = mContext.getSharedPreferences(
            SHARED_PREF_NAME, 0
        )
        return sharedPreferences.getBoolean(FIRST_TIME, true)
    }

    fun setFirstTime(status: Boolean?) {
        val sharedPreferences = mContext.getSharedPreferences(
            SHARED_PREF_NAME,
            0
        )
        val editor = sharedPreferences.edit()
        editor.putBoolean(FIRST_TIME, status!!)
        editor.apply()
    }


    /**
     * return userModel which hold all user data
     *
     * @return user model
     */
//    fun getUserDate(): Data {
//        val sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, 0)
//        val userModel = Data(
//            sharedPreferences.getInt("category_id", 0),
//            sharedPreferences.getString("category_name", "").toString(),
//            sharedPreferences.getInt("country_id", 0),
//            sharedPreferences.getString("email", "").toString(),
//            sharedPreferences.getInt("id", 0),
//            sharedPreferences.getString("image", "").toString(),
//            sharedPreferences.getString("mobile", "").toString(),
//            sharedPreferences.getString("status", "").toString(),
//            sharedPreferences.getString("token", "").toString(),
//            sharedPreferences.getString("username", "").toString(),
//            sharedPreferences.getString("verification_code", "").toString(),
//        )
//        return userModel
//    }


    /**
     * saving user data to be used in profile
     *
     * @param user is the model which hold all user data
     */
//    fun setUserDate(user: Data) {
//        val sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, 0)
//        val editor = sharedPreferences.edit()
//        editor.putInt("id", user.id)
//        editor.putInt("category_id", user.category_id)
//        editor.putInt("country_id", user.country_id)
//        editor.putString("category_name", user.category_name)
//        editor.putString("email", user.email)
//        editor.putString("image", user.image)
//        editor.putString("mobile", user.mobile)
//        editor.putString("status", user.status)
//        editor.putString("token", user.token)
//        editor.putString("username", user.username)
//        editor.putString("verification_code", user.verification_code)
//        editor.apply()
//    }


}