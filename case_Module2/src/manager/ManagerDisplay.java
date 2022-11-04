package manager;

import material.*;
import user.User;

import java.util.*;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

public class ManagerDisplay {
    private final double ONE_HUNDRED_THOUSAND = 100000;
    private final double FIFTY_THOUSAND = 50000;
    private final ManagerCRUD managerCRUD = new ManagerCRUD();
    private final FileIO<Materials> fileMaterials = new FileIO<>();
    private final String PATH_MATERIALS = "C:\\Users\\ASUS\\Desktop\\Máy tính\\Test_Java\\case_Module2\\src\\fileIO\\materialData.txt";
    private final String PATH_BILL = "C:\\Users\\ASUS\\Desktop\\Máy tính\\Test_Java\\case_Module2\\src\\fileIO\\billData.txt";
    private final String PATH_USER = "C:\\Users\\ASUS\\Desktop\\Máy tính\\Test_Java\\case_Module2\\src\\fileIO\\userData.txt";
    private List<Materials> listMaterials ;
    private final ManagerSystem managerSystem = new ManagerSystem();
    private final FileIO<User> fileUser = new FileIO<>();
    private  List<User> listBill = new ArrayList<>();
    private  List<User> listUser = new ArrayList<>();
    private final ManagerCart managerCart = new ManagerCart();
    public ManagerDisplay() {
    }
    private void readFile(){
        try {
            listMaterials = fileMaterials.readFile(PATH_MATERIALS);
            listBill = fileUser.readFile(PATH_BILL);
            listUser = fileUser.readFile(PATH_USER);
        }catch (Exception e) {
            System.out.println();
        }
    }
    public void displayByPriceLessOneHundredThousand(Scanner scanner){
        readFile();
        if(listMaterials.size() > 0){
            System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%s","ID","Tên","Giá","Số Lượng","Xuất Xứ","NSX",
                    "HSD\n");
            for (Materials listMaterial : listMaterials) {
                if (listMaterial.getPrice() < ONE_HUNDRED_THOUSAND && listMaterial.getPrice() > FIFTY_THOUSAND) {
                    System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%s",listMaterial.getId(),listMaterial.getName(),
                            listMaterial.getPrice(),listMaterial.getQuantity(),listMaterial.getOrigin(),
                            listMaterial.getManufacturingDate(),listMaterial.getExpiryDate()+"\n");
                }
            }
        }else {
            System.out.println("Danh sách không có sản phẩm nào!");
        }
    }
    public void displayByPriceLessFiftyThousand(Scanner scanner){
        readFile();
        if(listMaterials.size() > 0){
            System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%s","ID","Tên","Giá","Số Lượng","Xuất Xứ","NSX",
                    "HSD\n");
            for (Materials listMaterial : listMaterials) {
                if (listMaterial.getPrice() < FIFTY_THOUSAND) {
                    System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%s",listMaterial.getId(),listMaterial.getName(),
                            listMaterial.getPrice(),listMaterial.getQuantity(),listMaterial.getOrigin(),
                            listMaterial.getManufacturingDate(),listMaterial.getExpiryDate()+"\n");
                }
            }
        }else {
            System.out.println("Danh sách không có sản phẩm nào!");
        }
    }
    public void displayByPriceGreatOneHundredThousand(Scanner scanner){
        readFile();
        if(listMaterials.size() > 0){
            System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%s","ID","Tên","Giá","Số Lượng","Xuất Xứ","NSX",
                    "HSD\n");
            for (Materials listMaterial : listMaterials) {
                if (listMaterial.getPrice() > ONE_HUNDRED_THOUSAND) {
                    System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%s",listMaterial.getId(),listMaterial.getName(),
                            listMaterial.getPrice(),listMaterial.getQuantity(),listMaterial.getOrigin(),
                            listMaterial.getManufacturingDate(),listMaterial.getExpiryDate()+"\n");
                }
            }
        }else {
            System.out.println("Danh sách không có sản phẩm nào!");
        }
    }
    public String displayByOrigin(Scanner scanner){
        readFile();
        String originIn="0";
        if(listMaterials.size() > 0){
            try {
                    System.out.println("0.Thoát!!!!!");
                    System.out.println("Nhập lựa chọn cần hiển thị");
                    Origin origin = managerCRUD.updateOrigin(scanner);
                    originIn = String.valueOf(origin);
                    if (!originIn.equals("0")){
                        String originName;
                        System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%s", "ID", "Tên", "Giá", "Số Lượng", "Xuất Xứ", "NSX",
                                "HSD\n");
                        for (Materials material : listMaterials) {
                            originName = material.getOrigin().getName();
                            if (originIn.equals(originName)) {
                                System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%s", material.getId(), material.getName(),
                                        material.getPrice(), material.getQuantity(), material.getOrigin(),
                                        material.getManufacturingDate(), material.getExpiryDate() + "\n");
                            }
                        }
                    }
            }catch (Exception e){
                System.err.println("Nhập dữ liệu sai!!!");
            }
        }else {
            System.out.println("Danh sách không có sản phẩm nào!");
        }
        return originIn;
    }
    public int displayByAliment(Scanner scanner){
        readFile();
        int choice=-1;
        if(listMaterials.size() > 0){
                choice = managerCRUD.choiceAliment(scanner);
                if (choice>0){
                    System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%s", "ID", "Tên", "Giá", "Số Lượng", "Xuất Xứ", "NSX",
                            "HSD\n");
                    for (Materials listMaterial : listMaterials) {
                        if (listMaterial instanceof Meat && choice == 1) {
                            System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%s", listMaterial.getId(), listMaterial.getName(),
                                    listMaterial.getPrice(), listMaterial.getQuantity(), listMaterial.getOrigin(),
                                    listMaterial.getManufacturingDate(), listMaterial.getExpiryDate() + "\n");
                        } else if (choice == 2 && listMaterial instanceof Fruit) {
                            System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%s", listMaterial.getId(), listMaterial.getName(),
                                    listMaterial.getPrice(), listMaterial.getQuantity(), listMaterial.getOrigin(),
                                    listMaterial.getManufacturingDate(), listMaterial.getExpiryDate() + "\n");
                        } else if (choice == 3 && listMaterial instanceof Vegetable) {
                            System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%s", listMaterial.getId(), listMaterial.getName(),
                                    listMaterial.getPrice(), listMaterial.getQuantity(), listMaterial.getOrigin(),
                                    listMaterial.getManufacturingDate(), listMaterial.getExpiryDate() + "\n");
                        }
                    }
                }
        }else {
            System.out.println("Danh sách không có sản phẩm nào!");
        }return choice;
    }
    public void displayRandom() throws InterruptedException {
        readFile();
        int index;
        int size = listMaterials.size()-1;
        if(listMaterials.size() > 0){
            System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%s","ID","Tên","Giá","Số Lượng","Xuất Xứ","NSX",
                    "HSD\n");
            Thread.sleep(10);
            for (int i = 0; i < 5; i++) {
                Random rand = new Random();
                index = rand.nextInt(size);
                System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%s", listMaterials.get(index).getId(),
                        listMaterials.get(index).getName(), listMaterials.get(index).getPrice(),
                        listMaterials.get(index).getQuantity(),listMaterials.get(index).getOrigin(),
                        listMaterials.get(index).getManufacturingDate(),listMaterials.get(index).getExpiryDate()+"\n");
            }
            Thread.sleep(20);
        }else {
            System.out.println("Danh sách không có sản phẩm nào!");
        }
    }
    public void displayBillUser(){
        readFile();
        boolean flag = true;
        double sumBill = 0;
        int count = 1;
        String numPhone = managerSystem.numPhoneUser();
        System.err.printf("%-10s%-10s%-20s%-20s%-20s%-20s%s","STT","Tên","Số Điện Thoại","Địa Chỉ","Tổng tiền","Ngày mua",
                "Thông Tin Sản Phẩm\n");
        for (User user : listBill) {
            if (Objects.equals(user.getNumPhone(), numPhone)) {
                System.out.printf("%-10s%-10s%-20s%-20s%-20s%-20s%s", count, user.getUsername(),
                        user.getNumPhone(), user.getAddress(), user.getSumMoney(),user.getBuyDate(),
                        user.getInformation() + "\n");
                sumBill = user.getSumMoney();
                count++;
                flag = false;
            }
        }
        System.out.printf("%-30s%s","Tổng tiền: ",sumBill+"\n");
        if (flag){
            System.out.println("Bạn chưa mua đồ!!!!");
        }
    }
    public void search(Scanner scanner){
        readFile();
        int count = 0;
        System.out.println("Nhập từ cần tìm kiếm: ");
        String search = toUpperCase(scanner.nextLine());
        System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%s","ID","Tên","Giá","Số Lượng","Xuất Xứ","NSX",
                "HSD\n");
        for (Materials listMaterial : listMaterials) {
            if (toUpperCase(listMaterial.getName()).contains(search)) {
                System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%s",listMaterial.getId(),listMaterial.getName(),
                        listMaterial.getPrice(),listMaterial.getQuantity(),listMaterial.getOrigin(),
                        listMaterial.getManufacturingDate(),listMaterial.getExpiryDate()+"\n");
                count++;
            }
        }
        if (count == 0) System.err.println("Sản Phẩm Không Tồn Tại!!");
    }
}
