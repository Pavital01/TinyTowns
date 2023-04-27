package com.example.tinytowns

import android.view.View

class Player() : Cloneable{
    lateinit var name: String
    var number : Int

    var field : MutableList<View> = mutableListOf()

    init {
        name = "Default"
        number = 0
    }
    constructor(name : String) : this() {
        this.name = name
    }
//    public override fun clone(): Player {
//        val newPlayer = Player()
//        newPlayer.name = name
//        newPlayer.number = number
//        newPlayer.field = field.toList() as MutableList<View>
//        return newPlayer
//    }
}