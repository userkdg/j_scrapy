package cn.kindg.config.web;


import cn.kindg.config.activemq.ActiveMQProperties;
import cn.kindg.config.kafka.KafkaProperties;
import cn.kindg.core.base.AppResult;
import cn.kindg.core.exception.JScrapyException;
import cn.kindg.core.interceptor.AppLogInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@EnableConfigurationProperties({KafkaProperties.class, ActiveMQProperties.class})
public class AppWebConfig implements WebMvcConfigurer {
    private static Logger logger = LoggerFactory.getLogger(AppWebConfig.class);

    @Autowired
    private ApplicationContext applicationContext;

    public AppWebConfig() {
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AppLogInterceptor(this.applicationContext));
    }

    @ControllerAdvice
    public static class AppResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
        private Logger logger = LoggerFactory.getLogger(this.getClass());

        public AppResponseEntityExceptionHandler() {
        }

        @ExceptionHandler({Exception.class})
        public ResponseEntity<Object> handleGlobalException(HttpServletRequest request, Exception ex) {
            if (JScrapyException.class.isAssignableFrom(ex.getClass())) {
                this.logger.warn("业务校验异常[{}]...", request.getRequestURI(), ex);
                return new ResponseEntity(AppResult.fail(ex.getMessage()), HttpStatus.OK);
            } else {
                Throwable throwable = NestedExceptionUtils.getRootCause(ex);
                if (throwable != null && throwable instanceof ConstraintViolationException) {
                    this.logger.warn("POJO校验异常[{}].", request.toString());
                    Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) throwable).getConstraintViolations();
                    List<String> validMessages = (List) constraintViolations.stream().map((violation) -> violation.getMessage()).collect(Collectors.toList());
                    return this.buildValidateResponse(validMessages, request.toString());
                } else {
                    this.logger.error(request.getRequestURI(), ex);
                    return new ResponseEntity("系统异常，请稍候重试", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }

        protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
            this.logger.warn("POJO校验异常[{}].", request.toString());
            BindingResult bindResult = ex.getBindingResult();
            List<String> validMessages = (List) bindResult.getFieldErrors().stream().map((field) -> {
                return field.getDefaultMessage();
            }).collect(Collectors.toList());
            return this.buildValidateResponse(validMessages, request.toString());
        }

        protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
            this.logger.error(request.toString(), ex);
            return new ResponseEntity("系统异常，请稍候重试", headers, status);
        }

        private ResponseEntity<Object> buildValidateResponse(List<String> validMessages, String requestInfo) {
            AppResult<Object> result = AppResult.fail("数据校验不通过").addMoreData("valids", validMessages);
            this.logger.warn("POJO校验异常[{}]...{}", requestInfo, StringUtils.join(validMessages));
            return new ResponseEntity(result, HttpStatus.OK);
        }
    }
}