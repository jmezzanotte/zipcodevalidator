package com.wsi.mezz.zipcodeapp;

import java.util.Scanner;
import java.util.Arrays;

import com.wsi.mezz.exception.InvalidZipCodeException;
import com.wsi.mezz.exception.PairOutOfRangeException;
import com.wsi.mezz.zipcodevalidation.Pair;
import com.wsi.mezz.zipcodevalidation.ZipCode;

public class ZipCodeDriver {
	
	public static boolean keepGoing = true;
	public static final String INPUT_PROMPT = ">> ";
	
	public static void main(String[] args){

		while(keepGoing){
			switchBoard();
		}
	}

	/**
	 * Helper method to control program input
	 */
	private static void switchBoard(){
	        
		Scanner stdin = new Scanner(System.in);
		
	    final String INPUT_FORMAT = "Please enter zipcode ranges as [95674,94550] [12345,65784].";
	    final String INVALID_FORMAT = "Input not entered in correct format.";
	    
	    String userMenu = 
	    		"\nSELECT An Action\n" +
	            "-----------------------------------------------------------\n" +
	            "Enter 'z' - to enter zipcode ranges\n" + 
	            "-----------------------------------------------------------\n" +
	            "Enter any other character to quit\n";
	         
	    System.out.println(userMenu);
	         
	    String selection = stdin.nextLine();
	    
	    switch(selection){
	             
	    	case "z" :
	    		
	    		try{
		    		Scanner zipIn = new Scanner(System.in);
		    		
		            System.out.println(INPUT_FORMAT);
		            System.out.print(INPUT_PROMPT);
		            
		            // Creating an array that will hold arrays containing the 
		            // zipcode ranges entered by the user.
		            String[][] cleanInputList = cleanInput(zipIn.nextLine());
		            
		            // This array will receive the returned array of ZipCode ordered Pair
		            // objects returned by the validateZipCode Method
		            Pair<ZipCode>[] zipcodeList = validateZipCode(cleanInputList);
		            
		            // Now that we have our zipcodes validated and placed into 
		            // ordered pairs via the Pair object, we can call the static 
		            // method of the ZipCode class to find overlapping zipcode ranges.
		           	System.out.println(ZipCode.zipcodeOverlap(zipcodeList));
		           	                          
	    		}catch(ArrayIndexOutOfBoundsException e){
					System.out.println(INVALID_FORMAT);
				}catch(NullPointerException e){
					System.out.println(INVALID_FORMAT);
				}catch(InvalidZipCodeException e){
					System.out.println(INVALID_FORMAT);
				}
		           
	            break;
	    	default :
	    		keepGoing = false;
	            System.out.println("Goodbye");
	            break;  
	        }
	 }
	
	/**
	 * Helper method to clean initial user input. This method will remove brackets and 
	 * commas, sorts each array and places the values into an array that can be used for further 
	 * processing. 
	 * @param input
	 * @return
	 */
	private static String[][] cleanInput(String input){
		
		String bracketExp = "[\\[\\]()]"; 
		
		String[] out = input.split(" ");
		
		String[][] tempArr = new String[out.length][];
		
		// Placing cleaned output into an array that will hold an array of the 
		// String cleaned zipcode strings. 
		for(int i=0; i < out.length; i++){
			String[] x = out[i].replaceAll(bracketExp, "").split(",");
			Arrays.sort(x);
			tempArr[i] =x; 
		}
		
		return tempArr; 
	}
	
	/**
	 * This helper method takes the string array produced by the cleanInput method and parses
	 * its data into integers, which can then be converted into ZipCode objects. This will 
	 * allow us to validate whether are not integers are valid ZipCodes.
	 * @param arr
	 * @return Returns an array of ordered pairs that can then be analyzed for range overlap.
	 * @throws InvalidZipCodeException
	 */
	private static Pair<ZipCode>[] validateZipCode(String[][] arr) throws InvalidZipCodeException{
		
		Pair<ZipCode>[] orderedPairList = new Pair[arr.length]; 
		
		for(int i=0; i < arr.length; i++){
			
			String[] temp = arr[i];
			
			ZipCode lower = null; 
			ZipCode upper = null;

			try{
				lower = new ZipCode(Integer.parseInt(temp[0])); 
				upper = new ZipCode(Integer.parseInt(temp[1]));
				orderedPairList[i] = new Pair<ZipCode>(lower, upper );
			}catch(PairOutOfRangeException e){
				throw new InvalidZipCodeException("");
			}catch(NumberFormatException e){
				throw new InvalidZipCodeException("");
			}
		}
		
		return orderedPairList; 		
	}
	
}




