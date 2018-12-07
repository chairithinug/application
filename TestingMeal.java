package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestingMeal {
	// This is a tester
	public static void main(String args[]) {

		FoodData list = new FoodData();
		list.loadFoodItems("src/application/foodItems.csv");
		list.saveFoodItems("QQFORLIFE.txt");
		
//		list.loadFoodItems("src/application/foodItems.csv");
//		int i = 0;
//		List<FoodItem> al = list.getAllFoodItems();
//		Iterator<FoodItem> foodIterator = al.iterator();
//		while (foodIterator.hasNext()) {
//			i++;
//			System.out.println(foodIterator.next().getName());
//		}
//		System.out.println(i);

//		FoodItem newFood = new FoodItem("QQFORLIFE","QQ");
//		newFood.addNutrient("carbohydrate", 999999);
//		newFood.addNutrient("calories", 999999);
//		newFood.addNutrient("protein", 999999);
//		newFood.addNutrient("fat", 999999);
//		newFood.addNutrient("fiber", 999999);
//		
//		list.addFoodItem(newFood);
//		List<FoodItem> al = list.indexes.get("carbohydrate").rangeSearch(0.0, ">=");
//		Iterator<FoodItem> foodIterator = al.iterator();
//		while (foodIterator.hasNext()) {
//			System.out.println(foodIterator.next().getName());
//		}
//		System.out.println();
		
//		list.loadFoodItems("src/application/foodItems.csv");
		
		
		//List<FoodItem> al = list.indexes.get("carbohydrate").rangeSearch(0.0, "==");

//		Iterator<FoodItem> foodIterator = al.iterator();
//		while (foodIterator.hasNext()) {
//			System.out.println(foodIterator.next().getName());
//		}
//		System.out.println();
//
//		List<FoodItem> bl = list.filterByName("shrimp");
//		if (bl == null) {
//			System.out.println("NULL");
//		} else {
//			Iterator<FoodItem> foodIterator2 = bl.iterator();
//			while (foodIterator2.hasNext()) {
//				System.out.println(foodIterator2.next().getName());
//			}
//		}
		
//		List<String> rule = new ArrayList<String>();
//		rule.add("carbohydrate == 0");
//		rule.add("protein == 0");
//		rule.add("fat == 0");
//		rule.add("calories == 0");
//		rule.add("fiber == 0");
//		
//		List<FoodItem> b2 = list.filterByNutrients(rule);
//		if (b2 == null) {
//			System.out.println("NULL");
//		} else {
//			Iterator<FoodItem> foodIterator3 = b2.iterator();
//			while (foodIterator3.hasNext()) {
//				System.out.println(foodIterator3.next().getName());
//			}
//		}

//		Meal dinner = new Meal();

//		dinner.addToMeal();

//		System.out.println("Total calories is: " + dinner.analyzeMeal().get(0));
//		System.out.println("Total fat is: " + dinner.analyzeMeal().get(1));
//		System.out.println("Total carbohydrate is: " + dinner.analyzeMeal().get(2));
//		System.out.println("Total fiber is: " + dinner.analyzeMeal().get(3));
//		System.out.println("Total protein is: " + dinner.analyzeMeal().get(4));

	}
}
