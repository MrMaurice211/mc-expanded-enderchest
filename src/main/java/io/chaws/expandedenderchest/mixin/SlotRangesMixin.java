package io.chaws.expandedenderchest.mixin;

//? if >=1.20.5 {
/*import io.chaws.expandedenderchest.config.ExpandedEnderchestConfig;

import net.minecraft.world.inventory.SlotRange;
import net.minecraft.world.inventory.SlotRanges;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.function.Consumer;

@Mixin(SlotRanges.class)
public abstract class SlotRangesMixin {

	@Shadow
	private static void addSlotRange(List<SlotRange> list, String string, int i, int j) {
	}

	@Redirect(
		method = "<clinit>",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/Util;make(Ljava/lang/Object;Ljava/util/function/Consumer;)Ljava/lang/Object;"
		)
	)
	private static Object redirectUtilMake(Object list, Consumer<Object> consumer) {
		consumer.accept(list);

		@SuppressWarnings("unchecked")
		List<SlotRange> slotList = (List<SlotRange>) list;

		// Remove all existing enderchest entries
		slotList.removeIf(entry -> entry.getSerializedName().startsWith("enderchest"));

		// Now add only the number of enderchest slots you want
		int desiredEnderChestSize = ExpandedEnderchestConfig.getInstance().rowCount * 9;

		addSlotRange(slotList, "enderchest.", 200, desiredEnderChestSize);

		return list;
	}

}
*///?}
