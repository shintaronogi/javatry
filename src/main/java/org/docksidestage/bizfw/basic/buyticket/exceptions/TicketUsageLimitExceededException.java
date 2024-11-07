package org.docksidestage.bizfw.basic.buyticket.exceptions;

/**
 * @author shiny
 */
public class TicketUsageLimitExceededException extends IllegalStateException {

    private static final long serialVersionUID = 4L;

    public TicketUsageLimitExceededException(String msg) {
            super(msg);
        }
}
