package com.example.kotlinstudy.study

import android.content.Context
import android.util.AttributeSet

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
class Button : Clickable {
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
class Button2 : Clickable, Focusable {
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

open class RichButton : Clickable {  // open class, 다른 클래스가 이 클래스를 상속 가능
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

// !! 인터페이스의 멤버의 경우 final, open, abstract를 사용 X
// 인터페이스 멤버는 항상 열려 있으며 final로 변경할 수 X
// 인터페이스 멤버에게 본문이 없으면 자동으로 추상 멤버가 됨

/** 클래스 내에서 상속 제어 변경자의 의미 **/
// 1. final     (오버라이드 X, 클래스 멤버의 기본 변경자)
// 2. open      (오버라이드 O, 반드시 open을 명시해야 오버라이드할 수 있음)
// 3. abstract  (반드시 오버라이드, 추상 클래스의 멤버에만 이 변경자를 붙임, 추상 멤버에는 구현이 있으면 안됨)
// 오버라이드하는 멤버는 기본적으로 열려있음, 오버라이드 금지하려면 final을 명시

/** 4.1.3 코틀린의 가시성 변경자 **/
//                        [클래스 멤버]                  [최상위 선언]
// 1. public(기본 가시성)    모든 곳에서 볼 수 있음           모든 곳에서 볼 수 있음
// 2. internal            같은 모듈 안에서만 볼 수 있음      같은 모듈 안에서만 볼 수 있음
// 3. protected           하위 클래스 안에서만 볼 수 있음     (최상위 선언에 적용할 수 없음)
// 4. private             같은 클래스 안에서만 볼 수 있음     같은 파일 안에서만 볼 수 있음

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
}

// 오류: public 멤버가 자신의 internal 수신타입인 TalkativeButton을 노출함
//fun TalkativeButton.giveSpeech() {
//    yell()        // 오류: private
//    whisper()     // 오류: protected
//}

/** 4.1.4 내부 클래스와 중첩된 클래스: 기본적으로 중첩 클래스 **/
// 코틀린도 자바처럼 클래스 안에 다른 클래스 선언 가능
// 클래스 안에 다른 클래스를 선언하면 도우미 클래스를 캡슐화하거나
// 코드 정의를 그 코드를 사용하는 곳 가까이에 두고 싶을 때 유용
interface State : java.io.Serializable
interface View {
    fun getCurrentState(): State
    fun restoreState(state: State) {}
}

// Button 클래스의 상태를 저장하는 클래스는 Button 클래스 내부에 선언하면 편함
// 153,154p 자바 예제코드에서
// ButtonState 클래스는 바깥쪽 Button 클래스에 대한 참조를 묵시적으로 포함 -> ButtonState 직렬화 X
// 문제 해결: ButtonState를 static으로 선언 -> 참조 사라짐

// 코틀린 코드
class Button3 : View {
    override fun getCurrentState(): State = ButtonState()
    override fun restoreState(state: State) {}
    class ButtonState : State {} // -> 아무 변경자가 붙지 않으면 자바 static 중첩클래스와 동일

}
// !! 자바와 코틀린의 중첩 클래스와 내부 클래스의 관계
// 중첩 클래스(바깥쪽 클래스에 대한 참조를 저장하지 않음): 자바(static class A), 코틀린(class A)
// 내부 클래스(바깥쪽 클래스에 대한 참조를 저장함): 자바(class A), 코틀린(inner class A)
// 또한, 중첩 클래스는 클래스 계층을 만들되 그 계층에 속한 클래스의 수를 제한하고 싶은 경우 편리

// inner class 예시
class Outer {
    inner class Inner {
        // 내부 클래스(Inner) 안에서 바깥쪽 클래스(Outer)의 참조에 접근하려면 this@Outer 사용
        fun getOuterReference(): Outer = this@Outer
    }
}

/** 4.1.5 봉인된 클래스(sealed class): 클래스 계층 정의 시 계층 확장 제한 **/
// sealed class 특징 ->
// 1. 자동 open
// 2. 상속 제한 (sealed class 는 클래스 외부에 자신을 상속한 클래스를 둘 수 없음)
sealed class SealedExpr {
    // 중첩 클래스들
    class Num(val value: Int) : SealedExpr()
    class Sum(val left: SealedExpr, val right: SealedExpr) : SealedExpr()
}

fun eval(e: SealedExpr): Int =
    when (e) {
        is SealedExpr.Num -> e.value
        is SealedExpr.Sum -> eval(e.right) + eval(e.left)
    }
// 'when' 식이 모든 하위 클래스를 검사하므로 별도의 'else' 분기 필요 X

// !!
// 내부적으로 SealedExpr 클래스는 private 생성자를 가지고
// 그 생성자는 클래스 내부에서만 호출 가능
// 위에 sealed class 특징 2번의 이유!!!!!!!

/** !! 코틀린 1.5 이상 버전 변경사항 **/
// 1. sealed class 가 정의된 패키지 안의 아무 위치에 선언 가능,
// 2. sealed interface 추가
// 2번 추가 설명 ->
// 원래는 sealed interface를 정의 못했었음, sealed interface를 만들 수 있다면
// 그 인터페이스를 자바 쪽에서 구현하지 못하게 막을 수 있는 수단이 코틀린 컴파일러에게 없었기 때문

/** 4.2 뻔하지 않은 생성자와 프로퍼티를 갖는 클래스 선언 **/
// 코틀린도 자바처럼 하나 이상의 생성자를 선언할 수 있지만
// 한가지 바뀐 부분은 코틀린은 주 생성자, 부 생성자를 구분한다.
// 또한 코틀린에서는 초기화 블록을 통해 초기화 로직을 추가할 수 있다.

/** 4.2.1 클래스 초기화: 주 생성자와 초기화 블록 **/
// class User(val name: String) 에서 클래스 이름 뒤에 오는 괄호로 둘러싸인 코드를 주 생성자라고 부름
class User2 constructor(_nickname: String) {    // constructor 키워드는 주 생성자나 부 생성자 정의를 시작할 때 사용
    val nickname: String

    init {  // 초기화 블록
        nickname = _nickname
    }
}

// 그리고 주 생성자 앞에 별다른 애노테이션이나 가시성 변경자가 없다면 constructor를 생략해도 됨
class User3(_nickname: String) {
    val nickname = _nickname
}

// !! 결국 class User(val name: String) 처럼
// 프로퍼티 정의와 초기화를 간략히 쓸 수 있음

// 함수 파라미터와 마찬가지로 생성자 파라미터에도 디폴트 값 정의 가능
class User4(val check: Boolean = false)

// !! 클래스에 기반 클래스가 있다면 주 생성자에서 기반 클래스의 생성자를 호출해야 할 필요가 있음
// 기반 클래스를 초기화하려면 기반 클래스 이름 뒤에 괄호를 치고 생성자 인자를 넘김
open class TestUser(val nickname: String)
class TwitterUser(nickname: String) : TestUser(nickname)

// !
// 또 하나 알 수 있는 것은 
// 기반 클래스의 이름 뒤에는 괄호가 들어간다
open class BaseClass
interface BaseInterface

//class MainClass(): BaseClass  // -> 에러, 아래 처럼 빈 괄호 작성 해줘야함
class MainClass() : BaseClass(), BaseInterface// -> 인터페이스는 생성자가 없기 때문에 괄호 필요 X

// !! 어떤 클래스를 클래스 외부에서 인스턴스화하지 못하게 막고 싶다면
// 모든 생성자를 private으로 만들면 됨
class Secretive private constructor()
// -> 외부에서 Secretive를 인스턴스화할 수 없음

/** 4.2.2 부 생성자: 상위 클래스를 다른 방식으로 초기화 (가볍게) **/
// !! Tip: 인자에 대한 디폴트 값을 제공하기 위해 부 생성자를 여럿 만들지 마라.
// -> 대신 파라미터의 디폴트 값을 생성자 시그니처에 직접 명시하라

// 그래도 생성자 여럿 필요한 경우가 가끔 있음
// Ex) 가장 일반적인 상황은 프레임워크 클래스를 확장해야 하는데 여러 가지 방법으로 인스턴스를
// 초기화할 수 있게 다양한 생성자를 지원해야 하는 경우

// 예를들어 자바에서 선언된 생성자가 2개인 View클래스 (코틀린 코드)
open class View2 {  // 주 생성자 X
    // 부 생성자들
    constructor(ctx: Context) {}
    constructor(ctx: Context, attr: AttributeSet) {}
}

// 위 클래스를 확장하면서 똑같이 부 생성자를 정의할 수 있음
class MyButton : View2 {
    // 상위 클래스의 생성자를 호출함 (즉, 생성을 위임함)
    constructor(ctx: Context)
            : super(ctx) {

    }

    constructor(ctx: Context, attr: AttributeSet)
            : super(ctx, attr) {

    }
}

/** 4.2.3 인터페이스에 선언된 프로퍼티 구현 **/
interface User5 {
    val nickname: String    // 추상 프로퍼티
}

class PrivateUser(override val nickname: String) : User5 // 주 생성자에 있는 프로퍼티
class SubscribingUser(val email: String) : User5 {
    override val nickname: String
        get() = email.substringBefore('@')  // 커스텀 게터
}
// class FacebookUser(val accountId: Int): User5 {
//    override val nickname: String = getFacebookName(accountId)    // 프로퍼티 초기화 식
// }

interface User6 {
    val email: String
    val name: String    // 프로퍼티에 뒷받침하는 필드가 없음, 대신 매번 결과를 계산해 돌려줌
        get() = email.substringBefore('@')
}

/** 4.2.4 게터와 세터에서 뒷받침하는 필드에 접근 **/
// Ex) 프로퍼티에 저장된 값의 변경 이력을 로그에 남기려는 경우
class User7(val name: String) {
    var address: String = "unspecified"
        set(value: String) {
            println(
                """
            Address was changed for $name:
            "$field" -> "$value".
        """.trimIndent()
            )   // 뒷받침하는 필드 값 읽기
            field = value   // 뒷받침하는 필드 값 변경하기
            // !! 여기서
            // field: oldValue
            // value: newValue
            // 게터에서는 field 값을 읽을 수만 있고
            // 세터에서는 field 값을 읽거나 쓸 수 있음
        }
}

/** 4.2.5 접근자의 가시성 변경 **/
class LengthCounter {
    var counter: Int = 0
        private set // 이 클래스 밖에서 이 프로퍼티의 값을 바꿀 수 없음

    fun addWord(word: String) {
        counter += word.length
    }
}

/** 4.3 컴파일러가 생성한 메서드: 데이터 클래스와 클래스 위임 **/

/** 4.3.1 모든 클래스가 정의해야 하는 메서드 **/
// 해시 컨테이너: hashCode()
// JVM 언어에서는 hashCode가 지켜야 하는 "equals()"가 true를 반환하는 두 객체는
// 반드시 같은 hashCode()를 반환해야 한다" 라는 제약이 있음






fun main() {
    Button().click()
    println()
    val button = Button2()
    button.showOff()
    button.setFocus(true)
    button.click()

    println(PrivateUser("test@kotlinlang.org").nickname)
    println(SubscribingUser("test@kotlinlang.org").nickname)
    println()

    val user = User7("Alice")
    user.address = "Elsenheimerstrasse 47, 80687 Muenchen"  // 커스텀 세터 (추가) 호출
    println()

    val lengthCounter = LengthCounter()
    lengthCounter.addWord("Hi!")
    println(lengthCounter.counter)
}
