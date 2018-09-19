package com.example.valentindejoue.smartiris.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(
        tableName = "ArticleCommande",
        primaryKeys = ["idCommande","idCommande"],
        foreignKeys = [ForeignKey(
                            entity = Article::class,
                            parentColumns = ["id"],
                            childColumns = ["idArticle"]),
                        (ForeignKey(
                                entity = Commande::class,
                                parentColumns = ["id"],
                                childColumns = ["idCommande"])
                                )
                    ]
        )
data class ArticleCommande(@ColumnInfo(name = "idArticle") var idArticle : Long?,
                           @ColumnInfo(name = "idCommande") var idCommande : Long?,
                           @ColumnInfo(name = "quantite") var Quantite : Long,
                           @ColumnInfo(name = "prixtotal") var PrixTotal : Float) {
}