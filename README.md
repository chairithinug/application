# Fall 2018 Team Project: Food Query and Meal Analyzer

See Canvas assignment pages for details of the assignment.

## DTeam44:

- Anapat Chairithinugull(email:chairithinug@wisc.edu)
- Brock Thern(email:bthern@wisc.edu)
- Effy Chu(email:xchu6wisc.edu)
- Zening Fang(email:zfang27@wisc.edu)

## INSTRUCTIONS:
We are assuming we have unique ID's given to us by the user and any loaded files. We assume the format of the loaded files is correct.
TA's have verified these assumptions.

Import Button:
* Manually type in the location of the file you want to load in. You may need to manually type in the file path for the default foodItems.csv

Save Button:
* Saves your file in the same folder as the application folder

Food Count:
* To view the food count, hover your mouse over the food list and a pop-up will appear.

Create Button:
* To add new food to the food list, use this button.
* After pressing the Create Button, the user gets the Create pop-up. Hover mouse over the text fields to get tips on what data should be entered.

Meal Section of Homepage:
* To add food to meal list, select a food in the food list on the right. Click the Add Button in the lower-left area of the homepage.
* To remove food in meal list, select the food in the meal list and hit the Remove Button.
* Use Clear Button to clear the food in the meal list.

Filter Button:
* This button applies the food query rules.
* After pressing the Filter Button, the user gets the Filter pop-up. Hover mouse over the text fields to get tips on what data should be entered.
* To clear active filters, press the Filter Button, leave all fields empty, and press the Apply Button. This removes all filters.
* To remove a filter, press the Filter Button, choose the filters that you want excluding the one you want to remove, and press Apply.
* To view previously used filters, hover mouse over Filter Button on homepage.

## Download and install JDK 8

https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

## Download and install Eclipse Oxygen or later

https://www.eclipse.org/oxygen/

## Install JavaFX tools to Eclipse

1. Launch Eclipse (Oxygen or later)
2. Help -> Install New Software
3. Search for e(fx)clipse
4. Select All items
5. Click  Install
6. Accept certificate
7. Restart Eclipse

## Create a JavaFX project

1. Launch Eclipse
2. File -> New -> Other -> JavaFX -> JavaFX Project
3. Enter name
4. Leave source files in *package application*

## Add starter files to your JavaFX project

### clone from GitHub

Students may clone this repository and do the following from a local terminal window to get files in correct place for Eclipse JavaFX project.  Note: execute commands on the left and not the comments as shown on the right.

```
cd ~/cs400-workspace/p5                                  # cd to your local project directory
git clone https://github.com/cs400-deppeler/FoodQuery    # git clone to get files from this site
mv FoodQuery/.git .                                      # move .git file into current directory
mv FoodQuery/.gitignore .                                # moves .gitignore file into current directory
mv FoodQuery/* .                                         # moves all source and data files into current directory
rmdir FoodQuery                                          # remove the FoodQuery sub-directory
```

### Or, download files as in previous assignments from the assignment page

https://pages.cs.wisc.edu/~deppeler/cs400/assignments/p5/files/
