package application;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control. *;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.layout.HBox;


public class LifeExpectancyController {
	
	
	//currently radio button for female male can select both at the same time
	
	// if male starting  avg life is 81, if female it it 84
	// todays date - get DOB = age in days., this will be used for smoking
	// Weight and height used for bmi calculation, User input height in meters___, weight in kg___
	
	
	//if 18<BMI<30 no impact on life span
//Bmi < 18 = -4 years
//	30-35 = - 3 years off the average life span
//			Bmi in the 40-44 range = -6.5 years off average life span
//			Bmi in 45 to 49 range = - 9.8 range 
//			55-59 = -13.7

//Smokeing: 11 minutes off your life for every ciggarette smoked
//	Used to smoke:
//		User inputs :Age started smoking, quit smoking
//		User inputs: Avg number of cigarettes a day
//		Take quitAge - startAge * 365* cigarettes a day
//		= total cigarettes ever smoked* 11/(525600 (minutes in a year))
//
//
//		Currently smoke:
//		User inputs :Age started smoking
//		User inputs: Avg number of cigarettes a day
//		Take currentAge - startAge * 365* cigarettes a day
//		 cigarettesYearsImpact= total cigarettes ever smoked* 11/(525600 (minutes in a year)) *-1

	
//	Exercise [45 min]
//			High Intensity  Exercise hours per week: 
//			0 - none
//			1.00 - 1.5 hours per week -> + 3.4 years per week
//			3 + hours = + 4.2 years to life
//			5+ hours = 6.5 years 
//			10+ hours = 8 years
//
//
//			Moderate Intense Exercise hours per week:
//			0 - none
//			1- 2.5 hours moderate = + 2 years
//			5-8 hours -> + 3.5 years to life
//			8-10 Hours + 4.5 years to your life 

	
//	Diet: [30]
//			Scale: processed, 50/50, optimal
//			Input optimal diet note: no processed sugar, red meat, processed meat, minimal white meat
//
//			Primarily processed foods  = - 3 years
//			50/50 clean processed = 0
//			Fairly clean and some processed + 3 years d 
//			Extremely clean + 6 years
//			Optimal Diet = + 10
//			Optimal diet*** note***

//	Diet: [30]
//			Scale: processed, 50/50, optimal
//			Input optimal diet note: no processed sugar, red meat, processed meat, minimal white meat
//
//			Primarily processed foods  = - 3 years
//			50/50 clean processed = 0
//			Fairly clean and some processed + 3 years d 
//			Extremely clean + 6 years
//			Optimal Diet = + 10
//			Optimal diet*** note***

//	Implement stress levels
//	always = -2.8
//	often = -1.5
//	half the time = 0
//	rarley = + 1
//	never = + 2

//	Implement happiness levels
//	always = +5
//	often = +2.5
//	half the time = 0
//	rarley = - 3
//	never = + -4


	
	//family history = 30% make up of expected age
	//
	
	Double weight = 0.0;
	Double height = 0.0;
	Double familyAvg = 0.0;
	String alcoholConsumption = "";
	Boolean female;
	int happy = 0;
	int stress = 0;
	int highIntensity;
	int lowIntensity;
	int alcohol;
	String Diet;

	double smokeLife;
	
	Stage applicationStage;
	
    @FXML
    private ChoiceBox<?> dietInput;

    @FXML
    private Slider stressInput;

    @FXML
    private Label lifeExpectancyLabel;

    @FXML
    private RadioButton maleButton;
    
    @FXML
    private RadioButton femaleButton;

    @FXML
    private TextField familyAvgInput;

    @FXML
    private ChoiceBox<String> smokeChoiceBox;
    @FXML
    private ChoiceBox<String> alcoholInput;

    @FXML
    private TextField weightInput;

    @FXML
    private Slider highIntenseInput;

    @FXML
    private Slider lowIntenseInput;
    
    
    @FXML
    private TextField heightInput;

    @FXML
    private Slider happyInput;
    

    @FXML
    private TextField AvgCigs;

    @FXML
    private DatePicker SmokeDate;
    
    @FXML
    private DatePicker currentDate;

    @FXML
    private Button doneCurrentButton;
    
    @FXML Label errorLabel;
    
    @FXML
    private TextField nameTextField;
    
    @FXML
    private List <PersonLife> personLifeList;
	private String name;
	
	@FXML
    private Button resetController;


    @FXML
    private Label finalError;

    private static final DecimalFormat df = new DecimalFormat("0.00");
  

    // Opens a different window when currently or used to smoke is clicked
   @FXML
   void openSmokeWindow(ActionEvent event ) {
	   System.out.println("Smoke Test");
	   if(smokeChoiceBox == null) {

		   return;
	   }
	   if (smokeChoiceBox.getValue().equals("Currently Smoke")){
			try {
				FXMLLoader loader = new FXMLLoader();
				HBox root= loader.load(new FileInputStream("src/application/SmokeCurrentWindow.fxml"));
				Scene scene = new Scene (root, 335, 200);
				
				Main.mainStage.hide();
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("Currently Smoke Window");
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}   
	   }   
	   else if(smokeChoiceBox.getValue().equals("Used To Smoke")) {
			try {
				FXMLLoader loader = new FXMLLoader();
				HBox root= loader.load(new FileInputStream("src/application/UsedToSmokeWindow.fxml"));
				Scene scene = new Scene (root, 1050, 400);
				
				Main.mainStage.hide();
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("Used To Smoke Window");
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
	   }
	   else if(smokeChoiceBox.getValue().equals("Never Smoked")) {
		   
	   }

   }
   
   
   //when done buttone is clicked action returns to main window for Smoking
   @FXML
   void returnToMain(ActionEvent event) {


	   //(days * avg cig per day * 11)/525600 (mins in a year)
	   
	   try {
		   
		   LocalDate DateStart = currentDate.getValue();
		   LocalDate EndDate = SmokeDate.getValue();
 
		   long days = Duration.between(DateStart.atStartOfDay(), EndDate.atStartOfDay()).toDays();
		   
		   if(days < 0) {
			   errorLabel.setText("Input is invalid 1");
			   return;
		   }   
		   int numCigs = Integer.parseInt(AvgCigs.getText());
		   smokeLife = LifeExpectancyCalculator.smokeLifeLost(days, numCigs);
		   if (smokeLife < 0) {
			   errorLabel.setText("Input is invalid 2");
			   return;
		   }
	   
	   }
	   
	   catch(Exception e){
		   errorLabel.setText("Input is invalid 3");
		   return;
	   }
	   Stage stage = (Stage)doneCurrentButton.getScene().getWindow();
	   stage.close();
	   Main.mainStage.show();
   }
    
   
   @FXML
   void resetCalc(ActionEvent event) {
	   personLifeList = null;
	   lifeExpectancyLabel.setText("");
	   
   }
	
	
	
	   // calculate life expectancy
    @FXML
	void calulateLifeExpectancy(ActionEvent event) {

		try{
			String weightString = weightInput.getText();
			String heightString = heightInput.getText();
			String familyString = familyAvgInput.getText();
			name = nameTextField.getText();
			
			highIntensity = (int)highIntenseInput.getValue();
			lowIntensity = (int)lowIntenseInput.getValue();		
			stress = (int)stressInput.getValue();	
			happy =(int) happyInput.getValue();	
			alcoholConsumption = (String) alcoholInput.getValue();
			Diet = (String) dietInput.getValue();
			
			weight = Double.parseDouble(weightString);
			height = Double.parseDouble(heightString);
			familyAvg = Double.parseDouble(familyString);
			

		}
		catch(Exception e){
			finalError.setText("INVALID INPUT");
			return;
			
		}
		finalError.setText("");
		
		
		Double bmi = LifeExpectancyCalculator.calculateBmi(weight, height);
		Double life = LifeExpectancyCalculator.calculateLife(true,bmi,familyAvg,smokeLife,lowIntensity, highIntensity,alcoholConsumption,Diet,happy,stress);
		
		if(personLifeList == null) {
			personLifeList = new ArrayList();
			
		}
		
		personLifeList.add(new PersonLife(life, name));
		String result = "Life expectancy - ";
		double total = 0.0;
		
		for(int i = 0; i < personLifeList.size(); i++) {
			result += personLifeList.get(i).getName()+": "+ df.format(personLifeList.get(i).getLife())+ "\t";
			total += personLifeList.get(i).getLife();
			
			
		}
		
		result = "Average life of group: " + df.format(total/personLifeList.size()) + "\t" + result;
		lifeExpectancyLabel.setText(result);
		
	}

}
