package com.example.pizzamaker.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pizzamaker.model.PizzaViewModel
import java.text.NumberFormat

@Composable
fun ReviewOrderScreen(pizzaViewModel: PizzaViewModel, navController: NavController) {
    val pizza = pizzaViewModel.pizza.value
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Text(
            text = "Revise seu pedido",
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Tamanho: ${stringResource(pizza.size.label)}",
            style = MaterialTheme.typography.body1
        )
        Text(
            text = "Recheios:",
            style = MaterialTheme.typography.body1
        )
        pizza.toppings.forEach { (topping, _) ->
            Text(
                text = stringResource(topping.toppingName),
                style = MaterialTheme.typography.body2
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        val currencyFormatter = remember { NumberFormat.getCurrencyInstance() }
        val price = currencyFormatter.format(pizza.price)
        Text(
            text = "Total: $price",
            style = MaterialTheme.typography.body1,
            color = androidx.compose.ui.graphics.Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (pizza.toppings.isEmpty()) {
                    Toast.makeText(context, "Pizza não foi feita. Adicione recheios para fazer a pizza.", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                } else {
                    pizzaViewModel.resetPizza()
                    navController.popBackStack()
                    Toast.makeText(context, "Pedido Feito!", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Faça seu pedido")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {navController.popBackStack()}
        ) {
            Text("Editar pedido")
        }

    }

}