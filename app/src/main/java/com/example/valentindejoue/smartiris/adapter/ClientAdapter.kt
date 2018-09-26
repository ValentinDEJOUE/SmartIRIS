package com.example.valentindejoue.smartiris.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.valentindejoue.smartiris.R
import com.example.valentindejoue.smartiris.models.Client
import kotlinx.android.synthetic.main.client_list_item.view.*

class ClientAdapter(val clientList: MutableList<Client> = arrayListOf(), val clickListener: (Client) -> Unit) : RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ClientViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.client_list_item, p0, false)
        return ClientViewHolder(v)
    }

    override fun getItemCount(): Int {
        return clientList.size
    }

    override fun onBindViewHolder(p0: ClientViewHolder, p1: Int) {
        p0.itemView.tvNom.text = clientList[p1].Nom
        p0.itemView.tvAdresse.text = clientList[p1].Adresse
        p0.bind(clientList[p1], clickListener)
    }

    class ClientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(unClient: Client, clickListener: (Client) -> Unit) {
            itemView.tvNom.text = unClient.Nom
            itemView.tvAdresse.text = unClient.Adresse
            itemView.setOnClickListener { clickListener(unClient) }
        }
    }
}

