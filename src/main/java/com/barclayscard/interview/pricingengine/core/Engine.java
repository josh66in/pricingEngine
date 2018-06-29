package com.barclayscard.interview.pricingengine.core;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Engine {

	//remembers the productname and market conditions.
	private Map<String, Product> productMap = new HashMap<>();
	// remembers the productname and competitors and thier prices.
	private Map<String, List<CompetitorPrice>> competitorPriceMap = new HashMap<>();
	//remembers our final prices.
	private Map<String, Float> ourPrice = new HashMap<String, Float>();
	//remembers the productnames in the order they were received from input for proper output.
	private List<String> productNameMasterList = new ArrayList<>();
	
	private List<String> outputPrice = new ArrayList<>();

	public Map<String, Product> getProductMap() {
		return productMap;
	}

	public List<String> getProductNameMasterList() {
		return productNameMasterList;
	}

	public void setProductNameMasterList(List<String> productNameMasterList) {
		this.productNameMasterList = productNameMasterList;
	}

	public void setProductMap(Map<String, Product> productMap) {
		this.productMap = productMap;
	}

	public Map<String, List<CompetitorPrice>> getCompetitorPriceMap() {
		return competitorPriceMap;
	}

	public void setCompetitorPriceMap(Map<String, List<CompetitorPrice>> competitorPriceMap) {
		this.competitorPriceMap = competitorPriceMap;
	}

	public Map<String, Float> getOurPrice() {
		return ourPrice;
	}

	public void setOurPrice(Map<String, Float> ourPrice) {
		this.ourPrice = ourPrice;
	}

	//generates our market price from market conditions and other parameters.
	public void resolveOurPrice() {
		
		Iterator<String> productNameItr = getProductMap().keySet().iterator();
		
		while(productNameItr.hasNext()) {
			
			String productName = productNameItr.next();
			
			Iterator<CompetitorPrice> competitorPriceItr = getCompetitorPriceMap().get(productName).iterator();
			
			float averagePrice = 0;
			float sum = 0;
			
			while(competitorPriceItr.hasNext()) {
				
				CompetitorPrice competitorPrice = competitorPriceItr.next();
				
				float price = competitorPrice.getPrice();
				
				sum += price;		
				
			}
			
			int count = getCompetitorPriceMap().get(productName).size();
			
			if(count != 0)
				averagePrice = sum / count;
			else
				averagePrice = 0;
			
			Iterator<CompetitorPrice> competitorPriceItr1 = getCompetitorPriceMap().get(productName).iterator();
			
			Map<Float, Integer> priceCount = new HashMap<>();
			
			//finds average and price count to know how frequently a price occurs.
			while(competitorPriceItr1.hasNext()) {
				
				CompetitorPrice competitorPrice = competitorPriceItr1.next();
				
				float price = competitorPrice.getPrice();
				
				if(price > 1.5 * averagePrice 
						|| price < 0.5 * averagePrice)
					continue;	
				
				Integer count1 = priceCount.get(new Float(price));
				
				if(count1 == null) {
					priceCount.put(new Float(price), new Integer(1));
				} else {
					priceCount.put(new Float(price), new Integer(++count1));
				}
				
			}
	
			int maxCount = 0;
			float chosenPrice = 0;
			
			Iterator<Float> priceCountItr = priceCount.keySet().iterator();
			
			//selects the chosenPrice.
			while(priceCountItr.hasNext()) {
				
				float price1 = priceCountItr.next();
				int count1 = priceCount.get(new Float(price1));
				
				if(count1 > maxCount) {
					maxCount = count1;
					chosenPrice = price1;
				}
				
				if(count1 == maxCount) {
					maxCount = count1;
					if(chosenPrice > price1)
						chosenPrice = price1;
				}
				
			}
			
			Product product = getProductMap().get(productName);
			
			//applies the logic to generate price given market conditions.
			if(product.getSupply().equals(SupplyDemandValues.HIGH) && product.getDemand().equals(SupplyDemandValues.HIGH)) {
				getOurPrice().put(productName, new Float(chosenPrice));
			} else if(product.getSupply().equals(SupplyDemandValues.LOW) && product.getDemand().equals(SupplyDemandValues.LOW)) {
				getOurPrice().put(productName, new Float(1.1 * chosenPrice));
			} else if(product.getSupply().equals(SupplyDemandValues.LOW) && product.getDemand().equals(SupplyDemandValues.HIGH)) {
				getOurPrice().put(productName, new Float(1.05 * chosenPrice));
			} else if(product.getSupply().equals(SupplyDemandValues.HIGH) && product.getDemand().equals(SupplyDemandValues.LOW)) {
				getOurPrice().put(productName, new Float(0.95 * chosenPrice));
			}
			
		}
		
		this.printOutput();
		
	}


	//outputs the price.
	public void printOutput() {
		
		DecimalFormat df = new DecimalFormat("##0.0");

		char alphabet = 'A';
		
		Iterator<String> productNameItr = getProductNameMasterList().iterator();
		
		while(productNameItr.hasNext()) {
			
			String productName = productNameItr.next();
		
			String str = alphabet + " " + df.format(getOurPrice().get(productName));
			
			outputPrice.add(str);
			
			System.out.println(str);
			
			alphabet++;
			
		}
	}

	public List<String> getOutputPrice() {
		return outputPrice;
	}
	
}
