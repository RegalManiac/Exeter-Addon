package me.sophimoo.exeter;

import me.sophimoo.exeter.gui.themes.base.BaseGuiTheme;
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.gui.GuiThemes;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;

public class BaseAddon extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();

    public static final String MOD_ID = "exeter-addon";

    @Override
    public void onInitialize() {
        LOG.info("Initializing Exeter Theme Addon");

        GuiThemes.add(new BaseGuiTheme());
    }

    public static Identifier identifier(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    @Override
    public String getPackage() {
        return "me.sophimoo.exeter";
    }
}
