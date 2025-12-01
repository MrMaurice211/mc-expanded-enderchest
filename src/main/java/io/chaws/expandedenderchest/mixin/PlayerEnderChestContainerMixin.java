package io.chaws.expandedenderchest.mixin;

import io.chaws.expandedenderchest.config.ExpandedEnderchestConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.PlayerEnderChestContainer;

@Mixin(PlayerEnderChestContainer.class)
public class PlayerEnderChestContainerMixin extends SimpleContainer {

	@ModifyArg(
		method = "<init>",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/SimpleContainer;<init>(I)V"
		),
		index = 0
	)
	private static int modifyContainerSize(int original) {
		return ExpandedEnderchestConfig.getInstance().rowCount * 9;
	}

}
