package io.chaws.expandedenderchest.mixin;

import java.util.OptionalInt;

import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.level.block.EnderChestBlock;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnderChestBlock.class)
public class EnderChestBlockMixin {
	@Redirect(
		//? if <1.20.5 {
		// method = "use",
		//?} else {
		method = "useWithoutItem",
		//?}
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
		return playerEntity.openMenu(new SimpleMenuProvider(
			(syncId, playerInventory, playerEntityInner) ->
				ChestMenu.sixRows(
					syncId,
					playerInventory,
					playerEntityInner.getEnderChestInventory()
				),
			Component.translatable("container.enderchest")
		));
	}
}
