import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.voluntrix_app.R
//import model.Event
import com.example.voluntrix_app.Event


class EventAdapter(private val events: MutableList<Event>) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imgRecyclerRowItem)
        val titleTextView: TextView = itemView.findViewById(R.id.txtRecyclerRowItem)
        val descriptionTextView: TextView = itemView.findViewById(R.id.txtSubRecyclerRowItem)
        val anotherTextView: TextView = itemView.findViewById(R.id.txtPriceRecyclerRowItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_dashboard_single_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = events[position]

        // Set event data to the views
        holder.imageView.setImageResource(R.drawable.birthday_event) // Replace with your image resource
        holder.titleTextView.text = event.title
        holder.descriptionTextView.text = event.description
        holder.anotherTextView.text = "Additional Text" // Set your text here
    }

    override fun getItemCount(): Int {
        return events.size
    }
}
