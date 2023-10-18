package com.example.kotlinstudy.study

data class PersonSample(
    val name: String,
    val age: Int
)

fun main() {
    /** 23.10.18 **/
    /** 5.2.4 flatMap과 flatten: 중첩 된 컬렉션 안의 원소 처리 **/
    val list = listOf("abc", "def")
    println(list)
    val listToFlatMap = list.flatMap { it.toList() }
    println(listToFlatMap)

    /** 5.3 지연 계산(lazy) 컬렉션 연산 **/
    val personList = listOf(
        PersonSample("AAA", 1),
        PersonSample("ABB", 2),
        PersonSample("CCC", 3)
    )

    val people = personList
        .map(PersonSample::name)
        .filter { it.startsWith("A") }
    // -> 코틀린 표준 문서에 따르면 map 과 filter 는 리스트를 반환 한다고 써 있음,
    // 연쇄 호출이 리스트를 2개 만든 다는 뜻

    // 시퀀스(sequence)를 사용 하여 지연 계산할 수 있음
    val peopleS = personList.asSequence()   // 원본 컬렉션을 시퀀스로 변환
        .map(PersonSample::name)            // 시퀀스도 컬렉션과 동일한 API사용 가능
        .filter { it.startsWith("A") }
        .toList()                           // 시퀀스를 다시 리스트로 변환

    println(personList.map { it.name })
    println(people)
    println(peopleS)

    // 시퀀스(Sequence 특징)
    // 1. 원소가 수백만 개가 되면 성능 효율을 위해 컬렉션을 직접 사용하는 것보단 시퀀스 사용이 규칙!
    // 2. 시퀀스의 원소는 필요할 때 비로소 계산 된다.
    // 3. 중간처리 결과를 저장 하지 않고도 연산을 연쇄적으로 적용 해서 효율 적으로 계산을 수행할 수 있음.
    // 4. 어떤 컬렉션이든 asSequence() 확장함수를 사용 하면 시퀀스로 바꿀 수 있음
    // 5. 다시 toList()로 리스트로 변환 후 사용 하지만
    // 단순히 이터레이션만 필요 하다면 시퀀스 그대로 사용 가능


    /** 5.3.1 시퀀스 연산 실행: 중간 연산과 최종 연산 **/

    // 6. 시퀀스의 중간 연산은 항상 지연 계산 된다.
    // 최종 연산 호출 하면 연기 됐던 모든 계산이 수행
}