package com.example.tinytowns

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Game() : AppCompatActivity() {
    var opacity = 0.7f
    var constSizeResource = 150
    var constSizeCell = 10
    lateinit var choices : MutableMap<String,View>
    var previousView: View? = null
    lateinit var actual_resurse : Drawable
    var actualResourceName : String = "Заглушка"
    lateinit var count_players: Integer
    val countCells = 16 - 1
    var players = mutableListOf<Player>()
    var actual_player: Player = Player("Default_1")
    lateinit var master : Player
    var gameBoard: MutableList<View> = mutableListOf()
    var tempGameBoard: MutableList<View> = mutableListOf()
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
    val longClickListener = View.OnLongClickListener { view ->
        view.alpha = opacity
        println("YES-YES")

        true // Возвращаем true, чтобы показать, что событие было обработано
    }




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

//        val test : Buildings = Buildings.Abbey.
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
        if (masterInfo != null) masterInfo?.text = "Master is ${master.name}"

        choices = mutableMapOf()
        choices["wood"] = masterChoiseView.findViewById(R.id.choice_wood)
        choices["brick"] = masterChoiseView.findViewById(R.id.choice_brick)
        choices["stone"] = masterChoiseView.findViewById<View?>(R.id.choice_stone)
        choices["wheat"] = masterChoiseView.findViewById(R.id.choice_wheat)
        choices["glass"] = masterChoiseView.findViewById(R.id.choice_glass)

//        choices["stone"]?.background = getDrawable(R.drawable.stone_img)!!

        for ((key,value) in choices){

            value.setOnClickListener{
                actualResourceName = when(key){
                    "wood" -> getString(R.string.resource_wood_name)
                    "brick" -> getString(R.string.resource_brick_name)
                    "glass" -> getString(R.string.resource_glass_name)
                    "wheat" -> getString(R.string.resource_wheat_name)
                    "stone" -> getString(R.string.resource_stone_name)
                    else -> "Error"
                }
                actual_resurse = when(key){
                    "wood" -> getDrawable(R.color.resource_wood_color)!!
                    "brick" -> getDrawable(R.color.resource_brick_color)!!
                    "glass" -> getDrawable(R.color.resource_glass_color)!!
                    "wheat" -> getDrawable(R.color.resource_wheat_color)!!
                    "stone" -> getDrawable(R.color.resource_stone_color)!!
//                    "stone" -> getDrawable(R.drawable.stone_img)!!
                    else -> getDrawable(R.color.cell)!!
                }


                changeSize(value,100)
            }
        }

        toGame = masterChoiseView.findViewById(R.id.toGameButton)
        toGame.setOnClickListener{
            setContentView(gameView)
        }

//      Связывается List<View> с view из layout
//      И попутно присваиваем фон по умолчанию
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

        //Запаолняем поля игроков
        for (player in players){
            for (i in 0 .. countCells){
                val view = View(this)
                player.field.add(view)
                player.field.get(i).background = gameBoard.get(i).background
            }
        }

//
        for (i in gameBoard) {
            i.setOnClickListener {
                i.alpha = 1f
                touch = true
                if (it.background == defaultBackground) {
                    if(previousView != null){
                        previousView!!.background = defaultBackground
                        previousView = it
                    }
                    else{
                        previousView = it
                    }
                    it.background = actual_resurse
                }
            }
            i.setOnLongClickListener(longClickListener)
            i.layoutParams.width = constSizeCell
            i.layoutParams.height = constSizeCell
        }

        loadGame()

        //Listeners
        toNextPlayer = gameView.findViewById(R.id.nextPlayerButton)
        toNextPlayer.setOnClickListener {
            if (touch){
                saveBoard()
                previousView = null
                if (actual_player  == players.last()){
                    master = players[(master.number) % players.size]
                    setContentView(masterChoiseView)
                    if (masterInfo != null)
                        masterInfo?.text = "Master is ${master.name}"
                    actual_player = players[(actual_player.number) % count_players]
                    toGame.setOnClickListener{
                        setContentView(gameView)
                        loadGame()
                    }
                }
                else{
                    actual_player = players[(actual_player.number) % count_players]
                    loadGame()
                }
            touch = false
            }
        }

        infoPlayer = gameView.findViewById(R.id.Buildings)
        infoPlayer.setOnClickListener {
            val txt: TextView? = findViewById(R.id.info_message)
            if (txt != null)
                txt?.text = "number : ${actual_player.number} \nName : ${actual_player.name}${if (master == actual_player) "\nMASTER" else ""}"
        }
    }

    private fun loadGame(){
        loadBoard()
        showInfo()
    }
    private fun saveBoard(){
        for (i in 0..countCells) {
            gameBoard.get(i).alpha = 1f
            actual_player.field.get(i).background = gameBoard.get(i).background

        }
    }
    private fun loadBoard(){
        for (i in 0..countCells)
            gameBoard.get(i).background = actual_player.field.get(i).background
    }

    private fun changeSize(view: View,x : Int){
        val const = 150
        choices["wood"]?.layoutParams!!.height = constSizeResource
        choices["wood"]?.layoutParams!!.width = constSizeResource
        choices["brick"]?.layoutParams!!.height = constSizeResource
        choices["brick"]?.layoutParams!!.width = constSizeResource
        choices["stone"]?.layoutParams!!.height = constSizeResource
        choices["stone"]?.layoutParams!!.width = constSizeResource
        choices["wheat"]?.layoutParams!!.height = constSizeResource
        choices["wheat"]?.layoutParams!!.width = constSizeResource
        choices["glass"]?.layoutParams!!.height = constSizeResource
        choices["glass"]?.layoutParams!!.width = constSizeResource

        view.layoutParams.height = x
        view.layoutParams.width = x
        view.minimumHeight = x
        view.minimumWidth = x
    }

    private fun showInfo(){
        val txt: TextView? = gameView.findViewById(R.id.info_message)
        if (txt != null)
            txt?.text = "Player : ${actual_player.name}\nMaster ${master.name} selected ${actualResourceName}"
    }
}

