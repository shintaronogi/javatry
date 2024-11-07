package org.docksidestage.bizfw.basic.buyticket.exceptions;

/**
 * @author shiny
 */
public class TicketInvalidTimeException extends IllegalStateException {

    private static final long serialVersionUID = 3L;

    public TicketInvalidTimeException(String msg) {
        super(msg);
    }
}
