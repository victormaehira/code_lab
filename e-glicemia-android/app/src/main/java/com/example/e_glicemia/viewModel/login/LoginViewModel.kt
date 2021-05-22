package com.example.e_glicemia.viewModel.login

import androidx.lifecycle.ViewModel
import com.example.e_glicemia.domain.helpers.DomainError
import com.example.e_glicemia.domain.usecases.login.CheckUserIsLoggedContract
import com.example.e_glicemia.domain.usecases.login.MakeLoginContract
import com.example.e_glicemia.util.Utilities

class LoginViewModel(
    private val contract: LoginContract,
    private val checkUserIsLogged: CheckUserIsLoggedContract,
    private val makeLogin: MakeLoginContract
) : ViewModel() {

    var email: String = ""
    var password: String = ""

    fun onCreate() {
        /*
        if (checkUserIsLogged.execute()) {
            contract.goToMainActivity()
        }*/
    }

    fun onLoginPressed() {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            contract.showFormInstructions()
            return
        }
        if (Utilities.isValidEmail(email) == false) {
            contract.showInvalidEmailMessage()
            return
        }
        makeLogin.execute(email, password, {
            contract.showLoginComSucesssoMessage()
            contract.goToMainActivity()
        }, { error ->
            when (error) {
                DomainError.GENERIC_ERROR -> contract.showGenericErrorMessage()
            }
        })

    }

    fun onSignUpPressed() {
        contract.goToSignUpActivity()
    }

}