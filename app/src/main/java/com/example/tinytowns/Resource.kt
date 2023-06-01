package com.example.tinytowns

import android.content.Context
import android.graphics.drawable.Drawable



enum class Resources{
    Default{
        override val background = R.color.cell
        override fun getBackground(context: Context) : Drawable{
            val drawable = context.getDrawable(background)
            return drawable!!
        }
    },
    Wood{
        override val background = R.color.resource_wood_color
         val nameResource = "Wood"
        override fun getBackground(context: Context) : Drawable{
            val drawable = context.getDrawable(background)
            return drawable!!
        }
    },
    Brick{
        override val background = R.color.resource_brick_color
         val nameResource = "Wood"
        override fun getBackground(context: Context) : Drawable{
            val drawable = context.getDrawable(background)
            return drawable!!
        }
    },
    Stone{
        override val background = R.color.resource_stone_color
         val nameResource = "Wood"
        override fun getBackground(context: Context) : Drawable{
            val drawable = context.getDrawable(background)
            return drawable!!
        }
    },
    Wheat{
        override val background = R.color.resource_wheat_color
         val nameResource = "Wood"
        override fun getBackground(context: Context) : Drawable{
            val drawable = context.getDrawable(background)
            return drawable!!
        }
    },
    Glass{
        override val background = R.color.resource_glass_color
         val nameResource = "Glass"
        override fun getBackground(context: Context) : Drawable{
            val drawable = context.getDrawable(background)
            return drawable!!
        }
    };
    abstract val background : Int
    abstract fun getBackground(context: Context) : Drawable
       
}