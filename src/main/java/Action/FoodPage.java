package Action;

import Entity.Business;
import Entity.Food;
import Entity.FoodCategory;

public class FoodPage {

    private Food[] foods;

    public void init(Business business){

        foods = new Food[business.getFid().length];

        for (int i = 0;i<business.getFid().length;i++){
            foods[i] = new Food();
            foods[i].setFid(business.getFid()[i]);
            foods[i].setBid(business.getBid());
            foods[i].setFood();
        }
    }

    public void showFoodInfo(){
        System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s","Category","Id","Name","Price","Storage","SupportTime"));
        for (int i = 0;i< FoodCategory.foodcategory.length;i++){
            System.out.println(FoodCategory.foodcategory[i]);
            matchCategory(FoodCategory.foodcategory[i]);
        }
    }

    private void matchCategory(String category){

        for (int i = 0;i<foods.length;i++){
            if (foods[i].getFoodcategory().equals(category))
                System.out.println(String.format("%-15s%-15d%-15s%-15.2f%-15d%-15s"," ",foods[i].getFid(),foods[i].getFname(),foods[i].getFprice(),foods[i].getFstorage(),foods[i].getSupprotTime()));
        }
    }

    public Food[] getFoods() {
        return foods;
    }
}
