package com.example.valentindejoue.smartiris.activity

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import com.example.valentindejoue.smartiris.R
import com.example.valentindejoue.smartiris.preferences.AppPreferences
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppPreferences.init(this)
        setContentView(R.layout.activity_setting)
        val LLNomUtilisateur = findViewById<LinearLayout>(R.id.LLNomUtilisateur)
        val LLEmail = findViewById<LinearLayout>(R.id.LLEmail)

        LLNomUtilisateur.setOnClickListener {
            val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.popup_utilisateur, null)
            val popupWindow = PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            val ETUtilisateur = view.findViewById<EditText>(R.id.ETUtilisateur)
            ETUtilisateur.setText("test", TextView.BufferType.EDITABLE)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                popupWindow.elevation = 10.0F
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val slideIn = Slide()
                slideIn.slideEdge = Gravity.TOP
                popupWindow.enterTransition = slideIn

                val slideOut = Slide()
                slideOut.slideEdge = Gravity.RIGHT
                popupWindow.exitTransition = slideOut
            }
            val buttonValider = view.findViewById<Button>(R.id.BtnValiderUtilisateur)

            buttonValider.setOnClickListener {
                popupWindow.dismiss()
            }

            TransitionManager.beginDelayedTransition(LLRoot)
            popupWindow.showAtLocation(LLRoot, Gravity.CENTER, 0, 0)
        }

        LLEmail.setOnClickListener {

        }
    }
}