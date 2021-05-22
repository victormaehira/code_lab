package com.example.e_glicemia.data.login.useCases

import com.example.e_glicemia.domain.usecases.login.CheckUserIsLoggedContract
import com.google.firebase.auth.FirebaseAuth

class CheckUserIsLogged(
    private val auth: FirebaseAuth
) : CheckUserIsLoggedContract {

    override fun execute(): Boolean {
        return auth.currentUser != null
    }

}