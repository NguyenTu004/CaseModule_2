package material;

import java.time.LocalDate;

public class Vegetable extends Materials{

    public Vegetable() {
    }

    public Vegetable(String id, String name, LocalDate manufacturingDate, double price, double quantity, Origin origin) {
        super(id, name, manufacturingDate, price, quantity, origin);
    }

    @Override
    public double getMoney() {
        return getPrice()*getQuantity();
    }

    @Override
    public LocalDate getExpiryDate() {
        return getManufacturingDate().plusDays(7);
    }
}
