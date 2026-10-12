package org.nat_rix.slimy_launch_patch.mixin;

import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@org.spongepowered.asm.mixin.Mixin(ServerGamePacketListenerImpl.class)
public abstract class InvalidateBadClientPacketsMixin {
  @Shadow private double lastGoodX;
  @Shadow private double lastGoodY;
  @Shadow private double lastGoodZ;

  /**
   * Returns `true` if we have a minimum velocity in an axis and if the client packet movement would cancel out that movement.
   */
  @Unique
  static private boolean isBadMovement(double ourMovement, double target, double lastGood) {
    return Math.abs(ourMovement) > 0.2 && (((target - lastGood) > 0) != (ourMovement > 0));
  }

  @com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation(
    at = @org.spongepowered.asm.mixin.injection.At(
      value = "INVOKE",
      target = "Lnet/minecraft/server/network/ServerGamePacketListenerImpl;handlePlayerPositionChange(DDDFFZZ)V"
    ),
    method = "handleMovePlayer"
  )
  private void invalidateBadClientPacket(
    final ServerGamePacketListenerImpl packetListener,
    final double x,
    final double y,
    final double z,
    final float yRot,
    final float xRot,
    final boolean isOnGround,
    final boolean horColl,
    final com.llamalad7.mixinextras.injector.wrapoperation.Operation<Void> op
  ) {
    if (packetListener.getPlayer() instanceof org.nat_rix.slimy_launch_patch.IExtEntity extEntity
        && extEntity.getSlimeBounceTicks() != 0) {
      var movement = packetListener.getPlayer().getDeltaMovement();
      if (isBadMovement(movement.x, x, lastGoodX)
       || isBadMovement(movement.y, y, lastGoodY)
       || isBadMovement(movement.z, z, lastGoodZ)) return;
    }

    op.call(packetListener, x, y, z, yRot, xRot, isOnGround, horColl);
  }
}
