package com.example.bottombar.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.bottombar.MainActivity
import com.example.bottombar.R
import kotlinx.android.synthetic.main.activity_login.*

class loginActivity : AppCompatActivity(), View.OnClickListener {

    private var mDBOpenHelper: DBOpenHelper? = null
    private var mTvLoginactivityRegister: TextView? = null
    private var mRlLoginactivityTop: RelativeLayout? = null
    private var mEtLoginactivityUsername: EditText? = null
    private var mEtLoginactivityPassword: EditText? = null
    private var mLlLoginactivityTwo: LinearLayout? = null
    private var mBtLoginactivityLogin: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        mDBOpenHelper = DBOpenHelper(this)
    }


    private fun initView() {
        // 初始化控件
        mBtLoginactivityLogin = findViewById(R.id.bt_loginactivity_login)
        mTvLoginactivityRegister = findViewById(R.id.tv_loginactivity_register)
        mRlLoginactivityTop = findViewById(R.id.rl_loginactivity_top)
        mEtLoginactivityUsername = findViewById(R.id.et_loginactivity_username)
        mEtLoginactivityPassword = findViewById(R.id.et_loginactivity_password)
        mLlLoginactivityTwo = findViewById(R.id.ll_loginactivity_two)

        // 设置点击事件监听器
        bt_loginactivity_login.setOnClickListener(this)
        tv_loginactivity_register.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.tv_loginactivity_register -> {
                startActivity(Intent(this, RegisterActivity::class.java))
                finish()
            }
            R.id.bt_loginactivity_login -> {
                val name = mEtLoginactivityUsername!!.text.toString().trim { it <= ' ' }
                val password = mEtLoginactivityPassword!!.text.toString().trim { it <= ' ' }
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    val data = mDBOpenHelper?.allData
                    var match = false
                    var i = 0
                    while (i < data!!.size) {
                        val user = data!![i]
                        if (name == user.name && password == user.password) {
                            match = true
                            break
                        } else {
                            match = false
                        }
                        i++
                    }
                    if (match) {
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish() //销毁此Activity
                    } else {
                        Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}