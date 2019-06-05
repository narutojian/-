package Action;

import Entity.Food;
import Entity.Order;
import Entity.OrderDetail;
import Util.Cached;

import java.util.HashMap;
import java.util.Scanner;

public class FoodOrderPage {
    private HashMap<Integer,Boolean> foodstate;
    private HashMap<Integer,Integer> orderdata;

    public FoodOrderPage(){
        foodstate = new HashMap<>();
        orderdata = new HashMap<>();
    }

    private void menu(){

        System.out.println("        1. select food");
        System.out.println("        2. view orders");
        System.out.println("        3. modify orders");
        System.out.println("        4. pay");
        System.out.println("        5. back");
    }

    private void menuOfPay(){

        System.out.println("        1. Cash ");
        System.out.println("        2. AliPay");
        System.out.println("        3. WeChatPay");
        System.out.println("        4. E-currencyPay ");
        System.out.println("        5. CreditCardPay ");
    }

    public void handleMenu(Food[] foods){

        Scanner scanner = new Scanner(System.in);
        String choice;
        boolean flag = true;
        int id;
        int num;

        while (flag){

            menu();
            System.out.print("please input: ");
            choice = scanner.next();

            switch (choice){

                case "1":

                    while (true){
                        System.out.print("please input Id: ");
                        id = scanner.nextInt();
                        if (!checkId(id,foods))
                            System.out.println("Wrong Id!");
                        else break;
                    }
                    System.out.print("please input number of this food: ");
                    num = scanner.nextInt();
                    foodstate.put(id,true);
                    orderdata.put(id,num);
                    break;
                case "2":
                    showOrder(foods);
                    System.out.println("total price: "+getTotalPrice(foods));
                    break;
                case "3":
                    showOrder(foods);
                    OrderModifyPage orderModifyPage = new OrderModifyPage();
                    orderModifyPage.handleMenu(foodstate,orderdata);
                    break;
                case "4":
                    double totalPrice = getTotalPrice(foods);
                    System.out.println("total price: "+totalPrice);
                    System.out.println("Ways of pay: ");
                    menuOfPay();
                    String way;
                    Order order = new Order();
                    order.setDateTime();
                    order.setDiscount();
                    order.setCid(Cached.getCid());
                    order.setBid(foods[0].getBid());
                    order.setTotalprice(totalPrice);
                    order.setOrderprice();
                    while (true){
                        System.out.print("please input: ");
                        way = scanner.next();

                        if (Integer.parseInt(way) >= 1 && Integer.parseInt(way) <=5){
                            order.setWid(Integer.parseInt(way));
                            break;
                        }
                    }
                    order.addOrder();

                    System.out.println("your discount is: "+order.getDiscount());
                    System.out.println("final cost is: "+order.getOrderprice());

                    for (int key :
                            foodstate.keySet()) {
                        if (foodstate.get(key)){
                            OrderDetail orderDetail = new OrderDetail();
                            orderDetail.setFid(key);
                            orderDetail.setOid(Cached.getOid());
                            orderDetail.setFnum(orderdata.get(key));
                            orderDetail.addOderDetail(foods[0].getBid());
                        }
                    }
                    break;
                case "5":
                    flag = false;
                    break;
            }
        }
    }

    private boolean checkId(int id,Food[] foods){

        for (int i = 0;i<foods.length;i++){
            if (foods[i].getFid() == id)
                return true;
        }
        return false;
    }

    private String getFoodName(int id, Food[] foods){

        for (int i = 0;i<foods.length;i++){
            if (foods[i].getFid() == id)
                return foods[i].getFname();
        }
        return null;
    }

    private double getPrice(int id, Food[] foods){

        for (int i = 0;i<foods.length;i++){
            if (foods[i].getFid() == id)
                return foods[i].getFprice();
        }
        return 0;
    }

    private void showOrder(Food[] foods){

        System.out.println(String.format("%-15s%-15s%-15s","Id","Name","Number"));
        for (int key :
                foodstate.keySet()) {
            if (foodstate.get(key))
                System.out.println(String.format("%-15d%-15s%-15d",key,getFoodName(key,foods),orderdata.get(key)));
        }
    }

    private double getTotalPrice(Food[] foods){

        double total = 0;
        for (int key :
                foodstate.keySet()) {
            if (foodstate.get(key))
                total += orderdata.get(key)*getPrice(key,foods);
        }
        return total;
    }
}
