package com.example.tinytowns

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.updateLayoutParams

class Game() : AppCompatActivity() {
    lateinit var choices : MutableMap<String,View>
    var previousView: View? = null
    lateinit var actual_resurse : Drawable
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
    private lateinit var choiceWood : View
    private lateinit var choiceBrick : View
    private lateinit var choiceStone : View
    private lateinit var choiceWheat : View
    private lateinit var choiceGlass : View
    private var touch : Boolean = false




    private lateinit var playersNames: Array<String>

    init {

    }

    constructor(player: Player) : this() {
        this.actual_player = player
    }

    @SuppressLint("MissingInflatedId", "ResourceAsColor", "CutPasteId",
        "UseCompatLoadingForDrawables", "SuspiciousIndentation"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        val defaultBackground = getDrawable(R.color.cell)
        super.onCreate(savedInstanceState)
        //объекты layout-ов для переключения
        gameView = layoutInflater.inflate(R.layout.activity_game, null)
        masterChoiseView = layoutInflater.inflate(R.layout.master_choise, null)

        //связываются введённые имена и List<Player>
        playersNames = intent.getSerializableExtra("players") as Array<String>
        var count_players = playersNames.size
        for (i in 1..count_players) {
            players.add(Player(playersNames[i - 1]))
            players[i - 1].number = i
        }

        //Устанавливаются изначальные данные
        actual_player = players[0]
        master = players[0]


        setContentView(masterChoiseView)
        val masterInfo: TextView? = masterChoiseView.findViewById(R.id.master_info)
        if (masterInfo != null)
            masterInfo?.text = "Master is ${master.name}"
        else println("Nea")

        toGame = masterChoiseView.findViewById(R.id.toGameButton)
        toGame.setOnClickListener{
            setContentView(gameView)
        }





//        println("Master and actual_player is ${master == actual_player}")
//      Связывается List<View> с view из layout
        gameBoard.add(gameView.findViewById(R.id.cell_1_1))
        gameBoard.get(0).background = defaultBackground
        gameBoard.add(gameView.findViewById(R.id.cell_1_2))
        gameBoard.get(1).background = defaultBackground
        gameBoard.add(gameView.findViewById(R.id.cell_1_3))
        gameBoard.get(2).background = defaultBackground
        gameBoard.add(gameView.findViewById(R.id.cell_1_4))
        gameBoard.get(3).background = defaultBackground
        gameBoard.add(gameView.findViewById(R.id.cell_2_1))
        gameBoard.get(4).background = defaultBackground
        gameBoard.add(gameView.findViewById(R.id.cell_2_2))
        gameBoard.get(5).background = defaultBackground
        gameBoard.add(gameView.findViewById(R.id.cell_2_3))
        gameBoard.get(6).background = defaultBackground
        gameBoard.add(gameView.findViewById(R.id.cell_2_4))
        gameBoard.get(7).background = defaultBackground
        gameBoard.add(gameView.findViewById(R.id.cell_3_1))
        gameBoard.get(8).background = defaultBackground
        gameBoard.add(gameView.findViewById(R.id.cell_3_2))
        gameBoard.get(9).background = defaultBackground
        gameBoard.add(gameView.findViewById(R.id.cell_3_3))
        gameBoard.get(10).background = defaultBackground
        gameBoard.add(gameView.findViewById(R.id.cell_3_4))
        gameBoard.get(11).background = defaultBackground
        gameBoard.add(gameView.findViewById(R.id.cell_4_1))
        gameBoard.get(12).background = defaultBackground
        gameBoard.add(gameView.findViewById(R.id.cell_4_2))
        gameBoard.get(13).background = defaultBackground
        gameBoard.add(gameView.findViewById(R.id.cell_4_3))
        gameBoard.get(14).background = defaultBackground
        gameBoard.add(gameView.findViewById(R.id.cell_4_4))
        gameBoard.get(15).background = defaultBackground

        for (player in players){
            for (i in 0 .. countCells){
                val view = View(this)
                player.field.add(view)
                player.field.get(i).background = gameBoard.get(i).background
            }
        }



        val txt: TextView? = gameView.findViewById(R.id.info_message)
        if (txt != null)
            txt?.text = actual_player.name
        else println("Nea1")
        val view: View = gameView.findViewById(R.id.cell_1_1)
        val background: Drawable? = view.background
        val backgroundResourceName: String? = background?.toString()
        println("TEESSTT $backgroundResourceName")

        for (i in gameBoard) {
            i.setOnClickListener {
//                println("GameBoard ${gameBoard.get(1).resources.toString()}")
//                println("Default ${defaultBackground.resources.toString()}")
//                println("IT ${it.resources}")
                touch = true
                println("make touch = true")
                if (it.background == defaultBackground) {
                    println("it.background == defaultBackground")
                    if(previousView != null){
                        println("previousView != null")
//                        previousView!!.background = defaultBackground
                        previousView!!.background = defaultBackground
                        previousView = it
                    }
                    else{
                        previousView = it
                    }
                    it.background = actual_resurse
                }
            }
        }

        choices = mutableMapOf()
        choices["wood"] = masterChoiseView.findViewById(R.id.choice_wood)
        choices["brick"] = masterChoiseView.findViewById(R.id.choice_brick)
        choices["stone"] = masterChoiseView.findViewById(R.id.choice_stone)
        choices["wheat"] = masterChoiseView.findViewById(R.id.choice_wheat)
        choices["glass"] = masterChoiseView.findViewById(R.id.choice_glass)

        for ((key,value) in choices){
            value.setOnClickListener{

                actual_resurse = when(key){
                    "wood" -> {getDrawable(R.color.resource_wood_color)!!}
                    "brick" -> getDrawable(R.color.resource_brick_color)!!
                    "glass" -> getDrawable(R.color.resource_glass_color)!!
                    "wheat" -> getDrawable(R.color.resource_wheat_color)!!
                    "stone" -> getDrawable(R.color.resource_stone_color)!!
                    else -> getDrawable(R.color.cell)!!
                }

                changeSize(value,100)
            }

        }

        //Listeners
        toNextPlayer = gameView.findViewById(R.id.nextPlayerButton)
        toNextPlayer.setOnClickListener {
            println("Touch? ${touch}")
            if (touch){
                println("Touch = true")
                saveBoard()
                previousView = null
                if (actual_player  == players.last()){
//                println("Master and actual_player is ${master == actual_player}")
                    master = players[(master.number) % players.size]
                    setContentView(masterChoiseView)
                    if (masterInfo != null)
                        masterInfo?.text = "Master is ${master.name}"
                    else println("Nea")
                    actual_player = players[(actual_player.number) % count_players]
                    toGame.setOnClickListener{
                        setContentView(gameView)
                        loadGame()
                    }
                }
                else{
                    actual_player = players[(actual_player.number) % count_players]
                    //println("Actual player number ${actual_player.number}")
                    loadGame()
//            println("after load")
                }
            touch = false
            }


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

    private fun loadGame(){
//        println("load actualPlayer is ${actual_player.number}")
        loadBoard()
        val txt: TextView? = gameView.findViewById(R.id.info_message)
        if (txt != null)
            txt?.text = actual_player.name
        else println("Nea1")

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

    private fun changeSize(view: View,x : Int){
        val const = 150
        choices["wood"]?.layoutParams!!.height = const
        choices["wood"]?.layoutParams!!.width = const
        choices["brick"]?.layoutParams!!.height = const
        choices["brick"]?.layoutParams!!.width = const
        choices["stone"]?.layoutParams!!.height = const
        choices["stone"]?.layoutParams!!.width = const
        choices["wheat"]?.layoutParams!!.height = const
        choices["wheat"]?.layoutParams!!.width = const
        choices["glass"]?.layoutParams!!.height = const
        choices["glass"]?.layoutParams!!.width = const

        view.layoutParams.height = x
        view.layoutParams.width = x
        view.minimumHeight = x
        view.minimumWidth = x
//        it.layoutParams.height = 45
//            it.layoutParams.width = 45
//            it.minimumHeight = 45
//            it.minimumWidth = 45
    }
}

