package com.example.androiddevelopersstudy2

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

fun main(){
    val lamda = {number: Double ->
        number == 4.3213
    }

    println(invokeLamda(lamda))
    println(invokeLamda{it > 3.22})

    println(calculateGrade(85))
}