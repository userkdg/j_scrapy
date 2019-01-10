package cn.kindg.jscrapy.service;


import cn.kindg.core.base.ScrapyLog;
import cn.kindg.core.base.ServiceHandler;

public class LoginServiceImpl implements ScrapyLog, ServiceHandler {

    @Override
    public void doService() {
        log.debug("登陆成功");
    }

}
