package manager;

import material.Materials;
import user.User;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
public class ManagerAdmin {
    private final String PATH_BILL = "C:\\Users\\ASUS\\Desktop\\Máy tính\\Test_Java\\case_Module2\\src\\fileIO\\billData.txt";
    private final String PATH_MATERIALS = "C:\\Users\\ASUS\\Desktop\\Máy tính\\Test_Java\\case_Module2\\src\\fileIO\\materialData.txt";
    private List<User> listBill;
    private List<Materials> listMaterials;
    private final FileIO<User> fileUsers = new FileIO<>();
    private final FileIO<Materials> fileMaterials = new FileIO<>();
    private final String OUT_OF_DATE ;
    private final String GOOD ;
    private final String OUT_OF_STOCK ;

    public ManagerAdmin() {
        OUT_OF_DATE = "Out of date";
        GOOD = "Good";
        OUT_OF_STOCK = "Out of stock";
        listBill = fileUsers.readFile(PATH_BILL);
        listMaterials = fileMaterials.readFile(PATH_MATERIALS);
    }

    private void readFile() {
        try {
            listBill = fileUsers.readFile(PATH_BILL);
            listMaterials = fileMaterials.readFile(PATH_MATERIALS);
        }catch (RuntimeException e) {
            System.out.println();
        }
    }

    public void displayBill()   {
        readFile();
        double sumMoneyBill =0;
        System.out.printf("%-10s%-10s%-20s%-20s%-20s%-20s%s","STT","Tên","Số Điện Thoại","Địa Chỉ","Tổng tiền","Ngày mua",
                "Thông Tin Sản Phẩm\n");
        for (int i = 0; i < listBill.size(); i++) {
            System.out.printf("%-10s%-10s%-20s%-20s6%-20s%-20s%s",(i+1),listBill.get(i).getUsername(),
                    listBill.get(i).getNumPhone(),listBill.get(i).getAddress(),listBill.get(i).getSumMoney(),listBill.get(i).getBuyDate(),
                    listBill.get(i).getInformation()+"\n");
            sumMoneyBill += listBill.get(i).getSumMoney();
        }
        System.out.printf("%-60s%s","Tổng tiền: ",sumMoneyBill+"\n");
    }
    private double sumBill(){
        readFile();
        double sumBill =0;
        for (User user : listBill) {
            sumBill += user.getSumMoney();
        }
        return sumBill;
    }
    public void displayInformation() {
        readFile();
        CollectionSort sort = new CollectionSort();
        listMaterials.sort(sort);
        LocalDate date = LocalDate.now();
        String status ;
        double sumAll =0;
        double sumOutDate =0;
        System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%s", "ID", "Tên", "Giá",
                "Số lượng", "Xuất xứ", "Ngày sản xuất", "Ngày hết hạn", "Trạng thái", "Tổng giá\n");
        for (Materials materials : listMaterials) {
            Period period = Period.between(date, materials.getExpiryDate());
            if (period.getDays() < 0) {
                status = OUT_OF_DATE;
                sumOutDate += materials.getMoney();
            } else {
                status = GOOD;
                if (materials.getQuantity() <= 0) {
                    status = OUT_OF_STOCK;
                }else {
                    sumAll += materials.getMoney();
                }
            }
            System.out.printf("%-10s%-20s%-20.0f%-20.0f%-20s%-20s%-20s%-20s%s", materials.getId(), materials.getName(),
                    materials.getPrice(), materials.getQuantity(), materials.getOrigin(), materials.getManufacturingDate(),
                    materials.getExpiryDate(), status, materials.getMoney() + "\n");
        }
        double sumBill = sumBill();
        System.out.printf("%-37s%s","Tổng tiền hàng trong kho: ",sumAll+" VND\n");
        System.out.printf("%-37s%s","Tổng tiền hàng đã giao: ",sumBill+" VND\n");
        System.out.printf("%-37s%s","Tổng tiền hàng hết hạn: ",sumOutDate+" VND\n");
        System.out.printf("%-10s%s","Tổng tiền hàng trong kho và đã giao: ",(sumAll+sumBill)+" VND\n");
        System.out.printf("%-37s%s","Tổng tiền hàng: ",(sumAll+sumBill+sumOutDate)+" VND\n");
    }
}
