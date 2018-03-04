package com.barclayscard.interview.pricingengine.core;

	enum SupplyDemandValues {
		LOW, HIGH
	};

public class Product {
	
	private String productName = null;
	private SupplyDemandValues supply =  null;
	private SupplyDemandValues demand =  null;
	
	/**
	 * @param productName
	 */
	public Product(String productName) {
		super();
		this.productName = productName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public SupplyDemandValues getSupply() {
		return supply;
	}

	public void setSupply(SupplyDemandValues supply) {
		this.supply = supply;
	}

	public SupplyDemandValues getDemand() {
		return demand;
	}

	public void setDemand(SupplyDemandValues demand) {
		this.demand = demand;
	}
	
}
