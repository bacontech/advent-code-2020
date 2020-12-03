package com.bacontech.day2

import java.io.File


fun main(args: Array<String>) {
    val inputPath = "src/com/bacontech/day2/day2-input.txt"
    val lines  = File(inputPath).useLines { it.toList() }
//    lines.forEach { println(it) }
//    lines.forEach(action = (t) -> println(t))

    Day2KotlinTry("Alex").greet()
}

private fun printLines(lines: List<String>) {
    lines.forEach {
        println(it)
    }
}

class Day2KotlinTry(val name: String) {
    fun greet() {
        println("Hello, $name")
    }
}

