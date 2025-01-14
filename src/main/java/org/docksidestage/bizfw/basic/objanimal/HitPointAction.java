package org.docksidestage.bizfw.basic.objanimal;

/**
 * @author shiny
 */
public interface HitPointAction {
    // TODO jflute 自分の認識だと関数型インターフェースだと、Lambda式を直接渡せるが、メソッドが増えると渡せなくなる。
    //  なのでインターフェース設計において、匿名クラス・具象クラス・複数の単一インターフェースなどどういう場面で何を使うべきかのコツが知りたいです！
    // TODO jflute 1on1にてインターフェース設計について話すぞぅ (2025/01/05)
    void downHitPoint();
}