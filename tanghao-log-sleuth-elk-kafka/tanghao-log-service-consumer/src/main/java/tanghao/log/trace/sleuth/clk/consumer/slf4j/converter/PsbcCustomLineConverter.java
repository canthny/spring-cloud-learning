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
import org.apache.commons.lang.StringUtils;
import tanghao.log.trace.sleuth.clk.consumer.slf4j.LogConstant;

/**
 * <p>
 *  类注释
 * </p>
 * <p>日期: 2017-07-04 05:22   </p>
 * @author yuZhou
 */
public class PsbcCustomLineConverter extends MessageConverter
{
    @Override
    public String convert(ILoggingEvent event)
    {
        String line;
        String message = event.getFormattedMessage();
        if (StringUtils.isNotBlank(message) && (message.startsWith(LogConstant.LOG_JSON_DATA_METHOD_PARAMETERS_PREFIX)||message.startsWith(LogConstant.LOG_JSON_DATA_METHOD_RESULT_PREFIX)))
        {
            line = CallerData.NA;
        }
        else
        {
            line = getLine(event);
        }
        
        return line;
    }
    
    private String getLine(ILoggingEvent event)
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
}
