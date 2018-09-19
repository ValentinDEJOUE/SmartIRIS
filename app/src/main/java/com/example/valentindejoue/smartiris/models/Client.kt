package com.example.valentindejoue.smartiris.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Client")
data class Client(
        @PrimaryKey(autoGenerate = true) var id: Int?,
        @ColumnInfo(name = "nom") var Nom: String,
        @ColumnInfo(name = "adresse") var Adresse: String,
        @ColumnInfo(name = "societe") var Société: String) {}