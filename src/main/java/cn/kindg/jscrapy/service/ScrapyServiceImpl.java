package cn.kindg.jscrapy.service;


import cn.kindg.core.base.ScrapyLog;
import cn.kindg.core.base.ServiceHandler;

public class ScrapyServiceImpl implements ScrapyLog, ServiceHandler {

    private void scrapy() {
        log.debug("scrapy.....");
    }

    @Override
    public void doService() {
        log.debug("爬取");
        scrapy();
    }
}
