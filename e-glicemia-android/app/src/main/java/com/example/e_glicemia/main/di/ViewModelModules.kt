package com.example.e_glicemia.main.di

import com.example.e_glicemia.data.login.useCases.CheckUserIsLogged
import com.example.e_glicemia.data.login.useCases.MakeLogin
import com.example.e_glicemia.data.signUp.useCases.MakeSignUp
import com.example.e_glicemia.viewModel.login.LoginContract
import com.example.e_glicemia.viewModel.login.LoginViewModel
import com.example.e_glicemia.viewModel.signUp.SignUpContract
import com.example.e_glicemia.viewModel.signUp.SignUpViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

object ViewModelModules {

    val modules = module {
        viewModel { (contract: LoginContract) ->
            LoginViewModel(contract, get<CheckUserIsLogged>(), get<MakeLogin>()) }

        viewModel { (contract: SignUpContract) ->
            SignUpViewModel(contract, get<MakeSignUp>())
        }
    }

}