package com.troll.design.builder

data class Person(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val phone: String,
    val address: String
)

class PersonBuilder {
    var firstName = ""
    var lastName = ""
    var age = 0
    var phone = ""
    var address = ""
    fun build() = Person(firstName, lastName, age, phone, address)
}


fun main() {
    PersonBuilder().apply {
        firstName = ""
    }.build()
}