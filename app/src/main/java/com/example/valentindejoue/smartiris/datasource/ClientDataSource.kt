package com.example.valentindejoue.smartiris.datasource

import com.example.valentindejoue.smartiris.dao.ClientDao
import com.example.valentindejoue.smartiris.database.IClientDataSource
import com.example.valentindejoue.smartiris.models.Client
import com.example.valentindejoue.smartiris.repository.ClientRepository
import io.reactivex.Flowable

class ClientDataSource(private val clientDao: ClientDao) : IClientDataSource {
    override val allClient: Flowable<List<Client>>
        get() = clientDao.allClients

    override fun getClientById(clientId: Long): Flowable<Client> {
        return clientDao.getClient(clientId)
    }

    override fun insertClient(vararg client: Client) {
        clientDao.insert(*client)
    }

    override fun updateClient(vararg client: Client) {
        clientDao.update(*client)
    }

    override fun deleteClient(client: Client) {
        clientDao.delete(client)
    }

    companion object {
        private var instance: ClientDataSource? = null
        fun getInstance(clientDao: ClientDao): ClientDataSource {
            if (instance == null)
                instance = ClientDataSource(clientDao)
            return instance!!
        }
    }
}