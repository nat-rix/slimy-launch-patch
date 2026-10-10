public class ForceDeltaMovementSyncMixin {
  @org.spongepowered.asm.mixin.injection.Inject(
    at = @org.spongepowered.asm.mixin.injection.At(
      value = "INVOKE",
      target = "Lnet/minecraft/world/entity/Entity;setDeltaMovement(DDD)V"
    ),
    method = "moveCollidedEntities"
  )
  private static void forceDeltaMovementSync(
      net.minecraft.world.entity.Entity entity,
      final double x,
      final double y,
      final double z,
      final org.spongepowered.asm.mixin.injection.callback.CallbackInfo info
  ) {
    var level = entity.level();
    if (level != null) {
      var server = level.getServer();
      if (server != null) {
        server.forceGameTimeSynchronization();
      }
    }
  }
}
