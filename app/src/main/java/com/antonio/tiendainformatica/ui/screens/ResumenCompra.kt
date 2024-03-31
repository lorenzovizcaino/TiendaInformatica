package com.antonio.tiendainformatica.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.antonio.tiendainformatica.ui.model.Productos

@Composable
fun ResumenCompra(navController: NavHostController, viewModel: TiendaInformaticaViewModel){
    val context = LocalContext.current
    Column(){
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp),modifier= Modifier.padding(top=50.dp)) {
            items(viewModel.getProductosComprar()) {
                ItemProductoComprado(
                    viewModel = viewModel,
                    producto = it,
                    onItemSelected = {
                        Toast.makeText(context, it.nombre, Toast.LENGTH_SHORT).show()
                    })
            }



        }
        Card(border = BorderStroke(2.dp, Color.Green),modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .align(Alignment.CenterHorizontally)
            .padding(horizontal = 5.dp, vertical = 4.dp)) {
            Row(horizontalArrangement = Arrangement.Center){
                Text(text = "TOTAL",modifier= Modifier.weight(3f), textAlign = TextAlign.Center)
                Text(text = viewModel.sumaProductos.toString()+"€",
                    color = Color.Green,


                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    modifier = Modifier

                        .weight(1f)
                        .padding(end = 5.dp), fontSize = 18.sp)
            }
            

        }

    }


}



@Composable
fun ItemProductoComprado(viewModel: TiendaInformaticaViewModel, producto: Productos, onItemSelected: () -> Unit) {

    Card(border = BorderStroke(2.dp, Color.Green),modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 5.dp, vertical = 4.dp)) {
        Row (modifier= Modifier
            .fillMaxWidth()
            .background(Color.Gray)){
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
            Column (modifier = Modifier
                .padding(top = 16.dp)
                .weight(3f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start){
                Text(
                    text = producto.tipoComponente,
                    fontSize = 16.sp
                )
                Text(
                    text = producto.nombre,
                    fontSize = 14.sp
                )



            }
            Text(
                text = producto.precio.toString() + "€",
                color = Color.Green,

                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 24.dp, end = 5.dp), fontSize = 18.sp
            )



        }
    }

}
