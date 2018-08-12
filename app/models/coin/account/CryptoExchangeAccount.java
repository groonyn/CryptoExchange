package models.coin.account;

import models.coin.CryptoExchangeName;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "crypto_exchange_account")
public class CryptoExchangeAccount {

    @Id
    public Long id;

    public String accountName;

    public CryptoExchangeName cryptoExchangeName;

    public ApiKey apiKey = new ApiKey();

}
