package com.example.tinytowns

enum class Resource {
    Wood{
        override val buildName = "Wood"
    },
    Brick{
        override val buildName = "Wood"
    },
    Stone{
        override val buildName = "Wood"
    },
    Wheat{
        override val buildName = "Wood"
    },
    Glass{
        override val buildName = "Glass"
    };
    abstract val buildName : String
}