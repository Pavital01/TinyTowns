package com.example.tinytowns

import android.view.View

class Player() : java.io.Serializable{
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
//    val field: Field()
}