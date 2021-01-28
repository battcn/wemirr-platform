//package com.wemirr.platform;
//
//
//import com.deepoove.poi.XWPFTemplate;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Map;
//import java.util.Random;
//
///**
// * http://deepoove.com/poi-tl
// *
// * @author Levin
// * @date 2020-12-07
// */
//@Slf4j
//public class WordUtils {
//
//    private static final Random random = new Random();
//
//    /**
//     * 通过word模板并生成word文档
//     *
//     * @param paramData    参数数据
//     * @param templatePath word模板地址加模板文件名字
//     * @param outFilePath  输出文件地址（不带文件名字）
//     * @return 生成的word文件
//     */
//    public static File generate(Map<String, Object> paramData, String templatePath, String outFilePath) {
//        String outFileName = "word_" + System.currentTimeMillis() + "_" + random.nextInt(100) + ".doc";
//        return generateWord(paramData, templatePath, outFilePath, outFileName);
//    }
//
//
//    /**
//     * 通过word模板并生成word文档
//     *
//     * @param paramData    参数数据
//     * @param templatePath word模板地址加模板文件名字
//     * @param outFilePath  输出文件地址（不带文件名字）
//     * @param outFileName  输出文件名字
//     * @return 生成的word文件
//     */
//    public static File generateWord(Map<String, Object> paramData, String templatePath, String outFilePath, String outFileName) {
//        //判断输出文件路径和文件名是否含有指定后缀
//        outFilePath = addIfNoSuffix(outFilePath, "/", "\\");
//        outFileName = addIfNoSuffix(outFileName, ".doc", ".docx");
//        //解析word模板
//        XWPFTemplate template = XWPFTemplate.compile(templatePath).render(paramData);
//        //输出文件
//        FileOutputStream out = null;
//        File outFile = new File(outFilePath + outFileName);
//        try {
//            out = new FileOutputStream(outFile);
//            template.write(out);
//            out.flush();
//        } catch (IOException e) {
//            log.error("生成word写入文件失败", e);
//        } finally {
//            if (template != null) {
//                try {
//                    template.close();
//                } catch (IOException e) {
//                    log.error("解析word模板异常", e);
//                }
//            }
//            if (out != null) {
//                try {
//                    out.close();
//                } catch (IOException e) {
//                    log.error("输出文件异常", e);
//                }
//            }
//        }
//        return outFile;
//    }
//
//
//    /**
//     * 判断一个字符串是否含有指定的后缀信息，如果没有则添加（可以指定多个后缀匹配，默认添加第一个）
//     *
//     * @param str      需要检查的字符串
//     * @param suffixes 需要匹配的后缀
//     * @return 新字符串
//     * @date 2020-01-05
//     */
//    public static String addIfNoSuffix(String str, String... suffixes) {
//        if (str == null || str.length() == 0) {
//            return str;
//        }
//        boolean isTrue = true;
//        for (String suffix : suffixes) {
//            int len = suffix.length();
//            String subSuffix = str.substring(str.length() - len);
//            if (suffix.equals(subSuffix)) {
//                isTrue = false;
//                break;
//            }
//        }
//        if (isTrue) {
//            str += suffixes[0];
//        }
//        return str;
//    }
//
//}
