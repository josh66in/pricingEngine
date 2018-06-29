package com.barclayscard.interview.pricingengine.core;

public class CompetitorPrice {
	
	private String competitorName = null;
	private Float price = null;
	/**
	 * @param productName
	 * @param competitorName
	 * @param price
	 */
	public CompetitorPrice(String competitorName, Float price) {
		super();
		this.competitorName = competitorName;
		this.price = price;
	}

	public String getCompetitorName() {
		return competitorName;
	}
	public void setCompetitorName(String competitorName) {
		this.competitorName = competitorName;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}

}
