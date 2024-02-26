package com.pppopipupu.hbmqql.mixin;

import com.hbm.potion.HbmPotion;
import nc.radiation.RadiationHelper;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = HbmPotion.class,remap = false)
public class MixinHbmPotion {
    @Shadow
    public static HbmPotion radaway;
    @Inject(at = @At("HEAD"),method = "func_76394_a")
    public void performEffect(EntityLivingBase entity, int level, CallbackInfo ci) {
        if (((HbmPotion)(Object)this) == radaway){

            RadiationHelper.getEntityRadiation(entity).setTotalRads(Math.max( (RadiationHelper.getEntityRadiation(entity).getTotalRads())-(level+1)*0.08F,0),true);

        }
    }
    }


