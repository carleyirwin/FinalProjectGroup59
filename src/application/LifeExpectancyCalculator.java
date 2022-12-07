package application;

import java.time.Duration;

public class LifeExpectancyCalculator {
	
	/**
	 * This method assigns all the life altering values based on the user inputs
	 * @param
	 * @return
	 */
	
	static Double calculateLife(Boolean isFemale,Double Bmi, Double familyAvg, Double smokeLifeReduction,
			int lowIntensity, int highIntensity, String alcoholConsumption, String Diet, int happy, int stress){
		Double life = 0.0;
		if(isFemale) {
			life = 84.0;
		}
		else {
			life = 81.0;
		}
		
		
		//BMI IMPACT
		
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
	
		//SMOKE IMPACT
		life -= smokeLifeReduction;


		//HIGH INTENSITY WORKOUT IMPACT
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
		
		
		//LOW INTENSITY WORKOUT	IMPACT	
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
	
//		<String fx:value="Extremely Processed" />
//		<String fx:value="50/50 Processed and Clean" />
//		<String fx:value="Primarly Clean" />						            		
//		<String fx:value="Extremely Clean" />

		if(Diet.equals("Extremely Processed")) {
			//primarly processed
			life -= 3;
		}
		
		else if(Diet.equals("50/50 Processed and Clean")) {
			//50/50 0 impact
			
		}
		
		else if(Diet.equals("Primarly Clean")) {
			//Fairly cleam +3 impact
			life += 3;
		}
		else if(Diet.equals("Extremely Clean")) {
			//Very Clean + 6 years impact
			life += 6;
		}
		
		

		
		//ALCOHOL IMPACT
		if(alcoholConsumption == "0") {
	
		}
		else if(alcoholConsumption.equals("1-6")) {
			life -= 0.5;
		}
		else if(alcoholConsumption.equals("7-14")) {
			life -=1.5;
		}
		else if(alcoholConsumption.equals("15-20")) {
			life -=2.5;
		}
		
		else if(alcoholConsumption.equals("20-25")) {
			life -= 3.5;
		}
		else if(alcoholConsumption.equals("25+")) {
			life -= 5;
		}
	
		
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
	//525600 is the number of minutes in a year
	
	   static double smokeLifeLost(long days, int numCigs) {
		   return (days*numCigs*11)/525600.0;
		   
	   }
	    
	   static double smokeLifeLostFormer(long foremrDays, int formerCigs) {
		   return (foremrDays*formerCigs*11)/525600.0;
		   
	   }

		    //BMI CALCULATIONS
		static Double calculateBmi(Double weight, Double height) {
			height = height/100;
			
			return weight/(height * height);
			
		}
		
//		static Double calculateYearsLeft(Double yearsLeft) {
// 
//		}

}
