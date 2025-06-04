package net.mario.lepuscommunemod.event;

import net.mario.lepuscommunemod.LepusCommune;
import net.mario.lepuscommunemod.entity.ModEntities;
import net.mario.lepuscommunemod.entity.custom.BunnyEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



    @Mod.EventBusSubscriber(modid = LepusCommune.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public class ModEventBusEvents {

        @SubscribeEvent
        public static void registerAttributes(EntityAttributeCreationEvent event) {
            LepusCommune.LOGGER.info("Registering attributes for commune_bunny");
            event.put(ModEntities.COMMUNE_BUNNY.get(), BunnyEntity.createAttributes().build());
        }

    }
    
