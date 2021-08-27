package com.example.twitterapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.twitterapi.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var twitter_data: Data
    private val TAG = "MainActivity"
    var user_details = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding = ActivityMainBinding.bind(view)

        val username = binding.editTextUsername.text.toString()
        val twitterApi: TwitterApi =
            RetrofitBuilder.provideRetrofit().create(TwitterApi::class.java)

        val coroutineScope = CoroutineScope(Dispatchers.IO)

        fun getDataFromServer() {
            val call: Call<Data> = twitterApi.searchUser(username)
            call.enqueue(object : Callback<Data> {
                override fun onResponse(call: Call<Data>, response: Response<Data>) {
                    if(response.isSuccessful){
                        twitter_data = response.body()!!
                        user_details =
                            "id: ${twitter_data.id}\n name: ${twitter_data.name}\n username: $${twitter_data.username}"
                        binding.userDetails.text = user_details
                    }else{
                        binding.userDetails.text = response.code().toString()
                        return
                    }
                }

                override fun onFailure(call: Call<Data>, t: Throwable) {
                    binding.userDetails.text = t.message                }
            })
        }


        binding.searchButton.setOnClickListener {
            Toast.makeText(applicationContext, "clicked", Toast.LENGTH_SHORT).show()
            getDataFromServer()
        }

        binding.userDetails.text = user_details
    }


}