package com.axel.hogwartslibrary.ui.scenes.hat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.axel.hogwartslibrary.R
import com.axel.hogwartslibrary.helpers.Keys
import com.axel.hogwartslibrary.ui.scenes.main.MainActivity
import kotlinx.android.synthetic.main.activity_hat.*

class HatActivity : AppCompatActivity() {

    private lateinit var hatViewModel: HatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hat)
        hatViewModel = ViewModelProviders.of(this).get(HatViewModel::class.java)

        textWelcomeUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                hatViewModel.applyUsername(name = s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        btnWelcomeSelect.setOnClickListener {
            if (btnWelcomeSelect.text == getString(R.string.welcome_next)) {
                var intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                hatViewModel.getFacultyName()
            }
        }

        setupFaculty(viewModel = hatViewModel)
        setupLoading(viewModel = hatViewModel)
    }

    private fun setupFaculty(viewModel: HatViewModel) {
        viewModel.facultyName.observe(this, Observer { facultyName ->
            if (facultyName.isNotEmpty()) {
                val sharedPreferences = getSharedPreferences(getString(R.string.app_name), 0)
                sharedPreferences.edit()
                    .putString(Keys.Username.value, textWelcomeUsername.text.toString())
                    .putString(Keys.Faculty.value, facultyName)
                    .apply()

                txtWelcomeSelect.text =
                    getString(R.string.welcome_selected).replace("[faculty_name]", facultyName)
                txtWelcomeSelect.visibility = View.VISIBLE
                textWelcomeUsername.isEnabled = false
                btnWelcomeSelect.text = getString(R.string.welcome_next)
            }
        })
    }

    private fun setupLoading(viewModel: HatViewModel) {
        viewModel.isLoading.observe(this, Observer { isLoading ->
            textWelcomeUsername.isEnabled = !isLoading
            btnWelcomeSelect.isEnabled = !isLoading

            if (isLoading) {
                btnWelcomeSelect.text = getString(R.string.welcome_selecting)
            }

        })
    }

}