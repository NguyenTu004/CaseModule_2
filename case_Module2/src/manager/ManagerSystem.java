package manager;

import iSystem.ISystemMenu;
import user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerSystem {
    private List<User> listUsers;
    private final FileIO<User> fileUser = new FileIO<>();
    private final String PATH_USER = "C:\\Users\\ASUS\\Desktop\\Máy tính\\Test_Java\\case_Module2\\src\\fileIO\\userData.txt";
    private static String numPhone;
    private static int indexUser;
    public ManagerSystem() {
        listUsers = new ArrayList<>();
    }
    private void readFile(){
        try {
            listUsers = fileUser.readFile(PATH_USER);
        }catch (Exception e) {
            System.out.println();
        }
    }
    public int login(Scanner scanner) {
        readFile();
        int num ;
        boolean flag = true;
        boolean accountExists ;
        int isUser =0;
        String choice="";
        do {
            System.out.println("Tài Khoản: ");
            String account = scanner.nextLine();
            System.out.println("Mật Khẩu: ");
            String password = scanner.nextLine();
            if (password.equals("210104") && account.equals("Admin")) {
                isUser = 1;
                break;
            }
            accountExists = isAccount(account);
            if (accountExists) {
                num = checkUser( password);
                if (num == -1){
                    System.err.println("Đặng nhập thất bại!!");
                    System.err.println("Bạn Kiểm Tra Lại Tài Khoản Hoặc Mật Khẩu!!");
                }else {
                    isUser=2;
                    flag=false;
                }
            }else {
                System.err.println("Tài Khoản Không Tồn Tại!");
                System.out.println("0.Thoát");
                choice = scanner.nextLine();
            }
            if(choice.equals("0")){break;}
        }while (flag);
        return isUser;
    }
    private boolean isAccount(String account){
        boolean flag = false;
        for (int i = 0; i < listUsers.size(); i++) {
            if (listUsers.get(i).getAccount().equals(account)){
                flag = true;
                break;
            }
        }
        return flag;
    }
    public int checkUser ( String password) {
        int flag = -1;
        for (int i = 0; i < listUsers.size(); i++) {
            if (listUsers.get(i).getPassword().equals(password)) {
                flag = i;
                indexUser = i;
                numPhone = listUsers.get(i).getNumPhone();
                break;
            }
        }
        return flag;
    }
    public int indexUsers(){
        return indexUser;
    }
    public String numPhoneUser(){
        return numPhone;
    }
    public void outAdmin(Scanner scanner) throws InterruptedException {
        ISystemMenu menu = new ISystemMenu();
        menu.systemMenu(scanner);
    }
    public int register(Scanner scanner) {
        readFile();
        boolean flag = true;
        do {
            System.out.println("Tài Khoản: ");
            String account = scanner.nextLine();
            System.out.println("Mật Khẩu: ");
            String password = scanner.nextLine();
            System.out.println("Tên: ");
            String name = scanner.nextLine();
            System.out.println("Số điện thoại: ");
            String numPhone = scanner.nextLine();
            if (checkRegexPhoneNumber(numPhone)) {
                int choice = isAccountAlreadyExists(account, numPhone);
                if (choice == -1) {
                    listUsers.add(new User(account, password, name, numPhone));
                    fileUser.writeFile(listUsers, PATH_USER);
                    flag = false;
                }else {
                    System.err.println("Tài khoản đã tồn tại!!");
                }
            } else {
                System.err.println("Nhập sai số điện thoại hoặc số điện thoại đã tồn tại!!");
            }
            checkUser( password);
        }while (flag);
        return 2;
    }
    private int isAccountAlreadyExists(String account,String numPhone){
        int flag = -1;
        for (int i = 0; i < listUsers.size(); i++) {
            if ((listUsers.get(i).getAccount().equals(account) || listUsers.get(i).getNumPhone().equals(numPhone))
                || account.equals("Admin")){
                flag = i;
                break;
            }
        }
        return flag;
    }
    public boolean checkRegexPhoneNumber(String numPhone) {
        Pattern pattern = Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}");
        Matcher matcher = pattern.matcher(numPhone);
        return matcher.matches();
    }
}
