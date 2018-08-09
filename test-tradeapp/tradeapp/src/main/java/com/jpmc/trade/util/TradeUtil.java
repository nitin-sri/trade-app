package com.jpmc.trade.util;

import java.time.LocalDate;

import com.jpmc.trade.enums.Currency;
import com.jpmc.trade.exception.InvalidSettlementDateException;

public class TradeUtil {
	public static LocalDate getBusinessDateByCurrency(Currency currency, LocalDate settlementDate) {
		if (null == settlementDate) {
			throw new InvalidSettlementDateException("Null settlerment date passed");
		}
		switch (currency) {
		case AED:
		case SAR:
			switch (settlementDate.getDayOfWeek()) {
			case FRIDAY:
				return settlementDate.plusDays(2);
			case SATURDAY:
				return settlementDate.plusDays(1);
			default:
				return settlementDate;
			}
		default:
			switch (settlementDate.getDayOfWeek()) {
			case SATURDAY:
				return settlementDate.plusDays(2);
			case SUNDAY:
				return settlementDate.plusDays(1);
			default:
				return settlementDate;
			}

		}
	}

}
