package net.sdm.journeymapstages.mixin;

import com.mojang.blaze3d.matrix.MatrixStack;
import journeymap.client.ui.minimap.MiniMap;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.client.Minecraft;
import net.sdm.journeymapstages.Journeymapstages;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MiniMap.class, remap = false)
public class MiniMapMixin {

    @Inject(method = "drawMap(Lcom/mojang/blaze3d/matrix/MatrixStack;Z)V", at = @At("HEAD"), cancellable = true)
    public void sdm$drawMap(MatrixStack mStack, boolean preview, CallbackInfo ci){
        if(Journeymapstages.noHasStage(Minecraft.getInstance().player, Journeymapstages.minimapStage)){
            ci.cancel();
        }
    }
}
