package fragment

import EventAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.voluntrix_app.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import model.Event

class DashboardFragment : Fragment() {

//    lateinit var recyclerDashboard: RecyclerView
//    lateinit var layoutManager: RecyclerView.LayoutManager
//    lateinit var recyclerAdapter: DashboardRecyclerAdapter

    private lateinit var recyclerView: RecyclerView
    private lateinit var eventAdapter: EventAdapter
//    private val eventsList = mutableListOf<Event>()
    private val eventsList = mutableListOf<com.example.voluntrix_app.Event>()



//    val eventInfoList = arrayListOf<Event>(
//
//        Event("Trade Fair", "Photographer", "Rs500/per Hour", R.drawable.trade_fair),
//        Event("Marriage Event", "Event Manager", "Rs5000/for event", R.drawable.marriage_event),
//        Event("Seminar", "Speaker", "Rs1000/per Hour", R.drawable.seminar__event),
//        Event("Farewell Party", "Professional DJ", "Rs800/per Hour", R.drawable.farewell_party),
//        Event("Company Party", "Decorator", "Rs1000/for event", R.drawable.company_party),
//        Event("Annual Fest", "Photographer, DJ, Decorator", "Rs1000/per Hour for each", R.drawable.annual_fest),
//        Event("Birthday Event", "Entertainer", "Rs500/per Hour", R.drawable.birthday_event),
//        Event("Political Event", "Crew Member", "Rs1500/per Hour", R.drawable.political_event),
//        Event("Farmhouse Party", "Chef", "Rs2500/per Hour", R.drawable.farmhouse_party),
//        Event("Art and Science Exhibition", "Event Manager", "Rs5000/for event", R.drawable.exhibition),
//
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }
    private fun getUserData() {

        val database = FirebaseDatabase.getInstance()
        val eventsRef = database.getReference("events")

        val valueEventListener= object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){


                        val event = userSnapshot.getValue(com.example.voluntrix_app.Event::class.java)
                        eventsList.add(event!!)

                    }

                    recyclerView.adapter = EventAdapter(eventsList)


                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

//        recyclerDashboard = view.findViewById(R.id.recyclerDashboard)
//
//        layoutManager = LinearLayoutManager(activity)
//
//        recyclerAdapter = DashboardRecyclerAdapter(activity as Context, eventInfoList)
//
//        recyclerDashboard.adapter = recyclerAdapter
//
//        recyclerDashboard.layoutManager = layoutManager

        recyclerView = view.findViewById(R.id.recyclerDashboard)
        eventAdapter = EventAdapter(this.eventsList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = eventAdapter

        val database = FirebaseDatabase.getInstance()
        val eventsRef = database.getReference("events")

        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                eventsList.clear() // Clear the list before adding data

                for (snapshot in dataSnapshot.children) {
                    val event = snapshot.getValue(com.example.voluntrix_app.Event::class.java)
                    if (event != null) {
                        eventsList.add(event)
                    }
                }

                // Notify the adapter that the data has changed
                eventAdapter.notifyDataSetChanged()
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Handle the error
            }
        }

        // Attach the ValueEventListener to your events reference
        eventsRef.addValueEventListener(valueEventListener)


//        recyclerView = view.findViewById(R.id.recyclerDashboard)
////        eventAdapter = DashboardRecyclerAdapter(eventsList)
//        eventAdapter=DashboardRecyclerAdapter(eventsList)
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        recyclerView.adapter = eventAdapter

        return view
    }

}