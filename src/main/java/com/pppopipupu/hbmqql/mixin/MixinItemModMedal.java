package com.pppopipupu.hbmqql.mixin;

import com.hbm.capability.HbmLivingProps;
import com.hbm.items.armor.ItemModMedal;
import com.hbm.util.ContaminationUtil;
import nc.radiation.RadiationHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ItemModMedal.class,remap = false)
public class MixinItemModMedal {
    @Shadow
    private float minusRads;
    @Shadow
    private float decayRate;
    /**
     * @author
     * @reason
     */
    @Inject(method = "modUpdate",at = @At("HEAD"))

    public void modUpdate(EntityLivingBase entity, ItemStack armor, CallbackInfo ci) {
        if(!entity.world.isRemote) {

            float nco_rad = (float) (RadiationHelper.getEntityRadiation(entity).getTotalRads());
            nco_rad -= minusRads;

            RadiationHelper.getEntityRadiation(entity).setTotalRads(Math.max(nco_rad,0),true);
        }

    }
}
