package com.omar98k.todolist;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskClass {
    public String ListId="public";
    public String idOfTask;
    public String titleOfTask;
    public String contextOfTask;
    public long dateOfTask;
    public TaskClass(){ }
    public TaskClass(String ListId,String idOfTask, String titleOfTask, String contextOfTask, long dateOfTask){
        this.ListId = ListId;
        this.idOfTask=idOfTask;
        this.titleOfTask = titleOfTask;
        this.contextOfTask = contextOfTask;

        this.dateOfTask = dateOfTask;
    }

    public static String longToDate(long val){
        Date date=new Date(val);
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
        String dateText = df2.format(date);
        return dateText;
    }
}
