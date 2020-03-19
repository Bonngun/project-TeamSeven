package com.example.project

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_login.*
import org.json.JSONObject


class login : Fragment() {
    // TODO: Rename and change types of parameters
    var username:EditText ?= null
    var password: EditText ?= null

    var user : FirebaseUser? = null
    lateinit var facebookSignInButton : LoginButton
    var callbackManager : CallbackManager? = null
    // Firebase Auth Object.
    var firebaseAuth: FirebaseAuth? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        callbackManager!!.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FacebookSdk.sdkInitialize(FacebookSdk.getApplicationContext())
        AppEventsLogger.activateApp(activity!!.baseContext)

    }

    private fun handleFacebookAccessToken(token : AccessToken) {


        Log.d(ContentValues.TAG, "handleFacebookAccessToken:" + token)
        val credential = FacebookAuthProvider.getCredential(token.token)
        firebaseAuth!!.signInWithCredential(credential).addOnCompleteListener { task ->
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "signInWithCredential:success")

                    user = firebaseAuth!!.currentUser
                val menu = menu().newInstance(user!!.photoUrl.toString(),user!!.displayName.toString())

                    val fm =fragmentManager
                    val transaction : FragmentTransaction = fm!!.beginTransaction()
                    transaction.replace(R.id.login_button,menu,"menu")
                    transaction.addToBackStack("menu")
                    transaction.commit()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view:View = inflater.inflate(R.layout.fragment_login, container, false)
        val login_button:Button = view.findViewById(R.id.button_login)

        login_button.setOnClickListener{
            this.username = view.findViewById(R.id.input_username)
            this.password = view.findViewById(R.id.input_username)
            if(this.checklogin()){
                val menu = menu()
                val fm =fragmentManager
                val transaction : FragmentTransaction = fm!!.beginTransaction()
                transaction.replace(R.id.login_button,menu,"menu")
                transaction.addToBackStack("menu")
                transaction.commit()

            }
        }

        callbackManager = CallbackManager.Factory.create()
        facebookSignInButton  = view.findViewById(R.id.login_button) as LoginButton
        firebaseAuth = FirebaseAuth.getInstance()
        facebookSignInButton.setReadPermissions("email")

        // If using in a fragment
        facebookSignInButton.setFragment(this)

        val token: AccessToken?
        token = AccessToken.getCurrentAccessToken()

        if (token != null) { //Means user is not logged in
            LoginManager.getInstance().logOut()
        }

        // Callback registration
        facebookSignInButton.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) { // App code

                handleFacebookAccessToken(loginResult!!.accessToken)

            }
            override fun onCancel() { // App code
            }
            override fun onError(exception: FacebookException) { // App code
            }
        })

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) { // App code

                    handleFacebookAccessToken(loginResult!!.accessToken)

                }

                override fun onCancel() { // App code
                }

                override fun onError(exception: FacebookException) { // App code
                }
            })
        return view
    }

    fun checklogin():Boolean{

        val jsonString : String = loadJsonFromAsset("login.json", activity!!.baseContext).toString()
        val json = JSONObject(jsonString)
        val jsonArray = json.getJSONArray("login")

        for ( position in 0 until jsonArray.length()){

            var id:String = jsonArray.getJSONObject(position).getString("userid").toString()
            var pass:String = jsonArray.getJSONObject(position).getString("password").toString()

            if( id.equals(this.username?.text.toString())){
                if( pass.equals(this.password?.text.toString())){
                        Toast.makeText(context,"Login Success", Toast.LENGTH_LONG).show()
                        return true
                    }
            }
        }
        Toast.makeText(context,"Please Check Your Username or Password!!!", Toast.LENGTH_LONG).show()
        return false

    }

    private fun loadJsonFromAsset(filename: String, context: Context): String? {
        val json: String?

        try {
            val inputStream = context.assets.open(filename)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: java.io.IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }

    //
}
