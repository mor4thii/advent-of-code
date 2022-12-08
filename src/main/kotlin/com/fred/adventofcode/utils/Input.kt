package com.fred.adventofcode.utils

fun String.read() = object {}.javaClass.getResource(this)!!.readText()