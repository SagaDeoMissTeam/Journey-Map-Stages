package net.sdm.journeymapstages.addons.CraftTweaker;


import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.jmapstages.JMapStages")
public class CTMethods {


    @ZenCodeType.Method
    public static void setFullscreenStage(String stage) {
        CraftTweakerAPI.apply(new JourneyStageAction(JourneyStageAction.Type.FULLSCREEN,stage));
    }

    @ZenCodeType.Method
    public static void setMinimapStage(String stage) {
        CraftTweakerAPI.apply(new JourneyStageAction(JourneyStageAction.Type.MINIMAP,stage));
    }

    @ZenCodeType.Method
    public static void setWaypointStage(String stage) {
        CraftTweakerAPI.apply(new JourneyStageAction(JourneyStageAction.Type.WAYPOINT,stage));
    }

    @ZenCodeType.Method
    public static void setDeadhpointStage(String stage) {
        CraftTweakerAPI.apply(new JourneyStageAction(JourneyStageAction.Type.DEATHPOINT,stage));
    }
}
