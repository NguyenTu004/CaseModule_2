import iSystem.ISystemMenu;
import manager.FileIO;
import material.Origin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ISystemMenu menu = new ISystemMenu();
        Scanner scanner = new Scanner(System.in);
        menu.systemMenu(scanner);
    }
}