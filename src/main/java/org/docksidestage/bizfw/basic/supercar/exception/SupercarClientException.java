package org.docksidestage.bizfw.basic.supercar.exception;

public class SupercarClientException extends RuntimeException {
    // TODO jflute RuntimeExceptionは通常3つのコンストラクタを持つと思いますが、カスタムのExceptionを作る時は、
    //  三つとも作るや要件に応じて特定のものだけ作るとかベストプラクティスはどうなんでしょう？
    public SupercarClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
