package com.example.networkcalls2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkcalls2.databinding.ActivityMainBinding
import com.example.networkcalls2.databinding.ItemTodoBinding
import com.google.android.material.snackbar.Snackbar
import okio.IOException
import retrofit2.HttpException

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toDoAdopter: ToDoAdipter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()

        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getTodos()
            } catch (e: IOException) {
                Log.e(TAG, "IOExcwption, no internet connection")
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null) {
                toDoAdopter.todos = response.body()!!
            } else {
                Log.e(TAG, "Response not successful");
            }
        }

    }

    private fun setUpRecyclerView() = binding.rvTd.apply {
        toDoAdopter = ToDoAdipter()
        adapter = toDoAdopter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }

}
