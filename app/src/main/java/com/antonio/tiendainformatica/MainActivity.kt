package com.antonio.tiendainformatica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.antonio.tiendainformatica.ui.navigation.Navigation
import com.antonio.tiendainformatica.ui.screens.ProductosShow
import com.antonio.tiendainformatica.ui.screens.TiendaInformaticaViewModel
import com.antonio.tiendainformatica.ui.theme.TiendaInformaticaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController() //linea para recordar NavController entre pantallas
            val viewModel= remember{ TiendaInformaticaViewModel() } //linea para recordar viewModel entre pantallas
            TiendaInformaticaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                    //ProductosShow(navController, viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TiendaInformaticaTheme {
        Greeting("Android")
    }
}