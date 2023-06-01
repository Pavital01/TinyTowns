package com.example.tinytowns


enum class Buildings {
    Default{
        override val background = Resources.Default.background
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Wood.background
        )
    },
    Abbey{
         val buildName = R.string.building_name_abbey
         val buildColor = "orange"
         override val background = R.drawable.orange_building_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Stone.background,
            Resources.Brick.background,
            Resources.Glass.background,
        )
    },
    Almshouse{
         val buildName = R.string.building_name_almshouse
         val buildColor = "green"
        override val background = R.drawable.green_building_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Stone.background,
            Resources.Glass.background,
        )
    },
    Bakery{
         val buildName = R.string.building_name_abbey
         val buildColor = "yellow"
        override val background = R.drawable.yellow_buildin_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Wood.background
        )
    },
    Bank{
         val buildName = R.string.building_name_abbey
         val buildColor = "black"
        override val background = R.drawable.black_building_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Wheat.background,
            Resources.Wheat.background,
            Resources.Wood.background,
            Resources.Glass.background,
            Resources.Brick.background,
        )
    },
    Chapel{
         val buildName = R.string.building_name_abbey
         val buildColor = "orange"
        override val background = R.drawable.orange_building_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Wood.background
        )
    },
    Cloister{
         val buildName = R.string.building_name_abbey
         val buildColor = "orange"
        override val background = R.drawable.orange_building_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Wood.background
        )
    },
    Cottage{
         val buildName = R.string.building_name_abbey
         val buildColor = "blue"
        override val background = R.drawable.cottage_ico
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Wheat.background,
            Resources.Glass.background,
            Resources.Brick.background,
        )
    },
    Factory{
         val buildName = R.string.building_name_abbey
         val buildColor = "black"
        override val background = R.drawable.black_building_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Wood.background
        )
    },
    Farm{
         val buildName = R.string.building_name_abbey
         val buildColor = "red"
        override val background = R.drawable.red_building_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Wheat.background,
            Resources.Wheat.background,
            Resources.Wood.background,
            Resources.Wood.background
        )
    },
    FeastHall{
         val buildName = R.string.building_name_abbey
         val buildColor = "green"
        override val background = R.drawable.green_building_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Wood.background
        )
    },
    Fountain{
         val buildName = R.string.building_name_abbey
         val buildColor = "gray"
        override val background = R.drawable.gray_building_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Wood.background
        )
    },
    Granary{
         val buildName = R.string.building_name_abbey
         val buildColor = "red"
        override val background = R.drawable.red_building_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Wood.background
        )
    },
    Greenhouse{
         val buildName = R.string.building_name_abbey
         val buildColor = "red"
        override val background = R.drawable.red_building_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Wood.background
        )
    },
    Inn{
         val buildName = R.string.building_name_abbey
         val buildColor = "green"
        override val background = R.drawable.green_building_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Wood.background
        )
    },
    Market{
         val buildName = R.string.building_name_abbey
         val buildColor = "yellow"
        override val background = R.drawable.yellow_buildin_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Wood.background
        )
    },
    Millstone{
         val buildName = R.string.building_name_abbey
         val buildColor = "gray"
        override val background = R.drawable.gray_building_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Wood.background
        )
    },
    Orchard{
         val buildName = R.string.building_name_abbey
         val buildColor = "red"
        override val background = R.drawable.red_building_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Wood.background
        )
    },
    Shed{
         val buildName = R.string.building_name_abbey
         val buildColor = "gray"
        override val background = R.drawable.gray_building_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Wood.background
        )
    },
    Tailor{
         val buildName = R.string.building_name_abbey
         val buildColor = "yellow"
        override val background = R.drawable.yellow_buildin_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Wood.background
        )
    },
    Tavern{
         val buildName = R.string.building_name_abbey
         val buildColor = "green"
        override val background = R.drawable.green_building_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Wood.background
        )
    },
    Temple{
         val buildName = R.string.building_name_abbey
         val buildColor = "orange"
        override val background = R.drawable.orange_building_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Wood.background
        )
    },
    Theater{
         val buildName = R.string.building_name_abbey
         val buildColor = "yellow"
        override val background = R.drawable.yellow_buildin_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Glass.background,
            Resources.Wood.background,
            Resources.Wood.background
        )
    },
    TradingPost{
         val buildName = R.string.building_name_abbey
         val buildColor = "black"
        override val background = R.drawable.black_building_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Wood.background
        )
    },
    Warehouse{
         val buildName = R.string.building_name_abbey
         val buildColor = "black"
        override val background = R.drawable.black_building_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Wood.background
        )
    },
    Well{
         val buildName = R.string.building_name_abbey
         val buildColor = "gray"
        override val background = R.drawable.gray_building_logo
        override var resources: MutableList<Int> = mutableListOf(
            Resources.Stone.background,
            Resources.Wood.background
        )
    };
    abstract val resources : MutableList<Int>
    abstract val background : Int
}