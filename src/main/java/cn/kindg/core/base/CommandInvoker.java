package cn.kindg.core.base;

import com.google.common.collect.Lists;

import java.util.List;

public class CommandInvoker {
    private List<ICommand> commands = Lists.newArrayList();
    private ICommand command;

    public CommandInvoker setCommand(ICommand command) {
        this.command = command;
        commands.add(command);
        return this;
    }

    public ICommand getCommand() {
        return command;
    }

    public void execute(){
        commands.forEach(ICommand::action);
    }

}
