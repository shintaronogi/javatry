package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.flyable.Flyable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The object for dragon(ドラゴン).
 * @author shiny
 */
public class Dragon extends Animal implements Flyable {

        // ===================================================================================
        //                                                                          Definition
        //                                                                          ==========
        private static final Logger logger = LoggerFactory.getLogger(Dragon.class);

        // ===================================================================================
        //                                                                           Attribute
        //                                                                           =========
         private boolean isGrown; // when it's grown, it can breathe fire and fly

        // ===================================================================================
        //                                                                         Constructor
        //                                                                         ===========
        public Dragon(boolean isGrown) {
            super();
            this.isGrown = isGrown;
        }

        @Override
        protected int getInitialHitPoint() {
            return 300; // because it's a dragon and it's strong
        }

        // ===================================================================================
        //                                                                               Bark
        //                                                                              ======
        @Override
        protected String getBarkWord() {
            return "gogogogogo";
        }

        @Override
        protected BarkedSound doBark(String barkWord) {
            if (isGrown) {
                breatheFire();
            }
            return super.doBark(barkWord);
        }

        private void breatheFire() {
            logger.debug("bobobobobobobobo (Breathing fire)");
            downHitPoint();
        }

        // ===================================================================================
        //                                                                              Runner
        //                                                                              ======
        @Override
        public void fly() {
            if (isGrown) {
                logger.debug("...Flying now");
                downHitPoint();
            } else {
                logger.debug("...Too young to fly. Wait a little bit more and it will be able to fly.");
            }
        }

        // ===================================================================================
        //                                                                            Accessor
        //                                                                            ========
        public boolean isGrown() {
            return isGrown;
        }
}
