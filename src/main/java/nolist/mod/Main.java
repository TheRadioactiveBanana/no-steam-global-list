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
        Settings for the No Global Steam List mod.
        
        [lightgray]The game fetches a list of [accent]steam IDs[] from these URLs:
        - $LIST.
        
        This bans some players, and worse, [#F]allows certain users admin privileges[] without your explicit consent.
        
        [accent]This mod allows you to disable these lists.
        """.replace("$LIST", Seq.with(Vars.steamBansURLs).toString("\n- "));

    private static final String globalSteamBanHeader = """
        [lightgray]Enable the global steam ban list. (default=off)
        
        This list will [accent]ban any player[] with these IDs trying to connect to your game.
        """;

    private static final String globalSteamAdminHeader = """
        [lightgray]Enable the global steam admin list. (default=off)
        
        This list will [#f]grant any players with these IDs admin[] on your game.
        """;

    private static final String noSteamError = """
        No Steam Global List - The mod was [#F]not loaded[] as this is [accent]not a steam instance[].
        
        The mod will have [accent]no effect[] as the game [#00f]never loads the steam ban list[], due to not being a steam instance.
        """;

    @Override
    public void init(){
        if(!Vars.steam) {
            if(!Core.settings.getBool("Hush NSGL No Steam Message", false)) Vars.ui.showErrorMessage(noSteamError);
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

            t.checkPref("Hush NSGL No Steam Message", false);
        });
    }
}
