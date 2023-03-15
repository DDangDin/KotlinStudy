package com.example.kotlinstudy.playground

// Lamda
// val lamdaName : Type = {argumentList -> codeBody}
var square1: (Int) -> (Int) = {number -> number * number}
var square2 = {number: Int -> number * number}

val nameAge = {name: String, age: Int ->
    "My name is $name I'm ${age}"
}


// 확장함수
// fun 확장할 클래스.함수명: 리턴타입 {
//   return 리턴값
// }
val pizzaIsGreat: String.() -> String = {
    this + "Pizza is the best!"
}

fun extendString(name : String, age : Int) : String {
    val introduceMyself : String.(Int) -> String = {
        "I am $this and $it years old"
    }
    return name.introduceMyself(age)
}

// 람다의 Return

val calculateGrade: (Int) -> String = {
    when(it) {
        in 0..40 -> "fail"
        in 41..70 -> "pass"
        in 71..100 -> "perfect"
        else -> "Error"
    }
}

// 람다의 여러 표현
fun invokeLamda(lamda: (Double) -> Boolean): Boolean {
    return lamda(5.2343)
}


// Object 컴파일 될 때 한번만 생성. 따라서 불필요한 메모리를 줄일 수 있음
// Singleton Pattern`
object CarFactory {
    val cars = mutableListOf<Car>()
    fun makeCar(horsepower: Int): Car {
        val car = Car(horsepower)
        cars.add(car)
        return car
    }
}

data class Car(val horsepower: Int)





fun main(){
    val lamda = {number: Double ->
        number == 4.3213
    }

    println(invokeLamda(lamda))
    println(invokeLamda{it > 3.22})

    println(calculateGrade(85))

    println("-----------------------------------")
    val car = CarFactory.makeCar(10)
    val car2 = CarFactory.makeCar(200)
    println(CarFactory.cars.size.toString())
    println(car)
    println(car2)
}