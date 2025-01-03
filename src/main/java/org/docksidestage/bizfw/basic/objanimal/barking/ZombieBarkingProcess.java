package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.HitPointAction;
import org.docksidestage.bizfw.basic.objanimal.Zombie;

/**
 * The object for zombie's barking process(ゾンビの鳴き声プロセス).
 * @author shiny
 */
public class ZombieBarkingProcess extends BarkingProcess {

    // ===================================================================================
    //                                                                           Attribute
    //
    protected final Zombie zombie;

    // ===================================================================================
    //                                                                         Constructor
    //
    public ZombieBarkingProcess(Zombie zombie, HitPointAction hitPointAction) {
        super(hitPointAction);
        this.zombie = zombie;
    }

    // ===================================================================================
    //                                                                          Breathe In
    //
    @Override
    protected void breatheIn() {
        super.breatheIn();
        zombie.getZombieDiary().countBreatheIn();
    }
}
