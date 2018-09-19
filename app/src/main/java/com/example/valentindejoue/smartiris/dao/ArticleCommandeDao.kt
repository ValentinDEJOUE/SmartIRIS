package com.example.valentindejoue.smartiris.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.example.valentindejoue.smartiris.models.Article
import com.example.valentindejoue.smartiris.models.ArticleCommande

@Dao
interface ArticleCommandeDao {
    @Insert(onConflict = REPLACE)
    fun insert(unArticleCommande : ArticleCommande)

    @Query("SELECT * FROM Article A INNER JOIN ArticleCommande AC On A.id = AC.idArticle WHERE AC.idCommande=:pidCommande")
    fun getLesArticlesByCommande(pidCommande : Long) : List<Article>


}