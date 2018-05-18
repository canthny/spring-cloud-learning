package tanghao.lcn.demo.account.dao;

import tanghao.lcn.demo.account.domain.Account;

/**
 * @Author： Canthny
 * @Description： 账户Dao
 * @Date： Created in 2018/5/11 10:27
 */
public interface AccountDao {

     int createAccount(Account account);

     int updateAccount(Account account);
}
