package models.coin.exchange;

import models.coin.CryptoExchangeName;
import models.coin.OrderBook;
import models.coin.PairType;
import models.coin.Position;

import java.math.BigDecimal;
import java.util.List;

public interface CryptoExchange {

    public CryptoExchangeName getName();

    public List<PairType> getPairs() throws Exception;

    public BigDecimal getPrice(PairType pairType) throws Exception;

    public List<Position> getOpenPosition(PairType pairType) throws Exception;

    public OrderBook getOrderBook(PairType pairType) throws Exception;


}
