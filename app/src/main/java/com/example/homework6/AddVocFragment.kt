package com.example.homework6

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_add_voc.*
import java.io.PrintStream

class AddVocFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_voc, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addbtn.setOnClickListener {
            val word =word.text.toString()
            val meaning =mean.text.toString()
            val output = PrintStream(getActivity()?.openFileOutput("words.txt", Context.MODE_APPEND))
            output.println(word)
            output.println(meaning)
            output.close()
            Toast.makeText(getActivity(),"단어 : "+word+"\n뜻 : "+meaning+" 이 추가되었습니다!",Toast.LENGTH_SHORT).show()
            getActivity()?.finish()
        }
        cancelbtn.setOnClickListener {
            getActivity()?.finish()
        }

    }
}
