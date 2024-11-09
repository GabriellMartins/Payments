package com.br.gabrielmartins.services.database;

import com.br.gabrielmartins.models.Transaction;
import java.util.HashMap;
import java.util.Map;

public class CacheManagerService {

    private Map<String, Transaction> transactionCache = new HashMap<>();

    public void cacheTransaction(String transactionId, Transaction transaction) {
        transactionCache.put(transactionId, transaction);
    }

    public Transaction getTransaction(String transactionId) {
        return transactionCache.get(transactionId);
    }
}
