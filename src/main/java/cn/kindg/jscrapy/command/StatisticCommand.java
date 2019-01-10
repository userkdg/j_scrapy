package cn.kindg.jscrapy.command;

import cn.kindg.core.base.ICommand;
import cn.kindg.core.base.ServiceHandler;

/**
 * 统计分析指令
 */
public class StatisticCommand implements ICommand {
    /**
     * 具体统计分析抽象接口，业务服务类
     */
    private ServiceHandler serviceHandler;

    public StatisticCommand() {
    }

    public StatisticCommand(ServiceHandler serviceHandler) {
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
