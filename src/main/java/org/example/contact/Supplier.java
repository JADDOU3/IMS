package org.example.contact;

public class Supplier extends Contact{
    private final String TYPE = "supplier";

    @Override
    public String getType(){
        return TYPE;
    }
}
