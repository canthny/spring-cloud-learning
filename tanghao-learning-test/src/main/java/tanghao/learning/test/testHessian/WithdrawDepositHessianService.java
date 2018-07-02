package tanghao.learning.test.testHessian;

import com.hundsun.network.dto.ResponesWithdrawDeposit;
import com.hundsun.network.hspay.common.xmldomain.WithdrawMessageDtailDO;
import org.springframework.stereotype.Component;

/**
 * @Author： Canthny
 * @Description： //TODO 那么请问：这个类是干嘛的呢？
 * @Description：
 * @Date： Created in 2018/6/7 15:46
 */
@Component
public interface WithdrawDepositHessianService {

    public ResponesWithdrawDeposit WithdrawDeposit(WithdrawMessageDtailDO cancel) throws Exception;
}
