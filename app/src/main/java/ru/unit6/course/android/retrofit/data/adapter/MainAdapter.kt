package ru.unit6.course.android.retrofit.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.unit6.course.android.retrofit.data.model.User
import ru.unit6.course.android.retrofit.databinding.ItemLayoutBinding

class MainAdapter(private val users: ArrayList<User>)
    : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    var clickListener: PersonClickListener? = null

    interface PersonClickListener {
        fun onPersonClick(id: String)
    }

    class DataViewHolder(
        private val binding: ItemLayoutBinding,
        private val listener: PersonClickListener?)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            itemView.apply {
                binding.textViewUserName.text = user.name
                binding.textViewUserEmail.text = user.email
                Glide.with(binding.imageViewAvatar.context)
                    .load(user.avatar)
                    .into(binding.imageViewAvatar)
                    binding.ticketCard.setOnClickListener {
                        listener?.onPersonClick(user.id)
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(
            inflater,
            parent,
            false)
        return DataViewHolder(binding, clickListener)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addUsers(users: List<User>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }
}