package com.example.valentindejoue.smartiris

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val CVVisionClient = findViewById(R.id.CVVisionClient) as CardView
        CVVisionClient.setOnClickListener {
            val intent = Intent(this , VisionClientActivity::class.java)
            startActivity(intent);
        }
    }
}
