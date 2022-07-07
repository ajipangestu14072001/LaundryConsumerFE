package koperasi.nu.laundry_consumer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import koperasi.nu.laundry_consumer.R
import koperasi.nu.laundry_consumer.helper.FunctionHelper
import koperasi.nu.laundry_consumer.model.Transaksi


class HistoryAdapter(
    context: Context,
    modelInputList: MutableList<Transaksi>,
    adapterCallback: HistoryAdapterCallback
) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    var modelInputList: MutableList<Transaksi>
    var mContext: Context
    var mAdapterCallback: HistoryAdapterCallback
    fun setDataAdapter(items: List<Transaksi>?) {
        modelInputList.clear()
        modelInputList.addAll(items!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: Transaksi = modelInputList[position]
        holder.tvTitle.text = data.idTransaksi
        holder.tvDate.text = data.tanggalMasuk
        holder.tvItems.text = data.jumlahBarang.toString() + " Items"
        holder.tvPrice.text = data.totalHarga.toString()
    }

    override fun getItemCount(): Int {
        return modelInputList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var tvDate: TextView
        var tvItems: TextView
        var tvPrice: TextView
        var imageDelete: ImageView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvDate = itemView.findViewById(R.id.tvDate)
            tvItems = itemView.findViewById(R.id.tvItems)
            tvPrice = itemView.findViewById(R.id.tvPrice)
            imageDelete = itemView.findViewById(R.id.imageDelete)
            imageDelete.setOnClickListener { view ->
                val modelLaundry: Transaksi = modelInputList[adapterPosition]
                mAdapterCallback.onDelete(modelLaundry)
            }
        }
    }

    interface HistoryAdapterCallback {
        fun onDelete(modelLaundry: Transaksi?)
    }

    init {
        mContext = context
        this.modelInputList = modelInputList
        mAdapterCallback = adapterCallback
    }
}