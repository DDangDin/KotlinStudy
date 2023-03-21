package com.example.kotlinstudy.study

import java.io.BufferedReader
import java.io.StringReader
import java.util.TreeMap

/** 2.3 선택 표현과 처리 (enum, when) **/

/** 2.3.1 enum 클래스 정의 **/
enum class Color {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}

// 프로퍼티와 메서드가 있는 enum 클래스 선언
enum class ColorRGB(
    val r: Int, val g: Int, val b: Int  // 상수의 프로퍼티 정의
) {
    RED(255,0,0), ORANGE(255,165,0),
    YELLOW(255,255,0), GREEN(0,255,0), BLUE(0,0,255),
    INDIGO(75,0,130), VIOLET(238,130,238);

    fun rgb() = (r * 256 +g) * 256 +b
}
// -> enum class도 일반 class처럼 생성자와 프로퍼티 선언 가능

/** 2.3.2 when으로 enum 클래스 다루기 **/
// when: if와 마찬가지로 값을 만들어내는 식, 자바의 switch에 해당
fun getMnemonic(color: Color) =
    when (color) {
        Color.RED -> "Richard"
        Color.ORANGE -> "Of"
        Color.YELLOW -> "York"
        Color.GREEN -> "Gave"
        Color.BLUE -> "Battle"
        Color.INDIGO -> "In"
        Color.VIOLET -> "Vain"
    }

// 한 when 분기 안에 여러 값 사용하기
fun getWarmth(color: Color) = when(color) {
    Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
    Color.GREEN -> "neutral"
    Color.BLUE, Color.INDIGO -> "cold"
    else -> "error"
}

// !! when은 자바 switch와 달리 분기 조건에 임의의 객체를 허용
// 또한 when은 인자 없이 when {} -> 이렇게 바로 사용 가능하다.
// 하지만 when에 아무 인자도 없으려면 각 분기의 조건이 Boolean 결과를 계산하는 식이어야 한다.
// ex) when {
//  a == 10 -> "yes"
// }


/** 2.3.5 스마트 캐스트: 타입 검사와 타입 캐스트를 조합 **/
// SmartCast.kt 파일 확인


/** 2.4 이터레이션: while과 for 루프 **/
// while: 자바와 비슷
// for: for <item> in <elements> 형태

/** 2.4.2 수에 대한 이터레이션: 범위와 수열 **/
val oneToTen = 1..10    // 범위, 수열, IntRange

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 3 == 0 -> "Fizz "
    i % 5 == 0 -> "Buzz "
    else -> "$i "
}

fun useFizzBuzz() {
    for (i in 1..100) {
        print(fizzBuzz(i))
    }
    println()
    for (i in 100 downTo 1 step 2) {
        // !! downTo의 기본 증가값은 -1,
        // !! step 2를 붙이면 증가값이 절대값 2로 바뀌며 방향은 그대로이므로 -2
        print(fizzBuzz(i))
    }
    // !! 1..100 -> 양 끝 값 포함
    // !! 1 untile 100 -> 양 끝 값 제외
}

/** 2.4.3 맵에 대한 이터레이션 **/
fun mapIteration() {
    val binaryReps = TreeMap<Char, String>()

    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.code) // -> c.toInt() == c.code
        binaryReps[c] = binary  // = 자바의 binaryReps.put(c, binary)
    }

    for ((letter, binary) in binaryReps) {  // 구조분해
        println("$letter = $binary")
    }
}

fun loopWithIndex() {
    val list = arrayListOf("10", "11", "1001")
    for ((index, element) in list.withIndex()) {
        println("$index: $element")
    }
}

/** 2.4.4 in으로 컬렉션이나 범위의 원소 검사 **/
fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'
fun recognize(c: Char) = when(c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
    else -> "I don't know..."
}

/** 2.5 코틀린의 예외 처리 **/
fun readNumber(reader: BufferedReader): Int? {
    try {
        val line = reader.readLine()
        return Integer.parseInt(line)
    } catch (e: java.lang.NumberFormatException) {
        return null
    } finally {
        reader.close()
    }
}
// 코틀린의 try 키워드도 if나 when처럼 식이다.





fun main() {
    println(ColorRGB.BLUE.rgb())
    println(getMnemonic(Color.BLUE))
    println(getWarmth(Color.ORANGE))

    useFizzBuzz()
    println()
    mapIteration()
    println()
    loopWithIndex()

    println(isLetter('q'))
    println(isNotDigit('x'))
    println(recognize('8'))
    println("Kotlin" in "Java".."Scala")
    println("Kotlin" in setOf("Java", "Scala"))

    val reader = BufferedReader(StringReader("239"))
    println(readNumber(reader))
}