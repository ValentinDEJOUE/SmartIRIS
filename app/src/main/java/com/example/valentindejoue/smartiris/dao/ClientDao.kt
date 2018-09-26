package com.example.valentindejoue.smartiris.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.example.valentindejoue.smartiris.models.Client
import io.reactivex.Flowable

@Dao
interface ClientDao {
    @Insert
    fun insert(vararg unClient: Client)

    @Update(onConflict = REPLACE)
    fun update(vararg unClient: Client)

    @Delete
    fun delete(unClient: Client)

    @Query("SELECT * FROM Client C WHERE C.id=:clientId")
    fun getClient(clientId: Long): Flowable<Client>

    @get:Query("SELECT * FROM Client")
    val allClients: Flowable<List<Client>>
}