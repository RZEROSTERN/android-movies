package com.rzerocorp.androidmovies

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.rzerocorp.androidmovies.ui.main.SectionsPagerAdapter
import android.app.SearchManager
import android.content.Context
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import android.R.menu




class MainDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dashboard)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val item = menu.findItem(R.id.action_search)
        val sv = SearchView((this@MainDashboardActivity).supportActionBar!!.themedContext)

        MenuItemCompat.setShowAsAction(
            item,
            MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItemCompat.SHOW_AS_ACTION_IF_ROOM
        )

        MenuItemCompat.setActionView(item, sv)

        sv.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                System.out.println("search query submit")
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                System.out.println("tap")
                return false
            }
        })

        return true
    }
}