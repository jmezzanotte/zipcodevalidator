package com.wsi.mezz.zipcodevalidation;
import com.wsi.mezz.zipcodevalidation.Pair;
import com.wsi.mezz.exception.PairOutOfRangeException;
import static org.junit.Assert.*;

import org.junit.Test;

public class PairTest {

	@Test
	public void testSetPairFail() {
		
		Pair orderedPair = null;
		Throwable except = null;
		
		try{
			orderedPair = new Pair(200, 100);		
		}catch(PairOutOfRangeException e){
			except = e; 
		}
	
		assertTrue(except instanceof PairOutOfRangeException); 
		
	}
	
	@Test
	public void testSetPairStringPass(){
		
		Pair orderedPair = null; 
		
		try{
			orderedPair = new Pair("a", "x");
		
		}catch(PairOutOfRangeException e){
			System.out.println(e.getMessage());
		}
		
		assertTrue(orderedPair != null); 
		
	}
	
	@Test 
	public void testSetPairPass(){
		
		Pair orderedPair = null; 
		Throwable except = null; 
		
		try{
			orderedPair = new Pair(1,2); 
			orderedPair.setPair(1111, 2222); 
		}catch(PairOutOfRangeException e){
			except = e; 
		}
		
		assertTrue(except == null);
			
	}

	
	@Test
	public void testCompareTo(){
		
		Throwable except = null;
		
		try{
			
			Pair<Integer> p1 = new Pair<Integer>(95674, 99900); 
			Pair<Integer> p2 = new Pair<Integer>(94000, 99910); 
			Pair<Integer> p3 = new Pair<Integer>(94000, 99910); 
			
			String err = "Different object"; 
			
			assertEquals(p1.compareTo(p2), 1);
			assertEquals(p2.compareTo(p1), -1); 
			assertEquals(p2.compareTo(p3), 0);
			p3.compareTo(err);
		}catch(PairOutOfRangeException e){
			System.out.println(e.getMessage());
		}catch(IllegalArgumentException e){
			except = e;
		}
		
		assertTrue(except instanceof IllegalArgumentException);
		
	}
	
	
	@Test
	public void testEquals(){
		
		try{
			Pair p1 = new Pair(123, 456); 
			Pair p2 = new Pair(123, 456); 
			Pair p3 = new Pair(789, 345); 
			String err = "Different object"; 
			
			assertTrue(p1.equals(p2)); 
			assertFalse(p1.equals(p3));
			assertFalse(p1.equals(err));
		}catch(PairOutOfRangeException e){
			System.out.println(e.getMessage()); 
		}
		
	}

}
