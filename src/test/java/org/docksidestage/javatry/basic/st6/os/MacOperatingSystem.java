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

    @Override
    protected String getUserDirectory() {
        return "/Users/" + getLoginId();
    }
}
