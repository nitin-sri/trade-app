package com.jpmc.trade.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jpmc.trade.dao.TradeDao;
import com.jpmc.trade.dao.TradeDaoImpl;
import com.jpmc.trade.enums.BuySellIndicator;
import com.jpmc.trade.enums.Currency;
import com.jpmc.trade.model.Trade;

public class TradeServiceImplTest {

	TradeDao tradeDao;
	TradeService tradeService;


	@Before
	public void setUp() throws Exception {
		
		tradeDao = new TradeDaoImpl();
		tradeService = new TradeServiceImpl(tradeDao);
		
		Trade tradefoo = new Trade("foo",BuySellIndicator.B, new BigDecimal(".50"), Currency.SGP, LocalDate.of(2016, 01, 01), LocalDate.of(2016, 01, 02), 200, new BigDecimal("100.25"));
		Trade tradebar = new Trade("bar",BuySellIndicator.S, new BigDecimal(".22"), Currency.AED, LocalDate.of(2016, 01, 05), LocalDate.of(2016, 01, 07), 450, new BigDecimal("150.5"));
		
		Trade tradefoo2 = new Trade("foo2",BuySellIndicator.B, new BigDecimal(".50"), Currency.SGP, LocalDate.of(2016, 01, 01), LocalDate.of(2016, 01, 02), 300, new BigDecimal("100.25"));
		Trade tradebar2 = new Trade("bar2",BuySellIndicator.S, new BigDecimal(".22"), Currency.AED, LocalDate.of(2016, 01, 05), LocalDate.of(2016, 01, 07), 550, new BigDecimal("150.5"));
		
		Trade tradefoo3 = new Trade("foo3",BuySellIndicator.B, new BigDecimal(".50"), Currency.SGP, LocalDate.of(2016, 01, 01), LocalDate.of(2016, 01, 02), 400, new BigDecimal("100.25"));
		Trade tradebar3 = new Trade("bar3",BuySellIndicator.S, new BigDecimal(".22"), Currency.AED, LocalDate.of(2016, 01, 05), LocalDate.of(2016, 01, 07), 650, new BigDecimal("150.5"));
		
		tradeService.createTrade(tradefoo);
		tradeService.createTrade(tradebar);
		tradeService.createTrade(tradefoo2);
		tradeService.createTrade(tradebar2);
		tradeService.createTrade(tradefoo3);
		tradeService.createTrade(tradebar3);
	}

	@Test
	public void shoulFetchAmountInUSDSettledForBuy() {
		BigDecimal amount = tradeService.fetchAmountInUSDSettled(BuySellIndicator.B);
		assertEquals(new BigDecimal("45112.5000"), amount);
	}

	@Test 
	public void shouldFetchEntitiesBasedOnRanking() {
		List<Trade> outgoingTrades = tradeService.fetchEntitiesBasedOnRanking(BuySellIndicator.B);
		assertEquals("foo3", outgoingTrades.get(0).getEntity());
	}
	
}
