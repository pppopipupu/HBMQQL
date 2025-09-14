package com.pppopipupu.hbmqql.mixin;

import com.hbm.handler.HazmatRegistry;
import nc.capability.radiation.entity.IEntityRads;
import nc.config.NCConfig;
import nc.radiation.RadiationHelper;
import nc.util.NCMath;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(value = RadiationHelper.class, remap = false)
public abstract class MixinRadiationHelper {
    @Inject(method = "Lnc/radiation/RadiationHelper;addRadsToEntity(Lnc/capability/radiation/entity/IEntityRads;Lnet/minecraft/entity/EntityLivingBase;DZZD)D",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private static void addRadsToEntity(IEntityRads entityRads, EntityLivingBase entity, double rawRadiation, boolean ignoreResistance, boolean ignoreMultipliers, double updateRate, CallbackInfoReturnable<Double> cir) {
        if (rawRadiation <= (double) 0.0F) {
            cir.setReturnValue(0.0D);
        } else {
            if (!ignoreMultipliers) {
                if (entity.isInWater()) {
                    rawRadiation *= NCConfig.radiation_swim_mult;
                } else if (NCConfig.radiation_rain_mult != (double) 1.0F && entity.isWet()) {
                    rawRadiation *= NCConfig.radiation_rain_mult;
                }
            }

            double resistance = ignoreResistance ? Math.min((double) 0.0F, entityRads.getInternalRadiationResistance()) : entityRads.getFullRadiationResistance();
            double addedRadiation = resistance > (double) 0.0F ? NCMath.sq(rawRadiation) / (rawRadiation + resistance) : rawRadiation * ((double) 1.0F - resistance);
            double hbm_res = 0;
            if (!ignoreResistance) {
                for (ItemStack stack : entity.getArmorInventoryList()) {
                    if (!stack.isEmpty()) {
                        hbm_res += HazmatRegistry.getResistance(stack);
                    }
                }
                addedRadiation /= Math.pow(10, hbm_res);
            }

            entityRads.setTotalRads(entityRads.getTotalRads() + addedRadiation * updateRate, true);
            cir.setReturnValue(addedRadiation);
        }
    }

}
