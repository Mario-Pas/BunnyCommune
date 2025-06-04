package net.mario.lepuscommunemod.entity;

//import net.mario.lepuscommunemod.entity.custom.BunnyKingEntity;
import net.mario.lepuscommunemod.entity.custom.BunnyEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mario.lepuscommunemod.LepusCommune; // your main mod class

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, LepusCommune.MOD_ID);
    /** Worker/Builder/Guard bunnies */
    public static final RegistryObject<EntityType<BunnyEntity>> COMMUNE_BUNNY =
            ENTITIES.register("commune_bunny", () ->
                    EntityType.Builder.<BunnyEntity>of(
                                    BunnyEntity::new,
                                    MobCategory.CREATURE
                            )
                            .sized(0.6F, 0.85F)
                            .clientTrackingRange(8)
                            .build(String.valueOf(ResourceLocation.fromNamespaceAndPath(LepusCommune.MOD_ID, "commune_bunny")))

            );

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
