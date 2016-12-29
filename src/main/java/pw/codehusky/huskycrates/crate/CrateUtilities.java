package pw.codehusky.huskycrates.crate;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.ItemStack;
import pw.codehusky.huskycrates.HuskyCrates;
import pw.codehusky.huskycrates.crate.views.CSGOCrateView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lokio on 12/28/2016.
 */
public class CrateUtilities {
    private HashMap<String,VirtualCrate> crateTypes;
    private HashMap<String,ItemStack> keys;
    private HuskyCrates plugin;
    public CrateUtilities(HuskyCrates plugin){
        this.plugin = plugin;
    }
    public void launchCrateForPlayer(String crateType, Player target,HuskyCrates plugin){
        CSGOCrateView view = new CSGOCrateView(plugin,target);
        target.openInventory(view.getInventory(),plugin.genericCause);
    }
    public ItemStack getCrateItemStack(String crateType){
        return null;
    }
    public void generateVirtualCrates(ConfigurationLoader<CommentedConfigurationNode> config){
        try {
            CommentedConfigurationNode configRoot = config.load();
            Map<Object,? extends CommentedConfigurationNode> b = configRoot.getNode("crates").getChildrenMap();
            for(Object prekey: b.keySet()){
                String key = (String) prekey;
                crateTypes.put(key,new VirtualCrate(key,configRoot.getNode("crates",key)));
            }
            config.save(configRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
