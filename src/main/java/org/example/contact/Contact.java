package org.example.contact;

import lombok.Data;

@Data
public class Contact {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;

    public Contact(){}


    public String getType(){
        return null;
    }


}
