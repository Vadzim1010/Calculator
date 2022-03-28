package com.example.calculator

fun main() {
    val calculator = Calculator()


    val test1 = calculator.getResult("5 s 5")
    val test2 = calculator.getResult("5 * 5 / 000000")
    val test3 = calculator.getResult("10 * (10 + 0)")
    val test4 = calculator.getResult("5 / 0")
    val test6 = calculator.getResult("10 * (10 + 10)")
    val test7 = calculator.getResult("10  * (1  + 1) * 0.5 * 0 + (100 * 200 + 3 / 271)")
    val test8 =
        calculator.getResult("(200 * ((10 - 3 * 10 * (37 / 2 + 1 * (1 + 1) / 20 - (7 -1))) + (368+2))) - 1000")
    val test9 = calculator.getResult(readLine().toString())


    println(test1)
    println(test2)
    println(test3)
    println(test4)
    println(test6)
    println(test7)
    println(test8)
    println(test9)

}
