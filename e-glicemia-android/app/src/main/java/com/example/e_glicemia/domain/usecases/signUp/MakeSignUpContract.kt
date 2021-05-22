package com.example.e_glicemia.domain.usecases.signUp

import com.example.e_glicemia.domain.helpers.DomainError

interface MakeSignUpContract {
    fun execute(
        name: String,
        email: String,
        password: String,
        onSuccessListener: () -> Unit,
        onFailureListener: (DomainError) -> Unit
    )
}