package com.example.e_glicemia.domain.usecases.login

import com.example.e_glicemia.domain.helpers.DomainError

interface MakeLoginContract {
    fun execute(email: String,
                password: String,
                onSuccessListener: () -> Unit,
                onFailureListener: (DomainError) -> Unit)
}