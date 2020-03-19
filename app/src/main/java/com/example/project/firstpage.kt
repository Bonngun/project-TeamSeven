package com.example.project

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_firstpage.*

class firstpage : Fragment() {


    val timer = object: CountDownTimer(5000, 1000) {
        override fun onTick(millisUntilFinished: Long) {

        }
        override fun onFinish() {
            val login = login()
            val fm =fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.login_button,login,"login")
            transaction.addToBackStack("login")
            transaction.commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_firstpage, container, false)
        timer.start()
        return view
    }

}
