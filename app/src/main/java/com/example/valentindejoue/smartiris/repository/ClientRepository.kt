package com.example.valentindejoue.smartiris.repository

import com.example.valentindejoue.smartiris.database.IClientDataSource
import com.example.valentindejoue.smartiris.models.Client
import io.reactivex.Flowable

class ClientRepository(private val locationDataSource: IClientDataSource) : IClientDataSource {

    override val allClient: Flowable<List<Client>>
        get() = locationDataSource.allClient

    override fun insertClient(vararg unClient: Client) {
        locationDataSource.insertClient(*unClient)
    }

    override fun updateClient(vararg unClient: Client) {
        locationDataSource.updateClient(*unClient)
    }

    override fun deleteClient(unClient: Client) {
        locationDataSource.deleteClient(unClient)
    }

    override fun getClientById(clientId: Long): Flowable<Client> {
        return locationDataSource.getClientById(clientId)
    }

    companion object {
        private var instance: ClientRepository? = null
        fun getInstance(locationDataSource: IClientDataSource): ClientRepository {
            if (instance == null)
                instance = ClientRepository(locationDataSource)
            return instance!!
        }
    }
}