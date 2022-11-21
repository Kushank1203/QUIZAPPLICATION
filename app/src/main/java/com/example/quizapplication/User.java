package com.example.quizapplication;

public class User {
    public String user_name, user_email, user_mobile, user_password;
    int userType;
    public User(){

    }
    public User(String user_name,String user_email,String user_mobile, String user_password, int userType){
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_mobile = user_mobile;
        this.user_password = user_password;
        this.userType = userType;
    }
    public int getUserType(){
        return userType;
    }
}
