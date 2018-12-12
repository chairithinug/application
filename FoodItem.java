/**
 * Filename:   Main.java
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

import java.util.HashMap;

/**
 * This class represents a food item with all its properties.
 * 
 */
public class FoodItem {
	// The name of the food item.
	private String name;

	// The id of the food item.
	private String id;

	// Map of nutrients and value.
	private HashMap<String, Double> nutrients;

	/**
	 * Constructor
	 * 
	 * @param name name of the food item
	 * @param id   unique id of the food item
	 */
	public FoodItem(String id, String name) {
		this.name = name;
		this.id = id;
		nutrients = new HashMap<String, Double>();
	}

	/**
	 * Gets the name of the food item
	 * 
	 * @return name of the food item
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the unique id of the food item
	 * 
	 * @return id of the food item
	 */
	public String getID() {
		return this.id;
	}

	/**
	 * Gets the nutrients of the food item
	 * 
	 * @return nutrients of the food item
	 */
	public HashMap<String, Double> getNutrients() {
		return this.nutrients;
	}

	/**
	 * Adds a nutrient and its value to this food. If nutrient already exists,
	 * updates its value.
	 */
	public void addNutrient(String name, double value) {
		nutrients.put(name, value); // this already contain the update
	}

	/**
	 * Returns the value of the given nutrient for this food item. If not present,
	 * then returns 0.
	 */
	public double getNutrientValue(String name) {
		if (nutrients.get(name) != null) {
			return nutrients.get(name);
		} else {
			return 0;
		}
	}

	@Override
	public String toString() {
		return this.name;
	}
}
