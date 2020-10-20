package com.example.testtabapp.Activity

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtabapp.*
import com.example.testtabapp.Adapter.RecyclerViewAdapter
import com.example.testtabapp.DeleteUser.ResponseDelete
import com.example.testtabapp.RoomDatabase.UserViewModel
import com.example.testtabapp.Services.DeleteUserService
import com.example.testtabapp.Services.GetUserService
import com.example.testtabapp.UserInfo.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnMenuItemClick {
    val allUsers: ArrayList<String> = ArrayList()
    val BaseUrl = "http://gorest.co.in/"
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //Using Retrofit to build and call Apis.
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(GetUserService::class.java)
        val call = service.getUser()
        call.enqueue(object : Callback<Users> {

            override fun onFailure(call: Call<Users>, t: Throwable?) {
                Log.d("Body", "is" + t?.message.toString())
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Users>?,
                response: Response<Users>?
            ) {
                if (response != null) {
                    val body = response.body()
                    for (i in body.data?.indices!!) {
                        allUsers.add(body.data?.get(i)?.name.toString())
                    }
                }

                //Goes to recyclerview and displays list of Users.
                val layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
                recyclerView.layoutManager = layoutManager
                val adapter = RecyclerViewAdapter(this@MainActivity, allUsers)
                recyclerView.adapter = adapter
            }
        })
    }

    //Menu for deleting a user.
    override fun onMenuClick(pos: Int) {
        AlertDialog.Builder(this@MainActivity)
            .setTitle("Select Option")
            .setItems(
                arrayOf("Delete")
            ) { dialogInterface, i ->
                when (i) {
                    0 -> {

                        val alert: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                        alert.setTitle("Delete Alarm")
                        alert.setMessage("Are you sure, you want to delete Alarm?")
                            .setPositiveButton(
                                "DELETE"
                            ) { dialog, which ->
                                val retrofit = Retrofit.Builder()
                                    .baseUrl(BaseUrl)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build()
                                val service = retrofit.create(DeleteUserService::class.java)
                                //val int = allUsers[pos].get(pos)
                                val call = service.deleteUser(24, "df770ba95557f63ed585a2d41d172e5a3e04053d1b6b32119f598c0fe47a14bc")

                                call.enqueue(object : Callback<ResponseDelete> {

                                    override fun onFailure(call: Call<ResponseDelete>, t: Throwable?) {
                                        Log.d("Body", "is" + t?.message.toString())
                                        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                                    }
                                    override fun onResponse(
                                        call: Call<ResponseDelete>?,
                                        response: Response<ResponseDelete>?
                                    ) {
                                        if (response != null) {
                                            val body = response.body()
                                        }
                                    }
                                })

                            }.setNegativeButton("CANCEL", null)
                        val alert1: AlertDialog = alert.create()
                        alert1.show()
                    }
                }
            }.show()
    }
}
