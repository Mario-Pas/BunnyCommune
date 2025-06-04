package net.mario.lepuscommunemod.item;

import net.mario.lepuscommunemod.LepusCommune;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import  net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.network.chat.Component;

import java.awt.*;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LepusCommune.MOD_ID);

    public static final RegistryObject<CreativeModeTab> LEPUS_COMMUNE_TAB = CREATIVE_MODE_TABS.register("lepus_commune",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BUNNY_STAFF.get()))
                    .title(Component.translatable("itemGroup.lepus_commune"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.BUNNY_STAFF.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
