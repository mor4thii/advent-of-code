package com.fred.adventofcode

import com.fred.adventofcode.utils.read
import com.fred.adventofcode.utils.toPair

data class Tree(val nodeName: String, val parent: Tree?) {
    var size: Int = 0
    val children: MutableList<Tree> = mutableListOf()

    fun sizes(sizes: MutableList<Int>): Int =
        sizes.add(this.size + this.children.sumOf { it.sizes(sizes) }).let { sizes.last() }
}

fun main() {
    val input = "/input/7.in".read()

    val root = input.parseTree()

    sumOfDirectoriesSub100k(root)
    freeDiskSpace(root)
}

private fun String.parseTree(): Tree {
    val root = Tree("/", null)
    var current: Tree = root
    lines()
        .asSequence()
        .map { it.replace("$ ", "") }
        .filterNot { it == "ls" }
        .map { it.split(" ").toPair() }
        .forEach { argLine ->
            when (argLine.first) {
                "cd" -> current = when (argLine.second) {
                    "/" -> root
                    ".." -> current.parent!!
                    else -> current.children.find { it.nodeName == argLine.second }!!
                }

                "dir" -> current.children.add(Tree(argLine.second, current))
                else -> current.size += argLine.first.toInt()
            }
        }

    return root
}

private fun sumOfDirectoriesSub100k(root: Tree) {
    val sizes: MutableList<Int> = sizes(root)

    sizes.filter { it <= 100000 }.sum().let(::println)
}

private fun freeDiskSpace(root: Tree) {
    val sizes: MutableList<Int> = sizes(root)

    sizes.filter { it >= 30000000 - 70000000 + sizes.max() }.min().let(::println)
}

private fun sizes(root: Tree): MutableList<Int> {
    val sizes: MutableList<Int> = mutableListOf()
    root.sizes(sizes)
    return sizes
}
