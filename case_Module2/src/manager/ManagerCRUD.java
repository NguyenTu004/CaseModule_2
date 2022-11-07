package manager;

import material.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ManagerCRUD {
    public List<Origin> listOrigin= new ArrayList<>();
    public List<Materials> listMaterials= new ArrayList<>();
    private final FileIO<Origin> fileOrigin = new FileIO<>();
    private final FileIO<Materials> fileMaterials = new FileIO<>();
    private final String PATH_MATERIALS = "C:\\Users\\ASUS\\Desktop\\Máy tính\\Test_Java\\case_Module2\\src\\fileIO\\materialData.txt";
    private final String PATH_ORIGIN = "C:\\Users\\ASUS\\Desktop\\Máy tính\\Test_Java\\case_Module2\\src\\fileIO\\origin.txt";
    public ManagerCRUD() {
        try {
            listMaterials = fileMaterials.readFile(PATH_MATERIALS);
            listOrigin = fileOrigin.readFile(PATH_ORIGIN);
        }catch (RuntimeException e){
            System.out.println();
        }
    }
    public void readFile(){
        try {
            listMaterials = fileMaterials.readFile(PATH_MATERIALS);
            listOrigin = fileOrigin.readFile(PATH_ORIGIN);
        }catch (RuntimeException e){
            System.out.println();
        }
    }
    public void addMaterial(Scanner scanner) {
        readFile();
        int choice;
        do {
            try {
                System.out.println("_________________________________________________");
                choice = choiceAliment(scanner);
                if (choice == 0){
                    break;
                }
                if (choice >0 && choice <=3) {
                    System.out.println("Id sản phẩm: ");
                    String id = scanner.nextLine();
                    boolean checkId = checkIdExistence(id);
                    if (checkId) {
                        System.out.println("Tên sản phẩm: ");
                        String name = scanner.nextLine();
                        System.out.println("Ngày sản xuất sản phẩm: ");
                        LocalDate manufacturingDate = setDate(scanner);
                        System.out.println("Giá sản phẩm: ");
                        double price = Integer.parseInt(scanner.nextLine());
                        System.out.println("Số lượng: ");
                        double quantity = Integer.parseInt(scanner.nextLine());
                        System.out.println("Loại sản phẩm: ");
                        Origin origin = updateOrigin(scanner);
                        choiceMaterial(choice, id, name, manufacturingDate, price, quantity, origin);
                        fileMaterials.writeFile(listMaterials,PATH_MATERIALS);
                        System.out.println("Tạo thành công!!");
                    }else {
                        System.out.println("ID Sản Phẩm Đã Tồn Tại!");
                    }
                }else {
                    System.out.println("Nhập sai mời nhập lại!!");
                }
            }catch (Exception e){
                System.out.println("Nhập sai mời nhập lại!!");
            }
        }while (true);
    }

    private boolean checkIdExistence(String id) {
        for (int i = 0; i < listMaterials.size(); i++) {
            if (listMaterials.get(i).getId().equals(id)) {
                return false;
            }
        }
        return true;
    }

    public int choiceAliment(Scanner scanner) {
        int choice;
        System.out.println("1.Sản phẩm : Thịt");
        System.out.println("2.Sản phẩm : Quả");
        System.out.println("3.Sản phẩm : Rau");
        System.out.println("0.Thoát!!!");
        choice = Integer.parseInt(scanner.nextLine());
        return choice;
    }

    private void choiceMaterial(int choice,String id,String name,LocalDate manufacturingDate,double price,double quantity, Origin origin) {
        switch (choice){
            case 1: listMaterials.add(new Meat(id,name,manufacturingDate,price,quantity,origin));
                    break;
            case 2: listMaterials.add(new Fruit(id,name,manufacturingDate,price,quantity,origin));
                break;
            case 3: listMaterials.add(new Vegetable(id,name,manufacturingDate,price,quantity,origin));
                break;
        }
    }
    public LocalDate setDate(Scanner scanner) {
        System.out.println("Nhập ngày sản xuất<DD/MM/YYYY>: ");
        String date = scanner.nextLine();
        String[] parts = date.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        return LocalDate.of(year, month, day);
    }
    public void deleteMaterial(Scanner scanner){
        readFile();
        do {
            System.out.println("Nhập id sản phẩm cần xóa: ");
            System.out.println("0.Thoát!!!!");
            String id = scanner.nextLine();
            if (id.equals("0")){break;}
            int index = checkId(id);
            if (index == -1) {
                System.out.println("Nhập sai id!!!");
                break;
            }
            displayById(index);
            System.out.println("Nhập 'xóa' để xác nhậm: ");
            String textDele = scanner.nextLine();
            if (textDele.equalsIgnoreCase("xóa") || textDele.equalsIgnoreCase("xoa")) {
                listMaterials.remove(index);
                fileMaterials.writeFile(listMaterials,PATH_MATERIALS);
                System.out.println("Xóa thành công!!!!");
            }
        }while(true);
    }
    public void updateMaterial(Scanner scanner) {
        readFile();
        String id;
        boolean flag = false;
        do {
            System.out.println("Nhập id sản phẩm cần sửa: ");
            System.out.println("0.Thoát!!!");
            id = scanner.nextLine();
            if (id.equals("0")){break;}
            int index = checkId(id);
            if (index == -1) {
                System.out.println("Nhập sai id!!!");
                break;
            }
            displayById(index);
            System.out.println("Lựa chọn cần sửa: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("1.Tên sản phẩm: " + listMaterials.get(index).getName());
                    checkUpdate(scanner, index, 1);
                    flag= true;
                    break;
                case 2:
                    System.out.println("2.Giá sản phẩm: " + listMaterials.get(index).getPrice());
                    checkUpdate(scanner, index, 2);
                    flag= true;
                    break;
                case 3:
                    System.out.println("3.Số lượng sản phẩm: " + listMaterials.get(index).getQuantity());
                    checkUpdate(scanner, index, 3);
                    flag= true;
                    break;
                case 4:
                    System.out.println("4.Loại sản phẩm: " + listMaterials.get(index).getOrigin());
                    Origin origin = updateOrigin(scanner);
                    listMaterials.get(index).setOrigin(origin);
                    flag= true;
                    break;
                case 5:
                    System.out.println("5.Ngày sản xuất sản phẩm: " + listMaterials.get(index).getManufacturingDate());
                    LocalDate manufacturingDate = setDate(scanner);
                    listMaterials.get(index).setManufacturingDate(manufacturingDate);
                    flag= true;
                    break;
            }if (flag){
                fileMaterials.writeFile(listMaterials,PATH_MATERIALS);
                System.out.println("Sửa thành công!!!!");
            }
        }while(!Objects.equals(id,"0"));
    }
    public void displayMaterials(){
        readFile();

        System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%s","ID","Tên","Giá","Số Lượng","Xuất Xứ","NSX",
                "HSD\n");
        if(listMaterials.size()>0){
            for (Materials material : listMaterials){
                System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%s",material.getId(),material.getName(),
                        material.getPrice(),material.getQuantity(),material.getOrigin(),
                        material.getManufacturingDate(),material.getExpiryDate()+"\n");
            }
        }else {
            System.out.println("Danh sách không có sản phẩm nào!");
        }
    }
    public Origin updateOrigin(Scanner scanner) {
        Origin category = new Origin();
        try {
            System.out.println("1.Xuất xứ: " + listOrigin.get(0));
            System.out.println("2.Xuất xứ: " + listOrigin.get(1));
            System.out.println("3.Xuất xứ: " + listOrigin.get(2));
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice  >=0 && choice <=3) {
                switch (choice) {
                    case 1:
                        category = listOrigin.get(0);
                        break;
                    case 2:
                        category = listOrigin.get(1);
                        break;
                    case 3:
                        category = listOrigin.get(2);
                        break;
                    case 0:
                        category = listOrigin.get(3);
                        break;
                }
            }
        }catch (Exception e){
            System.out.println();
        }
        return category;
    }

    public void checkUpdate(Scanner scanner, int index, int choice) {
        String text = scanner.nextLine();
        if (!Objects.equals(text, "")) {
            switch (choice) {
                case 1: listMaterials.get(index).setName(text); break;
                case 2: try {
                    int num = Integer.parseInt(text);
                    listMaterials.get(index).setPrice(num);break;
                } catch (Exception e) {
                    System.out.println("Nhap sai du lieu");
                }
                break;
                case 3: try {
                    int num = Integer.parseInt(text);
                    listMaterials.get(index).setQuantity(num);break;
                } catch (Exception e) {
                    System.out.println("Nhap sai du lieu");
                }
                    break;
            }
        }
    }

    public int checkId(String id){
        readFile();
        int index=-1;
        for (int i = 0; i < listMaterials.size(); i++) {
            if (listMaterials.get(i).getId().equals(id)) {
                index  = i;
                break;
            }
        }
        return index;
    }
    public void displayById(int index) {
        System.out.println("*.Mã sản phẩm: "+listMaterials.get(index).getId());
        System.out.println("1.Tên sản phẩm: "+listMaterials.get(index).getName());
        System.out.println("2.Giá sản phẩm: "+listMaterials.get(index).getPrice());
        System.out.println("3.Số lượng sản phẩm: "+listMaterials.get(index).getQuantity());
        System.out.println("4.Loại sản phẩm: "+listMaterials.get(index).getOrigin());
        System.out.println("5.Ngày sản xuất sản phẩm: "+listMaterials.get(index).getManufacturingDate());
        System.out.println("*.Ngày hết hạn sản phẩm: "+listMaterials.get(index).getExpiryDate());

    }
}
