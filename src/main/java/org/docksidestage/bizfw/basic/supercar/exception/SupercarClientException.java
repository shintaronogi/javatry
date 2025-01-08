package org.docksidestage.bizfw.basic.supercar.exception;

public class SupercarClientException extends RuntimeException {
    // done jflute RuntimeExceptionは通常3つのコンストラクタを持つと思いますが、カスタムのExceptionを作る時は、
    //  三つとも作るや要件に応じて特定のものだけ作るとかベストプラクティスはどうなんでしょう？
    // done shiny [へんじ] まあ線引はなくて曖昧ですが、それぞれのレイヤーの独立性そしてこの業務の重要性に寄りますね by jflute (2025/01/05)
    // 「一応レイヤーとして分かれてるけど、まあほぼ一緒に動くことがほとんど」という場合は共通の例外でもいいかなと。
    // 「それぞれのレイヤーがわりと独立して別のところから呼び出されることがある」という場合は個別の例外の方がベターであると。
    // 「なんにせよ、この業務がサービスの根幹で、わずかでもわかりやすくしておきたい」なら個別の例外の方がベターであると。
    // 例外クラスの存在自体はドキュメントみたいなものなので、これもアクセルをどこまで踏むか次第ですね。
    // javatryではアクセルを踏んでほしいなと(^^。(最大のやり方を知ってこそ省略できるの観点から)
    // done shiny ↑ごめん、これは別の話ですね。固有例外を作るかどうかの話をぼくが勘違いして書いてしまっています by jflute (2025/01/08)
    // 本題は、例外クラスのコンストラクタをどう作るか？という話ですね。
    // RuntimeExceptionはJavaAPI組み込みで最高級の汎用クラスということなので何でも用意してるってことだけど...
    // 個人的には業務のプログラミングにおいてメッセージ無しでthrowして欲しくという気持ちがあるので、
    // たいてい以下のような感じにしている:
    //
    // public ActionClassPackageMismatchException(String msg) {
    //    super(msg);
    // }
    //
    // public ActionClassPackageMismatchException(String msg, Throwable cause) {
    //    super(msg, cause);
    // }
    //
    // causeとmsgを逆転させるようなオーバーロードメソッドも作らない。無駄な選択肢はなくすようにしている。
    //
    // 一方で、JavaAPIも1995年とかに作られたものは、ちょっといろいろある。
    // java.sql.Date問題と、HashSetいい感じ話をした。
    //
    public SupercarClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
