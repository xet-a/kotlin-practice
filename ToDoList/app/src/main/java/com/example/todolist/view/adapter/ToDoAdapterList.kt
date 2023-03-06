package com.example.todolist.view.adapter

class ToDoAdapterList {

}/*: ListAdapter<ToDoModel, ToDoAdapter.ToDoViewHolder>(diffUtil) {
    private var todoItems = emptyList<ToDoModel>()

    inner class ToDoViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(todoModel: ToDoModel) {
            binding.todoTitle.text = todoModel.title
            binding.todoDescription.text = todoModel.description
            binding.todoCreatedDate.text = todoModel.createdDate.toDateString("yyyy.MM.dd HH:mm")
        }
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return ToDoViewHolder(ItemTodoBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        Log.e("position: ", position.toString())
        holder.bind(todoItems[position])
        holder.bind(todoItems[position])
    }

    companion object diffUtil: DiffUtil.ItemCallback<ToDoModel>() {
        override fun areContentsTheSame(oldItem: ToDoModel, newItem: ToDoModel): Boolean {
            return oldItem.equals(newItem)
        }

        override fun areItemsTheSame(oldItem: ToDoModel, newItem: ToDoModel): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

fun Long.toDateString(format: String): String {
    val simpleDateFormat = SimpleDateFormat(format)
    return simpleDateFormat.format((Date(this)))
}*/