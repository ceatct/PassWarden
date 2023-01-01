package com.difl.passwarden

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.difl.passwarden.Database.MyDbManager
import com.difl.passwarden.databinding.ActivityMainBinding
import com.difl.passwarden.databinding.ActivityPasswordsBinding

@SuppressLint("StaticFieldLeak")
lateinit var bindingpas : ActivityPasswordsBinding
lateinit var recycler: RecyclerView

class Passwords : AppCompatActivity() {

    val myDbManager2 = MyDbManager(this)
    val adapter = Adapter(ArrayList(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingpas = ActivityPasswordsBinding.inflate(layoutInflater)
        setContentView(bindingpas.root)

        myDbManager2.openDB()

        bindingpas.bottom.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.home -> {
                    val randomIntent = Intent(this, MainActivity::class.java)
                    startActivity(randomIntent)}

                R.id.pas -> {}

                R.id.gen -> {
                    val randomIntent = Intent(this, Generate::class.java)
                    startActivity(randomIntent)}
            }
            true
        }

        bindingpas.textView7.setOnClickListener{
            myDbManager2.openDB()
            myDbManager2.delete()
            init()
            fill()
        }

        init()
        fill()
    }

    fun init(){
        bindingpas.recycler.layoutManager = LinearLayoutManager(this)
        bindingpas.recycler.adapter = adapter
    }

    fun fill(){
        myDbManager2.openDB()
        adapter.upd(myDbManager2.site())
    }
}