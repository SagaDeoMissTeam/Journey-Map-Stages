package net.sdm.journeymapstages.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import journeymap.client.ui.minimap.MiniMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.sdm.journeymapstages.Journeymapstages;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MiniMap.class, remap = false)
public class MiniMapMixin {

    @Inject(method = "drawMap(Lnet/minecraft/client/gui/GuiGraphics;Z)V", at = @At("HEAD"), cancellable = true)
    public void sdm$drawMap(GuiGraphics mStack, boolean preview, CallbackInfo ci){
        if(Journeymapstages.noHasStage(Minecraft.getInstance().player, Journeymapstages.minimapStage)){
            ci.cancel();
        }
    }
}
