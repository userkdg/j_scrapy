package cn.kindg.jscrapy.controller;

import cn.kindg.core.base.ScrapyLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 需要登陆授权后携带登陆后的信息eg:Cookies || token 等，控制器人口
 */
@RestController
@RequestMapping("/web/login")
public class LoginController implements ScrapyLog {

}
