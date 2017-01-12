package com.wsi.mezz.zipcodevalidation;

import com.wsi.mezz.exception.PairOutOfRangeException;

public class Pair<T extends Comparable> implements Comparable  {

	private T lowerBound; 
	private T upperBound; 
	
	public Pair(T lowerBound, T upperBound) throws PairOutOfRangeException{
		setPair(lowerBound, upperBound);
	}
	
	public void  setPair(T lBound, T uBound) throws PairOutOfRangeException{
		// validate that lowerBound is less than or equal to upperbound and vice versa
		if(isValidBounds(lBound, uBound)){
			lowerBound = lBound; 
			upperBound = uBound;
		}else{
			throw new PairOutOfRangeException("Invalid lower or upper bounds provided");
		}
		
	}
	
	public void setUpperBound(T uBound) throws PairOutOfRangeException{
		
		if(isValidBounds(lowerBound, uBound)){
			upperBound = uBound;
		}else{
			throw new PairOutOfRangeException("Invalid upper bound provided");
		}
	}
	
	/**
	 * Helper method to validate the upper and lower bounds of the Pair. 
	 * @param lBound
	 * @param uBound
	 * @return Returns true if bounds are valid, false otherwise.
	 */
	private boolean isValidBounds(T lBound, T uBound){
		if(lBound.compareTo(uBound) > 0 || uBound.compareTo(lBound) < 0){
			return false; 
		}else{
			return true;
		}
	}
	
	public T getLowerBound(){
		return lowerBound;
	}
	
	public T getUpperBound(){
		return upperBound;
	}
	
	@Override
	public String toString(){
		return String.format("[%s, %s]", lowerBound.toString(), upperBound.toString());
	}
	
	@Override
	public int compareTo(Object obj){
		if(obj instanceof Pair){
			Pair temp = (Pair)obj;
			return lowerBound.compareTo(temp.getLowerBound());
		}else{ 
			throw new IllegalArgumentException();
		}
	}
	
	@Override 
	public boolean equals(Object obj){
		if(obj instanceof Pair){
			Pair temp = (Pair)obj;
			return lowerBound == temp.getLowerBound() && upperBound == temp.getUpperBound(); 
		}else{
			return false;
		}
		
	}
	
	
}
