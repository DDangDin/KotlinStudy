package com.example.kotlinstudy.study

import java.lang.StringBuilder

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
fun String.lastChar(): Char = this[this.length - 1]
// 확장 함수 -> fun (함수가 확장할 클래스의 이름).(추가하려는 함수이름)
// String(클래스 이름) -> 수신 객체 타입(receiver type)
// this(확장 함수가 호출되는 대상이 되는 값) -> 수신 객체(receiver object)

// Ex) println("Kotlin".lastChar())
// 수신 객체 타입 -> String
// 수신 객체 -> "Kotlin"

// 어떻게 보면, String 클래스에 새로운 메서드를 추가하는 것과 같음
// 또한, 밑에 처럼 작성 가능
fun String.lastChar2(): Char = this.get(length - 1)
fun String.lastChar3(): Char = get(length - 1)
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

/** 확장 함수로 유틸리티 함수 정의 **/
fun <T> Collection<T>.joinToString(     // Collection<T>에 대한 확장 함수 선언
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {    // 'this'는 수신 객체를 가리킴, 여기서는 T 타입의 원소로 이뤄진 컬렉션
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)

    return result.toString()
}

// 위 함수 변형
fun Collection<String>.join(    // 구체적인 타입을 수신 객체 타입으로 지정할 수 있음 (여기선 문자열의 컬렉션에 대해서만 호출 가능)
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
) = joinToString(separator, prefix, postfix)

// !! 확장 함수는 오버라이드할 수 없음
// -> 확장 함수는 클래스 밖에 선언됨

/** 3.3.5 확장 프로퍼티 **/
// 장점: 프로퍼티 문법으로 더 짧게 코드를 작성할 수 있어서 편한 경우가 있음
// - 실제로 확장 프로퍼티는 아무 상태도 가질 수 없음
val String.lastCharExtension: Char         // 앞 절에서 lastChar함수를 프로퍼티를 바꿈
    get() = get(length - 1)
// - 확장 함수의 경우와 마찬가지로 확장 프로퍼티도 일반적인 프로퍼티와 같음,
// 단지 수신 객체 클래스가 추가됐을 뿐
// - 뒷받침하는 필드가 없어서 기본 게터 구현을 제공할 수 없으므로
// 최소한 게터는 꼭 정의를 해야 함

// <변경 가능한 확장 프로퍼티 선언하기>
var StringBuilder.lastCharExtension: Char   // StringBuilder에 같은 프로퍼티를 정의했음
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }
// - 확장 프로퍼티 사용법 = 멤버 프로퍼티 사용법

/** 3.4 컬렉션 처리: 가변 길이 인자, 중위 함수 호출, 라이브러리 지원 **/
// 코틀린 언어 특성
// - vararg 키워드를 사용하면 호출 시 인자 개수가 달라질 수 있는 함수 정의 가능
// - 중위 함수 호출 구문을 사용하면 인자가 하나뿐인 메서드를 간편하게 호출할 수 있음
// - 구조 분해 선언을 사용하면 복합적인 값을 분해해서 여러 변수에 나눠 담을 수 있음

//fun <T> List<T>.last(): T { /* 마지막 원소를 반환 */ }/*
//fun Collection<Int>.max(): Int { *//* 컬렉션의 최댓값을 찾음 *//* */}

/** 3.4.2 가변 인자 함수: 인자의 개수가 달라질 수 있는 함수 정의 **/
val list2 = listOf(2, 3, 5, 7, 11)
fun varargFunction(args: Array<String>) {
    val list = listOf("args: ", *args)  // 스프레드 연산자가 배열의 내용을 펼쳐줌 (자바에서는 사용 x)
    println(list)
}

/** 3.4.3 값의 쌍 다루기: 중위 호출과 구조 분해 선언 **/
val map2 = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
// - 'to'라는 단어는 코틀린 키워드가 아님
// - 중위 호출이라는 특별한 방식으로 to라는 일반 메서드를 호출
// Ex)
// 1.to("one") -> 'to' 메서드를 일반적인 방식으로 호출
// 1 to "one" -> 'to' 메서드를 중위 호출 방식으로 호출

// 함수를 중위 호출에 사용하게 허용하고 싶으면 infix 변경자를 함수 선안 앞에 추가
infix fun Any.to(other: Any) = Pair(this, other)
// val (number, name) = 1 to "one" -> 구조 분해 선언
// !! to 함수는 확장 함수이다. to를 사용하면 타입과 관계없이 임의의 순서쌍을
// 만들 수 있다. -> to의 수신 객체가 제네릭하다는 뜻이다.

/** 3.5 문자열과 정규식 다루기 **/
// - 코틀린 문자열 = 자바 문자열
// Ex) 마침표나 대시(-)로 문자열을 분리하는 예시 (마침표는 와일드카드 문자가 아닌 문자 자체로 쓰이게 하기 위해 excape시킴)
// println("12.345-6.A".split("\\.|-".toRegex())) // 정규식을 명시적으로 만든다.
// println("12.345-6.A".split(".", "-"))          // 위와 같음

// - 3중 따옴표 문자열을 사용하면
// 역슬래시를 포함한 어떤 문자도 이스케이프할 필요가 없다.
// Ex) 경로를 디렉터리, 파일 이름, 확장자로 분리하는 정규식
// """(.+)/(.+)\.(.+)"""
// -> 첫번째 (.+) 즉, 마지막 슬래시 / 전: 디렉터리
// -> 두번째 (.+) 즉, 마지막 점 전: 파일 이름
// -> 세번째 (.+) 즉, 점 뒤: 확장자

/** 3.6 코드 다듬기: 로컬 함수와 확장 **/
// - 많은 개발자들이 좋은 코드의 중요한 특징 중 하나가 중복이 없는 것이라 믿음
// - 하지만 자바는 'DRY(Don't Repeat Yourself)'원칙을 피하기 쉽지 않음
// - 코틀린에는 깔끔한 해법이 존재 (메서드 추출 리팩토링 등에 따른 불필요한 준비 코드가 늘어나는 문제)
// -> 코틀린에서는 함수에서 추출한 함수를 원 함수 내부에 중첩시킬 수 있음
class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException(
            "Can't save user ${user.id}: empty Name"
        )
    }
    if (user.address.isEmpty()) {
        throw IllegalArgumentException(
            "Can't save user ${user.id}: empty Address"
        )
    }
    // user를 데이터베이스에 저장한다.
    // !! if식 두개를 보면 필드 검증이 중복됨
}

// !! 로컬 함수를 사용해 코드 중복 줄이기
fun saveUser2(user: User) {
    fun validate(
        user: User,
        value: String,
        fieldName: String
    ) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                "Can't save user ${user.id}: empty $fieldName"
            )
        }
    }

    // 로컬 함수를 호출해서 각 필드를 검증함
    validate(user, user.name, "Name")
    validate(user, user.address, "Address")

    // !! !! !! 하지만 User 객체를 로컬 함수에게 하나하나 전달해야 한다는 점이 아쉬움
    // 그래서 밑에 함수로 해결
}

// 로컬 함수에서 바깥 함수의 파라미터 접근하기 (그냥 접근 가능)
fun saveUser3(user: User) {
    fun validate(value: String, fieldName: String) {    // 더 이상 바깥 함수의 user 파라미터 중복 사용 X
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                "Can't save user ${user.id}: " +    // 바깥 함수의 파라미터에 직접 접근 가능
                        " empty $fieldName"
            )
        }
    }

    validate(user.name, "Name")
    validate(user.address, "Address")
}

// 검증 로직을 확장 함수로 추출하기
fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                "Can't save user $id: empty $fieldName" // !! !! User의 프로퍼티를 직접 사용할 수 있음
            )
        }
    }

    validate(name, "Name")
    validate(address, "Address")
}

fun saveUser4(user:User) {
    user.validateBeforeSave()   // 확장 함수 호출

    // user를 데이터베이스에 저장한다.
}

// -> 코드를 확장 함수로 뽑아내는 기법은 놀랄 만큼 유용함!!!
// -> 이 경우, 검증 로직은 User를 사용하는 다른 곳에서는 쓰이지 않는 기능이기 때문에
// User에 포함시키고 싶지는 않음, User를 간결하게 유지하면 생각해야 할 내용이 줄어들어서
// 더 쉽게 코드를 파악 가능
// -> 반면 한 객체만을 다루면서 객체의 비공개 데이터를 다룰 필요는 없는
// 함수는 위 함수처럼 확장 함수로 만들면 객체.멤버 처럼 수신 객체를 지정하지 않고도
// 공개된 멤버 프로퍼티나 메서들에 접근 가능





fun main() {
    // javaClass -> 자바에서의 getClass()와 동일
    println(set.javaClass)
    println(list.javaClass)
    println(map.javaClass)

    collectionTest()

    val list = listOf(1, 2, 3)
    println(list)
    println("Kotlin".lastChar())    // 다른 일반 클래스 멤버를 호출하는 구문과 같음

    val list2 = listOf(1, 2, 3)
    println(list2.joinToString(separator = "; ", prefix = "(", postfix = ")"))
    println(list2.joinToString(separator = " "))
//    println(list2.join(" "))  // Error (원소들이 정수임)
    println(listOf("one", "two", "three").join(separator = " "))

    println("Kotlin".lastCharExtension)
    val sb = StringBuilder("Kotlin?")
    sb.lastCharExtension = '!'
    println(sb)

    varargFunction(arrayOf("one", "two", "three"))

    val (number, name) = 1 to "one"
    println("$number, $name")

    println("12.345-6.A".split("\\.|-".toRegex())) // 정규식을 명시적으로 만든다.
    println("12.345-6.A".split(".", "-")) // 위와 같음

//    saveUser(User(1, "", ""))
//    saveUser2(User(2, "", ""))
//    saveUser3(User(3, "", ""))
    saveUser4(User(4, "", ""))

}