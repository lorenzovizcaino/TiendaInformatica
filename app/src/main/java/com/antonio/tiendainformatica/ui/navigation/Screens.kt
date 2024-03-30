package com.antonio.tiendainformatica.ui.navigation

sealed class Screens(val route:String){
    object Menu: Screens("initial_screen")//info a aparecer en pantalla


    object ProductosShow: Screens("pantalla1")//info a aparecer en pantalla

}
