package net.mario.lepuscommunemod.entity.Model;// Made with Blockbench 4.12.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.mario.lepuscommunemod.entity.custom.BunnyEntity;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.HumanoidArm;

//import javax.swing.text.html.parser.Entity;

public class CommuneBunny extends EntityModel<BunnyEntity> implements ArmedModel {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.tryParse("lepuscommunemod:communebunny"),  // ← no longer uses (modid, path)
			"main"
	);
	private final ModelPart LeftArm;
	private final ModelPart RightArm;
	private final ModelPart Body;
	private final ModelPart RightLegJoint;
	private final ModelPart LeftLegJoint;
	private final ModelPart LeftLeg;
	private final ModelPart RightLeg;
	private final ModelPart LowerBack;
	private final ModelPart Thigh;
	private final ModelPart Tail;
	private final ModelPart UpperRightEar;
	private final ModelPart RightEar;
	private final ModelPart UpperLeftEar;
	private final ModelPart LeftEar;
	private final ModelPart Nose;
	private final ModelPart Head;

	public CommuneBunny(ModelPart root) {
		this.LeftArm = root.getChild("LeftArm");
		this.RightArm = root.getChild("RightArm");
		this.Body = root.getChild("Body");
		this.RightLegJoint = root.getChild("RightLegJoint");
		this.LeftLegJoint = root.getChild("LeftLegJoint");
		this.LeftLeg = root.getChild("LeftLeg");
		this.RightLeg = root.getChild("RightLeg");
		this.LowerBack = root.getChild("LowerBack");
		this.Thigh = root.getChild("Thigh");
		this.Tail = root.getChild("Tail");
		this.UpperRightEar = root.getChild("UpperRightEar");
		this.RightEar = root.getChild("RightEar");
		this.UpperLeftEar = root.getChild("UpperLeftEar");
		this.LeftEar = root.getChild("LeftEar");
		this.Nose = root.getChild("Nose");
		this.Head = root.getChild("Head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create(), PartPose.offset(2.0F, 19.0F, -3.0F));

		PartDefinition LeftArm_r1 = LeftArm.addOrReplaceChild("LeftArm_r1", CubeListBuilder.create().texOffs(18, 19).addBox(-1.6F, -1.0133F, -4.9779F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.75F, -0.025F, -0.75F, 1.5708F, 0.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create(), PartPose.offset(-2.0F, 19.0F, -3.0F));

		PartDefinition RightArm_r1 = RightArm.addOrReplaceChild("RightArm_r1", CubeListBuilder.create().texOffs(0, 27).addBox(-0.6F, -1.0133F, -5.8779F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.25F, -0.925F, -0.75F, 1.5708F, 0.0F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -8.5F, -4.0F, 6.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition RightLegJoint = partdefinition.addOrReplaceChild("RightLegJoint", CubeListBuilder.create(), PartPose.offset(-3.0F, 22.0F, 6.0F));

		PartDefinition RightLeg_Joint_r1 = RightLegJoint.addOrReplaceChild("RightLeg_Joint_r1", CubeListBuilder.create().texOffs(16, 27).addBox(-0.5F, -0.0737F, -2.0253F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.55F, 0.05F, 1.225F, -1.8675F, 0.0F, 0.0F));

		PartDefinition LeftLegJoint = partdefinition.addOrReplaceChild("LeftLegJoint", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 7.0F));

		PartDefinition LeftLeg_Joint_r1 = LeftLegJoint.addOrReplaceChild("LeftLeg_Joint_r1", CubeListBuilder.create().texOffs(24, 27).addBox(-1.5F, -0.0737F, -2.0253F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 0.05F, 0.25F, -1.8675F, 0.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(2.0F, 23.0F, 5.0F));

		PartDefinition LeftLeg_r1 = LeftLeg.addOrReplaceChild("LeftLeg_r1", CubeListBuilder.create().texOffs(30, 4).addBox(-1.5F, 0.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.0F, 2.325F, -1.5708F, 0.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-4.0F, 23.0F, 5.0F));

		PartDefinition RightLeg_r1 = RightLeg.addOrReplaceChild("RightLeg_r1", CubeListBuilder.create().texOffs(30, 0).addBox(-0.5F, 0.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.45F, -1.0F, 2.3F, -1.5708F, 0.0F, 0.0F));

		PartDefinition LowerBack = partdefinition.addOrReplaceChild("LowerBack", CubeListBuilder.create(), PartPose.offset(-0.025F, 24.0F, 0.1F));

		PartDefinition Lower_back_r1 = LowerBack.addOrReplaceChild("Lower_back_r1", CubeListBuilder.create().texOffs(0, 13).addBox(-4.0F, -2.0F, -1.0F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.525F, -6.4F, 4.225F, -0.7854F, 0.0F, 0.0F));

		PartDefinition Thigh = partdefinition.addOrReplaceChild("Thigh", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Thigh_r1 = Thigh.addOrReplaceChild("Thigh_r1", CubeListBuilder.create().texOffs(20, 13).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -5.675F, 5.825F, -1.5708F, 0.0F, 0.0F));

		PartDefinition Tail = partdefinition.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Tail_r1 = Tail.addOrReplaceChild("Tail_r1", CubeListBuilder.create().texOffs(28, 31).addBox(-1.0F, -2.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.25F, 6.375F, -1.5708F, 0.0F, 0.0F));

		PartDefinition UpperRightEar = partdefinition.addOrReplaceChild("UpperRightEar", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Upper_Right_Ear_r1 = UpperRightEar.addOrReplaceChild("Upper_Right_Ear_r1", CubeListBuilder.create().texOffs(19, 31).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -11.975F, -5.25F, 0.1309F, 0.0F, -0.1396F));

		PartDefinition RightEar = partdefinition.addOrReplaceChild("RightEar", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Right_Ear_r1 = RightEar.addOrReplaceChild("Right_Ear_r1", CubeListBuilder.create().texOffs(29, 8).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.725F, -10.0F, -4.25F, 0.0F, 0.0F, -0.1396F));

		PartDefinition UpperLeftEar = partdefinition.addOrReplaceChild("UpperLeftEar", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Upper_Left_Ear_r1 = UpperLeftEar.addOrReplaceChild("Upper_Left_Ear_r1", CubeListBuilder.create().texOffs(16, 31).addBox(-0.4808F, -2.0082F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -11.975F, -5.25F, 0.1309F, 0.0F, 0.1396F));

		PartDefinition LeftEar = partdefinition.addOrReplaceChild("LeftEar", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Left_Ear_r1 = LeftEar.addOrReplaceChild("Left_Ear_r1", CubeListBuilder.create().texOffs(24, 31).addBox(0.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -10.075F, -4.25F, 0.0F, 0.0F, 0.1396F));

		PartDefinition Nose = partdefinition.addOrReplaceChild("Nose", CubeListBuilder.create().texOffs(30, 11).addBox(-1.0F, -8.75F, -8.35F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 19).addBox(-3.0F, -10.35F, -8.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(BunnyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// You can leave this empty for now, or add basic ear/head movement here later
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLegJoint.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLegJoint.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LowerBack.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Thigh.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		UpperRightEar.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightEar.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		UpperLeftEar.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftEar.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Nose.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void translateToHand(HumanoidArm arm, PoseStack poseStack) {
		// Adjust the position where the item will render in the bunny's hand.
		// You can tweak this later to match your actual model's arm position.
		poseStack.translate(0.0D, 1.5D, 0.0D);
	}
}