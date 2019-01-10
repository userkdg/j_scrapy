package cn.kindg.jscrapy.command;

import cn.kindg.core.base.ICommand;
import cn.kindg.core.base.ServiceHandler;

/**
 * 爬取指令
 */
public class ScrapyCommand implements ICommand {
    /**
     * 具体爬取抽象接口，业务服务类
     */
    private ServiceHandler serviceHandler;

    public ScrapyCommand() {
    }

    public ScrapyCommand(ServiceHandler serviceHandler) {
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
