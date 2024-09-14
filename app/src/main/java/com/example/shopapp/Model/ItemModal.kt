package com.example.shopapp.Model

data class ItemModal(
    var title:String ="",
    var description: String = "",
    var picUrl: ArrayList<String> = ArrayList(),
    var size: ArrayList<String> = ArrayList(),
    var price: Double = 0.0,
    var rating: Double = 0.0,
//    var numberincart: Int = 8
)