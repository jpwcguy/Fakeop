package net.minefortune.fakeop;

import net.minefortune.fakeop.commands.FakeGamemodeCommand;
import net.minefortune.fakeop.commands.FakeOpCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Fakeop extends JavaPlugin {
    FakeOpCommand fakeOpCommand = null;
    FakeGamemodeCommand fakeGamemodeCommand = null;

    @Override
    public void onEnable() {
        fakeOpCommand = new FakeOpCommand();
        if (getServer().getPluginManager().getPlugin("ProtocolLib") != null && getServer().getPluginManager().isPluginEnabled("ProtocolLib")) {
            fakeGamemodeCommand = new FakeGamemodeCommand();
            if (getCommand("fakegamemode") != null) {
                getCommand("fakegamemode").setExecutor(fakeGamemodeCommand);
                getCommand("fakegamemode").setTabCompleter(fakeGamemodeCommand);
                getLogger().info("Fakegamemode command registered, protcollib supported");
            } else {
                getLogger().info("Fakeop command could not be registered!");
            }
        }
        if (getCommand("fakeop") != null) {
            getCommand("fakeop").setExecutor(fakeOpCommand);
            getCommand("fakeop").setTabCompleter(fakeOpCommand);
        } else {
            getLogger().info("Fakeop command could not be registered!");
        }
    }

}
