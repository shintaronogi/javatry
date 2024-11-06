package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author shiny
 */
public class TicketBuyResult {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final Ticket ticket; // The ticket bought by the customer
    private final Integer change; // The change from the ticket-buying transaction

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBuyResult(Ticket ticket, Integer change) {
        this.ticket = ticket;
        this.change = change;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public Ticket getTicket() {
        return ticket;
    }

    public Integer getChange() {
        return change;
    }
}
