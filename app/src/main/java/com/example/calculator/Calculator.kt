package com.example.calculator

class Calculator {

    private val listOfMarks = listOf("*", "/", "+", "-")

    fun getResult(equation: String): String {
        var equationList = equation.parseToList()
        var result = ""

        try {
            while (equationList.contains("(")) {
                equationList = openBrackets(equationList)
            }
            equationList = calculate(equationList)

            result = "= ${equationList[0]}"
            if (result.endsWith(".0")) {
                result = result.dropLast(2)
            }
        } catch (e: Exception) {
            return "Incorrect format. Please try again."
        }

        return if (result == "= Infinity" || result == "= NaN") {
            "Can't divide by zero. Please try again."
        } else {
            result
        }
    }


    private fun calculate(equation: List<String>): List<String> {
        val list = equation.toMutableList()
        var result = 0.0F

        if (!equation.isCorrectFormat()) {
            return emptyList()
        }

        listOfMarks.forEach { mark ->
            while (list.contains(mark)) {
                for (i in 0 until list.size) {
                    if (list[i] == mark) {
                        val x = list[i - 1]
                        val y = list[i + 1]
                        result = when (mark) {
                            "*" -> x.toFloat() * y.toFloat()
                            "/" -> x.toFloat() / y.toFloat()
                            "+" -> x.toFloat() + y.toFloat()
                            "-" -> x.toFloat() - y.toFloat()
                            else -> 0.0F
                        }
                        list[i + 1] = result.toString()
                        list.removeAt(i - 1)
                        list.removeAt(i - 1)
                        break
                    }
                }
            }
        }
        return list
    }

    private fun openBrackets(equation: List<String>): List<String> {
        val list = equation.toMutableList()
        var insideBrackets = listOf<String>()
        var startIndex = 0
        var endIndex = 0

        startIndex = equation.indexOfOpenBracket() + 1
        endIndex = equation.indexOfFirst { it == ")" } - 1

        insideBrackets = equation.slice(startIndex..endIndex)
        insideBrackets = calculate(insideBrackets)

        for (i in startIndex..endIndex + 2) {
            list.removeAt(startIndex - 1)
        }

        list.add(startIndex - 1, insideBrackets[0])

        return list
    }


    private fun String.parseToList(): List<String> {
        val list = mutableListOf<String>()
        var currentString = ""
        var index = 0

        this.forEach { char ->
            index++
            if (char.isNumber) {
                currentString += char
                if (index == this.length) {
                    list.add(currentString)
                }
                return@forEach
            }

            if (currentString.isBlank()) {
                list.add(char.toString())
            } else {
                list.add(currentString)
                list.add(char.toString())
                currentString = ""
            }
        }
        return list.removeSpaces
    }


    private val List<String>.removeSpaces
        get() = this.filter { it != " " }


    private fun List<String>.indexOfOpenBracket(): Int {
        var startIndex = 0
        for (i in 0 until this.size) {
            if (this[i] == "(" && i + 1 < this.size) {
                startIndex = i
            }
            if (this[i] == ")") {
                break
            }
        }
        return startIndex
    }

    private fun List<String>.isCorrectFormat(): Boolean {
        var result = false
        if (this.all { it[0].isNumber || it[0].isMark }) {
            for (i in 0 until this.size step 2) {
                if (this[i].all { it.isNumber || it == '-' } && this.size % 2 != 0) {
                    result = true
                } else {
                    return false
                }
            }
        }
        return result
    }
}