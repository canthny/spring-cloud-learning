/**
 * <p>
 * 文件名称:    com.hundsun.network.hspay.util.log.slf4j.encoder.PsbcCustomJsonLoggerPatternLayoutEncoder
 * </p>
 * <p>
 * 创建时间:    01:47
 * </p>
 *
 * @author yuZhou
 * @since 2017-05-17 01:47
 */
package tanghao.log.trace.sleuth.clk.consumer.slf4j.encoder;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.PatternLayoutEncoderBase;
import tanghao.log.trace.sleuth.clk.consumer.slf4j.layout.PsbcCustomJsonLoggerPatternLayout;

/**
 * <p>
 *  类注释
 * </p>
 * <p>日期: 2017-05-17 01:47   </p>
 * @author yuZhou
 */
public class PsbcCustomJsonLoggerPatternLayoutEncoder extends PatternLayoutEncoderBase<ILoggingEvent>
{
    @Override
    public void start() {
        PsbcCustomJsonLoggerPatternLayout loggerPatternLayout = new PsbcCustomJsonLoggerPatternLayout();
        loggerPatternLayout.setContext(context);
        loggerPatternLayout.setPattern(getPattern());
        loggerPatternLayout.setOutputPatternAsHeader(outputPatternAsHeader);
        loggerPatternLayout.start();
        this.layout = loggerPatternLayout;
        super.start();
    }
}
