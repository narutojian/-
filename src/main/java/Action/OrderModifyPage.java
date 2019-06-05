package Action;

import java.util.HashMap;
import java.util.Scanner;

public class OrderModifyPage {

    private void menu(){

        System.out.println("        1. delete order of this food ");
        System.out.println("        2. modify this number of food ");
        System.out.println("        3. back ");
    }

    public void handleMenu(HashMap<Integer,Boolean> foodState, HashMap<Integer,Integer> orderData){

        Scanner scanner = new Scanner(System.in);
        String choice;
        boolean flag = true;
        int id;
        int num;

        while (true){
            System.out.print("please input Id that you want to modify: ");
            id = scanner.nextInt();

            if (foodState.containsKey(id))
                break;
        }

        while (flag){

            menu();
            System.out.print("please input: ");
            choice = scanner.next();

            switch (choice){

                case "1":
                    foodState.put(id,false);
                    break;
                case "2":
                    while (true){
                        System.out.print("please input new number: ");

                        if ((num = scanner.nextInt()) > 0) {
                            orderData.put(id,num);
                            break;
                        }
                    }
                    break;
                case "3":
                    flag = false;
                    break;
            }
        }
    }
}
