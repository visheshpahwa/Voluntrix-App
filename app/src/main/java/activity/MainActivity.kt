package activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import fragment.AboutAppFragment
import fragment.BookmarksFragment
import fragment.DashboardFragment
import com.example.voluntrix_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import fragment.UserProfileFragment
import com.google.android.material.navigation.NavigationView
import fragment.NotificationFragment

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView

    var previousMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frame)
        navigationView = findViewById(R.id.navigationView)
        setUpToolbar()

        openDashboard()



        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {

            if(previousMenuItem != null){
                previousMenuItem?.isChecked = false
            }
            it.isCheckable = true
            it.isChecked = true
            previousMenuItem = it

            when(it.itemId){
                R.id.dashboard -> {
                    openDashboard()
                    drawerLayout.closeDrawers()
                }

                R.id.bookmarks -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, BookmarksFragment())
                        .commit()
                    supportActionBar?.title = "Bookmarks"
                    drawerLayout.closeDrawers()
                }

                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, UserProfileFragment())
                        .commit()
                    supportActionBar?.title = "User Profile"
                    drawerLayout.closeDrawers()
                }
                R.id.aboutApp -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, AboutAppFragment())
                        .commit()
                    supportActionBar?.title = "About App"
                    drawerLayout.closeDrawers()
                }
            }
            return@setNavigationItemSelectedListener true
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, DashboardFragment())
                        .addToBackStack(null)
                        .commit()
                    supportActionBar?.title = "Dashboard"
                    true
                }
                R.id.notification ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, NotificationFragment())
                        .addToBackStack(null)
                        .commit()
                    supportActionBar?.title = "Notification"
                    true
                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, UserProfileFragment())
                        .addToBackStack(null)
                        .commit()
                    supportActionBar?.title = "User Profile"
                    true
                }
                else -> false
            }

        }
    }




    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Voluntrix"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if(id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    fun openDashboard(){
        val fragment = DashboardFragment()
        val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame, fragment)
            transaction.commit()
        supportActionBar?.title = "Dashboard"
        navigationView.setCheckedItem(R.id.dashboard)
    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frame)

        when(frag){
            !is DashboardFragment -> openDashboard()
            else -> super.onBackPressed()
        }
    }

}