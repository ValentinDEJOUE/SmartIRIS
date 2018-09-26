package com.example.valentindejoue.smartiris.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import com.example.valentindejoue.smartiris.R
import com.example.valentindejoue.smartiris.adapter.ClientAdapter
import com.example.valentindejoue.smartiris.database.ClientDatabase
import com.example.valentindejoue.smartiris.datasource.ClientDataSource
import com.example.valentindejoue.smartiris.models.Client
import com.example.valentindejoue.smartiris.repository.ClientRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_visioclient_select.*

class VisionClientActivity() : AppCompatActivity() {
    var clientList: MutableList<Client> = ArrayList()

    //Database
    private var compositeDisposable: CompositeDisposable? = null
    private var clientRepository: ClientRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visioclient_select)

        //Init
        compositeDisposable = CompositeDisposable()

        registerForContextMenu(RVClient)
        RVClient.adapter = ClientAdapter(clientList, { clientItem: Client -> clientItemClicked(clientItem) })
        RVClient.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        //Database
        val clientDatabase = ClientDatabase.getInstance(this)
        clientRepository = ClientRepository.getInstance(ClientDataSource.getInstance(clientDatabase.clientDAO()))
        loadData()
    }

    private fun clientItemClicked(clientItem: Client)
    {
        val intent = Intent(this, DetailClientActivity::class.java)
        intent.putExtra("unIdClient", clientItem.id)
        startActivity(intent);
    }

    private fun loadData() {
        val disposable = clientRepository!!.allClient.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe({ clients -> onGetAlleClientSuccess(clients) })
        { throwable ->
            Toast.makeText(this@VisionClientActivity, "" + throwable.message, Toast.LENGTH_SHORT).show()
        }
        compositeDisposable!!.add(disposable)
    }


    private fun onGetAlleClientSuccess(clients: List<Client>) {
        clientList.clear()
        clientList.addAll(clients)
        RVClient.adapter?.notifyDataSetChanged()
    }


}