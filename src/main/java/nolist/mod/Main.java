package nolist.mod;

import arc.Core;
import arc.struct.Seq;
import arc.util.Reflect;
import mindustry.Vars;
import mindustry.gen.Icon;
import mindustry.mod.Mod;
import mindustry.net.SteamAdmin;

@SuppressWarnings("unused")
public class Main extends Mod {

    private static final String settingsPageHeader = """
        Settings for NSGL (No Steam Global List).
        
        [lightgray]Mindustry fetches [accent]Steam ID[] lists from these URLs:
        - $LIST.
        
        These lists can ban players and, worse, [#F]grant admin privileges[] without your explicit consent.
        
        [accent]NSGL lets you choose whether these lists are enabled.
        """.replace("$LIST", Seq.with(Vars.steamBansURLs).toString("\n- "));

    private static final String globalSteamBanHeader = """
        [lightgray]Enable the global Steam ban list. (default: off)
        
        When enabled, this list will [accent]ban players[] whose Steam IDs appear in it.
        """;

    private static final String globalSteamAdminHeader = """
        [lightgray]Enable the global Steam admin list. (default: off)
        
        When enabled, this list will [#f]grant admin[] to players whose Steam IDs appear in it.
        """;

    private static final String noSteamError = """
        NSGL was [#F]not loaded[] because this is [accent]not a Steam instance[].
        
        The mod would have [accent]no effect[] here because the game [#00f]does not load the global Steam lists[] on non-Steam instances.
        """;

    @Override
    public void init(){
        if(!Vars.steam) {
            if(!Core.settings.getBool("Hide NSGL No-Steam Warning", false)) Vars.ui.showErrorMessage(noSteamError);
            return;
        }

        Reflect.set(
            Reflect.get(
                SteamAdmin.class,
                "data"
            ),
            "bans",
            new ConfigurableObjectSet<>("bans")
        );

        Reflect.set(
            Reflect.get(
                SteamAdmin.class,
                "data"
            ),
            "admins",
            new ConfigurableObjectSet<>("admins")
        );

        Vars.ui.settings.addCategory("No Steam Global List", Icon.steam, t->{
            t.labelWrap(settingsPageHeader).row();

            t.table().growY();

            t.labelWrap(globalSteamBanHeader).row();
            t.checkPref("bans", false);

            t.labelWrap(globalSteamAdminHeader).row();
            t.checkPref("admins", false);

            t.table().growY();

            t.checkPref("Hide NSGL No-Steam Warning", false);
        });
    }
}
