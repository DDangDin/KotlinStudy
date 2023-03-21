package com.example.kotlinstudy.study

/** 2.3.5 스마트 캐스트 **/
// Expr 인터페이스: 아무 메서드도 선언하지 않으며,
// 단지 여러 타입의 식 객체를 아우르는 공통 타입 역할만 수행한다.

interface Expr
class Num(val value: Int): Expr                     // value프로퍼티만 존재하는 단순한 클래스, Expr 구현
class Sum(val left: Expr, val right: Expr): Expr    // 인자가 Expr 타입이므로 Num이나 다른 Sum이 인자로 올 수 있음

// 자바 스타일
fun eval(e: Expr): Int {
    if (e is Num) {         // 코틀린 타입 검사 키워드 -> is (자바의 instanceof와 비슷)
        val n = e as Num    // 원하는 타입으로 명시적으로 타입 캐스팅, 반드시 val 사용
        return n.value
    }

    if (e is Sum) {
        return eval(e.right) + eval(e.left)
    }

    throw IllegalArgumentException("Unknown expression")
}

// - 코틀린에서는 프로그래머 대신 컴파일러가 캐스팅을 해줌
// !! 변수를 is로 타입검사를 하고 나면 해당변수를 검사한 타입으로 사용 가능하게 해줌 -> 스마트 캐스팅

// 코틀린 스타일
fun evalKot(e: Expr): Int =
    if (e is Num) {
        e.value
    } else if (e is Sum) {
        eval(e.right) + eval(e.left)
    } else {
        throw IllegalArgumentException("Unknown expression")
    }
// 코틀린 스타일 (when사용)
fun evalKotWhen(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
        else -> throw IllegalArgumentException("Unknown expression")
    }


// 요약 -> 코틀린에서는 is로 타입검사를 하면 캐스팅 할필요가 없다. (스마트 캐스팅)


fun main() {
    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
    println(evalKot(Sum(Sum(Num(1), Num(2)), Num(4))))
    println(evalKotWhen(Sum(Sum(Num(1), Num(2)), Num(4))))
}