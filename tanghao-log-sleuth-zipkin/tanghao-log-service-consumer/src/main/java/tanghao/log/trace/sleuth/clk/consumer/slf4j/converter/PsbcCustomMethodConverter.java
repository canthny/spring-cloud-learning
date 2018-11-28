/**
 * <p>
 * 文件名称:    com.hundsun.network.hspay.util.log.slf4j.pattern.PsbcCustomMessageConverter
 * </p>
 * <p>
 * 创建时间:    05:22
 * </p>
 *
 * @author yuZhou
 * @since 2017-07-04 05:22
 */
package tanghao.log.trace.sleuth.clk.consumer.slf4j.converter;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import tanghao.log.trace.sleuth.clk.consumer.slf4j.LogConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  类注释
 * </p>
 * <p>日期: 2017-07-04 05:22   </p>
 * @author yuZhou
 */
public class PsbcCustomMethodConverter extends MessageConverter
{
    @Override
    public String convert(ILoggingEvent event)
    {
        String methodName;
        String message = event.getFormattedMessage();
        if (StringUtils.isNotBlank(message))
        {
            if (message.startsWith(LogConstant.LOG_JSON_DATA_METHOD_PARAMETERS_PREFIX))
            {
                methodName = getMethodName(message, LogConstant.LOG_JSON_DATA_METHOD_PARAMETERS_PREFIX);
            }
            else if (message.startsWith(LogConstant.LOG_JSON_DATA_METHOD_RESULT_PREFIX))
            {
                methodName = getMethodName(message, LogConstant.LOG_JSON_DATA_METHOD_RESULT_PREFIX);
            }
            else
            {
                methodName = getMethodName(event);
            }
        }
        else
        {
            methodName = getMethodName(event);
        }
        
        return methodName;
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
    
    private String getMethodName(String message, String prefix)
    {
        String resultInfoMessage = message.substring(prefix.length());
    
        try
        {
            Map<String,String> resultInfoMap = JSON.parseObject(resultInfoMessage, HashMap.class);
            return resultInfoMap.get(LogConstant.LOG_JSON_DATA_INFO_PARAM_NAME_METHOD);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    
        return "";
    }
}
