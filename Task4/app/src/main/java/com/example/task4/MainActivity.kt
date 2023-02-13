package com.example.task4


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


const val RC_SIGN_IN = 123
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: SignInButton = findViewById(R.id.sign_in_button)
        val tv:TextView = findViewById(R.id.tv_signIn)

        // configuration for the google signin
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail() // we only need email from google account
            .build()
        // Build a GoogleSignInClient with the options specified by gso.
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        btn.visibility = View.VISIBLE
        tv.visibility = View.GONE
        btn.setSize(SignInButton.SIZE_STANDARD)
        btn.setOnClickListener{
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
            Toast.makeText(this, "Button clicked", Toast.LENGTH_SHORT).show()

        }
        val acct = GoogleSignIn.getLastSignedInAccount(this)

        if (acct != null) {
            btn.visibility = View.VISIBLE
            tv.text = ("Logged In as " + acct.displayName)
            tv.visibility = View.VISIBLE
        }
    }
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        val btn: SignInButton = findViewById(R.id.sign_in_button)
        val tv:TextView = findViewById(R.id.tv_signIn)
        try {
            val account = completedTask.getResult(ApiException::class.java)

            btn.visibility = View.GONE
//            tv.text = account.displayName
//            tv.visibility = View.VISIBLE
            intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)

        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            btn.visibility = View.VISIBLE
//            tv.text = ""
//            tv.visibility = View.GONE
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

}