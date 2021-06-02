package com.wasim.myfirsttictactoygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_first_screen.*

class FirstScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)
        btn_doubleplayer.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
        btn_singleplayer.setOnClickListener{
            startActivity(Intent(this,MainActivity2::class.java))
        }
    }
}