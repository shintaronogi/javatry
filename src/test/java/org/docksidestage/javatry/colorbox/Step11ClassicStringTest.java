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
package org.docksidestage.javatry.colorbox;

import java.util.*;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.impl.CompactColorBox;
import org.docksidestage.bizfw.colorbox.impl.StandardColorBox;
import org.docksidestage.bizfw.colorbox.parser.ColorBoxParser;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, not using Stream API. <br>
 * Show answer by log() for question of javadoc. <br>
 * <pre>
 * addition:
 * o e.g. "string in color-boxes" means String-type content in space of color-box
 * o don't fix the YourPrivateRoom class and color-box classes
 * </pre>
 * @author jflute
 * @author shiny
 */
public class Step11ClassicStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * How many lengths does color name of first color-boxes have? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() { // example, so begin from the next method
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            ColorBox colorBox = colorBoxList.get(0);
            BoxColor boxColor = colorBox.getColor();
            String colorName = boxColor.getColorName();
            int answer = colorName.length();
            log(answer + " (" + colorName + ")"); // also show name for visual check
        } else {
            log("*not found");
        }
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMax_colorSize() {
        // TODO jflute: 実装する時は、普段画面に向かってぼやきながらコードを書いているのでそれをそのまま書きます
        //  「こういう時はもっとこう考えた方がいいよ」などあればぜひ聞きたいです！
        // 【シャイニーのつぶやき】
        // 前提：カラーボックスはリストなので、理論上名前が同じものが複数存在する可能性はあるが、
        // 問題文的に「which color name」なので一つと仮定して進める。
        // （二つ以上あれば、最初のものを返す）
        String longestColorName = "";

        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        if (colorBoxList.isEmpty()) {
            log("ColorBox is empty. Therefore the longest color name cannot be found. ");
            return;
        }

        for (ColorBox colorBox : colorBoxList) {
            BoxColor boxColor = colorBox.getColor();
            String colorName = boxColor.getColorName();
            if (colorName.length() > longestColorName.length()) {
                longestColorName = colorName;
            }
        }

        log(longestColorName + " (" + longestColorName.length() + ")");
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax_stringContent() {
        // 【シャイニーのつぶやき】
        // カラーボックスに入ってる文字列って何をさしているんだろう...?
        // カラーボックス自体のtoString()？ってそんなシンプルではないかな。
        // Reflectionとかを使うべきなのか...?（急に出てくる変なアイデア）
        // いや、普通にインターフェース・クラスの構成をみてみよう
        //　構成的には、ColorBoxというInterfaceがあって、BoxColor, BoxsSize, getSpaceListの三つのGetter Methodがある。
        // つまり、カラーボックスに「入ってる」ものはこの三つであるとと解釈できる。
        // Listはどうなんだと一瞬なりそうだけど、Listの中身はListに入っているもので、それをカラーボックスの中身と定義すると、
        // 他の二つと抽象度が変わってくるので、ListはList単体の大きさと考えられる（toStringあるし）
        // 問題文の解釈が難しい...
        // うーんいや、待てよ。もう一回Interfaceの定義をみてみよう。
        // getSpaceList()のJavadocに書いてあるのは..."@return The list of space, which has content of color-box, and ordered from upper. (NotNull, NotEmpty)"
        // なるほど、つまりカラーボックスに入ってるもの（Content）とはこれか！BoxSpaceに入っているものがContentだな。うん、そうに違いない。（間違ってても怒らないでええ）
        // BoxSpaceに入ってるContentは偉大なる父Object型かー...なんでも入ってる可能性はあるな
        // いや、でも問題文では「文字列の中で」と明確に書いてあるので、文字列型以外のContentは一旦無視して大丈夫そう
        //
        // 追記：一番上に「"string in color-boxes" means String-type content in space of color-box」て書いてた（汗

        String maxStringContent = "";

        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        if (colorBoxList.isEmpty()) {
            log("ColorBox is empty. Therefore the longest color name cannot be found. ");
            return;
        }

        for (ColorBox colorBox: colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();

            // 【シャイニーのつぶやき】
            // うーんここでSpaceListをIterateするとダブルループになるから計算量はO(n^2)になるかぁ
            // フラットなリストにするにすると、今度は空間がO(n^2)になるし、そこからソートするにしても計算量O(n log n)か
            // ダブルループだけど、Stringを一個確保するだけなら空間消費はO(1)。
            // Setにしても結局比較するのは文字列の長さだから基本的に変わらないか
            // もっと良い方法があるのかもしれないけど、思いつかないのでこのままダブルループを採用する！(もっと良い方法があればごめん、未来の僕)
            // TODO done shiny [ふぉろー] 自分も思いつかないですね(^^ by jflute (2025/02/13)
            // これが何度も繰り返されるのであれば、構造化したオブジェクトに保持しておいて最短で探すってできますが、
            // 一発ものを想定したら、nested loop するしかないかなと。
            for (BoxSpace boxSpace : boxSpaceList) {
                Object content = boxSpace.getContent();
                // 【シャイニーのつぶやき】
                // Content自体はnull-allowedみたいだ
                // えーっとでもcontent instanceof StringでNullじゃないことが保証できるので明示的なNullチェックは必要ない
                if (content instanceof String && ((String) content).length() > maxStringContent.length()) {
                    maxStringContent = (String) content;
                }
            }
        }

        log(maxStringContent + " (" + maxStringContent.length() + ")");
    }

    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (latter if same length) <br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (同じ長さのものがあれば後の方を))
     */
    public void test_length_findSecondMax_contentToString() {
        // 【シャイニーのつぶやき】
        // 文字列以外はtoString()って問題文に書いてあるので、さっきの問題でBoxSpaceの文字列だけをみていたのはあってるっぽいぞ。
        // TODO done shiny [ふぉろー] yes by jflute (2025/02/13)

        String maxContentToString = "";
        // 【シャイニーのつぶやき】
        // "second max"とは...?ってなるけど、意味はわかるので一旦スルー。
        // TODO done shiny [ふぉろー] たしかに！ by jflute (2025/02/13)
        String secondMaxContentToString = "";

        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        if (colorBoxList.isEmpty()) {
            log("ColorBox is empty. Therefore the longest color name cannot be found. ");
            return;
        }

        for (ColorBox colorBox: colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();

            // 【シャイニーのつぶやき】
            // さっき同様ダブルループの空間消費量O(1)で対処
            for (BoxSpace boxSpace : boxSpaceList) {
                Object content = boxSpace.getContent();
                if (Objects.isNull(content)) {
                    continue;
                }

                String contentToString = content.toString();
                // 【シャイニーのつぶやき】
                // 業務で実装するならテストを絶対書きたい所だ...凡ミスを起こしそう
                // といってもこれがテストなので、パターンだけちゃんと書いておく
                // ケース1: contentの長さが現在一番長いやつより長い場合（もしくは一緒の場合）：
                // 1. content, 2. 現在一番ながいやつ (問題文では二番目のやつは後のほうという言及があるので、ここでも「後の方を優先」で一貫性を保つ)
                // ケース2: contentの長さが現在一番ながいやつより小さくて、現在二番目にながいやつより長い場合（もしくは一緒の場合）：
                // 1. 現在一番ながいやつ、2. content
                // ケース３：
                // contentの長さが現在二番目のやつより短い場合（それ以外）：
                // なにもしない
                // これで大丈夫なはず！
                // TODO done shiny [ふぉろー] 大丈夫だと思います！ by jflute (2025/02/13)
                if (contentToString.length() >= maxContentToString.length()) {
                    secondMaxContentToString = maxContentToString;
                    maxContentToString = contentToString;
                } else if (contentToString.length() >= secondMaxContentToString.length()) {
                    secondMaxContentToString = contentToString;
                }
            }
        }

        log(secondMaxContentToString + " (" + secondMaxContentToString.length() + ")");
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        // 【シャイニーのつぶやき】
        // 前提：ここも二つ前のエクササイズと一緒で「文字列型」のもののみを対象とする
        int lengthSum = 0;

        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        if (colorBoxList.isEmpty()) {
            log("ColorBox is empty. Therefore the sum of length is obviously: 0");
            return;
        }

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();

            for (BoxSpace boxSpace: boxSpaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof String) {
                    lengthSum += ((String) content).length();
                }
            }
        }

        log("Sum of total lengths of strings in color-boxes is: " + lengthSum);
    }

    // ===================================================================================
    //                                                                      Pickup Methods
    //                                                                      ==============
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        final String firstWord = "Water";

        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        if (colorBoxList.isEmpty()) {
            log("ColorBox is empty. Therefore the box color of the box containing a string that starts with Water is not defined");
            return;
        }

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();

            for (BoxSpace boxSpace: boxSpaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof String && ((String) content).startsWith(firstWord)) {
                    log("The color of the box containing the string that starts with \"Water\" is: " + colorBox.getColor());
                    return;
                }
            }
        }
    }

    /**
     * What number character is starting with the late "ど" of string containing two or more "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        // 【シャイニーのつぶやき】
        // 前提：ここも「ど」を含む文字列が一つしかないと仮定
        // 何文字目から始まるか...カウントの仕方的にIndexというよりIndex + 1かな。
        // えーっと処理を簡潔化したいので...
        // とりあえず大前提さっきと同じくContentをループしていって確認する必要はある
        // おおまかにチェックしなきゅいけないことは３つで：
        // 1. 文字列型であること（nullではない）
        // 2. 「ど」を2回含むこと
        // 3. 最後の「ど」のindex + 1
        // 文字列型のチェックはいいとして、「ど」を2回含むかー...Javaの標準のString.contains()は引数一つに対して含んでるか含んでないかをBoolで返すだけだからだめ。
        // 一個あるのが確認できたら、Substringで削るみたいな考え方かな。
        // その場合でも、Containsで確認してからIndex取得するという二度手間がかかるのでやめたい。
        // ちょっと調べた：そもそもindexOf()はみつからなかったら-1を返すのでcontainsわざわざ必要ないか。
        // 開始Indexも指定できるのでSubstringで削る必要もない。
        // 正規表現っていうのもありかもしれない。が↑の方がシンプルにできそうなのでこれで実装してみる。

        String findWord = "ど";
        boolean found = false;

        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        if (colorBoxList.isEmpty()) {
            log("ColorBox is empty. Therefore no box has a string containing the word " + findWord + "at least two times");
            return;
        }

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();

            for (BoxSpace boxSpace : boxSpaceList) {
                Object content = boxSpace.getContent();

                // 【シャイニーのつぶやき】
                // さっきと違って処理がちょっと複雑化しそうなので、Stringじゃなければ早期Return
                // というかContinue
                if (!(content instanceof String)) {
                    continue;
                }

                int count = 0; // 「ど」が何回でるかのカウンター
                int currentIndex = 0; //  ↑で述べた通り、文字列を区切って調べていくので現在のIndexを保持
                int lastIndex = 0; // 答えとなる最後のIndex　

                // TODO done shiny [ふぉろー] おお、自力 lastIndexOf() だ、すごい。 by jflute (2025/02/13)
                // これはこれでトレーニングになるので良いです。一応、lastIndexOf() があるのでそれで代用できます。
                // へんしん：お、さすがにありましたか...笑 よく考えてみればめっちゃありそうなメソッドではありました。
                while ((currentIndex = ((String) content).indexOf(findWord, currentIndex)) != -1) {
                    count++;
                    lastIndex = currentIndex;
                    currentIndex++;
                }

                if (count >= 2) {
                    // 【シャイニーのつぶやき】
                    // これを関数として切り出すとしたら別のReturnの仕方ができるけど、
                    // そうでなくてここで簡潔してるので、フラグを作って見つからなかった場合ログにだそう。
                    // 業務によってはExceptionでもいいのかもしれない。
                    found = true;
                    int characterNum = ++lastIndex;
                    log("The last \"ど\" of the string starts at the number: " + characterNum);
                }
            }
        }

        if (!found) {
            log("Unfortunately, no word with at least containing two \"ど\" could've been found");
        }
    }

    // ===================================================================================
    //                                                                 Welcome to Guardian
    //                                                                 ===================
    /**
     * What is total length of text of GuardianBox class in color-boxes? <br>
     * (カラーボックスの中に入っているGuardianBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToGuardian() {
        // 【シャイニーのつぶやき】
        // GuardianBoxってなんだ...
        // 何もみずにColorBox Interface -> AbstractColorBoxを継承している新しいクラスかなーと思ったけど...「違った！」
        // YourPrivateRoomにある独自のStatic Classか
        // asGuardian()でInstanceを生成するみたい
        // 呼ばれている箇所的にcontentの中に入るってことか
        // プロパティがなんこある。Constructor内で初期化されるTextとBooleanが三つ。
        // そのBooleanをToggleする関数がそれぞれあって、順番に呼ばないとExceptionが投げられるのか
        // getText()でTextは取得できる。が↑の条件が揃ってないとだめ
        // toString()はあるが、呼ぶと"{Ha ha ha!}"が返ってくるwww
        //
        // まあなので、やることとしては今までと同じくColorBoxのリストからColorBoxとContentを取り出していって、
        // contentがGuardianBoxのインスタンスなら、BoolプロパティをToggleする関数を順番に読んでいき、
        // getText()でTextをGetして長さを足していく
        // 唯一気をつけないといけないのが、text == nullの場合で、独自のExceptionが投げられるのでそれはキャッチする (Ignoreする)
        int textLengthSum = 0;

        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        if (colorBoxList.isEmpty()) {
            log("ColorBox is empty. Therefore the sum of the text in GuardianBox class is obviously: 0");
            return;
        }

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();

            for (BoxSpace boxSpace : boxSpaceList) {
                Object content = boxSpace.getContent();

                if (!(content instanceof YourPrivateRoom.GuardianBox)) {
                    continue;
                }

                // TODO done shiny 細かいですが、ここまで何度も使っていれば変数抽出お願いしたいところですね by jflute (2025/02/13)
                // (IntelliJの機能で一発でできると思うので)
                YourPrivateRoom.GuardianBox guardianBox = (YourPrivateRoom.GuardianBox) content;
                guardianBox.wakeUp();
                guardianBox.allowMe();
                guardianBox.open();

                try {
                    String text = ((YourPrivateRoom.GuardianBox) content).getText();
                    textLengthSum += text.length();
                } catch (YourPrivateRoom.GuardianBoxTextNotFoundException ignored) {
                    // Catch and Ignore Exception since textLength is 0 in this case
                    // TODO done shiny [いいね] ignored という変数名もとても良いし、コメントも良い by jflute (2025/02/13)
                }
            }
        }

        log("The total length of text of GuardianBox class in color-boxes is: " + textLengthSum);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
        // 【シャイニーのつぶやき】
        // 問題の解釈としては、ContentがMap型の場合、表示形式を変えたいってことかな
        // java.util.Mapの標準のtoString()出力は、{key1=value1, key2=value2, key3=value3}
        // だから別のフォーマットで出力しましょうねということか
        // 独自クラスないでオーバーライドするのではなくて、あくまでMap型のままでFormatを変える
        // 専用のSingletonFormatterClassが欲しいところだけど、ここで簡潔してるのでこの中で処理を書く
        // トリプルForループにはなるけど、Contentの型がMap＜＞だったら、Map内のEntryをループして, key = valueと表示させる
        // 最初にmap: も必要か...MapがEmptyの場合は、{}になればいいからEmptyの場合の考慮は必要なさそう
        // 区切り文字もセミコロンになるのか...うーん
        // StringBuilderとかを使う場合、最後に無駄なセミコロンを除去しなきゃいけなくなるかな...そういう処理はあまり書きたくない
        // なので、単純にStringをJoinして、Separatorを指定できれば一番よき
        // リストにまとめてJoinするか...?
        // ちょっと調べた：JavaにはStringJoinerっていうものがあるらしい! StringBuilderと基本似たような挙動だけど、separator, prefix, suffixを指定できる。
        // ということで、Mapの中身はPrefix, Suffix含めてこれで対応できそうなので、書いてみる
        // TODO done shiny [いいね] すんごい綺麗にできてる！ by jflute (2025/02/13)

        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        if (colorBoxList.isEmpty()) {
            log("ColorBox is empty. Therefore no content.");
            return;
        }

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();

            for (BoxSpace boxSpace : boxSpaceList) {
                Object content = boxSpace.getContent();

                if (!(content instanceof Map)) {
                    continue;
                }

                // TODO done shiny [いいね] StringJoiner でだいぶ簡単にできちゃうんだね笑 by jflute (2025/02/13)
                StringJoiner stringJoiner = new StringJoiner("; ", "map:{ ", " }");
                for (Map.Entry<?, ?> entry : ((Map<?, ?>) content).entrySet()) {
                    stringJoiner.add(entry.getKey() + " = " + entry.getValue());
                }
                log(stringJoiner.toString());
            }
        }
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
        // 【シャイニーのつぶやき】
        //　今回は、ValueがMapだった場合にさらにネストさせるってことか
        // あまりにもForループをネストしすぎていて、いやになってくる（ひぃぃぃぃ）
        // 実務であれば、これは別ロジックに切り出したい
        // いやもう絶対読めない自身あるし、ロジックは一緒なので切り出そう...
        // いや、待てよ。問題文的には二重のMapの表現だけになってるけど、これがもし３重、４重となる場合も含んでるとしたら？
        // 単純なForループだけでは解決できないので再起的によばないといけなくなる
        // つまり、絶対にきりださないと無理かな
        // TODO done shiny [いいね] これまたすんごい良くできてる！ by jflute (2025/02/13)

        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        if (colorBoxList.isEmpty()) {
            log("ColorBox is empty. Therefore no content.");
            return;
        }

        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();

            for (BoxSpace boxSpace : boxSpaceList) {
                Object content = boxSpace.getContent();

                if (!(content instanceof Map)) {
                    continue;
                }

                log(formatMap((Map<?, ?>) content));
            }
        }
    }

    private String formatMap(Map<?, ?> map) {
        StringJoiner stringJoiner = new StringJoiner(" ; ", "map:{ ", " }");
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (entry.getValue() instanceof Map) {
                // TODO done shiny [いいね] 再帰Good by jflute (2025/02/13)
                stringJoiner.add(entry.getKey() + " = " + formatMap((Map<?, ?>) entry.getValue()));
            } else {
                stringJoiner.add(entry.getKey() + " = " + entry.getValue());
            }
        }
        return stringJoiner.toString();
    }

    // TODO jflute ここから先は後でレビュー (2025/02/13)
    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    // these are very difficult exercises so you can skip
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_flat() {
        // 【シャイニーのつぶやき】
        // うーんなるほど、ちょっと難しそう。
        // でも最近業務でめちゃめんどくさいSerializer・Deserializerを独自実装結構やったのでこのまま解きたい！
        // とりあえずwhiteのColorboxのupperスペース入っているTextを取得してくる所までのコードを書く。
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        if (colorBoxList.isEmpty()) {
            log("ColorBox is empty. Therefore no content.");
            return;
        }

        // 【シャイニーのつぶやき】
        // まず問題文から流れをどうしようか考えてみる...（色んなやり方はあるけど）
        // 1. ColorboxがWhiteかそれ以外の場合があるので、その判別を一つのポイントとしたい
        // 2. 次にColorboxのUpperスペースをとってくるもの
        // 3. その次にそのBoxSpaceからSecretText（content）をとってくるもの
        // UpperSpaceというのはAbstractColorBoxを継承しているCompactColorBoxとStandardColorBoxがもってる独自メソッドで、
        // SpaceListからindex = 0のものをとってくる。SpaceList[]はNotEmptyである為取れないことはないと仮定（まーリストである以上保証はできないのでExceptionどこかでなげてもよかったかも）
        // 複数のSpaceがあるので、SpaceからSecretTextを取り出すものは別関数に切り出しておくと、再利用できる。
        // とりあえず、その二つのクラスどちらかのインスタンスである場合はCastしてメソッドを呼び出し、そうでない場合はOptional.empty()を返すのがextractUpperSpace()メソッドとしては責務にあってると思う
        // そこから、getContent()を呼び出す（nullの可能性はあり）、nullでない場合はcontentがSecretBox型なのを確認してからキャストして返す
        // 4. SecretBox(というかcontent : nullable)がEmptyでないことを確認してから、TextをGetする（ここはSecretBoxのコンストラクタないでnullでないことが保証されている）
        // という流れが良さそうなので一旦ここまで実装してみる
        for (ColorBox colorBox : colorBoxList) {
            if (!isWhiteColorBox(colorBox)) {
                continue;
            }

            Optional<BoxSpace> optionalBoxSpace = extractUpperSpace(colorBox);
            if (!optionalBoxSpace.isPresent()) {
                continue;
            }

            Optional<YourPrivateRoom.SecretBox> optionalSecretBox = extractSecretBoxFromBoxSpace(optionalBoxSpace.get());
            if (!optionalSecretBox.isPresent()) {
                continue;
            }

            String secretText = optionalSecretBox.get().getText(); // textがNullでないことはConstructorで保証されている
            // 【シャイニーのつぶやき】
            // よさそう！とりあえずSecretBoxからTextは取れた。
             log(secretText);

            // よーしここから本番なんですが...
            // まず一旦シンプルに考えてみよう （一次元のフラットなMapの文字列の場合）
            //
            // Mapかどうかを判断するにはmap: {...}ってなってればいい
            // Mapであることが判断できれば...
            // その中身を取り出すには、最初のmap: {をReplace（String.replaceFirst()とかで）して、
            // 最後の1文字}をSubstringで削るのはありかな。
            // Mapの中身は ; で区切られているので、" ; "でSplitすればそれぞれのKeyValuePairが取れる
            // KeyValueのペアは = で区切られているので、" = "でSplitすればKeyとValueの値の配列が取れる
            // それをMapにAddだっけPutだっけ（Javaでは）すればMap自体は得られそうである。
            // ...一旦脳みそでシュミレーション...
            // FlatなMapだったらそれでも行けそうだけど、Nestしてたら無理だな
            // ; でSplitしてる時点でさらにネストされたMapがあれば、その中身も区切られちゃうから中途半端な区切り方になってダメ
            //
            // ちゃんとパースしていくしかなさそう、うん。
            // まあ、一つ問題としてどこまで厳密にパースするかっていう話は大前提ある。
            // map: {..って形ではなくて、{だけだったら？とか
            // 要素と要素の間のスペースが空文字二つとかないとかだったら？パースエラーにするの？とか
            // 型情報はどうする？IntegerとかBoolだったらとか
            // 理論上キーがMapなこともあるけど（なんでとはなるが）
            // 前提WhiteのColorboxに入れてる文字列をみると、そこまで厳密にパースエラーにする必要はなさそうなのでできるだけシンプルに実装しようと判断。
            // 全てのMapはKey: String, Value: Object (Map or String)と仮定して進めよう。
            // ステップを考えていく...
            // 1. Mapかどうかの判断軸は"{"と"}"この二つの文字である。
            // つまり、文字列をまずパースしていって、{がくるまで続ける（まあなければMapではないね）
            // {と一致してるものが見つかれば、その中身はKeyValuePairの集合ってことになる
            // 2. Keyをパースする
            // Keyはこの場合、= がでてくるまでの文字列（trimはする）それをMapのKeyに詰めれる。
            // 3. Valueをパースする
            // パースしていって{が見つかればステップを戻る（再帰的に呼び出す）
            // じゃなければ文字列としてパースしてReturn
            // 4. ;がきたら２からパースを続け、}がきたら終わる
            // ちょっと想像を雑にかいてみたけど、こんな感じで進められそうなので一旦ざっと書いて、そこから関数に分けていく!
            //
            // 経過1. JavaにはClosureないよねえ...
            // 経過2. クラスに切り出さないのはやっぱきついかな...パースしていく中でいまどこにいるかのIndexをどうしても保持したくなる？ （意識的に副作用起こしたい...空文字のスキップとか）
            // 一旦クラスに切り出してみる
            // 経過3. Utils的な使い方がしたいので、newするのは使う側的にはだるい...Parser.toMap()てきによばるのがベストそう
            // 経過4.　うーん別にIndexを保持しなくても、関数の間で残りのParseするべき文字列を渡し会えばいけそう（関数的な考え方）
            // 経過5. とりあえず書けた！

            Map<String, Object> result = ColorBoxParser.toMap(secretText);
            log(result);
        }
    }

    private boolean isWhiteColorBox(ColorBox colorBox) {
        return colorBox.getColor().getColorName().equals("white");
    }

    private Optional<YourPrivateRoom.SecretBox> extractSecretBoxFromBoxSpace(BoxSpace boxSpace) {
        return Optional.of(boxSpace.getContent())
                .filter(content -> content instanceof YourPrivateRoom.SecretBox)
                .map(secretBox -> (YourPrivateRoom.SecretBox) secretBox);
    }

    private Optional<BoxSpace> extractUpperSpace(ColorBox colorBox) {
        if (colorBox instanceof StandardColorBox) {
            return Optional.of(((StandardColorBox) colorBox).getUpperSpace());
        } else if (colorBox instanceof CompactColorBox) {
            return Optional.of(((CompactColorBox) colorBox).getUpperSpace());
        } else {
            return Optional.empty();
        }
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        if (colorBoxList.isEmpty()) {
            log("ColorBox is empty. Therefore no content.");
            return;
        }

        for (ColorBox colorBox : colorBoxList) {
            if (!isWhiteColorBox(colorBox)) {
                continue;
            }

            // Lower Spaceの処理
            Optional<BoxSpace> optionalLowerSpace = extractLowerSpace(colorBox);
            if (!optionalLowerSpace.isPresent()) {
                continue;
            }

            Optional<YourPrivateRoom.SecretBox> optionalSecretBoxLowerSpace = extractSecretBoxFromBoxSpace(optionalLowerSpace.get());
            if (!optionalSecretBoxLowerSpace.isPresent()) {
                continue;
            }

            String secretTextLowerSpace = optionalSecretBoxLowerSpace.get().getText();
            log(secretTextLowerSpace);
            log(ColorBoxParser.toMap(secretTextLowerSpace));

            // Middle Space の処理
            Optional<BoxSpace> optionalMiddleSpace = extractMiddleSpace(colorBox);
            if (!optionalMiddleSpace.isPresent()) {
                continue;
            }

            Optional<YourPrivateRoom.SecretBox> optionalSecretBoxMiddleSpace = extractSecretBoxFromBoxSpace(optionalMiddleSpace.get());
            if (!optionalSecretBoxMiddleSpace.isPresent()) {
                continue;
            }

            String secretTextMiddleSpace = optionalSecretBoxMiddleSpace.get().getText();
            log(secretTextMiddleSpace);
            log(ColorBoxParser.toMap(secretTextMiddleSpace));
        }
    }

    private Optional<BoxSpace> extractMiddleSpace(ColorBox colorBox) {
        if (colorBox instanceof StandardColorBox) {
            return Optional.of(((StandardColorBox) colorBox).getMiddleSpace());
        } else {
            return Optional.empty();
        }
    }

    private Optional<BoxSpace> extractLowerSpace(ColorBox colorBox) {
        if (colorBox instanceof StandardColorBox) {
            return Optional.of(((StandardColorBox) colorBox).getLowerSpace());
        } else if (colorBox instanceof CompactColorBox) {
            return Optional.of(((CompactColorBox) colorBox).getLowerSpace());
        } else {
            return Optional.empty();
        }
    }
}
