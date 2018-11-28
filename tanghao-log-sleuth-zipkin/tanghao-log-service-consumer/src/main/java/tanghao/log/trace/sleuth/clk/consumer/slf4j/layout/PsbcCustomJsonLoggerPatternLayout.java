/**
 * <p>
 * 文件名称:    com.hundsun.network.hspay.util.log.slf4j.layout.PsbcCustomJsonLoggerPatternLayout
 * </p>
 * <p>
 * 创建时间:    15:03
 * </p>
 *
 * @author yuZhou
 * @since 2017-05-08 15:03
 */
package tanghao.log.trace.sleuth.clk.consumer.slf4j.layout;

import ch.qos.logback.classic.PatternLayout;
import tanghao.log.trace.sleuth.clk.consumer.slf4j.converter.PsbcCustomJsonLoggerConvert;
import tanghao.log.trace.sleuth.clk.consumer.slf4j.converter.PsbcCustomLineConverter;
import tanghao.log.trace.sleuth.clk.consumer.slf4j.converter.PsbcCustomMessageConverter;
import tanghao.log.trace.sleuth.clk.consumer.slf4j.converter.PsbcCustomMethodConverter;

/**
 * <p>
 *  类注释
 * </p>
 * <p>日期: 2017-05-08 15:03   </p>
 * @author yuZhou
 */
public class PsbcCustomJsonLoggerPatternLayout extends PatternLayout
{
    static
    {
        defaultConverterMap.put("psbcCustomJsonLoggerPattern", PsbcCustomJsonLoggerConvert.class.getName());
    
//        defaultConverterMap.put("M", PsbcCustomMethodConverter.class.getName());
//        defaultConverterMap.put("method", PsbcCustomMethodConverter.class.getName());
//
//        defaultConverterMap.put("L", PsbcCustomLineConverter.class.getName());
//        defaultConverterMap.put("line", PsbcCustomLineConverter.class.getName());
//
//        defaultConverterMap.put("m", PsbcCustomMessageConverter.class.getName());
//        defaultConverterMap.put("msg", PsbcCustomMessageConverter.class.getName());
//        defaultConverterMap.put("message", PsbcCustomMessageConverter.class.getName());
    }
}
