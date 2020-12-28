package com.omar98k.todolist.classes;

import com.omar98k.todolist.classes.ListClass;
import com.omar98k.todolist.classes.TaskClass;

import java.util.HashMap;
import java.util.Map;

public class UserClass {
    public String name;
    public String id;
    public String email;
    public Map<String , ListClass> category  = new HashMap<>();
    public Map<String , TaskClass> notes  = new HashMap<>();


    public UserClass() {
    }
    public UserClass(String name1,String email1) {
        this.name=name1;
        this.email=email1;
    }
}
