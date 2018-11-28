package tanghao.log.trace.sleuth.elk.provider.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tanghao.log.trace.sleuth.elk.provider.annotation.MicroServiceMonitor;
import tanghao.log.trace.sleuth.elk.provider.log.LogConstant;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author： Canthny
 * @Description： 微服务监控AOP
 * @Date： Created in 2018/6/30 21:08
 */
@Component
@Aspect
public class MicroServiceMonitorAop {
    @Value("${spring.application.name}")
    private String appName;

    private static Logger log = LoggerFactory.getLogger(MicroServiceMonitorAop.class);

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private static String ipAddr;

    MicroServiceMonitorAop(){
        try {
            ipAddr =  InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Pointcut("@annotation(tanghao.log.trace.sleuth.elk.provider.annotation.MicroServiceMonitor)")
    private void doMcTraceLog() {}

    @Around("doMcTraceLog()&&@annotation(microServiceMonitor)")
    public Object around(ProceedingJoinPoint pjp,MicroServiceMonitor microServiceMonitor) throws Throwable {

        Class clazz = pjp.getTarget().getClass();//得到当前执行的类
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();//得到执行的方法
        Date beginTime = new Date();
        Object obj = pjp.proceed();
        Date endTime = new Date();
        String excuteTime = String.valueOf(endTime.getTime() - beginTime.getTime());

        MDC.put(LogConstant.X_LOCAL_IP,ipAddr);
        MDC.put(LogConstant.X_CLASS,className);
        MDC.put(LogConstant.X_METHOD,methodName);
        MDC.put(LogConstant.X_MC_TRACE_SERVICE_NAME,microServiceMonitor.serverName());
        MDC.put(LogConstant.X_MC_TRACE_EXCUTE_TIME,excuteTime);
        MDC.put(LogConstant.X_MC_TRACE_REQUEST_TIME,dateFormat.format(beginTime));
        MDC.put(LogConstant.X_MC_TRACE_RESPONSE_TIME,dateFormat.format(endTime));

        log.info("any thing you want");

        MDC.remove(LogConstant.X_CLASS);
        MDC.remove(LogConstant.X_METHOD);
        MDC.remove(LogConstant.X_MC_TRACE_SERVICE_NAME);
        MDC.remove(LogConstant.X_MC_TRACE_EXCUTE_TIME);
        MDC.remove(LogConstant.X_MC_TRACE_REQUEST_TIME);
        MDC.remove(LogConstant.X_MC_TRACE_RESPONSE_TIME);
        return obj;
//        JSONObject json = new JSONObject();
//        json.put("className",className);
//        json.put("methodName",methodName);
//        json.put("excuteTime",excuteTime);
//        json.put("ip",ip_addr);
//        json.put("serverName",appName);
//        json.put("log_level","info");
//        json.put("mc_monitor_timestamp",new Date());
//        log.info(json.toJSONString());

//        log.debug("日志切片处理开始....");
//        String seqNo = UUID.randomUUID().toString();
//        MDC.put(LogConstant.LOG_PARAM_NAME_SEQ_ID, seqNo);
//        MDC.put(LogConstant.LOG_PARAM_NAME_PARENT_ID, appName);
//        Map<String, String> mateData = new HashMap<>();
//        mateData.put(LogConstant.LOG_JSON_DATA_INFO_PARAM_NAME_EXECUTE_TIME, String.valueOf(endTime - beginTime));
//        log.info(LogConstant.LOG_JSON_DATA_METHOD_RESULT_PREFIX + JSON.toJSONString(mateData));
//        log.debug("日志切片处理结束....");

    }
}
