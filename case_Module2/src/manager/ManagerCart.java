package manager;
import material.Materials;
import user.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class ManagerCart {
    private final Map<String, Integer> listCart = new HashMap<>();
    private Set<String> keySet;
    private final Map<String, Double> mapMoney= new HashMap<>();
    private final Map<String,String> mapId= new HashMap<>();
    private Set<String> keyId;
    private final ManagerCRUD managerCRUD = new ManagerCRUD();
    private List<User> listBill = new ArrayList<>();
    private double sumMoneyCart ;
    private final FileIO<User> fileUser = new FileIO<>();
    private final String PATH_BILL = "C:\\Users\\ASUS\\Desktop\\Máy tính\\Test_Java\\case_Module2\\src\\fileIO\\billData.txt";
    private final String PATH_MATERIALS = "C:\\Users\\ASUS\\Desktop\\Máy tính\\Test_Java\\case_Module2\\src\\fileIO\\materialData.txt";
    private final String PATH_USER ;
    private final FileIO<Materials> fileMaterials = new FileIO<>();
    private List<Materials> listMaterials ;
    private final ManagerSystem managerSystem = new ManagerSystem();
    private List<User> listUsers = new ArrayList<>();
    public ManagerCart() {
        listMaterials = fileMaterials.readFile(PATH_MATERIALS);
        PATH_USER = "C:\\Users\\ASUS\\Desktop\\Máy tính\\Test_Java\\case_Module2\\src\\fileIO\\userData.txt";
    }
    private void readFile(){
        listMaterials = fileMaterials.readFile(PATH_MATERIALS);
        listBill = fileUser.readFile(PATH_BILL);
        listUsers = fileUser.readFile(PATH_USER);
    }
    public void addCart(Scanner scanner){
        readFile();
        String id = null;
        do {
            keySet = listCart.keySet();
            keyId = mapId.keySet();
            boolean flag;
            try {
                System.out.println("Nhập id sản phẩm cần thểm vô giỏ hàng: ");
                System.out.println("0.Thoát!!!");
                id = scanner.nextLine();
                if (id.equals("0")){break;}
                int index = managerCRUD.checkId(id);
                if (index == -1) {System.out.println("Sai id của sản phẩm: ");break;}
                if (checkQuantityBack(index)) break;
                if (checkExpiryDate(index)) break;
                System.out.println("Số lượng: ");
                int quantity = Integer.parseInt(scanner.nextLine());
                if (quantity > listMaterials.get(index).getQuantity()){
                    System.out.println("Số lượng bạn đặt đã quá số lượng sản phẩm đang có!!");
                }else {
                    flag = checkKeyCart( index, quantity);
                    if (flag) {
                        listCart.put(listMaterials.get(index).display(), quantity);
                        System.out.println("ĐÃ THÊM VÔ GIỎ HÀNG!!");
                    }
                    mapMoney.put(listMaterials.get(index).display(), listMaterials.get(index).getPrice());
                    mapId.put(id, listMaterials.get(index).display());
                }
            }catch (Exception e) {
                System.out.println("Nhập sai kiểu dữ liệu!!!");}
        }while (!Objects.equals(id,"0"));
    }
    private boolean checkQuantityBack(int index) {
        if (listMaterials.get(index).getQuantity() <= 0 ) {
            System.out.println("Sản phẩm đã hết hàng!!!");
            return true;
        }
        return false;
    }
    private boolean checkExpiryDate(int index) {
        LocalDate date = LocalDate.now();
        Period period = Period.between(date,listMaterials.get(index).getExpiryDate());
        if(period.getDays() < 0){
            System.out.println("Sản phẩm đã hết hạn");
            return true;
        }
        return false;
    }
    private boolean checkKeyCart(int index, double quantity) {
        boolean flag = true;
        for (String key : keySet) {
            if (Objects.equals(key, listMaterials.get(index).display())) {
                int amount = listCart.get(key);
                amount += quantity;
                if (amount <= listMaterials.get(index).getQuantity()){
                     listCart.put(key, amount);
                    flag = false;
                    System.out.println("ĐÃ THÊM VÔ GIỎ HÀNG!!");
                }else{
                    System.out.println("Số lượng bạn mua đã quá số lượng sản phẩm đang có!!");
                    flag = false;
                }
                break;
            }
        }
        return flag;
    }
    public void displayCart(){
        keySet = listCart.keySet();
        keyId = mapId.keySet();
        int count = 1;
        System.out.printf("%-10s%-80s%s","STT","Thông Tin Sản Phẩm","Số Lượng\n");
        for (String key : keySet){
            System.out.printf("%-10s%-80s%s",count,key,listCart.get(key)+"\n");
            sumMoneyCart += listCart.get(key)*mapMoney.get(key);
            count++;
        }
        System.out.printf("%-10s%s","Tổng tiền: ",sumMoneyCart+"\n");
    }
    public void updateCart(Scanner scanner){
        if(listCart.size()>0) {
            System.out.println("Nhập id sản phẩm cẩn thay đổi: ");
            String id = scanner.nextLine();
            String key = checkKeyCart(id);
            if (key != null) {
                System.out.println("Số lượng của sản phẩm là: " + listCart.get(key));
                int amount = Integer.parseInt(scanner.nextLine());
                if (amount <= 0) {
                    listCart.remove(key);
                } else {
                    listCart.put(key, amount);
                }
                System.out.println("Sửa thành công!!");
            } else {
                System.out.println("Nhập sai ID...");
            }
            sumMoneyCart = 0;
        }else{
            System.out.println("Danh sách không có sản phẩm nào!");
        }
    }
    public void deleteCart(Scanner scanner){
        if(listCart.size()>0) {
            System.out.println("Nhập id sản phẩm cẩn xóa: ");
            String id = scanner.nextLine();
            String key = checkKeyCart(id);
            if (key != null) {
                System.out.println("Nhập 'xóa' để xác nhận: ");
                String textDele = scanner.nextLine();
                if (textDele.equalsIgnoreCase("xóa") || textDele.equalsIgnoreCase("xoa")) {
                    listCart.remove(key);
                    sumMoneyCart = sumMoneyCart - (listCart.get(key) * mapMoney.get(key));
                    System.out.println("Xóa thành công!!!");
                }
            } else {
                System.out.println("Nhập sai id");
            }
        }else{
            System.out.println("Danh sách không có sản phẩm nào!");
        }
    }
    public void displayPayCart(Scanner scanner){
        keySet = listCart.keySet();
        keyId = mapId.keySet();
        int index = managerSystem.indexUsers();
        if(listCart.size()>0) {
            do {
                try {
                    System.out.println("Nhập địa chỉ: ");
                    String address = scanner.nextLine();
                    System.out.println("1.Xác nhận thông tin và đặt hàng: ");
                    LocalDate date = LocalDate.now();
                    int choice = Integer.parseInt(scanner.nextLine());
                    if (choice == 1) {
                        String information = informationMaterial();
                        listBill.add(new User(listUsers.get(index).getUsername(), listUsers.get(index).getNumPhone(),
                                address, information, sumMoneyCart, date));
                        updateQuantityMaterial();
                        fileMaterials.writeFile(listMaterials, PATH_MATERIALS);
                        fileUser.writeFile(listBill, PATH_BILL);
                        System.out.println("Đặt hàng thành công!!!");
                        sumMoneyCart = 0;
                        listCart.clear();
                        System.out.println("0.Thoát!!!");
                        String choose = scanner.nextLine();
                        if (choose.equals("0")) {
                            break;
                        }
                    } else {System.out.println("Nhập 1!!!");}
                } catch (Exception e) {System.out.println("Nhập sai thông tin !!!");}
            } while (true);
        }else{System.out.println("Danh sách không có sản phẩm nào!");}
    }
    private void updateQuantityMaterial(){
        for (String id : keyId) {
            double num = listCart.get(mapId.get(id));
            int index = managerCRUD.checkId(id);
            double quantity =listMaterials.get(index).getQuantity()-num;
            listMaterials.get(index).setQuantity(quantity);
        }
    }
    private String informationMaterial() {
        int count = 0;
        int size = keySet.size();
        StringBuilder information = new StringBuilder();
        for (String key : keySet) {
            information.append("[");
            information.append(key);
            information.append(" ,Số lượng: ");
            information.append(listCart.get(key));
            information.append("]");
            if (count < size-1){
                information.append("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
            }
            count++;
        }
        return information.toString();
    }
    private String checkKeyCart(String id) {
        String key=null;
        for (String keys : keyId){
            if (keys.equals(id)){
                key = mapId.get(keys);
                break;
            }
        }
        return key;
    }
}
