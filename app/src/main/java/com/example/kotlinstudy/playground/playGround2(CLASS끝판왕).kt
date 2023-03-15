package com.example.kotlinstudy.playground

import kotlin.math.PI
import kotlin.math.sqrt

fun main() {
    val squareCabin = SquareCabin(6, 50.0)
    val roundHut = RoundHut(3, 10.0)
    val roundTower = RoundTower(4, 15.5)

//    println("\nSquare Cabin\n============")
//    println("Capacity: ${squareCabin.capacity}")
//    println("Material: ${squareCabin.buildingMaterial}")
//    println("Has Room? ${squareCabin.hasRoom()}")

    //위코드 단순화
    with(squareCabin) {
        println("\nSquare Cabin\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Floor area: ${floorArea()}")
    }

    with(roundHut){
        println("\nRound Hut\n============")
        println("Material: ${buildingMaterial}")
        println("Capacity: ${capacity}")
        println("Floor area: ${floorArea()}")
        println ("Floor area(short): % .2f".format (floorArea ()))
//        println("Has room? ${hasRoom()}")
//        getRoom()
//        println("Has room? ${hasRoom()}")
//        getRoom()
        //hasRoom()응용 때문에
        println("Has room?")
        hasRoom()
        getRoom()
        println("Has room?")
        hasRoom()
        getRoom()
        println("Carpet size: ${calculateMaxCarpetSize()}")

    }

    with(roundTower) {
        println("\nRound Tower\n============")
        println("Material: ${buildingMaterial}")
        println("Capacity: ${capacity}")
        println("Floor area: ${floorArea()}")
        println("Carpet size: ${calculateMaxCarpetSize()}")
    }
}
abstract class Dwelling (private var residents: Int) {

    abstract val buildingMaterial: String
    abstract val capacity: Int

    abstract fun floorArea(): Double

//    fun hasRoom(): Boolean {
//        return residents < capacity
//    }
    //hasRoom() 응용
    fun hasRoom(){
        var leftSpace = 0
        if (capacity > residents) {
            leftSpace = capacity - residents
            println("${leftSpace} left.")
        }
    }

    fun getRoom() {
        if (capacity > residents) {
            residents++
            println("You got a room!")
        } else {
            println("Sorry, at capacity and no rooms left.")
        }
    }
}

class SquareCabin(residents: Int, val length: Double) : Dwelling(residents) {
    override val buildingMaterial = "Wood"
    override val capacity = 6

    override fun floorArea(): Double {
        return length * length
    }
}

open class RoundHut(
        residents: Int,
        val radius: Double) : Dwelling(residents) {
    override val buildingMaterial = "Straw"
    override val capacity = 4

    override fun floorArea(): Double {
        return PI * radius * radius
        //임포트 안할거면 대신에
        //kotlin.math.PI * radius * radius로 작성가능
    }

    fun calculateMaxCarpetSize(): Double {
        val diameter = 2 * radius
        return sqrt(diameter * diameter / 2)
    }
}

class RoundTower(
        residents: Int,
        radius: Double,
        val floors: Int = 2) : RoundHut(residents, radius) {

    override val buildingMaterial = "Stone"
    override val capacity = 4 * floors

    override fun floorArea(): Double {
        return super.floorArea() * floors
    }
}