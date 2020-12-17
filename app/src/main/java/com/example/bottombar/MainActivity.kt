package com.example.bottombar

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.example.bottombar.Fragment.MovieFragment
import com.example.bottombar.Fragment.MyFragment
import com.example.bottombar.Fragment.NewsFragment
import com.example.bottombar.Fragment.WeatherFragment
import com.example.bottombar.login.loginActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var bottomNavigation:BottomNavigationBar
     val fragmentList: MutableList<Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //
        setSupportActionBar(tool_bar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        //
        getFragments();
        bottomNavigation=findViewById(R.id.bottom_navigation_bar)
        bottomNavigation.addItem(BottomNavigationItem(R.drawable.news,"新闻"))
                .addItem(BottomNavigationItem(R.drawable.weather,"天气"))
                .addItem(BottomNavigationItem(R.drawable.movie,"影讯"))
                .setInActiveColor(R.color.colortext)
                .setActiveColor(R.color.teal_200)
                .setFirstSelectedPosition(0)
                .initialise()
        val myObject=object:BottomNavigationBar.OnTabSelectedListener{
            override fun onTabReselected(position: Int) {

            }

            override fun onTabUnselected(position: Int) {
                val fragmentTransaction:FragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.hide(fragmentList[position])
                fragmentTransaction.commitAllowingStateLoss()
            }

            override fun onTabSelected(position: Int) {
                val fragmentTransaction:FragmentTransaction = supportFragmentManager.beginTransaction()

                if(fragmentList[position].isAdded){
                    fragmentTransaction.show(fragmentList[position])
                }else{
                    fragmentTransaction.add(R.id.tabs, fragmentList[position])
                }
                fragmentTransaction.commitAllowingStateLoss()
            }
        }
        bottomNavigation.setTabSelectedListener(myObject)
        supportFragmentManager.beginTransaction().replace(R.id.tabs, fragmentList[0])
                .commitAllowingStateLoss()
    }
    private fun getFragments(){
        val  newsFragment: NewsFragment = NewsFragment()
        val weatherFragment: WeatherFragment = WeatherFragment()
        val movieFragment: MovieFragment = MovieFragment()

        fragmentList.add(newsFragment)
        fragmentList.add(weatherFragment)
        fragmentList.add(movieFragment)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_right, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val dialog: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        dialog.setMessage(R.string.helpdoc)
        dialog.setNegativeButton("取消", null)
        when (item.itemId) {
            R.id.jianjie -> dialog.show()
            android.R.id.home -> draw_layout.openDrawer(Gravity.LEFT)
        }
        return true
    }

    override  fun onClick(v: View?) {
        when (v!!.id) {
            android.R.id.home -> draw_layout.openDrawer(Gravity.RIGHT)
        }
    }
}