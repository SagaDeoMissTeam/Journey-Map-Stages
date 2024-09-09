package net.sdm.journeymapstages.mixin;

import journeymap.client.ui.fullscreen.Fullscreen;
import journeymap.client.ui.waypoint.WaypointEditor;
import journeymap.client.ui.waypoint.WaypointManager;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.sdm.journeymapstages.Journeymapstages;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(method = "setScreen", at = @At("HEAD"), cancellable = true)
    public void sdm$setScreen(Screen screen, CallbackInfo ci){
        if(screen instanceof Fullscreen && Journeymapstages.noHasStage(Minecraft.getInstance().player, Journeymapstages.fullScreenMap)){
            ci.cancel();
        } else if(screen instanceof WaypointEditor && Journeymapstages.noHasStage(Minecraft.getInstance().player, Journeymapstages.minimapStage)){
            ci.cancel();
        } else if(screen instanceof WaypointManager && Journeymapstages.noHasStage(Minecraft.getInstance().player, Journeymapstages.minimapStage)){
            ci.cancel();
        }
    }
}
