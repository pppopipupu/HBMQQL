package com.pppopipupu.hbmqql.mixin;

import com.hbm.handler.HazmatRegistry;
import nc.capability.radiation.entity.IEntityRads;
import nc.capability.radiation.source.IRadiationSource;
import nc.radiation.RadiationHelper;
import nc.util.NCMath;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static nc.config.NCConfig.radiation_rain_mult;
import static nc.config.NCConfig.radiation_swim_mult;
import static nc.radiation.RadiationHelper.getRadiationSource;


@Mixin(value = RadiationHelper.class,remap = false)
public abstract class MixinRadiationHelper  {
    /**
     * @author
     * @reason
     */
    @Overwrite
    public static double addRadsToEntity(IEntityRads entityRads, EntityLivingBase entity, double rawRadiation, boolean ignoreResistance, boolean ignoreMultipliers, int updateRate) {
        if (rawRadiation <= 0D) {
            return 0D;
        }
        if (!ignoreMultipliers) {
            if (entity.isInWater()) {
                rawRadiation *= radiation_swim_mult;
            }
            else if (radiation_rain_mult != 1D && entity.isWet()) {
                rawRadiation *= radiation_rain_mult;
            }
        }
        double resistance = ignoreResistance ? Math.min(0D, entityRads.getInternalRadiationResistance()) : entityRads.getFullRadiationResistance();

        double addedRadiation = resistance > 0D ? NCMath.sq(rawRadiation) / (rawRadiation + resistance) : rawRadiation * (1D - resistance);
        double hbm_res = 0;
        if(!ignoreResistance) {
            for (ItemStack stack : entity.getArmorInventoryList()) {
                if (!stack.isEmpty()) {
                    hbm_res += HazmatRegistry.getResistance(stack);
                }
            }
            addedRadiation /= Math.pow(10, hbm_res);
        }

        entityRads.setTotalRads(entityRads.getTotalRads() + addedRadiation * updateRate, true);

        return addedRadiation;
    }


}
