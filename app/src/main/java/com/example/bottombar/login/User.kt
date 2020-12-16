package com.example.bottombar.login


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