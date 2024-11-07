package org.docksidestage.bizfw.basic.buyticket;

import org.docksidestage.bizfw.basic.buyticket.usagepolicy.NightOnlyTicketUsagePolicy;
import org.docksidestage.bizfw.basic.buyticket.usagepolicy.RegularTicketUsagePolicy;
import org.docksidestage.bizfw.basic.buyticket.usagepolicy.TicketUsagePolicy;

/**
 * @author shiny
 */
public enum TicketType {
    ONE_DAY(1, 7400, new RegularTicketUsagePolicy()),
    TWO_DAY(2, 13200, new RegularTicketUsagePolicy()),
    FOUR_DAY(4, 22400, new RegularTicketUsagePolicy()),
    NIGHT_ONLY_TWO_DAY(2, 7400, new NightOnlyTicketUsagePolicy());

    private final int availableDays; // each ticket has specific available days (e.g. twoDay has 2)
    private final int price; // depends on the type of ticket
    private final TicketUsagePolicy usagePolicy; // defines a behavior how tickets are validated

    TicketType(int availableDays, int price, TicketUsagePolicy usagePolicy) {
        this.availableDays = availableDays;
        this.price = price;
        this.usagePolicy = usagePolicy;
    }

    public int getAvailableDays() {
        return availableDays;
    }

    public int getPrice() {
        return price;
    }

    public TicketUsagePolicy getUsagePolicy() {
        return usagePolicy;
    }
}
