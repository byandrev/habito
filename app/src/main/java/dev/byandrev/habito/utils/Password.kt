package dev.byandrev.habito.utils

fun isValidPassword(password: String): Boolean {
    val regexPattern = Regex("^[a-zA-Z0-9!@#$%^&*()_+=\\-\\[\\]{}|;:'\",.<>/?`~]+$")
    return regexPattern.matches(password)
}