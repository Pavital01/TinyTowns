package com.example.tinytowns

import android.view.View

class Field() : java.io.Serializable{
    var cells: MutableList<View> = mutableListOf()

//    init {
//        for (i in 0..15) {
//            cells.add(View(null)) // Replace null with a valid context, if needed
//        }
//    }

}