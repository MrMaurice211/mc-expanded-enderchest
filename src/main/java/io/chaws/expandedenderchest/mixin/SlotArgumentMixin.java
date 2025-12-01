package io.chaws.expandedenderchest.mixin;

//? if <1.20.5 {
import java.util.Map;
import java.util.function.Consumer;

import com.mojang.brigadier.arguments.ArgumentType;
import io.chaws.expandedenderchest.config.ExpandedEnderchestConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.commands.arguments.SlotArgument;

@Mixin(SlotArgument.class)
public abstract class SlotArgumentMixin implements ArgumentType<Integer> {


	@Redirect(
		method = "<clinit>",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/Util;make(Ljava/lang/Object;Ljava/util/function/Consumer;)Ljava/lang/Object;"
		)
	)
	private static Object redirectUtilMake(Object map, Consumer<Object> consumer) {
		// Execute the original consumer (which fills the map with default slots)
		consumer.accept(map);

		@SuppressWarnings("unchecked")
		Map<String, Integer> slotMap = (Map<String, Integer>) map;

		// Remove all existing enderchest entries
		slotMap.entrySet().removeIf(entry -> entry.getKey().startsWith("enderchest"));

		// Now add only the number of enderchest slots you want
		int desiredEnderChestSize = ExpandedEnderchestConfig.getInstance().rowCount * 9;

		for (int i = 0; i < desiredEnderChestSize; i++) {
			slotMap.put("enderchest." + i, 200 + i);
		}

		return map;
	}

}
//?}
