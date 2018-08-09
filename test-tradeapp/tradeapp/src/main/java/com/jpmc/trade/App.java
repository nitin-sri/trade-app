package com.jpmc.trade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

		List<Trade> tradesSorted = tradeService.fetchEntitiesBasedOnRanking(BuySellIndicator.S);
		BigDecimal amount = tradeService.fetchAmountInUSDSettled(BuySellIndicator.S);
		System.out.println(amount);

		for (Trade trade1 : tradesSorted) {
			System.out.println(trade1);
			System.out.println(trade1.getEntity() + " amount " + trade1.getUSDAmountOfTrade());
		}

	}

}
