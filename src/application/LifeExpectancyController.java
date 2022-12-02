package application;

import java.text.DecimalFormat;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control. *;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;



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
	Boolean female;
	int happy = 0;
	int stress = 0;
	int highIntensity;
	int lowIntensity;
	int alcohol;
	int Diet;
	
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
    private ChoiceBox<?> alcoholInput;

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

    private static final DecimalFormat df = new DecimalFormat("0.00");
  
	    @FXML
	void calulateLifeExpectancy(ActionEvent event) {
		String weightString = weightInput.getText();
		String heightString = heightInput.getText();
		String familyString = familyAvgInput.getText();
		highIntensity = (int)highIntenseInput.getValue();
		lowIntensity = (int)lowIntenseInput.getValue();		
		stress = (int)stressInput.getValue();	
		happy =(int) happyInput.getValue();			
		try{
			weight = Double.parseDouble(weightString);
			height = Double.parseDouble(heightString);
			familyAvg = Double.parseDouble(familyString);

		}
		catch(NumberFormatException e){
			return;
		}
		Double bmi = calculateBmi(weight, height);
		Double life = calculateLife(true,bmi,familyAvg,0,lowIntensity, highIntensity,0,0,happy,stress);
		
		
		lifeExpectancyLabel.setText(String.format("Your estimated life expectancy is: " +  df.format(life)+ "years old"));
	}
		
	    
	Double calculateBmi(Double weight, Double height) {
		height = height/100;
		
		return weight/(height * height);
		
	}
	
	Double calculateLife(Boolean isFemale,Double Bmi, Double familyAvg, int smoking,
			int lowIntensity, int highIntensity, int alcoholConsumption, int Diet, int happy, int stress){
		Double life = 0.0;
		if(isFemale) {
			life = 84.0;
		}
		else {
			life = 81.0;
		}
		
		if(Bmi < 18) {
			life -= 4;
		}
		else if(Bmi>= 40 && Bmi<= 44) {
			life -= 6.5;
		}
		else if(Bmi>= 45 && Bmi<= 49) {
			life -= 8.9;
		}
		else if(Bmi>= 50 && Bmi<= 54) {
			life -= 9.8;
		}
		else if(Bmi>= 55) {
			life -= 13.7;
		}
	
		
		if(smoking == 0) {
			//0 refers to not smoking
			
		}
		else if(smoking == 1) {
			// refers to previously smoked
			// 2 boxes for age started and quit
			//average cigaretted per day
			//days smoked* average cigagrettes per day*11 min off of life
			//that value/(365) = years off life
			
			life -= yearsOffSmoking();
			
		}
		else if(smoking == 2) {
			//refers to currently smokes
			life -= yearsOffSmoking();
		}

		
		
		
		if(highIntensity >= 1 && highIntensity< 3) {
			life += 3.4;	
		}
		
		else if(highIntensity >= 3 && highIntensity< 5) {
			life += 4.2;
		}
		
		else if(highIntensity >= 5 && highIntensity< 10) {
			life += 6.5;
		}
		else if(highIntensity == 10) {
			life += 8.0;			
		}
		
		
		
		if(lowIntensity >= 1 && lowIntensity< 2.5) {
			life += 2.0;	
		}
		
		else if(lowIntensity >= 2.5 && lowIntensity< 5) {
			life += 2.5;
		}
		
		else if(lowIntensity >= 5 && lowIntensity< 8) {
			life += 3.5;
		}
		else if(lowIntensity >= 8 && lowIntensity<= 10) {
			life += 4.5;			
		}
		
		
		
		
//		None > Na on impact
//				0-5 na on impact 
//				5-10 per week = ½ year
//				10-15 per week = -2 years
//				15+ = - 5 years

		if(alcoholConsumption>= 5 && alcoholConsumption< 10) {
			life -= 0.5;
		}
		
		else if(alcoholConsumption>= 10 && alcoholConsumption< 15) {
			life -= 2;
		}
		
		else if(alcoholConsumption>= 15) {
			life -= 5;
		}
		
		
		//
//		Primarily processed foods  = - 3 years
//		50/50 clean processed = 0
//		Fairly clean and some processed + 3 years d 
//		Extremely clean + 6 years

		if(Diet == 1) {
			//primarly processed
			life -= 3;
		}
		
		else if(Diet == 2) {
			//50/50 0 impact
			
		}
		
		else if(Diet == 3) {
			//Fairly cleam +3 impact
			life += 3;
		}
		else if(Diet == 4) {
			//Very Clean + 6 years impact
			life += 6;
		}
		
		
//		Implement happiness levels
//		always = +5
//		often = +2.5
//		half the time = 0
//		rarley = - 3
//		never = + -4		
		
		if(happy == 5) {
			life += 5;
		}
		else if(happy ==4) {
			life += 2.5;
		}
		else if(happy == 3) {
			
		}
		else if(happy == 2) {
			life -= 3;
		}
		else if(happy == 1) {
			life -= 4;
		}
		
		
		
		
//		Implement stress levels
//		always = -2.8
//		often = -1.5
//		half the time = 0
//		rarley = + 1
//		never = + 2


		if(stress == 5) {
			life -= 2.8;
		}
		else if(stress ==4) {
			life -= 1.5;
		}
		else if(stress == 3) {
			
		}
		else if(stress == 2) {
			life += 1;
		}
		else if(stress == 1) {
			life += 2;
		}
		
		
		
		
		life = 0.7*life + 0.3*familyAvg;
		return life;
		
	}
	
	
	Double yearsOffSmoking() {
		return 0.0;
	}
	
	


}
