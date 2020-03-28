package com.sales.g10.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoginDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLoginDetail(
        loginDetails: LoginDetails
    ): Long?

    @Query("SELECT password FROM LoginDetails where username =:gUserName")
    fun getUserNamePassword(gUserName:String): String?
}