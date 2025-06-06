package net.mario.lepuscommunemod.entity;

import net.mario.lepuscommunemod.LepusCommune;
import net.mario.lepuscommunemod.entity.client.BunnyRenderer;
import net.mario.lepuscommunemod.entity.custom.BunnyEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Registers our BunnyRenderer so that BunnyEntity actually uses it on the client.
 */
@Mod.EventBusSubscriber(
        modid = LepusCommune.MOD_ID,
        bus   = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class ClientEntityRenderers {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // The key is the EntityType<BunnyEntity>, and the value is BunnyRenderer::new.
        event.registerEntityRenderer(
                ModEntities.COMMUNE_BUNNY.get(),
                BunnyRenderer::new
        );
    }
}
