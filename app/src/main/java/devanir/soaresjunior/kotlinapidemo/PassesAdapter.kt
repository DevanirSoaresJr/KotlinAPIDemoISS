package devanir.soaresjunior.kotlinapidemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_iss_pass.view.*

class PassesAdapter : RecyclerView.Adapter<PassesAdapter.PassViewHolder>() {

    private val data = ArrayList<PassTime>()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PassViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_iss_pass, parent, false)
        return PassViewHolder(view)

    }
    fun setData(passes: List<PassTime>){
        data.clear()
        data.addAll(passes)
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(viewholder: PassViewHolder, position: Int) {
        viewholder.tvDuration.text = data[position].duration.toString()
        viewholder.tvRiseTime.text =  data[position].risetime.toString()
    }

    class PassViewHolder(private val myView: View) : RecyclerView.ViewHolder(myView){
         val tvDuration: TextView
         val tvRiseTime: TextView = myView.findViewById(R.id.tvRiseTime)

        init {
            tvDuration = myView.findViewById(R.id.tvDuration)
        }
    }
}