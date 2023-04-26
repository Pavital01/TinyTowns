package com.example.tinytowns

class Player() : java.io.Serializable{
    lateinit var name: String
    var number : Int

    var field : Field = Field()

    init {
        name = "Default"
        number = 0
    }
    constructor(name : String) : this() {
        this.name = name
    }
//    val field: Field()
}