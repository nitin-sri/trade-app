package com.jpmc.trade.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.jpmc.trade.dao.TradeDao;
import com.jpmc.trade.enums.BuySellIndicator;
import com.jpmc.trade.enums.Currency;
import com.jpmc.trade.model.Trade;

@RunWith(MockitoJUnitRunner.class)
public class TradeServiceImplTest {

	@Mock
	TradeDao tradeDao;
	TradeService tradeService;

	@Before
	public void setUp() throws Exception {
		tradeService = new TradeServiceImpl(tradeDao);
	}

	@Test
	public void shoulFetchAmountInUSDSettledForBuy() {
		Mockito.when(tradeDao.fetchAmountInUSDSettled(BuySellIndicator.B)).thenReturn(BigDecimal.TEN);
		BigDecimal amount = tradeService.fetchAmountInUSDSettled(BuySellIndicator.B);
		assertEquals(BigDecimal.TEN, amount);
	}

	@Test
	public void shoulFetchAmountInUSDSettledForSell() {
		Mockito.when(tradeDao.fetchAmountInUSDSettled(BuySellIndicator.S)).thenReturn(BigDecimal.TEN);
		BigDecimal amount = tradeService.fetchAmountInUSDSettled(BuySellIndicator.S);
		assertEquals(BigDecimal.TEN, amount);
	}

	@Test
	public void shoulFetchAmountInUSDSettledFor_Sell_On_Thursday() {
		Mockito.when(tradeDao.fetchAmountInUSDSettled(LocalDate.of(2016, 01, 07), BuySellIndicator.S))
				.thenReturn(BigDecimal.TEN);
		BigDecimal amount = tradeService.fetchAmountInUSDSettled(LocalDate.of(2016, 01, 07), BuySellIndicator.S);
		assertEquals(BigDecimal.TEN, amount);
	}

	@Test
	public void shouldFetchEntitiesBasedOnRanking() {
		List<Trade> dummyList = new ArrayList<>();
		Trade trade = new Trade("foo2", BuySellIndicator.B, new BigDecimal(".50"), Currency.SGP,
				LocalDate.of(2016, 01, 01), LocalDate.of(2016, 01, 02), 300, new BigDecimal("100.25"));
		dummyList.add(trade);
		trade = new Trade("foo", BuySellIndicator.B, new BigDecimal(".50"), Currency.SGP, LocalDate.of(2016, 01, 01),
				LocalDate.of(2016, 01, 02), 200, new BigDecimal("100.25"));
		dummyList.add(trade);

		Mockito.when(tradeDao.fetchEntitiesBasedOnRanking(BuySellIndicator.B)).thenReturn(dummyList);
		List<Trade> outgoingTrades = tradeService.fetchEntitiesBasedOnRanking(BuySellIndicator.B);
		assertEquals("foo2", outgoingTrades.get(0).getEntity());
	}

	@Test
	public void getDailySettledAmountTest() {
		Mockito.when(tradeDao.fetchAmountInUSDSettled(LocalDate.now(), BuySellIndicator.S))
				.thenReturn(new BigDecimal("43043.000"));
		Mockito.when(tradeDao.fetchAmountInUSDSettled(LocalDate.now(), BuySellIndicator.B))
				.thenReturn(new BigDecimal("40100.0000"));
		Map<String, BigDecimal> settledAmountMap = tradeService.getSettledAmountReportByDate(LocalDate.now());
		assertEquals(new BigDecimal("40100.0000"), settledAmountMap.get("OutgoingAmount"));
		assertEquals(new BigDecimal("43043.000"), settledAmountMap.get("IncomingAmount"));
	}

}
