package com.example.valentindejoue.smartiris.database

import com.example.valentindejoue.smartiris.models.Client
import io.reactivex.Flowable

interface IClientDataSource {
    val allClient: Flowable<List<Client>>
    fun getClientById(clientId: Long): Flowable<Client>
    fun insertClient(vararg client: Client)
    fun updateClient(vararg client: Client)
    fun deleteClient(client: Client)
}