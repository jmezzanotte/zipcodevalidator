package com.wsi.mezz.zipcodevalidation;

import java.util.Arrays;
import java.util.Stack;
import com.wsi.mezz.exception.InvalidZipCodeException;

public class ZipCode implements Comparable{

	private int zipcode; 
	public final int ZIPCODE_DIGITS = 5; 
	public final String NON_NUMERIC_ERROR = "Zipcode provided contains non-numeric characters.";
	public final String NUM_DIGITS_ERROR = "Zipcode provided consists of too many or too few digits.";
	
	/**
	 * A ZipCode object can not be instantiated unless a valid zipcode is provided. 
	 * For this class a valid zipcode must: 1.) Contain only numeric characters, 2.) must be 
	 * 5 digits. 
	 * @param zipcode
	 * @throws InvalidZipCodeException
	 */
	public ZipCode(int zipcode) throws InvalidZipCodeException{
		setZipCode(zipcode);
	}

	/**
	 * This method validates the incoming zipcode adheres to validation rules for a zipcode. 
	 * For this class a valid zipcode must: 1.) Contain only numeric characters, 2.) must be 
	 * 5 digits. 
	 * @param zipcode
	 * @throws InvalidZipCodeException
	 */
	public void setZipCode(int zipcode) throws InvalidZipCodeException{
			
		// check the number of digits is 5 
		int len = (int)Math.log10(zipcode)+1; 
						
		if(len != ZIPCODE_DIGITS){
			throw new InvalidZipCodeException(NUM_DIGITS_ERROR); 
		}
		
		this.zipcode = zipcode;
	}
	
	public int getZipCode(){
		return zipcode;
	}

	@Override
	public String toString(){
		return String.format("%d", zipcode);
	}
		
	@Override
	public boolean equals(Object obj){
		if(obj instanceof ZipCode){
			ZipCode temp = (ZipCode)obj; 
			return zipcode == temp.getZipCode();
		}else{
			return false; 
		}
	}
	
	@Override
	public int compareTo(Object obj){
		
		final int BEFORE = -1; 
		final int EQUAL = 0; 
		final int AFTER = 1; 
	
		if(obj instanceof ZipCode){
			ZipCode temp = (ZipCode)obj; 
			if(zipcode < temp.getZipCode()){
				return BEFORE; 
			}else if(temp.getZipCode() == zipcode){
				return EQUAL; 
			}else{
				return AFTER;
			}
		}else{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * This method takes in an array of Pair objects and determines whether or not the 
	 * range between the upper and lower bounds of the pair overlap. If there are 
	 * pairs that overlap the method will return the minimum number of ranges without 
	 * overlap.
	 * @param zipcodes
	 * @return Returns a string containing the minimum number or ranges without overlap.
	 * @throws InvalidZipCodeException
	 */
	public static String zipcodeOverlap(Pair[] zipcodes) throws InvalidZipCodeException{
		
		Stack<Pair> s = new Stack();
		
		StringBuilder result = new StringBuilder("");
		
        // This sort is very important. We have to make sure that we sort each ordered
        // Pair in the list sorted by its lower bound. The stack logic will not work otherwise
        Arrays.sort(zipcodes);
		
		s.push(zipcodes[0]);
		
		for(int i = 1; i < zipcodes.length; i++){
			
			// x1, x2 will represent the upper and lowerbound of the first range pushed to the stack
			// we will peek this range and compare it to the next zipcode range y1 and y2
			ZipCode x1 = (ZipCode) s.peek().getLowerBound();
			ZipCode x2 = (ZipCode) s.peek().getUpperBound();
			ZipCode y1 = (ZipCode) zipcodes[i].getLowerBound();
			ZipCode y2 = (ZipCode) zipcodes[i].getUpperBound();
			
			int x1Lower = x1.getZipCode(); 
			int x2Upper = x2.getZipCode();
			int y1Lower = y1.getZipCode(); 
			int y2Upper = y2.getZipCode(); 
			
			if((y1Lower >= x1Lower && y1Lower <= x2Upper)){
				// There is an overlap
				
				// do we need to replace the upper bound or keep it?
				if(y2Upper > x2Upper){
					x2.setZipCode(y2Upper);
				}

			}else if(y1Lower < x1Lower && y2Upper >= x2Upper){
				
				// There is an overlap. Replace upper and lower bound of current range
				x1.setZipCode(y1Lower);
				x2.setZipCode(y2Upper);
				
			}else if(y1Lower < x1Lower && y2Upper < x2Upper){
				x1.setZipCode(y1Lower);
				
			}else{
				
				// if there is no overlap, push onto stack
				s.push(zipcodes[i]);
			}
		}
		
		while(!s.isEmpty()){
			result.append(s.pop());
			result.append(" ");
		}
		
		// Extra spaced added when building string, trim it off before returning
		return result.toString().trim();
		
	}
	
		
}
