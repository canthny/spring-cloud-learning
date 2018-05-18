package tanghao.lcn.demo.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tanghao.lcn.demo.account.dao.AccountDao;
import tanghao.lcn.demo.account.domain.Account;

/**
 * @Author： Canthny
 * @Description： 账户控制类
 * @Date： Created in 2018/5/11 10:22
 */
@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    AccountDao accountDao;

    @RequestMapping(value = "/createAccount")
    @ResponseBody
    public int createAccount(){
        Account account = new Account();
        account.setBalance(99999999999999999L);
        account.setFreezeBalance(0L);
        return accountDao.createAccount(account);
    }

    @RequestMapping(value = "/updateAccount")
    @ResponseBody
    public int updateAccount(){
        Account account = new Account();
        account.setId(31L);
        account.setBalance(999999999999L);
        account.setFreezeBalance(1L);
        return accountDao.updateAccount(account);
    }
}
