package com.fred.adventofcode

import com.fred.adventofcode.utils.read

fun main() {
    val input = "/input/6.in".read()

    println(input.findMarker(4))  // 6-1
    println(input.findMarker(14))  // 6-2
}

private fun String.findMarker(length: Int) = this
    .windowed(length)
    .indexOfFirst { window -> window.toSet().size == length } + length
