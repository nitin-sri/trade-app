package com.jpmc.trade.util;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import com.jpmc.trade.enums.Currency;

public class TradeUtilTest {

	@Test
	public void getBusinessDateByCurrencyTest() {
		LocalDate businessDate = TradeUtil.getBusinessDateByCurrency(Currency.SAR, LocalDate.of(2018, 8, 10));
		assertEquals(LocalDate.of(2018, 8, 12), businessDate);
	}

}
