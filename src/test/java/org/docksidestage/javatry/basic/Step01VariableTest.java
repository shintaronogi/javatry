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

// done shiny [お知らせ] レビューのやり取りこちらのような感じでお願いします by jflute (2024/10/09)
// // レビューのやり取り | ハンズオンのjfluteレビュー
// https://dbflute.seasar.org/ja/tutorial/handson/review/jflutereview.html#review

// done shiny [読み物課題] レビューしやすいコード (Reviewable Code) by jflute (2024/10/02)
// https://jflute.hatenadiary.jp/entry/20160912/reviewable

// TODO jflute 1on1にて、背景コメントの補足をちょこっとずつやっていく (2024/10/02)
// http://dbflute.seasar.org/ja/tutorial/handson/review/codingpolicy.html#backgroundcomment

// done shiny クラスのJavaDocのauthorの修正をお願いします by jflute (2024/10/09)
// // 3. 最低限のクラスJavaDoc | ハンズオンのコーディングポリシー
// https://dbflute.seasar.org/ja/tutorial/handson/review/codingpolicy.html#minjavadoc
/**
 * The test of variable. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author shiny
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
        // done shiny [いいね] yes, ヘンテコですね（＾＾。デバッグのときは良いなと思うことはあるのですが... by jflute (2024/10/09)
        // よく世の中のweb画面でnullって表示されちゃったりする原因の一つだったりします。
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
        // done shiny [へんじ] 良い質問ですね。ループで繰り返さなければ気にしなくて大丈夫というところが結論です by jflute (2024/10/09)
        //
        // 想定される懸念としては、sea + land + piariとした場合に、まず seaとlandを足して、sealandのStringインスタンスがnewされて...
        // sealandとpiariを足してsealandpiariの文字列ができあがるみたいに、中間成果物のStringインスタンスが作られて無駄処理/無駄メモリ食いになるというところで...
        // (なので、sea+land+piari+dstore+amba+miraco+...とかやると中間成果物がたくさん!?うわー、みたいな)
        //
        // でも、コンパイルされると sea + land + piari は、new StringBuilder().append(sea).append(land).append(piari) に変換されます。
        // sea+land+piari+dstore+amba+miraco+... とかやっても、内部ではStringBuilderというクラスのappend()になります。
        // StringBuilder は内部で char[] に対して足していく処理になっているので、無駄な中間成果物は発生しません。
        // なので、まず一行(1statement)での+の連結に関しては、何も気にしなくて大丈夫というところです。
        //
        // +しないほうがいいという例はこちらです。ループでsea+stageを繰り返していきますが...
        // String sea = "";
        // for (String stage : stageList) {
        //     sea = sea + stage;
        // }
        // これは中間成果物を避けようがないので、ループが多ければ多いほど遅くなります。
        // この場合は、人間が明示的にStringBuilderを使うようにしないといけません。
        //
        // StringBiulder sea = new StringBuilder();
        // for (String stage : stageList) {
        //     sea.append(stage);
        // }
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
        // done shiny [いいね] 集中してくれてありがとう！（＾＾ by jflute (2024/10/09)
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
        // done shiny [ふぉろー] そうなんです！時に戻り値が「必要であれば使ってね」くらいの補足情報のときもあり... by jflute (2024/10/09)
        // e.g. 
        //  new File("/tmp/sea").delete(); // ファイルの削除、戻り値のbooleanで本当に削除できたか？が戻る
        // 確認が必要な場面もあれば、確認しなくてもいいやっていうケースもあるので、戻り値があっても見ないこともあります。
        // ゆえに、コンパイルエラーにできないんでしょうね。
        // でも確かに、Immutableなオブジェクトだったら無駄処理確定なので、何かしらエラーとか警告とかあって欲しいところですよね。
        //
        // ちなみに、BigDecimalのクラスのJavaDocコメントを見ると、第一声にImmutableと書いてあったりします。
        // また、add()メソッドにカーソルを当ててツールチップでJavaDocを見ると、第一声でReturnsとか書いてあるし...
        // 戻り値の説明のところで this + argend とか書いてあったりして、エディター上でクラスの情報を見ることもできます。
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
        // done shiny [お知らせ] ラッパー型はstep3でじっくり補足していきますね by jflute (2024/10/09)
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
        // done shiny [いいね] 素晴らしい。ここはプログラミング初心者の方が間違えやすいエクササイズなのですが... by jflute (2024/10/09)
        // 「引数の変数」と「呼び出し側で扱ってる変数」は「名前は同じでも別物(別変数)」という感覚を確認するところです。
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
        // done shiny [ふぉろー] 色々と思考してくださっててありがとうございます。 by jflute (2024/10/09)
        // StringBuilderのクラスのJavaDocを見ると.. "A mutable sequence of characters." と書いてあります。
        // こいつは、ひとつのインスタンスの中で自分自身の値をどんどん変えることができるものなのですね。
        //
        // helpメソッドの方で受け取ったStringBuilderのインスタンスは、testメソッドの一行目でnewしたインスタンスがそのまま渡ってきています。
        // なので、testメソッドのseaもhelpメソッドのseaも同じインスタンスを参照しているわけです。
        //
        // ここまでは一つ前のStringのエクササイズと同じなのですが、StringはImmutableなのでconcat()してもインスタンス自体の値は何も変わらない。
        // (かつ、戻り値を受け取ってもいないので、連結された新しいStringインスタンスは誰も使ってもいない)
        // 
        // 今回はMutableなので、helpでappend()したら、そのStringBuilderインスタンスの中の値は書き換わります。
        // testメソッドが参照するStringBuilderインスタンスも同じインスタンスですから、参照したら書き換わった値が取得されます。
        // ゆえに、harborだけではなく、harbor416なのですね。
        //
        // int landの方は、helpメソッドで ++land されていて、appendされるときのlandは416となりますが、
        // testメソッドで保持しているlandは415のままです(エクササイズとしてそれを確認するようにはなってないですが)。
        // ここは同じland変数でも「別の箱」ですから、helpのlandを1足しただけの状態です。
        // ラッパーにするとオブジェクト参照で同じインスタンスを共有することになるので、変更があったら適用されそうですが...
        // これはStringとStringBuilderの話と同じ、共有されてもImmutableであれば値を書き換えようがないです。
        // IntegerはImmutableなので、intがIntegerに変わってもインスタンスの中の値は書き換えられないので結果は変わらないです。
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
        // done shiny [ふぉろー] メソッドに対して引数で値を渡すとき... by jflute (2024/10/09)
        // プリミティブ型は、コピーのイメージで良いです。intなら1という値のコピーを渡す感じ。
        // ラッパー型は、オブジェクト型なのでポインターが渡されるイメージです。
        // (ポインターがコピーされるというイメージでも良いですね、インスタンスの住所を書いた紙がコピーされて住所を共有すると)
        //
        // 今回は、helpメソッドのseaのStringBuilderインスタンスは途中までtestが参照しているインスタンスと同じものです。
        // ですが、そのインスタンスに対してのappend()はどこでも呼ばれていないです。
        // new StringBuilder(seaStr).append(land); のところで、新しいStringBuilderインスタンスをnewしてappend()しているので、
        // もはやtestメソッドと共有してるStringBuilderインスタンスとは無関係なのです。(別の建物で作業してるみたいな)
        //
        // コンストラクターでseaStrを入れているので、別インスタンスだとしても繋がりがありそう？と一瞬思えるのですが...
        // sea.toString()は、StringBuilderからImmutableなStringを作り出して戻しているだけで、
        // 戻した瞬間からそのStringと元のStringBuilderは無関係になりますので、つながりは途絶えます。
        // (でも仮に new StringBuilder(sea) という感じで新しいStringBuilderに元のStringBuilderを入れ込んでも、
        // 中では元のStringBuilderから値をコピーしてもらうだけなので、インスタンス同士のつながりは発生しないようになっています)
        //
        // 最後、helpメソッドで sea = ... と代入したところで、元のStringBuilderインスタンスへの参照を放棄したことなります。
        // testメソッドのseaとhelpメソッドのsea変数は別物ですから、testメソッドのseaには特に何も変化は起きないです。
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

    // done shiny [いいね] 難しいエクササイズ！（＾＾。素晴らしいです by jflute (2024/10/09)
    public String help_yourExercise(String name) {
        ++age;
        name = name.substring(0, 4);
        StringBuilder realName = new StringBuilder(name);
        realName.append("taro");
        return realName.toString();
    }
}
