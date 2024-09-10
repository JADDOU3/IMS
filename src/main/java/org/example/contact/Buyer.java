package org.example.contact;

public class Buyer extends Contact{
    private final String TYPE = "buyers";

    @Override
    public String getType(){
        return TYPE;
    }
}
