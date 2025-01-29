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
package org.docksidestage.javatry.basic.st6.os;

// done shiny せっかくなので自分の名前を追加で by jflute (2025/01/15)

import static java.rmi.server.LogStream.log;

// TODO shiny unused import: java.rmi.server by jflute (2025/01/29)

/**
 * @author jflute
 * @author shiny
 */
public abstract class St6OperationSystem {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    // done shiny もうunusedなので削除しちゃって大丈夫です by jflute (2025/01/15)

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final String osType;
    private final String loginId;
    // done jflute 親クラスで定義されたAttributeに子クラスからアクセスする方法はでprotectedを使うのってどうなんだろうという疑問。(あまりみたことない)
    // done shiny [へんじ] 親クラスと子クラスの独立性/依存性に寄るかなと by jflute (2025/01/05)
    //
    // 例えば、親クラスがライブラリに入ってて、子クラスは現場で作るとかであれば、
    // 独立性は高く依存性は薄い方が良いので、親のAttributeをあまり見せないほうが良いかなと。
    //
    // 一方で、同じライブラリで親も子も閉じるような構造の場合、独立性は低く依存性は高くても許容できるので、
    // 直接見させちゃえってのはある。
    // もちろんその場合でも丁寧にgetter経由で提供するようにするってのも良いけど、
    // このAttributeの利用コードを探すってときに変数とgetter両方で検索しないといけないとかあるから、
    // ぼくはわりとprotectedで見せちゃうことよくある。
    //
    // 一方で一方で、独立性は高く依存性は薄い方が良いパターンでも、
    // 「privateで定義されててprotectedのgetterがなくてサブクラスが拡張できないで困る」
    // ってこともよくあるので、自分はそれだったらprotectedで公開しちゃう。
    // とはいえ、それも「内部構造をどれだけ守りたいか？」次第でケースバイケースだね。
    //
    // [1on1でのふぉろー] そのクラスがどう提供されるか？ライブラリの規模は？運用で大事にしているものは？
    // そのへんに寄ってセオリーは変わってきます。

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public St6OperationSystem(String osType, String loginId) {
        this.osType = osType;
        this.loginId = loginId;
    }

    // ===================================================================================
    //                                                                      User Directory
    //                                                                      ==============
    public String buildUserResourcePath(String relativePath) {
        String fileSeparator = getFileSeparator();
        String userDirectory = getUserDirectory();
        String resourcePath = userDirectory + fileSeparator + relativePath;
        return resourcePath.replace("/", fileSeparator);
    }

    protected abstract String getFileSeparator();

    protected abstract String getUserDirectory();

    // ===================================================================================
    //                                                                            Accessor
    //
    public String getOsType() {
        return osType;
    }

    public String getLoginId() {
        return loginId;
    }
}
