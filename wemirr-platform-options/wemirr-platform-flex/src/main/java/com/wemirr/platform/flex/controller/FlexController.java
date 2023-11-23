package com.wemirr.platform.flex.controller;

import com.mybatisflex.core.query.QueryWrapper;
import com.wemirr.platform.flex.domain.entity.Account;
import com.wemirr.platform.flex.repository.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

import static com.wemirr.platform.flex.domain.entity.table.AccountTableDef.ACCOUNT;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/flex")
@RequiredArgsConstructor
public class FlexController {


    private final AccountMapper accountMapper;

    @GetMapping("/list")
    public List<Account> list() {
        final QueryWrapper wrapper1 = QueryWrapper.create().where(ACCOUNT.ID.ge(10));
        accountMapper.selectListByQuery(wrapper1);
        final QueryWrapper wrapper2 = QueryWrapper.create(new Account()).gt(Account::getAge, 10);
        accountMapper.selectListByQuery(wrapper2);
        save();
        edit();
        return accountMapper.selectAll();
    }


    @GetMapping("/save")
    @Transactional(rollbackFor = Exception.class)
    public void save() {
        Account account2 = new Account();
        account2.setUserName("用户");
        account2.setAge(1);
        account2.setBirthday(Instant.now());
        accountMapper.insert(account2);
    }

    public void edit() {
        Account account2 = new Account();
        account2.setId(4L);
        account2.setUserName("用户4");
        account2.setAge(1);
        account2.setBirthday(Instant.now());
        accountMapper.update(account2);
    }


}
