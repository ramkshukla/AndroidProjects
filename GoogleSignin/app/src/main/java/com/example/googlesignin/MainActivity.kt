package com.example.googlesignin

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.googlesignin.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {
    // ViewBinding
    private lateinit var binding: ActivityMainBinding

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    // const
    private companion object{
        private const val RC_SIGN_IN = 100
        private const val TAG = "GOOGLE_SIGN_IN_TAG"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // configuration the google signIn
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // resolved at build time
            .requestEmail() // we only need email from google account
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        // init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //Google SignIn Button, click to begin google signin
        binding.googleSignInBtn.setOnClickListener{
            // begin google signin
            Log.d(TAG, "onCreate: begin google signIn")
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, RC_SIGN_IN)

        }
    }

    private fun checkUser() {
        //check if the user is logged in or not
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null){
            //if user already logged in
            //start profile activity
            startActivity(Intent(this@MainActivity, ProfileActivity::class.java))
            finish()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //Result returned from launching the intent from GoogleSignInApi.getSignInIntent(...)
        if(requestCode == RC_SIGN_IN){
            Log.d(TAG, "onActivityResult: Google signIn intent result")
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account = accountTask.getResult(ApiException::class.java)
                firebaseAuthWithGoogleAccount(account)
            }
            catch (e:Exception){
                // failed google signin
                Log.d(TAG, "onActivityResult: ${e.message}")
            }
        }
    }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?) {
        Log.d(TAG, "firebaseAuthWithGoogleLogin: begin firebase auth with google account")
        val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener {   authResult ->
                Log.d(TAG,"firebaseAuthWithGoogleAccount: LoggedIn")
                //get logged In User
                val firebaseUser = firebaseAuth.currentUser
                //get user info
                val uid = firebaseUser!!.uid
                val email = firebaseUser.email

                Log.d(TAG, "firebaseAuthWithGoogleAccount: Uid: $uid")
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Uid $email")

                // check if user is new or existing
                if (authResult.additionalUserInfo!!.isNewUser){
                    //user is new  - Account created
                    Log.d(TAG, "firebaseAuthWithAccount: Account created ...\n${email}")
                    Toast.makeText(this@MainActivity, "Accounted created....\n${email}", Toast.LENGTH_SHORT).show()
                }
                else{
                    // existing user - LoggedIn
                    Log.d(TAG, "firebaseAuthWithAccount: Existing user...\n${email}")
                    Toast.makeText(this@MainActivity, "Accounted created....\n${email}", Toast.LENGTH_SHORT).show()
                }

                // start profile activity
                startActivity(Intent(this@MainActivity, ProfileActivity::class.java))
                finish()
            }
            .addOnFailureListener{  e ->
                //login failed
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Login failed due to ${e.message}")
                Toast.makeText(this@MainActivity, "Login failed due to \n${e.message}", Toast.LENGTH_SHORT).show()


            }
    }
}