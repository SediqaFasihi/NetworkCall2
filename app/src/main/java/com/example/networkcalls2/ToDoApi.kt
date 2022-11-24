package com.example.networkcalls2

import retrofit2.Response
import retrofit2.http.GET

interface ToDoApi {
    @GET("/todos")
    suspend fun getTodos(): Response<List<ToDo>>

}