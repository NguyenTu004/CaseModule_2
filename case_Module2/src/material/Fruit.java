package material;

import java.time.LocalDate;

public class Fruit extends Materials{
    public Fruit() {
    }

    public Fruit(String id, String name, LocalDate manufacturingDate, double price, double quantity, Origin origin) {
        super(id, name, manufacturingDate, price, quantity, origin);
    }

    @Override
    public double getMoney() {
        return getQuantity()*getPrice();
    }

    @Override
    public LocalDate getExpiryDate() {
        return getManufacturingDate().plusDays(30);
    }

}
