package com.example.e_glicemia.util

import org.junit.Assert
import org.junit.Test

class UtilitiesTest {
    @Test
    fun isValidEmailTest() {
        var email:String = "abobora@teste.com"
        Assert.assertEquals(true, Utilities.isValidEmail(email))
    }

    @Test
    fun isInvalidEmailTest() {
        var email:String = "nao_sou_email_valido"
        Assert.assertEquals(false, Utilities.isValidEmail(email))
    }

    @Test
    fun isRegistroGlicemicoInvalido() {
        var glicemia = "nao_sou_valido"
        Assert.assertEquals(false, Utilities.isNumber(glicemia))
    }

    @Test
    fun isRegistroGlicemicoValido() {
        var glicemia = "120"
        Assert.assertEquals(true, Utilities.isNumber(glicemia))
    }

    @Test
    fun isDataValida() {
        var data = "25/12/2021"
        Assert.assertEquals(true, Utilities.isValid(data))
    }

    @Test
    fun isHoraValida() {
        var data = "23:30"
        Assert.assertEquals(true, Utilities.isValidTime(data))
    }
}