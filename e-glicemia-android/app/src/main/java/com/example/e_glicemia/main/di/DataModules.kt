package com.example.e_glicemia.main.di

import com.example.e_glicemia.data.login.useCases.CheckUserIsLogged
import com.example.e_glicemia.data.login.useCases.MakeLogin
import com.example.e_glicemia.data.signUp.useCases.MakeSignUp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

object DataModules {
    val modules = module {
        factory { CheckUserIsLogged(Firebase.auth) }
        factory { MakeLogin(Firebase.auth) }
        factory { MakeSignUp(Firebase.auth) }
    }
}