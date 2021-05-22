package com.example.e_glicemia.data.login.useCases

import com.example.e_glicemia.domain.helpers.DomainError
import com.example.e_glicemia.domain.usecases.login.MakeLoginContract
import com.google.firebase.auth.FirebaseAuth

class MakeLogin(
    private val auth: FirebaseAuth
): MakeLoginContract {

    override fun execute(
        email: String,
        password: String,
        onSuccessListener: () -> Unit,
        onFailureListener: (DomainError) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onSuccessListener()
            }
            .addOnFailureListener {
                onFailureListener(DomainError.GENERIC_ERROR)
            }
    }
}