package material;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Materials implements Serializable {
    private String id;
    private String name;
    private LocalDate manufacturingDate;
    private double price;
    private double quantity;
    private Origin origin;

    public Materials() {
    }

    public Materials(String id, String name, LocalDate manufacturingDate, double price, double quantity, Origin origin) {
        this.id = id;
        this.name = name;
        this.manufacturingDate = manufacturingDate;
        this.price = price;
        this.quantity = quantity;
        this.origin = origin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin category) {
        this.origin = category;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", manufacturingDate=" + manufacturingDate +
                ", price=" + price +
                ", quantity=" + quantity +
                ", origin=" + origin +
                '}';
    }
    public String display(){
        return " ID= '" + id + '\'' +
                ", '" + name + '\'' +
                ", " + price +
                ", " + origin.getName();
    }

    public abstract double getMoney();

    public abstract LocalDate getExpiryDate();

}
