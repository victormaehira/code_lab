package com.example.e_glicemia.viewModel.signUp

interface SignUpContract {
    fun showGenericErrorMessage()
    fun goToMainActivity()
    fun goToLoginActivity()
    fun showFormInstructions()
    fun showInvalidEmailMessage()
}