package com.example.valentindejoue.smartiris.activity

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.CardView
import android.widget.Toast
import com.example.valentindejoue.smartiris.R
import com.example.valentindejoue.smartiris.database.ClientDatabase
import com.example.valentindejoue.smartiris.datasource.ClientDataSource
import com.example.valentindejoue.smartiris.models.Client
import com.example.valentindejoue.smartiris.repository.ClientRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_visioclient_select.*
import java.io.*
import java.net.URI
import java.sql.ClientInfoStatus

class HomeActivity : AppCompatActivity() {

    var UTILISATEUR = "VALENTIN DEJOUE"
    var EMAIL = "TEST@GMAIL.COM"
    private var clientRepository: ClientRepository? = null
    private var compositeDisposable: CompositeDisposable? = null
    private var clientList: ArrayList<Client> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val clientDatabase = ClientDatabase.getInstance(this)
        clientRepository = ClientRepository.getInstance(ClientDataSource.getInstance(clientDatabase.clientDAO()))

        val CVVisionClient = findViewById(R.id.CVVisionClient) as CardView
        CVVisionClient.setOnClickListener {
            val intent = Intent(this , VisionClientActivity::class.java)
            startActivity(intent)
        }

        val BtnSettings = findViewById<FloatingActionButton>(R.id.BtnSettings)
        BtnSettings.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        val CVMAJListeClient = findViewById(R.id.CVMAJListeClient) as CardView
        CVMAJListeClient.setOnClickListener {
            val intent = Intent().setType("text/plain").setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Selectionnez un fichier"), 111)
        }

        val CVExportListeClient = findViewById(R.id.CVExportListeClient) as CardView
        CVExportListeClient.setOnClickListener {

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111 && resultCode == Activity.RESULT_OK) {
            var filePath = data?.data
            importerClient(filePath)
        }
    }

    fun importerClient(pathFile: Uri?) {
        try {
            val CLIENT_ID_IDX = 0
            val CLIENT_NOM_IDX = 1
            val CLIENT_ADDRESSE_IDX = 2
            val CLIENT_SOCIETE_IDX = 3

            var context: Context = this.applicationContext
            var resolver: ContentResolver = context.contentResolver
            var inputStream: InputStream = resolver.openInputStream(pathFile)
            val lineList = mutableListOf<String>()

            inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
            lineList.removeAt(0)
            for (line in lineList) {
                var splitline = line.split(",")
                if (splitline.size > 0) {
                    var unclient: Client = Client(
                            Integer.parseInt(splitline.get(CLIENT_ID_IDX)),
                            splitline.get(CLIENT_NOM_IDX),
                            splitline.get(CLIENT_ADDRESSE_IDX),
                            splitline.get(CLIENT_SOCIETE_IDX))
                    clientList.add(unclient)
                }
            }

            for (client in clientList) {
                try {
                    clientRepository!!.insertClient(client)
                } catch (E: Exception) {
                    Toast.makeText(this, "Problème Insertion Cient : " + E.message, Toast.LENGTH_SHORT)
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Erreur Lecteur CSV : " + e.message, Toast.LENGTH_SHORT)
            e.printStackTrace()
        }
    }

    private fun loadData() {
        val disposable = clientRepository!!.allClient.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe({ clients -> onGetAlleClientSuccess(clients) })
        { throwable ->
            Toast.makeText(this@HomeActivity, "" + throwable.message, Toast.LENGTH_SHORT).show()
        }
        compositeDisposable!!.add(disposable)
    }

    private fun onGetAlleClientSuccess(clients: List<Client>) {
        clientList.clear()
        clientList.addAll(clients)
        RVClient.adapter?.notifyDataSetChanged()
    }
}
