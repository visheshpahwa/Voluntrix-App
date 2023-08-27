package fragment

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.voluntrix_app.LoginActivity
import com.example.voluntrix_app.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth



/**
 * A simple [Fragment] subclass.
 * Use the [UserProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserProfileFragment : Fragment() {

    lateinit var navigationView: NavigationView

    lateinit var userName:TextView
    lateinit var userImg:ImageView
    lateinit var userEmail:TextView
    lateinit var auth: FirebaseAuth
    lateinit var btnLogoutProfile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_user_profile, container, false )

        userImg=view.findViewById(R.id.userImg)
        userEmail=view.findViewById(R.id.userEmail)
        userName=view.findViewById(R.id.userName)
        btnLogoutProfile=view.findViewById(R.id.btnLogoutProfile)


        val account = GoogleSignIn.getLastSignedInAccount(this.requireContext())
        if (account!=null){
            val name = account.displayName
            val email = account.email
            val photo = account.photoUrl
            userName.setText(name)
            userEmail.setText(email)
            Glide.with(this).load(photo).into(userImg);
        }
        auth = FirebaseAuth.getInstance()

        btnLogoutProfile.setOnClickListener {
            showLogoutConfirmationDialog()
        }

        // Inflate the layout for this fragment
        return view
    }

    private fun showLogoutConfirmationDialog() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Logout Confirmation")
        builder.setMessage("Are you sure you want to logout?")
        builder.setPositiveButton("Yes") { dialog: DialogInterface, _: Int ->
            // Perform logout and navigate to the login page
            performLogout()
        }
        builder.setNegativeButton("No") { dialog: DialogInterface, _: Int ->
            // Dismiss the dialog and do nothing
            dialog.dismiss()
        }

// Create and show the dialog
        val alertDialog = builder.create()
        alertDialog.show()


        val dialog = builder.create()
        dialog.show()
    }

    private fun performLogout() {
        auth.signOut()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .build()

        val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        googleSignInClient.signOut().addOnCompleteListener(requireActivity()) {
            // Redirect to the login or home screen as needed
            // For example, you can navigate back to your login activity
            startActivity(Intent(requireContext(), LoginActivity::class.java))

            // Finish the current activity to prevent returning to it using the back button
            requireActivity().finish()
        }

    }
}