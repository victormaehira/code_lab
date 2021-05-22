package com.example.e_glicemia.util

import java.lang.Double.parseDouble
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.regex.Matcher
import java.util.regex.Pattern


class Utilities {
    companion object {
        fun isValid(data: String): Boolean {
            val sdf: DateFormat = SimpleDateFormat("dd/MM/yyyy")
            sdf.setLenient(false)
            try {
                sdf.parse(data)
            } catch (e: ParseException) {
                return false
            }
            return true
        }
        fun isValidTime(hora: String): Boolean {
            val sdf: DateFormat = SimpleDateFormat("HH:mm")
            sdf.setLenient(false)
            try {
                sdf.parse(hora)
            } catch (e: ParseException) {
                return false
            }
            return true
        }
        fun isNumber(valor: String): Boolean {
            try {
                val num = parseDouble(valor)
            } catch (e: NumberFormatException) {
                return false
            }
            return true
        }
        fun isValidEmail(email: String): Boolean {
            return if (email.isNullOrEmpty()) {
                false
            } else {
                val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
                val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
                val matcher: Matcher = pattern.matcher(email)
                return matcher.matches()
            }
        }
    }
}