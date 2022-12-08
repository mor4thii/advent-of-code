package com.fred.adventofcode

import com.fred.adventofcode.utils.read
import com.fred.adventofcode.utils.transpose
import java.util.*

fun main() {
    val input = "/input/5.in".read()

    println(reorder(input, List<Stack<String>>::popCrates))
    println(reorder(input, List<Stack<String>>::moveCrates))
}

private fun reorder(input: String, action: List<Stack<String>>.(Triple<Int, Int, Int>) -> Unit): String? {
    val stacks = input
        .lines()
        .filter { it.contains('[') }
        .reversed()
        .let { parseStacks(it) }

    input
        .lines()
        .filter { it.contains("move") }
        .let { parseInstructions(it) }
        .forEach {
            stacks.action(it)
        }

    return stacks.map { it.last() }.reduce { a, b -> a.plus(b) }
}

private fun parseStacks(input: List<String>) = input
    .map { it.chunked(4) }
    .map { it.map { s -> s.filter { c -> c.isLetter() } } }
    .transpose()
    .map { it.filter { element -> element.isNotBlank() } }
    .map { Stack<String>().apply { addAll(it) } }

private fun parseInstructions(input: List<String>) = input
    .map { it.replace("[^0-9 ]*".toRegex(), "").trim() }
    .map { it.split("  ") }
    .map { Triple(it[0].toInt(), it[1].toInt(), it[2].toInt()) }

private fun List<Stack<String>>.popCrates(
    it: Triple<Int, Int, Int>,
) {
    for (i in 1..it.first) {
        this[it.third - 1].push(this[it.second - 1].pop())
    }
}

private fun List<Stack<String>>.moveCrates(
    it: Triple<Int, Int, Int>,
) {
    val foo = mutableListOf<String>()
    for (i in 1..it.first) {
        foo.add(this[it.second - 1].pop())
    }
    val reversed = foo.reversed()
    for (i in 1..it.first) {
        this[it.third - 1].push(reversed[i - 1])
    }
}
