package org.docksidestage.javatry.basic.st6.os;

/**
 * @author shiny
 */
public class OldWindowsOperatingSystem extends St6OperationSystem {

    // ===================================================================================
    //                                                                         Constructor
    //
    public OldWindowsOperatingSystem(String loginId) {
        super("OldWindows", loginId);
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
        // TODO JavaTryの方でTypoになってますかね？
        return "/Documents and Settings/" + getLoginId();
    }
}
