package com.example.homework6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        init()
    }

    private fun init() {
        val num = intent.getStringExtra("btnNum").toString()
        when(num){
            "1"->{
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right)
                    .replace(R.id.frame,VocabularyFragment())
                    .commit()
            }
            "2"->{
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right)
                    .replace(R.id.frame,AddVocFragment())
                    .commit()
            }
            "3"->{
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right)
                    .replace(R.id.frame,VocQuizFragment())
                    .commit()
            }
        }
    }

}

