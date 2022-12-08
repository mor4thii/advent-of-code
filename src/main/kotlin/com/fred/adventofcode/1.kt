package com.fred.adventofcode

import com.fred.adventofcode.utils.read
import kotlin.math.min

fun main() {
    val input = "/input/1.in".read()

    val max = findElfCarryingMaximumCalories(
        input, 3
    )

    println(max.last())
    println(max.sum())
}

private fun findElfCarryingMaximumCalories(input: String, n: Int): List<Int> {
    var inventoryList = inventoryFrom(input)
    val calories = mutableListOf<List<String>>()

    while (inventoryList.isNotEmpty()) {
        val inventoryEnd = findInventoryEnd(inventoryList)
        calories.add(inventoryList.subList(0, inventoryEnd))
        inventoryList = inventoryList.subList(nextInventory(inventoryEnd, inventoryList.size), inventoryList.size)
    }

    return calories.map { sumCaloriesOfNextElf(it) }.sorted().takeLast(n)
}

private fun inventoryFrom(input: String) = input.lines()
    .dropWhile { it.isBlank() }
    .dropLastWhile { it.isBlank() }

private fun findInventoryEnd(inventoryList: List<String>) =
    inventoryList.indexOfFirst { it.isBlank() }.coerceAtLeast(1)

private fun nextInventory(inventoryEnd: Int, inventorySize: Int) =
    min(inventoryEnd + 1, inventorySize)

private fun sumCaloriesOfNextElf(calories: List<String>) = calories
    .map { it.trim() }
    .filter { it.toIntOrNull() != null }
    .sumOf { it.toInt() }
