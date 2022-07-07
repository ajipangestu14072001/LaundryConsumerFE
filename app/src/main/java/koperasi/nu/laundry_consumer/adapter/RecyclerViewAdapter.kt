package koperasi.nu.laundry_consumer.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import koperasi.nu.laundry_consumer.R
import koperasi.nu.laundry_consumer.callback.FetchRecyclerViewItems
import koperasi.nu.laundry_consumer.model.Layanan
import koperasi.nu.laundry_consumer.model.Payment


class RecyclerViewAdapter(
    private val courseDataArrayList: List<Layanan>,
    private val mcontext: Context,
) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
    var listener: FetchRecyclerViewItems? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_product, parent, false)
        return RecyclerViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val modal = courseDataArrayList[position]
        var itemCount = 0
        var totalCount: Double
        holder.title.text = modal.namaLayanan
        holder.harga.text = modal.harga.toString()
        holder.cardItems.setOnClickListener {
            totalCount = (modal.harga * itemCount);
            listener?.onItemClicked(it, totalCount, modal)

        }
        holder.imageAdd1.setOnClickListener {
            itemCount += 1
            holder.tvPriceKaos.text = itemCount.toString()
            totalCount = (modal.harga * itemCount);
            listener?.onItemClicked(it, totalCount, modal)
        }
        holder.imageMinus1.setOnClickListener {
            if (itemCount > 0) {
                itemCount -= 1;
                holder.tvPriceKaos.text = itemCount.toString()
                totalCount = (modal.harga * itemCount)
                listener?.onItemClicked(it, totalCount, modal)
            }
        }
    }

    override fun getItemCount(): Int {
        return courseDataArrayList.size
    }

    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tvKaos)
        val harga: TextView = itemView.findViewById(R.id.tvKaosHarga)
        val cardItems: CardView = itemView.findViewById(R.id.cardRoot)
        val imageAdd1: ImageView = itemView.findViewById(R.id.imageAdd1)
        val tvPriceKaos: TextView = itemView.findViewById(R.id.tvPriceKaos)
        val imageMinus1: ImageView = itemView.findViewById(R.id.imageMinus1)

    }


}
