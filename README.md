# Fakeop

https://www.spigotmc.org/resources/fakeop.128986/

Troll your friends with this minecraft plugin

Detailed Usages:

FakeOP:

FakeOp allows you to trick a player into them thinking they're opped with a simple but effective opped message, use the
-v flag in the command to allow the entire server to see that you have "opped" the user.

FakeGamemode:

FakeGamemode utilizes Protocollib's GAME_STATE_CHANGE packet to make the client think they're in creative mode or
another gamemode. This goes with the same restrictions as the players "real" gamemode (The gamemode the player was in
before the command was ran).

FakeItem:

FakeItem utilizes ProtocolLib's SET_SLOT packet to make the client think that their inventory was updated and they have
a new item. This "new" item is what I call a ghost item, when the player does anything that updates their inventory the
item goes into thin air, this is safe and not at all dangerous to do as the client never actually recieves an item.

#### PS: HOLD THE ITEM YOU WANT TO MAKE A GHOST ITEM WHEN RUNNING THE COMMAND

NOTE: if the targetted user is in creative mode the item may stay in the inventory, as creative mode can save items (
like hotbar slots). also dont get creative mode confused with FakeGamemode as fakegamemode will not save the items and
will have the same restrictions as the "real" gamemode.

.

![img.png](img.png)

## ReadmeQuickly

Fakegamemode and FakeItem REQUIRES PROTOCOLLIB to work, if the command /fakegamemode or /fakeitem returns unknown
command then you do
NOT have a
working protocollib working on your server.
Official protocollib: https://github.com/dmulloy2/ProtocolLib

# Dependencys:

This plugin does NOT have any (hard) dependencys, HOWEVER it can use PROTOCOLLIB to enable the FakeGamemode utility,
which allows for the /fakegamemode and the /fakeitem command!
Here is a link to the spigot page for protocolib
https://www.spigotmc.org/resources/protocollib.1997/

# Permissions:

/fakegamemode - fakeop.gamemode

/fakeop - fakeop.op

/fakeitem - fakeop.item

# FakeOP Usages:

/fakeop \<player\> [-v (broadcast)]

##### Player argument:

The player argument must contain an online player, required.

##### (-v) argument:

The -v argument is a request for a fakeop to be broadcasted throughout the server for all players to see, this is an
optional argument.

# FakeGamemode Usages:

/fakeop \<gamemode\> [player]

##### Gamemode argument:

Minecraft gamemode argument, required:

- Adventure
- Creative
- Survival
- Spectator

##### Player argument:

The player argument must contain an online player, optional.

# FakeItem Usages:

/fakeitem \<player\>

##### Player argument:

The player argument must contain an online player, required.

# Why did I make this?

## ^^^^ cuz why not ^^^^