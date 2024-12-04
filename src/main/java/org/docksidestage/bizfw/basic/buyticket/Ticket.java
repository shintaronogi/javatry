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

import org.docksidestage.bizfw.basic.buyticket.exceptions.TicketInvalidTimeException;
import org.docksidestage.bizfw.basic.buyticket.exceptions.TicketUsageLimitExceededException;
import org.docksidestage.bizfw.basic.buyticket.usagepolicy.TicketUsagePolicy;

/**
 * @author jflute
 * @author shiny
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done shiny インスタンス変数の並び順、データの出どころの種類で分けるか？データの使われ方で分けるか？ by jflute (2024/11/06)
    // 正解はないですが1on1でフォローした通り色々なパターンあります。納得した形でやってもらえればなのでお任せします。
    private final TicketType type; // can be one-, two- or four-day
    private final int displayPrice; // written on ticket, park guest can watch this
    private final TicketUsagePolicy usagePolicy; // behavior of the Ticket which contains a validate mechanism

    private int remainingAvailableDays; // depends on the type of ticket (e.g. two days) and decreases with doInPark

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Ticket(TicketType type, int displayPrice) {
        this.type = type;
        this.remainingAvailableDays = type.getAvailableDays();
        this.displayPrice = displayPrice;
        this.usagePolicy = type.getUsagePolicy();
    }

    // done shiny 修行++: チケット種別が増えたとき、あっちらこっちら修正したくないので...あまりswitch caseの箇所を減らしたい by jflute (2024/11/06)
    // EnumにavailableDaysとpriceを持たせて、Getできるようにしました。こうすることでチケット種別が増える時は、EnumのTypeに加えて、この二つの値を追加するだけですみます。
    // また、変数の挙動的にはstatic finalと同じようなものを表現できているはずです。
    // そして、Ticketクラスではこちらの変数との意味の差別化＆Decrementされるのを強調するためRemainingを名前に追加しました。

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    /**
     * Method called when the guest enters the park with the ticket. Decrements the remainingAvailableDays when the ticket is valid.
     * @throws TicketUsageLimitExceededException When ticket has been used already and exceeded its usage limit.
     * @throws TicketInvalidTimeException Currently only applies for night-only ticket. When the guest tries to enter the park before at forbidden time.
     */
    public void doInPark() {
        // done shiny [いいね] しっかり流れのメソッドになっててわかりやすい！ by jflute (2024/11/13)
        usagePolicy.validate(this);
        remainingAvailableDays--;
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    // TODO done shiny toString()の参考実装です。(フレームワークとか何も使わないときの例) by jflute (2024/11/21)
    @Override
    public String toString() {
        // typeから導出できるものは省略してもいいかなと思って除外している
        return getClass().getName() + ":{" + type + ", " + remainingAvailableDays + "}";
    }
    //
    // まあ実際の現場では、実装を手軽にするために専用のクラスを使っちゃったりします。
    // 以下は、LastaFluteというぼくが作ってるフレームワークの例ですが、他のフレームワークでも似たようなものがあります。
    // // 気軽なtoString() | LastaFlute
    // https://dbflute.seasar.org/ja/lastaflute/howto/impldesign/beandesign.html#latostring

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public TicketType getType() {
        return type;
    }

    public int getDisplayPrice() {
        return displayPrice;
    }

    public boolean hasRemainingDays() {
        return remainingAvailableDays > 0;
    }
}
