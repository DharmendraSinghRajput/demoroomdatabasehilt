package com.example.demoroomdatabasehilt.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contact")
class UserModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id:Long,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "contact_number")
    var contact_number:String,
    @ColumnInfo(name = "image", typeAffinity =ColumnInfo.BLOB)
    var image:Bitmap

    )

