package hanu.a2_2001040023.Models;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String name;
    private int unitPrice;
    private String category;
    private String thumbnail;

    public Product(String name, int unitPrice, String thumbnail) {

        this.name = name;
        this.unitPrice = unitPrice;

        this.thumbnail = thumbnail;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


}