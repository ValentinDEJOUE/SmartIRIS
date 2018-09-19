package com.example.valentindejoue.smartiris.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.valentindejoue.smartiris.R
import com.example.valentindejoue.smartiris.dao.ClientDao
import com.example.valentindejoue.smartiris.models.Client
import kotlinx.android.synthetic.main.activity_visioclient_select.*

class VisionClientActivity : AppCompatActivity() {

    val lesClients : List<Client>? = null

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visioclient_select)
        addClients()
        RVClient.layoutManager = linearLayoutManager
    }

    fun addClients()
    {

    }
}