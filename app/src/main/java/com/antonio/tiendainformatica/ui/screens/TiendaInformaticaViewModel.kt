package com.antonio.tiendainformatica.ui.screens

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.antonio.tiendainformatica.R
import com.antonio.tiendainformatica.ui.model.Productos

class TiendaInformaticaViewModel: ViewModel(){
    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var loginenabled by mutableStateOf(false)
        private set

    var sumaProductos by mutableStateOf(0.0)
        private set

    var contadorProductos by mutableStateOf(0)
        private set

    var productoSelecionado by mutableStateOf(false)
        private set

//    var backgroundColorCard by mutableStateOf(Color.LightGray)
//        private set

    var lista= mutableListOf<Productos>(
        Productos("Placa Base","MSI PRO Z790-A MAX WIFI",272.99, R.drawable.msi_pro_z790,false),
        Productos("Procesador","Intel Core i5-12400F 2.5 GHz",136.98, R.drawable.intel_core_i5_12400f,false),
        Productos("Disco Duro","Seagate BarraCuda 3.5\" 2TB SATA 3",66.99, R.drawable.seagate_barracuda_2tb,false),
        Productos("Tarjeta Grafica","MSI GeForce RTX 4060 Ti GAMING X 16GB",529.65, R.drawable.msi_geforce_rtx4060,false),
        Productos("Memoria Ram","Corsair Vengeance RGB DDR5 White 5600MHz 64GB",241.45, R.drawable.corsair_vengeance_ddr5,false),
        Productos("Tarjeta Sonido","Creative Sound BlasterX AE-5 Plus",107.99, R.drawable.creative_sound_blaster,false),
        Productos("Cajas PC","Forgeon Arcanite ARGB Mesh Torre ATX Negra",159.99, R.drawable.torre_forgeon_arcanite,false),
        Productos("Fuente alimentación","Nox Hummer GD750 750W 80 Plus Gold",91.98, R.drawable.nox_hummer_gd750,false),
        Productos("Ventilador CPU","Forgeon Solarian Cooler 4Pipes 120mm",59.99, R.drawable.forgeon_solarian,false),
        Productos("Monitor","ASUS ROG Swift PG42UQ 41.5\" OLED",1520.48, R.drawable.asus_rog_monitor,false),
    )
        private set

//    fun getbackgroundColorCard(producto:Productos){
//        backgroundColorCard = if (producto.selecionado) {
//            Color.Green
//        } else {
//            Color.LightGray
//        }
//    }


    fun getEmail(email:String){
        this.email=email
    }

    fun getPassword(password:String){
        this.password=password
    }

    fun getloginenabled(loginenabled:Boolean){
        this.loginenabled=Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6
    }

    fun getProductoSelecionado(productoSelecionado:Boolean){
        this.productoSelecionado=productoSelecionado
    }

    fun sumarProductos(precio:Double){
        sumaProductos+=precio
    }

    fun sumarUnidadesProductos(){
        contadorProductos++
    }

    fun restarUnidadesProductos(){
        contadorProductos--
    }

    fun restarProductos(precio:Double){
        sumaProductos-=precio
    }

    fun getProductos(): MutableList<Productos> {
        return lista

//        listOf(
//            Productos("Placa Base","MSI PRO Z790-A MAX WIFI",272.99, R.drawable.msi_pro_z790,false),
//            Productos("Procesador","Intel Core i5-12400F 2.5 GHz",136.98, R.drawable.intel_core_i5_12400f,false),
//            Productos("Disco Duro","Seagate BarraCuda 3.5\" 2TB SATA 3",66.99, R.drawable.seagate_barracuda_2tb,false),
//            Productos("Tarjeta Grafica","MSI GeForce RTX 4060 Ti GAMING X 16GB",529.65, R.drawable.msi_geforce_rtx4060,false),
//            Productos("Memoria Ram","Corsair Vengeance RGB DDR5 White 5600MHz 64GB",241.45, R.drawable.corsair_vengeance_ddr5,false),
//            Productos("Tarjeta Sonido","Creative Sound BlasterX AE-5 Plus",107.99, R.drawable.creative_sound_blaster,false),
//            Productos("Cajas PC","Forgeon Arcanite ARGB Mesh Torre ATX Negra",159.99, R.drawable.torre_forgeon_arcanite,false),
//            Productos("Fuente alimentación","Nox Hummer GD750 750W 80 Plus Gold",91.98, R.drawable.nox_hummer_gd750,false),
//            Productos("Ventilador CPU","Forgeon Solarian Cooler 4Pipes 120mm",59.99, R.drawable.forgeon_solarian,false),
//            Productos("Monitor","ASUS ROG Swift PG42UQ 41.5\" OLED",1520.48, R.drawable.asus_rog_monitor,false),
//
//
//        )
    }








}