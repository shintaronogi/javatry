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
    @Override
    protected String getFileSeparator() {
        return "\\";
    }

    @Override
    protected String getUserDirectory() {
        return "/Users/" + getLoginId();
    }
}
