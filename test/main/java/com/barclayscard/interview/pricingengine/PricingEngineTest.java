package com.barclayscard.interview.pricingengine;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.barclayscard.interview.pricingengine.core.InputParser;

public class PricingEngineTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOne() {
//		fail("Not yet implemented");
		
		System.out.println("Test One");
		
		InputParser parser = new InputParser();
		
		List<String> productList = new ArrayList<>();
		List<String> competitorList = new ArrayList<>();
		
		productList.add("flashdrive H H");
		productList.add("ssd L H");
		
		competitorList.add("flashdrive X 1.0");
		competitorList.add("ssd X 10.0");
		competitorList.add("flashdrive Y 0.9");
		competitorList.add("flashdrive Z 1.1");
		competitorList.add("ssd Y 12.5");
		
		parser.parse(productList, competitorList);
		
		Iterator<String> itr = (Iterator<String>) (parser.getEngine().getOutputPrice()).iterator();
		
		assertEquals(itr.next(), "A 0.9");
		assertEquals(itr.next(), "B 10.5");
		
		System.out.println();
		
	}

	@Test
	public void testTwo() {
//		fail("Not yet implemented");
		
		System.out.println("Test Two");
		
		InputParser parser = new InputParser();
		
		List<String> productList = new ArrayList<>();
		List<String> competitorList = new ArrayList<>();
		
		productList.add("mp3player H H");
		productList.add("ssd L L");
		
		competitorList.add("ssd W 11.0");
		competitorList.add("ssd X 12.0");
		competitorList.add("mp3player X 60.0");
		competitorList.add("mp3player Y 20.0");
		competitorList.add("mp3player Z 50.0");
		competitorList.add("ssd V 10.0");
		competitorList.add("ssd Y 11.0");
		competitorList.add("ssd Z 12.0");
		
		parser.parse(productList, competitorList);
		
		Iterator<String> itr = (Iterator<String>) (parser.getEngine().getOutputPrice()).iterator();
		
		assertEquals(itr.next(), "A 50.0");
		assertEquals(itr.next(), "B 12.1");
		
		System.out.println();
		
	}
	
	@Test
	public void testThree() {
//		fail("Not yet implemented");
		
		System.out.println("Test Three");
		
		InputParser parser = new InputParser();
		
		List<String> productList = new ArrayList<>();
		List<String> competitorList = new ArrayList<>();
		
		productList.add("mp3player H H");
		productList.add("ssd L L");
		productList.add("flashdrive H H");
		
		competitorList.add("ssd W 11.0");
		competitorList.add("ssd X 12.0");
		competitorList.add("mp3player X 60.0");
		competitorList.add("mp3player Y 20.0");
		competitorList.add("mp3player Z 50.0");
		competitorList.add("ssd V 10.0");
		competitorList.add("ssd Y 11.0");
		competitorList.add("ssd Z 12.0");
		competitorList.add("flashdrive X 1.0");
		competitorList.add("ssd X 10.0");
		competitorList.add("flashdrive Y 0.9");
		competitorList.add("flashdrive Z 1.1");
		competitorList.add("ssd Y 12.5");
		
		parser.parse(productList, competitorList);
		
		Iterator<String> itr = (Iterator<String>) (parser.getEngine().getOutputPrice()).iterator();
		
		assertEquals(itr.next(), "A 50.0");
		assertEquals(itr.next(), "B 11.0");
		assertEquals(itr.next(), "C 0.9");
		
		System.out.println();
		
	}
}
