package com.example.project

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.recy_data.*


class detail : Fragment() {

    var image:String ?= null
    var firstname:String ?= null
    var lastname:String ?= null
    var position:String ?= null
    var html:String ?= null
    var css:String ?= null
    var javascript:String ?= null
    var php:String ?= null
    var sql:String ?= null

    fun newInstance(image:String,firstname:String,lastname:String,position:String,html:String,css:String,javascript:String,php:String,sql:String): detail {
        val fragment = detail()
        val bundle = Bundle()
        bundle.putString("image",image);
        bundle.putString("firstname",firstname);
        bundle.putString("lastname",lastname);
        bundle.putString("position",position);
        bundle.putString("html",html);
        bundle.putString("css",css);
        bundle.putString("javascript",javascript);
        bundle.putString("php",php);
        bundle.putString("sql",sql);


        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if(bundle != null){
            this.image = bundle.getString("image").toString()
            this.firstname = bundle.getString("firstname").toString()
            this.lastname = bundle.getString("lastname").toString()
            this.position = bundle.getString("position").toString()
            this.html = bundle.getString("html").toString()
            this.css = bundle.getString("css").toString()
            this.javascript = bundle.getString("javascript").toString()
            this.php = bundle.getString("php").toString()
            this.sql = bundle.getString("sql").toString()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_detail, container, false)
        val image_view : ImageView = view.findViewById(R.id.image_view)
        val firstname_view : TextView = view.findViewById(R.id.firstname_view)
        val lastname_view : TextView = view.findViewById(R.id.lastname_view)
        val position_view : TextView = view.findViewById(R.id.position_view)
        val html_view : TextView = view.findViewById(R.id.html_view)
        val css_view : TextView = view.findViewById(R.id.css_view)
        val java_view : TextView = view.findViewById(R.id.javascript_view)
        val php_view : TextView = view.findViewById(R.id.php_view)
        val sql_view : TextView = view.findViewById(R.id.sql_view)



        firstname_view.text = this.firstname
        lastname_view.text = this.lastname
        position_view.text = this.position
        html_view.text = this.html
        css_view.text = this.css
        java_view.text = this.javascript
        php_view.text = this.php
        sql_view.text = this.sql


        Glide.with(activity!!.baseContext).load(image).into(image_view)
        return view
    }

}
