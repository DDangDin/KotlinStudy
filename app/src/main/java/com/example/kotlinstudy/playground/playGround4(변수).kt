package com.example.kotlinstudy.playground

//                                      *** 상수 ***
const val CONST_A = 1234

// 상수 별도 사용 이유
// 변수는 런타임시 객체 생성하는데 시간이 더 소요되어 성능의 하락이 있을 수 있음

// 밑에 구문들 불가능, 상수 사용 불가능
//class Sample { const val CONST_A = 1234 }
//fun sample { const val CONST_A = 1234 }

// 반드시 companion obejct 안에 선언하여 객체의 생성과 관계없이
// 클래스와 관계된 (Sample.CONST_A) 고정적인 값으로만 사용하게 된다.
class Sample {
    companion object {
        const val CONST_A = 1234
    }
}

class FoodCourt {
    companion object {
        const val FOOD_CREAM_PASTA = "크림파스타"
        const val FOOD_STEAK = "스테이크"
        const val FOOD_PIZZA = "피자"
    }

    fun searchPrice(foodName: String) {
        val price = when(foodName) {
            FOOD_CREAM_PASTA -> 10000
            FOOD_STEAK -> 20000
            FOOD_PIZZA -> 30000
            else -> 0
        }
        println("${foodName}의 가격은 ${price}원 입니다.")
    }
}


//                                      *** lateinit var ***
class LateInitSample {
    lateinit var text: String
    fun getLateInitText(): String {
        if (::text.isInitialized) { // 확인하고 오류를 막을 수 있음
            return text
        } else {
          return "기본값"
        }
    }
}

fun main() {
    val foodCourt = FoodCourt()

    foodCourt.searchPrice(FoodCourt.FOOD_CREAM_PASTA)
    foodCourt.searchPrice(FoodCourt.FOOD_STEAK)
    foodCourt.searchPrice(FoodCourt.FOOD_PIZZA)

    val a = LateInitSample()

    println(a.getLateInitText())
    a.text = "새로 할당한 값"
    println(a.getLateInitText())

    // lazy delegate properties (지연 대리자 속성)
    val number: Int by lazy {
        println("초기화를 합니다")
        7
    }

    println("코드실행")
    println(number)
    println(number)
}