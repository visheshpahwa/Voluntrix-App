package fragment

import adapter.NotificationRecyclerAdapter
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.voluntrix_app.R
import model.Notification

class NotificationFragment : Fragment() {

    lateinit var recyclerNotification: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter: NotificationRecyclerAdapter

    val notificationList = arrayListOf<Notification>(
        Notification("New Event near your Location",
            "7 new events found near your location check it out fast to apply for new events"),
        Notification("Host is interested in your Profile",
            "Host is viewed your profile and checked your work and interested to work with you"),
        Notification("Someone sent you Request", "Host is want to connect with you open and accept the request"),
        Notification("Event of your Field",
            "New Events are listed based on your interest open and check it out")
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notification, container, false)
        recyclerNotification = view.findViewById(R.id.recyclerNotification)
        layoutManager = LinearLayoutManager(activity)
        recyclerAdapter = NotificationRecyclerAdapter(activity as Context, notificationList)

        recyclerNotification.adapter = recyclerAdapter

        recyclerNotification.layoutManager = layoutManager

        recyclerNotification.addItemDecoration(
            DividerItemDecoration(
                recyclerNotification.context,(layoutManager as LinearLayoutManager).orientation
            )
        )

        return view
    }

}