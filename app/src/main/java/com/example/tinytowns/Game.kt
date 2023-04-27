package com.example.tinytowns

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Game() : AppCompatActivity() {
    lateinit var count_players: Integer
    val countCells = 16 - 1
    var players = mutableListOf<Player>()
    var actual_player: Player = Player("Default_1")
    lateinit var master : Player
    var gameBoard: MutableList<View> = mutableListOf()
    lateinit var toNextPlayer: Button
    lateinit var infoPlayer: Button
    lateinit var test: Button
    lateinit var toGame: Button
    lateinit var masterInfo: TextView
    private lateinit var gameView: View
    private lateinit var masterChoiseView: View

    private lateinit var playersNames: Array<String>

    init {

    }

    constructor(player: Player) : this() {
        this.actual_player = player
    }

    @SuppressLint("MissingInflatedId", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gameView = layoutInflater.inflate(R.layout.activity_game, null)
        masterChoiseView = layoutInflater.inflate(R.layout.master_choise, null)

        playersNames = intent.getSerializableExtra("players") as Array<String>
        var count_players = playersNames.size
        for (i in 1..count_players) {
            players.add(Player(playersNames[i - 1]))
            players[i - 1].number = i
        }

        actual_player = players[0]
        master = players[0]

        setContentView(masterChoiseView)
        val masterInfo: TextView? = masterChoiseView.findViewById(R.id.master_info)
        if (masterInfo != null)
            masterInfo?.text = "Master is ${actual_player.name}"
        else println("Nea")

        toGame = masterChoiseView.findViewById(R.id.toGameButton)
        toGame.setOnClickListener{
            setContentView(gameView)
        }





//        println("Master and actual_player is ${master == actual_player}")

        gameBoard.add(gameView.findViewById(R.id.cell_1_1))
        gameBoard.add(gameView.findViewById(R.id.cell_1_2))
        gameBoard.add(gameView.findViewById(R.id.cell_1_3))
        gameBoard.add(gameView.findViewById(R.id.cell_1_4))
        gameBoard.add(gameView.findViewById(R.id.cell_2_1))
        gameBoard.add(gameView.findViewById(R.id.cell_2_2))
        gameBoard.add(gameView.findViewById(R.id.cell_2_3))
        gameBoard.add(gameView.findViewById(R.id.cell_2_4))
        gameBoard.add(gameView.findViewById(R.id.cell_3_1))
        gameBoard.add(gameView.findViewById(R.id.cell_3_2))
        gameBoard.add(gameView.findViewById(R.id.cell_3_3))
        gameBoard.add(gameView.findViewById(R.id.cell_3_4))
        gameBoard.add(gameView.findViewById(R.id.cell_4_1))
        gameBoard.add(gameView.findViewById(R.id.cell_4_2))
        gameBoard.add(gameView.findViewById(R.id.cell_4_3))
        gameBoard.add(gameView.findViewById(R.id.cell_4_4))

        for (player in players){
            for (i in 0 .. countCells){
                val view = View(this)
                player.field.add(view)
                player.field.get(i).background = gameBoard.get(i).background
            }
        }

        val txt: TextView? = findViewById(R.id.info_message)
        if (txt != null)
            txt?.text = actual_player.name
        else println("Nea1")


        for (i in gameBoard) {
            i.setOnClickListener {
                it.setBackgroundResource(R.color.resource_wood_color)
            }
        }

        //Listeners
        toNextPlayer = gameView.findViewById(R.id.nextPlayerButton)
        toNextPlayer.setOnClickListener {
            saveBoard()
            actual_player = players[(actual_player.number) % count_players]
            //println("Actual player number ${actual_player.number}")
            loadGame()
//            println("after load")

        }
        infoPlayer = gameView.findViewById(R.id.infoPlayerButton)
        infoPlayer.setOnClickListener {
            val txt: TextView? = findViewById(R.id.info_message)
            if (txt != null)
                txt?.text = "number : ${actual_player.number} \nName : ${actual_player.name}${if (master == actual_player) "\nMASTER" else ""}"
            else println("Nea")
        }
        test = gameView.findViewById(R.id.Buildings)
        test.setOnClickListener{
            gameBoard[3].setBackgroundColor(R.color.resource_brick_color)
        }
    }


    fun ediText(view: View) {
        val txt: TextView? = gameView.findViewById(R.id.info_message)
        if (txt != null)
            txt?.text = getString(R.string.building_name_abbey)
        else println("Nea")
    }

    private fun loadGame(){
//        println("load actualPlayer is ${actual_player.number}")
        loadBoard()
        val txt: TextView? = gameView.findViewById(R.id.info_message)
        if (txt != null)
            txt?.text = actual_player.name
        else println("Nea1")
    if (actual_player  == players.last()){
        println("Master and actual_player is ${master == actual_player}")
        master = players[(actual_player.number) % players.size]
    }

    }
    private fun saveBoard(){
        if (actual_player.field.size == 0){
//            println("saveBoard null")
            for (i in 0..countCells){
                val view = View(this)
                actual_player.field.add(view)
                actual_player.field.get(i).background = gameBoard.get(i).background
            }
        }
        else{
//            println("saveBoard noNull")
            for (i in 0..countCells)
                actual_player.field.get(i).background = gameBoard.get(i).background
        }
    }
    private fun loadBoard(){
        if (actual_player.field.size == 0){
//            println("loadBoard null")
            for (i in 0..countCells){
                val view = View(this)
                actual_player.field.add(view)
                actual_player.field.get(i).background = gameBoard.get(i).background
            }
        }
        for (i in 0..countCells) {
            val yellowColor = Color.argb(255, 255, 255, 0)
            gameBoard.get(i).background = actual_player.field.get(i).background

        }
    }
}

