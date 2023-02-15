package com.example.tinytowns

class Player() : java.io.Serializable{
    var name: String
    var number : Int = 0

    var field : Field = Field()

    init {
        name = "Default"
    }
    constructor(name : String) : this() {
        this.name = name
    }
//    val field: Field()
}