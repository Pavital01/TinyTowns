package com.example.tinytowns

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class NewGame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)
        val playersLayout = findViewById<LinearLayout>(R.id.playersLayout)
        val buttonsLayout = findViewById<LinearLayout>(R.id.buttonsLayout)
        val startGameButton = findViewById<Button>(R.id.startGameButton)
        val addPlayerButton = findViewById<Button>(R.id.addPlayerButton)

        val players = ArrayList<EditText>()
        val player1EditText = EditText(this)
        val player2EditText = EditText(this)

        player1EditText.id = View.generateViewId()
        player1EditText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        player1EditText.hint = "Player 1"
        playersLayout.addView(player1EditText, 0)

        player2EditText.id = View.generateViewId()
        player2EditText.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        player2EditText.hint = "Player 2"
        playersLayout.addView(player2EditText, 1)

        players.add(player1EditText)
        players.add(player2EditText)

        if (players.size >= 4) {
            addPlayerButton.visibility = View.GONE
        } else {
            addPlayerButton.visibility = View.VISIBLE
            addPlayerButton.setOnClickListener {
                val newEditText = EditText(this)
                newEditText.id = View.generateViewId()
                newEditText.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                newEditText.hint = "Player ${players.size + 1}"
                players.add(newEditText)
                playersLayout.addView(newEditText, players.size - 1)
                if (players.size >= 4) {
                    addPlayerButton.visibility = View.GONE
                }
            }
        }
        startGameButton.setOnClickListener {
            // Start the game with the selected players
            val intent = Intent(this, Game::class.java)
            intent.putExtra("players", players.map { it.text.toString() }.toTypedArray())
            startActivity(intent)
        }
    }
}