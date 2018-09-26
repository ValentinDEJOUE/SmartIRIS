package com.example.valentindejoue.smartiris.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.valentindejoue.smartiris.R
import com.example.valentindejoue.smartiris.R.*
import com.example.valentindejoue.smartiris.database.ClientDatabase
import com.example.valentindejoue.smartiris.datasource.ClientDataSource
import com.example.valentindejoue.smartiris.models.Client
import com.example.valentindejoue.smartiris.repository.ClientRepository
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_detail_client.*

class DetailClientActivity : AppCompatActivity() {
    private var compositeDisposable: CompositeDisposable? = null
    private var clientRepository: ClientRepository? = null
    private var unClient: Flowable<Client>? = null
    private var unClientobj: Client? = null
    private var menu: Menu? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_detail_client)
        var intent: Intent = intent
        var unIdClient: Int = intent.getIntExtra("unIdClient", -1)


        //Init
        compositeDisposable = CompositeDisposable()

        //Database
        val clientDatabase = ClientDatabase.getInstance(this)
        clientRepository = ClientRepository.getInstance(ClientDataSource.getInstance(clientDatabase.clientDAO()))
        loadData(unIdClient)

        val BtnMap = findViewById<ImageButton>(R.id.IBMap)
        BtnMap.setOnClickListener {
            var gmIntentURI = Uri.parse("geo:0,0?q=" + ETAdresse.text.toString())
            var intent = Intent(Intent.ACTION_VIEW, gmIntentURI)
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail_vision, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.MIEdit -> {
                ActiveEdition()
                item.isVisible = false
                menu!!.getItem(0).isVisible = true
            }
            R.id.MIValide -> {
                ValideEdition()
                item.isVisible = false
                menu!!.getItem(1).isVisible = true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun ActiveEdition() {
        var ETNom = findViewById<EditText>(R.id.ETUtilisateur)
        var ETAdresse = findViewById<EditText>(R.id.ETAdresse)
        var ETSociete = findViewById<EditText>(R.id.ETSociété)
        ETNom.isEnabled = true
        ETAdresse.isEnabled = true
        ETSociete.isEnabled = true
    }

    fun ValideEdition() {
        var ETNom = findViewById<EditText>(R.id.ETUtilisateur)
        var ETAdresse = findViewById<EditText>(R.id.ETAdresse)
        var ETSociete = findViewById<EditText>(R.id.ETSociété)
        unClientobj?.Nom = ETNom.text.toString()
        unClientobj?.Adresse = ETAdresse.text.toString()
        unClientobj?.Société = ETSociete.text.toString()
        clientRepository?.updateClient(unClientobj!!)
        ETNom.isEnabled = false
        ETAdresse.isEnabled = false
        ETSociete.isEnabled = false
    }

    fun loadData(pUnIdClient: Int) {
        if (pUnIdClient != -1) {
            var ETNom = findViewById<EditText>(R.id.ETUtilisateur)
            var ETAdresse = findViewById<EditText>(R.id.ETAdresse)
            var ETSociete = findViewById<EditText>(R.id.ETSociété)
            unClient = clientRepository!!.getClientById(pUnIdClient.toLong())
            unClientobj = unClient!!.blockingFirst()
            ETNom.setText(unClientobj?.Nom, TextView.BufferType.EDITABLE)
            ETAdresse.setText(unClientobj?.Adresse, TextView.BufferType.EDITABLE)
            ETSociete.setText(unClientobj?.Société, TextView.BufferType.EDITABLE)
        } else
            Toast.makeText(this, "Problème : L'identifiant Client n'est pas dans la base", Toast.LENGTH_LONG)

    }
}