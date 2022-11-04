package iSystem;

import manager.*;

import java.util.Scanner;


public class ISystemMenu {
    private final ManagerCRUD managerCRUD = new ManagerCRUD();
    private final ManagerSystem managerSystem = new ManagerSystem();
    private final ManagerDisplay managerDisplay = new ManagerDisplay();
    private final ManagerCart managerCart = new ManagerCart();
    private final ManagerAdmin managerAdmin = new ManagerAdmin();
    public ISystemMenu() {
    }

    public void systemMenu(Scanner scanner) throws InterruptedException {
        int index = 0;
        do {
            try {
                System.out.println("============================================================================================");
                System.out.println("1.Đăng nhập: ");
                System.out.println("2.Đăng Ký: ");
                System.out.println("0.Thoát!!!");
                System.out.println("Lựa chọn của bạn: ");
                    int choose = Integer.parseInt(scanner.nextLine());
                    if (choose == 0) break;
                    if (choose >= 0 && choose <= 2) {
                        switch (choose) {
                            case 1:
                                index = managerSystem.login(scanner);
                                break;
                            case 2:
                                index = managerSystem.register(scanner);
                                break;
                        }
                    } else {
                        System.err.println("Nhập sai mời bạn nhập lại");
                    }
            }catch (RuntimeException e) {
                System.err.println("Nhập sai mời bạn nhập lại");
            }
            if (index == 2) {
                user(scanner);
            } else if (index == 1) {
                admin(scanner);
            }
        }while (true);
    }

    private void admin(Scanner scanner) throws InterruptedException {
        int choice;
        try {
            do {
                System.out.println("MENU");
                System.out.println("1.Hiển thị tất cả sản phẩm: ");
                System.out.println("2.Hiển thị tất cả sản phẩm theo xuất xứ: ");
                System.out.println("3.Thêm sản phẩm: ");
                System.out.println("4.Sửa sản phẩm: ");
                System.out.println("5.Xóa sản phẩm: ");
                System.out.println("6.Thông tin tổng quát sản phẩm: ");
                System.out.println("7.Lịch sử hóa đơn: ");
                System.out.println("0.Đăng xuất Admin: ");
                System.out.println("Lựa chọn của bạn: ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 0 && choice <= 7) {
                    systemAdmin(scanner, choice);
                } else {
                    System.err.println("Nhập sai mời bạn nhập lại");
                }
            } while (choice != 0);
        }catch (Exception e){
            System.err.println("Nhập sai dữ liệu!!");
        }
    }

    private void systemAdmin(Scanner scanner, int choice) throws InterruptedException {
        switch (choice) {
            case 1:
                managerCRUD.displayMaterials();
                break;
            case 2:
                managerDisplay.displayByOrigin(scanner);
                break;
            case 3:
                managerCRUD.addMaterial(scanner);
                break;
            case 4:
                managerCRUD.updateMaterial(scanner);
                break;
            case 5:
                managerCRUD.deleteMaterial(scanner);
                break;
            case 6:managerAdmin.displayInformation();Thread.sleep(10);break;
            case 7:managerAdmin.displayBill();Thread.sleep(10);break;
            case 8:managerSystem.outAdmin(scanner);break;
        }
    }

    private void user(Scanner scanner) throws InterruptedException {
        int choice;
        do {
            try {
                System.out.println("MENU");
                System.out.println("1.Tìm kiếm: ");
                System.out.println("2.Sản phẩm ưu tiên: ");
                System.out.println("3.Hiển thị tất cả sản phẩm theo xuất xứ: ");
                System.out.println("4.Hiển thị tất cả sản phẩm theo giá: ");
                System.out.println("5.Hiển thị tất cả sản phẩm theo loại: ");
                System.out.println("6.Giỏ hàng: ");
                System.out.println("7.Lịch sử thanh toán: ");
                System.out.println("0.Thoát!!!");
                System.out.println("Lựa chọn của bạn: ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0){
                    break;
                }
                if (choice >0 && choice <=7) {
                    systemUser(scanner, choice);
                }else {System.err.println("Nhập sai mời bạn nhập lại");}
            }catch (NumberFormatException e){System.err.println("Nhập sai mời bạn nhập lại");}
        }while (true);
    }

    private void systemUser(Scanner scanner, int choice) throws InterruptedException {
        switch (choice) {
            case 2: managerDisplay.displayRandom();managerCart.addCart(scanner);break;
            case 3:String numOrigin;
                do{
                    numOrigin = managerDisplay.displayByOrigin(scanner);;
                    if (numOrigin.equals("0"))break;
                    managerCart.addCart(scanner);
                }while (true);
                break;
            case 4:int numPrice;do{
                    numPrice = displayByPrices(scanner);
                    if (numPrice == 0)break;
                    managerCart.addCart(scanner);
                }while (true);
                break;
            case 5:int numAliment;
                do{
                    numAliment = managerDisplay.displayByAliment(scanner);
                if (numAliment ==0)break;
                managerCart.addCart(scanner);
                }while (true);
                break;
            case 6:
                cart(scanner);
                break;
            case 7:managerDisplay.displayBillUser();break;
            case 1:managerDisplay.search(scanner);managerCart.addCart(scanner);break;
        }
    }

    private void cart(Scanner scanner) {
        int choice;
        do {
            managerCart.displayCart();
            try {
                System.out.println("1.Chỉnh sửa: ");
                System.out.println("2.Xóa: ");
                System.out.println("3.Thanh toán: ");
                System.out.println("0.Thoát!!!");
                System.out.println("Lựa chọn của bạn: ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >=0 && choice <=3){
                    if (choice==0){
                        break;
                    }
                    switch (choice) {
                        case 1:
                            managerCart.updateCart(scanner);
                            break;
                        case 2:
                            managerCart.deleteCart(scanner);
                            break;
                        case 3:
                            managerCart.displayPayCart(scanner);
                            break;
                    }
                }else {
                    System.out.println("Nhập từ 0---3!!");
                }
            }catch (Exception e){
                System.err.println("Nhập sai mời nhập lại!!");
            }
        }while (true);
    }

    private int displayByPrices(Scanner scanner) {
        int choice=0;
        try {
            System.out.println("1.Giá dưới 50.000VND");
            System.out.println("2.Giá trên 50.000VND và dưới 100.000VND");
            System.out.println("3.Giá trên 100.000VND");
            System.out.println("0.Thoát!!!");
            System.out.println("Lựa chọn của bạn: ");
            choice = Integer.parseInt(scanner.nextLine());
            if (choice >=0 && choice <=3) {
                if (choice > 0){
                    switch (choice) {
                        case 1:
                            managerDisplay.displayByPriceLessFiftyThousand(scanner);
                            break;
                        case 2:
                            managerDisplay.displayByPriceLessOneHundredThousand(scanner);
                            break;
                        case 3:
                            managerDisplay.displayByPriceGreatOneHundredThousand(scanner);
                            break;
                    }
                }
            }else {
                System.err.println("Nhập sai mời bạn nhập lại");
            }
        }catch (Exception e){
            System.err.println("Nhập sai mời bạn nhập lại");
        }
        return choice;
    }
}
