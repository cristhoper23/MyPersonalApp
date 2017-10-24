package com.cristhoper.mypersonalapp.repositories;

import android.util.Log;

import com.cristhoper.mypersonalapp.models.User;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cris on 12/10/2017.
 */

public class UserRepository {

    public static List<User> list(){
        List<User> users = SugarRecord.listAll(User.class);
        return users;
    }

//    static{
//        users.add(new User(100, "ebenites", "tecsup", "Erick Benites"));
//        users.add(new User(200, "jfarfan", "tecsup", "Jaime Farfán"));
//        users.add(new User(300, "drodriguez", "tecsup", "David Rodriguez"));
//    }

    public static User read(Long id){
        User user = SugarRecord.findById(User.class, id);
        return user;
    }

    public static void create(String username, String password, String fullname){
        User user = new User(username, password, fullname);
        SugarRecord.save(user);
    }

    public static void update(String fullname, String username, String password, Long id){
        User user = SugarRecord.findById(User.class, id);
        user.setFullname(fullname);
        user.setUsername(username);
        user.setPassword(password);

        SugarRecord.save(user);
    }

    public static void delete(Long id){
        User user = SugarRecord.findById(User.class, id);
        SugarRecord.delete(user);
    }

    public static User login(String username, String password){

        List<User> users = list();

        for (User user : users){
            Log.d("username", user.getUsername());
            Log.d("pass", user.getPassword());

            if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equalsIgnoreCase(password)){
                return user;
            }
        }
        return null;
    }

    public static User getUser(String username){

        List<User> users = list();

        for (User user : users){
            if(user.getUsername().equalsIgnoreCase(username)){
                return user;
            }
        }
        return null;
    }

}

