package de.skyl.hook.main;
// Coded By SkylTV //
// Copyright SkylTV //

public class Core {

    public static Core core;
    private Main main;
    private Manager manager;

    public Core(Main main){
        core = this;
        this.main = main;
        manager = new Manager(main);
    }

    public void enableCore(){
        manager.registerCommands();
        manager.registerListener();
    }

    public static Core getCore() {
        return core;
    }

    public Main getMain() {
        return main;
    }

    public Manager getManager() {
        return manager;
    }

    public static void setCore(Core core) {
        Core.core = core;
    }
}
