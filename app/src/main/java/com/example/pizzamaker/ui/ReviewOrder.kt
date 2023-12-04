package com.example.pizzamaker.ui

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pizzamaker.model.Pizza
import java.text.NumberFormat

@Composable
fun ReviewOrderScreen(pizza: Pizza) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Text(
            text = "Review Your Order",
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Size: ${stringResource(pizza.size.label)}",
            style = MaterialTheme.typography.body1
        )
        Text(
            text = "Toppings:",
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
            text = "Total Price: $price",
            style = MaterialTheme.typography.body1,
            color = androidx.compose.ui.graphics.Color.Green
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Add action for placing the order
            }
        ) {
            Text("Place Order")
        }

    }

}

@Preview
@Composable
fun prev() {
    ReviewOrderScreen(pizza = Pizza())
}