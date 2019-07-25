package com.dt.common.aspect;

import com.dt.common.annotation.LogOperator;
import com.dt.common.bean.ResponseBean;
import com.dt.common.constant.ErrorEnum;
import com.dt.common.utils.CommonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Component
public abstract class AbstractRestControllerAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Object methodAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object object = null;
        Object[] args = proceedingJoinPoint.getArgs();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Class targetClass = proceedingJoinPoint.getTarget().getClass();
        String methodName = signature.getName();
        Method method = signature.getMethod();
        LogOperator logOperator = method.getDeclaredAnnotation(LogOperator.class);
        try {
            object = proceedingJoinPoint.proceed(args);
            Object rs = object;
            if (!(object instanceof ResponseBean)) {
                ResponseBean responseBean = new ResponseBean();
                responseBean.setData(object);
                object = responseBean;
            }
            if (CommonUtils.isNotNull(logOperator)) {
                this.logPrint(logOperator, proceedingJoinPoint, args, rs);
            }
        } catch (Throwable throwable) {
            logger.error("异常报错:[类名:{},方法名:{}],error:{}", targetClass.getName(), methodName, CommonUtils.getExceptioniInformation(throwable));
            ErrorEnum errorEnum = ErrorEnum.ERROR;
            String errorMessage = null;
            Throwable throwableCause = throwable.getCause();
            String errorDetails = CommonUtils.getExceptioniInformation(throwable);
            String errorMsg = throwable.toString();
            if (CommonUtils.isNotNull(throwableCause)) {
                errorMessage = throwableCause.getMessage();
            } else {
                errorMessage = throwable.getMessage();
            }
            object = new ResponseBean(errorEnum.getErrorCode(), errorMessage);
        }
        return object;
    }

    /**
     * 方法标有logOperator注解的处理函数
     *
     * @param logOperator
     * @param proceedingJoinPoint
     * @param args
     */
    protected abstract void logPrint(LogOperator logOperator, ProceedingJoinPoint proceedingJoinPoint, Object[] args, Object returnData);

}
