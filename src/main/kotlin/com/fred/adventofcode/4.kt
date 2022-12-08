package com.fred.adventofcode

import com.fred.adventofcode.utils.read
import com.fred.adventofcode.utils.toPair

fun main() {
    val input = "/input/4.in".read()

    val ranges = getRanges(input)

    println(ranges.count { it.first contains it.second || it.second contains it.first })
    println(ranges.count { it.first intersects it.second })
}

private fun getRanges(input: String) = input.lines()
    .asSequence()
    .map { it.split(",").toPair() }
    .map { it.first.split("-").toPair() to it.second.split("-").toPair() }
    .map {
        it.first.first.toInt()..it.first.second.toInt() to it.second.first.toInt()..it.second.second.toInt()
    }

infix fun IntRange.contains(other: IntRange) = min() <= other.min() && other.max() <= max()
infix fun IntRange.intersects(other: IntRange) = min() <= other.max() && other.min() <= max()
