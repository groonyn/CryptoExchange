package controllers.coin;

import models.coin.*;
import models.coin.exchange.CexIoCryptoExchange;
import models.coin.exchange.CryptoExchange;
import models.coin.exchange.MockCryptoExchange;
import play.mvc.Controller;
import play.mvc.Result;
import service.UserProvider;
import views.html.coin.coinInfo;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TradeController extends Controller {

    @Inject
    private UserProvider userProvider;

    private TradeBean tradeBean;

    private List<CryptoExchange> cryptoExchangeList = Arrays.asList(
            new MockCryptoExchange(CryptoExchangeName.Bitfinex), new CexIoCryptoExchange());

    public Result info() throws Exception {
        return trade(cryptoExchangeList.get(0).getName().toString(), PairType.BTC_USD.toString());
    }

    public Result trade(String cryptoExhangeNameAsString, String pairAsString) throws Exception {
        PairType pair;
        try {
            pair = PairType.valueOf(pairAsString);
        } catch (IllegalStateException e) {
            return ok("pair not found");
        }

        //CryptoExchange cryptoExchange = new CexIoCryptoExchange();
        CryptoExchangeName cryptoExchangeName;
        try {
            cryptoExchangeName = CryptoExchangeName.valueOf(cryptoExhangeNameAsString);
        } catch (IllegalStateException e) {
            return ok("exhange not found");
        }

        CryptoExchange cryptoExchange = null;
        for (CryptoExchange ce: cryptoExchangeList) {
            if (ce.getName().equals(cryptoExchangeName)) {
                cryptoExchange = ce;
            }
        }
        if (cryptoExchange == null) {
            return ok("exhange not found");
        }

        tradeBean = new TradeBean();

        tradeBean.setSelectedCryptoExchangeName(cryptoExchangeName);
        tradeBean.setPairType(pair);
        List<CryptoExchangeName> cryptoExchangeNames = new ArrayList<>();
        for (CryptoExchange ce: cryptoExchangeList) {
            cryptoExchangeNames.add(ce.getName());
        }
        tradeBean.setCryptoExchangeNameList(cryptoExchangeNames);

        for (PairType p: cryptoExchange.getPairs()) {
            tradeBean.getPairList().add(p);
        }

        BigDecimal price = cryptoExchange.getPrice(pair);
        tradeBean.setPrice(price);

        OrderBook orderBook = cryptoExchange.getOrderBook(pair);
        tradeBean.setOrderBook(orderBook);

        List<Position> postionList = cryptoExchange.getOpenPosition(pair);
        tradeBean.setPositions(postionList);



        return ok(coinInfo.render(userProvider, tradeBean));
    }

}
