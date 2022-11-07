import iSystem.ISystemMenu;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ISystemMenu menu = new ISystemMenu();
        Scanner scanner = new Scanner(System.in);
        menu.systemMenu(scanner);
    }
}