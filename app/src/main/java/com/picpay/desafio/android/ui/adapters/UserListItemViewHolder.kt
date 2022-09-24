package com.picpay.desafio.android.ui.adapters

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding

class UserListItemViewHolder(
    val viewDataBinding: ListItemUserBinding
) : RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.list_item_user
    }

//    fun bind(user: User) {
//        itemView.name.text = user.name
//        itemView.username.text = user.username
//        itemView.progressBar.visibility = View.VISIBLE
//        Picasso.get()
//            .load(user.img)
//            .error(R.drawable.ic_round_account_circle)
//            .into(itemView.picture, object : Callback {
//                override fun onSuccess() {
//                    itemView.progressBar.visibility = View.GONE
//                }
//
//                override fun onError(e: Exception?) {
//                    itemView.progressBar.visibility = View.GONE
//                }
//            })
//    }

}