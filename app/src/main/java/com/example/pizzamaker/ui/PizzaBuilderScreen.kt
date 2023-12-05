package com.example.pizzamaker.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pizzamaker.R
import com.example.pizzamaker.model.Pizza
import com.example.pizzamaker.model.PizzaSize
import com.example.pizzamaker.model.Topping
import java.text.NumberFormat

object NavRoutes {
    const val PizzaBuilderScreen = "pizzaBuilderScreen"
    const val ReviewOrderScreen = "reviewOrderScreen"
}


@Composable
fun PizzaBuilderScreen (
    modifier: Modifier = Modifier,
    pizzaViewModel: PizzaViewModel,
    navController: NavController
) {
    val pizza = pizzaViewModel.pizza.value

    Column(
        modifier = modifier
    ) {
        ToppingsList(
            pizza = pizza,
            onEditPizza = { newPizza ->
                pizzaViewModel.updatePizza(newPizza)
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true)
        )

        SizeDropdown(
            pizza = pizza,
            onEditPizza = { newPizza ->
                pizzaViewModel.updatePizza(newPizza)
            }
        )

        OrderButton(
            pizza = pizza,
            onOrderClick = {
                pizzaViewModel.updatePizza(pizza) // Update the pizza in ViewModel
                navController.navigate(NavRoutes.ReviewOrderScreen)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

    }

}

@Composable
private fun SizeDropdown(pizza: Pizza, onEditPizza: (Pizza) -> Unit, modifier: Modifier = Modifier) {
    var expanded: Boolean by rememberSaveable { mutableStateOf(false) }
    val sizes = PizzaSize.values()

    Box(modifier = Modifier
        .fillMaxWidth()
        .border(width = 2.dp, color = colorResource(R.color.purple_700))
        .wrapContentSize(Alignment.TopEnd)) {
        Text(
            "Tamanho da pizza: ${stringResource(pizza.size.label)}",
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .padding(vertical = 4.dp, horizontal = 16.dp),
            style = MaterialTheme.typography.body1,

        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = modifier,
        ) {
        sizes.forEach { pizzaSize:PizzaSize ->
            DropdownMenuItem(onClick = {
                onEditPizza(pizza.withSize(pizzaSize))
                expanded = false
            }) {
                Text(text = stringResource(pizzaSize.label))
            }
        }
        }
    }
}

@Composable
private fun ToppingsList(
    pizza: Pizza,
    onEditPizza: (Pizza) -> Unit,
    modifier: Modifier = Modifier
) {
    var toppingBeingAdded:Topping? by rememberSaveable { mutableStateOf(null) }

    toppingBeingAdded?.let { topping ->
        ToppingPlacementDialog (
            topping,
            onSetToppingPlacement = { placement ->
                onEditPizza(pizza.withTopping(topping, placement))
            },
            onDismissRequest = {
                toppingBeingAdded = null
            }
        )
    }

    LazyColumn(modifier = modifier) {
        items(Topping.values()) { topping ->
            ToppingCell(
                topping = topping,
                placement = pizza.toppings[topping],
                onClickTopping = {
                    toppingBeingAdded = topping
                }
            )
        }
    }
}
@Composable
private fun OrderButton(
    pizza: Pizza,
    onOrderClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = {onOrderClick()

        }
    ) {
        val currencyFormatter = remember { NumberFormat.getCurrencyInstance() }
        val price = currencyFormatter.format(pizza.price)
        Text(
            text = stringResource(R.string.place_order_button, price)
                .toUpperCase(Locale.current)
        )
    }
}
@Composable
fun NavigationMain() {
    val navController = rememberNavController()
    val pizzaViewModel = viewModel<PizzaViewModel>() // Create ViewModel instance

    NavHost(navController = navController, startDestination = NavRoutes.PizzaBuilderScreen) {
        composable(NavRoutes.PizzaBuilderScreen) {
            PizzaBuilderScreen(pizzaViewModel = pizzaViewModel, navController = navController)
        }
        composable(NavRoutes.ReviewOrderScreen) {
            ReviewOrderScreen(pizzaViewModel = pizzaViewModel, navController = navController)
        }
    }
}