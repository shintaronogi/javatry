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

import org.docksidestage.unit.PlainTestCase;

// TODO shinny [読み物課題] レビューしやすいコード (Reviewable Code) by jflute (2024/10/02)
// https://jflute.hatenadiary.jp/entry/20160912/reviewable

// TODO jflute 1on1にて、背景コメントの補足をちょこっとずつやっていく (2024/10/02)
// http://dbflute.seasar.org/ja/tutorial/handson/review/codingpolicy.html#backgroundcomment
/**
 * The test of variable. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step01VariableTest extends PlainTestCase {

    // ===================================================================================
    //                                                                      Local Variable
    //                                                                      ==============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_variable_basic() { // example, so begin from the next method
        String sea = "mystic";
        log(sea); // your answer? => mystic
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_initial() {
        String sea = "mystic";
        Integer land = 8;
        String piari = null;
        String dstore = "mai";
        sea = sea + land + piari + ":" + dstore;
        log(sea); // your answer? => mystic8null:mai
        // 理由：
        // あんまりJavaに詳しくはないが、Stringはコンパイル時にStringBuilderのAppendされ、最終的にString.valueOfが呼ばれるので...
        // Intは文字列に問題なく変換され、nullは文字列"null"になるっていう一見ヘンテコなな仕様だった記憶がある。
        // 答え：正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_basic() {
        String sea = "mystic";
        String land = "oneman";
        sea = land;
        land = land + "'s dreams";
        log(sea); // your answer? => oneman
        // 理由：
        // seaにlandが代入されて中身がseaになって、出力されているのでoneman。
        // 仮説：Javaの変数同士のAssignは、多分Assign→変更があった際にポインタ・メモリの位置が変わるのではと思っている。Immutableってことを考えると。（もしくはAssignされた瞬間？）
        // 確かPythonとかCとかは同じままなので一方を変えたらもう一方も変わる？
        // 答え：正解でした。
        // 呟き：なんかJavaのStringは＋しないほうがいいみたいなのを聞いたことがあるんですが、実際concatとかAppendとかでパフォーマンスは違うのでしょうか？
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_int() {
        int sea = 94;
        int land = 415;
        sea = land;
        land++;
        log(sea); // your answer? => 415
        // 理由：上記と基本的な考えは一緒。landはIncrementされて416になっている。
        // 答え：正解でした。
        // 呟き：集中して読まないと引っかかりそうです笑
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_BigDecimal() {
        BigDecimal sea = new BigDecimal(94);
        BigDecimal land = new BigDecimal(415);
        sea = land;
        sea = land.add(new BigDecimal(1));
        sea.add(new BigDecimal(1));
        log(sea); // your answer? => 416（か95行目でエラー出るかな...？）
        // 理由：93行目で一度Assignされてるが、次の行でもう一度され直してるので、Landの415＋１。
        // BigDecimalは見る感じImmutableな気がするので、sea.add(...)と呼び出してもSea自体が変わるのでがなくadd()関数が新しいBigdecimalを返すっていう仕様になっていそう。
        // 答え：正解でした。戻り値を何にもAssignしなくても、コンパイラーエラーは出ないんですね。
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private String instanceBroadway;
    private int instanceDockside;
    private Integer instanceHangar;
    private String instanceMagiclamp;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_String() {
        String sea = instanceBroadway;
        log(sea); // your answer? => null
        // 理由： JavaのStringの初期値はnullだった気がする...
        // NullpointerExceptionは参照しなかったら出ないはず...
        // 答え：正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_int() {
        int sea = instanceDockside;
        log(sea); // your answer? => 0 (かな...?)
        // 理由： JavaのPrimitive型（float, charとか）はみんなDefaultの初期値があってNULLではないという記憶がある。
        // 呟き：ラッパー型はNULLあるイメージ。パフォーマンスの観点だって聞いたことがあるが、具体的に何でかはよくわかってない。（メモリ領域とかかな？）
        // 答え：正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_Integer() {
        Integer sea = instanceHangar;
        log(sea); // your answer? => null
        // 理由：上記に書いた通り、Integerのラッパー型は初期値がnullなはずです。
        // 答え： 正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_via_method() {
        instanceBroadway = "bbb";
        instanceMagiclamp = "magician";
        helpInstanceVariableViaMethod(instanceMagiclamp);
        String sea = instanceBroadway + "|" + instanceDockside + "|" + instanceHangar + "|" + instanceMagiclamp;
        log(sea); // your answer? => bbb|1|null|magician
        // 理由：instanceBroadway:"bbb"はそのまま(後に関数内で変わっている)、
        // instanceDocksideは０からインクレメントされる、
        // instanceHangarはnullのまま、
        // instanceMagiclampは関数のスコープオンリーで変えられてるのでそのまま
        // 答え：正解でした。
    }

    private void helpInstanceVariableViaMethod(String instanceMagiclamp) {
        instanceBroadway = "bigband";
        ++instanceDockside;
        instanceMagiclamp = "burn";
    }

    // ===================================================================================
    //                                                                     Method Argument
    //                                                                     ===============
    // -----------------------------------------------------
    //                                 Immutable Method-call
    //                                 ---------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_immutable_methodcall() {
        String sea = "harbor";
        int land = 415;
        helpMethodArgumentImmutableMethodcall(sea, land);
        log(sea); // your answer? => "harbor"
        // 理由：
        // 上記と一緒でスコープ外で変更があるだけ。あとconcatメソッドは多分戻り値が新しいStringなのでそもそも変わらないのでは。
        // 答え：正解でした。
        // 下で新たな気付きあり！（勘違いしてた）
    }

    private void helpMethodArgumentImmutableMethodcall(String sea, int land) {
        ++land;
        String landStr = String.valueOf(land); // is "416"
        sea.concat(landStr);
    }

    // -----------------------------------------------------
    //                                   Mutable Method-call
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_mutable_methodcall() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentMethodcall(sea, land);
        log(sea); // your answer? => harbor
        // harborの理由は上記と同じでスコープ。
        // 一瞬エラー出るかな？と思ったが、String.valueOf(Object obj)はobj.toString()が返されるはずでStringBuilderのtoString()は中身を返すのでエラーは出ないはず。
        // 答え：なんと！間違えてしまった！
        // 仮説ですが、プリミティブとラッパーで違いそうです。多分ラッパーだと、オブジェクトへの参照を渡すので、変更があったらそのまま適用される。
        // 上の問題で正解が出た理由は、concatメソッドの戻り値の説明が正しいと思われる。
    }

    private void helpMethodArgumentMethodcall(StringBuilder sea, int land) {
        ++land;
        sea.append(land);
    }

    // -----------------------------------------------------
    //                                   Variable Assignment
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_variable_assignment() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentVariable(sea, land);
        log(sea); // your answer? => harbor (悩むところですが...)
        // 上記の問題を解いてみてでた仮説ですが...
        // まず、関数に対して値を渡す時にはコンパイル時にコピーが渡されるのではないかなと思っています。
        // ここでPrimitive型とラッパー型で挙動が違う：イメージとしてはポインタの中の値のコピーが渡されるかそのまま*pのコピーとして渡されるか、だと思っている。
        // なので、関数内で新しい参照を作った場合、*pを新しく作るということなので元のオブジェクトに影響はないのでは...?
        // 答え：正解でした。仮説は正しい模様...?
    }

    private void helpMethodArgumentVariable(StringBuilder sea, int land) {
        ++land;
        String seaStr = sea.toString(); // is "harbor"
        sea = new StringBuilder(seaStr).append(land);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Define variables as followings:
     * <pre>
     * o local variable named sea typed String, initial value is "mystic"
     * o local variable named land typed Integer, initial value is null
     * o instance variable named piari typed int, without initial value
     * o show all variables by log() as comma-separated
     * </pre>
     * (変数を以下のように定義しましょう):
     * <pre>
     * o ローカル変数、名前はsea, 型はString, 初期値は "mystic"
     * o ローカル変数、名前はland, 型はInteger, 初期値は null
     * o インスタンス変数、名前はpiari, 型はint, 初期値なし
     * o すべての変数をlog()でカンマ区切りの文字列で表示
     * </pre>
     */
    private int piari;
    public void test_variable_writing() {
        // define variables here
        String sea = "mystic";
        Integer land = null;
        String result = sea + ", " + land + ", " + piari;
        log(result);
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Make your original exercise as question style about variable. <br>
     * (変数についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     *  What string is introduction variable at the method end?
     * (メソッド終了時の変数 introduction の中身は？)
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    Integer age = 23;
    String name = "Shiny";
    public void test_variable_yourExercise() {
        // write your code here
        String introduction = "Hi, " + help_yourExercise(name) + " is " + age + "year's old.";
        log(introduction);
    }

    public String help_yourExercise(String name) {
        ++age;
        name = name.substring(0, 4);
        StringBuilder realName = new StringBuilder(name);
        realName.append("taro");
        return realName.toString();
    }
}
