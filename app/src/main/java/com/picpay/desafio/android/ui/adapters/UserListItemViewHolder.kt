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
}