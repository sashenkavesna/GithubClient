package com.aliaksandramolchan.githubclient.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SearchView
import android.widget.Toast
import com.aliaksandramolchan.githubclient.R


class MainActivity : AppCompatActivity() {
    private lateinit var reposFragment: ReposFragment
    private lateinit var sender: QuerySender

    private val queryListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextChange(newText: String?): Boolean {
            return false;
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            sender.sendQuery(query)
            return false;
        }
    }

    private val closeListener = object : SearchView.OnCloseListener {
        override fun onClose(): Boolean {
            sender.sendCloseEvent(true)
            return false;
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        reposFragment = ReposFragment()
        sender = reposFragment
        if (checkNetworkConnection() == true) {
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                        .add(R.id.activity_main, reposFragment)
                        .commit()
            }
        } else Toast.makeText(this, R.string.network_error, 600).show()
        val searchView = findViewById(R.id.searchView) as SearchView
        searchView.setOnQueryTextListener(queryListener)
        searchView.setOnCloseListener(closeListener)
    }

    fun checkNetworkConnection(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.getActiveNetworkInfo()
        if (info != null && info.isConnected) {
            return true
        }
        return false
    }


    interface QuerySender {
        fun sendQuery(query: String?)
        fun sendCloseEvent(isClosed: Boolean)
    }
}
