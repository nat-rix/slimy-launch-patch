package org.nat_rix.slimy_launch_patch.mixin;

@org.spongepowered.asm.mixin.Mixin(net.minecraft.world.level.block.piston.PistonMovingBlockEntity.class)
public class HandleSlimeBouncesMixin {
  @com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation(
    at = @org.spongepowered.asm.mixin.injection.At(
      value = "INVOKE",
      target = "Lnet/minecraft/world/entity/Entity;setDeltaMovement(DDD)V"
    ),
    method = "moveCollidedEntities"
  )
  private static void handleSlimeBounce(
    final net.minecraft.world.entity.Entity entity,
    final double x,
    final double y,
    final double z,
    final com.llamalad7.mixinextras.injector.wrapoperation.Operation<Void> op
  ) {
    if (entity instanceof org.nat_rix.slimy_launch_patch.IExtEntity extEntity) {
      extEntity.setSlimeBounceTicks(7);
      extEntity.forcePlayerSyncMotion();
    }
    op.call(entity, x, y, z);
  }
}
