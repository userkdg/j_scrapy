package cn.kindg.core.interceptor;

import cn.kindg.core.annotation.AppLog;
import cn.kindg.core.log.AppLogData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.Ordered;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.lang.Nullable;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

public class AppLogInterceptor implements HandlerInterceptor, Ordered {
    private static Logger logger = LoggerFactory.getLogger(AppLogInterceptor.class);
    private NamedThreadLocal<StopWatch> stopWatchThreadLocal = new NamedThreadLocal("AppStopWatch");
    private ApplicationContext applicationContext;

    public AppLogInterceptor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("执行日志拦截器...");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        this.stopWatchThreadLocal.set(stopWatch);
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        StopWatch stopWatch = (StopWatch) this.stopWatchThreadLocal.get();
        stopWatch.stop();
        this.stopWatchThreadLocal.remove();
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AppLog operationLog = (AppLog) handlerMethod.getMethodAnnotation(AppLog.class);
            if (operationLog != null) {
                String resourceCode = operationLog.resourceCode();
                String resourceName = operationLog.resourceName();
                if (StringUtils.isBlank(resourceCode) && StringUtils.isBlank(resourceName)) {
                    AppLog resourceLog = (AppLog) handlerMethod.getBeanType().getAnnotation(AppLog.class);
                    if (resourceLog != null) {
                        resourceCode = resourceLog.code();
                        resourceName = resourceLog.name();
                    }
                }

                if (StringUtils.isBlank(resourceCode)) {
                    logger.warn("未设置资源编码");
                }

                if (StringUtils.isBlank(resourceName)) {
                    logger.warn("未设置资源名称");
                }

                EvaluationContext context = new StandardEvaluationContext();
                Arrays.stream(handlerMethod.getMethodParameters()).forEach((param) -> {
                    if (param.hasParameterAnnotation(PathVariable.class)) {
                        Object attribute = request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
                        if (attribute != null) {
                            Map<String, Object> pathVariables = (Map) attribute;
                            context.setVariable(param.getParameterName(), pathVariables.get(param.getParameterName()));
                        }
                    } else if (!param.hasParameterAnnotation(RequestBody.class)) {
                        context.setVariable(param.getParameterName(), request.getParameter(param.getParameterName()));
                    }

                });
                ExpressionParser parser = new SpelExpressionParser();
                String content = resourceName + "【 " + parser.parseExpression(operationLog.name(), new TemplateParserContext()).getValue(context) + "】";
                AppLogData logData = this.buildLogData(resourceCode, operationLog.code(), content, stopWatch.getTotalTimeMillis());
                this.applicationContext.publishEvent(logData);
            }
        } else {
            logger.warn("该类型处理器未进行业务日志记录：{}", handler);
        }

    }

    private AppLogData buildLogData(String resourceCode, String operationCode, String content, Long executionTime) {
        return new AppLogData().setResourceCode(resourceCode).setOperationCode(operationCode).setContent(content)
                .setHandlerTime(executionTime).setCreateTime(LocalDateTime.now());
    }

    public int getOrder() {
        return -98;
    }
}

