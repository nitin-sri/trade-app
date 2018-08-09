package com.jpmc.trade.service;

import java.math.BigDecimal;
import java.util.List;

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

	public List<Trade> fetchEntitiesBasedOnRanking(BuySellIndicator buySellIndicator) {
		return tradeDao.fetchEntitiesBasedOnRanking(buySellIndicator);
	}

	public void createTrade(Trade trade) {
		tradeDao.createTrade(trade);

	}

}
