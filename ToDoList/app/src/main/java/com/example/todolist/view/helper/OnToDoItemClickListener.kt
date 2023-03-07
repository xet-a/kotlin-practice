package com.example.todolist.view.helper

interface OnToDoItemClickListener {
    fun onItemMove(from: Int, to: Int): Boolean
    fun onItemSwipe(position: Int)
}