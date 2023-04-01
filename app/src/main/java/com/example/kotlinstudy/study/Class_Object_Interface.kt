package com.example.kotlinstudy.study

/** 4장 클래스, 객체, 인터페이스 **/
// !! sealed class는 클래스 상속을 제한

/** 4.1 클래스 계층 정의 **/

/** 4.1.1 코틀린 인터페이스 **/
// - 코틀린 인터페이스 안에는 추상 메서드뿐 아니라 구현이 있는 메서드도 정의 가능
// - 다만 인터페이스에는 아무런 상태(필드)도 들어갈 수 없음
// 자바와 마찬가지로 인터페이스는 여러 개 구현 가능하고,
// 클래스는 오직 하나만 확장 가능

interface Clickable {
    fun click() // 추상 메서드 (일반 메서드 선언)
    fun showOff() = println("I'm clickable!")   // 디폴트 구현이 있는 메서드
}
// 인터페이스 구현
class Button: Clickable {
    override fun click() = println("I was clicked")
}
interface Focusable {
    fun setFocus(b: Boolean) =
        println("I ${if (b) "got" else "lost"} focus.")

    fun showOff() = println("I'm focusable!")
}

// -> Clickable, Focusable 인터페이스에 동일한 메서드가 있다
// 한 클래스에서 두 인터페이스를 함께 구현하면 어느 쪽에 showOff 메서드도 선택되지 않음
// showOff 구현을 대체할 오버라이딩 메서드를 직접 제공하지 않으면 컴파일러 오류 발생
// 해결법은 밑에 코드
class Button2: Clickable, Focusable {
    override fun click() = println("I was clicked")
    override fun showOff() {    // super<상위 타입의 이름> 를 명시해 구분 가능
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }

    // 하나만 사용한다면
//    override fun showOff() = super<Clickable>.showOff()
}

/** 4.1.2 open, final, abstract 변경자: 기본적으로 final **/
// * 취약한 기반 클래스(fragile base class) *
// : 취약한 기반 클래스라는 문제는 하위 클래스가 기반 클래스에 대해
// 가졌던 가정이 기반 클래스를 변경함으로써 깨져버린 경우에 생김

// !! 이펙티브 자바 책에서 인용
// : '상속을 위한 설계와 문서를 갖추거나, 그럴 수 없다면 상속을 금지하라'라는 조언

// 이는 특별히 하위 클래스에서 오버라이드하게 의도된 클래스와 메서드가
// 아니라면 모두 final 로 만들라는 뜻 -> 코틀린도 마찬가지 철학을 따름

// 자바의 클래스와 메서드는 기본적으로 상속에 대해 열려있지만
// 코틀린의 클래스와 메서드는 기본적으로 final 임

// 어떤 클래스의 상속을 허용하려면 open 변경자를 붙여야함
// 더불어 오버라이드를 허용하고 싶은 메서드나 프로퍼티의 앞에도 open 변경자를 붙어야함

open class RichButton: Clickable {  // open class, 다른 클래스가 이 클래스를 상속 가능
    fun disable() {}                // 이 함수는 final 로 하위 클래스가 이 메서드를 오버라이드 X
    open fun animate() {}           // 하위 클래스에서 이 메서드를 오버라이드 할 수 있음
//    override fun click() {}         // 이 함수는 (상위 클래스에서 선언된) 열려있는 메서드를 오버라이드 한다.
    // 오버라이드한 메서드는 기본적으로 열려있음
    final override fun click() {}   // 오버라이드해서 구현한 메서드 오버라이드 금지 (final)
}

// * 추상클래스 *
// -> abstract 로 선언한 추상클래스는 인스턴스화할 수 없음
// 추상 멤버는 항상 open
abstract class Animated {
    abstract fun animate()
    // 추상클래스에 속했더라도 비추상 함수는 기본적으로 final, 원한다면 open 으로 오버라이드 허용 가능
    open fun stopAnimating() {}
    fun animateTwice() {}
}






fun main() {
    Button().click()
    println()
    val button = Button2()
    button.showOff()
    button.setFocus(true)
    button.click()
}