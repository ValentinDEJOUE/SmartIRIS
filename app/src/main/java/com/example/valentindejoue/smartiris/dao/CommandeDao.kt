package com.example.valentindejoue.smartiris.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.OnConflictStrategy.ABORT
import com.example.valentindejoue.smartiris.models.Client
import com.example.valentindejoue.smartiris.models.Commande

@Dao
interface CommandeDao {
    @Insert(onConflict = ABORT)
    fun insert(uneCommande : Commande)

    @Update(onConflict = REPLACE)
    fun update(uneCommande: Commande)

    @Delete
    fun delete(uneCommande: Commande)

    @Query("SELECT * FROM Commande")
    fun getAll() : List<Commande>

    @Query("SELECT * FROM Commande C WHERE C.idClient = :pidClient")
    fun getCommandeByClient(pidClient: Long) : List<Commande>
}