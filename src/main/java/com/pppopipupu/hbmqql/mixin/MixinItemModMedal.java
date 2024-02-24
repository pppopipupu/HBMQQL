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
    @Overwrite

    public void modUpdate(EntityLivingBase entity, ItemStack armor) {
        if(!entity.world.isRemote) {
            float rad = HbmLivingProps.getRadiation(entity);
            float nco_rad = (float) (RadiationHelper.getEntityRadiation(entity).getTotalRads());
            nco_rad -= minusRads;
            rad -= minusRads;
            HbmLivingProps.setRadiation(entity, Math.max(rad, 0));
            RadiationHelper.getEntityRadiation(entity).setTotalRads(Math.max(nco_rad,0),true);


            if(entity instanceof EntityPlayer){
                ContaminationUtil.neutronActivateInventory((EntityPlayer)entity, 0.0F, this.decayRate);
                ((EntityPlayer)entity).inventoryContainer.detectAndSendChanges();
            }
        }
    }
}
