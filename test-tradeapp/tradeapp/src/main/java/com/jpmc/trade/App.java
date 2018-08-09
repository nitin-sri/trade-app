package com.jpmc.trade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.jpmc.trade.dao.TradeDao;
import com.jpmc.trade.dao.TradeDaoImpl;
import com.jpmc.trade.enums.BuySellIndicator;
import com.jpmc.trade.enums.Currency;
import com.jpmc.trade.model.Trade;
import com.jpmc.trade.service.TradeService;
import com.jpmc.trade.service.TradeServiceImpl;

public class App {
	public static void main(String[] args) {
		// We can autowire using Spring. Temp creating objects.
		TradeDao tradeDao = new TradeDaoImpl();
		TradeService tradeService = new TradeServiceImpl(tradeDao);

		Trade trade = new Trade("foo", BuySellIndicator.B, new BigDecimal(".50"), Currency.SGP,
				LocalDate.of(2016, 01, 01), LocalDate.of(2016, 01, 02), 200, new BigDecimal("100.25"));
		tradeService.createTrade(trade);
		trade = new Trade("bar", BuySellIndicator.S, new BigDecimal(".22"), Currency.AED, LocalDate.of(2016, 01, 05),
				LocalDate.of(2016, 01, 07), 450, new BigDecimal("150.5"));
		tradeService.createTrade(trade);
		trade = new Trade("foo2", BuySellIndicator.B, new BigDecimal(".50"), Currency.SGP, LocalDate.of(2016, 01, 01),
				LocalDate.of(2016, 01, 02), 300, new BigDecimal("100.25"));
		tradeService.createTrade(trade);
		trade = new Trade("bar2", BuySellIndicator.S, new BigDecimal(".22"), Currency.AED, LocalDate.of(2016, 01, 05),
				LocalDate.of(2016, 01, 07), 550, new BigDecimal("150.5"));
		tradeService.createTrade(trade);
		trade = new Trade("foo3", BuySellIndicator.B, new BigDecimal(".50"), Currency.SGP, LocalDate.of(2016, 01, 01),
				LocalDate.of(2016, 01, 02), 400, new BigDecimal("100.25"));
		tradeService.createTrade(trade);
		trade = new Trade("bar3", BuySellIndicator.S, new BigDecimal(".22"), Currency.AED, LocalDate.of(2016, 01, 05),
				LocalDate.of(2016, 01, 07), 650, new BigDecimal("150.5"));
		tradeService.createTrade(trade);

		trade = new Trade("foo4", BuySellIndicator.B, new BigDecimal(".50"), Currency.SGP, LocalDate.of(2016, 01, 01),
				LocalDate.now(), 400, new BigDecimal("100.25"));
		tradeService.createTrade(trade);

		trade = new Trade("bar4", BuySellIndicator.S, new BigDecimal(".22"), Currency.AED, LocalDate.of(2016, 01, 05),
				LocalDate.now(), 650, new BigDecimal("150.5"));
		tradeService.createTrade(trade);

		trade = new Trade("foo5", BuySellIndicator.B, new BigDecimal(".50"), Currency.SGP, LocalDate.of(2016, 01, 01),
				LocalDate.now(), 400, new BigDecimal("100.25"));
		tradeService.createTrade(trade);

		trade = new Trade("bar5", BuySellIndicator.S, new BigDecimal(".22"), Currency.AED, LocalDate.of(2016, 01, 05),
				LocalDate.now(), 650, new BigDecimal("150.5"));
		tradeService.createTrade(trade);

		Map<String, BigDecimal> settledAmountMap = tradeService.getSettledAmountReportByDate(LocalDate.now());

		System.out.println(settledAmountMap);

		List<Trade> incomingTradesByRank = tradeService.fetchEntitiesBasedOnRanking(BuySellIndicator.S);
		List<Trade> outgoingTradesByRank = tradeService.fetchEntitiesBasedOnRanking(BuySellIndicator.B);
		
		System.out.println("Incoming Trades, Rank wise");
		incomingTradesByRank.forEach(t -> System.out.println(t.getEntity()));
		
		System.out.println("Outgoing Trades, Rank wise");
		outgoingTradesByRank.forEach(t -> System.out.println(t.getEntity()));
		
	}

}
