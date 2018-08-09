package com.jpmc.trade.util;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import com.jpmc.trade.enums.Currency;
import com.jpmc.trade.exception.InvalidSettlementDateException;

public class TradeUtilTest {

	@Test
	public void getBusinessDateByCurrencyTest_For_SAR_Friday() {
		LocalDate businessDate = TradeUtil.getBusinessDateByCurrency(Currency.SAR, LocalDate.of(2018, 8, 10));
		assertEquals(LocalDate.of(2018, 8, 12), businessDate);
	}

	@Test
	public void getBusinessDateByCurrencyTest_For_AED_Saturday() {
		LocalDate businessDate = TradeUtil.getBusinessDateByCurrency(Currency.AED, LocalDate.of(2018, 8, 11));
		assertEquals(LocalDate.of(2018, 8, 12), businessDate);
	}
	
	@Test
	public void getBusinessDateByCurrencyTest_For_SGP_Sunday() {
		LocalDate businessDate = TradeUtil.getBusinessDateByCurrency(Currency.SGP, LocalDate.of(2018, 8, 12));
		assertEquals(LocalDate.of(2018, 8, 13), businessDate);
	}
	
	@Test
	public void getBusinessDateByCurrencyTest_For_SGP_Monday() {
		LocalDate businessDate = TradeUtil.getBusinessDateByCurrency(Currency.SGP, LocalDate.of(2018, 8, 13));
		assertEquals(LocalDate.of(2018, 8, 13), businessDate);
	}
	
	@Test(expected = InvalidSettlementDateException.class)
	public void getBusinessDateByCurrencyShouldThrowException_For_SettlementDate_Null() {
		TradeUtil.getBusinessDateByCurrency(Currency.USD, null);
	}
	
}
