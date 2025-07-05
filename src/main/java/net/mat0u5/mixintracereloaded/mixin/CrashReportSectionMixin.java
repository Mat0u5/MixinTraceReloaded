package net.mat0u5.mixintracereloaded.mixin;

import net.mat0u5.mixintracereloaded.TraceUtils;
import net.minecraft.util.crash.CrashReportSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = CrashReportSection.class)
public abstract class CrashReportSectionMixin {
	@Shadow private StackTraceElement[] stackTrace;

	@Inject(method = "addStackTrace", at = @At("TAIL"))
	private void addStackTrace(StringBuilder crashReportBuilder, CallbackInfo ci) {
		TraceUtils.printTrace(stackTrace, crashReportBuilder);
	}
}
