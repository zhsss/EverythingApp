package com.example.bottombar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigation:BottomNavigationBar
     val fragmentList: MutableList<Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getFragments();
        bottomNavigation=findViewById(R.id.bottom_navigation_bar)
        bottomNavigation.addItem(BottomNavigationItem(R.drawable.news,"新闻"))
                .addItem(BottomNavigationItem(R.drawable.weather,"天气"))
                .addItem(BottomNavigationItem(R.drawable.movie,"影讯"))
                .addItem(BottomNavigationItem(R.drawable.my,"我的"))
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
        val myFragment: MyFragment = MyFragment()
        fragmentList.add(newsFragment)
        fragmentList.add(weatherFragment)
        fragmentList.add(movieFragment)
        fragmentList.add(myFragment)
    }
}