package com.example.homework6

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_vocabulary.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class VocabularyFragment : Fragment() {

    var words = mutableMapOf<String,String>()
    var array = ArrayList<String>()
    lateinit var adapter :MyAdapter
    lateinit var tts: TextToSpeech
    var isTtsReady = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vocabulary, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tts = TextToSpeech(getActivity(), TextToSpeech.OnInitListener {
            isTtsReady = true
            tts.language=Locale.US
        })

        readFile()
        recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL,false)
        adapter = MyAdapter(array,words)

        adapter.itemClickListener = object : MyAdapter.OnItemClickListener {
            override fun OnItemClick(
                holder: MyAdapter.MyViewHolder,
                view: View,
                data: String,
                position: Int
            ) {
                if(holder.textView2.visibility == View.VISIBLE){
                    holder.textView2.visibility = View.GONE
                }else {
                    holder.textView2.visibility = View.VISIBLE
                    tts.speak(data,TextToSpeech.QUEUE_ADD,null,null)

                }
            }
        }
        recyclerView.adapter = adapter
    }
    fun readFile(){
        var scan = Scanner(getActivity()?.openFileInput("words.txt"))
        while(scan.hasNextLine()){
            val word = scan.nextLine()
            val meaning = scan.nextLine()
            words[word] = meaning
            array.add(word)
        }
        scan.close()
    }

}
