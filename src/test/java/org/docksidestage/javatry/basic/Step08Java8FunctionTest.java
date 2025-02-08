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
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.docksidestage.javatry.basic.st8.St8DbFacade;
import org.docksidestage.javatry.basic.st8.St8Member;
import org.docksidestage.javatry.basic.st8.St8Withdrawal;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Java8 functions. <br>
 * Operate as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りに実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author shiny
 */
public class Step08Java8FunctionTest extends PlainTestCase {

    // ===================================================================================
    //                                                                              Lambda
    //                                                                              ======
    // -----------------------------------------------------
    //                                              Callback
    //                                              --------
    /**
     * Are all the strings by log() methods in callback processes same? (yes or no) <br>
     * (コールバック処理の中で出力しているログの文字列はすべて同じでしょうか？ (yes or no))
     */
    public void test_java8_lambda_callback_basic() {
        String title = "over";

        log("...Executing named class callback(!?)");
        helpCallbackConsumer(new St8BasicConsumer(title));

        log("...Executing anonymous class callback");
        helpCallbackConsumer(new Consumer<String>() {
            public void accept(String stage) {
                log(stage + ": " + title);
            }
        });

        log("...Executing lambda block style callback");
        helpCallbackConsumer(stage -> {
            log(stage + ": " + title);
        });

        log("...Executing lambda expression style callback");
        helpCallbackConsumer(stage -> log(stage + ": " + title));

        // your answer? => ログ出力される文字列はすべて同じ
        // 背景：(1から４の順番でみていく)
        // 前提：まず、Consumerというインターフェース<T型>があり、それにはaccept(T t)というメソッドが存在している
        // Callback関数では、最初にbroadway, 最後にhangarがログに出される。
        // 1. St8BasicConsumerクラスはConsumerを実装している。このクラスにはtitleというフィールドがあり、Constructorで初期化される。この例では、overで初期化されている。
        // acceptメソッドはOverrideされていて、引数stageでstage : titleがログに出されるようになっている。
        // したがって、Callback関数ないで呼ばれるaccept()はdockside : overとなる
        // 2. 匿名クラスを使ってConsumerのインターフェースをインスタンス化し、acceptメソッドをクラス内で定義している。
        // なので実質上とやっていることは全く一緒なので、accept()はdockside : overになる
        // 3. Step7のエクササイズでもちょっと触れたが...Consumerは関数型インターフェースである（一つの抽象メソッドしか持たない）
        // そして、Lambda式はそのインターフェースを手軽に実装できる構文として使える。
        // 渡しているLambda式は...引数stageでlog(stage + ": " + title)するというもの。
        // つまり上の二つでoverrideしたaccept()メソッドと同じものをLambda式として渡している。
        // したがって、出力するLogも同じになる。
        // 4. Lambda式を{}で囲むと複数のステートメントを書くことができるが、単一だと省略できる！
        //
        // 答え：正解でした。

        // cannot reassign because it is used at callback process
        //title = "wave";
    }

    /**
     * What is order of strings by log(). (write answer as comma-separated) <br>
     * (ログに出力される文字列の順番は？ (カンマ区切りで書き出しましょう))
     */
    public void test_java8_lambda_callback_order() {
        log("harbor");
        helpCallbackConsumer(stage -> {
            log(stage);
        });
        log("lost river");
        // your answer? => harbor, broadway, dockside, hangar, lost river
        //
        // 答え：正解でした。
    }

    private class St8BasicConsumer implements Consumer<String> {

        private final String title;

        public St8BasicConsumer(String title) {
            this.title = title;
        }

        @Override
        public void accept(String stage) {
            log(stage + ": " + title);
        }
    }

    private void helpCallbackConsumer(Consumer<String> oneArgLambda) {
        log("broadway");
        oneArgLambda.accept("dockside");
        log("hangar");
    }

    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_java8_lambda_callback_valueRoad() {
        String label = "number";
        String sea = helpCallbackFunction(number -> {
            return label + ": " + number;
        });
        log(sea); // your answer? => number: 7
    }

    private String helpCallbackFunction(Function<Integer, String> oneArgLambda) {
        return oneArgLambda.apply(7);
    }

    // -----------------------------------------------------
    //                                         Convert Style
    //                                         -------------
    /**
     * Change callback style like this:
     * <pre>
     * o sea: to lambda block style
     * o land: to lambda expression style
     * o piari: to lambda block style
     * </pre>
     * (このようにコールバックスタイルを変えてみましょう:)
     * <pre>
     * o sea: BlockのLambda式に
     * o land: ExpressionのLambda式に
     * o piari: BlockのLambda式に
     * </pre>
     */
    public void test_java8_lambda_convertStyle_basic() {
        helpCallbackSupplier(() -> {
            return "broadway";
        }); // sea);

        helpCallbackSupplier(() -> {
            return "dockside";
        }); // land

        helpCallbackSupplier(() -> {
            return "hangar";
        }); // piari
    }

    private void helpCallbackSupplier(Supplier<String> oneArgLambda) {
        String supplied = oneArgLambda.get();
        log(supplied);
    }

    // ===================================================================================
    //                                                                            Optional
    //                                                                            ========
    /**
     * Are the strings by two log() methods same? (yes or no) <br>
     * (二つのlog()によって出力される文字列は同じでしょうか？ (yes or no))
     */
    public void test_java8_optional_concept() {
        St8Member oldmember = new St8DbFacade().oldselectMember(1);
        if (oldmember != null) {
            log(oldmember.getMemberId(), oldmember.getMemberName());
        }

        Optional<St8Member> optMember = new St8DbFacade().selectMember(1);
        if (optMember.isPresent()) {
            St8Member member = optMember.get();
            log(member.getMemberId(), member.getMemberName());
        }
        // your answer? => 同じ
        // 背景：まず、St8Memberというクラスがあり、St8DbFacadeはそのクラスのインスタンスを生成するFactoryクラスの役割を果たしている。
        // St8DbFacadeのoldselectMember()メソッドは、受け取った引数memberId(Integer)に対してSt8Memberのインスタンスを生成する。
        // この時、memberIdが1-3の場合は、特定のインスタンスを生成し、それ以外の場合はnullを返す。
        // 上記の例えの場合、渡されるMemberIdは1なので、nullにはならず、そのインスタンスのmemberIdとmemberNameがログ出しされる。
        // selectMember()メソッドは、受け取った引数stageIdに対して、oldselectMember(stageId)を呼び出すが、唯一の違いは、Optional.ofnullable()でラップして、Optional型を返すこと。
        // つまり、1-3以外の数を渡した場合、nullではなくて空のOptionalが返ってくる。
        // だが、上記の例では、上と同じく1を渡しているため、T get()で受け取るインスタンスは同じであり、したがってLog出力される内容も一緒である。
        //
        // 答え：正解でした。
        
        // [1on1でのふぉろー] Optionalの根本メリット、それを流行らせる追加要素
    }

    /**
     * Are the strings by two log() methods same? (yes or no) <br>
     * (二つのlog()によって出力される文字列は同じでしょうか？ (yes or no))
     */
    public void test_java8_optional_ifPresent() {
        Optional<St8Member> optMember = new St8DbFacade().selectMember(1);
        if (optMember.isPresent()) {
            St8Member member = optMember.get();
            log(member.getMemberId(), member.getMemberName());
        }
        optMember.ifPresent(member -> {
            log(member.getMemberId(), member.getMemberName());
        });
        // your answer? => 同じ
        // 背景：まず、呼び出しているメソッド（selectMember(1)）は上のエクササイズと一緒。
        // なのでoptMemberは空ではなく、isPresent()はTrueとなる。したがって、log出力される内容は上記のエクササイズと一緒。
        // 下のifPresentの定義は、public void ifPresent(java. util. function. Consumer<? super T> action )でDescriptionにはご親切に：If a value is present, performs the given action with the value, otherwise does nothing.って書いてくれてる（Intellijくん）。
        // なのでOptionalのValueであるmember(インスタンス)で実行されるメソッド（getMemberId, getMemberName）は一緒であり、log出力される内容も一緒である。

    }

    /**
     * What string is sea, land, piari, bonvo, dstore, amba variables at the method end? <br>
     * (メソッド終了時の変数 sea, land, piari, bonvo, dstore, amba の中身は？)
     */
    public void test_java8_optional_map_flatMap() {
        St8DbFacade facade = new St8DbFacade();

        // traditional style
        St8Member oldmemberFirst = facade.oldselectMember(1);
        String sea;
        if (oldmemberFirst != null) {
            St8Withdrawal withdrawal = oldmemberFirst.oldgetWithdrawal();
            if (withdrawal != null) {
                sea = withdrawal.oldgetPrimaryReason();
                if (sea == null) {
                    sea = "*no reason1: the PrimaryReason was null";
                }
            } else {
                sea = "*no reason2: the Withdrawal was null";
            }
        } else {
            sea = "*no reason3: the selected Member was null";
        }

        Optional<St8Member> optMemberFirst = facade.selectMember(1);

        // map style
        String land = optMemberFirst.map(mb -> mb.oldgetWithdrawal())
                .map(wdl -> wdl.oldgetPrimaryReason())
                .orElse("*no reason: someone was not present");

        // flatMap style
        String piari = optMemberFirst.flatMap(mb -> mb.getWithdrawal())
                .flatMap(wdl -> wdl.getPrimaryReason())
                .orElse("*no reason: someone was not present");

        // flatMap and map style
        String bonvo = optMemberFirst.flatMap(mb -> mb.getWithdrawal())
                .map(wdl -> wdl.oldgetPrimaryReason())
                .orElse("*no reason: someone was not present");

        String dstore = facade.selectMember(2)
                .flatMap(mb -> mb.getWithdrawal())
                .map(wdl -> wdl.oldgetPrimaryReason())
                .orElse("*no reason: someone was not present");

        String amba = facade.selectMember(3)
                .flatMap(mb -> mb.getWithdrawal())
                .flatMap(wdl -> wdl.getPrimaryReason())
                .orElse("*no reason: someone was not present");

        int defaultWithdrawalId = -1;
        Integer miraco = facade.selectMember(2)
                .flatMap(mb -> mb.getWithdrawal())
                .map(wdl -> wdl.getWithdrawalId()) // ID here
                .orElse(defaultWithdrawalId);

        log(sea); // your answer? => music
        log(land); // your answer? => music
        log(piari); // your answer? => music
        log(bonvo); // your answer? => music
        log(dstore); // your answer? => *no reason: someone was not present
        log(amba); // your answer? => *no reason: someone was not present
        log(miraco); // your answer? => 12
        //
        // 背景：まず最初のseaですが...
        // 呼び出しているoldselectMember()は上のエクササイズと一緒、つまりnullじゃないことはわかっている、
        // さらに生成時のコンストラクタで、new St8Withdrawal(11, "music")が呼ばれているので、withDrawlもnullではなくて、String oldgetPrimaryReasonは引数で渡されているmusicを返す。
        // したがってseaは、musicである。
        // 次のland, piari, bonvoは上と実質同じことをやっている。selectMember()でstageId=1が渡されているので、生成されているインスタンスは上と一緒。
        // そこからmapなどを使ってチェインさせているが、呼び出しているメソッド(oldGet, get)に応じてflatmapを使用している（NestされたOptionalにならない為に）。
        // だが、やってることは一緒で、orElseではValueが存在するため、Otherが呼ばれずland, piari, bonvoの中身はmusicになる。
        // dstoreの時は、stageId/memberId = 2では、new St8Withdrawal(12, null)が渡されているので、withdrawl自体はnullではないが、PrimarySeasonはnullなので、oldGetPrimaryReasonは空のOptionalになり、orElseでOtherの部分が呼ばれ、したがってdstoreの中身は、*no reason: someone was not presentになる。
        // ambaの時は、stageId/memberId = 3なので、withdrawlはそもそもnullである。したがって、flatmapで返される値は全て空のOptionalであり、最終的のorElseでは上同様、*no reason: someone was not presentが呼ばれ、ambaの中身はそのStringになる。
        // 最後のmiracoは、stageId/memberId = 2で初期化されており、new St8Withdrawal(12, null)が渡されているのでgetWithDrawalIdは12を返し、miaracoはその12になる。
        //
        // 答え：正解でした。
    }

    // done jflute 次回1on1このへんから (2025/01/29)
    // [1on1でのふぉろー] Optionalの問答無用get()の教科書的な話
    // そして、現実論でみんなちゃんとしたorElseThrow()ができるだろうか？問題
    // そして、DBFluteのOptionalの考え方の紹介
    /**
     * What string is sea variables at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_java8_optional_orElseThrow() {
        Optional<St8Member> optMember = new St8DbFacade().selectMember(2);
        St8Member member = optMember.orElseThrow(() -> new IllegalStateException("over"));
        String sea = "the";
        try {
            String reason = member.getWithdrawal().map(wdl -> wdl.oldgetPrimaryReason()).orElseThrow(() -> {
                return new IllegalStateException("wave");
            });
            sea = reason;
        } catch (IllegalStateException e) {
            sea = e.getMessage();
        }
        log(sea); // your answer? => wave
        //
        // 背景：まず、St8DbFacadeで上のエクササイズ群同様にstageId/memberId = 2でOptional型のSt8Memberが初期化されている。
        // これはEmptyのOptional出ないことはわかっているので、次のmemberへのReassignはExceptionが呼ばれず成功する。
        // その後try/catchブロックに入るが、withdrawlのprimary reasonがnullなのは分かっているため、map(..oldgetPrimaryReason())の部分が空のOptionalをw返し、
        // したがってIllegalStateException("wave")がThrowされる。
        // それがキャッチされ、getMessage()でwaveがseaにAssignされるため、seaはwaveになる。
    }

    // TODO jflute Stream API, 1on1でふぉろー予定 (2025/02/05)
    // ===================================================================================
    //                                                                          Stream API
    //                                                                          ==========
    /**
     * What string is sea, land variables at the method end? <br>
     * (メソッド終了時の変数 sea, land の中身は？)
     */
    public void test_java8_stream_concept() {
        List<St8Member> memberList = new St8DbFacade().selectMemberListAll();
        List<String> oldfilteredNameList = new ArrayList<>();
        for (St8Member member : memberList) {
            if (member.getWithdrawal().isPresent()) {
                oldfilteredNameList.add(member.getMemberName());
            }
        }
        String sea = oldfilteredNameList.toString();
        log(sea); // your answer? => ["broadway", "dockside"]
        // 背景：St8DbFacadeのselectMemberListAll()ではselectMember(）：1, 2, 3で初期化され、memberが持ってるList purchase listにpurchaseを複数追加している。
        // つまり、この時点でmemberListはstageId/memberId: 1, 2, 3で初期されたインスタンスを三つ持っている状態。
        // それをForループで回し、getWithDrawl()が空でない場合、oldfilteredNameListのメンバーの名前を追加している。
        // 前回のエクササイズから、1と2はwithDrawlが空でなく、3が空であることは分かっているので、追加されるmemberの名前は、broway, dockside。そのリストをtoStringでString化してログ出ししている。
        //
        // 答え：正解でした。

        List<String> filteredNameList = memberList.stream() //
                .filter(mb -> mb.getWithdrawal().isPresent()) //
                .map(mb -> mb.getMemberName()) //
                .collect(Collectors.toList());
        String land = filteredNameList.toString();
        log(land); // your answer? => ["brodway", "dockside"]
        // 背景：やっている事は上と同じで、実現を単純にSteramAPI経由でやっている。
        // まず上記で初期化したmemberListをStream化して、そこからWithdrawalが空でないものだけをFilter、さらにgetMemberName()をMapすることで、memberNameでできたStreamに変化し、
        // collect(Collectors.toList())でそれをString型のListにAccumulateしている。
        // できたListをtoStringでString化して、Log出ししているので、答えは上と一緒になる。
        //
        // 答え：正解でした。
    }

    /**
     * What string is sea, variables at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_java8_stream_map_flatMap() {
        List<St8Member> memberList = new St8DbFacade().selectMemberListAll();
        int sea = memberList.stream()
                .filter(mb -> mb.getWithdrawal().isPresent())
                .flatMap(mb -> mb.getPurchaseList().stream())
                .filter(pur -> pur.getPurchaseId() > 100)
                .mapToInt(pur -> pur.getPurchasePrice())
                .distinct()
                .sum();
        log(sea); // your answer? => 600
        // 背景：まず途中まではやっていること一緒。memberListを初期化して、それをStream化する。
        // そのStreamからwithDrawlが空じゃないものだけをFilterする。
        // そこから各memberのPurchaseListをStream化して、Flatする（単一のStream化）。
        // (Member2のPurchaseListは空なのでmember1のpurchase ListだけがStream化される)
        // そ子からpurchaseIdが100以上のものだけをFilter（全て該当）
        // そこからpurchasePriceをIntにmapする(100, 200, 200, 300)になる。
        // distinct()で同じものを削除（200）し、100, 200, 300になる。
        // sum()でそれらを足して、100 + 200 + 300 = 600で合計600になる。
    }

    // *Stream API will return at Step12 again, it's worth the wait!
}
