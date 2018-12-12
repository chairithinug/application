/**
 * Filename:   Meal.java
 * Project:    Food Query and Meal Analysis
 * Version:    1.0
 * Date:       Nov 29th, 2018
 * Authors:    Anapat Chairithinugull, Brock Thern, Effy Chu, Zening Fang
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * Instructor: Deppeler (deppeler@cs.wisc.edu)
 * Credits:    
 * Bugs:       
 *
 * Due Date:   before 10:00 pm on November 30th
 */
package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class is a meal consisting of FoodItem and the number of each food
 * 
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
		Iterator<Integer> amountIterator = amount.iterator();
		while (mealIterator.hasNext() && amountIterator.hasNext()) {
			FoodItem curFood = mealIterator.next();
			Integer amountFood = amountIterator.next();
			// sum up nutrients
			calories += (curFood.getNutrientValue("calories") * amountFood);
			fat += (curFood.getNutrientValue("fat") * amountFood);
			carbohydrate += (curFood.getNutrientValue("carbohydrate") * amountFood);
			fiber += (curFood.getNutrientValue("fiber") * amountFood);
			protein += (curFood.getNutrientValue("protein") * amountFood);
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
	 * @param food
	 *            food to be removed
	 */
	public void removeFromMeal(FoodItem food) {
		Iterator<FoodItem> mealIterator = mealList.iterator();
		if (food == null) {
			return;
		}
		while (mealIterator.hasNext()) {
			FoodItem curFood = mealIterator.next();
			if (curFood.getID().equals(food.getID())) {
				int index = mealList.indexOf(curFood);
				if (amount != null && mealList != null) { // if this is the last food of on meal
					// if this is the last food count
					if (amount.get(index) == 1) {
						mealList.remove(index);
						amount.remove(index);
					} else { // decrement the food count
						Integer numberOfFood = amount.get(index);
						amount.set(index, --numberOfFood);
					}
				} else {
					clearMeal();
				}
				return;
			}
		}
		// error there is no food to be removed
		// System.out.println("No Food! Can't delete!");
	}

	/**
	 * Reset the whole meal
	 */
	public void clearMeal() {
		mealList.clear();
		amount.clear();
	}

}
