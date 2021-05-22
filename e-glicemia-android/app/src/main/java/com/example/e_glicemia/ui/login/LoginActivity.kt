package com.example.e_glicemia.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.e_glicemia.ui.glicemia.MainActivity
import com.example.e_glicemia.R
import com.example.e_glicemia.ui.signUp.SignUpActivity
import com.example.e_glicemia.viewModel.login.LoginContract
import com.example.e_glicemia.viewModel.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.databinding.DataBindingUtil
import com.example.e_glicemia.databinding.ActivityLoginBinding
//import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity(), LoginContract {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel

        viewModel.onCreate()
    }

    override fun showGenericErrorMessage() {
        Toast.makeText(this, "Falha ao realizar login", Toast.LENGTH_SHORT).show()
    }

    override fun showLoginComSucesssoMessage() {
        Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
    }

    override fun showFormInstructions() {
        Toast.makeText(this, "Favor preencher todos os campos", Toast.LENGTH_SHORT).show()
    }

    override fun showInvalidEmailMessage() {
        Toast.makeText(this, "E-mail em formato inválido!", Toast.LENGTH_SHORT).show()
    }

    override fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun goToSignUpActivity() {
        startActivity(Intent(this, SignUpActivity::class.java))
    }
}