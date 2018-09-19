package com.example.valentindejoue.smartiris.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.example.valentindejoue.smartiris.models.Article

@Dao
interface ArticleDao {

    @Query("SELECT * FROM Article")
    fun getAll() : List<Article>

    @Insert(onConflict = REPLACE)
    fun insert(unArticle : Article)

    @Delete
    fun delete(unArticle : Article)

    @Update(onConflict = REPLACE)
    fun update(unArticle: Article)
}