package com.example.tinytowns

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainMenu : AppCompatActivity() {
    private lateinit var toNewGame : Button
    private lateinit var toResumeGame : Button
    private lateinit var toRules : Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        toNewGame = findViewById(R.id.NewGameButton)
        toNewGame.setOnClickListener{
            val newGame = Intent(this,NewGame::class.java)
            startActivity(newGame)
        }
    }


}