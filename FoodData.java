package application;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents the backend for managing all the operations associated
 * with FoodItems
 *
 * @author sapan (sapan@cs.wisc.edu)
 */
public class FoodData implements FoodDataADT<FoodItem> {

	// List of all the food items.
	private List<FoodItem> foodItemList;

	private int branchingFactor; // NEW

	// Map of nutrients and their corresponding indices
	private HashMap<String, BPTree<Double, FoodItem>> indices;

	private boolean flagError = false;

	public boolean getStatusError() {
		return flagError;
	}

	public void setStatusError(boolean t) {
		flagError = t;
	}

	/**
	 * Public constructor
	 *
	 */
	public FoodData() {
		this.foodItemList = new ArrayList<FoodItem>();
		this.indices = new HashMap<String, BPTree<Double, FoodItem>>();
		this.branchingFactor = 4;
	}
	
	/**
	* This method Load file path of the food item to the data file
	*
	*@param the path of the files 
	**/
	
	@Override
	public void loadFoodItems(String filePath) {
		try {
			this.foodItemList = Files.lines(new File(filePath).toPath()).map(x -> x.split(","))
					.filter(x -> x.length != 0).map(x -> {
						FoodItem food = new FoodItem(x[0], x[1]);
						for (int i = 2; i < x.length; i += 2)
							food.addNutrient(x[i].toLowerCase(), Double.parseDouble(x[i + 1]));
						return food;
					}).collect(Collectors.toList());

			this.foodItemList.forEach((FoodItem foodItem) -> {
				if (foodItem != null) {
					foodItem.getNutrients().forEach((String nutrient, Double value) -> {
						if (!this.getIndices().containsKey(nutrient)) {
							this.getIndices().put(nutrient.toLowerCase(), new BPTree<>(branchingFactor));
						}
						this.getIndices().get(nutrient).insert(value, foodItem);
					});
				}
			});
		} catch (IOException e) {
			flagError = true;
			e.printStackTrace();
		}
	}
	
	/**
	* This method get a list of items containing string
	*
	* @param the names of fooditems
	**/
	@Override
	public List<FoodItem> filterByName(String substring) {
		return this.foodItemList.stream().filter(l -> l.getName().toLowerCase().contains(substring.toLowerCase()))
				.collect(Collectors.toList());
	}
	
	/**
	* This method get a filtered list of items
	*
	* @param rules for filter
	**/
	@Override
	public List<FoodItem> filterByNutrients(List<String> rules) {
		List<List<FoodItem>> matches = rules.stream().map(rule -> rule.split(" ")).map(
				rule -> this.getIndices().get(rule[0].toLowerCase()).rangeSearch(Double.parseDouble(rule[2]), rule[1]))
				.collect(Collectors.toList());
		if (matches.size() > 0) {
			matches.forEach(list -> matches.get(0).retainAll(list));
			return matches.get(0);
		} else {
			return null;
		}
	}
	
	/**
	* This method add an item into a loaded data
	*
	* @param fooditem that is added 
	**/
	@Override
	public void addFoodItem(FoodItem foodItem) {
		foodItem.getNutrients().forEach((String nutrient, Double value) -> {
			if (!this.getIndices().containsKey(nutrient)) {
				this.getIndices().put(nutrient, new BPTree<>(branchingFactor));
			}
			this.getIndices().get(nutrient).insert(value, foodItem);
		});
		this.foodItemList.add(foodItem);
	}

	public HashMap<String, BPTree<Double, FoodItem>> getIndices() {
		return indices;
	}
	
	/** 
	*This method get a list of all items
	**/
	@Override
	public List<FoodItem> getAllFoodItems() {
		return this.foodItemList;
	}

	/**
	 * Save the list of food items in ascending order by name
	 *
	 * @param filename name of the file where the data needs to be saved
	 */
	@Override
	public void saveFoodItems(String filename) {
		try {
			foodItemList.sort(new Comparator<FoodItem>() {
				@Override
				public int compare(FoodItem f1, FoodItem f2) {
					return f1.getName().toLowerCase().compareTo(f2.getName().toLowerCase());
				}
			});
			Files.write(new File(filename).toPath(), this.foodItemList.stream().map(item -> {
				ArrayList<String> nutrients = new ArrayList<>();
				nutrients.add("calories");
				nutrients.add("" + item.getNutrientValue("calories"));
				nutrients.add("fat");
				nutrients.add("" + item.getNutrientValue("fat"));
				nutrients.add("carbohydrate");
				nutrients.add("" + item.getNutrientValue("carbohydrate"));
				nutrients.add("fiber");
				nutrients.add("" + item.getNutrientValue("fiber"));
				nutrients.add("protein");
				nutrients.add("" + item.getNutrientValue("protein"));
				return item.getID() + "," + item.getName() + "," + String.join(",", nutrients);
			}).collect(Collectors.toList()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
