package org.docksidestage.bizfw.basic.buyticket.usagepolicy;

import java.time.LocalTime;

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.exceptions.TicketInvalidTimeException;
import org.docksidestage.bizfw.basic.buyticket.exceptions.TicketUsageLimitExceededException;

/**
 * @author shiny
 */
public class NightOnlyTicketUsagePolicy implements TicketUsagePolicy {
    @Override
    public void validate(Ticket ticket) {
        if (!ticket.hasRemainingDays()) {
            throw new TicketUsageLimitExceededException("Ticket is unavailable. Remaining days are exhausted.");
        }

        if (!isNightTime()) {
            throw new TicketInvalidTimeException("This ticket is only valid for use at night.");
        }
    }

    public static boolean isNightTime() {
        LocalTime now = LocalTime.now();
        return now.isAfter(LocalTime.of(18, 0)) && now.isBefore(LocalTime.of(23, 59));
    }
}
