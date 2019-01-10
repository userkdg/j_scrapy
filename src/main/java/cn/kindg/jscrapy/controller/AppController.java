package cn.kindg.jscrapy.controller;

import cn.kindg.core.annotation.AppLog;
import cn.kindg.core.base.*;
import cn.kindg.jscrapy.command.LoginCommand;
import cn.kindg.jscrapy.command.ScrapyCommand;
import cn.kindg.jscrapy.command.StatisticCommand;
import cn.kindg.jscrapy.service.LoginServiceImpl;
import cn.kindg.jscrapy.service.ScrapyServiceImpl;
import cn.kindg.jscrapy.service.StatisticServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用入口
 */
@RestController
@RequestMapping("/web/app")
public class AppController implements ScrapyLog {

    @AppLog(name = "开始工作", code = "200", resourceCode = "开始工作", resourceName = "开始工作")
    @GetMapping("/work")
    public String doWork() {
        log.debug("开始");

        ServiceHandler loginService = new LoginServiceImpl();
        ICommand loginCommand = new LoginCommand(loginService);

        ServiceHandler scrapyService = new ScrapyServiceImpl();
        ICommand scrapyCommand = new ScrapyCommand(scrapyService);

        ServiceHandler statisticService = new StatisticServiceImpl();
        ICommand statisticCommand = new StatisticCommand(statisticService);

        BeanFactory.create(CommandInvoker::new)
                .setCommand(loginCommand)
                .setCommand(scrapyCommand)
                .setCommand(statisticCommand)
                .execute();
        return "ok";
    }

    @Deprecated
    public static void main(String[] args) {

        BeanFactory.create(ScrapyServiceImpl::new).doService();
        BeanFactory.defaultValue(ScrapyServiceImpl::new).doService();
        BeanFactory.getOrDefault(new ScrapyServiceImpl(), null).doService();

        ServiceHandler loginService = new LoginServiceImpl();
        ICommand loginCommand = new LoginCommand(loginService);

        ServiceHandler scrapyService = new ScrapyServiceImpl();
        ICommand scrapyCommand = new ScrapyCommand(scrapyService);

        ServiceHandler statisticService = new StatisticServiceImpl();
        ICommand statisticCommand = new StatisticCommand(statisticService);

        BeanFactory.create(CommandInvoker::new)
                .setCommand(loginCommand)
                .setCommand(scrapyCommand)
                .setCommand(statisticCommand)
                .execute();
    }
}
