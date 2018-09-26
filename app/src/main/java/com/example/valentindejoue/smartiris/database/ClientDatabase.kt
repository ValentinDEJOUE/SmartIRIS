package com.example.valentindejoue.smartiris.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.valentindejoue.smartiris.dao.ClientDao
import com.example.valentindejoue.smartiris.database.ClientDatabase.Companion.DATABASE_VERSION
import com.example.valentindejoue.smartiris.models.Client

@Database(entities = arrayOf(Client::class), version = DATABASE_VERSION)
abstract class ClientDatabase : RoomDatabase() {
    abstract fun clientDAO(): ClientDao

    companion object {
        const val DATABASE_VERSION = 1
        val DATABASE_NAME = "SmartIRISDatabase"

        private var instance: ClientDatabase? = null
        fun getInstance(context: Context): ClientDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(context, ClientDatabase::class.java, DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build()
            return instance!!
        }
    }
}