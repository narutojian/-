import Action.MenuPage;
import Action.StartPage;
import Entity.Customer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        Customer customer = new Customer();

        System.out.println("Welcome to Canteen Management System!");
        StartPage startPage = new StartPage();
        startPage.handleMenu(customer);

        MenuPage menuPage = new MenuPage();
        menuPage.handleMenu(customer);

    }
}
