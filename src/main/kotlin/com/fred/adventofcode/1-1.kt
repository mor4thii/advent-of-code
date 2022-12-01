package com.fred.adventofcode

import kotlin.math.min

fun main() {
    val foo = findElfCarryingMaximumCalories("""
        
        
10,00
2000
30.00

4000

5000a
6000               

7000
                   8000
9000
                                                      
10000


""")

    println(foo)
}

private fun findElfCarryingMaximumCalories(input: String): Int {
    var inventoryList = inventoryFrom(input)
    val calories = mutableListOf<List<String>>()

    while (inventoryList.isNotEmpty()) {
        val inventoryEnd = findInventoryEnd(inventoryList)
        calories.add(inventoryList.subList(0, inventoryEnd))
        inventoryList = inventoryList.subList(nextInventory(inventoryEnd, inventoryList.size), inventoryList.size)
    }

    return calories.maxOf { sumCaloriesOfNextElf(it) }
}

private fun inventoryFrom(input: String) = input.lines()
    .dropWhile { it.isBlank() }
    .dropLastWhile { it.isBlank() }

private fun findInventoryEnd(inventoryList: List<String>) =
    inventoryList.indexOfFirst { it.isBlank() }.coerceAtLeast(1)

private fun sumCaloriesOfNextElf(calories: List<String>) = calories
    .map { it.trim() }
    .filter { it.toIntOrNull() != null }
    .sumOf { it.toInt() }

private fun nextInventory(inventoryEnd: Int, inventorySize: Int) =
    min(inventoryEnd + 1, inventorySize)
