<p align="center"> <img alt="The Mod's Icon" src="src/main/resources/assets/slimy_launch_patch/icon.png" /> </p>

# Slimy Launch Patch
A mod for Minecraft Java Edition 26.3 that fixes a bug on the server that sometimes inhibits piston slime block launchers to launch the player.
The bug is caused by the server that interprets old client packages where the player was not launched yet as up-to-date. This mod
tries to filter out such client packages.

This small project was inspired by [xjuunn's fix](https://github.com/xjuunn/piston-bounce-fix), which adds a grace period, but
does change the pistons timing, breaking at least my piston elevator.

# Install instructions

For this mod to run you need the [Fabric Mod loader](https://fabricmc.net/use/installer/) and the [Fabric API Mod](https://modrinth.com/mod/fabric-api).
You only need this mod on the server!
