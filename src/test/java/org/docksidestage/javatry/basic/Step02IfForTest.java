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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of if-for. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author shiny
 */
public class Step02IfForTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        if Statement
    //                                                                        ============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_if_basic() { // example, so begin from the next method
        int sea = 904;
        if (sea >= 904) {
            sea = 2001;
        }
        log(sea); // your answer? => 2001
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_else_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else {
            sea = 7;
        }
        log(sea); // your answer? => 7
        // 背景：「Condition: 904より大きい」に当てはまらない為
        // 答え：正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (sea >= 904) {
            sea = 7;
        } else if (sea >= 903) {
            sea = 8;
        } else {
            sea = 9;
        }
        log(sea); // your answer? => 7
        // 背景：「二番目のCondition：904 以上」に当てはまる為
        // 答え：正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_nested() {
        boolean land = false;
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (land && sea >= 904) {
            sea = 7;
        } else if (sea >= 903 || land) {
            sea = 8;
            if (!land) {
                land = true;
            } else if (sea <= 903) {
                sea++;
            }
        } else if (sea == 8) {
            sea++;
            land = false;
        } else {
            sea = 9;
        }
        if (sea >= 9 || (sea > 7 && sea < 9)) {
            sea--;
        }
        if (land) {
            sea = 10;
        }
        log(sea); // your answer? => 10
        // 背景：
        // 1. Condition: seaは903以上に当てはまるので中に入る → sea=8になる、landがFalseからTrueになる
        // 2. Condition: sea > 7 && sea < 9 に当てはまるので（８だから）、decrementされる
        // 3. LandがTrueなのでsea = 10になって終わり
        // 答え：正解でした。
        // TODO jflute 1on1にてソースコードリーディングのコツ1の話をする予定 (2024/10/18)
    }

    // ===================================================================================
    //                                                                       for Statement
    //                                                                       =============
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_inti_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (int i = 0; i < stageList.size(); i++) {
            String stage = stageList.get(i);
            if (i == 1) {
                sea = stage;
            }
        }
        log(sea); // your answer? => dockside
        // Index = 1の時飲みseaにstageがAssignされるので、seaはlistのIndex1のdocksideになる
        // 答え：正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            sea = stage;
        }
        log(sea); // your answer? => magiclamp
        // foreach文で単純に回るので最後の要素がAssignされてLoopが終わる
        // 答え：正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_continueBreak() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea); // your answer? => hangar
        // 背景：
        // 1. 最初の要素Broadwayの時はスキップ
        // 2. 次のdockside, hangarにはAssignされる
        // 3. hangarがgaを含んでるのでそこでLoopストップ
        // 答え：正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_listforeach_basic() {
        List<String> stageList = prepareStageList();
        StringBuilder sb = new StringBuilder();
        stageList.forEach(stage -> {
            if (sb.length() > 0) {
                return;
            }
            if (stage.contains("i")) {
                sb.append(stage);
            }
        });
        String sea = sb.toString();
        log(sea); // your answer? => dockside
        // 背景：
        // 1. ループ内でまず最初のConditionはスキップ
        // 2. iが含まれてるdocksideに来た時にAppendされる
        // 3. その時点で最初のConditionがTrueになるのでReturnされ続ける
        // 答え：正解でした。
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Make list containing "a" from list of prepareStageList() and show it as log by loop. (without Stream API) <br>
     * (prepareStageList()のリストから "a" が含まれているものだけのリストを作成して、それをループで回してログに表示しましょう。(Stream APIなしで))
     */
    public void test_iffor_making() {
        // write if-for here
        List<String> stageList = prepareStageList();
        stageList.forEach(stage -> {
            if (stage.contains("a"))
                log(stage);
        });
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Change foreach statement to List's forEach() (keep result after fix) <br>
     * (foreach文をforEach()メソッドへの置き換えてみましょう (修正前と修正後で実行結果が同じになるように))
     */
    public void test_iffor_refactor_foreach_to_forEach() {
        List<String> stageList = prepareStageList();
        String[] sea = { null };
        try {
            stageList.forEach(stage -> {
                if (stage.startsWith("br")) {
                    return;
                }
                sea[0] = stage;
                if (stage.contains("ga")) {
                    throw new RuntimeException();
                }
            });
        } catch (RuntimeException ignored) {
            // TODO shiny [いいね] ぼくもこういう例外のときは、変数名自体に ignored って付けちゃいます(^^ by jflute (2024/10/18)
            // オープンソースのコードでそういうのを見かけて真似るようになったので、わりと世界的な慣習かもですね。
        }
        log(sea[0]); // should be same as before-fix
    }
    // 背景：
    // できるだけ同じような形で実現しようと思った。
    // continueはreturnで同じような振る舞いを実現
    // Lambda expressionの中で使う変数はfinalもしくは実質的FinalじゃないといけないとIDEに怒られたのでArrayに変更 → 参照自体は実質Finalだが、中身は変えれるので
    // forEach()でBreakに当たるものが思い浮かばなかったのでExceptionを投げることに（あくまでエクササイズなので）→ Catchはするがスルー
    //
    // TODO shiny [ふぉろー] おお、見事に実現してますね！ by jflute (2024/10/18)
    // forEach()は文法的には単なるコールバック処理で、中でJavaの標準文法であるfor文を使っていてメソッド経由で代理している感じですね。
    // (イメージとしては)別世界の処理の中でローカル変数の書き換えができちゃうと複雑になっちゃうだろうということでJavaが禁止しています。
    // ゆえに、String[] sea にしないとループ内で副作用を起こせないだけです。
    // まあ通常、こういうケースではforEach()を使うのではなくfor文のままで実装することが多いです。
    // ここでは違いを知ってもらうためにあえて無茶なことをやってもらうエクササイズにしています。
    //
    // そして、continueの代わりがreturnはGoodです。コールバックのLambda式は要はメソッドなので、returnで強制終了させれば次のループに行きます。
    // 一方で、breakはどうにもならないのはその通りです。Java標準文法のfor文が隠されちゃっていますから、処理を止めるとなったら例外しかないわけですね。
    // ただ、javatryやる人で言うと、この時点で例外を知らない人が多いので、別の代替策でどうにかする人も多いです。
    // 挙動は一致しなくても結果が変わらなければ同じと捉えて、"ga" が含まれている時点で次回のループを空ループさせるような工夫をして、
    // stageListは最後まで回りますが、seaの値は全く変わらず同じなので結果が同じと。
    // どっちも正解なので、別の方法も頭でシミュレーションしてみて「なるほど」と思ってもらえればと。

    /**
     * Make your original exercise as question style about if-for statement. <br>
     * (if文for文についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * What string is num variable at the method end? <br>
     * (メソッド終了時の変数 num の中身は？)
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_iffor_yourExercise() {
        // write your code here
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> proceedNumbers = new ArrayList<>();
        Integer num;
        Boolean[] isItTrue = { false };

        try {
            numbers.forEach(number -> {
                if (number % 2 == 1) {
                    proceedNumbers.add(number * number);
                }
                isItTrue[0] = !isItTrue[0];
                if (number * number > 60) {
                    throw new RuntimeException();
                }
            });
        } catch (RuntimeException e) {
            if (!isItTrue[0]) {
                isItTrue[0] = true;
            }
        }

        num = isItTrue[0] ? proceedNumbers.get(proceedNumbers.size() - 1) : null;
        log(num);
        // TODO shiny [いいね] 49! ふぅ、合ってた... by jflute (2024/10/18)
        // とにかくtrue/falseどっちになるのかで最後大きく違うので先にそこを見る。
        // 例外が発生するとしないでtrue/falseが代わるので例外が発生するか？8*8で60超えで必ず例外は発生する。
        // true/falseの反転は交互に行われるようなので、偶数時にfalseに変更され、奇数時にtrueに変更される。
        // 8のときはfalseに変更された直後に例外が発生して、catchでtrueに戻される。
        // なので、最後はtrueでnumbersの最後の要素には、直近の奇数である7の自乗で49と。
        //
        // こういうエクササイズが考えつくということは、プログラムの制御の本質をしっかり理解している証拠ですね(^^。
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private List<String> prepareStageList() {
        List<String> stageList = new ArrayList<>();
        stageList.add("broadway");
        stageList.add("dockside");
        stageList.add("hangar");
        stageList.add("magiclamp");
        return stageList;
    }
}
