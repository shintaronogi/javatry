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

import java.io.File;
import java.io.IOException;

import org.docksidestage.bizfw.basic.supercar.SupercarClient;
import org.docksidestage.javatry.basic.st7.St7BasicExceptionThrower;
import org.docksidestage.javatry.basic.st7.St7ConstructorChallengeException;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of variable. <br>
 * Operate as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りに実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author shiny
 */
public class Step07ExceptionTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_exception_basic_catchfinally() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        StringBuilder sea = new StringBuilder();
        try {
            thrower.land();
            sea.append("dockside");
        } catch (IllegalStateException e) {
            sea.append("hangar");
        } finally {
            sea.append("broadway");
        }
        log(sea); // your answer? => hangarbroadway
        // 背景： さーっと読んでみると、ExceptionがThrowされてStringがAppendされるコードに見える。
        // Exceptionの中身をみてみると、land()でDebug Messageがいくつかと、IllegalStateExceptionがThrowされている。
        // つまり、thrower.land()でIllegalStateExceptionがThrowされるので、catch節に入り、"hangar"と"broadway"がAppendされる
        // docksideはExceptionがThrowされた後のコードなので呼ばれない。
        //
        // 答え：正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_basic_message() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        String sea = null;
        try {
            thrower.land();
            fail("no way here");
        } catch (IllegalStateException e) {
            sea = e.getMessage();
        }
        log(sea); // your answer? => oneman at showbase
        // 背景：やっていることは、前のエクササイズと似ている。
        // ExceptionをThrowして、Catchに入り、今回はMessageをGetしてSeaにAssignしている。
        // getMessage()はStringでExceptionをThrowした時のMessageをそのまま取ってくるので、oneman at showbaseになる。
        //
        // 答え：正解でした。
    }

    /**
     * What class name and method name and row number cause the exception? (you can execute and watch logs) <br>
     * (例外が発生したクラス名とメソッド名と行番号は？(実行してログを見て良い))
     */
    public void test_exception_basic_stacktrace() {
        St7BasicExceptionThrower thrower = new St7BasicExceptionThrower();
        try {
            thrower.land();
            fail("no way here");
        } catch (IllegalStateException e) {
            log(e);
        }
        // your answer? => St7BasicExceptionThrowserのoneman()メソッド、40行目。
        // 背景：エラーのスタックトレースをみていくと、下からどこで発生したかが見える。
        // どこで起きた→それはどのメソッドでどのクラスの→...etc.
        // 一番上をみてみると、実際にエラーがでた場所がわかる。
    }

    // ===================================================================================
    //                                                                           Hierarchy
    //                                                                           =========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_exception_hierarchy_Runtime_instanceof_RuntimeException() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof RuntimeException;
        log(sea); // your answer? => true
        // 背景：以前のエクササイズ（５だったかな）で独自のExceptionを定義した時にJavaのExceptionについて少し調べた。
        // 親クラスはThrowable（名前的に一瞬インターフェースかと思った）で内部実装をみると、Constructorや上にあるgetMessage()..などなどがある。
        // Throwableのサブクラスには、Error系とException系があり、Errorは通常プログラムで処理できない（続行できない）ようなエラー。StackOverflowとか。テストでAssertionErrorとかもみたりする。
        // 逆にExceptionはプログラム実行中に通常回復可能な状態を示すものという理解をしている。サブカテゴリとして、コンパイル時に明示的に処理・スローする必要があるChecked Exception（IOとかSQL）とUnchecked Exceptionがあるみたい。
        // Runtime ExceptionはUnchecked Exceptionと基底にあって、多くのサブクラスを有している（Nullpointer、Arithmetic、IllegalArgument、IllegalState、などなど）。
        // このエクササイズではIllegalStateを使っていて、これはRuntimeExceptionの一種なのでseaはTrueになる。
        //
        // 答え：正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Exception() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Exception;
        log(sea); // your answer? => true
        // 上にほとんど説明を書いてしまった...笑
        //
        // 答え：正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Error() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Error;
        log(sea); // your answer? => false
        //
        // 答え：正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Throwable() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Throwable;
        log(sea); // your answer? => true
        //
        // 答え：正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Throwable_instanceof_Exception() {
        Object exp = new Throwable("mystic");
        boolean sea = exp instanceof Exception;
        log(sea); // your answer? => false
        //
        // 答え：正解でした。
    }

    // ===================================================================================
    //                                                                         NullPointer
    //                                                                         ===========
    /**
     * What variable (is null) causes the NullPointerException? And what row number? (you can execute and watch logs) <br>
     * (NullPointerが発生する変数(nullだった変数)と、発生する行番号は？(実行してログを見ても良い))
     */
    public void test_exception_nullpointer_basic() {
        try {
            String sea = "mystic";
            String land = sea.equals("mystic") ? null : "oneman";
            String lowerCase = land.toLowerCase();
            log(lowerCase);
        } catch (NullPointerException e) {
            log(e);
        }
        // your answer? => 変数：land, 行番号：164
        // 背景：変数seaがmysticとしてIntitializeされてい流ので、LandがNullなる。
        // そのLandでtoLowercase()を呼ぼうとするとNullpointerExceptionがThrowされる。
        //
        // 答え：正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_nullpointer_headache() {
        try {
            String sea = "mystic";
            String land = !!!sea.equals("mystic") ? null : "oneman";
            String piari = !!!sea.equals("mystic") ? "plaza" : null;
            int sum = land.length() + piari.length();
            log(sum);
        } catch (NullPointerException e) {
            log(e);
        }
        // your answer? => 変数：piari, 行番号：182
        // !!! → !なので、piariがnullになる。
        //
        // 答え：正解でした。
    }

    /**
     * Refactor to immediately understand what variable (is null) causes the NullPointerException by row number in stack trace. <br>
     * (どの変数がNullPointerを引き起こしたのか(nullだったのか)、スタックトレースの行番号だけでわかるようにリファクタリングしましょう)
     */
    public void test_exception_nullpointer_refactorCode() {
        try {
            String sea = "mystic";
            String land = !!!sea.equals("mystic") ? null : "oneman";
            String piari = !!!sea.equals("mystic") ? "plaza" : null;
            // int sum = land.length() + piari.length();
            int landLength = land.length();
            int piariLength = piari.length();
            int sum = landLength + piariLength;
            log(sum);
        } catch (NullPointerException e) {
            log(e);
        }
        // [1on1でのふぉろー] 最近のJavaだと、NullPointerExceptionのメッセージで変数を教えてくれる。
    }

    // ===================================================================================
    //                                                                   Checked Exception
    //                                                                   =================
    /**
     * Show canonical path of new java.io.File(".") by log(), and if I/O error, show message and stack-trace instead <br>
     * (new java.io.File(".") の canonical path を取得してログに表示、I/Oエラーの時はメッセージとスタックトレースを代わりに表示)
     */
    public void test_exception_checkedException_basic() {
        try {
            File file = new File(".");
            String canonicalPath = file.getCanonicalPath();
            log(canonicalPath);
        } catch (IOException e) {
            log(e);
        }
        // [1on1でのふぉろー] チェック例外があまり流行ってない話。
        // でもちゃんと使えば便利なんだけど話も一応。
        // 発展して、Javaの仕組み化の文化も影響してる話。
    }

    // ===================================================================================
    //                                                                               Cause
    //                                                                               =====
    /**
     * What string is sea variable in the catch block?
     * And What is exception class name displayed at the last "Caused By:" of stack trace? <br>
     * (catchブロックの変数 sea, land の中身は？また、スタックトレースの最後の "Caused By:" で表示されている例外クラス名は？)
     */
    public void test_exception_cause_basic() {
        String sea = "mystic";
        String land = "oneman";
        try {
            throwCauseFirstLevel();
            fail("always exception but none");
        } catch (IllegalStateException e) {
            Throwable cause = e.getCause();
            sea = cause.getMessage();
            land = cause.getClass().getSimpleName();
            log(sea); // your answer? => Failed to call the third help method: symbol=-1
            // 背景：下のPrivateメソッドにちょくちょくメモを書いているが、throwCauseThirdLevel()が呼ばれて、Integer.valueOf()でエラーが出るので、NumberformatExceptionがThrowされる。
            // それが、throwCauseSecondLevelでCatchされ、IllegalArgumentExceptionがThrowされる。
            // さらに、それがthrowCauseFirstLevelがCatchされ、IllegalStateExceptionが呼ばれる。
            // 「ここ」では、それがCatchされ、CauseをGetする。CatchしたIllegalStateExceptionのCauseはIllegalArgumentExceptionなので、causeSecondLevelで呼ばれたExceptionのメッセージがGetされ、それがSeaになる。
            log(land); // your answer? => IllegalArgumentException
            // 背景：上にも書いてあるが、causeはIllegalArgumentExceptionなので、そのGetClas(), getSimpleName()はIllegalArgumentExcpetionになる。
            log("e", e); // your answer? => Caused by: NumberFormatException...: For input string: "piari"
            // エラーのスタックトレースは、名前の通り「Stack」なので、発生した場所から順に積み上げられていく(LIFO)。
            // キャッチされスローされると積み上がっていくので、一番上には一番最近キャッチ・スローされたエラーで、一番下には最初のものが表示される。
            // (「最後」っていう言葉が、上か下かよくわからないので一文脈的に番下として認識して書いてます)
        }
    }

    private void throwCauseFirstLevel() {
        int symbol = Integer.MAX_VALUE - 0x7ffffffe;
        // NOTE:
        // IntegerのMax Valueは32Bitなので2^31 - 1 = 2,147,483,647
        // 0x(１６進数)の7ffffffeは2,147,483,646
        // なのでSymbox = 1
        try {
            throwCauseSecondLevel(symbol);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Failed to call the second help method: symbol=" + symbol, e);
        }
    }

    private void throwCauseSecondLevel(int symbol) {
        try {
            --symbol;
            symbol--;
            // symbox = -1
            if (symbol < 0) {
                throwCauseThirdLevel(symbol);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to call the third help method: symbol=" + symbol, e);
        }
    }

    private void throwCauseThirdLevel(int symbol) {
        if (symbol < 0) {
            Integer.valueOf("piari");
        }
    }

    // done jflute 1on1ここから (2025/01/15)
    // ===================================================================================
    //                                                                         Translation
    //                                                                         ===========
    /**
     * Execute this test and read exception message and write situation and cause on comment for non-programmer. <br>
     * テストを実行して例外メッセージを読んで、プログラマーでない人にもわかるように状況と原因をコメント上に書きましょう。
     */
    public void test_exception_translation_debugChallenge() {
        try {
            new SupercarClient().buySupercar();
            fail("always exception but none");
        } catch (RuntimeException e) {
            log("*No hint here for training.", e);
            // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
            // What happens? Write situation and cause here. (何が起きた？状況と原因をここに書いてみましょう)
            // - - - - - - - - - -
            // 昔々、あるところにとても金持ちの人がいました。
            // その金持ちの人は、とても高い車を集めるのが好きなので、「SupercarClient」（スーパーカーのクライアント）と呼ばれています。
            // SupercarClientはスーパーカーのコレクションを持っていて、新たなスーパーカーを買うことができます。
            // でもスーパーカーを買うまでには、色々な複雑なプロセスが含まれているます。
            // SupercarClientは、スーパーカーを販売業者に注文し、そこから製造業者、さらに下請けと進んでいきます。これが社会の構造です。
            // 今回、クライアントはSeaみたいなハンドルのスーパーカーが欲しいと販売業者に言いました。
            // 販売業者はその情報を元にカタログを探し、大元の製造業者に伝えました。
            // 大元の製造業者は、そのカタログを元に必要なハンドルを探し、そのハンドルを作るように下請けのハンドル製造業者に伝えました。
            // それを製造するには、さらに必要なネジが必要だったので、さらに下請けの製造業者にスペックを伝え製造を頼みました。
            // ここで、一つの問題がおきました！このネジのスペックが可愛すぎたのです！このネジ製造会社の社長は可愛いものに飽きていて、もうこのネジは作らねえんだよ！と言いました。
            // ということで、残念ながらこのスペックのスーパーカーは作れないようです。
            // めでたし、めでたし。
            //
            //
            //
            // _/_/_/_/_/_/_/_/_/_/
        }
    }

    /**
     * Improve exception handling in supercar's classes to understand the situation
     * by only exception information as far as possible. <br>
     * できるだけ例外情報だけでその状況が理解できるように、Supercarのクラスたちの例外ハンドリングを改善しましょう。
     */
    public void test_exception_translation_improveChallenge() {
        // [1on1でのふぉろー] 例外の翻訳のお話。一つの中断イベントに付き、複数のメッセージがあるもの (複数の目撃証言)
        // フレームワークでの例外翻訳のお話も。(LastaFluteやDBFluteでの高度な例外メッセージ)
        try {
            // それぞれのクラスのネストで専用のException ClassとTry, Catch, Rethrow(cause)することでスタックトレースのエラー情報から状況が把握できるようにした。
            new SupercarClient().buySupercar(); // you can fix the classes
            fail("always exception but none");
        } catch (RuntimeException e) {
            log("*No hint here for training.", e);
        }
    }

    // done jflute 次回1on1ここから (2025/01/22)
    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Fix terrible (YABAI in Japanese) exception handling. (you can modify exception class) <br>
     * (やばい例外ハンドリングがあるので修正しましょう (例外クラスを修正してOK))
     */
    public void test_exception_writing_constructorChallenge() {
        try {
            helpSurprisedYabaiCatch();
        } catch (St7ConstructorChallengeException e) {
            log("Thrown by help method", e); // should show also "Caused-by" information
        }
    }

    private void helpSurprisedYabaiCatch() {
        try {
            helpThrowIllegalState();
        } catch (IllegalStateException e) {
            // Caused byが失われて何が理由でExceptionが出たのかわからなくてなっていたので、Exception側に新たなConstructorを追加し、Causeと一緒にThrowするように変更した。
            throw new St7ConstructorChallengeException("Failed to do something.", e);
        }
    }

    private void helpThrowIllegalState() {
        if (true) { // simulate something illegal
            String importantValue = "dummy"; // important to debug
            throw new IllegalStateException("something illegal: importantValue=" + importantValue);
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What is the concept difference between Exception and Error? Write it on comment. <br>
     * (ExceptionとErrorのコンセプトの違いはなんでしょうか？コメント上に書きましょう)
     */
    public void test_exception_zone_differenceExceptionError() {
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // Write here. (ここに書いてみましょう)
        // - - - - - - - - - -
        // もう既に↑に書いてしまっているけど、ExceptionとErrorのコンセプトの違いは..
        // 1. アプリケーションレベルかシステムレベルで発生するか
        // 2. 回復可能か不可能か
        // の違いだと思っています！つまり一言でいうと「どれだけ深刻か？」かなと。
        // アプリケーションレベル（ロジックとかで出る）のものは、開発者がCatchなどで処理することで正常な動作を維持できるもの（回復可能）
        // システムレベルで出るものは、深刻な問題で通常の場合、処理を続行できないものかなと。
        //
        // [1on1でのフォロー] 業務例外(ビジネス例外/アプリ例外)とシステム例外の違いとしては100点満点。
        // 一方で、業務例外を業務エラーと呼んだり、システム例外とシステムエラー呼んだり、どういうことだろう？
        // JavaのErrorとExceptionの違いはそこと一致するのか？そもそもなんで例外という言葉を使うのか？
        // 業務的に正常なレアケースを例外と呼んでも自然である。
        // でも、NullPointerException って....ほぼシステムエラーだよね。
        // throwした瞬間の解釈と、catchしたときの解釈って一緒化？
        // いったんは例外でthrowする...そしてcatchする人は全体像を知っているので、
        // そこで業務例外なのか？エラーなのか？判断できる。という考え方。
        // _/_/_/_/_/_/_/_/_/_/
    }
}
