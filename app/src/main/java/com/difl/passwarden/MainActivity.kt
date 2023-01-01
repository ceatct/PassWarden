package com.difl.passwarden

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.difl.passwarden.Database.MyDbManager
import com.difl.passwarden.databinding.ActivityMainBinding

@SuppressLint("StaticFieldLeak")
lateinit var binding : ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val myDbManager = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottom.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.home -> {}

                R.id.pas -> {
                    val randomIntent = Intent(this, Passwords::class.java)
                    startActivity(randomIntent)}

                R.id.gen -> {
                    val randomIntent = Intent(this, Generate::class.java)
                    startActivity(randomIntent)}
            }
            true
        }

        binding.textView4.setOnClickListener{

        if(binding.site.text.toString().isEmpty() and binding.name.text.toString().isEmpty()
            and binding.password.text.toString().isEmpty()){
            val msg = resources.getString(R.string.fill)
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        else {
                myDbManager.openDB()
                myDbManager.insert(binding.site.text.toString(), binding.name.text.toString(), binding.password.text.toString())
                val msg = resources.getString(R.string.saved)
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

}