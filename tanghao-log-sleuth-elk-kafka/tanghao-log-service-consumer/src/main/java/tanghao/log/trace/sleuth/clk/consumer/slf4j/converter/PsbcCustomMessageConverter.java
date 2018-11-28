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
public class PsbcCustomMessageConverter extends MessageConverter
{
    @Override
    public String convert(ILoggingEvent event)
    {
        String message = event.getFormattedMessage();
        if (StringUtils.isNotBlank(message))
        {
            if (message.startsWith(LogConstant.LOG_JSON_DATA_PREFIX))
            {
                message = message.substring(LogConstant.LOG_JSON_DATA_PREFIX.length());
            }
            else if (message.startsWith(LogConstant.LOG_JSON_DATA_BUSINESS_SYSTEM_RESULT_PREFIX))
            {
                message = message.substring(LogConstant.LOG_JSON_DATA_BUSINESS_SYSTEM_RESULT_PREFIX.length());
            }
            else if (message.startsWith(LogConstant.LOG_JSON_DATA_METHOD_PARAMETERS_PREFIX))
            {
                message = "请求参数集为: " + getMessage(message, LogConstant.LOG_JSON_DATA_METHOD_PARAMETERS_PREFIX);
            }
            else if (message.startsWith(LogConstant.LOG_JSON_DATA_METHOD_RESULT_PREFIX))
            {
                message = "处理返回结果为: " + getMessage(message, LogConstant.LOG_JSON_DATA_METHOD_RESULT_PREFIX);
            }
        }
        return message;
    }
    
    private String getMessage(String message, String prefix)
    {
        String resultInfoMessage = message.substring(prefix.length());
        
        try
        {
            Map<String,String> resultInfoMap = JSON.parseObject(resultInfoMessage, HashMap.class);
            
            String resultMessage = resultInfoMap.get(LogConstant.LOG_JSON_DATA_INFO_PARAM_NAME_DATA);
            
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
                resultMessage = resultMessage + ", 总耗时为: " + executeTime + " ms";
            }
            
            return resultMessage;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return "";
    }
}
