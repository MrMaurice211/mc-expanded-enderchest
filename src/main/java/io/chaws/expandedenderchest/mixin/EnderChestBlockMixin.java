package io.chaws.expandedenderchest.mixin;

import java.util.OptionalInt;

import io.chaws.expandedenderchest.config.ExpandedEnderchestConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.EnderChestBlock;

@Mixin(EnderChestBlock.class)
public class EnderChestBlockMixin {
	@Redirect(
		//? if <1.20.5 {
		 method = "use",
		//?} else {
		/*method = "useWithoutItem",
		*///?}
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/entity/player/Player;openMenu(Lnet/minecraft/world/MenuProvider;)Ljava/util/OptionalInt;"
		),
		require = 0
	)
	private OptionalInt onUse(Player playerEntity, MenuProvider factory) {
		return openHandledScreen(playerEntity);
	}

	@Unique
	private OptionalInt openHandledScreen(Player playerEntity) {
		int rows = ExpandedEnderchestConfig.getInstance().rowCount;
		MenuType<?> menuType = switch (rows) {
			case 1 -> MenuType.GENERIC_9x1;
			case 2 -> MenuType.GENERIC_9x2;
			case 4 -> MenuType.GENERIC_9x4;
			case 5 -> MenuType.GENERIC_9x5;
			case 6 -> MenuType.GENERIC_9x6;
			default -> MenuType.GENERIC_9x3;
		};
		return playerEntity.openMenu(new SimpleMenuProvider(
			(syncId, playerInventory, playerEntityInner) ->
				new ChestMenu(menuType, syncId, playerInventory, playerEntityInner.getEnderChestInventory(), rows),
			Component.translatable("container.enderchest")
		));
	}

}
