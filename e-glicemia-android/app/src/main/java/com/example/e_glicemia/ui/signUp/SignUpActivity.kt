package com.example.e_glicemia.ui.signUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.e_glicemia.ui.glicemia.MainActivity
import com.example.e_glicemia.R
import com.example.e_glicemia.databinding.ActivitySignUpBinding
import com.example.e_glicemia.ui.login.LoginActivity
import com.example.e_glicemia.viewModel.signUp.SignUpContract
import com.example.e_glicemia.viewModel.signUp.SignUpViewModel
import org.koin.core.parameter.parametersOf
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : AppCompatActivity(), SignUpContract {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignUpViewModel by viewModel { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.viewModel = viewModel
    }

    override fun showGenericErrorMessage() {
        Toast.makeText(this, "Falha ao realizar login", Toast.LENGTH_SHORT).show()
    }

    override fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun goToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun showFormInstructions() {
        Toast.makeText(this, "Favor preencher todos os campos", Toast.LENGTH_SHORT).show()
    }

    override fun showInvalidEmailMessage() {
        Toast.makeText(this, "E-mail em formato inválido!", Toast.LENGTH_SHORT).show()
    }

}