package com.example.kotlinstudy.playground

//fun main() {
//    val diceRange = 1..6
//    val randomNumber = diceRange.random()
//    println("Output: ${randomNumber}")
//}

////#1
//fun main() {
//    val myFirstDice = Dice()
//    //println(myFirstDice.sides)
//    val diceRoll = myFirstDice.roll()
//    println("Your ${myFirstDice.sides} sided dice rolled ${diceRoll}")
//
//    myFirstDice.sides = 20
//    println("Your ${myFirstDice.sides} sided dice rolled ${myFirstDice.roll()}")
//}
//
//class Dice {
//    var sides = 6
//
//    fun roll() : Int {
//        val randomNumber = (1..sides).random()
//        return randomNumber
//    }
//}

////#2
//fun main() {
//    val myFirstDice = Dice(6)
//    val diceRoll = myFirstDice.roll()
//    println("Your ${myFirstDice.numSides} sided dice rolled ${diceRoll}")
//
//    val mySecondDice = Dice(20)
//    println("Your ${mySecondDice.numSides} sided dice rolled ${mySecondDice.roll()}")
//}
//
//class Dice (val numSides: Int) {
//    fun roll(): Int {
//        val randomNumber = (1..numSides).random()
//        return randomNumber
//    }
//}

////#3
fun main() {
    val myFirstDice = Dice(6)
    val diceRoll = myFirstDice.roll()
    println("Your ${myFirstDice.numSides} sided dice rolled ${diceRoll}")

    val mySecondDice = Dice(20)
    println("Your ${mySecondDice.numSides} sided dice rolled ${mySecondDice.roll()}")
}

class Dice (val numSides : Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}