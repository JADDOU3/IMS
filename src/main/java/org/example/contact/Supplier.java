package org.example.contact;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Supplier extends Contact{
    private final String TYPE = "supplier";
    private int rating;
    @Setter
    private boolean isPreferred;


    public boolean getIsPreferred() {
        return isPreferred;
    }

    public Supplier(){}

    public Supplier(Contact contact){
        setId(contact.getId());
        setName(contact.getName());
        setPhone(contact.getPhone());
        setEmail(contact.getEmail());
        setAddress(contact.getAddress());
    }

    @Override
    public String getType(){
        return TYPE;
    }
}
