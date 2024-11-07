package org.docksidestage.bizfw.basic.buyticket.exceptions;

/**
 * @author shiny
 */
public class TicketSoldOutException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TicketSoldOutException(String msg) {
        super(msg);
    }
}
