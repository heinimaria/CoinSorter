package coinsorter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class testCoinSorter {
	
	public static void main(String[] args) {
		String answer, subAnswer, currency;
		int minInputCoin, maxInputCoin, totalValue, coinDenomination;
		
		//create separate scanner objects for string and int inputs 
		Scanner stringKeyboard = new Scanner(System.in);
		Scanner intKeyboard = new Scanner(System.in);
		
		ArrayList <Integer> coinDenominations = new ArrayList<Integer>(Arrays.asList(10,20,50,100,200));
		
		//create a new CoinSorter class object 
		CoinSorter cs = new CoinSorter("£", 0, 10000, coinDenominations);
		
		/* MAIN MENU
		 	Display main menu, take input as a string to avoid runtime error
		 	If user input is not one of the menu numbers, the program will just display the main menu again
		  */ 
		do 
			{
			System.out.println("***Coin Sorter - Main Menu***");
			System.out.println("\n1 - Coin calculator");
			System.out.println("2 - Multiple coin calculator");
			System.out.println("3 - Print coin list");
			System.out.println("4 - Set details");
			System.out.println("5 - Display program configurations");
			System.out.println("6 - Quit the program");
			answer = stringKeyboard.nextLine();
			switch(answer) { 
				case "1": 	/*  COIN CALCULATOR
								Prompt for an input until the user enters a valid integer within the current minCoinIn and maxCoinIn range
							 	As the scanner takes an integer, we need to validate the input beforehand to avoid runtime errors
							 */
							do { 
								System.out.println("Enter a value in the range of " + cs.getMinCoinIn() + "-" + cs.getMaxCoinIn());
								//check whether input type is integer, if not, display error message and prompt for an input again
								while(!intKeyboard.hasNextInt()) {
									System.out.println("Invalid input. Enter a whole number in the range of " + cs.getMinCoinIn() + "-" + cs.getMaxCoinIn());
									intKeyboard.next();								
								}
								totalValue = intKeyboard.nextInt();
								if(totalValue < cs.getMinCoinIn() || totalValue > cs.getMaxCoinIn()) {
									System.out.print("Invalid input. ");
								}	
							} while(totalValue < cs.getMinCoinIn() || totalValue > cs.getMaxCoinIn());
							
							//prompt for an input until the user enters a valid denomination
							System.out.println(cs.printCoinList());
							do {
								System.out.println("Enter a valid denomination");
								//check whether input type is integer, if not, display error message and prompt for an input again
								while(!intKeyboard.hasNextInt()) {
									System.out.println("Invalid input. Enter a valid denomination");
									intKeyboard.next();							
								}
								coinDenomination = intKeyboard.nextInt();
								if(!coinDenominations.contains(coinDenomination)) {
									System.out.print("Invalid input. ");
								}	
							} while(!coinDenominations.contains(coinDenomination));
							System.out.println(cs.coinCalculator(totalValue, coinDenomination) + "\n");
							break;
							
				case "2": 	/*  MULTIPLE COIN CALCULATOR
								Prompt for an input until the user enters a valid integer: the number must fall within the current minCoinIn and maxCoinIn range
							 	As the scanner takes an integer, we need to validate the input type beforehand to avoid runtime errors
							 */
							do{
								System.out.println("Enter a value in the range of " + cs.getMinCoinIn() + "-" + cs.getMaxCoinIn());
								while(!intKeyboard.hasNextInt()) {
									System.out.println("Invalid input, enter a whole number in the range of " + cs.getMinCoinIn() + " and " + cs.getMaxCoinIn());
									intKeyboard.next();
								}
								totalValue = intKeyboard.nextInt();
								if(totalValue < cs.getMinCoinIn() || totalValue > cs.getMaxCoinIn() ) {
									System.out.print("Invalid input. ");									
								}
							} while(totalValue < cs.getMinCoinIn() || totalValue > cs.getMaxCoinIn());
							
							//prompt for an input until the user enters a valid denomination
							System.out.println(cs.printCoinList());
							do {
								System.out.println("Enter a valid denomination");
								cs.printCoinList();
								while(!intKeyboard.hasNextInt()) {
									System.out.println("Invalid input. Enter a valid denomination");
									intKeyboard.next();
								}
								coinDenomination = intKeyboard.nextInt();
								if(!coinDenominations.contains(coinDenomination)) {
									System.out.print("Invalid input. ");
								}
							//repeat until user enters a valid coin denomination
							} while(!coinDenominations.contains(coinDenomination));
							//print multiCoinCalculator result
							System.out.println(cs.multiCoinCalculator(totalValue, coinDenomination) + "\n");
							break;
							
				case "3": 	//print coin list - as the CoinSorter class constructor doesn't accept an empty list, further validations are not needed here
							System.out.println(cs.printCoinList() + "\n");						
							break;
							
				case "4": 	/*SET DETAILS SUB-MENU
								Take input as string to avoid runtime errors
							*/
							do {			
								System.out.println("***Set Details Sub-Menu***");
								System.out.println("1 - Set currency");
								System.out.println("2 - Set minimum coin input value");
								System.out.println("3 - Set maximum coin input value");
								System.out.println("4 - Return to main menu");
								subAnswer = stringKeyboard.nextLine();					
								switch(subAnswer) {
								case "1": 	System.out.println("Set new currency");
											//this scanner object takes a string so input validation is not necessary beforehand
											currency = stringKeyboard.nextLine();
											//if input currency is a number or blank
											while(currency.replace(",", "").matches("-?\\d+(\\.\\d+)?") || currency.isBlank()) {
												System.out.println("Currency can't be a number or blank. Enter another value");
												currency = stringKeyboard.nextLine();
											}
											//set new currency
											cs.setCurrency(currency);
											System.out.println("Currency set to " + cs.getCurrency() + "\n");
											break;
											
								case "2": 	//this scanner object takes an integer so input must be validated beforehand
											do { 
												System.out.println("Set new minimum value - must be in the range of 0-" + cs.getMaxCoinIn());
												while(!intKeyboard.hasNextInt()) {													
													System.out.println("Invalid input. Enter a whole number in the range of 0-" + cs.getMaxCoinIn());
													intKeyboard.next();
												}
												minInputCoin = intKeyboard.nextInt();
												if(minInputCoin < 0 || minInputCoin > cs.getMaxCoinIn() || minInputCoin > 10000) {
													System.out.print("Invalid input. ");
													
												}
											} while(minInputCoin < 0 || minInputCoin > cs.getMaxCoinIn() || minInputCoin > 10000);
											//set new min value
											cs.setMinCoinIn(minInputCoin);
											System.out.println("Success! Minimum value set to " + cs.getMinCoinIn() + "\n");
											break;
											
								case "3": 	//this scanner object takes an integer so input must be validated beforehand
											do { 
												System.out.println("Set new maximum value - must be in the range of " + cs.getMinCoinIn() + "-10000");
												while(!intKeyboard.hasNextInt()) {
													System.out.println("Invalid input. Enter a whole number in the range of " + cs.getMinCoinIn() + "-10000");
													intKeyboard.next();													
												}
												maxInputCoin = intKeyboard.nextInt();
												if((maxInputCoin < 0 || maxInputCoin < cs.getMinCoinIn() || maxInputCoin > 10000)) {
													System.out.print("Invalid input. ");
												}
											} while(maxInputCoin < 0 || maxInputCoin < cs.getMinCoinIn() || maxInputCoin > 10000);
											//set new max value
											cs.setMaxCoinIn(maxInputCoin);
											System.out.println("Success! Maximum value set to " + cs.getMaxCoinIn() + "\n");
											break;															
								}
							//if user enters 4, go back to the main menu	
							} while(!subAnswer.equals("4"));
								continue;
				case "5": 	//DISPLAY PROGRAM CONFIGURATIONS
							System.out.println(cs.displayProgramConfigs() + "\n");
							break;				
							
				}
			//if the user enters 6, program terminates	
			} while(!answer.equals("6"));
				System.out.print("Terminating program...");
		}
}

	

