package org.nat_rix.slimy_launch_patch.mixin;

import org.spongepowered.asm.mixin.Unique;

@org.spongepowered.asm.mixin.Mixin(net.minecraft.world.entity.Entity.class)
public abstract class EntitySlimeBounceCounter implements org.nat_rix.slimy_launch_patch.IExtEntity {
  @Unique private int slimeBounceTicks = 0;

  @Unique @Override public int getSlimeBounceTicks() { return this.slimeBounceTicks; }
  @Unique @Override public void setSlimeBounceTicks(int val) { this.slimeBounceTicks = val; }

  @org.spongepowered.asm.mixin.injection.Inject(
    at = @org.spongepowered.asm.mixin.injection.At("HEAD"),
    method = "tick"
  )
  private void decrementSlimeBounceTicks(final org.spongepowered.asm.mixin.injection.callback.CallbackInfo info) {
    if (this.slimeBounceTicks > 0) this.slimeBounceTicks--;
  }
}
