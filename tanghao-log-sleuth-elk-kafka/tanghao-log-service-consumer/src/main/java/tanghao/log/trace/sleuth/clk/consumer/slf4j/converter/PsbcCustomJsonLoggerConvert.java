/**
 * <p>
 * 文件名称:    com.hundsun.network.hspay.util.log.slf4j.pattern.PsbcCustomJsonLoggerConvert
 * </p>
 * <p>
 * 创建时间:    14:55
 * </p>
 *
 * @author yuZhou
 * @since 2017-05-08 14:55
 */
package tanghao.log.trace.sleuth.clk.consumer.slf4j.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang.StringUtils;
import tanghao.log.trace.sleuth.clk.consumer.slf4j.LogConstant;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 类注释
 * </p>
 * <p>日期: 2017-05-08 14:55   </p>
 *
 * @author yuZhou
 */
public class PsbcCustomJsonLoggerConvert extends ClassicConverter
{
    protected static final String JSON_DATA_MESSAGE_PLACEHOLDER = "{{jsonDataMessage}}";
    protected static final String JSON_LOG_DATA_NAME_LINE = "line";
    protected static final String JSON_LOG_DATA_NAME_PARAMETERS = "PARAMETERS";
    protected static final String JSON_LOG_DATA_NAME_RESULT = "RESULT";
    
    protected long lastTimestamp = -1;
    protected String timestampStrCache = null;
    protected SimpleDateFormat simpleFormat = null;
    protected String businessName = null;
    
    @Override
    public String convert(ILoggingEvent event)
    {
        CustomLogger customLogger = new CustomLogger();
        customLogger.setSeqId(getSeqId(event));
        customLogger.setParentId(getParentId(event));
        customLogger.setBusiness(businessName);
        customLogger.setTime(getTime(event));
        customLogger.setLeave(event.getLevel().toString());
        customLogger.setPackageName(getPackageName(event));
        customLogger.setClassName(getFullyQualifiedName(event));
        // customLogger.setMethodName(getMethodName(event));
        customLogger.setThreadName(getThreadName(event));
        customLogger.setLine(getLineNumber(event));
        customLogger.setMessage(event.getFormattedMessage());
        return customLogger.toJsonString(event);
    }
    
    @Override
    public void start()
    {
        businessName = getFirstOption();
        businessName = businessName == null ? "未设置产品线" : businessName;
        String datePattern = LogConstant.LOG_DATE_TIME_PATTERN;
        try
        {
            simpleFormat = new SimpleDateFormat(datePattern);
        }
        catch (IllegalArgumentException e)
        {
            addWarn("Could not instantiate SimpleDateFormat with pattern " + datePattern, e);
            // default to the ISO8601 format
            simpleFormat = new SimpleDateFormat(CoreConstants.ISO8601_PATTERN);
        }
        List optionList = getOptionList();
        // if the option list contains a TZ option, then set it.
        if (optionList != null && optionList.size() > 1)
        {
            TimeZone tz = TimeZone.getTimeZone((String) optionList.get(1));
            simpleFormat.setTimeZone(tz);
        }
    }
    
    private String getTime(ILoggingEvent le)
    {
        long timestamp = le.getTimeStamp();
        synchronized (this)
        {
            // if called multiple times within the same millisecond
            // return cache value
            if (timestamp == lastTimestamp)
            {
                return timestampStrCache;
            }
            else
            {
                lastTimestamp = timestamp;
                // SimpleDateFormat is not thread safe.
                // See also http://jira.qos.ch/browse/LBCLASSIC-36
                timestampStrCache = simpleFormat.format(new Date(timestamp));
                return timestampStrCache;
            }
        }
    }
    
    private String getSeqId(ILoggingEvent event)
    {
        return event.getMDCPropertyMap().get(LogConstant.LOG_PARAM_NAME_SEQ_ID);
    }
    
    private String getParentId(ILoggingEvent event)
    {
        return event.getMDCPropertyMap().get(LogConstant.LOG_PARAM_NAME_PARENT_ID);
    }
    
    private String getThreadName(ILoggingEvent event)
    {
        return event.getThreadName();
    }
    
    private String getPackageName(ILoggingEvent event)
    {
        StackTraceElement[] cda = event.getCallerData();
        if (cda != null && cda.length > 0)
        {
            return cda[0].getClassName();
        }
        else
        {
            return CallerData.NA;
        }
    }
    
    private String getFullyQualifiedName(ILoggingEvent event)
    {
        StackTraceElement[] cda = event.getCallerData();
        if (cda != null && cda.length > 0)
        {
            return cda[0].getClassName();
        }
        else
        {
            return CallerData.NA;
        }
    }
    
    private String getLineNumber(ILoggingEvent event)
    {
        StackTraceElement[] cda = event.getCallerData();
        if (cda != null && cda.length > 0)
        {
            return Integer.toString(cda[0].getLineNumber());
        }
        else
        {
            return CallerData.NA;
        }
    }
    
    private String getMethodName(ILoggingEvent event)
    {
        StackTraceElement[] cda = event.getCallerData();
        if (cda != null && cda.length > 0)
        {
            return cda[0].getMethodName();
        }
        else
        {
            return CallerData.NA;
        }
    }
    
    public static class CustomLogger
    {
        private static final String JSON_DATA_FILL_PLACEHOLDER = "\"" + JSON_DATA_MESSAGE_PLACEHOLDER + "\"";
        private static final String JSON_LOG_DATA_NAME_MESSAGE = "message";
    
        /**
         * 日志标识号
         */
        @JSONField(name = "SEQ-ID")
        private String seqId;
        /**
         * 上层模块编码
         */
        @JSONField(name = "PARENT-ID")
        private String parentId;
        /**
         * 产品线
         */
        @JSONField(name = "LOG-TYPE")
        private String business;
        /**
         * 时间
         */
        @JSONField(name = "DATE")
        private String time;
        /**
         * 日志级别
         */
        @JSONField(name = "LEVEL")
        private String leave;
        /**
         * 线程名称
         */
        @JSONField(name = "THREAD-ID")
        private String threadName;
        
        @JSONField(name = "PACKAGE")
        private String packageName;
        /**
         * 类名
         */
        @JSONField(name = "PACKAGE", serialize = false)
        private String className;
        /**
         * 方法名
         */
        private String methodName;
        /**
         * 行数
         */
        @JSONField(serialize = false)
        private String line;
        /**
         * 日志内容
         */
        @JSONField(name = "CONTENT", serialize = false)
        private String message;
        /**
         * 接口方法入参
         */
        @JSONField(name = "PARAMETERS", serialize = false)
        private String parameters;
        /**
         * 接口方法返回结果
         */
        @JSONField(name = "RESULT", serialize = false)
        private String result;
        
        @JSONField(name = "ResponseTime")
        private Long executeTime;
        /**
         * 业务返回代码
         */
        @JSONField(serialize = false)
        private String businessCode;
        /**
         * 业务返回消息
         */
        @JSONField(serialize = false)
        private String businessMessage;

        /**
         * 日志自定义内容
         */
        @JSONField(name = "CONTENT")
        private Map<String, Object> dataMap = new HashMap<>();
    
        public String getSeqId()
        {
            return seqId;
        }
    
        public void setSeqId(String seqId)
        {
            this.seqId = seqId;
        }
    
        public String getParentId()
        {
            return parentId;
        }
    
        public void setParentId(String parentId)
        {
            this.parentId = parentId;
        }
    
        public String getTime()
        {
            return time;
        }
        
        public void setTime(String time)
        {
            this.time = time;
        }
        
        public String getLeave()
        {
            return leave;
        }
        
        public void setLeave(String leave)
        {
            this.leave = leave;
        }
    
        public String getThreadName()
        {
            return threadName;
        }
    
        public void setThreadName(String threadName)
        {
            this.threadName = threadName;
        }
    
        public String getPackageName()
        {
            return packageName;
        }
    
        public void setPackageName(String packageName)
        {
            this.packageName = packageName;
        }
    
        public String getClassName()
        {
            return className;
        }
        
        public void setClassName(String className)
        {
            this.className = className;
        }
        
        public String getMethodName()
        {
            return methodName;
        }

        public void setMethodName(String methodName)
        {
            this.methodName = methodName;
        }
        
        public String getLine()
        {
            return line;
        }

        public void setLine(String line)
        {
            this.line = line;
            if (StringUtils.isNotBlank(line))
            {
                dataMap.put(JSON_LOG_DATA_NAME_LINE, line);
            }
            else
            {
                dataMap.remove(JSON_LOG_DATA_NAME_LINE);
            }
        }
        
        public String getMessage()
        {
            return message;
        }
        
        public void setMessage(String message)
        {
            this.message = message;
            if (StringUtils.isNotBlank(message))
            {
                dataMap.put(JSON_LOG_DATA_NAME_MESSAGE, message);
            }
        }
        
        public String getBusiness()
        {
            return business;
        }
        
        public void setBusiness(String business)
        {
            this.business = business;
        }
    
        public String getParameters()
        {
            return parameters;
        }
    
        public void setParameters(String parameters)
        {
            this.parameters = parameters;
            if (StringUtils.isNotBlank(parameters))
            {
                dataMap.put(JSON_LOG_DATA_NAME_PARAMETERS, parameters);
            }
        }
    
        public String getResult()
        {
            return result;
        }
    
        public void setResult(String result)
        {
            this.result = result;
            if (StringUtils.isNotBlank(result))
            {
                dataMap.put(JSON_LOG_DATA_NAME_RESULT, result);
            }
        }
    
        public Long getExecuteTime()
        {
            return executeTime;
        }
    
        public void setExecuteTime(Long executeTime)
        {
            this.executeTime = executeTime;
        }

        public String getBusinessCode()
        {
            return businessCode;
        }

        public void setBusinessCode(String businessCode)
        {
            this.businessCode = businessCode;
            if (StringUtils.isNotBlank(businessCode))
            {
                dataMap.put(LogConstant.LOG_JSON_DATA_INFO_PARAM_NAME_RETURN_CODE, businessCode);
            }
        }

        public String getBusinessMessage()
        {
            return businessMessage;
        }

        public void setBusinessMessage(String businessMessage)
        {
            this.businessMessage = businessMessage;
            if (StringUtils.isNotBlank(businessMessage))
            {
                dataMap.put(LogConstant.LOG_JSON_DATA_INFO_PARAM_NAME_RETURN_MSG, businessMessage);
            }
        }

        public Map<String, Object> getDataMap()
        {
            return dataMap;
        }
    
        public void setDataMap(Map<String, Object> dataMap)
        {
            this.dataMap = dataMap;
        }
    
        public String toJsonString(ILoggingEvent event)
        {
            String result;
            String message = this.getMessage();
            if (StringUtils.isNotBlank(message))
            {
                if (message.startsWith(LogConstant.LOG_JSON_DATA_PREFIX))
                {
                    setMessage(JSON_DATA_MESSAGE_PLACEHOLDER);
                    result = JSON.toJSONString(this);
                    result = result.replace(JSON_DATA_FILL_PLACEHOLDER, message.substring(LogConstant.LOG_JSON_DATA_PREFIX.length()));
                }
                else if (message.startsWith(LogConstant.LOG_JSON_DATA_BUSINESS_SYSTEM_RESULT_PREFIX))
                {
                    String printMessage = buildPrintMessage(message, LogConstant.LOG_JSON_DATA_BUSINESS_SYSTEM_RESULT_PREFIX);
                    setMessage(printMessage);
                    result = JSON.toJSONString(this);
                }
                else if (message.startsWith(LogConstant.LOG_JSON_DATA_METHOD_PARAMETERS_PREFIX))
                {
                    setMessage("开始接口调用...");
                    setLine(null);
                    setParameters(JSON_DATA_MESSAGE_PLACEHOLDER);
                    setPackageName(event.getLoggerName());
                    setClassName(event.getLoggerName());
                    String printMessage = buildPrintMessage(message, LogConstant.LOG_JSON_DATA_METHOD_PARAMETERS_PREFIX);
                    result = JSON.toJSONString(this);
                    result = result.replace(JSON_DATA_FILL_PLACEHOLDER, printMessage);
                }
                else if (message.startsWith(LogConstant.LOG_JSON_DATA_METHOD_RESULT_PREFIX))
                {
                    setLine(null);
                    setPackageName(event.getLoggerName());
                    setClassName(event.getLoggerName());
                    String printMessage = buildPrintMessage(message, LogConstant.LOG_JSON_DATA_METHOD_RESULT_PREFIX);
                    setMessage("接口调用完成, 总耗时 " + getExecuteTime() + " ms...");
                    boolean nullResult = StringUtils.isNotBlank(printMessage);
                    if (nullResult)
                    {
                        setResult(JSON_DATA_MESSAGE_PLACEHOLDER);
                    }
                    result = JSON.toJSONString(this);
                    if (nullResult)
                    {
                        result = result.replace(JSON_DATA_FILL_PLACEHOLDER, printMessage);
                    }
                }
                else
                {
                    result = JSON.toJSONString(this);
                }
            }
            else
            {
                result = JSON.toJSONString(this);
            }
            return result;
        }
    
        private String buildPrintMessage(String message, String prefix)
        {
            String resultInfoMessage = message.substring(prefix.length());
        
            try
            {
                Map<String, String> resultInfoMap = JSON.parseObject(resultInfoMessage, HashMap.class);

                // 特殊处理: 新增对业务返回代码等信息输出
                if (resultInfoMap.containsKey(LogConstant.LOG_JSON_DATA_INFO_PARAM_NAME_RETURN_CODE))
                {
                    setBusinessCode(resultInfoMap.get(LogConstant.LOG_JSON_DATA_INFO_PARAM_NAME_RETURN_CODE));
                    setBusinessMessage(resultInfoMap.get(LogConstant.LOG_JSON_DATA_INFO_PARAM_NAME_RETURN_MSG));

                    return resultInfoMap.get(LogConstant.LOG_JSON_DATA_INFO_PARAM_NAME_RETURN_MSG);
                }

                setMethodName(resultInfoMap.get(LogConstant.LOG_JSON_DATA_INFO_PARAM_NAME_METHOD));

                Long executeTime = null;
                try
                {
                    executeTime = Long.valueOf(resultInfoMap.get(LogConstant.LOG_JSON_DATA_INFO_PARAM_NAME_EXECUTE_TIME));
                }
                catch (NumberFormatException ignored)
                {
                }
                if (executeTime != null && executeTime > 0L)
                {
                    setExecuteTime(executeTime);
                }

                return resultInfoMap.get(LogConstant.LOG_JSON_DATA_INFO_PARAM_NAME_DATA);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        
            return "";
        }
    }
}
