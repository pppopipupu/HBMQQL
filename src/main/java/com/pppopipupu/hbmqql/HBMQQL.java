package com.pppopipupu.hbmqql;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = HBMQQL.MODID, name = HBMQQL.NAME, version = HBMQQL.VERSION,dependencies = "required-after:nuclearcraft;required-after:hbm")
public class HBMQQL
{
    public static final String MODID = "hbmqql";
    public static final String NAME = "HBMQQL Mod";
    public static final String VERSION = "2.0";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.info("还是pvp大佬");
    }
}
