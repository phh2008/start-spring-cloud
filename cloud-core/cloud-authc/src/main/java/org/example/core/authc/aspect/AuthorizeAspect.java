package org.example.core.authc.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.core.authc.component.AuthorizeHelper;
import org.example.core.common.annotation.Authorize;
import org.example.core.common.exception.CloudException;
import org.example.core.common.result.ResultCodeEnum;
import org.example.core.tool.utils.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.core.annotation.SynthesizingMethodParameter;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 签权切面
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/29
 */
@Order(2)
@Aspect
@Slf4j
@Component
public class AuthorizeAspect implements ApplicationContextAware {

    private static final ParameterNameDiscoverer PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    private static final ExpressionParser SPEL_PARSER = new SpelExpressionParser();

    private ApplicationContext applicationContext;

    @Autowired
    private AuthorizeHelper authorizeHelper;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Before("execution(public * org.example..*.controller..*.*(..))&&@annotation(authorize)")
    public void doBefore(JoinPoint joinPoint, Authorize authorize) {
        if (doAuthorize(joinPoint, authorize)) {
            return;
        }
        //无权限
        throw new CloudException(ResultCodeEnum.UNAUTHORIZED);
    }

    private boolean doAuthorize(JoinPoint joinPoint, Authorize authorize) {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        //获取注解表达式
        String condition = authorize.value();
        if (StringUtils.isNotBlank(condition)) {
            Expression expression = SPEL_PARSER.parseExpression(condition);
            //把方法参数添加到SPEL
            Object[] args = joinPoint.getArgs();
            StandardEvaluationContext context = getEvaluationContext(method, args);
            //获取SPEL表达式的值
            Boolean valid = expression.getValue(context, Boolean.class);
            return valid != null && valid;
        }
        return false;
    }

    private StandardEvaluationContext getEvaluationContext(Method method, Object[] args) {
        StandardEvaluationContext context = new StandardEvaluationContext(authorizeHelper);
        context.setBeanResolver(new BeanFactoryResolver(applicationContext));
        for (int i = 0; i < args.length; i++) {
            // 获取方法参数信息
            MethodParameter methodParameter = new SynthesizingMethodParameter(method, i);
            methodParameter.initParameterNameDiscovery(PARAMETER_NAME_DISCOVERER);
            // 把请求参数设置为SPEL变量
            context.setVariable(methodParameter.getParameterName(), args[i]);
        }
        return context;
    }


}
