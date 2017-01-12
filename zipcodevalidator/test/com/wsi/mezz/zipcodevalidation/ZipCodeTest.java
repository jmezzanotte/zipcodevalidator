package com.wsi.mezz.zipcodevalidation;
import com.wsi.mezz.zipcodevalidation.ZipCode;
import com.wsi.mezz.exception.InvalidZipCodeException;
import com.wsi.mezz.exception.PairOutOfRangeException;
import static org.junit.Assert.*;
import org.junit.Test;

public class ZipCodeTest {

	@Test
	public void testInit() {
		ZipCode zip = null; 
		
		try{
			zip = new ZipCode(123); 
		}catch(InvalidZipCodeException e){
			System.out.println(e.getMessage()); 
		}
		
		assertEquals(zip,null);
	}
	
	@Test 
	public void testCompareTo(){
		
		ZipCode zipOne = null; 
		ZipCode zipTwo = null; 
		ZipCode zipThree = null;
		String otherType = "Other type";
		Throwable except = null; 
		
		try{
			zipOne = new ZipCode(95674); 
			zipTwo = new ZipCode(94550); 
			zipThree = new ZipCode(94550);
			zipThree.compareTo(otherType);
		}catch(InvalidZipCodeException e){
			System.out.println(e.getMessage());
		}catch(IllegalArgumentException e){
			except = e; 
		}
		
		assertEquals(zipOne.compareTo(zipTwo), 1); 
		assertEquals(zipTwo.compareTo(zipOne), -1);
		assertEquals(zipTwo.compareTo(zipThree), 0);
		assertTrue(except instanceof IllegalArgumentException); 

	}
	
	@Test
	public void testSetZip(){
		
		ZipCode zip = null;
		ZipCode zipTwo = null; 
		Throwable except = null;
		Throwable exceptTwo = null;
		
		try{
			zip = new ZipCode(12345);
			zip.setZipCode(23);
		}catch(InvalidZipCodeException e){
			except = e; 
			exceptTwo = e; 
		}
		
		assertTrue(except instanceof InvalidZipCodeException); 
		
	}
	
	@Test
	public void testEquals(){
		
		ZipCode zipOne = null; 
		ZipCode zipTwo = null; 
		
		try{
			zipOne = new ZipCode(12345);
			zipTwo = new ZipCode(12345); 
			
			assertTrue(zipOne.equals(zipTwo));
			
		}catch(InvalidZipCodeException e){
			System.out.println(e.getMessage()); 
		}
	
	}
	
	
	@Test
	public void testZipcodeOverlap(){
		try{
			
			Pair<ZipCode> zipOne = new Pair(new ZipCode(20000), new ZipCode(40000));
			Pair<ZipCode> zipTwo = new Pair(new ZipCode(10000), new ZipCode(50000));
			
			Pair<ZipCode> zipThree = new Pair(new ZipCode(10000), new ZipCode(20000));
			Pair<ZipCode> zipFour = new Pair(new ZipCode(30000), new ZipCode(50000));
			Pair<ZipCode> zipFive = new Pair(new ZipCode(40000), new ZipCode(50000));
			Pair<ZipCode> zipSix = new Pair(new ZipCode(12345), new ZipCode(24001)); 
			Pair<ZipCode> zipSeven = new Pair(new ZipCode(34567), new ZipCode(40000)); 
			Pair<ZipCode> zipEight = new Pair(new ZipCode(24000), new ZipCode(30000)); 
			Pair<ZipCode> zipNine = new Pair(new ZipCode(29999), new ZipCode(35000));
			
			Pair[] zipRangesOne = {zipOne, zipTwo};
			Pair[] zipRangesTwo = {zipThree, zipFour, zipFive};
			Pair[] zipRangesThree = {zipSix, zipSeven, zipEight, zipNine};
			
			String result1 = ZipCode.zipcodeOverlap(zipRangesOne);
			String result2 = ZipCode.zipcodeOverlap(zipRangesTwo);
			String result3 = ZipCode.zipcodeOverlap(zipRangesThree);
			System.out.println(result3);
			assertEquals(result1, "[10000, 50000]");
			assertEquals(result2, "[30000, 50000] [10000, 20000]");
			assertEquals(result3, "[12345, 40000]");
			
			
			
		}catch(PairOutOfRangeException e){
			System.out.println(e.getMessage());
		}catch(InvalidZipCodeException e){
			System.out.println(e.getMessage());
		}
	}
	
}
