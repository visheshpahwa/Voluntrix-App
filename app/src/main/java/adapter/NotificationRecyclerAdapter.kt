package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.voluntrix_app.R
import model.Notification

class NotificationRecyclerAdapter(val context: Context, val notificationList: ArrayList<Notification>) : RecyclerView.Adapter<NotificationRecyclerAdapter.NotificationViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_notification_single_row, parent, false)
        return NotificationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notificationList[position]
        holder.txtTitle.text = notification.title
        holder.txtSampleText.text = notification.message
    }

    class NotificationViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtTitle: TextView = view.findViewById(R.id.txtRecyclerNotificationRowItem)
        val txtSampleText: TextView = view.findViewById(R.id.txtSubRecyclerNotificationRowItem)
    }
}