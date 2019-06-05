package Action;

import Entity.Customer;

import java.util.Scanner;

public class CustomerModifyPage {

    private String pwd;
    private String tel;

    public void Menu(){
        System.out.println("        1. Modify your password");
        System.out.println("        2. Modify you telephone");
        System.out.println("        3. Modify your password and telephone");
        System.out.println("        4. back");
    }

    public void handleMenu(Customer customer){

        Scanner scanner = new Scanner(System.in);
        String choice;
        boolean flag = true;

        while (flag){
            Menu();
            System.out.print("please input: ");
            choice = scanner.next();

            switch (choice){

                case "1":
                    System.out.print("please input new password: ");
                    pwd = scanner.next();
                    if (customer.modifyCustomer(pwd,1)) {
                        customer.setPwd(pwd);
                        System.out.println("Modify password successfully!");
                    }
                    else System.out.println("Fail to modify password!");
                    break;
                case "2":
                    System.out.print("please input your new telephone: ");
                    tel = scanner.next();
                    if (customer.modifyCustomer(tel,2)) {
                        customer.setTelephone(tel);
                        System.out.println("Modify telephone successfully!");
                    }
                    else System.out.println("Fail to modify telephone!");
                    break;
                case "3":
                    System.out.print("please input new password: ");
                    pwd = scanner.next();
                    System.out.print("please input new telephone: ");
                    tel = scanner.next();

                    if (customer.modifyCustomer(pwd,tel)){
                        customer.setPwd(pwd);
                        customer.setTelephone(tel);
                        System.out.println("Modify password and telephone successfully!");
                    }
                    else System.out.println("Fail to modify password and telephone!");
                case "4":
                    flag = false;
                    //                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    break;
            }
        }

//        scanner.close();
    }
}
