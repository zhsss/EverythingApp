package com.example.bottombar.login

/**
 * Created by littlecurl 2018/6/24
 */
class User(//用户名
        var name: String, //密码
        var password: String) {

    override fun toString(): String {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}'
    }

}