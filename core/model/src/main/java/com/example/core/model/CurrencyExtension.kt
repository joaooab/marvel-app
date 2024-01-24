package com.example.core.model

fun String?.toCurrency() = "$${this ?: 0.0}"
fun String?.formatDate() = "${this?.split("T")?.firstOrNull().orEmpty()}"