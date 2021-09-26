package ru.unit6.course.android.retrofit.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.unit6.course.android.retrofit.data.model.UserDB
import ru.unit6.course.android.retrofit.databinding.ItemLayoutBinding

class DatabaseAdapter(private val users: ArrayList<UserDB>)
    : RecyclerView.Adapter<DatabaseAdapter.DataViewHolder>(){

    var clickListener: PersonClickListener? = null

    interface PersonClickListener {
        fun onPersonClick(id: Int)
    }

    class DataViewHolder(private val binding: ItemLayoutBinding,
                         private val listener: PersonClickListener?
    )
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(user_db: UserDB) {
            with(binding) {
                textViewUserName.text = user_db.name
                textViewUserEmail.text = user_db.email
                Glide.with(imageViewAvatar.context)
                     .load(user_db.avatar)
                     .into(imageViewAvatar)
                ticketCard.setOnClickListener {
                    listener?.onPersonClick(user_db.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
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

    fun addUsers(users: List<UserDB>) {
        this.users.apply {
            addAll(users)
        }
    }
}