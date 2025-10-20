package com.example.trabalho_unidade1_dispositivos_moveis

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.basquete.MainActivity as BasqueteActivity
import com.example.calculadora_app.MainActivity as CalculadoraActivity
import com.example.pomodoroapp.MainActivity as PomodoroActivity

class MainActivity : AppCompatActivity() {

    // tag da classe dos logs.
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppLogger.i(TAG, "onCreate: Activity está sendo criada.")

        applySavedTheme()

        setContentView(R.layout.activity_main)

        val iconApp1: ImageView = findViewById(R.id.icon_app1)
        val iconApp2: ImageView = findViewById(R.id.icon_app2)
        val iconApp3: ImageView = findViewById(R.id.icon_app3)
        val themeIcon: ImageView = findViewById(R.id.theme_icon)

        iconApp1.setOnClickListener {
            AppLogger.d(TAG, "Ícone do Basquete clicado. Iniciando BasqueteActivity...")
            val intent = Intent(this, BasqueteActivity::class.java)
            startActivity(intent)
        }
        iconApp2.setOnClickListener {
            AppLogger.d(TAG, "Ícone da Calculadora clicado. Iniciando CalculadoraActivity...")
            val intent = Intent(this, CalculadoraActivity::class.java)
            startActivity(intent)
        }
        iconApp3.setOnClickListener {
            AppLogger.d(TAG, "Ícone do Pomodoro clicado. Iniciando PomodoroActivity...")
            val intent = Intent(this, PomodoroActivity::class.java)
            startActivity(intent)
        }

        setupThemeToggle(themeIcon)
    }

    private fun applySavedTheme() {
        val sharedPreferences = getSharedPreferences("ThemePrefs", Context.MODE_PRIVATE)
        val isNightMode = sharedPreferences.getBoolean("isNightMode", false)

        AppLogger.d(TAG, "applySavedTheme: Aplicando tema salvo. Modo Noturno = $isNightMode")

        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun setupThemeToggle(themeIcon: ImageView) {
        themeIcon.setOnClickListener {
            AppLogger.d(TAG, "Ícone de tema clicado.")
            val currentNightMode = resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK
            val isNightMode: Boolean

            if (currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
                AppLogger.i(TAG, "Mudando para o Modo Claro (Day Mode).")
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                isNightMode = false
            } else {
                AppLogger.i(TAG, "Mudando para o Modo Escuro (Night Mode).")
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                isNightMode = true
            }

            val sharedPreferences = getSharedPreferences("ThemePrefs", Context.MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                putBoolean("isNightMode", isNightMode)
                apply()
                AppLogger.d(TAG, "Novo estado do tema salvo: isNightMode = $isNightMode")
            }
        }
    }
}
