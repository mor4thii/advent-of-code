package com.fred.adventofcode

import com.fred.adventofcode.utils.read

val lowercasePriorities = ('a'..'z').mapIndexed { index, c -> c to index + 1 }.toMap()
val uppercasePriorities = ('A'..'Z').mapIndexed { index, c -> c to index + 27 }.toMap()

fun main() {
    val input = "/input/3.in".read()

    println(bothCompartments(input))
    println(threeElves(input))
}

fun bothCompartments(input: String) = input.lines()
    .asSequence()
    .map { splitInHalf(it) }
    .map { intersect(it) }
// This is again opinionated as we assume that there is exactly one element in the intersection. Sanitizing would be appropriate here
    .mapNotNull { it.firstOrNull() }
    .map { if (it.isLowerCase()) lowercasePriorities.getOrDefault(it, 0) else uppercasePriorities.getOrDefault(it, 0) }
    .reduce { a, b -> a + b }

private fun splitInHalf(line: String) =
    (line.length / 2).let { mid -> line.substring(0, mid) to line.substring(mid) }

fun threeElves(input: String) = input.lines()
    .asSequence()
    .chunked(3)
    .map { Triple(it[0], it[1], it[2]) }
    .map { intersect(it) }
// This is again opinionated as we assume that there is exactly one element in the intersection. Sanitizing would be appropriate here
    .mapNotNull { it.firstOrNull() }
    .map { if (it.isLowerCase()) lowercasePriorities.getOrDefault(it, 0) else uppercasePriorities.getOrDefault(it, 0) }
    .reduce { a, b -> a + b }

fun intersect(triple: Triple<String, String, String>) =
    intersect(triple.first to triple.second)
        .intersect(triple.third.toSet())

private fun intersect(it: Pair<String, String>) =
    it.first.toSet()
        .intersect(it.second.toSet())
