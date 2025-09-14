//当时不知道为啥写了这个
//package com.pppopipupu.hbmqql.mixin;
//
//import com.hbm.inventory.CentrifugeRecipes;
//import com.hbm.items.ModItems;
//import net.minecraft.init.Items;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.oredict.OreDictionary;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import java.util.LinkedHashMap;
//
//import static com.hbm.inventory.OreDictManager.B;
//import static com.hbm.inventory.OreDictManager.TH232;
//
//
//@Mixin(value = CentrifugeRecipes.class,remap = false)
//public class MixinCentrifugeRecipes {
//    @Shadow
//    private static LinkedHashMap<Object, ItemStack[]> recipes = new LinkedHashMap<Object, ItemStack[]>();
//    @Inject(at = @At("HEAD"),method = "register")
//    private static void register(CallbackInfo ci) {
//        recipes.put("crystalThorium", new ItemStack[] { new ItemStack(ModItems.powder_thorium, 2), new ItemStack(ModItems.powder_thorium, 2), new ItemStack(ModItems.powder_uranium, 1), new ItemStack(ModItems.nugget_ra226, 1) });
//        if(!OreDictionary.getOres("crystalIridium").isEmpty()) {
//            recipes.put("crystalIridium", new ItemStack[] { new ItemStack(OreDictionary.getOres("dustIridium").get(0).getItem(),2,OreDictionary.getOres("dustIridium").get(0).getMetadata()), new ItemStack(OreDictionary.getOres("dustIridium").get(0).getItem(),2,OreDictionary.getOres("dustIridium").get(0).getMetadata()), new ItemStack(ModItems.powder_lithium_tiny, 1), new ItemStack(ModItems.powder_iron, 1) });
//        }
//        if(!OreDictionary.getOres("crystalSilver").isEmpty()) {
//            recipes.put("crystalSliver", new ItemStack[]{new ItemStack(OreDictionary.getOres("dustSilver").get(0).getItem(), 2, OreDictionary.getOres("dustSilver").get(0).getMetadata()), new ItemStack(OreDictionary.getOres("dustSilver").get(0).getItem(), 2, OreDictionary.getOres("dustSilver").get(0).getMetadata()), new ItemStack(ModItems.powder_lithium_tiny, 1), new ItemStack(ModItems.powder_iron, 1)});
//        }
//        if(!OreDictionary.getOres("crystalTin").isEmpty()) {
//            recipes.put("crystalTin", new ItemStack[]{new ItemStack(OreDictionary.getOres("dustTin").get(0).getItem(), 2, OreDictionary.getOres("dustTin").get(0).getMetadata()), new ItemStack(OreDictionary.getOres("dustTin").get(0).getItem(), 2, OreDictionary.getOres("dustTin").get(0).getMetadata()), new ItemStack(ModItems.powder_lithium_tiny, 1), new ItemStack(ModItems.powder_iron, 1)});
//        }
//        if(!OreDictionary.getOres("crystalMagnesium").isEmpty()) {
//            recipes.put("crystalMagnesium", new ItemStack[]{new ItemStack(OreDictionary.getOres("dustMagnesium").get(0).getItem(), 2, OreDictionary.getOres("dustMagnesium").get(0).getMetadata()), new ItemStack(OreDictionary.getOres("dustMagnesium").get(0).getItem(), 2, OreDictionary.getOres("dustMagnesium").get(0).getMetadata()), new ItemStack(ModItems.powder_lithium_tiny, 1), new ItemStack(ModItems.powder_iron, 1)});
//        }
//        if(!OreDictionary.getOres("crystalPlatinum").isEmpty()) {
//            recipes.put("crystalPlatinum", new ItemStack[]{new ItemStack(OreDictionary.getOres("dustPlatinum").get(0).getItem(), 2, OreDictionary.getOres("dustPlatinum").get(0).getMetadata()), new ItemStack(OreDictionary.getOres("dustPlatinum").get(0).getItem(), 2, OreDictionary.getOres("dustPlatinum").get(0).getMetadata()), new ItemStack(ModItems.powder_lithium_tiny, 1), new ItemStack(ModItems.powder_iron, 1)});
//        }
//        if(!OreDictionary.getOres("crystalNickel").isEmpty()) {
//            recipes.put("crystalNickel", new ItemStack[]{new ItemStack(OreDictionary.getOres("dustNickel").get(0).getItem(), 2, OreDictionary.getOres("dustNickel").get(0).getMetadata()), new ItemStack(OreDictionary.getOres("dustNickel").get(0).getItem(), 2, OreDictionary.getOres("dustNickel").get(0).getMetadata()), new ItemStack(ModItems.powder_lithium_tiny, 1), new ItemStack(ModItems.powder_iron, 1)});
//        }
//        if(!OreDictionary.getOres("crystalDraconium").isEmpty()) {
//            recipes.put("crystalDraconium", new ItemStack[] { new ItemStack(OreDictionary.getOres("dustDraconium").get(0).getItem(),2), new ItemStack(OreDictionary.getOres("dustDraconium").get(0).getItem(),2), new ItemStack(Items.ENDER_PEARL, 1) });
//        }
//        if(!OreDictionary.getOres("crystalOsmium").isEmpty()) {
//            recipes.put("crystalOsmium", new ItemStack[] { new ItemStack(OreDictionary.getOres("dustOsmium").get(0).getItem(),2,OreDictionary.getOres("dustOsmium").get(0).getMetadata()), new ItemStack(OreDictionary.getOres("dustOsmium").get(0).getItem(),2,OreDictionary.getOres("dustOsmium").get(0).getMetadata()), new ItemStack(ModItems.powder_lithium_tiny, 1), new ItemStack(ModItems.powder_iron, 1) });
//        }
//        if(!OreDictionary.getOres("crystalAncientDebris").isEmpty()) {
//            recipes.put("crystalAncientDebris", new ItemStack[] { new ItemStack(OreDictionary.getOres("dustAncientDebris").get(0).getItem(),2), new ItemStack(OreDictionary.getOres("dustAncientDebris").get(0).getItem(),2), new ItemStack(ModItems.powder_quartz, 2),new ItemStack(ModItems.powder_quartz, 2)});
//        }
//        if(!OreDictionary.getOres("crystalBoron").isEmpty()) {
//            recipes.put(B.crystal(), new ItemStack[]{new ItemStack(ModItems.powder_boron, 2), new ItemStack(ModItems.powder_boron, 2), new ItemStack(ModItems.powder_neodymium_tiny, 1), new ItemStack(ModItems.powder_lithium_tiny, 1)});
//        }
//        if(!OreDictionary.getOres("crystalMithril").isEmpty()) {
//            recipes.put("crystalMithril", new ItemStack[] { new ItemStack(OreDictionary.getOres("dustMithril").get(0).getItem(),2,OreDictionary.getOres("dustMithril").get(0).getMetadata()), new ItemStack(OreDictionary.getOres("dustMithril").get(0).getItem(),2,OreDictionary.getOres("dustMithril").get(0).getMetadata()), new ItemStack(ModItems.powder_gold, 2), new ItemStack(ModItems.powder_gold, 2) });
//        }
//
//    }
//    }
//
