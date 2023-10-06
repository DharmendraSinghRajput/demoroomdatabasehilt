package com.example.demoroomdatabasehilt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoroomdatabasehilt.databinding.RowUserBinding
import com.example.demoroomdatabasehilt.model.UserModel

class UserAdapter( private val onClickListener: (UserModel) -> Unit): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var items = ArrayList<UserModel>()
     lateinit var rowUserBinding:RowUserBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        rowUserBinding =RowUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  UserViewHolder(rowUserBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(position)
        rowUserBinding.textUserName.text=items[position].name
        rowUserBinding.imgProfile.setImageBitmap(items[position].image)


    }

    override fun getItemCount(): Int {
        return  items.size
    }
  inner  class UserViewHolder(rowUserBinding: RowUserBinding) :RecyclerView.ViewHolder(rowUserBinding.root) {


        fun bind(position: Int) {

            rowUserBinding.data =items[position]
            rowUserBinding.root.setOnClickListener {
                onClickListener(items[position])
            }


        }


    }
    fun addItems(list: ArrayList<UserModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }




}