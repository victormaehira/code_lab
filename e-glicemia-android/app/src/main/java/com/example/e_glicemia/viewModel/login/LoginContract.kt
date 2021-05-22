package com.example.e_glicemia.viewModel.login

interface LoginContract {
    fun showGenericErrorMessage()
    fun goToMainActivity()
    fun goToSignUpActivity()
    fun showLoginComSucesssoMessage()
    fun showFormInstructions()
    fun showInvalidEmailMessage()
}