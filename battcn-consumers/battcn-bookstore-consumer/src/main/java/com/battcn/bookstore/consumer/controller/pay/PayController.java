package com.battcn.bookstore.consumer.controller.pay;

import com.battcn.framework.redis.sequence.SequenceGenerator;
import com.battcn.framework.redis.sequence.SequenceType;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public String getPayNo() {
        return this.sequenceGenerator.generateSequence(SequenceType.BO);
    }

}
