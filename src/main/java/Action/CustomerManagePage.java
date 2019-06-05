package Action;

import Entity.Customer;

import java.util.Scanner;

public class CustomerManagePage {

    public void Menu(){
        System.out.println("        1. Modify customer information");
        System.out.println("        2. View customer information");
        System.out.println("        3. back");
    }

    public void handleMenu(Customer customer){

        Scanner scanner = new Scanner(System.in);
        String choice;
        boolean flag = true;
        CustomerModifyPage customerModifyPage = null;

        while (flag){
            Menu();
            System.out.print("please input: ");
            choice = scanner.next();

            switch (choice){
                case "1":
//                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    customerModifyPage = new CustomerModifyPage();
                    customerModifyPage.handleMenu(customer);
                    break;
                case "2":
//                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    customer.Display();
                    System.out.println("Press 'q' to return....");

                    while (!scanner.next().equals("q")){}
                    //                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    break;
                case "3":
                    flag = false;
                    //                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    break;
            }
        }
    }
}
