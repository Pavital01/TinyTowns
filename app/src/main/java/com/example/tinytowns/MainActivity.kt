package com.example.tinytowns

//import android.content.Intent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var toMainMenu: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toMainMenu = findViewById(R.id.imageView)
        toMainMenu.setOnClickListener{
            val intent = Intent(this,MainMenu::class .java)
            startActivity(intent)
        }
    }
}