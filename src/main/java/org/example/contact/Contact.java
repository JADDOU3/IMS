package org.example.contact;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;

    public Contact(){}

    public Contact getContact(int id){

        return this;
    }

    public String getType(){
        return null;
    }


}
