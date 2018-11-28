/**
 * <p>
 * 文件名称:    com.hundsun.network.hspay.util.log.LogConstant
 * </p>
 * <p>
 * 创建时间:    16:01
 * </p>
 *
 * @author yuZhou
 * @since 2017-05-08 16:01
 */
package tanghao.log.trace.sleuth.clk.consumer.slf4j;

/**
 * <p>
 *  类注释
 * </p>
 * <p>日期: 2017-05-08 16:01   </p>
 * @author yuZhou
 */
public interface LogConstant
{
    /**
     * 日志 amd 参数名 - seqId
     */
    String LOG_PARAM_NAME_SEQ_ID = "psbc-log-seq-id";
    /**
     * 日志 amd 参数名 - 上层模块
     */
    String LOG_PARAM_NAME_PARENT_ID = "psbc-log-parent-id";
    /**
     * 日志时间格式
     */
    String LOG_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 日志 JSON 数据前缀
     */
    String LOG_JSON_DATA_PREFIX = "JSON_DATA::";
    /**
     * 日志 JSON 数据前缀
     */
    String LOG_JSON_DATA_METHOD_PARAMETERS_PREFIX = "JSON_DATA_METHOD_PARAMETERS::";
    /**
     * 日志 JSON 数据前缀
     */
    String LOG_JSON_DATA_METHOD_RESULT_PREFIX = "JSON_DATA_METHOD_RESULT::";
    /**
     * 日志 JSON 数据前缀
     */
    String LOG_JSON_DATA_BUSINESS_SYSTEM_RESULT_PREFIX = "JSON_DATA_BUSINESS_SYSTEM_RESULT::";
    /**
     * 日志 JSON 消息数据 参数名 - 执行时间
     */
    String LOG_JSON_DATA_INFO_PARAM_NAME_EXECUTE_TIME = "executeTime";
    
    /**
     * 日志 JSON 消息数据 参数名 - 方法名
     */
    String LOG_JSON_DATA_INFO_PARAM_NAME_METHOD = "methodName";

    /**
     * 日志 JSON 消息数据 参数名 - 业务返回代码
     */
    String LOG_JSON_DATA_INFO_PARAM_NAME_RETURN_CODE = "returnCode";

    /**
     * 日志 JSON 消息数据 参数名 - 业务返回消息
     */
    String LOG_JSON_DATA_INFO_PARAM_NAME_RETURN_MSG = "returnMsg";

    /**
     * 日志 JSON 消息数据 参数名 - 数据
     */
    String LOG_JSON_DATA_INFO_PARAM_NAME_DATA = "data";
}
