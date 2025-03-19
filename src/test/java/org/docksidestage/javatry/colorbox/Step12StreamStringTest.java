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
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, using Stream API you can. <br>
 * Show answer by log() for question of javadoc.
 * <pre>
 * addition:
 * o e.g. "string in color-boxes" means String-type content in space of color-box
 * o don't fix the YourPrivateRoom class and color-box classes
 * </pre>
 * @author jflute
 * @author shiny
 */
public class Step12StreamStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * What is color name length of first color-box? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() { // example, so begin from the next method
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String answer = colorBoxList.stream()
                .findFirst()
                .map(colorBox -> colorBox.getColor().getColorName())
                .map(colorName -> colorName.length() + " (" + colorName + ")")
                .orElse("*not found");
        log(answer);
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMax_colorSize() {
        // [1on1でのふぉろー] max()のところの文法的な解釈を細かく見てみた。
        // 途中で、型が変わっているところもポイント、読むときに一個前の戻り値を意識しないと。
         List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
         String answer = colorBoxList.stream()
                 .map(colorBox -> colorBox.getColor().getColorName())
                 .max(Comparator.comparingInt(String::length))
                 .orElse("*not found");
         log(answer);

         // 一方で、ここはデザインが発生するところで... by jflute
         // こういうふうに分離するデザインもあるかも (選択肢ある)
         // Optional<String> optMax = colorBoxList.stream()
         //         .map(colorBox -> colorBox.getColor().getColorName())
         //         .max(Comparator.comparingInt(String::length));
         // log(optMax.orElse("*not found"));
          
          // stream()というおまじないに関する質問から派生してEclipse Collectionsの紹介
          // 逆に Eclipse Collections を知ることで、stream() のおまじないの特徴がわかった。
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax_stringContent() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(BoxSpace::getContent)
                .filter(content -> content instanceof String)
                .map(content -> (String) content)
                .max(Comparator.comparingInt(String::length))
                .orElse("*not found");
        log(answer);
    }

    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (latter if same length) <br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (同じ長さのものがあれば後の方を))
     */
    public void test_length_findSecondMax_contentToString() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        // TODO shiny reversed()後のnaturalOrderは無くても結果は変わらないっぽい？ by jflute (2025/03/12)
        String answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(BoxSpace::getContent)
                .filter(Objects::nonNull)
                .map(Object::toString)
                .sorted(Comparator.comparingInt(String::length).reversed().thenComparing(Comparator.naturalOrder()))
                .skip(1)
                .findFirst()
                .orElse("*not found");
        log(answer);
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(BoxSpace::getContent)
                .filter(content -> content instanceof String)
                .mapToInt(content -> ((String) content).length())
                .sum();
        log(answer);
    }

    // ===================================================================================
    //                                                                      Pickup Methods
    //                                                                      ==============
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(BoxSpace::getContent)
                .filter(content -> content instanceof String)
                .filter(content -> ((String) content).startsWith("Water"))
                .findFirst() // 最初のやつに割りキリ
                .map(contentWithWater -> colorBoxList.stream()
                        .filter(colorBox -> colorBox.getSpaceList().stream()
                                .map(BoxSpace::getContent)
                                .filter(Objects::nonNull)
                                .anyMatch(content -> content.equals(contentWithWater)))
                        .map(colorBox -> colorBox.getColor().getColorName())
                        .findFirst() // 論理的には必ず存在する (Waterは見つかってるわけだから)
                        .orElse("*not found") // なのでここは万が一処理 (実際に実行されない)
                ).orElse("*not found"); // そもそもWaterが無かった場合など
        log(answer);

        // [1on1でのふぉろー] こうも書ける？ by jflute (2025/03/12)
        //String jfluteAns = colorBoxList.stream()
        //      .filter(colorBox -> colorBox.getSpaceList().stream()
        //              .map(BoxSpace::getContent)
        //              .filter(content -> content instanceof String)
        //              .map(content -> (String) content)
        //              .anyMatch(content -> content.startsWith("Water"))) // ここ！
        //      .findFirst() // 最初のやつに割りキリ
        //      .map(colobBox -> colobBox.getColor().getColorName())
        //      .orElse("*not found");
        //log(jfluteAns);
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(BoxSpace::getContent)
                .filter(content -> content instanceof String)
                .filter(content -> ((String) content).chars().filter(chr -> chr == 'ど').count() >= 2)
                .mapToInt(str -> ((String) str).lastIndexOf('ど') + 1)
                .findFirst()
                .orElse(-1);
        if (answer > 0) {
            log(answer);
        } else {
            log("*not found");
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
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(BoxSpace::getContent)
                .filter(content -> content instanceof YourPrivateRoom.GuardianBox)
                .map(content -> (YourPrivateRoom.GuardianBox) content)
                .peek(guardianBox -> {
                    guardianBox.wakeUp();
                    guardianBox.allowMe();
                    guardianBox.open();
                })
                .mapToInt(guardianBox -> {
                    try {
                        return guardianBox.getText().length();
                    } catch (YourPrivateRoom.GuardianBoxTextNotFoundException ignored) {
                        return 0;
                    }
                })
                .sum();
        log(answer);
        // [1on1でのふぉろー] ちょっとLambdaがでかくなったら、privateメソッド化するというテクニックある話
        //
        // 加えて、Javaだと極端な関数思考ではないので、前後気にせず読めるLambda引数名が嬉しい話
        // shinyさんの引数名はわかりやすい。
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> answers = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(BoxSpace::getContent)
                .filter(content -> content instanceof Map)
                .map(content -> ((Map<?, ?>) content).entrySet().stream()
                        .map(entry -> entry.getKey() + " = " + entry.getValue())
                        .collect(Collectors.joining(" ; ", "map:{ ", "}"))
                )
                .collect(Collectors.toList());

        if (answers.isEmpty()) {
            log("*not found");
            return;
        }

        for (String answer : answers) {
            log(answer);
        }
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> answers = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(BoxSpace::getContent)
                .filter(content -> content instanceof Map)
                .map(content -> (Map<?, ?>) content)
                .map(this::formatMap)
                .collect(Collectors.toList());
        if (answers.isEmpty()) {
            log("*not found");
            return;
        }

        for (String answer : answers) {
            log(answer);
        }
    }

    private String formatMap(Map<?, ?> map) {
        return map.entrySet().stream()
                .map(entry -> entry.getKey() + " = " + ((entry.getValue() instanceof Map) ? formatMap((Map<?, ?>) entry.getValue()) : entry.getValue()))
                .collect(Collectors.joining(" ; ", "map:{ ", "}"));
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    // has small #adjustmemts from ClassicStringTest
    // comment out because of too difficult to be stream?
    ///**
    // * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
    // * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
    // */
    //public void test_parseMap_flat() {
    //}
    //
    ///**
    // * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
    // * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
    // */
    //public void test_parseMap_nested() {
    //}
}
