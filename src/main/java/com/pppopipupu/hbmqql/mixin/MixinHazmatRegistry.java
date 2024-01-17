package com.pppopipupu.hbmqql.mixin;

import com.hbm.handler.HazmatRegistry;
import com.hbm.lib.Library;
import com.hbm.potion.HbmPotion;
import nc.capability.radiation.entity.IEntityRads;
import nc.radiation.RadiationHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = HazmatRegistry.class,remap = false)
public abstract class MixinHazmatRegistry {
/**
 * @author pppopipupu
 * @reason
 */
@Overwrite
    public static float getResistance(EntityLivingBase player) {
        float res = 0.0F;
    IEntityRads rads = RadiationHelper.getEntityRadiation(player);
        if (player.getUniqueID().toString().equals(Library.HbMinecraft) || player.getUniqueID().toString().equals(Library.Drillgon) || player.getUniqueID().toString().equals(Library.Alcater)) {
            res += 1.0F;
        }
    res+=   rads.getFullRadiationResistance()>1? Math.sqrt(rads.getFullRadiationResistance())/3 : rads.getFullRadiationResistance()/10;
        for(ItemStack stack : player.getArmorInventoryList()) {
            if(!stack.isEmpty()) {
                res += HazmatRegistry.getResistance(stack);
            }
        }

        PotionEffect radx = player.getActivePotionEffect(HbmPotion.radx);
        if(radx != null)
            res += 0.1F * (1+radx.getAmplifier());




        return res;
    }






}
