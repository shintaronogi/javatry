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

import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketType;
import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.docksidestage.bizfw.basic.objanimal.Cat;
import org.docksidestage.bizfw.basic.objanimal.Dog;
import org.docksidestage.bizfw.basic.objanimal.Dragon;
import org.docksidestage.bizfw.basic.objanimal.Zombie;
import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.flyable.Flyable;
import org.docksidestage.bizfw.basic.objanimal.loud.AlarmClock;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;
import org.docksidestage.bizfw.basic.objanimal.runner.FastRunner;
import org.docksidestage.javatry.basic.st6.dbms.St6MySql;
import org.docksidestage.javatry.basic.st6.dbms.St6PostgreSql;
import org.docksidestage.javatry.basic.st6.dbms.St6Sql;
import org.docksidestage.javatry.basic.st6.os.MacOperatingSystem;
import org.docksidestage.javatry.basic.st6.os.St6OperationSystem;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of object-oriented. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step06ObjectOrientedTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        About Object
    //                                                                        ============
    // -----------------------------------------------------
    //                                        Against Object
    //                                        --------------
    /**
     * Fix several mistakes (except simulation) in buying one-day passport and in-park process. <br>
     * (OneDayPassportを買って InPark する処理の中で、(simulationを除いて)間違いがいくつかあるので修正しましょう)
     */
    @SuppressWarnings("unused") // Dead codeの部分をあえて残すために
    public void test_objectOriented_aboutObject_againstObject() {
        //
        // [ticket booth info]
        //
        // simulation: actually these variables should be more wide scope
        int oneDayPrice = 7400;
        int quantity = 10;
        Integer salesProceeds = null;

        //
        // [buy one-day passport]
        //
        // simulation: actually this money should be from customer
        int handedMoney = 10000;
        if (quantity <= 0) {
            throw new IllegalStateException("Sold out");
        }
        if (handedMoney < oneDayPrice) {
            throw new IllegalStateException("Short money: handedMoney=" + handedMoney);
        }
        // step05同様、quantityをDecrementする場所はExceptionの後であるべき
        --quantity;
        // step05同様、salesProceeds(売上)は、チケットの値段に依存するべきである
        // そしてNullpointerExceptionを避けるためIfで分岐する（が、salesProceedsの初期値を0、またはPrimitive型にする方がいいと思う）
        // done shiny Dead codeになってるのは、salesProceedsが単なるローカル変数だから by jflute (2024/12/25)
        // まあ、業務想定で考えたらチェックするのは当然のことなので、これはこれでOK
        if (salesProceeds == null) {
            salesProceeds = oneDayPrice;
        } else {
            salesProceeds += oneDayPrice;
        }

        // done shiny まだある by jflute (2024/12/25)
        //
        // [ticket info]
        //
        // simulation: actually these variables should be more wide scope
        // よくみたらdisplayPriceにquantityを代入してる！？
        int displayPrice = oneDayPrice;
        boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
        // simulation: actually this process should be called by other trigger
        if (alreadyIn) {
            // displayPrice =とするならば、DisplayPriceをログ出しするべき。
            throw new IllegalStateException("Already in park by this ticket: displayPrice=" + displayPrice);
        }
        alreadyIn = true;

        //
        // [final process]
        //
        // 一般的に引数多いメソッドは避けるべきだと思う。プログラミングエラーを起こしやすい。関連するものたちはObjectなどで管理するべき。
        // [1on1でのフォロー] オブジェクトとは？の話、そして、開発者としては間違えないように集中力を高めるの大事話
        saveBuyingHistory(quantity, displayPrice, salesProceeds, alreadyIn);
    }

    private void saveBuyingHistory(int quantity, Integer salesProceeds, int displayPrice, boolean alreadyIn) {
        if (alreadyIn) {
            // done shiny まだある by jflute (2024/12/25)
            // simulation: only logging here (normally e.g. DB insert)

            // 引数がバラバラでした。これで終わりだと信じたい！
            showTicketBooth(quantity, salesProceeds);
            showYourTicket(displayPrice, alreadyIn);
        }
    }

    private void showTicketBooth(int quantity, Integer salesProceeds) {
        log("Ticket Booth: quantity={}, salesProceeds={}", quantity, salesProceeds);
    }

    private void showYourTicket(int displayPrice, boolean alreadyIn) {
        log("Ticket: displayPrice={}, alreadyIn={}", displayPrice, alreadyIn);
    }

    // -----------------------------------------------------
    //                                          Using Object
    //                                          ------------
    /**
     * Read (analyze) this code and compare with the previous test method, and think "what is object?". <br>
     * (このコードを読んで(分析して)、一つ前のテストメソッドと比べて、「オブジェクトとは何か？」を考えてみましょう)
     */
    public void test_objectOriented_aboutObject_usingObject() {
        //
        // [ticket booth info]
        //
        TicketBooth booth = new TicketBooth();

        // *booth has these properties:
        //int oneDayPrice = 7400;
        //int quantity = 10;
        //Integer salesProceeds = null;

        //
        // [buy one-day passport]
        //
        // if step05 has been finished, you can use this code by jflute (2019/06/15)
        //Ticket ticket = booth.buyOneDayPassport(10000);
        booth.buyOneDayPassport(10000); // as temporary, remove if you finished step05
        Ticket ticket = new Ticket(TicketType.ONE_DAY, 7400); // also here (Step5中にエラー出るので一旦ここは合わせて回避)

        // *buyOneDayPassport() has this process:
        //if (quantity <= 0) {
        //    throw new TicketSoldOutException("Sold out");
        //}
        //if (handedMoney < oneDayPrice) {
        //    throw new TicketShortMoneyException("Short money: handedMoney=" + handedMoney);
        //}
        //--quantity;
        //salesProceeds = handedMoney;

        // *ticket has these properties:
        //int displayPrice = oneDayPrice;
        //boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
        ticket.doInPark();

        // *doInPark() has this process:
        //if (alreadyIn) {
        //    throw new IllegalStateException("Already in park by this ticket: displayPrice=" + displayPrice);
        //}
        //alreadyIn = true;

        //
        // [final process]
        //
        saveBuyingHistory(booth, ticket);
    }

    private void saveBuyingHistory(TicketBooth booth, Ticket ticket) {
        if (ticket.hasRemainingDays()) {
            // only logging here (normally e.g. DB insert)
            doShowTicketBooth(booth);
            doShowYourTicket(ticket);
        }
    }

    private void doShowTicketBooth(TicketBooth booth) {
        log("Ticket Booth: quantity={}, salesProceeds={}", booth.getOneDayPassportQuantity(), booth.getSalesProceeds());
    }

    private void doShowYourTicket(Ticket ticket) {
        log("Your Ticket: displayPrice={}, alreadyIn={}", ticket.getDisplayPrice(), ticket.hasRemainingDays());
    }

    // write your memo here:
    // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
    // what is object?
    // オブジェクトは、「クラスという物や概念を抽象したもの」を具体的に表現したもの。車 → Toyota社の何とかモデル、動物 → 猫・犬などなど、、
    // これによって人間にとって直感的に理解しやすく、「コード」という一見意味不明なものが「わかりやすく意味を持つもの」となると個人的には思っている。
    // また抽象化して共通かすることによって、コードを再利用しやすくしたり、独立性を持つことによって責任範囲をそれぞれに持たせたりできる。
    // さらに、継承という概念を使って拡張しやすくしたりできる。
    // カプセル化して外部から隠蔽したり！（めっちゃ出てくる）
    // _/_/_/_/_/_/_/_/_/_/

    // ===================================================================================
    //                                                              Polymorphism Beginning
    //                                                              ======================
    /**
     * What string is sea and land variable at the method end? <br>
     * (メソッド終了時の変数 sea, land の中身は？)
     */
    public void test_objectOriented_polymorphism_1st_concreteOnly() {
        Dog dog = new Dog();
        BarkedSound sound = dog.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        // 背景：DogクラスはAnimalクラスを継承しているクラス
        // Animalクラスには、bark()メソッドがありその中でAbstractなgetBarkWord()メソッドからBarkwordを取得し、その言葉をもったBarksoundクラスのオブジェクトを返す
        // getBarkWord()はDogクラスでImplementされていて、wanを返すようになっている
        // したがって、その言葉をGetするgetBarkWord()はwanを返す
        int land = dog.getHitPoint();
        log(land); // your answer? => 7
        // 背景：bark()メソッドの中でhpをDecrementするdownHitPoint()メソッドが3回呼ばれていいるため
        //
        // 答え：正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_2nd_asAbstract() {
        Animal animal = new Dog();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
        // 背景：Polymorphismの基本的な考え方の一つで、名前は確かアップキャスティング？って呼ばれているはず
        // 継承したクラスの参照を親クラスに代入することは問題なくできる（利点は様々）
        //
        // 答え：正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_3rd_fromMethod() {
        Animal animal = createAnyAnimal();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
        // 背景：Factoryパターンにちょっと似ている。createAnyAnimal() (メソッド名は一旦置いておいて)は、新しいDog() -> Animalインスタンスを返すので、
        // やっていることは実質上と同じ。
        //
        // 答え：正解でした。
    }

    private Animal createAnyAnimal() {
        return new Dog();
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_4th_toMethod() {
        Dog dog = new Dog();
        doAnimalSeaLand_for_4th(dog);
    }

    private void doAnimalSeaLand_for_4th(Animal animal) {
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
        // 背景：やっている事としてはまたまた上のエクササイズと一緒で、違いとしては今度はDogクラスのインスタンスを外から関数に渡しているということ
        //
        // 答え：正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_5th_overrideWithSuper() {
        Animal animal = new Cat();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => nya-
        int land = animal.getHitPoint();
        log(land); // your answer? => 5
        // 背景：今回は、AnimalクラスをExtendした別のCatクラスの実装
        // Dogとの違いの一つがdownHitPoint()がOverrideされていて、HPが偶数の時さらにHPが１下がるという事
        // downHipPoint()は上記同様3回呼ばれているので、加えて-2され５になる
        // さらに鳴き声はnya-になっている...笑
        //
        // 答え：正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_6th_overriddenWithoutSuper() {
        Animal animal = new Zombie();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => uooo
        int land = animal.getHitPoint();
        log(land); // your answer? => -1
        // 背景：鳴き声(getBarkWord)は、uoooを返すようにOverrideされている
        // constructor内で呼ばれるgetInitialHitPoint()がOverrideされて、-1が返されるようになっている（ゾンビだからか）
        //
        // 答え：正解でした。
    }

    /**
     * What is happy if you can assign Dog or Cat instance to Animal variable? <br>
     * (Animal型の変数に、DogやCatなどのインスタンスを代入できると何が嬉しいのでしょう？)
     */
    public void test_objectOriented_polymorphism_7th_whatishappy() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // what is happy?
        //
        // 依存性の低さによる、設計の柔軟性やテストのしやすさだったり使い回しや簡単な置き換えが実現できることがPolymorphismの利点だと思っている。
        // 上にもあるとおり、メソッドの引数をAnimal animalにすることで異なるサブクラスを渡せたり、
        // List<Animal> animalsというリストを作って、Appendし、ループで呼び出せたり...などなど
        // サブクラスが増える場合も含めて、安全に「使いまわせて」拡張性が高いというのを実現できる
        // さらに、Animal animalしておくことで右側がDogからCatに置き換わっても修正量が減る。
        // 逆に：依存性が高いと拡張や修正するときのコストが高くなる
        // 例えば：コードやテストがDogだけに依存していると、Dogが廃止になってDog2とかで書き換えないといけない時に全て書き換えないといけなくなる
        //
        // けど...done jflute サブクラスの独自メソッドを定義した場合（Overrideではない）、
        // スーパークラスからは呼び出せないということが起きる。
        // なので設計的にはそういうことをしないというのがベストプラクティスだったりするんですか？
        // [1on1でのふぉろー] 具象のメソッドを呼ぶ必要があるなら具象に依存してるってことなのでそもそも抽象的に扱う必要がない。
        // 具象のメソッドを呼ばず抽象クラスのメソッドで事足りるのであれば、それは抽象クラスにだけ概念的に依存してるということ。
        // 実際、抽象概念にだけ依存していれば良いケースというのが多いので、そのときは抽象的に扱いましょうという感じ。
        // _/_/_/_/_/_/_/_/_/_/
    }

    // ===================================================================================
    //                                                              Polymorphism Interface
    //                                                              ======================
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_dispatch() {
        Loudable loudable = new Zombie();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => uooo
        String land = ((Zombie) loudable).bark().getBarkWord();
        log(land); // your answer? => uooo
        // まず、Loudableはインターフェース
        // インターフェースはClassとちょっと似てて型として左側に定義できる
        // また、インターフェースは振る舞いを定義するものであり、メソッドしか定義できない（Abstract Classとはちょっと違う）
        // Animalクラスをみてみると、ImplementsでLoudableが実装されている。
        // 実装では、soundLoudly()関数がOverrideされていて、barksoundをGetするメソッドとなっている
        // したがって、seaの中身はuooになる
        // また、下の部分も本質的にやっていることは一緒でloudableをZombieにキャスティングすることで、soundLoudlyの内部実装と同じことをして、barksoundを返している
        // したがって、landの中身もuooになる
        //
        // 答え：正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_hierarchy() {
        Loudable loudable = new AlarmClock();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => jiri jiri..
        boolean land = loudable instanceof Animal;
        log(land); // your answer? => false
        // AlarmClockはLoudableを実装してて独自のsoundLoudly関数が定義されている
        // だが、AlarmClockはAnimalのインスタンスではないのでlandはFalseになる
        //
        // 答え：正解でした。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_partImpl() {
        Animal seaAnimal = new Cat();
        Animal landAnimal = new Zombie();
        boolean sea = seaAnimal instanceof FastRunner;
        log(sea); // your answer? => true
        boolean land = landAnimal instanceof FastRunner;
        log(land); // your answer? => false
        // CatクラスとZombieクラスをみてみると、それぞれFastRunnerインターフェースを継承している・していない
        // Catはしているのに対して、Zombieはしていないので、seaはTrueになり、landはFalseになる
        //
        // 答え：正解でした。
    }

    /**
     * Make Dog class implement FastRunner interface. (the method implementation is same as Cat class) <br>
     * (DogもFastRunnerインターフェースをimplementsしてみましょう (メソッドの実装はCatと同じで))
     */
    public void test_objectOriented_polymorphism_interface_runnerImpl() {
        // your confirmation code here
        Animal dog = new Dog();
        log(dog instanceof FastRunner); // → should be true

        log(dog.getHitPoint()); // → should be 10

        FastRunner fastRunner = (FastRunner) dog;
        fastRunner.run();

        log(dog.getHitPoint()); // → should be 9
    }

    /**
     * What is difference as concept between abstract class and interface? <br>
     * (抽象クラスとインターフェースの概念的な違いはなんでしょう？)
     */
    public void test_objectOriented_polymorphism_interface_whatisdifference() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // what is difference?
        // 概念的には「抽象的なモノ」であるか、「振る舞い」であるかが違いかなと思っています。
        // つまり抽象的なモノをを継承するということは、"is-a"の関係だと思っていて、だからabstract classから一度に一つしか継承できないと思っています。
        // この場合、設計の粒度にも関わってきますが、「車」と「電車」というabstract classがあって、is a car and is a trainっていうのはおかしい。
        // あと「何が」の情報を持っている。「車」でいうと部品だったり、人でいうと「年齢」だったり...
        // （抽象）クラスに名詞が使われるのもこういう所から来ているのかなと思っています。
        // 反対にインターフェースは共通振る舞いを定義するもの（モノって言っちゃってますが、日本語難しい）っていうのは、「何が」は関係なく「何を」を表すもの。
        // つまり「動く生物」っていうものであれば、「呼吸」とか「走る」とかが振る舞いで、年齢がどうとか部品がどうとか関係ない概念を表している。
        // だからインターフェースには動詞が使われることが多いのかなって思ってます（Serializable, Clonableなどなど）。
        // _/_/_/_/_/_/_/_/_/_/
        // [1on1でのふぉろー] Javaの多重継承なくてinterfaceとハイブリッドなお話。
    }
    // done jflute 1on1にて、Javaでのinterfaceの使われ方、大きく二つのパターンについて話す (2024/12/25)
    // HitPointAction にて話した。

    // TODO jflute 次回ここから (2024/12/25)
    // ===================================================================================
    //                                                                 Polymorphism Making
    //                                                                 ===================
    /**
     * Make concrete class of Animal, which is not FastRunner, in "objanimal" package. (implementation is as you like) <br>
     * (FastRunnerではないAnimalクラスのコンクリートクラスをobjanimalパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeConcrete() {
        // your confirmation code here
        Animal dragon = new Dragon(true);
        BarkedSound sound = dragon.bark();
        String sea = sound.getBarkWord();
        log(sea); // should be bobobobobo....
        int land = dragon.getHitPoint();
        log(land); // should be 296
    }

    /**
     * Make interface implemented by part of Animal concrete class in new package under "objanimal" package. (implementation is as you like) <br>
     * (Animalクラスの一部のコンクリートクラスだけがimplementsするインターフェースをobjanimal配下の新しいパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeInterface() {
        // your confirmation code here
        Animal dragon = new Dragon(true);
        log(dragon instanceof Flyable); // should be true
        ((Flyable) dragon).fly();

        Animal youngDragon = new Dragon(false);
        ((Flyable) youngDragon).fly();
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Extract St6MySql, St6PostgreSql (basic.st6.dbms)'s process to abstract class (as super class and sub-class) <br>
     * (St6MySql, St6PostgreSql (basic.st6.dbms) から抽象クラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_generalization_extractToAbstract() {
        // your confirmation code here
        St6Sql mySql = new St6MySql();
        log(mySql.buildPagingQuery(10, 1)); // should be limit 0, 10
        St6Sql postgreSql = new St6PostgreSql();
        log(postgreSql.buildPagingQuery(10, 1)); // offset 0 limit 10
    }

    /**
     * Extract St6OperationSystem (basic.st6.os)'s process to concrete classes (as super class and sub-class) <br>
     * (St6OperationSystem (basic.st6.os) からコンクリートクラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_specialization_extractToConcrete() {
        // your confirmation code here
        St6OperationSystem os = new MacOperatingSystem("shiny");
        log(os.buildUserResourcePath("sea")); // should be /Users/shiny/sea
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Extract Animal's bark() process as BarkingProcess class to also avoid large abstract class. <br>
     * (抽象クラス肥大化を抑制するためにも、Animalのbark()のプロセス(処理)をBarkingProcessクラスとして切り出しましょう)
     */
    public void test_objectOriented_writing_withDelegation() {
        // your confirmation code here
        boolean isDragonGrown = true;
        Animal dragon = new Dragon(isDragonGrown);
        dragon.bark();
        log(dragon.getHitPoint()); // should be 296
    }

    /**
     * Put barking-related classes, such as BarkingProcess and BarkedSound, into sub-package. <br>
     * (BarkingProcessやBarkedSoundなど、barking関連のクラスをサブパッケージにまとめましょう)
     * <pre>
     * e.g.
     *  objanimal
     *   |-barking
     *   |  |-BarkedSound.java
     *   |  |-BarkingProcess.java
     *   |-loud
     *   |-runner
     *   |-Animal.java
     *   |-Cat.java
     *   |-Dog.java
     *   |-...
     * </pre>
     */
    public void test_objectOriented_writing_withPackageRefactoring() {
        // your confirmation code here
    }

    /**
     * Is Zombie correct as sub-class of Animal? Analyze it in thirty seconds. (thinking only) <br>
     * (ゾンビは動物クラスのサブクラスとして適切でしょうか？30秒だけ考えてみましょう (考えるだけでOK))
     */
    public void test_objectOriented_zoo() {
        // write your memo here:
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // is it corrent?
        // うーん多少複雑性によるとは思いますが、適切ではないと思います。
        // クラスとサブクラスの関係はis-aの関係であるべきだと思うので、ゾンビは動物か？と言われたらまず概念的にそうでないと思います。
        // 一般的に動物にある特性（や場合によって振る舞い）というのはゾンビに対しては、適応しないものが多い気がしている。
        // このエクササイズの例でもHPという特性はそもそもゾンビに必要ではない。
        // これがもっと複雑な実装になれば、「ゾンビにこれはないので、こう書き変えて、これはいらなくて...」みたいなのが沢山出てくる予感。
        // _/_/_/_/_/_/_/_/_/_/
    }
}
