package com.example.networkcalls2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.networkcalls2.databinding.ActivityMainBinding
import com.example.networkcalls2.databinding.ItemTodoBinding

class ToDoAdipter : RecyclerView.Adapter<ToDoAdipter.TodoViewHolder>() {
    class TodoViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root)
    private val diffCallback = object : DiffUtil.ItemCallback<ToDo>(){

        override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
            return oldItem.id ==newItem.id
        }

        override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this,diffCallback)
    var todos: List<ToDo>
        get() = differ.currentList
        set(value) {
        differ.submitList(value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            ItemTodoBinding.inflate(
                (LayoutInflater.from(parent.context))
                , parent, false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todos[position]
            textTitle.text = todo.title
            checkBox.isChecked = todo.completed
        }
    }

    override fun getItemCount() = todos.size


}