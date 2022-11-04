package material;

import java.time.LocalDate;

public class Meat extends Materials {
    public Meat() {
    }

    public Meat(String id, String name, LocalDate manufacturingDate, double price, double quantity, Origin origin) {
        super(id, name, manufacturingDate, price, quantity, origin);
    }

    @Override
    public double getMoney() {
        return getQuantity()*getPrice();
    }

    @Override
    public LocalDate getExpiryDate() {
        return getManufacturingDate().plusDays(20);
    }

}
