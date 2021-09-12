package com.example.noteapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
class Note (
        @ColumnInfo(name = "title") val noteTitle:String,
        @ColumnInfo(name = "description") val description:String,
        @ColumnInfo(name = "timestamp") val timeStamp:String
    )
{

    @PrimaryKey(autoGenerate = true) var id = 0;
}