package com.example.voluntrix_app

import activity.MainActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity(){
    private lateinit var btnLogin:Button
    private lateinit var client: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin=findViewById(R.id.btnLogin)
        val  options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        client=GoogleSignIn.getClient(this,options)

        btnLogin.setOnClickListener {
            val intent=client.signInIntent
            startActivityForResult(intent,10001)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==10001){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken,null)
            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener{task->
                    if(task.isSuccessful){

//                        val i  = Intent(this,OnBoardingActivity::class.java)
//                        startActivity(i)

                        // Inside your MainActivity or SplashScreen activity, where you decide which activity to launch first
                        val isFirstLaunch = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                            .getBoolean("isFirstLaunch", true)

                        if (isFirstLaunch) {
                            // The app is launched for the first time, so start the OnboardingActivity
                            startActivity(Intent(this, OnBoardingActivity::class.java))

                            // Mark that the app has been launched before
                            val editor = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).edit()
                            editor.putBoolean("isFirstLaunch", false)
                            editor.apply()
                        } else {
                            // It's not the first launch, so start your main app activity or login screen
                            startActivity(Intent(this, MainActivity::class.java))
                        }
                        finish()


                    }else{
                        Toast.makeText(this,task.exception?.message,Toast.LENGTH_SHORT).show()
                    }

                }
        }
    }

    override fun onStart() {
        super.onStart()
        if(FirebaseAuth.getInstance().currentUser != null){
            val i  = Intent(this,OnBoardingActivity::class.java)
            startActivity(i)
        }
    }

}

