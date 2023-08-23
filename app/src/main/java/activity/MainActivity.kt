package activity

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.example.voluntrix_app.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import fragment.AboutAppFragment
import fragment.BookmarksFragment
import fragment.DashboardFragment
import fragment.UserProfileFragment


class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView

    lateinit var btnLogout: Button
    lateinit var userName:TextView
    lateinit var userImg:ImageView

    lateinit var auth: FirebaseAuth

    var previousMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.voluntrix_app.R.layout.activity_main)
        drawerLayout = findViewById(com.example.voluntrix_app.R.id.drawerLayout)
        coordinatorLayout = findViewById(com.example.voluntrix_app.R.id.coordinatorLayout)
        toolbar = findViewById(com.example.voluntrix_app.R.id.toolbar)
        frameLayout = findViewById(com.example.voluntrix_app.R.id.frame)
        navigationView = findViewById(com.example.voluntrix_app.R.id.navigationView)
        setUpToolbar()

        openDashboard()

        val headerView = navigationView.getHeaderView(0)

        btnLogout = headerView.findViewById(com.example.voluntrix_app.R.id.btnLogout)
        userImg=headerView.findViewById(com.example.voluntrix_app.R.id.userImg)
        userName=headerView.findViewById(com.example.voluntrix_app.R.id.userName)

        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account!=null){
            val name = account.displayName
            val email = account.email
            val photo = account.photoUrl
            val displayName="Hey, "+name
            userName.setText(displayName)
            Glide.with(this).load(photo).into(userImg);
        }
        auth = FirebaseAuth.getInstance()
         btnLogout.setOnClickListener {
             auth.signOut()

             val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                 .build()
             val googleSignInClient = GoogleSignIn.getClient(this, gso)
             googleSignInClient.signOut().addOnCompleteListener(this) {
                 // Redirect to the login or home screen as needed
                 // For example, you can navigate back to your login activity
                  startActivity(Intent(this, LoginActivity::class.java))

                 // Finish the current activity to prevent returning to it using the back button
                 finish()
             }

             // Perform the action when the button is clicked
//             val intent = Intent(this@MainActivity, LoginActivity::class.java)
//             startActivity(intent)
//             finish()
         }


        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            com.example.voluntrix_app.R.string.open_drawer,
            com.example.voluntrix_app.R.string.close_drawer
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
                com.example.voluntrix_app.R.id.dashboard -> {
                    openDashboard()
                    drawerLayout.closeDrawers()
                }

                com.example.voluntrix_app.R.id.bookmarks -> {
                    supportFragmentManager.beginTransaction()
                        .replace(com.example.voluntrix_app.R.id.frame, BookmarksFragment())
                        .commit()
                    supportActionBar?.title = "Bookmarks"
                    drawerLayout.closeDrawers()
                }

                com.example.voluntrix_app.R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(com.example.voluntrix_app.R.id.frame, UserProfileFragment())
                        .commit()
                    supportActionBar?.title = "User Profile"
                    drawerLayout.closeDrawers()
                }
                com.example.voluntrix_app.R.id.aboutApp -> {
                    supportFragmentManager.beginTransaction()
                        .replace(com.example.voluntrix_app.R.id.frame, AboutAppFragment())
                        .commit()
                    supportActionBar?.title = "About App"
                    drawerLayout.closeDrawers()
                }
            }
            return@setNavigationItemSelectedListener true
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
            transaction.replace(com.example.voluntrix_app.R.id.frame, fragment)
            transaction.commit()
        supportActionBar?.title = "Dashboard"
        navigationView.setCheckedItem(com.example.voluntrix_app.R.id.dashboard)
    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(com.example.voluntrix_app.R.id.frame)

        when(frag){
            !is DashboardFragment -> openDashboard()
            else -> super.onBackPressed()
        }
    }

}