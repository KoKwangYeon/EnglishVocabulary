package com.example.homework6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.fragment_vocabulary.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {

        button.setOnClickListener{
            val i = Intent(this,SecondActivity::class.java)
            i.putExtra("btnNum","1")
            startActivity(i)
            overridePendingTransition(0,0)
        }
        button2.setOnClickListener {
            val i = Intent(this,SecondActivity::class.java)
            i.putExtra("btnNum","2")
            startActivity(i)
            overridePendingTransition(0,0)
        }
        button3.setOnClickListener {
            val i = Intent(this,SecondActivity::class.java)
            i.putExtra("btnNum","3")
            startActivity(i)
            overridePendingTransition(0,0)
        }




    }



}
