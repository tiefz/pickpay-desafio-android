package com.picpay.desafio.android.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.domain.User
import com.picpay.desafio.android.utils.UserListDiffCallback

class UserListAdapter : RecyclerView.Adapter<UserListItemViewHolder>() {

    var users = emptyList<User>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                UserListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        val withDataBinding: ListItemUserBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            UserListItemViewHolder.LAYOUT,
            parent,
            false
        )
        return UserListItemViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.user = users[position]
        }
    }

    override fun getItemCount(): Int = users.size
}