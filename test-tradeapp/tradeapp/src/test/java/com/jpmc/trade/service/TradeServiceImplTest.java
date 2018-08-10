package com.jpmc.trade.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
		when(tradeDao.fetchAmountInUSDSettled(BuySellIndicator.B)).thenReturn(BigDecimal.TEN);
		BigDecimal amount = tradeService.fetchAmountInUSDSettled(BuySellIndicator.B);

		assertEquals(BigDecimal.TEN, amount);
		verify(tradeDao).fetchAmountInUSDSettled(BuySellIndicator.B);
	}

	@Test
	public void shoulFetchAmountInUSDSettledForSell() {
		when(tradeDao.fetchAmountInUSDSettled(BuySellIndicator.S)).thenReturn(BigDecimal.TEN);
		BigDecimal amount = tradeService.fetchAmountInUSDSettled(BuySellIndicator.S);

		assertEquals(BigDecimal.TEN, amount);
		verify(tradeDao).fetchAmountInUSDSettled(BuySellIndicator.S);
	}

	@Test
	public void shoulFetchAmountInUSDSettledFor_Sell_On_Thursday() {
		when(tradeDao.fetchAmountInUSDSettled(LocalDate.of(2016, 01, 07), BuySellIndicator.S))
				.thenReturn(BigDecimal.TEN);
		BigDecimal amount = tradeService.fetchAmountInUSDSettled(LocalDate.of(2016, 01, 07), BuySellIndicator.S);

		assertEquals(BigDecimal.TEN, amount);
		verify(tradeDao).fetchAmountInUSDSettled(LocalDate.of(2016, 01, 07), BuySellIndicator.S);
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

		when(tradeDao.fetchEntitiesBasedOnRanking(BuySellIndicator.B)).thenReturn(dummyList);
		List<Trade> outgoingTrades = tradeService.fetchEntitiesBasedOnRanking(BuySellIndicator.B);

		assertEquals("foo2", outgoingTrades.get(0).getEntity());
		verify(tradeDao).fetchEntitiesBasedOnRanking(BuySellIndicator.B);
	}

	@Test
	public void getDailySettledAmountTest() {
		when(tradeDao.fetchAmountInUSDSettled(LocalDate.now(), BuySellIndicator.S))
				.thenReturn(new BigDecimal("43043.000"));
		when(tradeDao.fetchAmountInUSDSettled(LocalDate.now(), BuySellIndicator.B))
				.thenReturn(new BigDecimal("40100.0000"));
		Map<String, BigDecimal> settledAmountMap = tradeService.getSettledAmountReportByDate(LocalDate.now());

		assertEquals(new BigDecimal("40100.0000"), settledAmountMap.get("OutgoingAmount"));
		assertEquals(new BigDecimal("43043.000"), settledAmountMap.get("IncomingAmount"));
		verify(tradeDao).fetchAmountInUSDSettled(LocalDate.now(), BuySellIndicator.S);
		verify(tradeDao).fetchAmountInUSDSettled(LocalDate.now(), BuySellIndicator.B);
	}

}
