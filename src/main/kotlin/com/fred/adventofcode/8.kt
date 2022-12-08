package com.fred.adventofcode

import com.fred.adventofcode.utils.read
import kotlin.math.max

fun main() {
    val input = "/input/8.in"
        .read()
        .lines()
        .map { it.map(Char::digitToInt) }

    println(treesSeenFromTheOutside(input))
    println(viewingDistance(input))
}

private fun treesSeenFromTheOutside(
    input: List<List<Int>>,
): Int {
    val indices = input.indices
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

    return visible.size
}

private fun viewingDistance(
    input: List<List<Int>>,
): Int {
    val indices = input.indices
    val vonNeumannNeighbors = listOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)

    var best = 0
    for (y in indices) {
        for (x in indices) {
            var scenicValue = 1
            for ((dy, dx) in vonNeumannNeighbors) {
                var distance = 0
                var ys = y + dy
                var xs = x + dx
                while (indices.twoDimensionallyContains(ys, xs)) {
                    distance += 1
                    if (input[ys][xs] >= input[y][x]) break
                    ys += dy
                    xs += dx
                }
                scenicValue *= distance
            }
            best = max(best, scenicValue)
        }
    }

    return best
}

private fun IntRange.twoDimensionallyContains(ys: Int, xs: Int) =
    this.contains(ys) && this.contains(xs)