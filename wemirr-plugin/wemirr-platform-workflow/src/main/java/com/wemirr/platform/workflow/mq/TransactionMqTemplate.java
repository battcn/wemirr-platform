package com.wemirr.platform.workflow.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author battcn
 * @since 2025/5/28
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionMqTemplate {

//    private final RocketMQTemplate rocketMQTemplate;

    public void sendMessage(String topic, String tag, String message, Boolean afterTransactionCommit) {
        if (TransactionSynchronizationManager.isActualTransactionActive() && afterTransactionCommit) {
            // 当前处于事务中，注册事务提交后发送消息
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    log.info("send message Transaction topic：{},tag:{},message：{}", topic, tag, message);
//                    rocketMQTemplate.convertAndSend(topic + ":" + tag, message);
                }
            });
        } else {
            log.info("send message unTransaction topic：{},tag:{},message：{}", topic, tag, message);
            // 非事务环境，直接发送消息
//            rocketMQTemplate.convertAndSend(topic + ":" + tag, message);
        }
    }

    public void sendMessage(String topic, String message) {
        log.info("send message topic：{},message：{}", topic, message);
//        rocketMQTemplate.convertAndSend(topic, message);
    }

    /**
     * 发送顺序消息，基于businessCode保证顺序消费
     *
     * @param topic 消息主题
     * @param tag 消息标签
     * @param businessCode 业务编码，作为顺序消息的分区键
     * @param message 消息内容
     * @param afterTransactionCommit 是否在事务提交后发送
     */
    public void sendOrderlyMessage(String topic, String tag, String businessCode, String message, Boolean afterTransactionCommit) {
        if (TransactionSynchronizationManager.isActualTransactionActive() && afterTransactionCommit) {
            // 当前处于事务中，注册事务提交后发送顺序消息
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
//                    log.info("send orderly message Transaction topic：{},tag:{},businessCode:{},message：{}", topic, tag, businessCode, message);rocketMQTemplate.syncSendOrderly(topic + ":" + tag, message, businessCode);
                }
            });
        } else {
            log.info("send orderly message unTransaction topic：{},tag:{},businessCode:{},message：{}", topic, tag, businessCode, message);
            // 非事务环境，直接发送顺序消息
//            rocketMQTemplate.syncSendOrderly(topic + ":" + tag, message, businessCode);
        }
    }
}
