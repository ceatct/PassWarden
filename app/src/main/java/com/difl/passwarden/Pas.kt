package com.difl.passwarden

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.difl.passwarden.databinding.ActivityPasBinding

@SuppressLint("StaticFieldLeak")
lateinit var bindingp : ActivityPasBinding

class Pas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingp = ActivityPasBinding.inflate(layoutInflater)
        setContentView(bindingp.root)

        val arguments = intent.extras
        val site = arguments!!["site"].toString()
        val login = arguments!!["login"].toString()
        val password = arguments!!["password"].toString()

        bindingp.tsite.text = "Site: $site"
        bindingp.tlogin.text = "Login: $login"
        bindingp.tpas.text = "Password: $password"

        bindingp.tpas.setOnClickListener{
            val clipboard: ClipboardManager =
                this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("", password)
            clipboard.setPrimaryClip(clip)

            val msg = resources.getString(R.string.copied)
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }
}