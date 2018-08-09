package com.jpmc.trade.dao;

import java.math.BigDecimal;
import java.util.List;

import com.jpmc.trade.enums.BuySellIndicator;
import com.jpmc.trade.model.Trade;

public interface TradeDao {
    BigDecimal fetchAmountInUSDSettled(BuySellIndicator buySellIndicator);

    List<Trade> fetchEntitiesBasedOnRanking(BuySellIndicator buySellIndicator);

    void createTrade(Trade trade);

}
