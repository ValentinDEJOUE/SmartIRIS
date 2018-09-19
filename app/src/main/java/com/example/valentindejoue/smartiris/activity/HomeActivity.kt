package com.example.valentindejoue.smartiris.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import com.example.valentindejoue.smartiris.R
import com.example.valentindejoue.smartiris.database.SmartIrisDatabase
import com.example.valentindejoue.smartiris.thread.DbWorkerThread

class HomeActivity : AppCompatActivity() {

    private var Db : SmartIrisDatabase? = null

    private lateinit var DbWorkerThread : DbWorkerThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        DbWorkerThread = DbWorkerThread("dbWorkerThread")
        DbWorkerThread.start()


        val CVVisionClient = findViewById(R.id.CVVisionClient) as CardView
        CVVisionClient.setOnClickListener {
            val intent = Intent(this , VisionClientActivity::class.java)
            startActivity(intent);
        }
        val CVEditionClient = findViewById(R.id.CVEditionClient) as CardView
        CVVisionClient.setOnClickListener {
            val intent = Intent(this , VisionClientActivity::class.java)
            startActivity(intent);
        }
        val CVMAJListeClient = findViewById(R.id.CVMAJListeClient) as CardView
        CVVisionClient.setOnClickListener {
            val intent = Intent(this , VisionClientActivity::class.java)
            startActivity(intent);
        }
        val CVExportListeClient = findViewById(R.id.CVExportListeClient) as CardView
        CVVisionClient.setOnClickListener {
            val intent = Intent(this , VisionClientActivity::class.java)
            startActivity(intent);
        }
    }
}
