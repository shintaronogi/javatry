package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Dragon;
import org.docksidestage.bizfw.basic.objanimal.HitPointAction;

/**
 * The object for dargon's barking process(ドラゴンの鳴き声プロセス).
 * @author shiny
 */
public class DragonBarkingProcess extends BarkingProcess {

    // ===================================================================================
    //                                                                          Definition
    //
    protected final Dragon dragon;

    // ===================================================================================
    //                                                                         Constructor
    //
    public DragonBarkingProcess(Dragon dragon, HitPointAction hitPointAction) {
        super(hitPointAction);
        this.dragon = dragon;
    }

    // ===================================================================================
    //                                                                          Breathe In
    //
    @Override
    protected void breatheIn() {
        if (dragon.isGrown()) {
            dragon.breatheFire();
        }
        super.breatheIn();
    }
}
