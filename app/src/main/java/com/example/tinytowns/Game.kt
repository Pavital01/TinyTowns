package com.example.tinytowns

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Game() : AppCompatActivity() {
    var constSizeResource = 150
    lateinit var choices : MutableMap<String,View>
    lateinit var buildings : MutableMap<String,View>
    var previousView: View? = null
    lateinit var actual_resurse : Drawable

    var actualResourceName : String = "Заглушка"
    lateinit var count_players: Integer
    val countCells = 16 - 1
    var players = mutableListOf<Player>()
    var actual_player: Player = Player("Default_1")
    lateinit var master : Player
    var gameBoard: MutableList<ImageView> = mutableListOf()
    var tempGameBoard: MutableList<View> = mutableListOf()
    lateinit var toNextPlayer: Button
    lateinit var infoPlayer: Button
    lateinit var toBuildings: Button
    lateinit var toGame: Button
    lateinit var backGame: Button
    lateinit var cancelBuilding: Button
    lateinit var masterInfo: TextView
    private lateinit var gameView: View
    private lateinit var masterChoiseView: View
    private lateinit var buildingsLayout: View
    private var touch : Boolean = false
    private lateinit var playersNames: Array<String>

    constructor(player: Player) : this() {
        this.actual_player = player
    }

    @SuppressLint("MissingInflatedId", "ResourceAsColor", "CutPasteId",
        "UseCompatLoadingForDrawables", "SuspiciousIndentation"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //объекты layout-ов для переключения
        gameView = layoutInflater.inflate(R.layout.activity_game, null)
        masterChoiseView = layoutInflater.inflate(R.layout.master_choise, null)
        buildingsLayout = layoutInflater.inflate(R.layout.game_buildings,null)

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
        val defaultBackground = getDrawable(R.color.cell)
        var actualBuilding : Drawable = defaultBackground!!
        val selected = getDrawable(R.drawable.selected)
        val longClickListener = View.OnLongClickListener { view ->
//        view.alpha = opacity
            if ((view.background != defaultBackground) and (!touch))  {
                var view1 = view as ImageView
                view1.setImageDrawable(selected)
            }
            true // Возвращаем true, чтобы показать, что событие было обработано
        }


        setContentView(masterChoiseView)
        val masterInfo: TextView? = masterChoiseView.findViewById(R.id.master_info)
        if (masterInfo != null) masterInfo?.text = "Master is ${master.name}"

        choices = mutableMapOf()
        choices["wood"] = masterChoiseView.findViewById(R.id.choice_wood)
        choices["brick"] = masterChoiseView.findViewById(R.id.choice_brick)
        choices["stone"] = masterChoiseView.findViewById<View?>(R.id.choice_stone)
        choices["wheat"] = masterChoiseView.findViewById(R.id.choice_wheat)
        choices["glass"] = masterChoiseView.findViewById(R.id.choice_glass)

        buildings = mutableMapOf()
        buildings["cottage"] = buildingsLayout.findViewById(R.id.choice_cottage)
        buildings["red"] = buildingsLayout.findViewById(R.id.choice_red_building)
        buildings["gray"] = buildingsLayout.findViewById(R.id.choice_gray_building)
        buildings["orange"] = buildingsLayout.findViewById(R.id.choice_orange_building)
        buildings["green"] = buildingsLayout.findViewById(R.id.choice_green_building)
        buildings["yellow"] = buildingsLayout.findViewById(R.id.choice_yellow_building)
        buildings["black"] = buildingsLayout.findViewById(R.id.choice_black_building)


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

        for ((key,value) in buildings){
//            value.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
//            value.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            value.setOnClickListener {
                actualBuilding = when (key) {
                    "cottage" -> getDrawable(R.drawable.test_bitmap)!!
                    "red" -> getDrawable(R.drawable.red_building_logo)!!
                    "gray" -> getDrawable(R.drawable.gray_building_logo)!!
                    "orange" -> getDrawable(R.drawable.orange_building_logo)!!
                    "green" -> getDrawable(R.drawable.green_building_logo)!!
                    "yellow" -> getDrawable(R.drawable.yellow_buildin_logo)!!
                    "black" -> getDrawable(R.drawable.black_building_logo)!!
//                    "stone" -> getDrawable(R.drawable.stone_img)!!
                    else -> defaultBackground!!
                }
            }
        }

        toGame = masterChoiseView.findViewById(R.id.toGameButton)
        toGame.setOnClickListener{
            setContentView(gameView)
        }

//      Связывается List<View> с view из layout
//      И попутно присваиваем фон по умолчанию
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



//
        for (i in gameBoard) {
            i.background = defaultBackground
            i.setOnClickListener {
                i.setImageDrawable(null)

                if (it.background == defaultBackground) {
                    touch = true
                    if(previousView != null){
                        previousView!!.background = defaultBackground
                        previousView = it
                    }
                    else{
                        previousView = it
                    }
                    if (actualBuilding == defaultBackground)
                    {
                        println("Resurce")
                        it.background = actual_resurse
                    }
                    else{
                        println("Building")
                        it.background = actualBuilding
                    }

                }
            }
            i.setOnLongClickListener(longClickListener)
        }
//Запаолняем поля игроков
        for (player in players){
            for (i in 0 .. countCells){
                val view = View(this)
                player.field.add(view)
                player.field.get(i).background = gameBoard.get(i).background
            }
        }
        loadGame()

        //Listeners
        toNextPlayer = gameView.findViewById(R.id.nextPlayerButton)
        toNextPlayer.setOnClickListener {
            if (touch){
                saveBoard()
                actualBuilding = defaultBackground!!
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

        toBuildings = gameView.findViewById(R.id.Buildings)
        toBuildings.setOnClickListener {
            setContentView(buildingsLayout)
        }
        backGame = buildingsLayout.findViewById(R.id.backButton)
        backGame.setOnClickListener{
            setContentView(gameView)
        }
        cancelBuilding = buildingsLayout.findViewById(R.id.cancelButton)
        cancelBuilding.setOnClickListener{
            actualBuilding = defaultBackground!!
        }
    }

    private fun loadGame(){
        loadBoard()
        showInfo()
    }
    private fun saveBoard(){
        for (i in 0..countCells) {
            gameBoard.get(i).setImageDrawable(null)
            actual_player.field.get(i).background = gameBoard.get(i).background

        }
    }
    private fun loadBoard(){
        for (i in 0..countCells)
            gameBoard.get(i).background = actual_player.field.get(i).background
        println("6")
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

