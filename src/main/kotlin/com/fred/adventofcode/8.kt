package com.fred.adventofcode

import com.fred.adventofcode.utils.read
import kotlin.math.max

fun main() {
    val input = "/input/8.in"
        .read()
        .lines()
        .map { it.map(Char::digitToInt) }

    // Input has same length in both dimensions, so keeping it simple here
    val indices = input.indices

    println(treesSeenFromTheOutside(indices, input))
}

private fun treesSeenFromTheOutside(
    indices: IntRange,
    input: List<List<Int>>,
): MutableSet<Pair<Int, Int>> {
    val visible = mutableSetOf<Pair<Int, Int>>()

    for (i in indices) {
        for (direction in listOf(indices, indices.reversed())) {
            var maximum = -1
            for (x in direction) {
                val current = input[i][x]
                if (current > maximum) {
                    visible.add(i to x)
                }
                maximum = max(maximum, current)
            }
            maximum = -1
            for (y in direction) {
                val current = input[y][i]
                if (current > maximum) {
                    visible.add(y to i)
                }
                maximum = max(maximum, current)
            }
        }
    }
    return visible
}