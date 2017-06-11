package tenzi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Tenzi {
	// creating fields
	private static List<Integer> diceList;
	private static Random r;

	public static void main(String[] args) {
		// initializing fields
		diceList = new ArrayList<Integer>();
		r = new Random();

		// rollNum variable to keep track of how many times the dice are
		// re-rolled
		int rollNum = 0;

		// do-while loop that rolls the dice and then checks if they are equal
		// if the dice are not equal, the loop continues
		do {
			rollDice();
			rollNum++;
			System.out.println(diceList);
		} while (allDiceEqual() == false);

		// once the do-while loop breaks, prints diceArray
		// and how many rolls it took to get all of the numbers to be equal

		System.out.println("All 10 dice are equal after " + rollNum + " rolls!");
	}

	// void rollDice() method that assigns a random number from 1-6
	// to each number in the diceArray
	private static void rollDice() {
		getMostFrequentDice();

		// filling in any empty spaces in diceArray w/ random nums
		while (diceList.size() < 10) {
			diceList.add((r.nextInt(6) + 1));
		}
	}

	// boolean diceEqual() method that checks if
	// all the numbers in diceArray are equal
	// returns false if they are not equal, and true if they are
	private static boolean allDiceEqual() {
		for (int i = 0; i < (diceList.size() - 1); i++) {
			if (diceList.get(i) != diceList.get(i + 1)) {
				return false;
			}
		}
		return true;
	}

	// getMostFrequentDice method to delete everything in diceList
	// except for the largest group of dice w/ the same value
	private static void getMostFrequentDice() {
		// duplicateMap to hold each dice value and its frequency
		Map<Integer, Integer> diceFrequencyMap = new HashMap<Integer, Integer>();

		// sorting diceList
		Collections.sort(diceList);

		// for loop to initialize duplicateMap
		for (int i = 1; i <= 6; i++) {
			diceFrequencyMap.put(i, Collections.frequency(diceList, i));
		}

		// creating list that contains the values of the map
		List<Integer> mapValues = new ArrayList<Integer>(diceFrequencyMap.values());

		// sorting the map value list
		Collections.sort(mapValues);

		// getting largest frequency quantity and value
		int largestFrequencyQuantity = mapValues.get(mapValues.size() - 1);
		int largestFrequencyValue = (Integer) getKeyFromValue(diceFrequencyMap, largestFrequencyQuantity);

		// clearing diceList
		diceList.clear();

		// setting diceList to most frequent dice group
		for (int i = 0; i < largestFrequencyQuantity; i++) {
			diceList.add(largestFrequencyValue);
		}
	}

	public static Object getKeyFromValue(Map map, Object value) {
		for (Object o : map.keySet()) {
			if (map.get(o).equals(value)) {
				return o;
			}
		}
		return null;
	}

}
