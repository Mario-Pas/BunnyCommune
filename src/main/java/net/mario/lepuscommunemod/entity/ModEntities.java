package net.mario.lepuscommunemod.entity;

import net.mario.lepuscommunemod.LepusCommune;
import net.mario.lepuscommunemod.entity.custom.BunnyEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Holds the DeferredRegister for ENTITY_TYPES and registers BunnyEntity.
 */
public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, LepusCommune.MOD_ID);

    public static final RegistryObject<EntityType<BunnyEntity>> COMMUNE_BUNNY =
            ENTITIES.register("commune_bunny", () ->
                    EntityType.Builder.<BunnyEntity>of(
                                    BunnyEntity::new,
                                    MobCategory.CREATURE
                            )
                            .sized(0.6F, 0.85F)
                            .build(LepusCommune.MOD_ID + ":commune_bunny")
            );

    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }

    public static void onAttributes(EntityAttributeCreationEvent event) {
        AttributeSupplier.Builder b = Rabbit.createAttributes()
                .add(Attributes.MAX_HEALTH,    10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D);
        event.put(COMMUNE_BUNNY.get(), b.build());
    }
}
