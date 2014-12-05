package com.epam.edu.jmp.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.edu.jmp.exception.CurrenciesExchangeIsNotSupportedExceprion;
import com.epam.edu.jmp.exception.NotEnoughMoneyToPerformMoneyTransferException;
import com.epam.edu.jmp.model.Account;
import com.epam.edu.jmp.model.Bank;
import com.epam.edu.jmp.model.ExchangeRate;
import com.epam.edu.jmp.util.BankUtils;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class MoneyExchangeService {

    @Inject
    private EntityManager em;

    private final static Logger LOG = LoggerFactory
            .getLogger(MoneyExchangeService.class);

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void exchangeMoney(Bank bank, Account from, Account to, double amount)
            throws NotEnoughMoneyToPerformMoneyTransferException,
            CurrenciesExchangeIsNotSupportedExceprion {
        ExchangeRate rate = BankUtils.getCurrencyExchangeRate(bank,
                from.getCurrency(), to.getCurrency());
        boolean oneBankAccounts = BankUtils.checkBankAccounts(bank, from, to);
        if (oneBankAccounts && BankUtils.checkEnoughMoney(from, amount)) {
            double convertedValue = doMoneyExchange(from, to, amount, rate);
            LOG.info("Exchanged " + amount + from.getCurrency().getShortCode()
                    + " from " + from + " to " + to + ". Loaded value "
                    + convertedValue + to.getCurrency().getShortCode()
                    + ". Exchange rate " + rate.getRate());
        }

    }

    private double doMoneyExchange(Account from, Account to, double amount,
            ExchangeRate rate) {
        from.setMoneyValue(from.getMoneyValue() - amount);
        em.merge(from);
        double convertedValue = Math.round(amount * rate.getRate() * 100.0) / 100.0d;
        to.setMoneyValue(to.getMoneyValue() + convertedValue);
        em.merge(to);
        return convertedValue;
    }

}
