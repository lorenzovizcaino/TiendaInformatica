package com.antonio.tiendainformatica.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.antonio.tiendainformatica.ui.miscompose.EspacioH
import com.antonio.tiendainformatica.ui.miscompose.EspacioV
import com.antonio.tiendainformatica.ui.model.Productos
import com.antonio.tiendainformatica.ui.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumenCompra(navController: NavHostController, viewModel: TiendaInformaticaViewModel) {


    Scaffold(
        topBar = {
            MyTopBar2(navController, viewModel)
        },
        content = { padding ->
            ElementosComprados(viewModel)
        }
    )


}

@Composable
fun MyTopBar2(
    navController: NavHostController, viewModel: TiendaInformaticaViewModel,
    backgroundColor: Color = Color.Black,
    contentColor: Color = Color.Red,
    elevation: Dp = AppBarDefaults.TopAppBarElevation
) {
    val context = LocalContext.current
    TopAppBar(

        navigationIcon = {
            IconButton(onClick = { navController.navigate(route = Screens.ProductosShow.route) }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Ir hacia atras",
                    tint = Color.White
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "cuenta cliente",
                    tint = Color.White,

                    )
            }


        },
        title = { Text(" ${viewModel.email}", color = Color.White) },
        actions = {

            IconButton(onClick = {
                showToast2("Compartir", context)
                //compartir("Contenido para compartir",context)
            }) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "Compartir",
                    tint = Color.White
                )
            }


        },
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation

    )

}

@Composable
fun ElementosComprados(viewModel: TiendaInformaticaViewModel) {
    //val scrollState = rememberScrollState()
    val context = LocalContext.current
    Column(/*modifier = Modifier.verticalScroll(scrollState)*/) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 70.dp)
        ) {
            items(viewModel.getProductosComprar()) {
                ItemProductoComprado(
                    viewModel = viewModel,
                    producto = it,
                    onItemSelected = {
                        Toast.makeText(context, it.nombre, Toast.LENGTH_SHORT).show()
                    })
            }


        }
        CalculoDelTotal(viewModel)
        EspacioH(espacio = 20)
        BotonTramitarPedido(viewModel,context)



    }
}

@Composable
fun BotonTramitarPedido(viewModel: TiendaInformaticaViewModel, context: Context) {
    Button(
        onClick = { Toast.makeText(context, "Pedido Tramitado", Toast.LENGTH_SHORT).show()}, modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = ButtonDefaults.buttonColors(Color(255,216,20)),
    ) {
        if(viewModel.contadorProductos>1){
            Text(text = "Tramitar pedido (${viewModel.contadorProductos} productos)",
                color = Color.White,
                fontSize = 18.sp)
        }else{
            Text(text = "Tramitar pedido (${viewModel.contadorProductos} producto)",
                color = Color.White,
                fontSize = 18.sp)
        }

    }
}

@Composable
fun CalculoDelTotal(viewModel: TiendaInformaticaViewModel) {
    Card(
        border = BorderStroke(2.dp, Color(255,216,20)), modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)

            .padding(horizontal = 5.dp, vertical = 4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
        ) {
            Text(
                text = "TOTAL",
                modifier = Modifier
                    .weight(3f)
                    .padding(top = 5.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = viewModel.getTotalFormateado() + "€",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 5.dp, top = 5.dp), fontSize = 18.sp
            )
        }


    }
}


@Composable
fun ItemProductoComprado(
    viewModel: TiendaInformaticaViewModel,
    producto: Productos,
    onItemSelected: () -> Unit
) {

    Card(
        border = BorderStroke(2.dp, Color(255,216,20)), modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {
            Image(
                painter = painterResource(id = producto.foto),
                contentDescription = "foto producto",
                modifier = Modifier
                    .padding(10.dp)
                    .width(50.dp)
                    .height(50.dp)
                    .weight(1f),


                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .weight(3f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = producto.tipoComponente,
                    fontSize = 16.sp
                )
                Text(
                    text = producto.nombre,
                    style = TextStyle(lineHeight = 16.sp),
                    fontSize = 14.sp,

                    )


            }
            Text(
                text = producto.precio.toString() + "€",
                color = Color.Black,

                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 24.dp, end = 5.dp), fontSize = 18.sp
            )


        }
    }

}
