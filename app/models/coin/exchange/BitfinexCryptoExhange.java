package models.coin.exchange;

import controllers.coin.RestHelper;
import models.coin.CryptoExchangeName;
import models.coin.OrderBook;
import models.coin.PairType;
import models.coin.Position;
import models.coin.dto.BitfinexPrice;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class BitfinexCryptoExhange implements CryptoExchange {
    @Override
    public CryptoExchangeName getName() {
        return CryptoExchangeName.Bitfinex;
    }

    @Override
    public BigDecimal getPrice(PairType pairType) throws Exception {
        BitfinexPrice bitfinex = new RestHelper().mapDtoFromUrl("https://api.bitfinex.com/v1/pubticker/" + pairType.getCurrency1() + pairType.getCurrency2(), BitfinexPrice.class);
        return new BigDecimal(bitfinex.last_price);
    }

    @Override
    public List<Position> getOpenPosition(PairType pairType) throws Exception {
        return null;
    }

    @Override
    public List<PairType> getPairs() throws Exception {
        return Arrays.asList(PairType.BTC_USD, PairType.ETH_USD);
    }

    @Override
    public OrderBook getOrderBook(PairType pairType) throws Exception {
        OrderBook retVal = new OrderBook();
        return retVal;
    }


}
