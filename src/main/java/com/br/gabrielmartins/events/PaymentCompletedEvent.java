package com.br.gabrielmartins.events;

import com.br.gabrielmartins.models.Transaction;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PaymentCompletedEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Transaction transaction;

    public PaymentCompletedEvent(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
