import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents a food item with all its properties.
 * 
 * @author aka
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
     * @param name name of the food item
     * @param id unique id of the food item 
     */
    public FoodItem(String id, String name) {
    	this.name = name;
    	this.id = id;
        nutrients=new HashMap<String,Double>();
       // Scanner scnr = new Scanner ("foodItems.csv");
//         String info = null;
//         Scanner sc = new Scanner(info);
//         while (scnr.hasNextLine()) {
//         	info = scnr.nextLine();
//         	if (sc.hasNext()) {
//         		if (sc.next() == id && sc.hasNext()) { // not sure if has has
//         			if (sc.next() == name) {
//         				break;
//         			}
//         		}
//         	}
//         }
//         while (sc.hasNext()) {
//         	String n_type = null;
//         	Double data = 0.0;
//         	if (sc.hasNext()) {
//         		n_type = sc.next();
//         	}
//         	else break;
//         	if (sc.hasNextDouble()) {
//         		data = sc.nextDouble();
//         	}
//         	else break;
//         	if (n_type != null) {
//         		nutrients.put(n_type, data);
//         	}
        	
//         }
//         scnr.close();
    }
    
    /**
     * Gets the name of the food item
     * 
     * @return name of the food item
     */
    public String getName() {
//     	if (name == null) {
//     		System.out.println("No information under this name yet");
//     		return null;
//     	}
        return this.name;
    }

    /**
     * Gets the unique id of the food item
     * 
     * @return id of the food item
     */
    public String getID() {
//     	if (id == null) {
//     		System.out.println("No such ID found");
//     		return null;
//     	}
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
     * Adds a nutrient and its value to this food. 
     * If nutrient already exists, updates its value.
     */
    public void addNutrient(String name, double value) {
       // if (name != null) {//Check for valid data elsewhere??
        	nutrients.put(name, value); // this already contain the update
        	// not so sure about if correctly insert
      //  }
//         else {
//         	System.out.println("given invalid name information.");
//         }
    }

    /**
     * Returns the value of the given nutrient for this food item. 
     * If not present, then returns 0.
     */
    public double getNutrientValue(String name) {
//         if (nutrients.containsKey(name)) {
            if(nutrients.get(name)!=null){
                return nutrients.get(name);
            }else{
              return 0;
            }

        	
//         }
//         else {
//         	System.out.println("no such nutrient.");
//         }
//         return -1;
//     }
    
}
