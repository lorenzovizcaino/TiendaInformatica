package com.antonio.tiendainformatica.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.antonio.tiendainformatica.R
import com.antonio.tiendainformatica.ui.miscompose.EspacioH
import com.antonio.tiendainformatica.ui.miscompose.EspacioV
import com.antonio.tiendainformatica.ui.model.Productos
import com.antonio.tiendainformatica.ui.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductosShow(navController: NavHostController, viewModel: TiendaInformaticaViewModel) {
    
    Scaffold(
        topBar = {
            MyTopBar(navController,viewModel)
        },
        content = {padding ->
            Contenido(navController,viewModel)
        }
    )
    
    


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(navController: NavHostController, viewModel: TiendaInformaticaViewModel,
    backgroundColor: Color = Color.Black,
    contentColor: Color = Color.Red,
    elevation: Dp = AppBarDefaults.TopAppBarElevation
    ) {
        val context= LocalContext.current
        TopAppBar(

            navigationIcon = {
                IconButton(onClick = {navController.navigate(route = Screens.Menu.route)}) {
                    Icon(imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Ir hacia atras",
                        tint = Color.White
                    )
                }

                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Ir hacia atras",
                        tint = Color.White,

                    )
                }

            },
            title = { Text("${viewModel.email}", color = Color.White) },
            actions = {

                IconButton(onClick = {
                    showToast2("Compartir",context)
                    //compartir("Contenido para compartir",context)
                }) {
                    Icon(imageVector = Icons.Filled.Share,
                        contentDescription = "Compartir",
                        tint = Color.White
                    )
                }



                    IconButton(onClick = {navController.navigate(route = Screens.ResumenCompra.route)}) {
                        if(viewModel.contadorProductos>0){
                            BadgedBox(badge = { Badge { Text(text = "${viewModel.contadorProductos}") } }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.AddShoppingCart,
                                    contentDescription = "Carrito",
                                    tint = Color.White
                                )
                            }
                        }else{
                            Icon(
                                imageVector = Icons.Filled.AddShoppingCart,
                                contentDescription = "Carrito",
                                tint = Color.White
                            )
                        }

                    }

            },
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            elevation = elevation

        )
}

@Composable
fun Contenido(navController: NavHostController, viewModel: TiendaInformaticaViewModel) {
    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp),modifier=Modifier.padding(top=50.dp)) {
        items(viewModel.getProductos()) {
            ItemProducto(
                viewModel = viewModel,
                producto = it,
                onItemSelected = {
                    Toast.makeText(context, it.nombre, Toast.LENGTH_SHORT).show()
                })
        }
    }
    Text(text = viewModel.sumaProductos.toString())
}

@Composable
fun ItemProducto(
    viewModel: TiendaInformaticaViewModel,
    producto: Productos,
    onItemSelected: (Productos) -> Unit
) {
    var isChecked by remember { mutableStateOf(producto.selecionado) }
    var backgroundColor by remember { mutableStateOf(Color.LightGray) }
    backgroundColor = if (producto.selecionado) {
        Color.Green
    } else {
        Color.LightGray
    }
   // viewModel.getbackgroundColorCard(producto)
    Card(border = BorderStroke(2.dp, Color.Red),modifier = Modifier
        .fillMaxWidth()

        .clickable { onItemSelected(producto) }
        .padding(horizontal = 8.dp, vertical = 16.dp)) {
        Column (modifier=Modifier.background(backgroundColor)){
            Image(
                painter = painterResource(id = producto.foto),
                contentDescription = "foto producto",
                modifier = Modifier
                    .padding(30.dp)
                    .width(350.dp)
                    .height(350.dp),

                contentScale = ContentScale.Crop
            )
            Text(
                text = producto.tipoComponente,
                modifier = Modifier.align(Alignment.CenterHorizontally), fontSize = 16.sp
            )
            Text(
                text = producto.nombre,
                modifier = Modifier.align(Alignment.CenterHorizontally), fontSize = 14.sp
            )


            Text(
                text = producto.precio.toString() + "€",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp), fontSize = 20.sp
            )
            Checkbox(
                modifier = Modifier

                    .align(Alignment.CenterHorizontally),
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.White,
                    uncheckedColor = Color.Red,
                    checkmarkColor = Color.Red

                ),
                checked = isChecked,
                onCheckedChange = {
                    isChecked = it
                    producto.selecionado=isChecked
                    if (isChecked) {
                        viewModel.sumarProductos(producto.precio)
                        viewModel.sumarUnidadesProductos()
                        viewModel.listaComprar.add(producto)

                    } else {
                        viewModel.restarProductos(producto.precio)
                        viewModel.restarUnidadesProductos()
                        viewModel.listaComprar.remove(producto)
                    }


                })


        }
    }
}

fun showToast2(string: String, context: Context) {

    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()

}
