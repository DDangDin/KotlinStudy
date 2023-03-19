package com.example.kotlinstudy.study

/** '!!' 키워드 붙은 문장은 중요 문장, 꼭 읽어보기!! **/
/** 2.1 기본 요소: 함수와 변수 **/

/** 2.1.1 Hello World **/
// - 코틀린의 함수 선언 키워드: fun
// - 파라미터 이름 뒤에 파라미터의 타입 씀
// - 함수를 최상위 수준에 정의할 수 있다. (클래스 안에 함수를 넣는 자바와는 다름)
// - 자바: System.out.println, 코틀린: println
// - 세미콜론 불필요

/** 2.1.2 함수 **/
// 반환 타입은 함수 뒤에 :을 붙이고 타입 작성
fun max(a: Int, b: Int): Int {
    return if(a > b) a else b   // -> 삼항 연산자

    //  !! 코틀린에서 if는 식이다 (문이 아니다)
    //  식은 값을 만들어 내며 다른 식의 하위 요소로
    //  계산에 참여할 수 있는 반면 문은 자신을 둘러싸고 있는 가장 안쪽 블록의 최상위 요소로 존재하며
    //  아무런 값을 만들어내지 않는다는 차이가 있음

    // 자바: 모든 제어 구조가 문
    // !! 코틀린: 루프를 제외한 대부분의 제어 구조가 식 -> 여러 일반적인 패턴을 아주 간결하게 표현 가능
}

// 식이 본문인 함수
fun maxSimple(a: Int, b: Int): Int = if(a > b) a else b

/** 2.1.3 변수 **/
// val 변수이름: 타입
// - 변경 가능한 변수: var
// - 변경 불가능한 변수: val (자바에서의 final)
// !! 기본적으로는 모든 변수를 val 키워드를 사용해 불변 변수로 선언,
// 나중에 꼭 필요할 때에만 var로 변경
// !! val 참조 자체는 불변일지라도 그 참조가 가리키는 객체의 내부 값은 변경될 수 있음

/** 2.1.4 문자열 템플릿 **/
// val name = "Bob"
// println("Hello, $name")
// println("Hello, ${name}")
// println("Hello, \$name")

/** 2.2 클래스와 프로퍼티 **/
// 자바 Person 클래스
//public class Person {
//    private final String name;
//
//    public Person(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//}

// !! 코틀린 Person 클래스
class Person(val name: String)  // -> 접근제한자 키워드: 코틀린은 기본으로 public (생략)
// -> 이런 유형의 클래스(코드가 없이 데이터만 저장하는 클래스)를 값 객체(value object)라 부름
// 클래스의 목적: 데이터를 캡슐화
class Person2(
    val name: String,
    var isPerson: Boolean
)




fun main() {
    // Int 타입의 값을 반환하는 함수 호출
    println(max(1,2))       // 블록이 본문인 함수
    println(maxSimple(1,2)) // 식이 본문인 함수
    // 결과 값은 동일

    val languages = arrayListOf("Java") // 불변 참조
    languages.add("Kotlin")             // 참조가 가리키는 객체 내부를 변경

    val person = Person2("Bob", true)
    println("${person.name}, ${person.isPerson}")
    person.isPerson = false
    println("${person.name}, ${person.isPerson}")


}