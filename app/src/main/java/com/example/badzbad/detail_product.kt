package com.example.myapplogin_fragment


import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder

import com.bumptech.glide.request.RequestOptions
import com.example.badzbad.R
import kotlinx.android.synthetic.main.fragment_detail_product.*


/**
 * A simple [Fragment] subclass.
 */
class detail_product : Fragment() {

    private var title:String?=null
    private var detail:String?=null
    private var image:String?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail_product, container, false)
        val layout_title =view?.findViewById<TextView>(R.id._title)
        val layout_detail =view?.findViewById<TextView>(R.id._det)
        val layout_image =view.findViewById<ImageView>(R.id.imageView)


        layout_title?.text = this.title
        layout_detail?.text = this.detail

            Glide.with(this)
                .load(image)
                .into(layout_image)



        return view
    }

    fun newInstance(title: String, detail: String, image:String): detail_product {
            val fragment = detail_product()
        val bundle = Bundle()
        bundle.putString("title", title)
        bundle.putString("detail", detail)
        bundle.putString("image", image)
        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var bundle = arguments
        if (bundle != null) {
            title = bundle.getString("title").toString()
            detail = bundle.getString("detail").toString()
            image = bundle.getString("image").toString()
        }
    }

}
