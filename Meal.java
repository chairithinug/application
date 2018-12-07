package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class is a meal consisting of FoodItem and the number of each food
 * 
 * @author Dteam44
 *
 */
public class Meal {
	/**
	 * An array containing what food is in this meal
	 */
	private ArrayList<FoodItem> mealList;
	/**
	 * An array containing how many dishes of that food in the meal
	 */
	private ArrayList<Integer> amount;

	/**
	 * A constructor of Meal
	 */
	public Meal() {
		mealList = new ArrayList<FoodItem>();
		amount = new ArrayList<Integer>();
	}

	/**
	 * Return a list of Food in the meal
	 * 
	 * @return mealList
	 */
	public List<FoodItem> getMeal() {
		return mealList;
	}

	/**
	 * Return a list of number of each food in the meal
	 * 
	 * @return amount
	 */
	public List<Integer> getAmount() {
		return amount;
	}

	/**
	 * Add food to a meal
	 * 
	 * @param food
	 */
	public void addToMeal(FoodItem food) {
		Iterator<FoodItem> mealIterator = mealList.iterator();
		while (mealIterator.hasNext()) {
			FoodItem curFood = mealIterator.next();
			// If this food is already added
			if (curFood.getID().equals(food.getID())) {
				// Increase the amount of food
				int index = mealList.indexOf(curFood);
				Integer numberOfFood = amount.get(index);
				amount.set(index, ++numberOfFood);

				return;
			}
		}
		// Food is not in the list, add it and set amount to 1.
		mealList.add(food);
		amount.add(new Integer(1));
	}

	/**
	 * Analyze all food a meal
	 * 
	 * @return totalData a list of the amount of each nutrient
	 */
	public List<Double> analyzeMeal() {
		double calories = 0;
		double fat = 0;
		double carbohydrate = 0;
		double fiber = 0;
		double protein = 0;

		Iterator<FoodItem> mealIterator = mealList.iterator();
		while (mealIterator.hasNext()) {
			FoodItem curFood = mealIterator.next();
			// sum up nutrients
			calories += curFood.getNutrientValue("calories");
			fat += curFood.getNutrientValue("fat");
			carbohydrate += curFood.getNutrientValue("carbohydrate");
			fiber += curFood.getNutrientValue("fiber");
			protein += curFood.getNutrientValue("protein");
		}
		// generate a list of report
		ArrayList<Double> totalData = new ArrayList<Double>();
		totalData.add(calories);
		totalData.add(fat);
		totalData.add(carbohydrate);
		totalData.add(fiber);
		totalData.add(protein);
		return totalData;
	}

	/**
	 * Remove food from a meal
	 * 
	 * @param food food to be removed
	 */
	public void removeFromMeal(FoodItem food) {
		Iterator<FoodItem> mealIterator = mealList.iterator();
		while (mealIterator.hasNext()) {
			FoodItem curFood = mealIterator.next();
			if (curFood.getID().equals(food.getID())) {
				int index = mealList.indexOf(curFood);
				// if this is the last food count
				if (amount.get(index) <= 1) {
					mealList.remove(index);
					amount.remove(index);
				} else { // decrement the food count
					Integer numberOfFood = amount.get(index);
					amount.set(index, --numberOfFood);
				}
				return;
			}
		}
		// error there is no food to be removed
		System.out.println("No Food! Can't delete!");
	}

	/**
	 * Reset the whole meal
	 */
	public void clearMeal() {
		mealList = null;
		amount = null;
	}

}