package com.example.homework6

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_voc_quiz.*
import kotlinx.android.synthetic.main.fragment_vocabulary.*
import kotlinx.android.synthetic.main.fragment_vocabulary.recyclerView
import java.util.*
import kotlin.collections.ArrayList

class VocQuizFragment : Fragment() {
    var words = mutableMapOf<String,String>()
    var engWord = ArrayList<String>()
    var korMean = ArrayList<String>()
    var choice = arrayListOf<String>("1","2","3","4","5")
    var vocNum=0
    var answer =0
    var score = 0
    lateinit var adapter :MyAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_voc_quiz, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        readFile()
        if(vocNum <5){
            Toast.makeText(getActivity(),"퀴즈를 하기위한 단어의 갯수가 모자릅니다!",Toast.LENGTH_SHORT).show()
            getActivity()?.finish()
        }else{
            vocNum--
        recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL,false)

        var randNum = (0..vocNum).random()
        var answerNum = (0..4).random()
        var newNum = (0..4).random()
        answer =randNum
        engVoc.text=engWord[randNum]
        choice[answerNum] = korMean[randNum]

        var i = 0
        while(i <5){
            if(i == answerNum){
                i++
                continue
            }
            randNum = (0..vocNum).random()
            var n = 0
            for(j in 0..i){
                if(choice[j] == korMean[i]){
                    n = 1
                    continue
                }
            }
            if(n ==0){
                choice[i] =korMean[randNum]
                i++

            }

        }
        scoreText.text = "S C O R E   :   0"
        adapter = MyAdapter(choice,words)
        recyclerView.adapter = adapter

        adapter.itemClickListener = object:MyAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: MyAdapter.MyViewHolder,
                view: View,
                data: String,
                position: Int
            ) {
                if(data == korMean[answer]){
                    Toast.makeText(getActivity(),"정답입니다.\n"+engWord[answer]+"  "+korMean[answer],
                        Toast.LENGTH_SHORT).show()
                    score++
                    scoreText.text = "S C O R E   :   "+score
                    engVoc.setBackgroundColor(Color.parseColor("#4CAF50"))
                }else{
                    Toast.makeText(getActivity(),"오답입니다\n정답 : "+engWord[answer]+"  "+korMean[answer],
                        Toast.LENGTH_SHORT).show()
                    score--
                    scoreText.text = "S C O R E   :   "+score
                    engVoc.setBackgroundColor(Color.parseColor("#F44336"))
                }

                var randNum = (0..vocNum).random()
                answer =randNum
                engVoc.text=engWord[randNum]

                answerNum = (0..4).random()
                choice[answerNum]=korMean[randNum]

                var i = 0
                while(i <5){
                    if(i == answerNum){
                        i++
                        continue
                    }
                    randNum = (0..vocNum).random()
                    var n = 0
                    for(j in 0..i){
                        if(choice[j] == korMean[i]){
                            n = 1
                            continue
                        }
                    }
                    if(n ==0){
                        choice[i] =korMean[randNum]
                        i++

                    }
                }

                Handler().postDelayed({
                    engVoc.setBackgroundColor(Color.parseColor("#F4E3C4"))

                },500)
                adapter.notifyDataSetChanged()



            }
        }

        }

    }
    fun readFileScan(scan: Scanner){
        while(scan.hasNextLine()){
            val word = scan.nextLine()
            val meaning = scan.nextLine()
            words[word] = meaning
            engWord.add(word)
            korMean.add(meaning)
            vocNum++
        }
        scan.close()
    }
    fun readFile(){
        var scan = Scanner(getActivity()?.openFileInput("words.txt"))
        readFileScan(scan)


    }

}
