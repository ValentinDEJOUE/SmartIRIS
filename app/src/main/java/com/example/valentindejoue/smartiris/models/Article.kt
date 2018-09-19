package com.example.valentindejoue.smartiris.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Article")
data class Article(
        @PrimaryKey(autoGenerate = true) var id:Long?,
        @ColumnInfo(name="nom") var Nom : String,
        @ColumnInfo(name="poids") var Poids:Float,
        @ColumnInfo(name="prix") var Prix: Float)
{

}