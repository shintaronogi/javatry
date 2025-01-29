package org.docksidestage.javatry.basic.st6.os;

/**
 * @author shiny
 */
public class WindowsOperatingSystem extends St6OperationSystem {

    // ===================================================================================
    //                                                                         Constructor
    //
    public WindowsOperatingSystem(String loginId) {
        super("Windows", loginId);
    }

    // ===================================================================================
    //                                                                      User Directory
    //                                                                      ==============
    // [1on1でのふぉろー] 思考エクササイズ2: WindowsとOldWindowsで値(ロジック)が同じだけど再利用する？
    // 新と旧で概念を分けて考えることもできるかも by shiny
    // 概念としては同じと捉えることができても、実務的な面で再利用するかどうかはまだ判断がある by jflute
    @Override
    protected String getFileSeparator() {
        return "\\";
    }

    @Override
    protected String getUserDirectory() {
        return "/Users/" + getLoginId();
    }
}
