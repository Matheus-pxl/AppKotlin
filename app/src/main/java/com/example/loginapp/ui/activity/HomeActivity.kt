package com.example.loginapp.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.databinding.ActivityHomeBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setTitle("Home")

        binding.buttonTest.setOnClickListener{
            val URL : String= ""
            if(URL.isNotEmpty()){
                val client = OkHttpClient()
                val request = Request.Builder().url(URL).build()
                client.newCall(request).enqueue(object : Callback{
                    override fun onFailure(call: Call, e: IOException) {
                        e.printStackTrace()
                    }

                    override fun onResponse(call: Call, response: Response) {
                        Log.i("response","Received response from server")
                        response.use{
                            if(!response.isSuccessful){
                                Log.e("HTTP Error","Something didn't load, or wasn't successful")

                            }else{
                                val body = response?.body?.string()
                                binding.urlDumpText.text = body
                            }
                        }
                    }

                })
            }
        }
    }
}
