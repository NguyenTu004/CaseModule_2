package material;

import java.io.Serializable;

public class Origin implements Serializable {
    private String nation;

    public Origin(String nation) {
        this.nation = nation;
    }

    public Origin() {
    }

    public String getName() {
        return nation;
    }

    public void setName(String nation) {
        this.nation = nation;
    }

    @Override
    public String toString() {
        return nation;
    }
}
