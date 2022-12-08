package com.fred.adventofcode

import com.fred.adventofcode.utils.read

enum class Shape(val value: Int) {
    ROCK(1), PAPER(2), SCISSORS(3);

    fun outcomeAgainst(other: Shape) = when (this) {
        other -> 3
        ROCK -> if (other == SCISSORS) 6 else 0
        PAPER -> if (other == ROCK) 6 else 0
        SCISSORS -> if (other == PAPER) 6 else 0
    }

    fun losesAgainst() = when (this) {
        ROCK -> PAPER
        PAPER -> SCISSORS
        SCISSORS -> ROCK
    }

    fun winsAgainst() = when (this) {
        ROCK -> SCISSORS
        PAPER -> ROCK
        SCISSORS -> PAPER
    }

    companion object {
        fun from(input: String) = when (input) {
            "A", "X" -> ROCK
            "B", "Y" -> PAPER
            "C", "Z" -> SCISSORS
            else -> throw IllegalArgumentException("Cannot convert $input into a shape")
        }
    }

}

enum class Outcome {
    LOSE, DRAW, WIN;

    fun against(shape: Shape) = when (this) {
        DRAW -> shape
        LOSE -> shape.winsAgainst()
        WIN -> shape.losesAgainst()
    }

    companion object {
        fun from(input: String) = when (input) {
            "X" -> LOSE
            "Y" -> DRAW
            "Z" -> WIN
            else -> throw IllegalArgumentException("Cannot convert $input into a outcome")
        }
    }

}

fun main() {
    val input = "/input/2.in".read()
    println(score(input, ::playStrategy))
    println(score(input, ::outcomeStrategy))
}

fun score(input: String, strategy: (String) -> List<Pair<Shape, Shape>>) =
    strategy(input)
        .map { outcome(it) }
        .reduce { a, b -> a + b }

private fun outcome(match: Pair<Shape, Shape>) =
    match.second.value + match.second.outcomeAgainst(match.first)

// Input sanitation should happen here before naively applying this function
private fun playStrategy(input: String) =
    input.lines()
        .map { it.split(" ") }
        .map { Shape.from(it.first()) to Shape.from(it.last()) }

private fun outcomeStrategy(input: String) =
    input.lines()
        .map { it.split(" ") }
        .map { Shape.from(it.first()) to Outcome.from(it.last()) }
        .map { it.first to it.second.against(it.first) }




