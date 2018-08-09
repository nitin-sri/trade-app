package com.jpmc.trade.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpmc.trade.dao.TradeDao;
import com.jpmc.trade.enums.BuySellIndicator;
import com.jpmc.trade.model.Trade;

//@Service - we  can hook Spring framework
public class TradeServiceImpl implements TradeService {

	private TradeDao tradeDao;

	public TradeServiceImpl(TradeDao tradeDao) {
		this.tradeDao = tradeDao;
	}

	public BigDecimal fetchAmountInUSDSettled(BuySellIndicator buySellIndicator) {
		return tradeDao.fetchAmountInUSDSettled(buySellIndicator);
	}

	public BigDecimal fetchAmountInUSDSettled(LocalDate settlementDate, BuySellIndicator buySellIndicator) {
		return tradeDao.fetchAmountInUSDSettled(settlementDate, buySellIndicator);
	}

	public List<Trade> fetchEntitiesBasedOnRanking(BuySellIndicator buySellIndicator) {
		return tradeDao.fetchEntitiesBasedOnRanking(buySellIndicator);
	}

	public void createTrade(Trade trade) {
		tradeDao.createTrade(trade);

	}
	
	public Map<String, BigDecimal> getSettledAmountReportByDate(LocalDate settlementDate){
		Map<String, BigDecimal> settledAmountMap = new HashMap<>();
		BigDecimal incomingAmount = this.fetchAmountInUSDSettled(settlementDate,BuySellIndicator.S);
		BigDecimal outgoingAmount = this.fetchAmountInUSDSettled(settlementDate, BuySellIndicator.B);
		settledAmountMap.put("IncomingAmount", incomingAmount);
		settledAmountMap.put("OutgoingAmount", outgoingAmount);
		return settledAmountMap;
		
	}

}
