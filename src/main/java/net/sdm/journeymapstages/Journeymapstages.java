package net.sdm.journeymapstages;

import journeymap.client.waypoint.Waypoint;
import journeymap.client.waypoint.WaypointStore;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod("journeymapstages")
public class Journeymapstages {

    public static String minimapStage = "";
    public static String fullScreenMap = "";
    public static String wayPoints = "";
    public static String deadPoints = "";

    public Journeymapstages() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static boolean noHasStage(PlayerEntity player, String stage) {
        return !stage.isEmpty() && !GameStageHelper.hasStage(player,stage);
    }

    @SubscribeEvent
    public void tick(TickEvent.PlayerTickEvent event){
        if(event.player.level.isClientSide && event.player.level.getGameTime() % 20 == 0){
            PlayerEntity player = event.player;
            if(noHasStage(player, wayPoints)) {
                for (Waypoint waypoint : WaypointStore.INSTANCE.getAll()) {
                    if(!waypoint.isDeathPoint()){
                        waypoint.setEnable(false);
                        waypoint.setDirty();
                    }
                }
            }
            if(noHasStage(player, deadPoints)) {
                for (Waypoint waypoint : WaypointStore.INSTANCE.getAll()) {
                    if(waypoint.isDeathPoint()){
                        waypoint.setEnable(false);
                        waypoint.setDirty();
                    }
                }
            }
        }
    }

}
