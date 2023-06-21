package fragment

import adapter.DashboardRecyclerAdapter
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.voluntrix_app.R
import model.Event

class DashboardFragment : Fragment() {

    lateinit var recyclerDashboard: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter: DashboardRecyclerAdapter

    val eventInfoList = arrayListOf<Event>(
        Event("Trade Fair", "Photographer", "Rs500/per Hour", R.drawable.trade_fair),
        Event("Marriage Event", "Event Manager", "Rs5000/for event", R.drawable.marriage_event),
        Event("Seminar", "Speaker", "Rs1000/per Hour", R.drawable.seminar__event),
        Event("Farewell Party", "Professional DJ", "Rs800/per Hour", R.drawable.farewell_party),
        Event("Company Party", "Decorator", "Rs1000/for event", R.drawable.company_party),
        Event("Annual Fest", "Photographer, DJ, Decorator", "Rs1000/per Hour for each", R.drawable.annual_fest),
        Event("Birthday Event", "Entertainer", "Rs500/per Hour", R.drawable.birthday_event),
        Event("Political Event", "Crew Member", "Rs1500/per Hour", R.drawable.political_event),
        Event("Farmhouse Party", "Chef", "Rs2500/per Hour", R.drawable.farmhouse_party),
        Event("Art and Science Exhibition", "Event Manager", "Rs5000/for event", R.drawable.exhibition),

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        recyclerDashboard = view.findViewById(R.id.recyclerDashboard)

        layoutManager = LinearLayoutManager(activity)

        recyclerAdapter = DashboardRecyclerAdapter(activity as Context, eventInfoList)

        recyclerDashboard.adapter = recyclerAdapter

        recyclerDashboard.layoutManager = layoutManager
        return view
    }

}