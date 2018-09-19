package com.example.valentindejoue.smartiris.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.valentindejoue.smartiris.dao.ArticleDao
import com.example.valentindejoue.smartiris.dao.ClientDao
import com.example.valentindejoue.smartiris.dao.CommandeDao
import com.example.valentindejoue.smartiris.models.Article
import com.example.valentindejoue.smartiris.models.ArticleCommande
import com.example.valentindejoue.smartiris.models.Client
import com.example.valentindejoue.smartiris.models.Commande

@Database(entities = arrayOf(Client::class, Commande::class, ArticleCommande::class, Article::class),version = 1)
abstract class SmartIrisDatabase : RoomDatabase()
{
    abstract fun ClientDao():ClientDao
    abstract fun CommandeDao():CommandeDao
    abstract fun ArticleCommande():ArticleCommande
    abstract fun ArticleDao():ArticleDao

    companion object
    {
        private var INSTANCE: SmartIrisDatabase? = null
        fun getInstance(context: Context): SmartIrisDatabase? {
            if (INSTANCE == null) {
                synchronized(SmartIrisDatabase::class)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SmartIrisDatabase::class.java, "smartiris.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}