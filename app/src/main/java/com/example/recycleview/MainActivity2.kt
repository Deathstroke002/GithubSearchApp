package com.example.recycleview

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null

    // private var adapter: RecyclerView.Adapter<RecyclerAdapter.ReposViewHolder>? = null
    private var resultBody: List<Repos>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        layoutManager = LinearLayoutManager(this)
        val message= intent.getStringExtra(EXTRA_MESSAGE).toString()
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        val reposApi = GithubCall.getInstance().create(GithubInterface::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            val result = message?.let { reposApi.getRepos(it) }
            if (result != null) {
                resultBody = result.body()
            }
            else{
                var defaultText: TextView = findViewById(R.id.defaultText)
                defaultText.text = "Github Account Does Not Exist"
            }
            if (result != null) {
                runOnUiThread {
                    var adapter = resultBody?.let { RecyclerAdapter(it) }
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity2)
                    adapter?.setOnItemClickListener(object : RecyclerAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            Toast.makeText(
                                this@MainActivity2,
                                "Opening item no $position",
                                Toast.LENGTH_SHORT
                            ).show()
                            val gson = Gson()
                            val message = resultBody?.get(position)?.full_name.toString()
                            val messaget = resultBody?.get(position)?.description.toString()
                            val intent = Intent(this@MainActivity2, MainActivity3::class.java)
                            intent.putExtra("message",message)
                            intent.putExtra("messaget",messaget)
                            Log.d("MYACTIVITY543", message)
                            startActivity(intent)
//
                        }
                    })
                }
            }



        }
    }
}