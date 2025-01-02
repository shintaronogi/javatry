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

import org.docksidestage.bizfw.basic.objanimal.runner.FastRunner;

// TODO done shiny authorおねがいします〜 by jflute (2024/12/25)
/**
 * The object for dog(犬).
 * @author jflute
 * @author shiny
 */
public class Dog extends Animal implements FastRunner {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Dog() {
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    protected String getBarkWord() {
        return "wan"; // bow? in English
    }

    // TODO done shiny せっかくなのでタグコメントも入れて、Runカテゴリであると区切ってみてください by jflute (2024/12/25)
    // ===================================================================================
    //                                                                               Run
    //                                                                              ======
    @Override
    public void run() {
        downHitPoint();
    }
}
