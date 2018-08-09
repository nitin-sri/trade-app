package com.jpmc.trade.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

import com.jpmc.trade.enums.BuySellIndicator;
import com.jpmc.trade.enums.Currency;

public class TradeTest {

	@Test
	public void testUSDAmountOfTrade_For_Buy_SGP(){
		Trade trade = new Trade("foo", BuySellIndicator.B, new BigDecimal(".50"), Currency.SGP,
				LocalDate.of(2016, 01, 01), LocalDate.of(2016, 01, 02), 200, new BigDecimal("100.25"));
		BigDecimal amount = trade.getUSDAmountOfTrade();
		assertEquals(new BigDecimal("10025.0000"), amount);
	}

}
