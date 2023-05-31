package hanu.a2_2001040023.Models;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String name;
    private int unitPrice;
    private String category;
    private String thumbnail;
    private int quantity;


    private int product_sum_price;


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", thumbnail='" + thumbnail + '\'' +
                ", quantity=" + quantity +
                ", product_sum_price=" + product_sum_price +
                '}';
    }

    public Product(int id, String name, int unitPrice, String thumbnail, int quantity, int product_sum_price) {

        this.name = name;
        this.unitPrice = unitPrice;
        this.id = id;
        this.thumbnail = thumbnail;
        this.quantity = quantity;
        this.product_sum_price = product_sum_price;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProduct_sum_price() {
        return product_sum_price;
    }

    public void setProduct_sum_price(int product_sum_price) {
        this.product_sum_price = product_sum_price;
    }
}