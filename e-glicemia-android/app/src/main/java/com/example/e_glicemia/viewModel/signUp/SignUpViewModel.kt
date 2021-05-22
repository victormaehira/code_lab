package com.example.e_glicemia.viewModel.signUp

import androidx.lifecycle.ViewModel
import com.example.e_glicemia.domain.usecases.signUp.MakeSignUpContract
import com.example.e_glicemia.util.Utilities

class SignUpViewModel(
    private val contract: SignUpContract,
    private val makeSignUp: MakeSignUpContract
) : ViewModel() {

    var name = ""
    var email = ""
    var password = ""

    fun onSignUpPressed() {
        if (email.isNullOrEmpty() || password.isNullOrEmpty() || name.isNullOrEmpty()) {
            contract.showFormInstructions()
            return
        }
        if (Utilities.isValidEmail(email) == false) {
            contract.showInvalidEmailMessage()
            return
        }
        makeSignUp.execute(
            name,
            email,
            password,
            {
                contract.goToMainActivity()
            },
            { error ->
                contract.showGenericErrorMessage()
            }
        )
    }

    fun onExitPressed() {
        contract.goToLoginActivity()
    }
}