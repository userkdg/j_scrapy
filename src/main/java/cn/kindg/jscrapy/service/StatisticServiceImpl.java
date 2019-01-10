package cn.kindg.jscrapy.service;


import cn.kindg.core.base.ScrapyLog;
import cn.kindg.core.base.ServiceHandler;

public class StatisticServiceImpl implements ScrapyLog, ServiceHandler {

    @Override
    public void doService() {
        log.debug("统计分析");
    }

}
