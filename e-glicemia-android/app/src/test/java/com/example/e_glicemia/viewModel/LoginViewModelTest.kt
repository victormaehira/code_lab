package com.example.e_glicemia.viewModel

import com.example.e_glicemia.domain.usecases.login.CheckUserIsLoggedContract
import com.example.e_glicemia.domain.usecases.login.MakeLoginContract
import com.example.e_glicemia.viewModel.login.LoginContract
import com.example.e_glicemia.viewModel.login.LoginViewModel
import com.nhaarman.mockitokotlin2.times
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class LoginViewModelTest {

    var sut: LoginViewModel? = null

    private val contract = Mockito.mock(LoginContract::class.java)
    private val checkUserIsLogged = Mockito.mock(CheckUserIsLoggedContract::class.java)
    private val makeLogin = Mockito.mock(MakeLoginContract::class.java)


    @Before
    fun setup() {
        sut = LoginViewModel(
            contract,
            checkUserIsLogged,
            makeLogin
        )
    }

    @Test
    fun `Should not call go to main activity if user is logged`() {
        Mockito.`when`(checkUserIsLogged.execute()).thenAnswer {
            return@thenAnswer false
        }

        sut?.onCreate()

        Mockito.verify(contract, times(0)).goToMainActivity()
    }

    @Test
    fun `Should call go to SignUp activity if button is pressed`() {
        sut?.onSignUpPressed()

        Mockito.verify(contract, times(1)).goToSignUpActivity()
    }


}