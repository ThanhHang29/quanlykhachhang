package com.ttth.item;

/**
 * Created by Administrator on 22/03/2016.
 */
public class Customer {
    private int id;
    private String name;
    private String birthday;
    private String email;
    private String phoneNumber;
    private String organization;
    private String positional;
    private String address;
    private String type;

    public Customer(int id, String name, String birthday, String email, String phoneNumber,
                    String organization, String positional, String address, String type) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.organization = organization;
        this.positional = positional;
        this.address = address;
        this.type = type;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                "\n name='" + name +
                "\n birthday='" + birthday +
                "\n email='" + email +
                "\n phoneNumber='" + phoneNumber +
                "\n organization='" + organization +
                "\n positional='" + positional +
                "\n address='" + address +
                "\n type='" + type ;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getOrganization() {
        return organization;
    }

    public String getPositional() {
        return positional;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }
}
