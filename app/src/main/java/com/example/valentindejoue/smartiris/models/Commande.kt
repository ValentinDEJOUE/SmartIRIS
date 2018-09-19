package com.example.valentindejoue.smartiris.models

import android.arch.persistence.room.*
import com.example.valentindejoue.smartiris.models.Article

@Entity(tableName = "Commande", foreignKeys = [ForeignKey( entity = Client::class,parentColumns = arrayOf("id"), childColumns = arrayOf("idClient"))])
data class Commande(
        @PrimaryKey(autoGenerate = true) var id : Int,
        @ColumnInfo(name = "idClient") var idClient : Long,
        @ColumnInfo(name = "prixtotal") var PrixTotal : Float) {

}