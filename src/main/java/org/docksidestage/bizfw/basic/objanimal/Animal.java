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
package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.barking.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.barking.BarkingProcess;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The object for animal(動物).
 * @author jflute
 */
public abstract class Animal implements Loudable {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    // TODO done shiny unused by jflute (2025/01/15)
    // → 上のコメント自体も消しちゃってもですが、フォローコメントなど入ってるので残す。
    // [1on1でのフォロー] IntelliJ の idea ディレクトリについて。
    // https://dbflute.seasar.org/ja/manual/topic/friends/intellij/index.html

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected int hitPoint; // is HP

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Animal() {
        hitPoint = getInitialHitPoint();
    }

    protected int getInitialHitPoint() {
        return 10; // as default
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    public BarkedSound bark() {
        BarkingProcess barkingProcess = createBarkingProcess();
        String barkWord = getBarkWord();
        return barkingProcess.run(barkWord);
    }

    protected BarkingProcess createBarkingProcess() {
        // [1on1でのふぉろー] オブジェクト指向とコールバックのコラボレーション
        // オブジェクト指向の線引きは？ (結局コールバックもポリモーフィズム)
        return new BarkingProcess(() -> downHitPoint());
    }

    protected abstract String getBarkWord();

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    protected void downHitPoint() {
        --hitPoint;
        if (hitPoint <= 0) {
            throw new IllegalStateException("I'm very tired, so I want to sleep" + getBarkWord());
        }
    }

    // ===================================================================================
    //                                                                               Loud
    //                                                                              ======
    @Override
    public String soundLoudly() {
        return bark().getBarkWord();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getHitPoint() {
        return hitPoint;
    }
}
