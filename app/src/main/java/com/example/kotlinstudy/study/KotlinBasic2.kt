package com.example.kotlinstudy.study

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






fun main() {
    println(ColorRGB.BLUE.rgb())
    println(getMnemonic(Color.BLUE))
    println(getWarmth(Color.ORANGE))
}