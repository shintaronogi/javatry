package org.docksidestage.bizfw.basic.objanimal;

/**
 * @author shiny
 */
public interface HitPointAction {
    // done jflute 自分の認識だと関数型インターフェースだと、Lambda式を直接渡せるが、メソッドが増えると渡せなくなる。
    //  なのでインターフェース設計において、匿名クラス・具象クラス・複数の単一インターフェースなどどういう場面で何を使うべきかのコツが知りたいです！
    // done jflute 1on1にてインターフェース設計について話すぞぅ (2025/01/05)
    // [1on1ふぉろー] Lambda式出てきて、インターフェースを分割することが多くなった。それは、Lambda式が使えるから。
    // その代わり、引数は増えます。(分割した分) e.g. sea(() -> xxx, () -> yyy);
    // まあ、そういうデザイン全然悪くはないけど...インターフェースの概念をきっちり分解して定義できるか？ってところもある。
    // その分解が曖昧だと、とあるときにやはりもう一個メソッド追加したくなったり。かといって別のインターフェースにするときに名前が困るとか。
    // 個人的には、3つ4つになってきたら、もう別にLambda式にこだわらなくていいんじゃない？とは思う。
    //
    // コールバックで使う想定のインターフェースなのかどうか？にも寄る
    // インターフェースの使われどころの大きな２つのパターン:
    // o 1機能に特化したインターフェース、名前が -er, -able とかになりがち e.g. HitPointAction も悪くない!?
    //   (いや、HitPointActionって名前だと、将来回復するメソッドを追加したくなっちゃうかも)
    //   (なので、Lambda式で呼ばれることにこだわる場合は、もうちょい名前を特化させた方が良いかも)
    //
    // o オブジェクト指向とコラボして概念をインターフェース化したインターフェース
    //   e.g. AbstractColorBox implements ColorBox
    //   (第三者が呼べるメソッドの一覧性、内部的なpublicを作りやすい)
    //
    void downHitPoint();
}