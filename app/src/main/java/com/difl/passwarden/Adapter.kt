package com.difl.passwarden

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.difl.passwarden.Database.MyDbManager


class Adapter(list: ArrayList<String>, var context: Context): RecyclerView.Adapter<Adapter.NavigationVewHolder>() {

    var mainList = list
    lateinit var myDbManager : MyDbManager

    class NavigationVewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text:TextView = itemView.findViewById(R.id.text)
        val del:ImageButton = itemView.findViewById(R.id.del)
        fun setData(title: String){
            text.text = title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationVewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NavigationVewHolder(inflater.inflate(R.layout.item, parent, false))
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: NavigationVewHolder, position: Int) {
        holder.setData(mainList.get(position))

        holder.del.setOnClickListener{
            val myDbManager = MyDbManager(context)
            myDbManager.openDB()
            myDbManager.delitem(myDbManager.id().get(position))
            mainList.removeAt(position)
            notifyItemRangeChanged(0, mainList.size)
            notifyItemRemoved(position)
        }

        holder.itemView.setOnClickListener{
            val myDbManager = MyDbManager(context)
            myDbManager.openDB()

            val intent = Intent(context, Pas::class.java)

            intent.putExtra("site", myDbManager.site().get(position))
            intent.putExtra("login", myDbManager.login().get(position))
            intent.putExtra("password", myDbManager.pass().get(position))

            (context as Activity).startActivityForResult(intent, 99)
        }
    }

    override fun getItemCount(): Int {
        return mainList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun upd(list: ArrayList<String>){
        mainList.clear()
        mainList.addAll(list)
        notifyDataSetChanged()
    }

}
