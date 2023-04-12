package com.example.kotlinstudy





fun main() {

//    int a = 0;

    val intRange: IntRange = 1..10
    val intRange2 = 1 until 10
    val text = "index: "
    for (i in intRange2) {
        println("$text $i")
    }

    val sample = SampleClass("name")

}

fun compare(a: Int): Int {
    if(a is Int) {
        return a
    } else {
        return  0
    }
}

open class SampleClass(val name: String) {

    open fun setName(){

    }
}

interface SampleInter{}


class SampleClass2(name: String): SampleClass(name), SampleInter {


}

