package com.jpmc.trade.enums;

public enum BuySellIndicator {
	B("BUY"), S("SELL");

	private final String description;

	private BuySellIndicator(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}
