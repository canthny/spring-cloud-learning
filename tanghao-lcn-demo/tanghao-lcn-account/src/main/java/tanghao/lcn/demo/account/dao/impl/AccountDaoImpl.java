package tanghao.lcn.demo.account.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tanghao.lcn.demo.account.dao.AccountDao;
import tanghao.lcn.demo.account.domain.Account;

/**
 * @Author： Canthny
 * @Description： 账户dao实现类
 * @Date： Created in 2018/5/11 10:30
 */
@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int createAccount(Account account) {
        String sql = "insert into ep_account(account_no,balance,freeze_balance,gmt_create,gmt_modified) values  (?,?,?,sysdate(),sysdate())";
        return jdbcTemplate.update(sql,account.getAccountNo(),account.getBalance(),account.getFreezeBalance());
    }

    @Override
    public int updateAccount(Account account) {
        String sql = "update ep_account set balance ="+account.getBalance()+" , freeze_balance = "+account.getFreezeBalance()+" where id="+account.getId();
        return jdbcTemplate.update(sql);
    }
}
