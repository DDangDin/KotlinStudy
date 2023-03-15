package com.example.kotlinstudy.playground

// Scope function (범위 지정 함수)
// 특정 객체에 대한 작업을 블록 안에 넣어 실행할 수 있도록 하는 함수
// 1. apply
// 2. run
// 3. with
// 4. let
// 5. also

data class Person(
    var name: String = "",
    var age: Int = 0,
    var temperature: Float = 36.5f
) {
    fun isSick(): Boolean = temperature > 37.5f
}

// apply
// 수신객체 내부 프로퍼티를 변경한다음 수신객체 자체를 반환하기 위해 사용되는 함수
// *객체 생성 시에 다양한 프로퍼티를 설정해야 하는 경우 자주 사용된다*
val person = Person().apply {
    name = "TestName"
    age = 24
    temperature = 36.2f
}

// run
// apply와 똑같이 작동, 하지만 수신객체 return X
// 대신 run 블록의 마지막 라인을 return 함
// 수신객체에 대해 특정한 동작을 수행한 후 결과값을 리턴 받아야 할 경우 사용
val person2 = Person(name = "kim", age = 24, temperature = 36.5f)
val isPersonSick = person2.run {
    temperature = 37.2f
    isSick()
}
// 수신객체 없이도 사용가능, 그러면 수신객체 명시해줘야함
val isPersonSick2 = run {
    person.temperature = 37.2f
}

// with
// 수신객체에 대한 작업 후 마지막 라인을 return
// run과 완전 똑같지만 수신객체를 파라미터로 받아 사용
val person3 = Person(name = "kim", age = 24, temperature = 36.5f)
val isPersonSick3 = with(person3) {
    temperature = 38.0f
    isSick()
}

// let
// 수신객체를 이용해 작업을 한 후 마지막 줄을 return 할 때 사용
// run이나 with과는 수신객체를 접근할 때 it을 사용해야 한다는 점만 다르고 나머지 동작은 같다
// *** 다음과 같은 경우 사용 ***
// 1. null check 후 코드를 실행해야 하는 경우
// 2. nullable한 수신객체를 다른 타입의 변수로 변환해야 하는경우
// nullable한 값을 처리해야 할 때는 let을 사용!!!
// ***
// ! 물론 let은 nullable하지 않은 대상에 대해서도 사용할 수 있지만,
// 실무에서는 nullable한 값이 아닐 경우에는 run을 사용하는 것이 일반적이다 !
// ***

// also
// apply와 마찬가지로 수신객체 자신을 반환
var number = 3
fun getAndIncreaseNumber() = number.also {
    number++
}

fun main() {
    val person: Person? = null
    val isReserved = person?.let { it: Person ->

    }

    println("first number ${getAndIncreaseNumber()}")
    println("second number ${getAndIncreaseNumber()}")
}


