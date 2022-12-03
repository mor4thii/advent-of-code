package com.fred.adventofcode

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

    fun winsAgainst() = when(this) {
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

fun score(input: String) =
    roundsStrategy(input)
        .map { outcome(it) }
        .reduce { a, b -> a + b }

// Input sanitation should happen here before naively applying this function
// 2-1
//private fun roundsStrategy(input: String) =
//    input.lines()
//        .map { it.split(" ") }
//        .map { Shape.from(it.first()) to Shape.from(it.last()) }

// 2-2
private fun roundsStrategy(input: String) =
    input.lines()
        .map { it.split(" ") }
        .map { Shape.from(it.first()) to Outcome.from(it.last()) }
        .map { it.first to it.second.against(it.first) }

private fun outcome(match: Pair<Shape, Shape>) =
    match.second.value + match.second.outcomeAgainst(match.first)

fun main() {
    val input = """B Y
C Z
C Y
C Y
A X
C Y
C Y
C Y
A X
B X
B Y
B Y
B Z
C Z
C Z
C Z
B Y
A Z
C Y
C X
B Z
C Y
C Z
B Y
B Y
A X
B Y
A X
C Y
B Y
B X
A Y
C X
A Y
B Y
B Y
A Y
B Y
A Z
B Y
B X
C Z
B X
B Y
A Z
B X
C Y
C X
C Y
C Y
B Y
C Z
C Z
B Y
B Y
B Y
C X
B Y
B Z
B Y
B X
C X
B X
A Y
C Z
C X
B Y
B Y
C Z
B X
C X
C Y
C Y
C Y
B X
C Z
C Z
B Z
B Y
C Y
C Y
B Z
A Y
B Y
A X
B Y
A X
A X
B Y
B Z
A X
C Y
C Z
C Y
C Z
B X
C Y
A X
B Y
C Y
C Z
A Z
A Z
C Z
A Y
C Y
B Y
B Z
C X
A Z
A Y
C Z
B X
C Y
C Y
B Z
C X
B Y
C Y
A X
C X
B Y
C Y
B Y
C X
B Z
C Y
C Z
B Y
B Y
B Y
B Y
B Z
C Y
B Z
A Y
C Z
C Z
C Z
B Y
B X
C Z
C Z
C Z
C Z
C Z
B Y
C Y
A Z
B Y
C Y
A Y
B Y
C X
A X
A Y
C Z
C Y
B X
C Z
B Y
C Y
C Y
C X
B Z
B Z
C Z
A X
B Z
C Y
C X
C Z
A X
A X
C X
B Y
C Y
B Y
B Y
B X
C X
B Y
B Y
C Y
A Y
C Y
B Y
C Y
A Y
B Z
C Y
B Y
C Y
B Z
A X
C Z
C Y
B Y
C Y
C Y
A X
B Y
C Y
A Z
C X
A X
B Y
A X
A X
C Z
B Y
C Z
C Z
C Z
C Z
B Y
C Y
A Z
A X
B Y
C Z
B Z
C X
C X
B Y
C Y
B Y
B Z
B Z
C X
B Y
C X
C X
A Z
B Y
B X
A Z
A Y
A X
A Y
C Y
A X
A Y
C Y
C Z
B Y
A Y
C Y
B X
A Y
B Y
A Y
B Y
C Y
A Y
B Y
C Y
C Y
C Y
B Y
C Z
C Z
A Z
B Y
C X
B Z
C Y
A X
B X
B Z
A Y
C Z
A X
A Z
C Y
B Y
B Y
B X
B Y
B Y
B Z
A Y
B Y
A X
C Y
C Z
C Z
B Y
C Y
B Y
C X
C Y
A Y
B Y
C Z
B Y
C Y
C Y
B X
A Z
A Y
A Y
C Y
C Y
B Y
B Z
B Y
A Z
B Y
A X
C Y
A X
C Z
C Z
C Y
C Y
A Y
C Z
C Z
C Y
B Y
C Z
B Z
A X
C Z
A Y
B Y
A X
A X
A X
B Z
B Y
A Y
A X
B X
C Z
B Y
C X
C Z
A Z
C Y
A Z
A Y
A Z
C Y
A Z
C Y
B Y
B Y
A X
A Y
B Y
B X
A Y
C Z
B Y
C Y
C Z
B Y
C Y
A X
A X
B Y
B Y
C Z
A X
B Z
A X
C Y
B X
C Z
B Y
C Y
A Z
C Z
C Y
C Z
C Y
B X
B X
C Z
A Z
C X
C X
A X
B Z
A X
B X
A Y
B Y
C Y
C Z
C Y
C Z
C Z
B Y
C Y
B Z
C Z
C Z
C Y
B Y
C Y
A Y
B Y
C Z
B Y
A X
C Y
C X
C Z
B Y
B X
B Y
C Y
B Z
A Z
C Z
C Y
C Z
C Y
A X
B Y
A X
B Y
B Y
A X
C Y
B X
A X
C Z
C Z
A X
A Y
A Y
C X
B Y
A X
B Y
A Z
A X
C Y
A X
C Y
C Z
C Y
B Z
A X
B Z
B Z
A Z
C X
C Y
B Y
C X
C Y
C X
C X
B X
C X
C Z
A X
A Y
A X
B X
B Y
B Y
C Z
B Z
B Z
B Y
C Z
C Y
C Z
B X
C Y
C Y
C Z
B X
C Y
C Z
B Y
B Z
C X
B Z
C Y
A X
A X
A Z
B Y
C X
C Y
B X
A X
A Y
C Z
A Y
C Y
C Z
C X
C Z
C Z
A X
B Y
A X
A Z
B X
C X
B X
B Y
C Y
C Y
C Z
C Y
C Y
C X
C Z
C Y
B Z
C Y
B Y
A Y
B Y
C Y
A X
C Y
B Z
B Y
C X
C X
C Z
C Y
B Y
C Y
B Y
B Y
C X
C X
C Y
B Y
B Y
B X
C X
A Z
B X
C Y
B X
C Y
C Y
B X
B X
C Y
A X
B Y
A Y
B Y
B Y
B Y
A X
C Z
A Y
C X
A Y
C Y
A X
C Y
C Z
B Y
B Y
A Y
C Y
B Y
C Y
C Y
A Z
B Y
A X
C Z
C Y
A X
A X
C X
B Y
A X
A Z
C Y
C Z
B Y
A X
C Z
C Z
B Y
B X
C Y
B Y
B Y
C Z
B X
B Z
C Y
C Y
B X
C Z
C X
A Y
B X
C Y
B Y
C Z
C Y
B Y
C Z
C X
A Y
B Y
C Z
C Y
A Y
C Y
B Y
B Y
C Y
B Z
C Y
C Y
B Z
C Y
B Y
A X
C Z
C Y
C Z
C Y
A X
A Z
C Y
A Y
C X
B X
A X
C Y
C Z
A X
C Y
C Y
A Z
C X
B Z
C X
C Y
B Z
A X
A X
B Z
A X
A Y
B X
C Y
A Y
C Y
A Z
C Y
B X
B Y
A Y
C Y
C Y
C Y
C Z
B X
C Z
C Y
B Y
B Y
C Z
B Z
B Y
A X
C Y
C X
A Y
B Z
C X
A Z
B Z
C Z
C Z
C Y
C Y
B Y
B Y
B X
A Z
C Y
A Y
C Z
A Y
A X
C Z
C Y
A X
B Y
C Z
C Y
C Z
C Y
C X
B Z
B Y
B Y
A Z
B Z
B Y
C Z
B Y
B X
C Z
B Y
C Z
C Y
C Y
A X
B Y
B Z
A X
C X
A Y
B X
B Y
A Y
A X
A Z
A X
C Y
B Z
A Z
C Z
C X
A X
C X
A X
B Z
C Y
B Z
C Z
C Z
A X
A Z
C X
B Y
C Z
A X
C Y
A Y
B Y
C Y
B X
A X
B X
C Y
A Z
B X
B Y
C X
B Y
A Y
B Y
A Z
C Z
C Z
C Z
C Y
B Y
B Y
C Z
C Y
A Y
A Y
C Z
A Z
C Y
C Z
C Y
C Z
A X
C X
C Z
B Y
C Z
C Y
B Y
B Y
A Z
A Z
C Y
C Z
C Z
C Z
C X
B Z
C Y
A Z
C Y
B X
B Y
C Y
B Y
C Z
C Z
B Y
C Y
A Z
B Y
B Y
B Y
B Y
C Y
B Y
A X
C Y
A Y
C Y
C Z
A Y
A Z
C Y
C Y
C Y
C Z
B Y
A Y
B Y
C X
B Y
B Y
B Y
C Y
B X
C Z
A X
B Y
C Z
C Z
B Z
B Y
C Y
B Y
B Y
C X
B X
A Y
A Z
C Z
A Z
C Z
C Z
C Z
A Z
C Y
C X
A X
C X
A Z
C Y
C Z
C Z
B Y
C Y
A X
C X
C Y
C Z
A X
A Z
C X
A X
B Y
A X
C X
A X
C Y
B Y
C Z
B X
A Z
C Z
B Z
C Z
C Y
B Y
C Z
C Y
A Y
B X
B X
A Z
B Y
A X
C Y
C Y
B Y
A X
B Y
B Y
C Z
A Y
C Z
C Z
B Y
C Z
C Z
C Y
C Y
C X
C Y
A Z
C Y
A Z
C Z
B X
A X
C Z
C Z
B Y
C Y
C Y
A X
C Y
A X
C Y
C Z
B Y
B Z
A X
B Y
A Y
B X
C X
A Y
A X
C Y
C Z
A X
C Y
A Y
C Y
B Y
C X
C Z
C Y
A X
B Y
B Y
A X
C Y
B Y
C Y
A Y
B Y
B Z
B X
B Y
C Y
B Y
A Y
C Z
C Y
C Z
C Y
B Z
C X
C Z
C Y
C X
A X
C Y
C X
B Y
C Y
C Z
C Y
B Y
B Y
C Y
C Z
B Z
C Y
C Z
B Y
C Z
C X
C Y
C Y
C Z
A X
A Y
C X
C Y
C X
C Y
A X
C Y
A X
B Y
B Y
A X
B X
A Y
B Y
C Z
C Y
C Y
B Y
A Z
C Z
A Z
B Y
B Y
C Y
C Z
B Y
C Z
B Z
C Z
C Y
A X
C Z
B Y
A Z
C Y
C Z
A Y
C Y
B Y
C Z
B Z
B Y
B Z
B Y
C Y
C Y
C Z
B Y
A X
B Y
B Y
C Y
B Z
B Y
C Y
C X
C Z
C Z
B Y
C Y
C Z
B Z
C Z
C Z
B Y
C X
C X
B Y
C Z
B Y
B Y
C X
A Z
B Z
B X
B X
C Z
C Z
B Z
A Y
C Y
B Y
C X
C Z
A Z
B Z
C Y
B Y
B Z
C Z
A X
C Z
B Y
A Y
B Y
B Z
A X
C Y
A Z
C Z
C Y
B Y
A Y
B Y
B Y
C Y
A X
B Y
C Z
C Z
C Y
B Z
B Y
B Z
B X
C Y
A X
B Y
C Z
C Y
C Z
C Z
C X
C Y
C Z
B Y
A X
C Y
C Y
C Y
A Y
A Y
A Y
C X
B Y
B Y
A Y
C Z
C Z
B Z
C Y
B Y
A X
C X
B Y
C Y
B Y
B Z
C Z
A Y
B Z
C X
B Y
C Z
C Z
C Y
B Y
B Z
B Y
B X
B X
C Z
A Y
B Y
C Z
C Y
A Z
A Z
C Z
C Y
B Y
A Y
B Y
B Y
A X
C Y
A X
C Z
B Y
B Y
B Y
A Y
C Y
C Z
B Y
C Y
C Y
B Y
A X
C Y
C Y
B Y
B Y
C Z
A X
C Y
C Y
C Y
B Y
C Y
A Z
B Y
C Y
A Z
A Z
C Z
C Z
B Y
A Y
B Z
C Z
B Y
B Z
A Y
C X
A X
C Y
B X
B Y
A Y
B Y
C Z
A Z
B Y
C Z
B Y
C Z
C Z
A X
C Y
A Z
B Y
B Y
B Y
B Z
A Y
C Z
A Y
B Y
B Y
B Y
B X
A Y
C Y
A X
C Y
A X
A X
B Y
B Z
B Y
B Y
C Y
C Y
B Y
C Z
A X
A Z
A Y
C Y
C X
B Z
A X
A Y
C Y
C Z
B Y
C X
B Y
B X
B Y
A Y
A Z
B Z
A X
A Z
B Y
B Z
C Y
C Y
C Y
A Y
B Y
B Y
B Y
A Z
B Y
C Y
B Y
A Y
A X
B Y
C Y
A Y
C Y
C Y
A Z
C X
C Y
C Y
B X
A Y
B Z
A Z
B Y
C Z
C Z
C Z
B Y
A Y
B Z
C Y
A X
B Y
A Y
B Y
B Y
C Y
B X
C X
C Y
C Z
B Y
C Z
A Y
A X
C Y
B Y
C X
A X
C Z
C Y
C Z
C Y
A X
C Y
C X
C Z
B Y
B Y
A Z
A Y
B Z
C Z
C Z
C Z
C Y
A Z
B Y
A X
C Y
A X
B Z
B Y
B Y
B Y
A X
B Y
C X
A X
A X
B X
B Y
C Y
A X
B Y
B X
B Z
B Y
B Y
C Y
B Y
C Z
A Z
C Y
C Y
A X
A X
C Y
B Y
C Z
C Y
B Y
B Z
B Z
A X
C Y
C Y
B Y
A Y
C Z
A Z
C X
B Y
B Y
C Z
A Z
B Y
B Y
C Y
C Z
A X
B Y
B Y
C Y
B Y
C Y
C Y
C Y
A X
C Y
B Y
B Y
A X
A Y
B Y
B Y
C Z
C X
C Z
A X
A X
C Y
C X
B Z
B X
C Z
C Z
B Y
B Y
C Y
C Y
C Z
C Y
A X
B Z
B Y
C Z
C X
C Y
B Y
B Y
B Y
C Z
B Y
A X
C Z
B X
B Z
A Y
A Z
B Y
B X
A X
B Y
B Y
A Z
A X
C X
A Z
A X
A Z
C Z
A X
C Y
B Y
B Y
C Y
B Z
B X
C Y
B Y
A Z
C X
C Y
B Y
A Z
C Y
C Y
A X
C X
C Y
C X
A Y
B Z
A Z
C Z
B Y
C Z
C Y
A Z
B Y
A Z
C Y
A X
A Z
B Y
B Y
B Z
B Y
A X
C Y
B Y
C Y
B Y
A X
C Z
B Y
C Y
A X
C Y
B Y
B X
C X
A X
B Y
C Y
B Y
B Y
C Z
B X
C Z
A X
C Z
A X
A Z
C Y
C Y
B Y
B Y
A Z
A X
C Z
B Y
B Y
A Y
C Y
C Z
C X
C Y
A Y
B Z
C Z
A Z
C Y
C X
C X
B Z
A X
C Y
A X
C Z
B Y
C Y
A Z
A X
B Y
B Y
B X
C Z
C X
B Y
C X
A Y
A Y
C Z
C Z
C Z
B Z
C Z
A Y
C Z
A X
A Y
C Z
B Y
C Y
C X
C Z
B Y
A X
B X
B Y
B X
C Y
A X
B Y
C Y
C Y
B Y
B Y
A X
A Y
C X
C Y
B Y
C Z
A X
C Z
B Z
C Z
C Y
C X
C Y
A X
B Y
C X
A X
C Y
C X
C Y
A Z
C Y
B Y
C X
C Z
C Y
B Y
C Y
A Y
A X
C Y
C Y
C Z
A X
B Y
C Y
B Z
C Y
B Y
A X
B Z
C Z
C Z
C Y
C X
B Z
C Z
C Z
B Y
B X
A Z
A X
C Y
A X
B X
A X
A Z
C Z
C Y
C Z
C Z
B Z
C Z
B Y
C Y
B Y
A Z
C Y
B Y
C Z
A Y
B Z
B X
B Y
B Y
C Y
C Y
B Y
B X
B X
A X
C Z
B Y
C Z
C Y
B Y
A X
C X
C Z
B Z
C Y
C Y
B Y
B Y
C Z
A X
B Y
C Z
C Y
C Z
C Y
B Y
C Y
C X
C X
A X
A X
B Z
B Y
B Y
C Z
A X
B Y
A Y
B Y
A Z
C Z
C Y
A Y
A X
B Y
B Y
C X
A X
C Z
C Y
A Z
B Y
C Z
C X
B Z
C Z
B Y
A Y
B Y
B Y
A X
B Z
B Y
C Z
C Y
B X
A Z
C Z
B Y
C Y
A Z
B Y
A Y
B Y
B Z
A Z
B Y
C Y
C Y
C Y
A X
B Y
C X
C Y
A X
B Z
A Y
C Z
B Y
B Z
B Y
C Y
B Z
B Y
B Z
C Y
B Z
B Y
B Z
C Y
A Y
C Z
C Y
C Y
B Y
A Y
A Z
A X
C Z
B Z
C X
B Y
B Y
C Z
A X
C Z
C Y
B Z
A Z
B Y
C Y
C Y
A X
B Y
C X
A X
B Y
A Y
A X
B Z
C Z
C X
A Z
C Y
A Y
C Y
A X
C Z
B Y
A X
B Y
B Y
A Z
C Z
A X
A X
A X
A X
C Y
B Y
C Y
C X
C Y
A Y
C Z
A X
B X
B Y
C Z
B Y
B Z
A X
C Y
B Y
C Y
B Y
C Z
C Y
C X
A Y
A Y
C Y
A Z
B Y
A Y
B Z
B Y
C Y
A Y
B Y
C X
C Y
C Z
C Z
A X
C Z
B Y
B X
B X
A Y
C Z
A Y
C X
A X
C Z
C Y
C X
C X
C Z
A Z
C Z
B Y
B Z
C Z
C Y
A X
A X
C X
B X
C Y
B Y
B Z
C Z
C Z
B Y
B Y
B Z
A X
B Y
A X
B Y
C Y
B X
C Z
C Z
C Y
A Y
B Y
B Y
B Y
C Z
A Z
A Z
A Y
A Y
B Y
C Z
C Z
A Z
A Z
B Y
A Z
A Y
C Y
B Y
B Y
A X
C Z
C Z
B Y
A X
B Y
A Z
B Y
A X
A X
C Z
C Y
B Y
C Y
B X
B Y
A Y
B Y
B X
C Y
B Y
C Y
C Y
B Y
C X
C Y
A X
B Y
C Y
A Y
B Y
A X
C Z
B Y
C Y
B Y
C X
A Y
C Z
B Y
B Y
B Z
B Z
C Z
C Z
C Z
A Y
B Y
A Z
A X
C Z
B Y
A X
B Y
B X
C Z
B Y
C Y
B X
C X
C X
A Z
C X
A Z
C Z
B X
C Y
A X
C Y
B Y
C Y
B Y
C Z
C Z
C Z
C Z
C Z
C Y
A X
B X
B X
B Z
A X
C Y
A X
C Z
C Z
C Y
A X
C Z
A X
A X
A X
C Z
C X
B Y
C Y
B Z
C Y
B Y
A X
A Y
C Z
B Y
B Y
B X
C Z
C Z
A Y
A X
C Y
A X
C Y
C Y
B Z
B Y
B X
C Y
A Y
A X
B Y
B Y
C Y
A Y
B Y
B Y
B X
A Z
C Y
C Y
B Z
C Y
B Z
B Y
C Y
A Y
B Y
A X
C Y
C Z
C Y
A X
C Z
B Y
B Z
A X
C Y
C Y
C Y
C Z
B Y
B Y
A X
C Y
B Y
A Y
C Z
C X
C Y
A X
C Y
C Z
A X
C Y
A Z
B Y
B X
B Y
B Z
B Y
B Y
B Y
C X
A X
B Y
A X
A Y
C Z
C Y
C Z
C Y
B Y
B X
B X
C X
B Y
A X
A X
C Y
C X
B X
C X
C Z
C Z
B Z
C Z
C X
B Y
B X
B Y
C Y
A Y
A Y
C Z
B X
B Y
B Z
A X
C Y
A Y
C Y
C Z
C X
B Z
A X
A X
A Y
A Y
A Z
B Y
C X
C Z
C Z
B Y
B Z
C Y
B Y
C Y
B Y
B Y
B Y
C Y
C Y
C Y
A X
B X
C Z
C Y
A X
B Y
A Y
C Y
A X
B Y
B X
B Y
C X
C Z
A X
C Y
B Y
A Y
C Z
C Y
B Z
C Z
B Y
A X
B Y
C Z
A Y
B Y
A Y
B Y
B X
C X
C Y
A Y
B Z
A X
A Z
B Y
A X
C Y
B Y
A Y
A X
B X
B X
B Y
A Z
C Z
C Y
C X
C X
C Z
C Y
B X
A Y
C Y
B Y
B Y
A X
B Y
B Y
A Z
A Y
B X
A Z
B Z
B Y
A X
A X
C Y
A Y
C Y
C Z
A Y
C Y
C X
C X
C Y
B X
A X
A Y
B X
A Y
C X
B Y
B Z
B Y
A Y
C Y
B X
A Z
A X
B Y
C X
C Y
C Y
B Y
A Y
C Y
C Y
B Y
C Y
B Z
B Y
B Y
A Y
B Y
C Z
B Y
B Y
B X
B Y
B Z
C X
B X
C Z
B Z
C Z
A X
C X
A X
B Y
B X
B Y
C Y
C X
B Y
A X
B Y
B Y
C Y
A X
C Y
C X
B Z
A Z
C Y
B Z
A X
B Y
A X
B Y
A Y
C Y
C Y
C Y
C X
C Z
A X
B Y
C Y
B Z
C Z
C Z
C Z
C Y
C Y
C Y
A X
B Y
B Y
B Y
B Y
B Y
C Z
A Z
C X
A X
C Z
A X
C Y
C Y
C Z
C Y
C Y
C Y
C Y
C Z
A Y
B Y
B Z
C Z
A X
A Y
C Z
C Z
A Y
C X
A Z
B Y
B Y
A Y
C Z
A X
C Y
A X
C X
B Y
B Y
B Y
A Z
B Z
C Y
A X
B Y
B Z
B Y
B Y
C Z
A Y
B Y
C Z
A X
C Y
C X
C Y
B X
C Z
C Z
B Y
A Z
A Y
C Z
B X
B Y
B Y
A Y
C X
C Y
B Y
A Y
A X
B Y
C X
B Y
B X
C Z
C Z
C Y
C Z
C Z
C X
A X
A Y
C Y
B Y
B Y
C Y
A Y
C Z
A Y
A X
B X
C X
C Z
C Z
B X
C Y
A Z
C Z
C Z
C Y
A X
C Z
B Y
A Y
A X
C Y
C Y
C Y
B Y
A Z
C Y
C Y
C Y
B Y
B X
C Y
A Z
B Y
B Y
C Y
C Z
C Y
A X
A X
C Y
C Y
B Z
A Y
B Y
A X
A X
C Y
B Y
B Z
C Z
C Y
C Y
C X
B Z
A Z
B X
B Y
B Y
A Y
A X
C Z
C X
C Z
A Z
B Y
A Z
A X
A X
B Z
B Y
A Z
A X
A Z
B Y
B Y
C Z
C Y
A Y
A Y
B Y
A Y
C Z
C Y
C Z
C Y
C Z
C Z
C X
C X
C X
B Y
C X
C Z
B Y
B Y
C Y
B Y
C Y
B X
B Y
B Z
C Z
B Y
C Y
B Y
C Y
A X
B Y
B Y
C Z
B Y"""
    val score = score(input)
    println(score)
}




