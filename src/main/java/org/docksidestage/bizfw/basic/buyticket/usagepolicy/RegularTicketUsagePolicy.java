package org.docksidestage.bizfw.basic.buyticket.usagepolicy;

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.exceptions.TicketUsageLimitExceededException;

/**
 * @author shiny
 */
public class RegularTicketUsagePolicy implements TicketUsagePolicy {
    @Override
    public void validate(Ticket ticket) {
        if (!ticket.hasRemainingDays()) {
            throw new TicketUsageLimitExceededException("Ticket is unavailable. Remaining days are exhausted.");
        }
    }
}
