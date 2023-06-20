package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.voluntrix_app.R
import model.Event

class DashboardRecyclerAdapter(val context: Context, val itemList: ArrayList<Event>) : RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder{

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row, parent, false)
        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val event = itemList[position]
        holder.txtEventName.text = event.eventName
        holder.txtEventRequirement.text = event.eventRequirement
        holder.txtPrice.text = event.eventBudget
        holder.imgEvent.setImageResource(event.eventImage)
    }

    class DashboardViewHolder(view:View): RecyclerView.ViewHolder(view){
        val txtEventName: TextView = view.findViewById(R.id.txtRecyclerRowItem)
        val txtEventRequirement: TextView = view.findViewById(R.id.txtSubRecyclerRowItem)
        val imgEvent: ImageView = view.findViewById(R.id.imgRecyclerRowItem)
        val txtPrice: TextView = view.findViewById(R.id.txtPriceRecyclerRowItem)
    }
}