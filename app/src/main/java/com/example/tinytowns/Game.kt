package com.example.tinytowns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.TextView

class Game() : AppCompatActivity(){
    val count_players = 2
    var players = mutableListOf<Player>()
    var actual_player : Player = Player("Default_1")
    var gameBoard : MutableList<View> = mutableListOf()
    init{

        for (i in 1..count_players){
            players.add(Player("Player_$i"))
            players[i - 1].number = i
//            players[i - 1].field.cells = gameBoard
        }
        actual_player = players[0]
    }
    constructor(player: Player):this(){
        this.actual_player = player
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        try {
            actual_player = intent.getSerializableExtra("next_player") as Player
        }
        catch (e : Exception){}

        gameBoard.add(findViewById(R.id.cell_1_1))
        gameBoard.add(findViewById(R.id.cell_1_2))
        gameBoard.add(findViewById(R.id.cell_1_3))
        gameBoard.add(findViewById(R.id.cell_1_4))
        gameBoard.add(findViewById(R.id.cell_2_1))
        gameBoard.add(findViewById(R.id.cell_2_2))
        gameBoard.add(findViewById(R.id.cell_2_3))
        gameBoard.add(findViewById(R.id.cell_2_4))
        gameBoard.add(findViewById(R.id.cell_3_1))
        gameBoard.add(findViewById(R.id.cell_3_2))
        gameBoard.add(findViewById(R.id.cell_3_3))
        gameBoard.add(findViewById(R.id.cell_3_4))
        gameBoard.add(findViewById(R.id.cell_4_1))
        gameBoard.add(findViewById(R.id.cell_4_2))
        gameBoard.add(findViewById(R.id.cell_4_3))
        gameBoard.add(findViewById(R.id.cell_4_4))

//        for (i in gameBoard.indices){
//            gameBoard[i].background = actual_player.field.cells[i].background
//        }

//        for (i in gameBoard.indices){
//            actual_player.field.cells[i].background = gameBoard[i].background
//        }

        //val playerGameBoard : MutableList<View> = player.field.cells
        val txt : TextView? = findViewById(R.id.info_message)
        if (txt != null)
            txt?.text = actual_player.name
        else println("!!!!!!!!!!!!!!!!!!!Hui tam!!!!!!!!!!!!!!!!!!!!!!!!!")

//        var k = 0
//        for (i in gameBoard.indices){
//            gameBoard[i].background = playerGameBoard[i].background
//        }
        for(i in gameBoard)
        {
            i.setOnClickListener{
                it.setBackgroundResource(R.color.resource_wood_color)
            }
        }
    }

    fun editResource(view: View){
        view.setBackgroundResource(R.color.resource_brick_color)
    }

    fun ediText(view : View){
        val txt : TextView? = findViewById(R.id.info_message)
        if (txt != null)
        txt?.text = getString(R.string.building_name_abbey)
        else println("!!!!!!!!!!!!!!!!!!!Hui tam!!!!!!!!!!!!!!!!!!!!!!!!!")
    }

    fun nextPlayer(view: View){
        val intent = Intent(this,Game::class.java)
        intent.putExtra("next_player",players[(actual_player.number) % count_players])
        for (i in actual_player.field.cells.indices){
            actual_player.field.cells[i] = gameBoard[i]
        }
        startActivity(intent)
    }

    fun infoPlayer(view: View) {
        val txt : TextView? = findViewById(R.id.info_message)
        if (txt != null)
            txt?.text = "number : ${actual_player.number} \nName : ${actual_player.name}"
        else println("!!!!!!!!!!!!!!!!!!!Hui tam!!!!!!!!!!!!!!!!!!!!!!!!!")
    }
}