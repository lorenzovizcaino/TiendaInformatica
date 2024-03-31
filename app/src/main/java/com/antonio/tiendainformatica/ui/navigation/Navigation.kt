package com.antonio.tiendainformatica.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.antonio.tiendainformatica.ui.screens.Menu


import com.antonio.tiendainformatica.ui.screens.TiendaInformaticaViewModel
import com.antonio.tiendainformatica.ui.screens.ProductosShow
import com.antonio.tiendainformatica.ui.screens.ResumenCompra

@Composable
fun Navigation() {
    val navController = rememberNavController() //linea para recordar NavController entre pantallas
    val viewModel= remember{ TiendaInformaticaViewModel() } //linea para recordar viewModel entre pantallas
    NavHost(navController, startDestination = Screens.Menu.route) {
        //pantalla principal con la navegación
        composable(route = Screens.Menu.route) {
            Menu(navController, viewModel) }//Nombre del fichero .kt al que navegar

        composable(route = Screens.ProductosShow.route) {
            ProductosShow(navController,viewModel) //Nombre de la función composable a la que navegar
        }
        composable(route = Screens.ResumenCompra.route) {
            ResumenCompra(navController,viewModel) //Nombre de la función composable a la que navegar
        }



    }
}