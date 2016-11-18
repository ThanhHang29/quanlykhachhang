package com.ttth.item;

/**
 * Created by Administrator on 24/03/2016.
 */
public class Personnel {
    private int id;
    private String userName;
    private String pass;

    public Personnel(int id, String userName, String pass) {
        this.id = id;
        this.userName = userName;
        this.pass = pass;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", userName='" + userName +
                ", pass='" + pass ;

    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPass() {
        return pass;
    }
}
