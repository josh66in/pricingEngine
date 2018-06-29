package com.barclayscard.interview.pricingengine.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class InputParser {

	private Engine engine = new Engine();
	
	//parses the input data received from command line in form of list and sends it to pricing engine.
	public void parse(List<String> productList, List<String> competitorList) {
	
		for(String str : productList) {
			StringTokenizer stkr = new StringTokenizer(str);
			
			while(stkr.hasMoreTokens()) {
				
				String productName = stkr.nextToken();
				String supply = stkr.nextToken();
				String demand = stkr.nextToken();
						
				Product product = new Product(productName);
				
				if(supply.equals("L")) {
					product.setSupply(SupplyDemandValues.LOW);
				} else {
					product.setSupply(SupplyDemandValues.HIGH);
				}
				
				if(demand.equals("L")) {
					product.setDemand(SupplyDemandValues.LOW);
				} else {
					product.setDemand(SupplyDemandValues.HIGH);
				}
				engine.getProductMap().put(productName, product);
				engine.getProductNameMasterList().add(productName);
			}
			
		}
		
		for(String str : competitorList) {
			StringTokenizer stkr = new StringTokenizer(str);
			
			while(stkr.hasMoreTokens()) {
				
				String productName = stkr.nextToken();
				String competitorName = stkr.nextToken();
				String priceStr = stkr.nextToken();
				
				Float price = Float.parseFloat(priceStr);
				
				CompetitorPrice competitorPrice = new CompetitorPrice(competitorName, price);
				
				List<CompetitorPrice> competitorPriceList = engine.getCompetitorPriceMap().get(productName);
				
				if(competitorPriceList == null) {
					competitorPriceList = new ArrayList<>();
				}
				competitorPriceList.add(competitorPrice);
				engine.getCompetitorPriceMap().put(productName, competitorPriceList);			
				
			}
			
		}
		
		this.getEngine().resolveOurPrice();
		
	}
	
	public static void main(String args[]) {
	
		List<String> productsListStrings = new ArrayList<>();
		String str = null;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			str = br.readLine();
			Integer countOfProducts = Integer.parseInt(str);
			for(int i = 0; i < countOfProducts; i++) {
				productsListStrings.add(br.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			str = br.readLine();
		
			Integer countOfCompetitors = Integer.parseInt(str);
			
			List<String> competitorsListStrings = new ArrayList<>();
			for(int i = 0; i < countOfCompetitors; i++) {
			
				competitorsListStrings.add(br.readLine());
			}	

			InputParser parser = new InputParser();
			parser.parse(productsListStrings, competitorsListStrings);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public Engine getEngine() {
		return engine;
	}
	
}
