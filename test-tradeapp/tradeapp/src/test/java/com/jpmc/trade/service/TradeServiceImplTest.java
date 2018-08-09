package com.jpmc.trade.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
		
		Trade trade = new Trade("foo",BuySellIndicator.B, new BigDecimal(".50"), Currency.SGP, LocalDate.of(2016, 01, 01), LocalDate.of(2016, 01, 02), 200, new BigDecimal("100.25"));
		tradeService.createTrade(trade);
		
		trade = new Trade("bar",BuySellIndicator.S, new BigDecimal(".22"), Currency.AED, LocalDate.of(2016, 01, 05), LocalDate.of(2016, 01, 07), 450, new BigDecimal("150.5"));
		tradeService.createTrade(trade);
		
		trade = new Trade("foo2",BuySellIndicator.B, new BigDecimal(".50"), Currency.SGP, LocalDate.of(2016, 01, 01), LocalDate.of(2016, 01, 02), 300, new BigDecimal("100.25"));
		tradeService.createTrade(trade);
		
		trade = new Trade("bar2",BuySellIndicator.S, new BigDecimal(".22"), Currency.AED, LocalDate.of(2016, 01, 05), LocalDate.of(2016, 01, 07), 550, new BigDecimal("150.5"));
		tradeService.createTrade(trade);
		
		trade = new Trade("foo3",BuySellIndicator.B, new BigDecimal(".50"), Currency.SGP, LocalDate.of(2016, 01, 01), LocalDate.of(2016, 01, 02), 400, new BigDecimal("100.25"));
		tradeService.createTrade(trade);
		
		trade = new Trade("bar3",BuySellIndicator.S, new BigDecimal(".22"), Currency.AED, LocalDate.of(2016, 01, 05), LocalDate.of(2016, 01, 07), 650, new BigDecimal("150.5"));
		tradeService.createTrade(trade);
		
		trade = new Trade("foo4",BuySellIndicator.B, new BigDecimal(".50"), Currency.SGP, LocalDate.of(2016, 01, 01), LocalDate.now(), 400, new BigDecimal("100.25"));
		tradeService.createTrade(trade);
		
		trade = new Trade("bar4",BuySellIndicator.S, new BigDecimal(".22"), Currency.AED, LocalDate.of(2016, 01, 05), LocalDate.now(), 650, new BigDecimal("150.5"));
		tradeService.createTrade(trade);
		
		trade = new Trade("foo5",BuySellIndicator.B, new BigDecimal(".50"), Currency.SGP, LocalDate.of(2016, 01, 01), LocalDate.now(), 400, new BigDecimal("100.25"));
		tradeService.createTrade(trade);
		
		trade = new Trade("bar5",BuySellIndicator.S, new BigDecimal(".22"), Currency.AED, LocalDate.of(2016, 01, 05), LocalDate.now(), 650, new BigDecimal("150.5"));
		tradeService.createTrade(trade);
	}

	@Test
	public void shoulFetchAmountInUSDSettledForBuy() {
		BigDecimal amount = tradeService.fetchAmountInUSDSettled(BuySellIndicator.B);
		assertEquals(new BigDecimal("85212.5000"), amount);
	}

	@Test
	public void shoulFetchAmountInUSDSettledForSell() {
		BigDecimal amount = tradeService.fetchAmountInUSDSettled(BuySellIndicator.S);
		assertEquals(new BigDecimal("97674.500"), amount);
	}
	
	@Test
	public void shoulFetchAmountInUSDSettledFor_Sell_On_Thursday() {
		BigDecimal amount = tradeService.fetchAmountInUSDSettled(LocalDate.of(2016, 01, 07), BuySellIndicator.S);
		assertEquals(new BigDecimal("54631.500"), amount);
	}
	
	@Test 
	public void shouldFetchEntitiesBasedOnRanking() {
		List<Trade> outgoingTrades = tradeService.fetchEntitiesBasedOnRanking(BuySellIndicator.B);
		assertEquals("foo3", outgoingTrades.get(0).getEntity());
	}
	
	@Test
	public void getDailySettledAmountTest() {
		Map<String, BigDecimal> settledAmountMap = tradeService.getSettledAmountReportByDate(LocalDate.now());
		assertEquals(new BigDecimal("40100.0000"), settledAmountMap.get("OutgoingAmount"));
		assertEquals(new BigDecimal("43043.000"), settledAmountMap.get("IncomingAmount"));
	}
	
}
