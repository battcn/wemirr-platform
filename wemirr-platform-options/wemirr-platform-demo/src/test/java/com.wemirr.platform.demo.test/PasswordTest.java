package com.wemirr.platform.demo.test;

import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

public class PasswordTest {

    @SneakyThrows
    @Test
    public void test1() {
        String string = SecureUtil.aes("we-wemirr-groups".getBytes(StandardCharsets.UTF_8)).encryptBase64("123456");
        System.out.println(string);

        // 指定 16 字节（128 位）的密钥和 IV
        String keyStr = "we-wemirr-groups"; // 16 字节
        String ivStr = "we-wemirr-groups";  // 16 字节

        // 将字符串转换为字节数组
        byte[] key = keyStr.getBytes(StandardCharsets.UTF_8);
        byte[] iv = ivStr.getBytes(StandardCharsets.UTF_8);

        // 创建 AES 实例并指定 CTR 模式（不需要填充）
        AES aes = new AES("CTR", Padding.NoPadding.name(), key, iv);

        // 加密
        String data = "123456";
        String encryptedHex = aes.encryptHex(data);
        System.out.println("Encrypted (Hex): " + encryptedHex);

        // 解密
        String decryptedData = aes.decryptStr(encryptedHex);
        System.out.println("Decrypted: " + decryptedData);

        System.out.println(aes.decryptStr("OV9M8/hW"));
    }
}
