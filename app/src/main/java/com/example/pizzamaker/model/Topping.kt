package com.example.pizzamaker.model

import androidx.annotation.StringRes
import com.example.pizzamaker.R

enum class Topping(
    @StringRes val toppingName: Int
) {
    Presunto(
        R.string.topping_basil
    ),
    Queijo(
        toppingName = R.string.topping_mushroom
    ),
    Pepperoni(
        toppingName = R.string.topping_olive
    ),
    Camarão(
        toppingName = R.string.topping_peppers
    ),
    Bacon(
        toppingName = R.string.topping_pepperoni
    ),
    Calabresa(
        toppingName = R.string.topping_pineapple
    )
}