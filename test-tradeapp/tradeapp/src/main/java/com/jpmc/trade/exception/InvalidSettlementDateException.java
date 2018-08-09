package com.jpmc.trade.exception;

public class InvalidSettlementDateException extends RuntimeException {
	private static final long serialVersionUID = 8518572290878441008L;

	public InvalidSettlementDateException(String message) {
		super(message);
	}
}
