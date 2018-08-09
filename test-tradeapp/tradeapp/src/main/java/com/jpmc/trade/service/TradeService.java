package com.jpmc.trade.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.jpmc.trade.enums.BuySellIndicator;
import com.jpmc.trade.model.Trade;

public interface TradeService {
	BigDecimal fetchAmountInUSDSettled(BuySellIndicator buySellIndicator);
	
	BigDecimal fetchAmountInUSDSettled(LocalDate settlementDate, BuySellIndicator buySellIndicator);

	List<Trade> fetchEntitiesBasedOnRanking(BuySellIndicator buySellIndicator);
	
	Map<String, BigDecimal> getSettledAmountReportByDate(LocalDate settlementDate);

	void createTrade(Trade trade);

}
