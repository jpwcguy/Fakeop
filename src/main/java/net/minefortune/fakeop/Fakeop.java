package net.minefortune.fakeop;

import net.minefortune.fakeop.commands.FakeOpCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Fakeop extends JavaPlugin {
    FakeOpCommand fakeOpCommand = null;

    @Override
    public void onEnable() {
        fakeOpCommand = new FakeOpCommand();
        if (getCommand("fakeop") != null) {
            getCommand("fakeop").setExecutor(fakeOpCommand);
            getCommand("fakeop").setTabCompleter(fakeOpCommand);
        } else {
            getLogger().info("Fakeop command could not be registered!");
        }
    }

}
