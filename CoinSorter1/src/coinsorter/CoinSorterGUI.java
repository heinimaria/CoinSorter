package coinsorter;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene; 
import javafx.scene.shape.Circle; 
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label; 
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.scene.control.TextField; 
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;




public class CoinSorterGUI extends Application {
		
		//create an ArrayList to store coin denominations
		ArrayList <Integer> coinDenominations = new ArrayList<Integer>(Arrays.asList(10,20,50,100,200));
		//create and object of the CoinSorter class
		CoinSorter coinSorter = new CoinSorter("£", 0, 10000, coinDenominations);
		
		@Override
		public void start(Stage stage) 
		{				
			//create a TabPane object to organise the layout
			TabPane tabPane = new TabPane();
			//create tabs that can't be closed
			tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
			
			//COIN CALCULATOR						
			Label coinCalculator = new Label("Coin calculator");
			coinCalculator.setMinSize(400,50);         
			coinCalculator.setMaxSize(400,50);
			coinCalculator.setAlignment(Pos.CENTER);
			coinCalculator.setFont(Font.font("Verdana", 20));
			
			Label coinCalculatorText = new Label();
			coinCalculatorText.setFont(Font.font("Verdana", 12));
			coinCalculatorText.setMinSize(400,50);         
			coinCalculatorText.setMaxSize(400,50);
			coinCalculatorText.setAlignment(Pos.CENTER);
			coinCalculatorText.setWrapText(true);
			coinCalculatorText.setText("Enter a total value of coins in pennies and a coin denomination. The calculator tells you how many coins of the desired denomination you will get.");
			
			TextField totalValueField = new TextField();         
			totalValueField.setMaxWidth(80);		
			
			TextField coinTypeField = new TextField();
			coinTypeField.setMaxWidth(80);
			
			Label totalValueLabel = new Label("Value");
			totalValueLabel.setFont(Font.font("Verdana", 12));
			
			Label coinTypeLabel = new Label("Denomination");
			coinTypeLabel.setFont(Font.font("Verdana", 12));
			
			Label calculatorResult = new Label();
			calculatorResult.setMinSize(400,50);         
			calculatorResult.setMaxSize(400,50); 
			calculatorResult.setWrapText(true);
			calculatorResult.setAlignment(Pos.CENTER);
			calculatorResult.setFont(Font.font("Verdana", 12));
			
			Button calcButton = new Button("Calculate");							
			calcButton.setOnAction(e -> {	try	
											{	//check that input fields are not empty
												if(totalValueField.getText().isEmpty() || coinTypeField.getText().isEmpty()) {
													calculatorResult.setText("You must enter a value for both fields");
													calculatorResult.setTextFill(Color.RED);				
													
												}
												//check whether both total value input and coin denomination input are false
												else if((Integer.parseInt(totalValueField.getText()) < coinSorter.getMinCoinIn() || Integer.parseInt(totalValueField.getText()) > coinSorter.getMaxCoinIn()) && (!coinDenominations.contains(Integer.parseInt(coinTypeField.getText())))) {
													calculatorResult.setText("Value must be in the range of " + coinSorter.getMinCoinIn() + "-" + coinSorter.getMaxCoinIn() + ". You must also enter a valid Denomination. " + coinSorter.printCoinList());
													calculatorResult.setTextFill(Color.RED);
													
												}
												//check whether total value is in the range 
												else if(Integer.parseInt(totalValueField.getText()) < coinSorter.getMinCoinIn() || Integer.parseInt(totalValueField.getText()) > coinSorter.getMaxCoinIn()) {
													calculatorResult.setText("Value must be in the range of " + coinSorter.getMinCoinIn() + "-" + coinSorter.getMaxCoinIn() + ". You can also go to Settings to change the value range.");
													calculatorResult.setTextFill(Color.RED);													
												}
												//check whether input denomination is in the list
												else if(!coinDenominations.contains(Integer.parseInt(coinTypeField.getText()))){
													calculatorResult.setText("You must enter a valid Denomination. " + coinSorter.printCoinList());
													calculatorResult.setTextFill(Color.RED);																									
												}
												//if total value is in range and coin denomination is in the list, display the result
												else {
													calculatorResult.setText(coinSorter.coinCalculator(Integer.parseInt(totalValueField.getText()), Integer.parseInt(coinTypeField.getText())));
													calculatorResult.setTextFill(Color.GREEN);																				
												}	
											}
											//if the input type is not a whole number, display an error message
											catch(final NumberFormatException exception) 
											{	//use regex to check which one of the two values is not a positive integer
												//if neither is, display the following
												if((!totalValueField.getText().matches("^\\d*$")) && (!coinTypeField.getText().matches("^\\d*$"))) {
													calculatorResult.setText("Invalid input - Value and Denomination must be whole numbers.");
													calculatorResult.setTextFill(Color.RED);
												
												}
												//if total value is not a positive integer, display the following
												else if(!totalValueField.getText().matches("^\\d+$")) {
													calculatorResult.setText("Invalid input - Value must be a whole number.");
													calculatorResult.setTextFill(Color.RED);
													
												}
												//if total value is not a positive integer, display the following
												else if(!coinTypeField.getText().matches("^\\d+$")) {
													calculatorResult.setText("Invalid input - Denomination must be a whole number");
													calculatorResult.setTextFill(Color.RED);													
												}
											}																												
									}			
									);
			
			//MULTIPLE COIN CALCULATOR
			TextField totalMultiValueField = new TextField();         
			totalMultiValueField.setMaxWidth(80);
			
			TextField multiCoinTypeField = new TextField();
			multiCoinTypeField.setMaxWidth(80);
			
			Label multiCoinCalculator = new Label("Multiple Coin Calculator");
			multiCoinCalculator.setMinSize(400,50);         
			multiCoinCalculator.setMaxSize(400,50);
			multiCoinCalculator.setAlignment(Pos.CENTER);
			multiCoinCalculator.setFont(Font.font("Verdana", 20));
			
			Label multiCoinCalculatorText = new Label();
			multiCoinCalculatorText.setFont(Font.font("Verdana", 12));
			multiCoinCalculatorText.setMinSize(400,50);         
			multiCoinCalculatorText.setMaxSize(400,50);
			multiCoinCalculatorText.setAlignment(Pos.CENTER);
			multiCoinCalculatorText.setWrapText(true);
			multiCoinCalculatorText.setText("Enter a total value of coins in pennies and a coin denomination you want to exclude. The calculator gives you a breakdown of the remaining denominations you will get.");
			
			Label totalMultiValueLabel = new Label("Value");
			totalMultiValueLabel.setFont(Font.font("Verdana", 12));
			
			Label multiCoinTypeLabel = new Label("Denomination");
			multiCoinTypeLabel.setFont(Font.font("Verdana", 12));
			
			Label multiCalculatorResult = new Label();
			multiCalculatorResult.setMinSize(400,50);         
			multiCalculatorResult.setMaxSize(400,50); 
			multiCalculatorResult.setWrapText(true);
			multiCalculatorResult.setAlignment(Pos.CENTER);
			multiCalculatorResult.setFont(Font.font("Verdana", 12));
			
			Button multiCalcButton = new Button("Calculate");
			multiCalcButton.setOnAction(e -> { 	try
												{	//check that input fields are not empty
													if(totalMultiValueField.getText().isEmpty() || multiCoinTypeField.getText().isEmpty()) {
														multiCalculatorResult.setText("You must enter a value for both fields");
														multiCalculatorResult.setTextFill(Color.RED);
													}
													//check whether both total value input and coin denomination input are false
													else if((!coinDenominations.contains(Integer.parseInt(multiCoinTypeField.getText())) && (Integer.parseInt(totalMultiValueField.getText()) < coinSorter.getMinCoinIn() || Integer.parseInt(totalMultiValueField.getText()) > coinSorter.getMaxCoinIn()))) {
														multiCalculatorResult.setText("Value must be in the range of " + coinSorter.getMinCoinIn() + "-" + coinSorter.getMaxCoinIn() + ". You must elso enter a valid Denomination. " + coinSorter.printCoinList());
														multiCalculatorResult.setTextFill(Color.RED);
													}
													//check that total value is in the range 
													else if(Integer.parseInt(totalMultiValueField.getText()) < coinSorter.getMinCoinIn() || Integer.parseInt(totalMultiValueField.getText()) > coinSorter.getMaxCoinIn()) {
														multiCalculatorResult.setText("Value must be in the range of " + coinSorter.getMinCoinIn() + "-" + coinSorter.getMaxCoinIn() + ". You can also go to Settings to change the value range.");
														multiCalculatorResult.setTextFill(Color.RED);
													}
													//check that denomination is valid 
													else if(!coinDenominations.contains(Integer.parseInt(multiCoinTypeField.getText()))) {
														multiCalculatorResult.setText("You must enter a valid Denomination. " + coinSorter.printCoinList());
														multiCalculatorResult.setTextFill(Color.RED);
													}
													else {multiCalculatorResult.setText(coinSorter.multiCoinCalculator(Integer.parseInt(totalMultiValueField.getText()), Integer.parseInt(multiCoinTypeField.getText())));
													multiCalculatorResult.setTextFill(Color.GREEN);
													}
												}
												//if the input type is not a whole number, display an error message
												catch(final NumberFormatException exception) 
													{
													//use regex to check which one of the two values is not a positive integer
													//if neither is, display a relevant error message
													if((!totalMultiValueField.getText().matches("^\\d*$")) && (!multiCoinTypeField.getText().matches("^\\d*$"))) {
														multiCalculatorResult.setText("Invalid input - Value and Denomination must be whole numbers.");
														multiCalculatorResult.setTextFill(Color.RED);
													
													}
													//if total value is not a positive integer, display a relevant error message 
													else if(!totalMultiValueField.getText().matches("^\\d*$")) {
														multiCalculatorResult.setText("Invalid input - Value must be a whole number.");
														multiCalculatorResult.setTextFill(Color.RED);
														
													}
													//if total value is not a positive integer, a relevant error message
													else if(!multiCoinTypeField.getText().matches("^\\d*$")) {
														multiCalculatorResult.setText("Invalid input - Denomination must be a whole number");
														multiCalculatorResult.setTextFill(Color.RED);
														
													}
												}				
											}
											);	
			
			//DENOMINATIONS
			Label coinLabel = new Label("Coin Denominations");
			coinLabel.setFont(Font.font("Verdana", 20));
			
			Label coinList = new Label();
			coinList.setText(coinSorter.printCoinList());				
					
			
			//CONFIGURATIONS
			Label configTitle = new Label("Current Configurations");
			configTitle.setFont(Font.font("Verdana", 20));
			
			Label configLabel = new Label(coinSorter.displayProgramConfigs());
			configLabel.setFont(Font.font("Verdana", 12));	
			
			
			//SETTINGS			
			Label settingsTitle = new Label("Settings");
			settingsTitle.setFont(Font.font("Verdana", 20));
			
			Label minValueLabel = new Label("Current Min Value:");
			Label maxValueLabel = new Label("Current Max Value:");
			Label currencyLabel = new Label("Current Currency:");
			
			//current values to be displayed on the settings tab
			TextField currentMinValue = new TextField();
			currentMinValue.setText(Integer.toString(coinSorter.getMinCoinIn()));
			currentMinValue.setMaxWidth(100);
			
			Label displayMinMessage = new Label();
			displayMinMessage.setMinSize(550,30);         
			displayMinMessage.setMaxSize(550,30); 
			displayMinMessage.setWrapText(true);
			displayMinMessage.setAlignment(Pos.CENTER);
			displayMinMessage.setFont(Font.font("Verdana", 10));

			
			TextField currentMaxValue = new TextField(); 
			currentMaxValue.setText(Integer.toString(coinSorter.getMaxCoinIn()));
			currentMaxValue.setMaxWidth(100);
			
			Label displayMaxMessage = new Label();
			displayMaxMessage.setMinSize(550,30);         
			displayMaxMessage.setMaxSize(550,30); 
			displayMaxMessage.setWrapText(true);
			displayMaxMessage.setAlignment(Pos.CENTER);
			displayMaxMessage.setFont(Font.font("Verdana", 10));

			//combobox represents the currency field as a editable dropdown menu
			ComboBox<String> currencyComboBox = new ComboBox();
			currencyComboBox.setValue(coinSorter.getCurrency());
			currencyComboBox.setMaxWidth(100);
			currencyComboBox.setEditable(true);
	        currencyComboBox.getItems().addAll("£", "€", "$", "¥");
	        
			//create a circle for an info tooltip
			Circle currencyInfo = new Circle(6,6,6);
			currencyInfo.setFill(Color.LIGHTGREY);
			currencyInfo.setStroke(Color.DARKGREY);
			Text text = new Text("i");
			text.setBoundsType(TextBoundsType.VISUAL); 
			StackPane infoStack = new StackPane();
			infoStack.getChildren().addAll(currencyInfo, text);
			
			//convert the circle into a tooltip
			Tooltip.install(currencyInfo, new Tooltip("You can edit this field and add any currency of your choice"));
					
			//displays a currency related message 
			Label displayCurrencyMessage = new Label();
			displayCurrencyMessage.setMinSize(550,30);         
			displayCurrencyMessage.setMaxSize(550,30); 
			displayCurrencyMessage.setWrapText(true);
			displayCurrencyMessage.setAlignment(Pos.CENTER);
			displayCurrencyMessage.setFont(Font.font("Verdana", 10));
			
			Button saveMinValueButton = new Button("Save");
			saveMinValueButton.setFont(Font.font(12));
			saveMinValueButton.setOnAction(e -> {	//these are set to false to begin with so that they won’t display messages from previous button actions
													displayMinMessage.setVisible(false);
													displayMaxMessage.setVisible(false);
													displayCurrencyMessage.setVisible(false);
													try {
														//if min value field is empty, show an error message
														if(currentMinValue.getText().isEmpty()) {
															displayMinMessage.setVisible(true);
															displayMinMessage.setTextFill(Color.RED);
															displayMinMessage.setText("Invalid input. Minimum value field can't be left empty");
															displayMinMessage.setVisible(true);
															currentMinValue.setText(Integer.toString(coinSorter.getMinCoinIn()));
															
															}
														//if min value is less than 0, show an error message
														else if(Integer.parseInt(currentMinValue.getText()) < 0) {
															displayMinMessage.setVisible(true);
															displayMinMessage.setTextFill(Color.RED);
															displayMinMessage.setText("Invalid input. Minimum value must be equal to or greater than 0");
															currentMinValue.setText(Integer.toString(coinSorter.getMinCoinIn()));													
														}
																							
														//if minimum value is greater than current max, show an error message
														else if(Integer.parseInt(currentMinValue.getText()) > coinSorter.getMaxCoinIn()) {
															displayMinMessage.setVisible(true);
															displayMinMessage.setTextFill(Color.RED);
															displayMinMessage.setText("Invalid input. Minimum value must be equal to or less than the current maximum value");
															currentMinValue.setText(Integer.toString(coinSorter.getMinCoinIn()));
														}
														else {
															if(Integer.parseInt(currentMinValue.getText()) != coinSorter.getMinCoinIn()) {
																coinSorter.setMinCoinIn(Integer.parseInt(currentMinValue.getText()));
																displayMinMessage.setVisible(true);
																displayMinMessage.setText("Success! Minimum value is set to " + coinSorter.getMinCoinIn());
																displayMinMessage.setTextFill(Color.GREEN);
																
																//when a valid value has been entered, the Configurations tab gets updated as well
																configLabel.setText(coinSorter.displayProgramConfigs());
															}
															
														}
														
													}catch(final NumberFormatException exception) {
														displayMinMessage.setText("Invalid input. Enter a whole number");
														displayMinMessage.setTextFill(Color.RED);
														displayMinMessage.setVisible(true);
														currentMinValue.setText(Integer.toString(coinSorter.getMinCoinIn()));													
													}
											}
											);
			
			Button saveMaxValueButton = new Button("Save");
			saveMaxValueButton.setFont(Font.font(12));
			saveMaxValueButton.setOnAction(e-> {	//these are set to false to begin with so that they won’t display messages from previous button actions
													displayMinMessage.setVisible(false);
													displayMaxMessage.setVisible(false);
													displayCurrencyMessage.setVisible(false);
													try {
														//if max field is empty, show an error message 
														if(currentMaxValue.getText().isEmpty()) {
															displayMaxMessage.setVisible(true);
															displayMaxMessage.setText("Invalid input. Maximum value field can't be left empty");
															displayMaxMessage.setTextFill(Color.RED);
															currentMaxValue.setText(Integer.toString(coinSorter.getMaxCoinIn()));
															
														}
														//if max value is greater than 10000, show an error message 
														else if(Integer.parseInt(currentMaxValue.getText()) > 10000) {
															displayMaxMessage.setVisible(true);
															displayMaxMessage.setText("Invalid input. Maximum value can't be greater than 10000");
															displayMaxMessage.setTextFill(Color.RED);
															currentMaxValue.setText(Integer.toString(coinSorter.getMaxCoinIn()));
														}
														
														//if max value is less than current min value
														else if(Integer.parseInt(currentMaxValue.getText()) < coinSorter.getMinCoinIn()) {
															displayMaxMessage.setVisible(true);
															displayMaxMessage.setTextFill(Color.RED);
															displayMaxMessage.setText("Invalid input. Maximum value must be equal to or greater than the current minimum value");
															currentMaxValue.setText(Integer.toString(coinSorter.getMaxCoinIn()));
														}
														else {
															if(Integer.parseInt(currentMaxValue.getText()) != coinSorter.getMaxCoinIn()){
																coinSorter.setMaxCoinIn(Integer.parseInt(currentMaxValue.getText()));
																displayMaxMessage.setVisible(true);
																displayMaxMessage.setText("Success! Maximum value is set to " + coinSorter.getMaxCoinIn());
																displayMaxMessage.setTextFill(Color.GREEN);
																
																//when a valid value has been entered, the Configurations tab gets updated as well
																configLabel.setText(coinSorter.displayProgramConfigs());		
															}														
														}
														
													} catch(final NumberFormatException exception) {
														displayMaxMessage.setText("Invalid input. Enter a whole number");
														displayMaxMessage.setTextFill(Color.RED);
														displayMaxMessage.setVisible(true);
														currentMaxValue.setText(Integer.toString(coinSorter.getMaxCoinIn()));
													}
											}
											);
			
			Button saveCurrencyButton = new Button("Save");
			saveCurrencyButton.setFont(Font.font(12));
			saveCurrencyButton.setOnAction(e->{//these are set to false to begin with so that they won’t display messages from previous button actions
												displayMinMessage.setVisible(false);
												displayMaxMessage.setVisible(false);
												displayCurrencyMessage.setVisible(false);
												
												//if currency field is empty, show an error message
												if(currencyComboBox.getValue().isEmpty()) {
													displayCurrencyMessage.setVisible(true);
													displayCurrencyMessage.setText("Currency field can't be left empty");
													displayCurrencyMessage.setTextFill(Color.RED);													
													currencyComboBox.setValue(coinSorter.getCurrency());													
												}
												//use regex to check if input value is a number
												else if(currencyComboBox.getValue().matches("-?\\d+(\\.\\d+)?")) {
													displayCurrencyMessage.setVisible(true);
													displayCurrencyMessage.setText("Currency cannot be a number");
													displayCurrencyMessage.setTextFill(Color.RED);
													currencyComboBox.setValue(coinSorter.getCurrency());			
												}
												else {
													if (!currencyComboBox.getValue().equals(coinSorter.getCurrency())) {
														coinSorter.setCurrency(currencyComboBox.getValue());
														displayCurrencyMessage.setVisible(true);
														displayCurrencyMessage.setText("Success! Currency is set to " + coinSorter.getCurrency());
														displayCurrencyMessage.setTextFill(Color.GREEN);
														
														//when a valid value has been entered, the Configurations tab gets updated as well
														configLabel.setText(coinSorter.displayProgramConfigs());														
													}															
												}											
											}
											);
			
			//creating a horizontal container for the calculator's two input fields
			VBox totalValueInputFields = new VBox();
			totalValueInputFields.setAlignment(Pos.CENTER);
			totalValueInputFields.setSpacing(5);
			totalValueInputFields.getChildren().addAll(totalValueLabel, totalValueField);
			
			VBox coinTypeInputFields = new VBox();
			coinTypeInputFields.setAlignment(Pos.CENTER);
			coinTypeInputFields.setSpacing(5);
			coinTypeInputFields.getChildren().addAll(coinTypeLabel, coinTypeField);
			
			HBox calculatorInputFields = new HBox();
			calculatorInputFields.setSpacing(20);
			calculatorInputFields.setAlignment(Pos.CENTER);
			calculatorInputFields.getChildren().addAll(totalValueInputFields, coinTypeInputFields);
			
			VBox calculatorField = new VBox();
			calculatorField.setSpacing(5);
			calculatorField.setAlignment(Pos.CENTER);
			calculatorField.getChildren().addAll(coinCalculator,coinCalculatorText, calculatorInputFields, calcButton, calculatorResult);
			
			//Calculator tab
			Tab coinCalculatorTab = new Tab("Coin Calculator", calculatorField);
			
			//creating a horizontal container for the multiple coin calculator's two input fields
			VBox multiValueInputFields = new VBox();
			multiValueInputFields.setSpacing(5);
			multiValueInputFields.setAlignment(Pos.CENTER);
			multiValueInputFields.getChildren().addAll(totalMultiValueLabel, totalMultiValueField);
			
			VBox multiCoinTypeInputFields = new VBox();
			multiCoinTypeInputFields.setSpacing(5);
			multiCoinTypeInputFields.setAlignment(Pos.CENTER);
			multiCoinTypeInputFields.getChildren().addAll(multiCoinTypeLabel, multiCoinTypeField);
				
			HBox multiCalculatorInputField = new HBox();
			multiCalculatorInputField.setSpacing(20);
			multiCalculatorInputField.setAlignment(Pos.CENTER);
			multiCalculatorInputField.getChildren().addAll(multiValueInputFields,multiCoinTypeInputFields);
			
			VBox multipleCalcField = new VBox();
			multipleCalcField.setSpacing(5);
			multipleCalcField.setAlignment(Pos.CENTER);
			multipleCalcField.getChildren().addAll(multiCoinCalculator, multiCoinCalculatorText, multiCalculatorInputField, multiCalcButton, multiCalculatorResult);
			
			//multiple coin calculator tab
			Tab multipleCoinTab = new Tab("Multiple Coin Calculator", multipleCalcField);
			
			VBox coinListField = new VBox();
			coinListField.setSpacing(20);
			coinListField.setMaxSize(550,90);
			coinListField.setAlignment(Pos.CENTER);
			coinListField.getChildren().addAll(coinLabel, coinList);
			
			//denominations tab
			Tab denomTab = new Tab("Denominations", coinListField);
			
			VBox configField = new VBox();
			configField.setSpacing(20);
			configField.setMaxSize(550,115);
			configField.setAlignment(Pos.CENTER);
			configField.getChildren().addAll(configTitle, configLabel);
			
			//configurations tab
			Tab configTab = new Tab("Configurations", configField);
			
			VBox settingsTitleField = new VBox();
			settingsTitleField.setSpacing(6);
			settingsTitleField.setMinSize(550,40);         
			settingsTitleField.setMaxSize(550,40);
			settingsTitleField.setAlignment(Pos.CENTER);
			settingsTitleField.getChildren().addAll(settingsTitle);			
			
			HBox setMinField = new HBox();	
			setMinField.setSpacing(5);
			setMinField.setMinSize(500,10);         
			setMinField.setMaxSize(500,10);
			setMinField.setAlignment(Pos.CENTER);
			setMinField.getChildren().addAll(minValueLabel, currentMinValue, saveMinValueButton);
			
			VBox combineMinFields = new VBox();
			combineMinFields.setMinSize(550,40);         
			combineMinFields.setMaxSize(550,40);
			combineMinFields.getChildren().addAll(setMinField, displayMinMessage);
			
			HBox setMaxField = new HBox();
			setMaxField.setSpacing(5);
			setMaxField.setMinSize(500,10);         
			setMaxField.setMaxSize(500,10);
			setMaxField.setAlignment(Pos.CENTER);
			setMaxField.getChildren().addAll(maxValueLabel, currentMaxValue, saveMaxValueButton);
			
			VBox combineMaxFields = new VBox();
			combineMaxFields.setMinSize(550,40);         
			combineMaxFields.setMaxSize(550,40);
			combineMaxFields.getChildren().addAll(setMaxField, displayMaxMessage);
			
			HBox setCurrencyField = new HBox();
			setCurrencyField.setSpacing(5);
			setCurrencyField.setMinSize(527,10);         
			setCurrencyField.setMaxSize(527,10);
			setCurrencyField.setAlignment(Pos.CENTER);
			setCurrencyField.getChildren().addAll(currencyLabel, currencyComboBox, infoStack, saveCurrencyButton);
			
			VBox combineCurrencyFields = new VBox();
			combineCurrencyFields.setMinSize(550,40);         
			combineCurrencyFields.setMaxSize(550,40);
			combineCurrencyFields.getChildren().addAll(setCurrencyField, displayCurrencyMessage);
			
			VBox settingsField = new VBox();
			settingsField.setSpacing(10);
			settingsField.setAlignment(Pos.TOP_CENTER);
			settingsField.getChildren().addAll(settingsTitleField, combineMinFields, combineMaxFields, combineCurrencyFields);
			
			Tab settingsTab = new Tab("Settings", settingsField);
			
			//add all five tabs to the tabPane 
			tabPane.getTabs().addAll(coinCalculatorTab, multipleCoinTab, denomTab, configTab, settingsTab);
			tabPane.getStyleClass().add("floating"); 
			VBox tabPaneBox = new VBox(tabPane);
			
			//make tabPaneBox root 
			VBox root = new VBox();
			root.setStyle("-fx-background-color: #204236;");
			root.getChildren().addAll(tabPaneBox);
			
			Scene scene = new Scene(root, 550, 350, Color.TEAL);
			stage.setScene(scene);
			stage.setTitle("Coin Sorter");
			stage.show();
			
		}	
}

