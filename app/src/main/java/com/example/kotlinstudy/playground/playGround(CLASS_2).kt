package com.example.kotlinstudy.playground

// Nested Class (중첩 클래스)
// 객체지향 / 캡슐화
// 외부클래스의 내용을 공유할 수 없는 별개의 클래스
class AClass {
    val tempText1 = "tempTest1"
    class ANestedClass {
        fun test() {
            println("NestedClass test")
        }
    }
}


// Inner Class(내부 클래스)
// RecyclerView
// 혼자서 객체를 만들 수 없음(외부 클래스의 객체가 있어야만 생성 및 사용 가능)
class BClass {
    val tempText2 = "tempText2"
    inner class BInnerClass {
        fun test() {
            println("innerClass test")
            println(tempText2)
        }
    }
}





fun main() {
    val aClass = AClass.ANestedClass()
    aClass.test()

//    val BClass = BClass.BInnerClass()
    val BClass = BClass().BInnerClass()
    BClass.test()
}