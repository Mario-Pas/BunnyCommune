package net.mario.lepuscommunemod.entity.client;

import net.mario.lepuscommunemod.entity.custom.BunnyEntity;
import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class BunnyRenderer extends MobRenderer<BunnyEntity, RabbitModel<BunnyEntity>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/rabbit/brown.png");

    public BunnyRenderer(EntityRendererProvider.Context context) {
        super(context, new RabbitModel<>(context.bakeLayer(ModelLayers.RABBIT)), 0.4f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull BunnyEntity entity) {
        return TEXTURE;
    }
}