package net.mario.lepuscommunemod.entity.client;

import net.mario.lepuscommunemod.entity.Model.CommuneBunny;
import net.mario.lepuscommunemod.entity.custom.BunnyEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class BunnyRenderer extends MobRenderer<BunnyEntity, CommuneBunny> {
    public BunnyRenderer(EntityRendererProvider.Context context) {
        super(context, new CommuneBunny(context.bakeLayer(CommuneBunny.LAYER_LOCATION)), 0.4f);
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(BunnyEntity entity) {
        return ResourceLocation.fromNamespaceAndPath("lepuscommunemod", "textures/entity/bunny/commune_bunny.png");
    }
}