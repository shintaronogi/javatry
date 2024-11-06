/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author jflute
 * @author shiny
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final TicketType type; // can be one-, two- or four-day
    private int availableDays; // depends on the type of ticket (e.g. two days) and decreases with doInPark
    private final int displayPrice; // written on ticket, park guest can watch this

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Ticket(TicketType type, int displayPrice) {
        this.type = type;
        this.availableDays = calculateAvailableDays(type);
        this.displayPrice = displayPrice;
    }

    private int calculateAvailableDays(TicketType type) {
        switch (type) {
        case ONE_DAY:
            return 1;
        case TWO_DAY:
            return 2;
        case FOUR_DAY:
            return 4;
        default:
            throw new IllegalArgumentException("Unknown TicketType: " + type);
        }
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark() {
        if (!isAvailable()) {
            throw new IllegalStateException("Ticket is unavailable. Already in park by this ticket, exceeding the limit.: displayedPrice=" + displayPrice);
        }
        availableDays--;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public TicketType getType() {
        return type;
    }

    public int getDisplayPrice() {
        return displayPrice;
    }

    public boolean isAvailable() {
        return availableDays > 0;
    }
}
