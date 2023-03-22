package com.example.kotlinstudy.study

/** 3. 함수 정의와 호출 **/

/** 3.1 코틀린에서 컬렉션 만들기 **/
val set = hashSetOf(1, 7, 53)
val list = arrayListOf(1, 7, 53)
val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
// !! 코틀린 컬렉션 = 자바 컬렉션 (똑같은 클래스)
// 하지만 코틀린에서는 더 많은 기능 사용 가능 (ex. collectionTest함수 내 내용)
fun collectionTest() {
    val strings = listOf("first", "second", "third")
    println(strings.last())
    val numbers = setOf(1, 14, 2)
    println(numbers.max())
}

// 디폴트 파라미터 값
// 자바에서는 디폴트 파라미터 값이라는 개념이 없어서
// 사용을 원할 시 @JvmOverloads 애노테이션을 함수에 추가
// -> 코틀린 컴파일러가 자동으로 처리해줌

// 최상위 프로퍼티
// const val = public static final (자바)

/** 3.3 메서드를 다른 클래스에 추가: 확장 함수와 확장 프로퍼티 **/
// !!
// 확장 함수: 어떤 클래스의 멤버 메서드인 것처럼 호출할 수 있지만
// 그 클래스의 밖에 선언된 함수
fun String.lastChar(): Char = this[this.length-1]
// 확장 함수 -> fun (함수가 확장할 클래스의 이름).(추가하려는 함수이름)
// String(클래스 이름) -> 수신 객체 타입(receiver type)
// this(확장 함수가 호출되는 대상이 되는 값) -> 수신 객체(receiver object)

// Ex) println("Kotlin".lastChar())
// 수신 객체 타입 -> String
// 수신 객체 -> "Kotlin"

// 어떻게 보면, String 클래스에 새로운 메서드를 추가하는 것과 같음
// 또한, 밑에 처럼 작성 가능
fun String.lastChar2(): Char = this.get(length-1)
fun String.lastChar3(): Char = get(length-1)
// -> this 생략 가능

// 확장 함수는 캡슐화를 깨지 않음
// 그 이유는, 확장 함수 안에서는 클래스 내부에서만 사용할 수 있는
// private, protected 멤버를 사용할 수 없다.

// 호출 하는 입장에서는 클래스 멤버 메서드인지 확장 함수인지 구분 X
// 구분 여부가 중요한 경우도 거의 없음

/** 3.3.1 임포트와 확장 함수 **/
// 확장 함수를 import하여 사용할 때
// Ex) import strings.lastChar as last
// -> as 키워드를 붙여 임포트를 하게 되면
// "Kotlin".last() 처럼 쓸 수 있게 된다. -> 이름 충돌 방지

// Tip. 코틀린 문법상 확장 함수는 반드시 짧은 이름을 써야 함









fun main() {
    // javaClass -> 자바에서의 getClass()와 동일
    println(set.javaClass)
    println(list.javaClass)
    println(map.javaClass)

    collectionTest()

    val list = listOf(1,2,3)
    println(list)
    println("Kotlin".lastChar())    // 다른 일반 클래스 멤버를 호출하는 구문과 같음
}