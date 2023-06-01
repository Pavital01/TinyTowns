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
    val randomBuildings : List<Buildings> = mutableListOf(
//        Buildings.Cottage,
//        Buildings.Bank,
//        Buildings.Theater,
//        Buildings.Almshouse,
//        Buildings.Abbey,
//        Buildings.Farm,
        Buildings.Well
        )
//    companion object {
//        public val context = applicationConext
//    }


    var actualBuilding : Buildings? = null
    var constSizeResource = 150
    lateinit var choices : MutableMap<String,View>
    lateinit var buildings : MutableMap<String,View>
    var previousView: View? = null
    lateinit var actual_resurse : Drawable
    var selectedList : MutableList<ImageView> = mutableListOf()
    var actualResourceName : String = "Заглушка"
    lateinit var count_players: Integer
    val countCells = 16 - 1
    var players = mutableListOf<Player>()
    var actual_player: Player = Player("Default_1")
    lateinit var master : Player
    var gameBoard: MutableList<ImageView> = mutableListOf()
    lateinit var toNextPlayer: Button
    lateinit var toBuildings: Button
    lateinit var toGame: Button
    lateinit var backGame: Button
    lateinit var cancelBuilding: Button
    private lateinit var gameView: View
    private lateinit var masterChoiseView: View
    private lateinit var buildingsLayout: View
    private var touch : Boolean = false
    private lateinit var playersNames: Array<String>
    @SuppressLint("MissingInflatedId", "ResourceAsColor", "CutPasteId",
        "UseCompatLoadingForDrawables", "SuspiciousIndentation"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Tests


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
        val defaultBackground = getDrawable(Resources.Default.background)
//        var actualBuilding : Drawable = defaultBackground!!
        val selected = getDrawable(R.drawable.selected)
        val longClickListener = View.OnLongClickListener { view ->
            if ((view.background != defaultBackground) and (!touch))  {
                var view1 = view as ImageView
                view1.setImageDrawable(selected)
                selectedList.add(view)
            }
            true // Возвращаем true, чтобы показать, что событие было обработано
        }
        val backgroundWood = getDrawable(Resources.Wood.background)
        val backgroundBrick = getDrawable(Resources.Brick.background)
        val backgroundStone = getDrawable(Resources.Stone.background)
        val backgroundWheat = getDrawable(Resources.Wheat.background)
        val backgroundGlass = getDrawable(Resources.Glass.background)
        println("Wood : ${backgroundWood}")
        println("Brick : ${backgroundBrick}")
        println("Stone : ${backgroundStone}")
        println("Wheat : ${backgroundWheat}")
        println("Glass : ${backgroundGlass}")


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
                    "wood" -> backgroundWood!!
                    "brick" -> backgroundBrick!!
                    "stone" -> backgroundStone!!
                    "wheat" -> backgroundWheat!!
                    "glass" -> backgroundGlass!!
//                    "stone" -> getDrawable(R.drawable.stone_img)!!
                    else -> getDrawable(Resources.Default.background)!!
                }


                changeSize(value,100)
            }
        }

        for ((key,value) in buildings){
//            value.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
//            value.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            value.setOnClickListener {
                showInfo("You choose ${key}")
                actualBuilding = when (key) {
                    "cottage" -> Buildings.Cottage//getDrawable(R.drawable.cottage_ico)!!
                    "red" -> Buildings.Farm//getDrawable(R.drawable.red_building_logo)!!
                    "gray" -> Buildings.Well//getDrawable(R.drawable.gray_building_logo)!!
                    "orange" -> Buildings.Abbey//getDrawable(R.drawable.orange_building_logo)!!
                    "green" -> Buildings.Almshouse//getDrawable(R.drawable.green_building_logo)!!
                    "yellow" -> Buildings.Theater//getDrawable(R.drawable.yellow_buildin_logo)!!
                    "black" -> Buildings.Bank//getDrawable(R.drawable.black_building_logo)!!
//                    "stone" -> getDrawable(R.drawable.stone_img)!!
                    else -> Buildings.Well//defaultBackground!!
                }
            }
        }

        //Кнопка к игре из Buildings
        toGame = masterChoiseView.findViewById(R.id.toGameButton)
        toGame.setOnClickListener{
            setContentView(gameView)
        }

//      Связывается List<View> с view из layout
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

//Для каждой игровой клетки
        for (i in gameBoard) {
            i.background = defaultBackground//Фон
            i.setOnClickListener {

                if (it.background == defaultBackground) {
                    touch = true
                    if(previousView != null){
                        previousView!!.background = defaultBackground
                        previousView = it
                    }
                    else{
                        previousView = it
                    }
                    it.background = actual_resurse
                }
                if ((i.drawable != null) and (actualBuilding != null)){
                    println("1")
                    if (verification(backgroundWood,backgroundBrick,backgroundStone,backgroundWheat,backgroundGlass)){
                        println("2")
                        for (view in selectedList){
                            view.background = defaultBackground
                            view.setImageDrawable(null)
                        }
                        selectedList.clear()
                        i.background = getDrawable(actualBuilding!!.background)
                    }
                }
                i.setImageDrawable(null)
                selectedList.remove(i)
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
                actualBuilding = null
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
            showInfo()
            actualBuilding = null
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
    private fun showInfo(string : String){
        val txt: TextView? = gameView.findViewById(R.id.info_message)
        if (txt != null)
            txt?.text = string
    }
    fun verification(
        backgroundWood : Drawable?,
        backgroundBrick : Drawable?,
        backgroundStone : Drawable?,
        backgroundWheat : Drawable?,
        backgroundGlass : Drawable?
    ) : Boolean{
        println("Wood2 : ${backgroundWood}")
        println("Brick2 : ${backgroundBrick}")
        println("Stone2 : ${backgroundStone}")
        println("Wheat2 : ${backgroundWheat}")
        println("Glass2 : ${backgroundGlass}")

        var testList : MutableList<Drawable?> = mutableListOf()
        for (i in actualBuilding!!.resources){
            testList.add(when (i){
                Resources.Wood.background -> backgroundWood
                Resources.Brick.background -> backgroundBrick
                Resources.Stone.background -> backgroundStone
                Resources.Wheat.background -> backgroundWheat
                Resources.Glass.background -> backgroundGlass
                else -> backgroundBrick
            })
        }

        println("testList : ${testList}")
//        val resources: MutableList<Drawable?> = actualBuilding?.resources?.map{ elem ->
//            getDrawable(elem)
//        }!!.toMutableList()
        println("Resources : ")
//        for(elem in resources) {
//            println(elem)}
        var tempList = selectedList.toMutableList()
        for (i in selectedList) println(i.background)
//        println("SelectedList : ${selectedList}")
//        println("ResourcesList : ${resources}")
        if (tempList.size == testList.size){
            println("Size == ")
            for (resource in testList){
                println("resource from testList : ${resource}")

                for (value in tempList){
                    println("Value from selectedList : ${value}")
                    if (value.background == resource){
                        println("resource == value : ${resource} == ${value}")
                        println("SelectedList.size before = ${tempList.size}")
                        tempList.remove(value)
                        println("SelectedList.size after = ${tempList.size}")
                        break
                    }
                }
            }
            if (tempList.size == 0) {
                println("RETURN TRUE")
                return true
            }
        }
        return false
//        actualBuilding?.verification(selectedList)
    }

}

