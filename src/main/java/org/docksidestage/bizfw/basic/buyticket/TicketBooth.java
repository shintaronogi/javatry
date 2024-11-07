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

import java.util.HashMap;

import org.docksidestage.bizfw.basic.buyticket.exceptions.TicketShortMoneyException;
import org.docksidestage.bizfw.basic.buyticket.exceptions.TicketSoldOutException;

/**
 * @author jflute
 * @author shiny
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final HashMap<TicketType, Integer> quantities; // stores the quantity for each ticket type
    private Integer salesProceeds; // null allowed: until first purchase

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
        quantities = new HashMap<>();
        for (TicketType type: TicketType.values()) {
            quantities.put(type, MAX_QUANTITY);
        }
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    // you can rewrite comments for your own language by jflute
    // e.g. Japanese
    // /**
    // * 1Dayパスポートを買う、パークゲスト用のメソッド。
    // * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
    // * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
    // * @throws TicketShortMoneyException 買うのに金額が足りなかったら
    // */
    // [jflute memo] javadocの話をした。なんでもかんでもjavadocにするわけじゃないけどpublicとかはわりとjavadoc。
    // [jflute memo] どうしてもコピペせざるを得ないときのテクニックの話。
    // [jflute memo] コード整形用のテキストファイル、エラー保存用のテキストファイルなどの話。
    // TODO done shiny JavaDoc, 戻り値の説明をお願いします by jflute (2024/11/06)
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     * @return TicketBuyResult The result of the transaction containing the ticket itself and the change
     */
    public TicketBuyResult buyOneDayPassport(Integer handedMoney) {
        return doBuyPassport(TicketType.ONE_DAY, handedMoney);
    }

    // TODO done shiny JavaDoc, 戻り値の説明をお願いします by jflute (2024/11/06)
    /**
     * Buy two-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     * @return TicketBuyResult The result of the transaction containing the ticket itself and the change
     */
    public TicketBuyResult buyTwoDayPassport(Integer handedMoney) {
        return doBuyPassport(TicketType.TWO_DAY, handedMoney);
    }

    /**
     * Buy four-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     * @return TicketBuyResult The result of the transaction containing the ticket itself and the change
     */
    public TicketBuyResult buyFourDayPassport(Integer handedMoney) {
        return doBuyPassport(TicketType.FOUR_DAY, handedMoney);
    }

    /**
     * Buy night-only two-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     * @return TicketBuyResult The result of the transaction containing the ticket itself and the change
     */
    public TicketBuyResult buyNightOnlyTwoDayPassport(Integer handedMoney) {
        return doBuyPassport(TicketType.NIGHT_ONLY_TWO_DAY, handedMoney);
    }

    private TicketBuyResult doBuyPassport(TicketType type, int handedMoney) {
        int quantity = quantities.get(type);
        int price = type.getPrice();

        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        quantities.put(type, --quantity);
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + price;
        }else {
            salesProceeds = price;
        }
        Integer change = handedMoney - price;
        Ticket ticket = new Ticket(type, price);
        TicketBuyResult ticketBuyResult = new TicketBuyResult(ticket, change);
        return ticketBuyResult;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getOneDayPassportQuantity() {
        return quantities.get(TicketType.ONE_DAY);
    }

    public int getTwoDayPassportQuantity() {
        return quantities.get(TicketType.TWO_DAY);
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
