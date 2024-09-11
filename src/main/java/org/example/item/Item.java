package org.example.item;

import lombok.Data;


@Data
public class Item {
    private int id;
    private String name;
    private int quantity;
    private int price;

    public Item(){
    }


}
