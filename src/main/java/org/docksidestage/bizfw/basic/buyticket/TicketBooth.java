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
import java.util.Map;

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
    // TODO done shiny HashMap使うときは、Mapインターフェースで受け取る習慣がある by jflute (2024/11/13)
    private final Map<TicketType, Integer> quantities; // stores the quantity for each ticket type
    private Integer salesProceeds; // null allowed: until first purchase

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
        quantities = new HashMap<>();
        for (TicketType type : TicketType.values()) {
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
    // done shiny JavaDoc, 戻り値の説明をお願いします by jflute (2024/11/06)
    // TODO done shiny [時々tips] 引数、戻り値に (NotNull) 的な情報を載せるというやり方もある by jflute (2024/11/13)
    // (Javaの標準APIのクラスでも書いてあるものもある e.g. LocalDate@plusDays(), File@listFiles())
    // TODO done shiny @returnの書き方、クラス名なしで大丈夫です。戻り値って一個しかないので何も情報なくても特定できるから。 by jflute (2024/11/13)
    // 例えば、@paramだったら、どの引数の説明かわからないので、特定するために引数名を入れて説明を入れている。
    // @throwsだと、複数の例外がthrowされる可能性があるので、それぞれの例外ごとに説明を書いている。
    // TODO done shiny 詳細を列挙するのはわかりやすさを追加するのでGood, な一方で、断定すると違う誤解を生むかもしれない。 by jflute (2024/11/13)
    // ので、"など", "とか" って付ける。チケットとかお釣りとか「そういうの」が入ってるが伝われば良い。あえてボカす。
    // TODO done shiny [いいね] 列挙は列挙で良いやり方で、具体例があると直感的でわかりやすいというのがあるので。 by jflute (2024/11/13)
    // あと、高尚な概念的な文章を考えるのって時間が掛かるので、「例えばこういうの」って具体例を挙げるだけの説明でも良い。
    // (自分は、e.g. で列挙するだけで終了のコメントとかもよく書く)
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     * @return The result of the transaction containing the ticket itself, the change, etc. (NotNull)
     */
    public TicketBuyResult buyOneDayPassport(Integer handedMoney) {
        return doBuyPassport(TicketType.ONE_DAY, handedMoney);
    }

    // done shiny JavaDoc, 戻り値の説明をお願いします by jflute (2024/11/06)
    /**
     * Buy two-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     * @return The result of the transaction containing the ticket itself, the change, etc. (NotNull)
     */
    public TicketBuyResult buyTwoDayPassport(Integer handedMoney) {
        return doBuyPassport(TicketType.TWO_DAY, handedMoney);
    }

    /**
     * Buy four-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     * @return The result of the transaction containing the ticket itself, the change, etc. (NotNull)
     */
    public TicketBuyResult buyFourDayPassport(Integer handedMoney) {
        return doBuyPassport(TicketType.FOUR_DAY, handedMoney);
    }

    /**
     * Buy night-only two-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     * @return The result of the transaction containing the ticket itself, the change, etc.  (NotNull)
     */
    public TicketBuyResult buyNightOnlyTwoDayPassport(Integer handedMoney) {
        return doBuyPassport(TicketType.NIGHT_ONLY_TWO_DAY, handedMoney);
    }

    // [1on1でのふぉろー] privateにprivateで切り出して、全体の流れと個々の詳細を分けるというテクニックもある by jflute
    // shinyさんからの質問: privateに切り出したとき、インスタンス変数の値を直接使うか、引数で渡して独立性を高めるか、迷う。
    // jflute回答: これは...ぼくも迷う。ただ気にするのは、そのprivateメソッドの独立性を演出するかしないか？そこ次第。
    // そういう意味では、インスタンス変数の値を直接使うのがデフォルトな感覚で、独立性が必要なときに引数渡しする。
    // (ただ、独立性が必要なときは、そこまでやるんだったら場合によっては別クラスに切り出すとかかも!?)
    // 一方で、staticでもいけるprivateでもstaticにはしない。なぜなら、外していいstaticなのに、読み手にそれが伝わらず...
    // 将来の人が外さずに頑張ろうとしてしまうことの懸念があるから。
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
        } else {
            salesProceeds = price;
        }
        // TODO done shiny [いいね] changeをいったん変数で受けてるのわかりやすい by jflute (2024/11/13)
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
