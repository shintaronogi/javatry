package org.docksidestage.bizfw.basic.buyticket.exceptions;

/**
 * @author shiny
 */
public class TicketShortMoneyException extends IllegalStateException {

    private static final long serialVersionUID = 2L;

    public TicketShortMoneyException(String msg) {
        super(msg);
    }
}
