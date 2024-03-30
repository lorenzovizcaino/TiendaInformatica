package com.antonio.tiendainformatica.ui.model

import androidx.annotation.DrawableRes

data class Productos(
    var tipoComponente:String,
    var nombre:String,
    var precio:Double,
    @DrawableRes var foto:Int,
    var selecionado:Boolean
)
