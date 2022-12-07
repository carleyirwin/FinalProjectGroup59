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
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.layout.HBox;

// figure out used to smoke
public class LifeExpectancyController {
	
	//family history = 30% make up of expected age
	//
	
	Double weight = 0.0;
	Double height = 0.0;
	Double familyAvg = 0.0;
	Double yearsLeft = 0.0;
	String alcoholConsumption = "";
	Boolean female;
	Boolean lifeAllowing;
	
	//WANT TO ADD AN ERROR MESSAGE FOR NON LIFE SUSTAINABLE OUTCOMES: EX BMI IS LIKE AN 8 
	//IS IMPOSSIBLE, WANT TO SET A MESSAGE SAYING BMI NOT LIFE SUSTAINABLE.
	

	int happy = 0;
	int stress = 0;
	int highIntensity;
	int lowIntensity;
	int alcohol;
	String Diet;
	LocalDate DateStart;

	//LocalDate EndDate;

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
    private DatePicker birthDate;
    
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
    private DatePicker currentD;

    @FXML
    private Label errorLabels;

    @FXML
    private DatePicker SmokeD;

    @FXML
    private TextField AvgCig;

    @FXML
    private Button doneUsedToButton;
	
    @FXML
    private Text smokeLabel;
    

    
    
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
				HBox root= loader.load(new FileInputStream("src/application/SmokeWindow.fxml"));
				LifeExpectancyController controller = loader.getController();
				Scene scene = new Scene (root, 335, 200);
				
				Main.mainStage.hide();
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("Currently Smoke Window");
				stage.show();
				controller.smokeLabel.setText("Current Date:");
			} catch(Exception e) {
				e.printStackTrace();
			}   
	   }   
	   else if(smokeChoiceBox.getValue().equals("Used To Smoke")) {

			try {
				FXMLLoader loader = new FXMLLoader();
				HBox root= loader.load(new FileInputStream("src/application/SmokeWindow.fxml"));
				LifeExpectancyController controller = loader.getController();
				Scene scene = new Scene (root, 335, 200);
				
				Main.mainStage.hide();
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("Used To Smoke Window");
				stage.show();
				controller.smokeLabel.setText("Estimation of Quit Date:");
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
		   
		   DateStart = currentDate.getValue();
		   LocalDate EndDate = SmokeDate.getValue();
		   
		   long days = Duration.between(DateStart.atStartOfDay(), EndDate.atStartOfDay()).toDays();
		   
//		   if(days < 0) {
//			   errorLabel.setText("Input is invalid 1");
//			   return;
//		   }   
		   int numCigs = Integer.parseInt(AvgCigs.getText());
		   smokeLife = LifeExpectancyCalculator.smokeLifeLost(days, numCigs);
		   
		   if (smokeLife < 0 || days < 0) {
			   errorLabel.setText("INVALID INPUT");
			   return;
		   } 
	   }
	   
	   catch(Exception e){
		   errorLabel.setText("INVALID INPUT");
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
		//Double yearsLeftTotal = LifeExpectancyCalculator.calculateYearsLeft()
		Double life = LifeExpectancyCalculator.calculateLife(true,bmi,familyAvg,smokeLife,lowIntensity, highIntensity,alcoholConsumption,Diet,happy,stress);
		
		if(personLifeList == null) {
			personLifeList = new ArrayList();
			
		}
		
		if(bmi < 12 || bmi > 100) {
			finalError.setText("Unlikley to sustain life under these conditions. Please Check that your weight and height is accurate");
			return;
		}
		
		
		   DateStart = currentDate.getValue();
		   LocalDate EndDate = SmokeDate.getValue();
		   
		   long days = Duration.between(DateStart.atStartOfDay(), EndDate.atStartOfDay()).toDays();
		   
		   
		   
		LocalDate birthD = birthDate.getValue();
		long daysBDAY = Duration.between(birthD.atStartOfDay(), DateStart.atStartOfDay()).toDays();
		if(daysBDAY <= 0 ) {
			finalError.setText("INVALID INPUT, can not have initial smoke date prior to birthday.");
			return;
		}
		
		
		personLifeList.add(new PersonLife(life, name));
		String result = "Life expectancy of ";
		double total = 0.0;
		
		for(int i = 0; i < personLifeList.size(); i++) {
			result += personLifeList.get(i).getName()+": "+ df.format(personLifeList.get(i).getLife())+ "\t";
			total += personLifeList.get(i).getLife();
			
			
		}
		
		result = "Average life of group: " + df.format(total/personLifeList.size()) + "\t" + result;
		lifeExpectancyLabel.setText(result);
		

		
	}

}
