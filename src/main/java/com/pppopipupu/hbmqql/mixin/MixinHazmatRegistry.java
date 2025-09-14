package com.pppopipupu.hbmqql.mixin;

import com.hbm.handler.HazmatRegistry;
import nc.capability.radiation.entity.IEntityRads;
import nc.radiation.RadiationHelper;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = HazmatRegistry.class, remap = false)
public abstract class MixinHazmatRegistry {
    @Inject(method = "Lcom/hbm/handler/HazmatRegistry;getResistance(Lnet/minecraft/entity/EntityLivingBase;)F",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void modifyResistance(EntityLivingBase player, CallbackInfoReturnable<Float> cir) {
        IEntityRads rads = RadiationHelper.getEntityRadiation(player);
        cir.setReturnValue((float) (cir.getReturnValueF() / rads.getFullRadiationResistance() > 1 ? Math.sqrt(rads.getFullRadiationResistance()) / 3 : rads.getFullRadiationResistance() / 10));
    }


}

