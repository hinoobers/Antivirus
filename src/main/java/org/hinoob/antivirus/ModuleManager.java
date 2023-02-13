package org.hinoob.antivirus;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.Getter;
import lombok.Setter;
import org.hinoob.antivirus.module.Module;
import org.hinoob.antivirus.module.NoMoreOps;
import org.hinoob.antivirus.module.SuspiciousPluginDescription;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    private static ModuleManager instance;

    public static ModuleManager getInstance() {
        if(instance == null){
            instance = new ModuleManager();
        }

        return instance;
    }

    private final List<Module> modules = new ArrayList<>();

    public void register() {
        modules.add(new NoMoreOps());
        modules.add(new SuspiciousPluginDescription());
    }

    public List<Module> get(){
        return modules;
    }
}
