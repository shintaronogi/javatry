package org.docksidestage.bizfw.basic.supercar.exception;

public class SupercarClientException extends RuntimeException {
    // TODO done jflute RuntimeExceptionは通常3つのコンストラクタを持つと思いますが、カスタムのExceptionを作る時は、
    //  三つとも作るや要件に応じて特定のものだけ作るとかベストプラクティスはどうなんでしょう？
    // TODO shiny [へんじ] まあ線引はなくて曖昧ですが、それぞれのレイヤーの独立性そしてこの業務の重要性に寄りますね by jflute (2025/01/05)
    // 「一応レイヤーとして分かれてるけど、まあほぼ一緒に動くことがほとんど」という場合は共通の例外でもいいかなと。
    // 「それぞれのレイヤーがわりと独立して別のところから呼び出されることがある」という場合は個別の例外の方がベターであると。
    // 「なんにせよ、この業務がサービスの根幹で、わずかでもわかりやすくしておきたい」なら個別の例外の方がベターであると。
    // 例外クラスの存在自体はドキュメントみたいなものなので、これもアクセルをどこまで踏むか次第ですね。
    // javatryではアクセルを踏んでほしいなと(^^。(最大のやり方を知ってこそ省略できるの観点から)
    public SupercarClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
