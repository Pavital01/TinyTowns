package com.example.tinytowns

enum class Buildings {
    Abbey{
        override val buildName = "Abbey"
        override val buildColor = "orange"
    },
    Almshouse{
        override val buildName = "Almshouse"
        override val buildColor = "green"
    },
    Bakery{
        override val buildName = "Bakery"
        override val buildColor = "yellow"
    },
    Bank{
        override val buildName = "Bank"
        override val buildColor = "black"
    },
    Chapel{
        override val buildName = "Chapel"
        override val buildColor = "orange"
    },
    Cloister{
        override val buildName = "Cloister"
        override val buildColor = "orange"
    },
    Cottage{
        override val buildName = "Cottage"
        override val buildColor = "blue"
    },
    Factory{
        override val buildName = "Factory"
        override val buildColor = "black"
    },
    Farm{
        override val buildName = "Farm"
        override val buildColor = "red"
    },
    FeastHall{
        override val buildName = "Feast Hall"
        override val buildColor = "green"
    },
    Fountain{
        override val buildName = "Fountain"
        override val buildColor = "gray"
    },
    Granary{
        override val buildName = "Granary"
        override val buildColor = "red"
    },
    Greenhouse{
        override val buildName = "Greenhouse"
        override val buildColor = "red"
    },
    Inn{
        override val buildName = "Inn"
        override val buildColor = "green"
    },
    Market{
        override val buildName = "Market"
        override val buildColor = "yellow"
    },
    Millstone{
        override val buildName = "Millstone "
        override val buildColor = "gray"
    },
    Orchard{
        override val buildName = "Orchard"
        override val buildColor = "red"
    },
    Shed{
        override val buildName = "Shed"
        override val buildColor = "gray"
    },
    Tailor{
        override val buildName = "Tailor"
        override val buildColor = "yellow"
    },
    Tavern{
        override val buildName = "Tavern"
        override val buildColor = "green"
    },
    Temple{
        override val buildName = "Temple"
        override val buildColor = "orange"
    },
    Theater{
        override val buildName = "Theater"
        override val buildColor = "yellow"
    },
    TradingPost{
        override val buildName = "Trading Post"
        override val buildColor = "black"
    },
    Warehouse{
        override val buildName = "Warehouse"
        override val buildColor = "black"
    },
    Well{
        override val buildName = "Fountain"
        override val buildColor = "gray"
    };


    abstract val buildName : String
    abstract val buildColor : String


}