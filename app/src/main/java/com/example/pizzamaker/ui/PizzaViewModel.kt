package com.example.pizzamaker.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.pizzamaker.model.Pizza

class PizzaViewModel : ViewModel() {
    var pizza = mutableStateOf(Pizza())
        private set

    fun updatePizza(newPizza: Pizza) {
        pizza.value = newPizza
    }

    fun resetPizza() {
        pizza.value = Pizza()
    }
}