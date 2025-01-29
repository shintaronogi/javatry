package org.docksidestage.javatry.basic.st6.os;

/**
 * @author shiny
 */
public class MacOperatingSystem extends St6OperationSystem {

    // ===================================================================================
    //                                                                         Constructor
    //
    public MacOperatingSystem(String loginId) {
        super("Mac", loginId);
    }

    // ===================================================================================
    //                                                                      User Directory
    //                                                                      ==============
    @Override
    protected String getFileSeparator() {
        return "/";
    }

    // [1on1でのふぉろー] 思考エクササイズ1: Mac/WindowsでUserDirectory一緒だけど再利用する？
    // コードが同じになっても、それはたまたま一緒なだけ、っていうケースもある。
    // そのときに再利用してしまうと、片方だけ変わるって状況に対応できなくなる。(それがあり得る)
    // だし、そもそも再利用するときのメソッド名にめちゃ困るはず。
    // 再利用は、コードが同じだからするのではなく、意味が同じかどうか？で判断する。
    @Override
    protected String getUserDirectory() {
        return "/Users/" + getLoginId();
    }
}
