package com.pppopipupu.hbmqql;

import com.hbm.handler.HazmatRegistry;
import com.hbm.items.ModItems;


import nc.init.NCArmor;
import nc.radiation.RadArmor;
import nc.recipe.vanilla.CraftingRecipeHandler;
import nc.util.ArmorHelper;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.hbm.handler.HazmatRegistry.fixRounding;
import static nc.config.NCConfig.radiation_horse_armor_public;
import static nc.radiation.RadArmor.armorWithRadResistance;

@Mod(modid = HBMQQL.MOD_ID, dependencies = "required-after:nuclearcraft;required-after:hbm")
public class HBMQQL {
    public static final String MOD_ID = "hbmqql";


    public static final Item rad_transformer = new Item().setRegistryName("rad_transformer");
    double helmet = 0.2D;
    double chest = 0.4D;
    double legs = 0.3D;
    double boots = 0.1D;

    double iron = 0.0225D; // 5%
    double gold = 0.03D; // 5%
    double steel = 0.045D; // 10%
    double titanium = 0.045D; // 10%
    double alloy = 0.07D; // 15%
    double cobalt = 0.125D; // 25%

    double hazYellow = 0.6D; // 75%
    double hazRed = 1.0D; // 90%
    double hazGray = 2D; // 99%
    double liquidator = 2.4D; // 99.6%
    double paa = 3.0D; // 99.9%


    double t45 = 1D; // 90%
    double ajr = 1.3D; // 95%
    double hev = 1.6D; // 97.5%
    double bj = 1D; // 90%
    double rpa = 2D; // 99%
    double fau = 4D; // 99.99%
    double dns = 6D; // 99.9999%
    public static Logger logger;
    double security = 0.01D; // 2.3%
    double star = 0.25D; // 44%
    double cmb = 0.5D; // 68%
    double schrab = 1D; // 90.0%
    double euph = 10D; // 99.99999999%
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        ForgeRegistries.ITEMS.register(rad_transformer);
        ModelLoader.setCustomModelResourceLocation(rad_transformer, 0, new ModelResourceLocation(rad_transformer.getRegistryName(), "inventory"));


    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        HazmatRegistry.registerHazmat(NCArmor.boots_hazmat,0.4);
        HazmatRegistry.registerHazmat(NCArmor.chest_hazmat,0.7);
        HazmatRegistry.registerHazmat(NCArmor.legs_hazmat,0.4);
        HazmatRegistry.registerHazmat(NCArmor.helm_hazmat,0.4);

    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent postEvent) {
        for (Item item : ForgeRegistries.ITEMS.getValuesCollection()) {

            NonNullList<ItemStack> stacks = NonNullList.create();
            item.getSubItems(CreativeTabs.SEARCH, stacks);
            for (ItemStack stack : stacks) {


                CraftingRecipeHandler.addShapelessArmorUpgradeRecipe(armorWithRadResistance(stack,((HazmatRegistry.getResistance(stack)*15)+ Math.pow(HazmatRegistry.getResistance(stack),3)/3) ), stack, new ItemStack(rad_transformer, 1));
                }
            }

    }

    }



