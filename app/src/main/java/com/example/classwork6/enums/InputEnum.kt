package com.example.classwork6.enums

enum class InputEnum {
    EMAIL_IS_EMPTY {
        override fun toString() = "Email is empty"
    },
    PASSWORD_IS_EMPTY {
        override fun toString() = "Password is empty"
    },
    EMAIL_DOES_NOT_MATCH {
        override fun toString() = "Please enter valid email"
    },
    ALL_IS_OK
}