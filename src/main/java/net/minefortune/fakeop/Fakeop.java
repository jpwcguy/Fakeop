package net.minefortune.fakeop;

import net.minefortune.fakeop.commands.FakeGamemodeCommand;
import net.minefortune.fakeop.commands.FakeItemCommand;
import net.minefortune.fakeop.commands.FakeOpCommand;
import net.minefortune.fakeop.events.FakeItemListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Fakeop extends JavaPlugin {
    FakeOpCommand fakeOpCommand = null;
    FakeGamemodeCommand fakeGamemodeCommand = null;
    FakeItemCommand fakeItemCommand = null;

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
                getLogger().info("Fakegamemode command could not be registered!");
            }
            fakeItemCommand = new FakeItemCommand();
            if (getCommand("fakeitem") != null) {
                getCommand("fakeitem").setExecutor(fakeItemCommand);
                getCommand("fakeitem").setTabCompleter(fakeItemCommand);
                getServer().getPluginManager().registerEvents(new FakeItemListener(), this);
                getLogger().info("FakeItem command registered, protcollib supported");
            } else {
                getLogger().info("FakeItem command could not be registered!");
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
