package com.example.testtabapp.Activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testtabapp.Services.GetUserService
import com.example.testtabapp.R
import com.example.testtabapp.UserInfo.Users
import kotlinx.android.synthetic.main.activity_second.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SecondActivity : AppCompatActivity(){

    private var string : String? = null
    val BaseUrl = "http://gorest.co.in/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        string =intent.getStringExtra("KEY")
        Log.d("Body", "is$string")

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(GetUserService::class.java)
        val call = service.getUser()
        call.enqueue(object : Callback<Users> {

            override fun onFailure(call: Call<Users>, t: Throwable?) {
                Log.d("Body", "is" + t?.message.toString())
                Toast.makeText(this@SecondActivity,"Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Users>?,
                response: Response<Users>?
            ) {
                if (response != null) {
                    val body = response.body()
                    for (i in body.data?.indices!!) {
                        if (body.data?.get(i)?.name == string){
                            val name = string
                            val email = body.data?.get(i)?.email
                            val gender = body.data?.get(i)?.gender
                            val status = body.data?.get(i)?.status
                            val createdAt = body.data?.get(i)?.createdAt

                            tv_name.text = name
                            tv_email.text = email
                            tv_gender.text = gender
                            tv_status.text = status
                            tv_createdAt.text = createdAt
                        }
                    }
                }
            }
        })
    }
}