package com.example.physical

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup

class SettingsActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sexRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialize o SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Referência ao RadioGroup
        sexRadioGroup = findViewById(R.id.sexRadioGroup)

        // Recuperar a última seleção do usuário
        val lastSelectedSex = sharedPreferences.getString("user_sex", "")

        // Marcar a opção selecionada anteriormente, se houver
        val radioButton: RadioButton? = when (lastSelectedSex) {
            "Masculino" -> findViewById(R.id.maleRadioButton)
            "Feminino" -> findViewById(R.id.femaleRadioButton)
            "Outro" -> findViewById(R.id.otherRadioButton)
            else -> null
        }
        radioButton?.isChecked = true

        // Define um ouvinte para o RadioGroup para salvar a escolha do usuário
        sexRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton: RadioButton = findViewById(checkedId)
            val selectedSex = selectedRadioButton.text.toString()

            // Salve a escolha do usuário no SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("user_sex", selectedSex)
            editor.apply()
        }
    }
}
