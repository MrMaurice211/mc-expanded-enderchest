package io.chaws.expandedenderchest.mixin;

import com.mojang.authlib.GameProfile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerEntityMixin {
	@Unique
	private static final int EXPANDED_ENDERCHEST_SIZE = 9 * 6;

	@Shadow
	public abstract PlayerEnderChestContainer getEnderChestInventory();

	@Inject(method = "<init>", at = @At(value = "RETURN"))
	private void expandEnderChest(
		final Level world,
		//? if <1.21.6 {
		/*final BlockPos pos,
		final float yaw,
		*///?}
		final GameProfile gameProfile,
		final CallbackInfo ci
	) {
		var accessor = (SimpleInventoryAccessor)getEnderChestInventory();
		accessor.setSize(EXPANDED_ENDERCHEST_SIZE);
		accessor.setStacks(NonNullList.withSize(EXPANDED_ENDERCHEST_SIZE, ItemStack.EMPTY));
	}
}
