package net.mario.lepuscommunemod.item;

import net.mario.lepuscommunemod.LepusCommune;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.mario.lepuscommunemod.entity.ModEntities;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, LepusCommune.MOD_ID);


    public static final RegistryObject<Item> BUNNY_STAFF = ITEMS.register("bunny_staff",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COMMUNE_BUNNY_SPAWN_EGG =ITEMS.register("commune_bunny_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.COMMUNE_BUNNY, 0xFFFFFF, 0xAA66FF,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
