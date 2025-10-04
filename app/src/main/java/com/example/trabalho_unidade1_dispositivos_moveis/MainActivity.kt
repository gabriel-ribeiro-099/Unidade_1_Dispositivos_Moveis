package com.example.trabalho_unidade1_dispositivos_moveis

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

import com.example.basquete.MainActivity as BasqueteActivity
import com.example.calculadora_app.MainActivity as CalculadoraActivity
import com.example.pomodoroapp.MainActivity as PomodoroActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iconApp1: ImageView = findViewById(R.id.icon_app1)
        val iconApp2: ImageView = findViewById(R.id.icon_app2)
        val iconApp3: ImageView = findViewById(R.id.icon_app3)

        iconApp1.setOnClickListener {
            val intent = Intent(this, BasqueteActivity::class.java)
            startActivity(intent)
        }

        iconApp2.setOnClickListener {
            val intent = Intent(this, CalculadoraActivity::class.java)
            startActivity(intent)
        }

        iconApp3.setOnClickListener {
            val intent = Intent(this, PomodoroActivity::class.java)
            startActivity(intent)
        }
    }
}