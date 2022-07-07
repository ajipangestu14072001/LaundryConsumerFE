package koperasi.nu.laundry_consumer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import koperasi.nu.laundry_consumer.R
import koperasi.nu.laundry_consumer.model.Payment


class PaymentAdapter(artists: List<Payment>, context: Context) :
    RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {
    private val artists: List<Payment>
    private var checkedRadioButton: CompoundButton? = null


    private val context: Context
    override fun getItemCount(): Int {
        return artists.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PaymentViewHolder {
        return PaymentViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_container, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val artist: Payment = artists[position]

        holder.picLogo.setImageResource(artist.photo)
        holder.textName.text = artist.name
        holder.radio.setOnCheckedChangeListener(checkedChangeListener)
        if (holder.radio.isChecked) checkedRadioButton =
            holder.radio
    }
    private val checkedChangeListener = CompoundButton.OnCheckedChangeListener {
            compoundButton, isChecked ->
        checkedRadioButton?.apply { setChecked(!isChecked) }
        checkedRadioButton = compoundButton.apply { setChecked(isChecked) }


    }

    class PaymentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var picLogo: ImageView
        var textName: TextView
        var radio: RadioButton
        var layoutRootContainer: LinearLayout


        init {
            picLogo = itemView.findViewById(R.id.picLogo)
            textName = itemView.findViewById(R.id.textName)
            radio = itemView.findViewById(R.id.rbButton)
            layoutRootContainer = itemView.findViewById(R.id.layoutRootContainer)
        }
    }

    init {
        this.artists = artists
        this.context = context
    }
}