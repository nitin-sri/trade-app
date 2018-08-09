package com.jpmc.trade.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.jpmc.trade.enums.BuySellIndicator;
import com.jpmc.trade.enums.Currency;

public final class Trade {
	private final String entity;
	private final BuySellIndicator indicator;
	private final BigDecimal agreedFx;
	private final Currency currency;
	private final LocalDate instructionDate;
	private final LocalDate settlementDate;
	private final long units;
	private final BigDecimal pricePerUnit;

	public Trade(String entity, BuySellIndicator indicator, BigDecimal agreedFx, Currency currency,
			LocalDate instructionDate, LocalDate settlementDate, long units, BigDecimal pricePerUnit) {
		this.entity = entity;
		this.indicator = indicator;
		this.agreedFx = agreedFx;
		this.currency = currency;
		this.instructionDate = instructionDate;
		this.units = units;
		this.pricePerUnit = pricePerUnit;
		this.settlementDate = settlementDate;
	}
	public String getEntity() {
		return entity;
	}

	public BuySellIndicator getIndicator() {
		return indicator;
	}

	public BigDecimal getAgreedFx() {
		return agreedFx;
	}

	public Currency getCurrency() {
		return currency;
	}

	public LocalDate getInstructionDate() {
		return instructionDate;
	}

	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	public long getUnits() {
		return units;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}
	
	public BigDecimal getUSDAmountOfTrade() {
		return pricePerUnit.multiply(BigDecimal.valueOf(units)).multiply(agreedFx);
	}

}
