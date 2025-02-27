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
package org.docksidestage.javatry.basic;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of data type. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author shiny
 */
public class Step03DataTypeTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          Basic Type
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_datatype_basicType() {
        String sea = "mystic";
        Integer land = 416;
        LocalDate piari = LocalDate.of(2001, 9, 4);
        LocalDateTime bonvo = LocalDateTime.of(2001, 9, 4, 12, 34, 56);
        Boolean dstore = true;
        BigDecimal amba = new BigDecimal("9.4");

        piari = piari.plusDays(1);
        land = piari.getYear();
        bonvo = bonvo.plusMonths(1);
        land = bonvo.getMonthValue();
        land--;
        if (dstore) {
            BigDecimal addedDecimal = amba.add(new BigDecimal(land));
            sea = String.valueOf(addedDecimal);
        }
        log(sea); // your answer? => 18.4
        // 背景：
        // dstoreの値によってSeaの中身が変わるか変わらないかが決まるので、まずそこをみる → TrueなのでIfの中には入る
        // landはbonvoの月プラス１を取得した後、-1されるので9
        // ambda 9.4にland 9がプラスされて18.4 → それがSeaになる
        //
        // 答え：正解でした。(IntelliJはナビゲーションで内部実装見れるか便利だ...!)
    }

    // ===================================================================================
    //                                                                           Primitive
    //                                                                           =========
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_primitive() {
        byte sea = 127; // max
        short land = 32767; // max
        int piari = 1;
        long bonvo = 9223372036854775807L; // max
        float dstore = 1.1f;
        double amba = 2.3d;
        char miraco = 'a';
        boolean dohotel = miraco == 'a';
        if (dohotel && dstore >= piari) {
            bonvo = sea;
            land = (short) bonvo;
            bonvo = piari;
            sea = (byte) land;
            log(sea);
            if (amba == 2.3D) {
                // amba += 0.5; Roundされるか切り捨てされるかを一瞬試した痕跡。
                sea = (byte) amba;
                log(sea);
            }
        }
        if ((int) dstore > piari) {
            sea = 0;
        }
        log(sea); // your answer? => 2 (かな...)
        // intellijが賢くて既にIf Statementがalways trueだよーとか教えてくれるw というのは置いておいて...
        // まず、dstore > piariはFalseなのは、変数二つともReassignされてない＆dstoreをIntにすると切り捨てられるので、1 > 1は常にFalse
        // 逆にdstore >= piariは 1>=1 なのでTrue＆miraco == 'a'もTrueなのでIfの中に入る
        // Seaの結果はambda == 2.3Dに依存するので、そこをみる → Ambdaを書き換えられてないのでTrueになる （dはJavaでDouble型(double precision number)に明示的につけれるもの、大文字でもいけたはず...）
        // ambdaがByte型になる → Byteは整数8ビットなので切り捨てられる → 結果Seaは2になる
        //
        // 答え：正解でした。(切り捨てられるのかっていうのがちょっと自信なかったけど、あってそう。ちょっと試してみたけどRoundではない)
        // 呟き：Javaでは、整数だとbyte, short, int, longで偶に名前がごっちゃになって「あれ、これ何バイトだっけ？」ってなるのでGoみたいにint8, int16, ...ってしてくれてるのは好きです。
        // done jflute プログラム書くときは、Overflowにならないように気をつけるので気にしたことはなかったですが、Overflowの計算のロジックが知りたいです！
        // done shiny [ふぉろー] 一緒に検証してみた by jflute (2024/10/30)
        // 小数点は切り捨て、縮小変換はなんと-1になる (もし-のデカい値だったら逆に+1になる)
        //
        // 実際の現場では、int, long, booleanくらいしかプリミティブ型は使われない。
        // 標準のintが普段は利用されていて、21億xxxを超えるような数値だけlongが使われる。
        //
        // booleanはそのままtrue/falseだけを表現するものとして使われる。
        // (逆にBooleanはほとんど使われない: true/false/nullってほぼあり得ない)
        // (だた0ではない、例えばリクエストパラメーター(JSON)とかで項目未指定を表現するためにBooleanとかある)
        // (そういう意味ではローカル変数でBooleanはほぼほぼないと言える)
        //
        // float, doubleは小数点で使うと思いきや、コンピューター的な誤差の問題があるので業務ではBigDecimal使う。
        //
        // charは、フレームワークとかライブラリとかではパフォーマンスのために使うけど、業務ではStringに代替される。
    }

    // ===================================================================================
    //                                                                              Object
    //                                                                              ======
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_object() {
        St3ImmutableStage stage = new St3ImmutableStage("hangar");
        String sea = stage.getStageName();
        log(sea); // your answer? => hangar
        // 答え：正解でした。
    }

    private static class St3ImmutableStage {

        private final String stageName;

        public St3ImmutableStage(String stageName) {
            this.stageName = stageName;
        }

        public String getStageName() {
            return stageName;
        }
    }
}
