package net.sdm.journeymapstages.addons.CraftTweaker;

import com.blamejared.crafttweaker.api.action.base.IRuntimeAction;
import net.sdm.journeymapstages.Journeymapstages;

public class JourneyStageAction implements IRuntimeAction {
    private final Type type;
    private final String stage;

    public JourneyStageAction(Type type, String stage) {
        this.type = type;
        this.stage = stage;
    }

    @Override
    public void apply () {
        switch(this.type) {
            case FULLSCREEN:
                Journeymapstages.fullScreenMap = this.stage;
                return;
            case MINIMAP:
                Journeymapstages.minimapStage = this.stage;
                return;
            case WAYPOINT:
                Journeymapstages.wayPoints = this.stage;
                return;
            case DEATHPOINT:
                Journeymapstages.deadPoints = this.stage;
        }
    }

    @Override
    public String describe() {
        return String.format("Restricting Journey Map %s stage to %s.",this.type.name().toLowerCase(),this.stage);
    }

    public enum Type {
        FULLSCREEN,
        MINIMAP,
        WAYPOINT,
        DEATHPOINT
    }
}
