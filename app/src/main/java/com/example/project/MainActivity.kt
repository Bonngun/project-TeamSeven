package com.example.project

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class MainActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
//        transaction.replace(R.id.contentContainer,fisrtpage,"fisrtpage")
//        transaction.addToBackStack("fisrtpage")


        //transaction.replace(R.id.login_button, authen,"fragment_authen")
        //transaction.addToBackStack("fragment_authen")

        val fisrtpage = firstpage()
        val login = login()
        val manager = supportFragmentManager;
        val transaction = manager.beginTransaction();

        transaction.replace(R.id.login_button, login,"fragment_login")
        transaction.addToBackStack("fragment_login")
        transaction.commit()
        debugHashKey()

    }


private fun debugHashKey() {
    try {
        val info = packageManager.getPackageInfo(
            "com.example.facebook_authen",
            PackageManager.GET_SIGNATURES
        )
        for (signature in info.signatures) {
            val md: MessageDigest = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            Log.d("KeyHash:", Base64.getEncoder().encodeToString(md.digest()))
        }
    } catch (e: PackageManager.NameNotFoundException) {
    } catch (e: NoSuchAlgorithmException) {
    }
}

//    override fun onBackPressed() {
//
//        val manager = supportFragmentManager.findFragmentById(R.id.login_button)
//
//        if (manager is login ) {
//            finish()
//        }else if(manager is  menu){
//            finish()
//        }
//        else{
//            super.onBackPressed();
//        }
//
//    }
}
