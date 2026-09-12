package net.sdm.journeymapstages;

import journeymap.client.waypoint.ClientWaypointImpl;
import journeymap.common.waypoint.WaypointStore;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("journeymapstages")
public class Journeymapstages {

    public static String minimapStage = "";
    public static String fullScreenMap = "";
    public static String wayPoints = "";
    public static String deadPoints = "";

    public Journeymapstages() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static boolean noHasStage(Player player, String stage) {
        return !stage.isEmpty() && !GameStageHelper.hasStage(player,stage);
    }

    @SubscribeEvent
    public void tick(TickEvent.PlayerTickEvent event){
        if(event.player.level().isClientSide && event.player.level().getGameTime() % 20 == 0){
            Player player = event.player;
            if(noHasStage(player, wayPoints)) {
                for (ClientWaypointImpl waypoint : WaypointStore.getInstance().getAll()) {
                    if(!waypoint.isDeathPoint()){
                        waypoint.setEnabled(false);
                        waypoint.setDirty(true);
                    }
                }
            }
            if(noHasStage(player, deadPoints)) {
                for (ClientWaypointImpl waypoint : WaypointStore.getInstance().getAll()) {
                    if(waypoint.isDeathPoint()){
                        waypoint.setEnabled(false);
                        waypoint.setDirty(true);
                    }
                }
            }
        }
    }

}
