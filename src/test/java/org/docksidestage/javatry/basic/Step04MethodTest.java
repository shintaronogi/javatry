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

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of method. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author shiny
 */
public class Step04MethodTest extends PlainTestCase {

    // ===================================================================================
    //                                                                         Method Call
    //                                                                         ===========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_method_call_basic() {
        String sea = supplySomething();
        log(sea); // your answer? => over
        // 背景
        // in supply: {}がログされるのはあるものの、Returnされるのはoverなので！
        //
        // 答え：正解でした。

    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_call_many() {
        String sea = functionSomething("mystic");
        consumeSomething(supplySomething());
        runnableSomething();
        log(sea); // your answer? => mysmys
        // functionSomething()は引数のnameの指定されたTarget文字列をReplaceする → ticがmysになるのでmysmys → returnされsea = mysmysになる
        // supplysomething()は上の問題同様overを返す → consumeSomethingはスコープ内のseaに変更を加えるので副作用なし
        // runnableSomething()も同様
        // なので結果はmysmysになる
        //
        // 答え：正解でした。
    }

    // [ふぉろー] function, supply, consumeのFunctionInterfaceとの名前の関わりの話をした by jflute (2024/10/30)
    // runnableの慣習的な話もした
    private String functionSomething(String name) {
        String replaced = name.replace("tic", "mys");
        log("in function: {}", replaced);
        return replaced;
    }

    private String supplySomething() {
        String sea = "over";
        log("in supply: {}", sea);
        return sea;
    }

    private void consumeSomething(String sea) {
        log("in consume: {}", sea.replace("over", "mystic"));
    }

    private void runnableSomething() {
        String sea = "outofshadow";
        log("in runnable: {}", sea);
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_object() {
        St4MutableStage mutable = new St4MutableStage();
        int sea = 904;
        boolean land = false;
        helloMutable(sea - 4, land, mutable);
        if (!land) {
            sea = sea + mutable.getStageName().length();
        }
        log(sea); // your answer? => 910
        // まずlandがFalseかTrueかで結果が変わるのでそこをみる → 初期値はFalse、そしてhelloMutable()はLandに対して副作用なしなのでFalseのまま
        // helloMutable()は引数のSeaをIncrementしてReturnするだけなので実際のSeaには影響なし
        // helloMutable()に渡されるオブジェクトはStep01で学んだ通り参照が渡されるので、setStageNameでstageNameがmysticになる
        // "mystic"は長さ６なので904と足して910になる
        //
        // 答え：正解でした。
    }

    private int helloMutable(int sea, Boolean land, St4MutableStage piari) {
        sea++;
        land = true;
        piari.setStageName("mystic");
        return sea;
    }

    private static class St4MutableStage {

        private String stageName;

        public String getStageName() {
            return stageName;
        }

        public void setStageName(String stageName) {
            this.stageName = stageName;
        }
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private int inParkCount;
    private boolean hasAnnualPassport;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_instanceVariable() {
        hasAnnualPassport = true;
        int sea = inParkCount;
        offAnnualPassport(hasAnnualPassport);
        for (int i = 0; i < 100; i++) {
            goToPark();
        }
        ++sea;
        sea = inParkCount;
        log(sea); // your answer? => 100
        // 背景：漠然読みから、seaにはinParkCountの値が代入されてるのが分かる
        // inParkCountは上でInitializeされてる（intなので初期値0）
        // goToPark()でhasAnnualPassportがTrueの時Incrementされる、かつ100回のForループの中なので+100されると仮定
        // hasAnnualPassportを見てみると、最初にTrueにされている（下のPrivate関数ではスコープ内の変更）
        // なのでループに入り、SeaにAssignされるので100
        //
        // 答え：正解でした。
    }

    private void offAnnualPassport(boolean hasAnnualPassport) {
        hasAnnualPassport = false;
    }

    private void goToPark() {
        if (hasAnnualPassport) {
            ++inParkCount;
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    // write instance variables here
    private Boolean availableLogging = true;

    /**private
     * Make private methods as followings, and comment out caller program in test method:
     * <pre>
     * o replaceAwithB(): has one argument as String, returns argument replaced "A" with "B" as String 
     * o replaceCwithB(): has one argument as String, returns argument replaced "C" with "B" as String 
     * o quote(): has two arguments as String, returns first argument quoted by second argument (quotation) 
     * o isAvailableLogging(): no argument, returns private instance variable "availableLogging" initialized as true (also make it separately)  
     * o showSea(): has one argument as String argument, no return, show argument by log()
     * </pre>
     * (privateメソッドを以下のように定義して、テストメソッド内の呼び出しプログラムをコメントアウトしましょう):
     * <pre>
     * o replaceAwithB(): 一つのString引数、引数の "A" を "B" に置き換えたStringを戻す 
     * o replaceCwithB(): 一つのString引数、引数の "C" を "B" に置き換えたStringを戻す 
     * o quote(): 二つのString引数、第一引数を第二引数(引用符)で囲ったものを戻す 
     * o isAvailableLogging(): 引数なし、privateのインスタンス変数 "availableLogging" (初期値:true) を戻す (それも別途作る)  
     * o showSea(): 一つのString引数、戻り値なし、引数をlog()で表示する
     * </pre>
     */
    public void test_method_making() {
        // use after making these methods
        String replaced = replaceCwithB(replaceAwithB("ABC"));
        String sea = quote(replaced, "'");
        if (isAvailableLogging()) {
            showSea(sea);
        }
    }

    // TODO shiny [いいね] メソッドの定義順がとても直感的でわかりやすい by jflute (2024/10/30)
    // publicの処理の流れとprivateの個々の部品の関係性が、メソッドの定義の場所で表現されている。
    // write methods here
    private String replaceAwithB(String input) {
        return input.replace("A", "B");
    }

    private String replaceCwithB(String input) {
        // TODO shiny A->B じゃなくて C->B でございます by jflute (2024/10/30)
        return input.replace("A", "B");
    }

    // TODO shiny [いいね] 引数名の表現が素晴らしい by jflute (2024/10/30)
    private String quote(String innerString, String outerString) {
        return String.format("%s%s%s", outerString, innerString, outerString);
    }

    // TODO shiny こういうケースなら、booleanの方がよく使われます by jflute (2024/10/30)
    private Boolean isAvailableLogging() {
        return availableLogging;
    }

    private void showSea(String input) {
        log(input);
    }
}
