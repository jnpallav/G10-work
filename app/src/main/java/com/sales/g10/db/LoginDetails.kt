package com.sales.g10.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class LoginDetails {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    @ColumnInfo(name = "username")
    var gUserName: String? = null

    @ColumnInfo(name = "password")
    var gPassword: String? = null
}