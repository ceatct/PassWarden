package com.difl.passwarden

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.difl.passwarden.databinding.ActivityGenerateBinding
import java.util.*


@SuppressLint("StaticFieldLeak")
lateinit var bindinggen : ActivityGenerateBinding

class Generate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindinggen = ActivityGenerateBinding.inflate(layoutInflater)
        setContentView(bindinggen.root)

        bindinggen.bottom.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.home -> {
                    val randomIntent = Intent(this, MainActivity::class.java)
                    startActivity(randomIntent)}

                R.id.pas -> {
                    val randomIntent = Intent(this, Passwords::class.java)
                    startActivity(randomIntent)}

                R.id.gen -> {}
            }
            true
        }

        bindinggen.textView8.setOnClickListener{
            val n = 10
            bindinggen.password.text = getRandPassword(n)
        }

        bindinggen.password.setOnClickListener{
            val clipboard: ClipboardManager =
                this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("", bindinggen.password.getText().toString())
            clipboard.setPrimaryClip(clip)

            val msg = resources.getString(R.string.copied)
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    fun getRandPassword(n: Int): String{

        val characterSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

        val random = Random(System.nanoTime())
        val password = StringBuilder()

        for (i in 0 until n)
        {
            val rIndex = random.nextInt(characterSet.length)
            password.append(characterSet[rIndex])
        }

        return password.toString()
    }
}