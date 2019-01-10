package cn.kindg.jscrapy.command;

import cn.kindg.core.base.ICommand;
import cn.kindg.core.base.ServiceHandler;

/**
 * 登陆指令
 */
public class LoginCommand implements ICommand {
    /**
     * 具体登陆抽象接口，业务服务类
     */
    private ServiceHandler serviceHandler;

    public LoginCommand() {
    }

    public LoginCommand(ServiceHandler serviceHandler) {
        this.serviceHandler = serviceHandler;
    }

    public void setServiceHandler(ServiceHandler serviceHandler) {
        this.serviceHandler = serviceHandler;
    }

    @Override
    public void action() {
        serviceHandler.doService();
    }
}
