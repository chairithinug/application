package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Meal {
	private ArrayList<FoodItem> mealList;
	private ArrayList<Integer> amount;

	public Meal() {
		mealList = new ArrayList<FoodItem>();
		amount = new ArrayList<Integer>();
	}

	public List<FoodItem> getMeal() {
		return mealList;
	}

	public List<Integer> getAmount() {
		return amount;
	}

	public void addToMeal(FoodItem food) {
		Iterator<FoodItem> mealIterator = mealList.iterator();
		while (mealIterator.hasNext()) {
			FoodItem curFood = mealIterator.next();
			if (curFood.id.equals(food.id)) {
				int index = mealList.indexOf(curFood);
				Integer numberOfFood = amount.get(index);
				amount.set(index, ++numberOfFood);
				return;
			}
		}
		mealList.add(food);
		amount.add(new Integer(1));
	}
	
	public List<Integer> analyzeMeal() {
		int carb = 0;
		Iterator<FoodItem> mealIterator = mealList.iterator();
		while (mealIterator.hasNext()) {
			FoodItem curFood = mealIterator.next();
			carb += curFood.carb;
		}
		ArrayList<Integer> totalData = new ArrayList<Integer>();
		totalData.add(carb);  // add carb
		// add fat
		
		return totalData;
	}

	public void removeFromMeal(FoodItem food) {
		Iterator<FoodItem> mealIterator = mealList.iterator();
		while (mealIterator.hasNext()) {
			FoodItem curFood = mealIterator.next();
			if (curFood.id.equals(food.id)) {
				int index = mealList.indexOf(curFood);
				if (amount.get(index) <= 1) {
					mealList.remove(index);
					amount.remove(index);
				} else {
					Integer numberOfFood = amount.get(index);
					amount.set(index, --numberOfFood);
				}
				return;
			}
		}
		System.out.println("No Food! Can't delete!");
	}

	public void clearMeal() {
		mealList = null;
		amount = null;
	}

	// TO BE DELETED
	static class FoodItem {
		public int carb;
		public String id;

		public FoodItem(String name, int x) {
			carb = x;
			id = name;
		}
	}

	public static void main(String args[]) {
		Meal dinner = new Meal();
		for (int i = 0; i < 5; i++) {
			dinner.addToMeal(new FoodItem("Food" + i, i));
		}

		dinner.addToMeal(new FoodItem("Food0", 0));

		for (int i = 0; i < dinner.getMeal().size(); i++) {
			System.out.print(dinner.getMeal().get(i).id);
			System.out.println(" " + dinner.getAmount().get(i));
		}
		System.out.println();
		dinner.removeFromMeal(new FoodItem("Food0", 0));
		dinner.removeFromMeal(new FoodItem("Food0", 0));
		dinner.removeFromMeal(new FoodItem("Food0", 0));
		dinner.removeFromMeal(new FoodItem("Food1", 1));
		
		for (int i = 0; i < dinner.getMeal().size(); i++) {
			System.out.print(dinner.getMeal().get(i).id);
			System.out.println(" " + dinner.getAmount().get(i));
		}
		
		System.out.println("Total Carb is: " + dinner.analyzeMeal().get(0));
	}
}