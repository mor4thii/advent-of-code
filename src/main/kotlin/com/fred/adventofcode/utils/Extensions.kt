package com.fred.adventofcode.utils

fun List<String>.toPair(): Pair<String, String> {
    require(size == 2)
    return first() to last()
}

fun List<List<String>>.transpose() =
    List(this.first().size) { line ->
        List(this.size) { row ->
            this[row][line]
        }
    }