package io.chaws.expandedenderchest.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import io.chaws.expandedenderchest.config.ExpandedEnderchestConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.PlayerEnderChestContainer;

@Mixin(ChestMenu.class)
public abstract class ChestMenuMixin {

	@ModifyVariable(
		method = "<init>(Lnet/minecraft/world/inventory/MenuType;ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/Container;I)V",
		at = @At("HEAD"),
		index = 1,
		argsOnly = true)
	private static MenuType<?> modifyMenuType(MenuType<?> original, @Local(argsOnly = true) Container container) {
		if (container instanceof PlayerEnderChestContainer) {
			return switch (ExpandedEnderchestConfig.getInstance().rowCount) {
				case 1 -> MenuType.GENERIC_9x1;
				case 2 -> MenuType.GENERIC_9x2;
				case 4 -> MenuType.GENERIC_9x4;
				case 5 -> MenuType.GENERIC_9x5;
				case 6 -> MenuType.GENERIC_9x6;
				default -> MenuType.GENERIC_9x3;
			};
		}
		return original;
	}

	// Modify the j parameter (5th parameter, controls row count)
	@ModifyVariable(
		method = "<init>(Lnet/minecraft/world/inventory/MenuType;ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/Container;I)V",
		at = @At("HEAD"),
		index = 5,
		argsOnly = true)
	private static int modifyJ(int original, @Local(argsOnly = true) Container container) {
		if (container instanceof PlayerEnderChestContainer)
			return ExpandedEnderchestConfig.getInstance().rowCount;
		return original;
	}
}
