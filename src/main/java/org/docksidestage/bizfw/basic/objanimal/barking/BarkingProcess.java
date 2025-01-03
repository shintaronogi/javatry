package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.HitPointAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The object for animal's barking process(動物の鳴き声プロセス).
 * @author shiny
 */
public class BarkingProcess {

    // ===================================================================================
    //                                                                          Definition
    //
    private static final Logger logger = LoggerFactory.getLogger(BarkingProcess.class);

    // ===================================================================================
    //                                                                           Behavior
    //                                                                           =========
    private final HitPointAction hitPointAction;

    // ===================================================================================
    //                                                                         Constructor
    //
    public BarkingProcess(HitPointAction hitPointAction) {
        this.hitPointAction = hitPointAction;
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    public BarkedSound run(String barkWord) {
        breatheIn();
        prepareAbdominalMuscle();
        BarkedSound barkedSound = doBark(barkWord);
        return barkedSound;
    }

    protected void breatheIn() {
        logger.debug("...Breathing in for barking");
        hitPointAction.downHitPoint();
    }

    protected void prepareAbdominalMuscle() {
        logger.debug("...Using my abdominal muscle for barking");
        hitPointAction.downHitPoint();
    }

    protected BarkedSound doBark(String barkWord) {
        hitPointAction.downHitPoint();
        return new BarkedSound(barkWord);
    }

}
