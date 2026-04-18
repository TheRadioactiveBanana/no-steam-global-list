package nolist.mod;

import arc.Core;
import arc.struct.ObjectSet;

public class ConfigurableObjectSet<T> extends ObjectSet<T> {

    private final String name;

    public ConfigurableObjectSet(String name){
        this.name = name;
    }

    @Override
    public boolean contains(T key){
        return Core.settings.getBool(name + "-enabled", false) && super.contains(key);
    }
}
