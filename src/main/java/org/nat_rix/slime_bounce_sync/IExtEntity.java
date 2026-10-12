package org.nat_rix.slimy_launch_patch;

public interface IExtEntity {
  int getSlimeBounceTicks();
  void setSlimeBounceTicks(int val);

  default void forcePlayerSyncMotion() {
    if ((Object) this instanceof net.minecraft.world.entity.player.Player player) {
      if ((Object) this instanceof net.minecraft.server.level.ServerEntity serverEntity)
        serverEntity.sendChanges();
    }
  }
}
