package org.docksidestage.bizfw.basic.buyticket.usagepolicy;

import org.docksidestage.bizfw.basic.buyticket.Ticket;

/**
 * @author shiny
 */
public interface TicketUsagePolicy {
    void validate(Ticket ticket);
}
