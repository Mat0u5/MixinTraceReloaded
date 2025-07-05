package net.mat0u5.mixintracereloaded.mixin;

import net.mat0u5.mixintracereloaded.TraceUtils;
import net.minecraft.util.crash.CrashReport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = CrashReport.class)
public abstract class CrashReportMixin {
	@Shadow private StackTraceElement[] stackTrace;

	@Inject(method = "addStackTrace", at = @At(value = "FIELD", target = "Lnet/minecraft/util/crash/CrashReport;otherSections:Ljava/util/List;"))
	private void addStackTrace(StringBuilder crashReportBuilder, CallbackInfo ci) {
		int trailingNewlineCount = 0;
		// Remove trailing \n
		if (crashReportBuilder.charAt(crashReportBuilder.length() - 1) == '\n') {
			crashReportBuilder.deleteCharAt(crashReportBuilder.length() - 1);
			trailingNewlineCount++;
		}
		if (crashReportBuilder.charAt(crashReportBuilder.length() - 1) == '\n') {
			crashReportBuilder.deleteCharAt(crashReportBuilder.length() - 1);
			trailingNewlineCount++;
		}
		TraceUtils.printTrace(stackTrace, crashReportBuilder);
		crashReportBuilder.append("\n".repeat(trailingNewlineCount));
	}
}
