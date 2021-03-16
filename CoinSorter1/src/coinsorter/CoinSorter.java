package coinsorter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CoinSorter {
	private String currency;
	private int minCoinIn;
	private int maxCoinIn;
	private ArrayList<Integer> coinList = new ArrayList<>();
	
	//constructor that takes parameters
	public CoinSorter(String currencyInput, int minCoinInput, int maxCoinInput, ArrayList<Integer> coinListInput) {
		//if coinListInput elements are 0 or less, throw an exception
		for(int i = 0; i < coinListInput.size(); i++) {
			if(coinListInput.get(i) <= 0) {
				throw new IllegalArgumentException("coinListInput elements must be greater than 0");
			} 
		}
		//if coinListInput is empty, throw an exception
		if(coinListInput.isEmpty()) {
			throw new IllegalArgumentException("coinListInput can't be empty");
		}
		//if minCoinInput or maxCoinInput are negative integers, throw an exception
		else if(minCoinInput < 0 || maxCoinInput < 0) {
			throw new IllegalArgumentException("minCoinInput and maxCoinInput must be equal to or greater than 0");			
		}
		//if minCoinInput or maxCoinInput are greater than 10000, throw an exception 
		else if(minCoinInput > 10000 || maxCoinInput > 10000) {
			throw new IllegalArgumentException("minCoinInput and maxCoinInput must be equal to or less than 10000");
		}
		//if minCoinInput is greater than maxCoinInput, throw an exception
		else if(minCoinInput > maxCoinInput) {
			throw new IllegalArgumentException("minCoinInput can't be greater than maxCoinInput");			
		}
		//if currencyInput is a number, throw an exception
		else if(currencyInput.replace(",", "").matches("-?\\d+(\\.\\d+)?")) {
			throw new IllegalArgumentException("currencyInput can't be a number");
		}
		//if currencyInput is blank, throw an exception
		else if(currencyInput.isBlank()) {
			throw new IllegalArgumentException("currencyInput can't be blank");
		}
		else {
			currency = currencyInput;
			minCoinIn = minCoinInput;
			maxCoinIn = maxCoinInput;
			coinList =  coinListInput;
		}	
	}
	
	//constructor that doesn't take parameters
	public CoinSorter() {
		minCoinIn = 0;
		maxCoinIn = 10000; 
		currency = "£";
		coinList = new ArrayList<Integer>(Arrays.asList(10, 20, 50, 100, 200));
	}
	
	//set currency, can't be a number or blank
	public void setCurrency(String currencyIn) {
		if(!currencyIn.replace(",","").matches("-?\\d+(\\.\\d+)?") && !currencyIn.isBlank()) {
			currency = currencyIn;
		}
	}
	
	//set minimum coin, must be equal to or greater than 0 and maxCoinIn; can't be greater than 10000 
	public void setMinCoinIn(int minCoinInput) {
		//check that minCoinInput is at least 0 
		if(minCoinInput >= 0 && minCoinIn <= maxCoinIn && minCoinInput <= 10000){
			minCoinIn = minCoinInput;				
		}	
	}
	
	//set maximum coin, must be equal to or greater than minCoinIn
	public void setMaxCoinIn(int maxCoinInput) {
		//check that maxCoinInput is equal to or greater than minCoinIn and 0; can't be greater than 10000 
		if(maxCoinInput >= minCoinIn && maxCoinInput > 0 && maxCoinInput <= 10000) {
			maxCoinIn = maxCoinInput;
		} 
	}
	
	//get currency
	public String getCurrency() {
		return currency;
	}
	
	//get minimum coin
	public int getMinCoinIn() {
		return minCoinIn;
	}
	
	//get maximum coin
	public int getMaxCoinIn() {
		return maxCoinIn;
	}
	
	//return a string of current coin denominations
	public String printCoinList() {
		//sort the list from lowest to highest
		Collections.sort(coinList);
		return "The current coin denominations in circulation are: " + coinList.toString().replace("[", "").replace("]", "");
	}
	
	//coinCalculator, takes two integers as parameters and returns the result as a string 
	public String coinCalculator(int totalValue, int coinType) {
		int numberOfCoins, remainder;
		numberOfCoins = totalValue / coinType;
		remainder = totalValue % coinType;
		return "A total of " + numberOfCoins + " x " + coinType + "p coins can be exchanged, with a remainder of " + remainder + "p";
	}
	
	//multiCoinCalculator, takes two integers as parameters, returns the results as a string
	public String multiCoinCalculator(int totalValue, int coinType) {
		int numberOfCoins, remainder;
		coinList.clone();
		
		//sorting the coinList from highest to lowest 
		Collections.sort(coinList, Collections.reverseOrder());	
		//initialise a remainder variable and set the value to the total value
		remainder = totalValue;		
		//create a list to store the values as we iterate through coinList
		ArrayList<String> coinCount = new ArrayList<>();	
		
		//iterate through coinList
		for(int i = 0; i < coinList.size(); i++) {
			//if denomination is not the excluded one
			if(coinList.get(i) != coinType) {
				//get the number of coins for the denomination in question
				numberOfCoins = remainder / coinList.get(i);
				//set new value for the remainder variable
				remainder = remainder - (numberOfCoins * coinList.get(i));
				//add number of coins and the denomination in question to the list
				coinCount.add(Integer.toString(numberOfCoins) +  " x " + coinList.get(i) + "p");				
			} 
			else {
				//if denomination is the excluded one, add the following string to the list
				coinCount.add("0 x " + coinList.get(i) + "p");		
				continue;								
			}			
		}
		return coinCount.toString().replace("[", "").replace("]", "") + ", with a remainder of " + remainder + "p";			
	}
	
	//returns a string of program configurations
	public String displayProgramConfigs() {
		return "Currency: " + currency + "\nMin Value: " + minCoinIn + "\nMax Value: " + maxCoinIn;
	}
}

