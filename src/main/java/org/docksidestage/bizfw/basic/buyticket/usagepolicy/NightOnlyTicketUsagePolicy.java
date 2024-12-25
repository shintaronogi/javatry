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
        // done shiny [読み物課題] 例外メッセージ、敬語で満足でもロスロスパターン by jflute (2024/11/21)
        // https://jflute.hatenadiary.jp/entry/20170804/explossloss
        // done shiny [ふぉろー] 一方で、ticketを単純に + するだけではTicketの情報は出てこないです。 by jflute (2024/11/21)
        //  e.g. "...exhausted: " + ticket;
        //
        // この場合、toString()メソッドが内部的には呼び出されます。
        // toString()は、すべてのクラスのスーパークラスであるObjectクラスが定義しています。
        // デフォルトの実装ではクラス名くらいしか出てこないので、Ticket固有の情報は出てきません。
        // 
        // かといって、ticket.getType() + ", " + ticket.getXxx() + ", " は面倒なので...
        // Ticketクラス側でtoString()をオーバーライドすると良いです。
        // Ticketクラスの方で参考実装を載せておきますね。
        //
        // done shiny ということで、ロスロスパターンを避けましょう by jflute (2024/12/04)
        if (!ticket.hasRemainingDays()) {
            throw new TicketUsageLimitExceededException("Ticket is unavailable. Remaining days are exhausted. Ticket: " + ticket);
        }

        if (!isNightTime()) {
            String errorMessage =
                    String.format("This ticket is only valid for use at night (between 18:00 - 23:00). Ticket: %s, Current time: %s",
                            ticket, LocalTime.now());
            throw new TicketInvalidTimeException(errorMessage);
        }
    }

    // [1on1でのふぉろー] staticの使いどころの話をした。
    // Javaは機能がてんこ盛りなので、それぞれの機能をどの程度使うか？ってさじ加減が発生する。
    // done shiny 必要になるまではprivateにしておいていいかなと by jflute (2024/11/21)
    // もしくは、staticを除去してサブクラス固有のメソッドとして公開しておくか...
    // 本気なら別クラスに切り出し。
    private boolean isNightTime() {
        LocalTime now = LocalTime.now();
        return now.isAfter(LocalTime.of(17, 59)) && now.isBefore(LocalTime.of(23, 1));
    }
}
