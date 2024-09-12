package org.example.contact;

import lombok.Data;

@Data
public class Buyer extends Contact{
    private final String TYPE = "buyer";
    private String purchaseHistory;
    private int loyaltyPoints;

    public Buyer(){

    }
    public Buyer(Contact contact){
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
