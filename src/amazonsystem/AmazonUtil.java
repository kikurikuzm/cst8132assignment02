package amazonsystem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AmazonUtil {
	public static float convertStringToFloat(String desiredString){
		char[] validCharacters = new char[] {'0','1','2','3','4','5','6','7','8','9','.'}; //array to filter out any currency symbols
		String cleanedString = "";
		float convertedFloat = 0;
		
		for(int i = 0; i < desiredString.length(); i++) {//for loop going over entire string
			char currentCharacter = desiredString.charAt(i);
			for(int c = 0; c < validCharacters.length; c++) {//for loop comparing each character against 
				if(currentCharacter == validCharacters[c]) {
					cleanedString += currentCharacter;
				}
			}
		}
		
		if(desiredString.isEmpty()||desiredString.isBlank()) {//if the string is empty or blank return a zero, just in case
			cleanedString = "0";
		}
		
		return Float.parseFloat(cleanedString);
	}
		
	public static String[] lineReader(String fileName, int desiredLineIndex) {
		try {
			FileReader fileReader = new FileReader(fileName);
			
			int currentLineIndex = 0;
			int currentColumn = 0;
			String currentString = "";
			String[] currentCSVArray = new String[10];
			//indices and their corresponding column:
			//0=name, 1=main_category, 2=sub_category, 3=image_link, 4=product_link,
			//5=product_rating, 6=amount_of_ratings, 7=discount_price, 8=actual_price
			
			while (currentLineIndex <= desiredLineIndex) 
				try {
					Character currentCharacter = (char)fileReader.read(); // read first character of line 0 to start off
					
					while(currentLineIndex != desiredLineIndex) { // if I'm reading the wrong line rn
						while (currentCharacter != '\n') { // read until the end of the line
							currentCharacter = (char)fileReader.read();
						} if (currentCharacter == '\n') { // if we just got to the end of the line...
							currentCharacter = (char)fileReader.read();
							currentLineIndex ++; // ...increment the current line index and loop until I'm on the right line
						}
					}
					
					// reading the correct line number
					while (currentLineIndex == desiredLineIndex) { // I am finally reading the right line
						
						// case where commas must be extracted with data from inside double quotes
						do { // this do-while accommodates for one column potentially having several values inside quotes
							if (currentCharacter.equals('"')) {
								currentCharacter = (char)fileReader.read(); // go to next character after the opening quote
								while (!currentCharacter.equals('"')) { // read all characters up until closing quote
									currentString += currentCharacter; // read non-quotes into string
									currentCharacter = (char)fileReader.read(); // reader will be on closing quote on last iteration
								}
								currentCharacter = (char)fileReader.read(); // read next character after closing quote
							} else {
								break; //breaks if the character isn't a quote to avoid infinite while loop 
							}
						} while (!currentCharacter.equals(',') && !currentCharacter.equals('\n')); // reader will be on a delimiting comma on last iteration (except at EOL)
						
						// case where column data does not have quotes
						while (!currentCharacter.equals(',') && !currentCharacter.equals('\n') && !currentCharacter.equals('\r')) {
							currentString += currentCharacter;
							currentCharacter = (char)fileReader.read();  // reader will be on a delimiting comma on last iteration (except at EOL)
						}
						
						// at this point, currentCharacter must be either , or \n
						if (currentCharacter.equals(',') || currentCharacter.equals('\r')) { //checks for \r as it comes before the newline (\n) of each line
							// column incrementing process
							currentCSVArray[currentColumn] = currentString; // store data
							currentColumn++; // next column
							currentString = ""; // reset string
							currentCharacter = (char)fileReader.read();
						} else { // if this is executed, currentCharacter must be \n
							// line incrementing process
							currentColumn = 0; // reset column
							currentString = ""; // reset string
							currentLineIndex++; // after reading the correct line, this exits the reading loop
						}
					}
				} 
				catch (IOException e){
					System.out.println("IOException");
					System.out.println(e);
				}
				
			return currentCSVArray; //Return the collection of columns for the corresponding line
		}
		catch (FileNotFoundException e){
			System.out.println("File not found.");
			System.out.println(e);
		}
		
		return new String[1]; //Returning small array when function fails
			  
	}
	
	public static boolean isValidFloat(String checkingFloat) {
		float convertedFloat = Float.valueOf(checkingFloat);
		
		if(convertedFloat >= 0) {
			return true;
		} else if(convertedFloat < 0) {
			return false;
		} else {
			return false;
		}
	}
	
	public static boolean isValidInt(String checkingInt) {
		int convertedInt;
		try {
			convertedInt = Integer.valueOf(checkingInt);
		} catch (NumberFormatException e) {
			return false;
		}
		
		if(convertedInt >= 0) {
			return true;
		} else if(convertedInt < 0) {
			return false;
		} else {
			return false;
		}
	}
	
	public static boolean isValidInt(String checkingInt, int integer1, int integer2) {//not sure what the two extra integers are for...
		return false; //placehol
	}
	
	public static boolean isValidString(String checkingString) {
		if(checkingString.isBlank()||checkingString.isEmpty()||checkingString.equals(null)) {
			return false;
		} else {
			return true;
		}
	}
}