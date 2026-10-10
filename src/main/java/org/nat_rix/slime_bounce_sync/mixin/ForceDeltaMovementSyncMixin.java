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
      static double x,
      static double y,
      static double z,
      org.spongepowered.asm.mixin.injection.callback.CallbackInfo info
  ) {
    auto level = entity.level();
    if (level != null) {
      auto server = level.getServer();
      if (server != null) {
        server.forceGameTimeSynchronization();
      }
    }
  }
}
