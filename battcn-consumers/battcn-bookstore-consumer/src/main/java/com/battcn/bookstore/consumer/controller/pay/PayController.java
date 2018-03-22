package com.battcn.bookstore.consumer.controller.pay;

import com.battcn.bookstore.consumer.pojo.PayVo;
import com.battcn.framework.redis.annotation.CacheLock;
import com.battcn.framework.redis.annotation.LockParam;
import com.battcn.framework.redis.sequence.SequenceGenerator;
import com.battcn.framework.redis.sequence.SequenceType;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 支付相关
 *
 * @author Levin
 * @since 2018/3/21 0021
 */
@RequestMapping("/app/pays")
@RestController
@Api(value = "支付管理", description = "支付管理", tags = "3.0")
public class PayController {

    private final SequenceGenerator sequenceGenerator;

    @Autowired(required = false)
    public PayController(SequenceGenerator sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @CacheLock(lockedPrefix = "pay", expire = 100)
    @GetMapping
    public String getPayNo(@RequestParam String id, @RequestParam @LockParam String name, @RequestParam @LockParam int age) {
        return this.sequenceGenerator.generateSequence(SequenceType.BO);
    }

    @CacheLock(lockedPrefix = "shop:book:pay", expire = 100)
    @PutMapping
    public String putPay(@RequestBody PayVo vo) {
        return this.sequenceGenerator.generateSequence(SequenceType.BO);
    }

}
