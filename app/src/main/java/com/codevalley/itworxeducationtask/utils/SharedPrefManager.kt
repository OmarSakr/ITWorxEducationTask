package com.codevalley.itworxeducationtask.utils

import android.content.Context
import com.codevalley.itworxeducationtask.models.userModel.UserModel

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
    fun getUserDate(): UserModel {
        val sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, 0)
        val userModel = UserModel(
            sharedPreferences.getString("country", "").toString(),
            sharedPreferences.getString("firstCategory", "").toString(),
            sharedPreferences.getString("secondCategory", "").toString(),
            sharedPreferences.getString("thirdCategory", "").toString(),
        )
        return userModel
    }


    /**
     * saving user data to be used in profile
     *
     * @param user is the model which hold all user data
     */
    fun setUserDate(user: UserModel) {
        val sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, 0)
        val editor = sharedPreferences.edit()
        editor.putString("country", user.country)
        editor.putString("firstCategory", user.firstCategory)
        editor.putString("secondCategory", user.secondCategory)
        editor.putString("thirdCategory", user.thirdCategory)
        editor.apply()
    }


}