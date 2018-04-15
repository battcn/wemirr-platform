-- ----------------------------
-- 本SQL采用的是MYSQL5.6 如果版本不 < 5.6请去官方安装
-- 否则运行SQL会出现：[Err] 1067 - Invalid default value for 'created_time'
-- 如果真的想使用,请修改
-- `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' (修改前)
-- `created_time` datetime DEFAULT NULL COMMENT '创建时间' (修改后)
-- ----------------------------


/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : battcn3.0

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 27/03/2018 20:36:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book`  (
  `book_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书籍编号',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书籍名称',
  `source` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书籍来源',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书籍类型(玄幻小说)',
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `description` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述（简介）',
  `cover` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书籍封面',
  `status` bit(1) NULL DEFAULT b'0' COMMENT '是否完结 0=未完结 1=已完结',
  `recommend` bit(1) NULL DEFAULT b'0' COMMENT '是否推荐 0=不推荐 1=推荐',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`book_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '书籍信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES ('NO1521203244709', '天蚕土豆', 'http://www.biquge5200.com/0_844/', '玄幻小说', '斗破苍穹', '这里是属于斗气的世界，没有花俏艳丽的魔法，有的，仅仅是繁衍到巅峰的斗气！想要知道异界的斗气在发展到巅峰之后是何种境地吗？请观斗破苍穹^^据调查，斗气，并非是国外产品，而是正宗的国产货，虽然斗气基本上已经泛滥在异界小说之中，不过土豆相信，斗破苍穹，能写出一些属于斗气的特色。', 'http://r.i.biquge5200.com/files/article/image/0/844/844s.jpg', b'0', b'1', '2018-03-16 20:27:29', '2018-03-27 20:20:38');

-- ----------------------------
-- Table structure for t_book_chapter
-- ----------------------------
DROP TABLE IF EXISTS `t_book_chapter`;
CREATE TABLE `t_book_chapter`  (
  `id` bigint(18) NOT NULL AUTO_INCREMENT COMMENT 'ID自增',
  `source` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '章节链接地址',
  `target` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '章节链接地址',
  `book_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书籍编号',
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `status` bit(1) NULL DEFAULT b'0' COMMENT '是否已生成模板文件 0=未生成 1=已生成',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1686 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '书籍章节' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_book_chapter
-- ----------------------------
INSERT INTO `t_book_chapter` VALUES (1, 'http://www.biquge5200.com/0_844/638404.html', NULL, 'NO1521203244709', '第一千六百二十四章结束，也是开始 大结局', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (2, 'http://www.biquge5200.com/0_844/638403.html', NULL, 'NO1521203244709', '第一千六百二十三章结束，也是开始。（大结局）', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (3, 'http://www.biquge5200.com/0_844/638402.html', NULL, 'NO1521203244709', '第一千六百二十三章 双帝之战！（下）', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (4, 'http://www.biquge5200.com/0_844/638401.html', NULL, 'NO1521203244709', '第一千六百二十二章 双帝之战！', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (5, 'http://www.biquge5200.com/0_844/638400.html', NULL, 'NO1521203244709', '第一千六百二十一章出关！', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (6, 'http://www.biquge5200.com/0_844/638399.html', NULL, 'NO1521203244709', '第一千六百二十章斗帝强者的力量！', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (7, 'http://www.biquge5200.com/0_844/638398.html', NULL, 'NO1521203244709', '第一千六百一十九章 魂帝，魂天帝！', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (8, 'http://www.biquge5200.com/0_844/638397.html', NULL, 'NO1521203244709', '第一千六百一十八章 浩劫', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (9, 'http://www.biquge5200.com/0_844/638396.html', NULL, 'NO1521203244709', '第一千六百一十七章 源气', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (10, 'http://www.biquge5200.com/0_844/636719.html', NULL, 'NO1521203244709', '第一章 陨落的天才', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (11, 'http://www.biquge5200.com/0_844/636720.html', NULL, 'NO1521203244709', '第二章 斗气大陆', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (12, 'http://www.biquge5200.com/0_844/636721.html', NULL, 'NO1521203244709', '第三章 客人【求收藏，求推荐票^_^】', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (13, 'http://www.biquge5200.com/0_844/636722.html', NULL, 'NO1521203244709', '第四章 云岚宗 【求收藏，求推荐！】', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (14, 'http://www.biquge5200.com/0_844/636723.html', NULL, 'NO1521203244709', '第五章 聚气散 【求推荐，求收藏^_^】', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (15, 'http://www.biquge5200.com/0_844/636724.html', NULL, 'NO1521203244709', '第六章 炼药师', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (16, 'http://www.biquge5200.com/0_844/636725.html', NULL, 'NO1521203244709', '第七章 休！', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (17, 'http://www.biquge5200.com/0_844/636726.html', NULL, 'NO1521203244709', '第八章 神秘的老者', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (18, 'http://www.biquge5200.com/0_844/636727.html', NULL, 'NO1521203244709', '第九章 药老！', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (19, 'http://www.biquge5200.com/0_844/636728.html', NULL, 'NO1521203244709', '第十章 借钱', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (20, 'http://www.biquge5200.com/0_844/636729.html', NULL, 'NO1521203244709', '第十一章 坊市', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (21, 'http://www.biquge5200.com/0_844/636730.html', NULL, 'NO1521203244709', '第十二章 离他远点', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (22, 'http://www.biquge5200.com/0_844/636731.html', NULL, 'NO1521203244709', '第十三章 黑铁片', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (23, 'http://www.biquge5200.com/0_844/636732.html', NULL, 'NO1521203244709', '第十四章 吸掌', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (24, 'http://www.biquge5200.com/0_844/636733.html', NULL, 'NO1521203244709', '第十五章 修炼', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (25, 'http://www.biquge5200.com/0_844/636734.html', NULL, 'NO1521203244709', '第十六章 萧宁', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (26, 'http://www.biquge5200.com/0_844/636735.html', NULL, 'NO1521203244709', '第十七章 冲突【求收藏，推荐票^_^】', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (27, 'http://www.biquge5200.com/0_844/636736.html', NULL, 'NO1521203244709', '第十八章 玄阶高级斗技：八极崩', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (28, 'http://www.biquge5200.com/0_844/636737.html', NULL, 'NO1521203244709', '第十九章 残酷训练', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (29, 'http://www.biquge5200.com/0_844/636738.html', NULL, 'NO1521203244709', '第二十章 拍卖', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (30, 'http://www.biquge5200.com/0_844/636739.html', NULL, 'NO1521203244709', '第二十一章 二品炼药师谷尼', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (31, 'http://www.biquge5200.com/0_844/636740.html', NULL, 'NO1521203244709', '第二十二章 风卷决 【求收藏，求推荐！】', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (32, 'http://www.biquge5200.com/0_844/636741.html', NULL, 'NO1521203244709', '第二十三章 争抢', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (33, 'http://www.biquge5200.com/0_844/636742.html', NULL, 'NO1521203244709', '第二十四章 一切待续', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (34, 'http://www.biquge5200.com/0_844/636743.html', NULL, 'NO1521203244709', '第二十五章 钱由我出', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (35, 'http://www.biquge5200.com/0_844/636744.html', NULL, 'NO1521203244709', '第二十六章 苦修', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (36, 'http://www.biquge5200.com/0_844/636745.html', NULL, 'NO1521203244709', '第二十七章 冲击第七段', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (37, 'http://www.biquge5200.com/0_844/636746.html', NULL, 'NO1521203244709', '第二十八章 强化“吸掌”', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (38, 'http://www.biquge5200.com/0_844/636747.html', NULL, 'NO1521203244709', '第二十九章 重要的日子', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (39, 'http://www.biquge5200.com/0_844/636748.html', NULL, 'NO1521203244709', '第三十章 辱人者，人恒辱之', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (40, 'http://www.biquge5200.com/0_844/636749.html', NULL, 'NO1521203244709', '第三十一章 一星斗者', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (41, 'http://www.biquge5200.com/0_844/636750.html', NULL, 'NO1521203244709', '第三十二章 挑战', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (42, 'http://www.biquge5200.com/0_844/636751.html', NULL, 'NO1521203244709', '第三十三章 证实', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (43, 'http://www.biquge5200.com/0_844/636752.html', NULL, 'NO1521203244709', '第三十四章 翻身', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (44, 'http://www.biquge5200.com/0_844/636753.html', NULL, 'NO1521203244709', '第三十五章 罪恶感', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (45, 'http://www.biquge5200.com/0_844/636754.html', NULL, 'NO1521203244709', '第三十六章 滑稽的突破', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (46, 'http://www.biquge5200.com/0_844/636755.html', NULL, 'NO1521203244709', '第三十七章 萧玉', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (47, 'http://www.biquge5200.com/0_844/636756.html', NULL, 'NO1521203244709', '第三十八章 这小家伙，不简单呐', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (48, 'http://www.biquge5200.com/0_844/636757.html', NULL, 'NO1521203244709', '第三十九章 仪式复测', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (49, 'http://www.biquge5200.com/0_844/636758.html', NULL, 'NO1521203244709', '第四十章 震撼', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (50, 'http://www.biquge5200.com/0_844/636759.html', NULL, 'NO1521203244709', '第四十一章 增气散', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (51, 'http://www.biquge5200.com/0_844/636760.html', NULL, 'NO1521203244709', '第四十二章 你输了', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (52, 'http://www.biquge5200.com/0_844/636761.html', NULL, 'NO1521203244709', '第四十三章 强横的萧炎', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (53, 'http://www.biquge5200.com/0_844/636762.html', NULL, 'NO1521203244709', '第四十四章 陪你试试', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (54, 'http://www.biquge5200.com/0_844/636763.html', NULL, 'NO1521203244709', '第四十五章 落幕', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (55, 'http://www.biquge5200.com/0_844/636764.html', NULL, 'NO1521203244709', '第四十六章 暴怒的萧炎', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (56, 'http://www.biquge5200.com/0_844/636765.html', NULL, 'NO1521203244709', '第四十七章 亵渎', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (57, 'http://www.biquge5200.com/0_844/636766.html', NULL, 'NO1521203244709', '第四十八章 斗气阁', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (58, 'http://www.biquge5200.com/0_844/636767.html', NULL, 'NO1521203244709', '第四十九章 选择功法', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (59, 'http://www.biquge5200.com/0_844/636768.html', NULL, 'NO1521203244709', '第五十章 帮？', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (60, 'http://www.biquge5200.com/0_844/636769.html', NULL, 'NO1521203244709', '第五十一章 安心', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (61, 'http://www.biquge5200.com/0_844/636770.html', NULL, 'NO1521203244709', '第五十二章 突破', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (62, 'http://www.biquge5200.com/0_844/636771.html', NULL, 'NO1521203244709', '第五十三章 第九段', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (63, 'http://www.biquge5200.com/0_844/636772.html', NULL, 'NO1521203244709', '第五十四章 筹钱', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (64, 'http://www.biquge5200.com/0_844/636773.html', NULL, 'NO1521203244709', '第五十五章 不小心', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (65, 'http://www.biquge5200.com/0_844/636774.html', NULL, 'NO1521203244709', '第五十六章 迦南学院', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (66, 'http://www.biquge5200.com/0_844/636775.html', NULL, 'NO1521203244709', '第五十七章 广告', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (67, 'http://www.biquge5200.com/0_844/636776.html', NULL, 'NO1521203244709', '第五十八章 高价', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (68, 'http://www.biquge5200.com/0_844/636777.html', NULL, 'NO1521203244709', '第五十九章 拍卖结束', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (69, 'http://www.biquge5200.com/0_844/636778.html', NULL, 'NO1521203244709', '第六十章 药材到手', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (70, 'http://www.biquge5200.com/0_844/636779.html', NULL, 'NO1521203244709', '第六十一章 装', NULL, b'0', '2018-03-16 20:27:29', '2018-03-16 20:27:29');
INSERT INTO `t_book_chapter` VALUES (71, 'http://www.biquge5200.com/0_844/636780.html', NULL, 'NO1521203244709', '第六十二章 打', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (72, 'http://www.biquge5200.com/0_844/636781.html', NULL, 'NO1521203244709', '第六十三章 异火榜', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (73, 'http://www.biquge5200.com/0_844/636782.html', NULL, 'NO1521203244709', '第六十四章 炼制聚气散', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (74, 'http://www.biquge5200.com/0_844/636783.html', NULL, 'NO1521203244709', '第六十五章 晋级斗者', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (75, 'http://www.biquge5200.com/0_844/636784.html', NULL, 'NO1521203244709', '第六十六章 焚决', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (76, 'http://www.biquge5200.com/0_844/636785.html', NULL, 'NO1521203244709', '第六十七章 选择', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (77, 'http://www.biquge5200.com/0_844/636786.html', NULL, 'NO1521203244709', '第六十八章 陨落心炎', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (78, 'http://www.biquge5200.com/0_844/636787.html', NULL, 'NO1521203244709', '第六十九章 气愤的薰儿', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (79, 'http://www.biquge5200.com/0_844/636788.html', NULL, 'NO1521203244709', '第七十章 探查', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (80, 'http://www.biquge5200.com/0_844/636789.html', NULL, 'NO1521203244709', '第七十一章 萧家形势', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (81, 'http://www.biquge5200.com/0_844/636790.html', NULL, 'NO1521203244709', '第七十二章 初学炼药', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (82, 'http://www.biquge5200.com/0_844/636791.html', NULL, 'NO1521203244709', '第七十三章 第一次炼药', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (83, 'http://www.biquge5200.com/0_844/636792.html', NULL, 'NO1521203244709', '第七十四章 不请自来', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (84, 'http://www.biquge5200.com/0_844/636793.html', NULL, 'NO1521203244709', '第七十五章 大手笔', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (85, 'http://www.biquge5200.com/0_844/636794.html', NULL, 'NO1521203244709', '第七十六章 合作', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (86, 'http://www.biquge5200.com/0_844/636795.html', NULL, 'NO1521203244709', '第七十七章 断其药路', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (87, 'http://www.biquge5200.com/0_844/636796.html', NULL, 'NO1521203244709', '第七十八章 炼啊炼的就突破了', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (88, 'http://www.biquge5200.com/0_844/636797.html', NULL, 'NO1521203244709', '第七十九章 萧家的反击', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (89, 'http://www.biquge5200.com/0_844/636798.html', NULL, 'NO1521203244709', '第八十章 炼药师柳席', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (90, 'http://www.biquge5200.com/0_844/636799.html', NULL, 'NO1521203244709', '第八十一章 察觉', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (91, 'http://www.biquge5200.com/0_844/636800.html', NULL, 'NO1521203244709', '第八十二章 坦白', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (92, 'http://www.biquge5200.com/0_844/636801.html', NULL, 'NO1521203244709', '第八十三章 小坊主', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (93, 'http://www.biquge5200.com/0_844/636802.html', NULL, 'NO1521203244709', '第八十四章 废掉', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (94, 'http://www.biquge5200.com/0_844/636803.html', NULL, 'NO1521203244709', '第八十五章 接受', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (95, 'http://www.biquge5200.com/0_844/636804.html', NULL, 'NO1521203244709', '第八十六章 挑战', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (96, 'http://www.biquge5200.com/0_844/636805.html', NULL, 'NO1521203244709', '第八十七 下杀手', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (97, 'http://www.biquge5200.com/0_844/636806.html', NULL, 'NO1521203244709', '第八十八章 落幕', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (98, 'http://www.biquge5200.com/0_844/636807.html', NULL, 'NO1521203244709', '第八十九章 月黑风高', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (99, 'http://www.biquge5200.com/0_844/636808.html', NULL, 'NO1521203244709', '第九十章 料理后事', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (100, 'http://www.biquge5200.com/0_844/636809.html', NULL, 'NO1521203244709', '第九十一章 夜中相遇', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (101, 'http://www.biquge5200.com/0_844/636810.html', NULL, 'NO1521203244709', '第九十二章 抢', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (102, 'http://www.biquge5200.com/0_844/636811.html', NULL, 'NO1521203244709', '第九十三章 半路毁药', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (103, 'http://www.biquge5200.com/0_844/636812.html', NULL, 'NO1521203244709', '第九十五章 眼光挺差', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (104, 'http://www.biquge5200.com/0_844/636813.html', NULL, 'NO1521203244709', '第九十六章 加列家族的境地', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (105, 'http://www.biquge5200.com/0_844/636814.html', NULL, 'NO1521203244709', '新书开张，求声月票！^_^', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (106, 'http://www.biquge5200.com/0_844/636815.html', NULL, 'NO1521203244709', '第九十七章 承喏', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (107, 'http://www.biquge5200.com/0_844/636816.html', NULL, 'NO1521203244709', '第九十八章 雪妮', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (108, 'http://www.biquge5200.com/0_844/636817.html', NULL, 'NO1521203244709', '第九十九章 刁难', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (109, 'http://www.biquge5200.com/0_844/636818.html', NULL, 'NO1521203244709', '第一百零章 威胁', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (110, 'http://www.biquge5200.com/0_844/636819.html', NULL, 'NO1521203244709', '第一百零一章 潜力值的分级', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (111, 'http://www.biquge5200.com/0_844/636820.html', NULL, 'NO1521203244709', '第一百零二章 最恐怖的一个', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (112, 'http://www.biquge5200.com/0_844/636821.html', NULL, 'NO1521203244709', '第一百零三章 请假', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (113, 'http://www.biquge5200.com/0_844/636822.html', NULL, 'NO1521203244709', '第一百零四章 初战大斗师', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (114, 'http://www.biquge5200.com/0_844/636823.html', NULL, 'NO1521203244709', '第一百零五章 离去之前', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (115, 'http://www.biquge5200.com/0_844/636824.html', NULL, 'NO1521203244709', '第一百零六章 离开', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (116, 'http://www.biquge5200.com/0_844/636825.html', NULL, 'NO1521203244709', '第一百零七章 云岚宗', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (117, 'http://www.biquge5200.com/0_844/636826.html', NULL, 'NO1521203244709', '第一百零八章 八极崩的暗劲', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (118, 'http://www.biquge5200.com/0_844/636827.html', NULL, 'NO1521203244709', '第一百零九章 血莲精', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (119, 'http://www.biquge5200.com/0_844/636828.html', NULL, 'NO1521203244709', '【非常重要的事情宣布，希望每一个读者都进来看看！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (120, 'http://www.biquge5200.com/0_844/636829.html', NULL, 'NO1521203244709', '第一百一十章 小医仙【求月票！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (121, 'http://www.biquge5200.com/0_844/636830.html', NULL, 'NO1521203244709', '第一百一十一章 加入队伍', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (122, 'http://www.biquge5200.com/0_844/636831.html', NULL, 'NO1521203244709', '第一百一十二章 进入魔兽山脉', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (123, 'http://www.biquge5200.com/0_844/636832.html', NULL, 'NO1521203244709', '第一百一十三章 山洞', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (124, 'http://www.biquge5200.com/0_844/636833.html', NULL, 'NO1521203244709', '第一百一十四 章 探宝(求推荐票！)', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (125, 'http://www.biquge5200.com/0_844/636834.html', NULL, 'NO1521203244709', '第一百一十五章 洞口遇险', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (126, 'http://www.biquge5200.com/0_844/636835.html', NULL, 'NO1521203244709', '第一百一十六章 冰灵焰草', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (127, 'http://www.biquge5200.com/0_844/636836.html', NULL, 'NO1521203244709', '第一百一十七章 飞行斗技：鹰之翼', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (128, 'http://www.biquge5200.com/0_844/636837.html', NULL, 'NO1521203244709', '第一百一十八章 生死逃亡【万字更新求月票！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (129, 'http://www.biquge5200.com/0_844/636838.html', NULL, 'NO1521203244709', '第一百一十九章 紫云翼', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (130, 'http://www.biquge5200.com/0_844/636839.html', NULL, 'NO1521203244709', '第一百二十章 净莲妖火', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (131, 'http://www.biquge5200.com/0_844/636840.html', NULL, 'NO1521203244709', '第一百二十一章 晋级六星！【大更求月票！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (132, 'http://www.biquge5200.com/0_844/636841.html', NULL, 'NO1521203244709', '第一百二十二章 地阶斗技：焰分噬浪尺！', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (133, 'http://www.biquge5200.com/0_844/636842.html', NULL, 'NO1521203244709', '第一百二十三章 报复开始【第一更！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (134, 'http://www.biquge5200.com/0_844/636843.html', NULL, 'NO1521203244709', '第一百二十四章 杀戮', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (135, 'http://www.biquge5200.com/0_844/636844.html', NULL, 'NO1521203244709', '第一百二十五章 八星斗者赫蒙【求推荐票！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (136, 'http://www.biquge5200.com/0_844/636845.html', NULL, 'NO1521203244709', '第一百二十六章 击杀【强烈的求推荐票！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (137, 'http://www.biquge5200.com/0_844/636846.html', NULL, 'NO1521203244709', '第一百二十七章 大围剿【三更求月票，求推荐票！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (138, 'http://www.biquge5200.com/0_844/636847.html', NULL, 'NO1521203244709', '一百二十八章 追杀【求推荐票，月票！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (139, 'http://www.biquge5200.com/0_844/636848.html', NULL, 'NO1521203244709', '第一百二十九章 独战斗师【求推荐票，月票！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (140, 'http://www.biquge5200.com/0_844/636849.html', NULL, 'NO1521203244709', '第一百三十章 突破七星【求推荐票，月票！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (141, 'http://www.biquge5200.com/0_844/636850.html', NULL, 'NO1521203244709', '第一百三十一章 神秘女人与六阶魔兽紫晶翼狮王', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (142, 'http://www.biquge5200.com/0_844/636851.html', NULL, 'NO1521203244709', '第一百三十二章 斗皇级别的战斗', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (143, 'http://www.biquge5200.com/0_844/636852.html', NULL, 'NO1521203244709', '第一百三十三章 旖旎的疗伤', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (144, 'http://www.biquge5200.com/0_844/636853.html', NULL, 'NO1521203244709', '第一百三十四章 山洞同居', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (145, 'http://www.biquge5200.com/0_844/636854.html', NULL, 'NO1521203244709', '第一百三十二章 春药惹得祸【求月票！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (146, 'http://www.biquge5200.com/0_844/636855.html', NULL, 'NO1521203244709', '第一百三十三章 春光满洞', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (147, 'http://www.biquge5200.com/0_844/636856.html', NULL, 'NO1521203244709', '第一百三十四章 破解封印', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (148, 'http://www.biquge5200.com/0_844/636857.html', NULL, 'NO1521203244709', '第一百三十五章 联手行动【第一更！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (149, 'http://www.biquge5200.com/0_844/636858.html', NULL, 'NO1521203244709', '第一百三十六章 伴生紫晶源【第二更！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (150, 'http://www.biquge5200.com/0_844/636859.html', NULL, 'NO1521203244709', '第一百三十七章 紫灵晶到手【第三更！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (151, 'http://www.biquge5200.com/0_844/636860.html', NULL, 'NO1521203244709', '第一百三十八章 生死时速【拼命狂暴第四更！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (152, 'http://www.biquge5200.com/0_844/636861.html', NULL, 'NO1521203244709', '第一百三十九章 吸收紫色能量', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (153, 'http://www.biquge5200.com/0_844/636862.html', NULL, 'NO1521203244709', '第一百四十章 九星斗者【第二更！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (154, 'http://www.biquge5200.com/0_844/636863.html', NULL, 'NO1521203244709', '第一百五十章 甘慕【第三更！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (155, 'http://www.biquge5200.com/0_844/636864.html', NULL, 'NO1521203244709', '第一百五十一章 击杀九星斗者', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (156, 'http://www.biquge5200.com/0_844/636865.html', NULL, 'NO1521203244709', '第一百五十二章 再见小医仙', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (157, 'http://www.biquge5200.com/0_844/636866.html', NULL, 'NO1521203244709', '第一百五十三章 闯狼头【月初求保底月票！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (158, 'http://www.biquge5200.com/0_844/636867.html', NULL, 'NO1521203244709', '第一百五十四章 踢场【第二更！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (159, 'http://www.biquge5200.com/0_844/636868.html', NULL, 'NO1521203244709', '第一百五十五章 击杀二星斗师【第三更！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (160, 'http://www.biquge5200.com/0_844/636869.html', NULL, 'NO1521203244709', '第一百五十六章 小山谷', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (161, 'http://www.biquge5200.com/0_844/636870.html', NULL, 'NO1521203244709', '第一百五十七章 紫焰', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (162, 'http://www.biquge5200.com/0_844/636871.html', NULL, 'NO1521203244709', '第一百五十八章 炼化火种', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (163, 'http://www.biquge5200.com/0_844/636872.html', NULL, 'NO1521203244709', '第一百五十九章 奇怪的言行【三更，求声月票！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (164, 'http://www.biquge5200.com/0_844/636873.html', NULL, 'NO1521203244709', '第一百六十章 厄难毒体', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (165, 'http://www.biquge5200.com/0_844/636874.html', NULL, 'NO1521203244709', '第一百六十一章 可怕的体质', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (166, 'http://www.biquge5200.com/0_844/636875.html', NULL, 'NO1521203244709', '第一百六十二 吞噬紫火之前的准备【二合一章节！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (167, 'http://www.biquge5200.com/0_844/636876.html', NULL, 'NO1521203244709', '第一百六十三章 魔核到手【第三更！求声月票！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (168, 'http://www.biquge5200.com/0_844/636877.html', NULL, 'NO1521203244709', '第一百六十四章 炼丹！功法进化！【两章合一，求月票！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (169, 'http://www.biquge5200.com/0_844/636878.html', NULL, 'NO1521203244709', '第一百六十五章 晋阶斗师！【第三更！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (170, 'http://www.biquge5200.com/0_844/636879.html', NULL, 'NO1521203244709', '第一百六十六章 斗师与斗者的差距', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (171, 'http://www.biquge5200.com/0_844/636880.html', NULL, 'NO1521203244709', '第一百六十七章 离别', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (172, 'http://www.biquge5200.com/0_844/636881.html', NULL, 'NO1521203244709', '第一百六十八章 炼化异火的三种必备之物', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (173, 'http://www.biquge5200.com/0_844/636882.html', NULL, 'NO1521203244709', '第一百六十九章 奥托大师', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (174, 'http://www.biquge5200.com/0_844/636883.html', NULL, 'NO1521203244709', '第一百七十章 炼药师公会', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (175, 'http://www.biquge5200.com/0_844/636884.html', NULL, 'NO1521203244709', '第一百七十一章 一品炼药师的考核', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (176, 'http://www.biquge5200.com/0_844/636885.html', NULL, 'NO1521203244709', '第一百七十二章 通过考核', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (177, 'http://www.biquge5200.com/0_844/636886.html', NULL, 'NO1521203244709', '第一百七十三章 最年轻的二品炼药师', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (178, 'http://www.biquge5200.com/0_844/636887.html', NULL, 'NO1521203244709', '第一百七十四章 炼药师的极品待遇', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (179, 'http://www.biquge5200.com/0_844/636888.html', NULL, 'NO1521203244709', '第一百七十五章 古特', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (180, 'http://www.biquge5200.com/0_844/636889.html', NULL, 'NO1521203244709', '第一百七十六章 交换【求声月票~~~~！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (181, 'http://www.biquge5200.com/0_844/636890.html', NULL, 'NO1521203244709', '第一百七十七章 冰灵寒泉到手【求声推荐票！月票！】', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (182, 'http://www.biquge5200.com/0_844/636891.html', NULL, 'NO1521203244709', '第一百七十八章 炼药师大会', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (183, 'http://www.biquge5200.com/0_844/636892.html', NULL, 'NO1521203244709', '第一百七十九章 飞行途中', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (184, 'http://www.biquge5200.com/0_844/636893.html', NULL, 'NO1521203244709', '第一百八十章 五品丹药所引发的空中血案', NULL, b'0', '2018-03-16 20:27:30', '2018-03-16 20:27:30');
INSERT INTO `t_book_chapter` VALUES (185, 'http://www.biquge5200.com/0_844/636894.html', NULL, 'NO1521203244709', '第一百八十六章 抵达', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (186, 'http://www.biquge5200.com/0_844/636895.html', NULL, 'NO1521203244709', '第一百八十七章 神秘的残破图片？', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (187, 'http://www.biquge5200.com/0_844/636896.html', NULL, 'NO1521203244709', '第一百八十三章 神秘的老人【第一更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (188, 'http://www.biquge5200.com/0_844/636897.html', NULL, 'NO1521203244709', '第一百八十四章 交手【第二更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (189, 'http://www.biquge5200.com/0_844/636898.html', NULL, 'NO1521203244709', '第一百八十五章 药老出手【第三更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (190, 'http://www.biquge5200.com/0_844/636899.html', NULL, 'NO1521203244709', '第一百八十六章 曾经的十大强者，冰皇！【第一更，求月票！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (191, 'http://www.biquge5200.com/0_844/636900.html', NULL, 'NO1521203244709', '第一百八十七章 沙漠之行【第二更！求声月票！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (192, 'http://www.biquge5200.com/0_844/636901.html', NULL, 'NO1521203244709', '第一百八十八章 沙漠苦修【迟来的第三更...】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (193, 'http://www.biquge5200.com/0_844/636902.html', NULL, 'NO1521203244709', '第一百八十九章 巧遇', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (194, 'http://www.biquge5200.com/0_844/636903.html', NULL, 'NO1521203244709', '第一百九十章 初遇蛇人,斗师初显威', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (195, 'http://www.biquge5200.com/0_844/636904.html', NULL, 'NO1521203244709', '第一百九十一章 兄弟', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (196, 'http://www.biquge5200.com/0_844/636905.html', NULL, 'NO1521203244709', '第一百九十二章 青鳞【第二更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (197, 'http://www.biquge5200.com/0_844/636906.html', NULL, 'NO1521203244709', '第一百九十三章测验', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (198, 'http://www.biquge5200.com/0_844/636907.html', NULL, 'NO1521203244709', '三章已更，土豆求月票！', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (199, 'http://www.biquge5200.com/0_844/636908.html', NULL, 'NO1521203244709', '第一百九十四章 兄弟间的比试', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (200, 'http://www.biquge5200.com/0_844/636909.html', NULL, 'NO1521203244709', '第一百九十五章 青鳞的发现', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (201, 'http://www.biquge5200.com/0_844/636910.html', NULL, 'NO1521203244709', '第一百九十六章 探测地形', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (202, 'http://www.biquge5200.com/0_844/636911.html', NULL, 'NO1521203244709', '第一百九十七章 解决麻烦', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (203, 'http://www.biquge5200.com/0_844/636912.html', NULL, 'NO1521203244709', '第一百九十八章 通道', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (204, 'http://www.biquge5200.com/0_844/636913.html', NULL, 'NO1521203244709', '第一百九十九章 探测【求月票！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (205, 'http://www.biquge5200.com/0_844/636914.html', NULL, 'NO1521203244709', '第两百章 岩浆中的神秘生物', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (206, 'http://www.biquge5200.com/0_844/636915.html', NULL, 'NO1521203244709', '第两百零一章 遇袭【第一更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (207, 'http://www.biquge5200.com/0_844/636916.html', NULL, 'NO1521203244709', '第两百零二章 双头火灵蛇【第二更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (208, 'http://www.biquge5200.com/0_844/636917.html', NULL, 'NO1521203244709', '第两百零三章 药老出手【第三更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (209, 'http://www.biquge5200.com/0_844/636918.html', NULL, 'NO1521203244709', '第两百零四章 碧蛇三花瞳【第四更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (210, 'http://www.biquge5200.com/0_844/636919.html', NULL, 'NO1521203244709', '第一百九十六章 地穴之底【求声月票！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (211, 'http://www.biquge5200.com/0_844/636920.html', NULL, 'NO1521203244709', '第一百九十七章 青莲地心火', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (212, 'http://www.biquge5200.com/0_844/636921.html', NULL, 'NO1521203244709', '第一百九十八章 略有收获【求声月票！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (213, 'http://www.biquge5200.com/0_844/636922.html', NULL, 'NO1521203244709', '第一百九十九章 晋级！启程！', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (214, 'http://www.biquge5200.com/0_844/636923.html', NULL, 'NO1521203244709', '第两百章 沙漠深处【第一更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (215, 'http://www.biquge5200.com/0_844/636924.html', NULL, 'NO1521203244709', '第两百零一章 蛇女月媚【第二更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (216, 'http://www.biquge5200.com/0_844/636925.html', NULL, 'NO1521203244709', '第两百零二章 恐怖的阵容！【第三更！求月票！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (217, 'http://www.biquge5200.com/0_844/636926.html', NULL, 'NO1521203244709', '第两百零三章 强者间的战斗', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (218, 'http://www.biquge5200.com/0_844/636927.html', NULL, 'NO1521203244709', '第两百零四章 神秘黑袍人', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (219, 'http://www.biquge5200.com/0_844/636928.html', NULL, 'NO1521203244709', '第两百零五章 夜闯部落', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (220, 'http://www.biquge5200.com/0_844/636929.html', NULL, 'NO1521203244709', '第两百零六章 沙漠中心的城市', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (221, 'http://www.biquge5200.com/0_844/636930.html', NULL, 'NO1521203244709', '第两百零七章 美杜莎女王', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (222, 'http://www.biquge5200.com/0_844/636931.html', NULL, 'NO1521203244709', '第两百零八章 丹王古河的大手笔！【迟来的第三更，恳请月票！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (223, 'http://www.biquge5200.com/0_844/636932.html', NULL, 'NO1521203244709', '三章已更，最后24小时，请求诸位支援！', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (224, 'http://www.biquge5200.com/0_844/636933.html', NULL, 'NO1521203244709', '第两百零九章 谈判失败', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (225, 'http://www.biquge5200.com/0_844/636934.html', NULL, 'NO1521203244709', '第两百一十章 争分夺秒', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (226, 'http://www.biquge5200.com/0_844/636935.html', NULL, 'NO1521203244709', '第两百一十四章 再见青莲地心火！【第一更，月初求月票！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (227, 'http://www.biquge5200.com/0_844/636936.html', NULL, 'NO1521203244709', '第两百一十五章 进化开始', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (228, 'http://www.biquge5200.com/0_844/636937.html', NULL, 'NO1521203244709', '第两百一十六章 进化成功？【第三更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (229, 'http://www.biquge5200.com/0_844/636938.html', NULL, 'NO1521203244709', '三章已更，月初求月票！', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (230, 'http://www.biquge5200.com/0_844/636939.html', NULL, 'NO1521203244709', '第两百一十七章 七彩吞天蟒', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (231, 'http://www.biquge5200.com/0_844/636940.html', NULL, 'NO1521203244709', '第两百一十八章收服青莲地心火！', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (232, 'http://www.biquge5200.com/0_844/636941.html', NULL, 'NO1521203244709', '第两百一十九章 混乱的局面', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (233, 'http://www.biquge5200.com/0_844/636942.html', NULL, 'NO1521203244709', '第两百二十章 携宝而逃', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (234, 'http://www.biquge5200.com/0_844/636943.html', NULL, 'NO1521203244709', '第两百二十一章 千里逃亡', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (235, 'http://www.biquge5200.com/0_844/636944.html', NULL, 'NO1521203244709', '第两百二十二章 云芝？【四章连更，求月票！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (236, 'http://www.biquge5200.com/0_844/636945.html', NULL, 'NO1521203244709', '四章连更！求月票！！！', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (237, 'http://www.biquge5200.com/0_844/636946.html', NULL, 'NO1521203244709', '第两百一十八章 短促的相见', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (238, 'http://www.biquge5200.com/0_844/636947.html', NULL, 'NO1521203244709', '道歉一声，昨天有事耽搁了更新', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (239, 'http://www.biquge5200.com/0_844/636948.html', NULL, 'NO1521203244709', '第两百一十九章 五蛇毒刹印', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (240, 'http://www.biquge5200.com/0_844/636949.html', NULL, 'NO1521203244709', '第两百二十五章 能耐', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (241, 'http://www.biquge5200.com/0_844/636950.html', NULL, 'NO1521203244709', '第二百二十六章 开花结果时', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (242, 'http://www.biquge5200.com/0_844/636951.html', NULL, 'NO1521203244709', '第二百二十七章 异火的吞噬，启动！', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (243, 'http://www.biquge5200.com/0_844/636952.html', NULL, 'NO1521203244709', '第二百二十八章 抽离火种', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (244, 'http://www.biquge5200.com/0_844/636953.html', NULL, 'NO1521203244709', '第两百二十九章 异火锻体', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (245, 'http://www.biquge5200.com/0_844/636954.html', NULL, 'NO1521203244709', '第两百三十章 成功', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (246, 'http://www.biquge5200.com/0_844/636955.html', NULL, 'NO1521203244709', '更新已发，请诸位月票支持！', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (247, 'http://www.biquge5200.com/0_844/636956.html', NULL, 'NO1521203244709', '第二百三十一章 修补与强化', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (248, 'http://www.biquge5200.com/0_844/636957.html', NULL, 'NO1521203244709', '今天有些事，更新稍晚点。', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (249, 'http://www.biquge5200.com/0_844/636958.html', NULL, 'NO1521203244709', '第两百三十二章 萧炎的第一种本源异火：青莲地心火！', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (250, 'http://www.biquge5200.com/0_844/636959.html', NULL, 'NO1521203244709', '第两百三十三章 进化功法！', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (251, 'http://www.biquge5200.com/0_844/636960.html', NULL, 'NO1521203244709', '第两百三十四章 煎熬之痛', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (252, 'http://www.biquge5200.com/0_844/636961.html', NULL, 'NO1521203244709', '第两百三十五章 焚决进化！', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (253, 'http://www.biquge5200.com/0_844/636962.html', NULL, 'NO1521203244709', '第两百三十六章 再见冰皇', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (254, 'http://www.biquge5200.com/0_844/636963.html', NULL, 'NO1521203244709', '第两百三十七章 谈话', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (255, 'http://www.biquge5200.com/0_844/636964.html', NULL, 'NO1521203244709', '第两百三十八章 天鼎榜', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (256, 'http://www.biquge5200.com/0_844/636965.html', NULL, 'NO1521203244709', '第两百三十九章 深藏不露', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (257, 'http://www.biquge5200.com/0_844/636966.html', NULL, 'NO1521203244709', '第两百四十章 破解封印', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (258, 'http://www.biquge5200.com/0_844/636967.html', NULL, 'NO1521203244709', '第两百四十一章 残图到手，聘请保镖', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (259, 'http://www.biquge5200.com/0_844/636968.html', NULL, 'NO1521203244709', '地两百四十二章 石漠城的变故', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (260, 'http://www.biquge5200.com/0_844/636969.html', NULL, 'NO1521203244709', '斗者不败！斗者永不言败！', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (261, 'http://www.biquge5200.com/0_844/636970.html', NULL, 'NO1521203244709', '地两百四十三章 击杀大斗师！', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (262, 'http://www.biquge5200.com/0_844/636971.html', NULL, 'NO1521203244709', '第两百四十四章 直闯【五千字，求月票！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (263, 'http://www.biquge5200.com/0_844/636972.html', NULL, 'NO1521203244709', '第两百四十五章 震慑', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (264, 'http://www.biquge5200.com/0_844/636973.html', NULL, 'NO1521203244709', '第两百四十六章 墨家', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (265, 'http://www.biquge5200.com/0_844/636974.html', NULL, 'NO1521203244709', '第两百四十七章 吞并', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (266, 'http://www.biquge5200.com/0_844/636975.html', NULL, 'NO1521203244709', '第两百四十八章 盐城', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (267, 'http://www.biquge5200.com/0_844/636976.html', NULL, 'NO1521203244709', '第两百四十九章 纳兰！', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (268, 'http://www.biquge5200.com/0_844/636977.html', NULL, 'NO1521203244709', '抱歉。', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (269, 'http://www.biquge5200.com/0_844/636978.html', NULL, 'NO1521203244709', '第两百五十章 休整【第一更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (270, 'http://www.biquge5200.com/0_844/636979.html', NULL, 'NO1521203244709', '第两百五十一章 长久打手【第二更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (271, 'http://www.biquge5200.com/0_844/636980.html', NULL, 'NO1521203244709', '第两百五十二章 纳兰嫣然【第三更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (272, 'http://www.biquge5200.com/0_844/636981.html', NULL, 'NO1521203244709', '第两百五十三章 搜寻以及墨家的野心', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (273, 'http://www.biquge5200.com/0_844/636982.html', NULL, 'NO1521203244709', '更新延迟一会。', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (274, 'http://www.biquge5200.com/0_844/636983.html', NULL, 'NO1521203244709', '第两百五十四章 墨盟', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (275, 'http://www.biquge5200.com/0_844/636984.html', NULL, 'NO1521203244709', '第两百五十五章 砸场', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (276, 'http://www.biquge5200.com/0_844/636985.html', NULL, 'NO1521203244709', '第两百五十六章 狠辣手段', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (277, 'http://www.biquge5200.com/0_844/636986.html', NULL, 'NO1521203244709', '第两百五十七章 斩杀墨承', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (278, 'http://www.biquge5200.com/0_844/636987.html', NULL, 'NO1521203244709', '第两百五十八章 神秘的青衣女人', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (279, 'http://www.biquge5200.com/0_844/636988.html', NULL, 'NO1521203244709', '危险的举动，更新换月票？！', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (280, 'http://www.biquge5200.com/0_844/636989.html', NULL, 'NO1521203244709', '第两百五十九章 三名斗皇强者的战斗！【第一更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (281, 'http://www.biquge5200.com/0_844/636990.html', NULL, 'NO1521203244709', '第两百六十章 八翼黑蛇皇【第二更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (282, 'http://www.biquge5200.com/0_844/636991.html', NULL, 'NO1521203244709', '第两百六十一章 天空大战【第三更，3370的加更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (283, 'http://www.biquge5200.com/0_844/636992.html', NULL, 'NO1521203244709', '第两百六十二章 异火相融，佛怒火莲！【第三更，3670的加更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (284, 'http://www.biquge5200.com/0_844/636993.html', NULL, 'NO1521203244709', '第两百六十三章 恐怖的破坏力【第一更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (285, 'http://www.biquge5200.com/0_844/636994.html', NULL, 'NO1521203244709', '第两百六十四章 药老沉睡【第二更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (286, 'http://www.biquge5200.com/0_844/636995.html', NULL, 'NO1521203244709', '第两百六十五章 依靠自己【第三更，3970的加更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (287, 'http://www.biquge5200.com/0_844/636996.html', NULL, 'NO1521203244709', '第两百六十六章 休养与控火能力', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (288, 'http://www.biquge5200.com/0_844/636997.html', NULL, 'NO1521203244709', '第两百六十七章 美杜莎女王再现？【第二更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (289, 'http://www.biquge5200.com/0_844/636998.html', NULL, 'NO1521203244709', '第两百六十八章 较量', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (290, 'http://www.biquge5200.com/0_844/636999.html', NULL, 'NO1521203244709', '第两百六十九章 暴涨的契合度【第一更，求月票！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (291, 'http://www.biquge5200.com/0_844/637000.html', NULL, 'NO1521203244709', '第两百七十章 紫火丹【第二更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (292, 'http://www.biquge5200.com/0_844/637001.html', NULL, 'NO1521203244709', '第两百七十一章 山寨版的佛怒火莲【迟来的第三更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (293, 'http://www.biquge5200.com/0_844/637002.html', NULL, 'NO1521203244709', '三章已更，请诸位月票支持！', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (294, 'http://www.biquge5200.com/0_844/637003.html', NULL, 'NO1521203244709', '第两百七十二章 解决隐患，离开之前', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (295, 'http://www.biquge5200.com/0_844/637004.html', NULL, 'NO1521203244709', '抱歉，实在熬不住了，今天四更补偿', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (296, 'http://www.biquge5200.com/0_844/637005.html', NULL, 'NO1521203244709', '第两百七十三章 抵达帝都【第一更！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (297, 'http://www.biquge5200.com/0_844/637006.html', NULL, 'NO1521203244709', '第两百七十四 章 米特尔拍卖场，故人【二合一！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (298, 'http://www.biquge5200.com/0_844/637007.html', NULL, 'NO1521203244709', '第两百七十五章 恶毒【迟来的第四更，抱歉！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (299, 'http://www.biquge5200.com/0_844/637008.html', NULL, 'NO1521203244709', '最后二十小时的疯狂！', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (300, 'http://www.biquge5200.com/0_844/637009.html', NULL, 'NO1521203244709', '第两百七十六章 寻药【第一更，求保底月票！】', NULL, b'0', '2018-03-16 20:27:31', '2018-03-16 20:27:31');
INSERT INTO `t_book_chapter` VALUES (301, 'http://www.biquge5200.com/0_844/637010.html', NULL, 'NO1521203244709', '第两百七十七章 阻拦【第二更！】', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (302, 'http://www.biquge5200.com/0_844/637011.html', NULL, 'NO1521203244709', '第两百七十八章 贱骨头【第三更，求保底月票！】', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (303, 'http://www.biquge5200.com/0_844/637012.html', NULL, 'NO1521203244709', '第两百七十九章 七幻青灵涎', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (304, 'http://www.biquge5200.com/0_844/637013.html', NULL, 'NO1521203244709', '第两百八十章 薰儿', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (305, 'http://www.biquge5200.com/0_844/637014.html', NULL, 'NO1521203244709', '第两百八十一章 隐藏在暗中的保护', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (306, 'http://www.biquge5200.com/0_844/637015.html', NULL, 'NO1521203244709', '第两百八十二章 我试试', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (307, 'http://www.biquge5200.com/0_844/637016.html', NULL, 'NO1521203244709', '第两百八十三章 倔着骨，咬着牙，忍着辱', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (308, 'http://www.biquge5200.com/0_844/637017.html', NULL, 'NO1521203244709', '第两百八十四章 驱毒', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (309, 'http://www.biquge5200.com/0_844/637018.html', NULL, 'NO1521203244709', '第两百八十五章 意外之喜，黑指', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (310, 'http://www.biquge5200.com/0_844/637019.html', NULL, 'NO1521203244709', '第二百八十六章 淘宝', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (311, 'http://www.biquge5200.com/0_844/637020.html', NULL, 'NO1521203244709', '第二百八十七章 冠军的福利', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (312, 'http://www.biquge5200.com/0_844/637021.html', NULL, 'NO1521203244709', '第两百八十八章 参加', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (313, 'http://www.biquge5200.com/0_844/637022.html', NULL, 'NO1521203244709', '第两百八十九章 柳翎', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (314, 'http://www.biquge5200.com/0_844/637023.html', NULL, 'NO1521203244709', '第两百九十章 潜在对手', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (315, 'http://www.biquge5200.com/0_844/637024.html', NULL, 'NO1521203244709', '第两百九十一章 晋级七星，最后的测验', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (316, 'http://www.biquge5200.com/0_844/637025.html', NULL, 'NO1521203244709', '第两百九十二章 提炼', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (317, 'http://www.biquge5200.com/0_844/637026.html', NULL, 'NO1521203244709', '第两百九十三章 测验', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (318, 'http://www.biquge5200.com/0_844/637027.html', NULL, 'NO1521203244709', '第两百九十四章 结束', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (319, 'http://www.biquge5200.com/0_844/637028.html', NULL, 'NO1521203244709', '第两百九十六章 黑马', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (320, 'http://www.biquge5200.com/0_844/637029.html', NULL, 'NO1521203244709', '第两百九十七章 聚会，木战', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (321, 'http://www.biquge5200.com/0_844/637030.html', NULL, 'NO1521203244709', '第两百九十八章 短暂的对决', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (322, 'http://www.biquge5200.com/0_844/637031.html', NULL, 'NO1521203244709', '第两百九十九章 纳兰嫣然的出手', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (323, 'http://www.biquge5200.com/0_844/637032.html', NULL, 'NO1521203244709', '第三百章 收场', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (324, 'http://www.biquge5200.com/0_844/637033.html', NULL, 'NO1521203244709', '第三百零一章 突如其来的斗皇气势', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (325, 'http://www.biquge5200.com/0_844/637034.html', NULL, 'NO1521203244709', '第三百零二章 麻袍加老', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (326, 'http://www.biquge5200.com/0_844/637035.html', NULL, 'NO1521203244709', '第三百零三章 加老的实力', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (327, 'http://www.biquge5200.com/0_844/637036.html', NULL, 'NO1521203244709', '第三百零四章 法犸，夭夜，大会开始！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (328, 'http://www.biquge5200.com/0_844/637037.html', NULL, 'NO1521203244709', '第三百零五章 第一轮，开始！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (329, 'http://www.biquge5200.com/0_844/637038.html', NULL, 'NO1521203244709', '第三百零六章 惊心动魄', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (330, 'http://www.biquge5200.com/0_844/637039.html', NULL, 'NO1521203244709', '第三百零七章 测验，神秘的灰袍人！【两更合一！】', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (331, 'http://www.biquge5200.com/0_844/637040.html', NULL, 'NO1521203244709', '第三百零八章 过于简单的第二轮', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (332, 'http://www.biquge5200.com/0_844/637041.html', NULL, 'NO1521203244709', '第三百零九章 问题所在', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (333, 'http://www.biquge5200.com/0_844/637042.html', NULL, 'NO1521203244709', '第三百一十章 力挽狂澜,大会暂休', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (334, 'http://www.biquge5200.com/0_844/637043.html', NULL, 'NO1521203244709', '第三百一十一章 诡秘的黑袍人', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (335, 'http://www.biquge5200.com/0_844/637044.html', NULL, 'NO1521203244709', '第三百一十二章 真相[vip]', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (336, 'http://www.biquge5200.com/0_844/637045.html', NULL, 'NO1521203244709', '第三百一十三章 炎利', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (337, 'http://www.biquge5200.com/0_844/637046.html', NULL, 'NO1521203244709', '第三百一十四章 天降横财', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (338, 'http://www.biquge5200.com/0_844/637047.html', NULL, 'NO1521203244709', '第三百一十五章 三纹青灵丹【第四更！】', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (339, 'http://www.biquge5200.com/0_844/637048.html', NULL, 'NO1521203244709', '第三百一十六章 最后一轮：开始！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (340, 'http://www.biquge5200.com/0_844/637049.html', NULL, 'NO1521203244709', '第三百一十七章 各显神通', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (341, 'http://www.biquge5200.com/0_844/637050.html', NULL, 'NO1521203244709', '第三百一十八章 失败', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (342, 'http://www.biquge5200.com/0_844/637051.html', NULL, 'NO1521203244709', '第三百一十九章 冠军，我要了！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (343, 'http://www.biquge5200.com/0_844/637052.html', NULL, 'NO1521203244709', '第三百二十章 再度崛起', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (344, 'http://www.biquge5200.com/0_844/637053.html', NULL, 'NO1521203244709', '第三百二十一章 紫心破障丹', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (345, 'http://www.biquge5200.com/0_844/637054.html', NULL, 'NO1521203244709', '第三百二十二章 炸炉', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (346, 'http://www.biquge5200.com/0_844/637055.html', NULL, 'NO1521203244709', '第三百二十三章 最后的胜利者！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (347, 'http://www.biquge5200.com/0_844/637056.html', NULL, 'NO1521203244709', '第三百二十四章 评价', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (348, 'http://www.biquge5200.com/0_844/637057.html', NULL, 'NO1521203244709', '第三百二十五章 大会结束！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (349, 'http://www.biquge5200.com/0_844/637058.html', NULL, 'NO1521203244709', '第三百二十六章 领取奖励', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (350, 'http://www.biquge5200.com/0_844/637059.html', NULL, 'NO1521203244709', '第三百二十七章 七幻青灵涎到手【第三更！】', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (351, 'http://www.biquge5200.com/0_844/637060.html', NULL, 'NO1521203244709', '第三百二十八章 药老苏醒？', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (352, 'http://www.biquge5200.com/0_844/637061.html', NULL, 'NO1521203244709', '第三百二十九章 夜谈', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (353, 'http://www.biquge5200.com/0_844/637062.html', NULL, 'NO1521203244709', '第三百三十章 服用三纹青灵丹', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (354, 'http://www.biquge5200.com/0_844/637063.html', NULL, 'NO1521203244709', '第三百三十一章 晋阶大斗师！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (355, 'http://www.biquge5200.com/0_844/637064.html', NULL, 'NO1521203244709', '第三百三十二章 托付', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (356, 'http://www.biquge5200.com/0_844/637065.html', NULL, 'NO1521203244709', '第三百三十三章 萧家，萧炎！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (357, 'http://www.biquge5200.com/0_844/637066.html', NULL, 'NO1521203244709', '第三百三十四章 三年之约！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (358, 'http://www.biquge5200.com/0_844/637067.html', NULL, 'NO1521203244709', '第三百三十五章 纳兰嫣然。败？', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (359, 'http://www.biquge5200.com/0_844/637068.html', NULL, 'NO1521203244709', '第三百三十六章 双方的真实实力', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (360, 'http://www.biquge5200.com/0_844/637069.html', NULL, 'NO1521203244709', '第三百三十七章 白热化的战斗！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (361, 'http://www.biquge5200.com/0_844/637070.html', NULL, 'NO1521203244709', '第三百三十八章 风之极：落日耀', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (362, 'http://www.biquge5200.com/0_844/637071.html', NULL, 'NO1521203244709', '第三百三十九章 暴露', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (363, 'http://www.biquge5200.com/0_844/637072.html', NULL, 'NO1521203244709', '第三百四十章 小型佛怒火莲！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (364, 'http://www.biquge5200.com/0_844/637073.html', NULL, 'NO1521203244709', '第三百四十一章 结束！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (365, 'http://www.biquge5200.com/0_844/637074.html', NULL, 'NO1521203244709', '第三百四十二章 风波再起', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (366, 'http://www.biquge5200.com/0_844/637075.html', NULL, 'NO1521203244709', '第三百四十三章 逃不掉的麻烦', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (367, 'http://www.biquge5200.com/0_844/637076.html', NULL, 'NO1521203244709', '第三百四十四章 一触即发', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (368, 'http://www.biquge5200.com/0_844/637077.html', NULL, 'NO1521203244709', '第三百四十五章 三名斗王强者', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (369, 'http://www.biquge5200.com/0_844/637078.html', NULL, 'NO1521203244709', '第三百四十六章 大战！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (370, 'http://www.biquge5200.com/0_844/637079.html', NULL, 'NO1521203244709', '第三百四十七章 七彩吞天蟒出场！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (371, 'http://www.biquge5200.com/0_844/637080.html', NULL, 'NO1521203244709', '第三百四十八章 悲剧的云棱', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (372, 'http://www.biquge5200.com/0_844/637081.html', NULL, 'NO1521203244709', '第三百四十九章 云烟覆日阵', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (373, 'http://www.biquge5200.com/0_844/637082.html', NULL, 'NO1521203244709', '第三百五十章 神秘斗皇出场！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (374, 'http://www.biquge5200.com/0_844/637083.html', NULL, 'NO1521203244709', '第三百五十一章 斗皇，凌影', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (375, 'http://www.biquge5200.com/0_844/637084.html', NULL, 'NO1521203244709', '第三百五十二章 云岚宗的底牌！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (376, 'http://www.biquge5200.com/0_844/637085.html', NULL, 'NO1521203244709', '第三百五十三章 云岚宗上任宗主，斗宗云山！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (377, 'http://www.biquge5200.com/0_844/637086.html', NULL, 'NO1521203244709', '第三百五十四章 下山', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (378, 'http://www.biquge5200.com/0_844/637087.html', NULL, 'NO1521203244709', '第三百五十五章 分别与交易', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (379, 'http://www.biquge5200.com/0_844/637088.html', NULL, 'NO1521203244709', '第三百五十六章 回家之途', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (380, 'http://www.biquge5200.com/0_844/637089.html', NULL, 'NO1521203244709', '第三百五十七章 萧家变故', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (381, 'http://www.biquge5200.com/0_844/637090.html', NULL, 'NO1521203244709', '第三百五十八章 一个不留', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (382, 'http://www.biquge5200.com/0_844/637091.html', NULL, 'NO1521203244709', '第三百五十九章 他必须死！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (383, 'http://www.biquge5200.com/0_844/637092.html', NULL, 'NO1521203244709', '第三百六十章 安顿萧家', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (384, 'http://www.biquge5200.com/0_844/637093.html', NULL, 'NO1521203244709', '第三百六十一章 再上云岚宗！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (385, 'http://www.biquge5200.com/0_844/637094.html', NULL, 'NO1521203244709', '第三百六十二章 药岩，云芝', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (386, 'http://www.biquge5200.com/0_844/637095.html', NULL, 'NO1521203244709', '第三百六十三章 击杀云棱', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (387, 'http://www.biquge5200.com/0_844/637096.html', NULL, 'NO1521203244709', '第三百六十四章 生死之局！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (388, 'http://www.biquge5200.com/0_844/637097.html', NULL, 'NO1521203244709', '第三百六十五章 生死门', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (389, 'http://www.biquge5200.com/0_844/637098.html', NULL, 'NO1521203244709', '第三百三十六章 斗宗强者间的大战！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (390, 'http://www.biquge5200.com/0_844/637099.html', NULL, 'NO1521203244709', '第三百三十七章 大逃亡开始', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (391, 'http://www.biquge5200.com/0_844/637100.html', NULL, 'NO1521203244709', '第三百三十八章 养伤', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (392, 'http://www.biquge5200.com/0_844/637101.html', NULL, 'NO1521203244709', '第三百三十九章 节节攀升！晋级！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (393, 'http://www.biquge5200.com/0_844/637102.html', NULL, 'NO1521203244709', '第三百四十章 天火三玄变！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (394, 'http://www.biquge5200.com/0_844/637103.html', NULL, 'NO1521203244709', '第三百四十一章 秘法的妙处', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (395, 'http://www.biquge5200.com/0_844/637104.html', NULL, 'NO1521203244709', '第三百四十二章 血腥报复！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (396, 'http://www.biquge5200.com/0_844/637105.html', NULL, 'NO1521203244709', '第三百四十三章 魔兽山脉中的围杀', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (397, 'http://www.biquge5200.com/0_844/637106.html', NULL, 'NO1521203244709', '第三百四十四章 突如其来的援兵', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (398, 'http://www.biquge5200.com/0_844/637107.html', NULL, 'NO1521203244709', '第三百四十五章 逃脱', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (399, 'http://www.biquge5200.com/0_844/637108.html', NULL, 'NO1521203244709', '第三百四十六章 大岭城', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (400, 'http://www.biquge5200.com/0_844/637109.html', NULL, 'NO1521203244709', '第三百四十七章 离开前的准备', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (401, 'http://www.biquge5200.com/0_844/637110.html', NULL, 'NO1521203244709', '第三百四十八章 黑角域', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (402, 'http://www.biquge5200.com/0_844/637111.html', NULL, 'NO1521203244709', '第三百四十九章 暴露', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (403, 'http://www.biquge5200.com/0_844/637112.html', NULL, 'NO1521203244709', '第三百五十章 离开加玛帝国！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (404, 'http://www.biquge5200.com/0_844/637113.html', NULL, 'NO1521203244709', '第三百五十一章 神秘势力，魂殿？', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (405, 'http://www.biquge5200.com/0_844/637114.html', NULL, 'NO1521203244709', '第三百五十二章 迦南学院，萧家有女初长成', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (406, 'http://www.biquge5200.com/0_844/637115.html', NULL, 'NO1521203244709', '第三百五十三章 黑域大平原', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (407, 'http://www.biquge5200.com/0_844/637116.html', NULL, 'NO1521203244709', '第三百五十四章 不需要慈悲的混乱地域', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (408, 'http://www.biquge5200.com/0_844/637117.html', NULL, 'NO1521203244709', '第三百五十五章 黑榜，黑风暴', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (409, 'http://www.biquge5200.com/0_844/637118.html', NULL, 'NO1521203244709', '第三百五十六章 黑印城', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (410, 'http://www.biquge5200.com/0_844/637119.html', NULL, 'NO1521203244709', '第三百五十七章 经济窘迫的萧炎', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (411, 'http://www.biquge5200.com/0_844/637120.html', NULL, 'NO1521203244709', '第三百五十八章 炼丹脱贫', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (412, 'http://www.biquge5200.com/0_844/637121.html', NULL, 'NO1521203244709', '第三百五十九章 黑印拍卖场', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (413, 'http://www.biquge5200.com/0_844/637122.html', NULL, 'NO1521203244709', '第三百六十章 拍卖会开始', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (414, 'http://www.biquge5200.com/0_844/637123.html', NULL, 'NO1521203244709', '第三百六十一章 飞行斗技：雷蝠天翼', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (415, 'http://www.biquge5200.com/0_844/637124.html', NULL, 'NO1521203244709', '第三百六十二章 雷蝠天翼的争夺以及残破地', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (416, 'http://www.biquge5200.com/0_844/637125.html', NULL, 'NO1521203244709', '第三百六十三章 横生变故', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (417, 'http://www.biquge5200.com/0_844/637126.html', NULL, 'NO1521203244709', '第三百六十四章 地阶身法斗技：三千雷动', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (418, 'http://www.biquge5200.com/0_844/637127.html', NULL, 'NO1521203244709', '第三百六十五章 重头戏！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (419, 'http://www.biquge5200.com/0_844/637128.html', NULL, 'NO1521203244709', '第三百六十六章 七品丹药：阴阳玄龙丹！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (420, 'http://www.biquge5200.com/0_844/637129.html', NULL, 'NO1521203244709', '第三百六十七章 尾随', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (421, 'http://www.biquge5200.com/0_844/637130.html', NULL, 'NO1521203244709', '第三百六十八章 埋伏截杀', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (422, 'http://www.biquge5200.com/0_844/637131.html', NULL, 'NO1521203244709', '第三百六十九章 大路激战', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (423, 'http://www.biquge5200.com/0_844/637132.html', NULL, 'NO1521203244709', '第三百七十章 鹬蚌相争，萧炎得利！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (424, 'http://www.biquge5200.com/0_844/637133.html', NULL, 'NO1521203244709', '第三百七十一章 天火三玄变第一重：青莲变！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (425, 'http://www.biquge5200.com/0_844/637134.html', NULL, 'NO1521203244709', '第三百七十二章 收获！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (426, 'http://www.biquge5200.com/0_844/637135.html', NULL, 'NO1521203244709', '第三百七十三章 吞服阴阳玄龙丹！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (427, 'http://www.biquge5200.com/0_844/637136.html', NULL, 'NO1521203244709', '第三百七十四章 和平镇', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (428, 'http://www.biquge5200.com/0_844/637137.html', NULL, 'NO1521203244709', '第三百七十五章 迦南学院执法队', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (429, 'http://www.biquge5200.com/0_844/637138.html', NULL, 'NO1521203244709', '第三百七十六章 关键时刻！', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (430, 'http://www.biquge5200.com/0_844/637139.html', NULL, 'NO1521203244709', '第三百三十七章 一招', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (431, 'http://www.biquge5200.com/0_844/637140.html', NULL, 'NO1521203244709', '第三百三十八章 杀鸡儆猴', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (432, 'http://www.biquge5200.com/0_844/637141.html', NULL, 'NO1521203244709', '第三百三十九章 黑夜中的对碰', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (433, 'http://www.biquge5200.com/0_844/637142.html', NULL, 'NO1521203244709', '第三百四十章 初次交锋', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (434, 'http://www.biquge5200.com/0_844/637143.html', NULL, 'NO1521203244709', '第三百三十八章 扑朔迷离', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (435, 'http://www.biquge5200.com/0_844/637144.html', NULL, 'NO1521203244709', '第三百三十九章 劲敌', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (436, 'http://www.biquge5200.com/0_844/637145.html', NULL, 'NO1521203244709', '第三百八十三章 对战陆牧', NULL, b'0', '2018-03-16 20:27:32', '2018-03-16 20:27:32');
INSERT INTO `t_book_chapter` VALUES (437, 'http://www.biquge5200.com/0_844/637146.html', NULL, 'NO1521203244709', '第三百八十四章 玩火', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (438, 'http://www.biquge5200.com/0_844/637147.html', NULL, 'NO1521203244709', '第三百八十六章 丹火之技', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (439, 'http://www.biquge5200.com/0_844/637148.html', NULL, 'NO1521203244709', '第三百八十七章 执法队：吴昊', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (440, 'http://www.biquge5200.com/0_844/637149.html', NULL, 'NO1521203244709', '第三百八十八章 挑战', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (441, 'http://www.biquge5200.com/0_844/637150.html', NULL, 'NO1521203244709', '第三百八十九章 家传玉片', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (442, 'http://www.biquge5200.com/0_844/637151.html', NULL, 'NO1521203244709', '第三百九十章 最后的选拔赛', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (443, 'http://www.biquge5200.com/0_844/637152.html', NULL, 'NO1521203244709', '第三百九十一章 大混战', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (444, 'http://www.biquge5200.com/0_844/637153.html', NULL, 'NO1521203244709', '第三百九十二章 撼雷地弧爆', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (445, 'http://www.biquge5200.com/0_844/637154.html', NULL, 'NO1521203244709', '第三百九十三章 战斗中晋级！', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (446, 'http://www.biquge5200.com/0_844/637155.html', NULL, 'NO1521203244709', '第三百九十四章 薰儿的实力', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (447, 'http://www.biquge5200.com/0_844/637156.html', NULL, 'NO1521203244709', '第三百九十五章 以一敌三', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (448, 'http://www.biquge5200.com/0_844/637157.html', NULL, 'NO1521203244709', '第三百九十七章 毫不留情', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (449, 'http://www.biquge5200.com/0_844/637158.html', NULL, 'NO1521203244709', '第三百九十八章 比赛落幕', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (450, 'http://www.biquge5200.com/0_844/637159.html', NULL, 'NO1521203244709', '第三百九十九章 大赛后的安宁', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (451, 'http://www.biquge5200.com/0_844/637160.html', NULL, 'NO1521203244709', '第四百章 神秘的藏书阁', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (452, 'http://www.biquge5200.com/0_844/637161.html', NULL, 'NO1521203244709', '第四百零一章 神秘的守阁人', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (453, 'http://www.biquge5200.com/0_844/637162.html', NULL, 'NO1521203244709', '第四百零二章 争抢', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (454, 'http://www.biquge5200.com/0_844/637163.html', NULL, 'NO1521203244709', '第四百零三章 声波斗技', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (455, 'http://www.biquge5200.com/0_844/637164.html', NULL, 'NO1521203244709', '第四百零四章 狮虎碎金吟', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (456, 'http://www.biquge5200.com/0_844/637165.html', NULL, 'NO1521203244709', '第四百零五章 修炼', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (457, 'http://www.biquge5200.com/0_844/637166.html', NULL, 'NO1521203244709', '第四百零六章 虎啸震山林', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (458, 'http://www.biquge5200.com/0_844/637167.html', NULL, 'NO1521203244709', '第四百零七章 内院的位置', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (459, 'http://www.biquge5200.com/0_844/637168.html', NULL, 'NO1521203244709', '第四百零八章 火能猎捕赛', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (460, 'http://www.biquge5200.com/0_844/637169.html', NULL, 'NO1521203244709', '第四百零九章 队长', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (461, 'http://www.biquge5200.com/0_844/637170.html', NULL, 'NO1521203244709', '第四百一十章 反抢', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (462, 'http://www.biquge5200.com/0_844/637171.html', NULL, 'NO1521203244709', '第四百一十一章 火能的作用', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (463, 'http://www.biquge5200.com/0_844/637172.html', NULL, 'NO1521203244709', '第四百一十二章 猎人与猎物的位置调换', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (464, 'http://www.biquge5200.com/0_844/637173.html', NULL, 'NO1521203244709', '第四百一十三章 飞速成长的配合', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (465, 'http://www.biquge5200.com/0_844/637174.html', NULL, 'NO1521203244709', '第四百一十四章 形成整体之后的战斗力', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (466, 'http://www.biquge5200.com/0_844/637175.html', NULL, 'NO1521203244709', '第四百一十五章 大反击', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (467, 'http://www.biquge5200.com/0_844/637176.html', NULL, 'NO1521203244709', '第四百一十六章 强者对战', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (468, 'http://www.biquge5200.com/0_844/637177.html', NULL, 'NO1521203244709', '第四百一十七章 大战起', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (469, 'http://www.biquge5200.com/0_844/637178.html', NULL, 'NO1521203244709', '第四百一十八章 胜局暂现', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (470, 'http://www.biquge5200.com/0_844/637179.html', NULL, 'NO1521203244709', '第四百一十九章 鹬蚌相争，渔翁后随', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (471, 'http://www.biquge5200.com/0_844/637180.html', NULL, 'NO1521203244709', '第四百二十章 变故', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (472, 'http://www.biquge5200.com/0_844/637181.html', NULL, 'NO1521203244709', '第四百二十一章 斗黑煞队', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (473, 'http://www.biquge5200.com/0_844/637182.html', NULL, 'NO1521203244709', '第四百二十二章 争分夺秒', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (474, 'http://www.biquge5200.com/0_844/637183.html', NULL, 'NO1521203244709', '第四百二十三章 扭转局面', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (475, 'http://www.biquge5200.com/0_844/637184.html', NULL, 'NO1521203244709', '第四百二十四章 悲愤的沙铁', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (476, 'http://www.biquge5200.com/0_844/637185.html', NULL, 'NO1521203244709', '第四百二十五章 分赃，养伤', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (477, 'http://www.biquge5200.com/0_844/637186.html', NULL, 'NO1521203244709', '第四百二十六章 白煞队', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (478, 'http://www.biquge5200.com/0_844/637187.html', NULL, 'NO1521203244709', '第四百二十七章 最后的大战！', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (479, 'http://www.biquge5200.com/0_844/637188.html', NULL, 'NO1521203244709', '第四百二十八章 赢！', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (480, 'http://www.biquge5200.com/0_844/637189.html', NULL, 'NO1521203244709', '第四百二十九章 奖励', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (481, 'http://www.biquge5200.com/0_844/637190.html', NULL, 'NO1521203244709', '第四百三十章 安顿', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (482, 'http://www.biquge5200.com/0_844/637191.html', NULL, 'NO1521203244709', '第四百三十一章 新生纳贡费', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (483, 'http://www.biquge5200.com/0_844/637192.html', NULL, 'NO1521203244709', '第四百三十二章 震慑与客气', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (484, 'http://www.biquge5200.com/0_844/637193.html', NULL, 'NO1521203244709', '第四百三十三章 磐门！', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (485, 'http://www.biquge5200.com/0_844/637194.html', NULL, 'NO1521203244709', '第四百三十四章 神秘黑塔？', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (486, 'http://www.biquge5200.com/0_844/637195.html', NULL, 'NO1521203244709', '第四百三十五章 修炼加速器', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (487, 'http://www.biquge5200.com/0_844/637196.html', NULL, 'NO1521203244709', '第四百三十六章 震动', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (488, 'http://www.biquge5200.com/0_844/637197.html', NULL, 'NO1521203244709', '第四百三十七章 初见', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (489, 'http://www.biquge5200.com/0_844/637198.html', NULL, 'NO1521203244709', '第四百三十八章 黑洞', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (490, 'http://www.biquge5200.com/0_844/637199.html', NULL, 'NO1521203244709', '第四百三十九章 神秘的无形火蟒', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (491, 'http://www.biquge5200.com/0_844/637200.html', NULL, 'NO1521203244709', '第四百四十章 麻烦事', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (492, 'http://www.biquge5200.com/0_844/637201.html', NULL, 'NO1521203244709', '第四百四十一章 赌注', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (493, 'http://www.biquge5200.com/0_844/637202.html', NULL, 'NO1521203244709', '第四百四十二章 战付敖', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (494, 'http://www.biquge5200.com/0_844/637203.html', NULL, 'NO1521203244709', '第四百四十三章 白帮的实力', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (495, 'http://www.biquge5200.com/0_844/637204.html', NULL, 'NO1521203244709', '第四百四十四章 相遇', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (496, 'http://www.biquge5200.com/0_844/637205.html', NULL, 'NO1521203244709', '第四百四十五章 暗中交锋', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (497, 'http://www.biquge5200.com/0_844/637206.html', NULL, 'NO1521203244709', '第四百四十六章 中级修炼室', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (498, 'http://www.biquge5200.com/0_844/637207.html', NULL, 'NO1521203244709', '第四百四十七章 淬炼', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (499, 'http://www.biquge5200.com/0_844/637208.html', NULL, 'NO1521203244709', '第四百四十八章 第二层', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (500, 'http://www.biquge5200.com/0_844/637209.html', NULL, 'NO1521203244709', '第四百四十九章 探查与会谈', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (501, 'http://www.biquge5200.com/0_844/637210.html', NULL, 'NO1521203244709', '第四百五十章 七星大斗师', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (502, 'http://www.biquge5200.com/0_844/637211.html', NULL, 'NO1521203244709', '第四百五十一章 磐门的变化', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (503, 'http://www.biquge5200.com/0_844/637212.html', NULL, 'NO1521203244709', '第四百五十二章 陀舍古帝玉？', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (504, 'http://www.biquge5200.com/0_844/637213.html', NULL, 'NO1521203244709', '第四百五十三章 陀舍古帝', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (505, 'http://www.biquge5200.com/0_844/637214.html', NULL, 'NO1521203244709', '第四百五十四章 青木仙藤', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (506, 'http://www.biquge5200.com/0_844/637215.html', NULL, 'NO1521203244709', '第四百五十五章 古怪的家伙', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (507, 'http://www.biquge5200.com/0_844/637216.html', NULL, 'NO1521203244709', '第四百五十六章 交易', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (508, 'http://www.biquge5200.com/0_844/637217.html', NULL, 'NO1521203244709', '第四百五十七章 青芝火灵膏，速灵风丹', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (509, 'http://www.biquge5200.com/0_844/637218.html', NULL, 'NO1521203244709', '第四百五十八章 闭关', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (510, 'http://www.biquge5200.com/0_844/637219.html', NULL, 'NO1521203244709', '第四百五十九章 找上门的麻烦', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (511, 'http://www.biquge5200.com/0_844/637220.html', NULL, 'NO1521203244709', '第四百六十章 柳菲', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (512, 'http://www.biquge5200.com/0_844/637221.html', NULL, 'NO1521203244709', '第四百六十一章 赫长老', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (513, 'http://www.biquge5200.com/0_844/637222.html', NULL, 'NO1521203244709', '第四百六十二章 修炼进展', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (514, 'http://www.biquge5200.com/0_844/637223.html', NULL, 'NO1521203244709', '第四百六十三章 再次突破！', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (515, 'http://www.biquge5200.com/0_844/637224.html', NULL, 'NO1521203244709', '第四百六十四章 霸枪柳擎', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (516, 'http://www.biquge5200.com/0_844/637225.html', NULL, 'NO1521203244709', '第四百六十五章 赚取火能', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (517, 'http://www.biquge5200.com/0_844/637226.html', NULL, 'NO1521203244709', '第四百六十六章 大批炼制', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (518, 'http://www.biquge5200.com/0_844/637227.html', NULL, 'NO1521203244709', '第四百六十七章 丰硕的收获！', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (519, 'http://www.biquge5200.com/0_844/637228.html', NULL, 'NO1521203244709', '第四百六十八章 冲突', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (520, 'http://www.biquge5200.com/0_844/637229.html', NULL, 'NO1521203244709', '第四百六十九章 药帮韩闲', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (521, 'http://www.biquge5200.com/0_844/637230.html', NULL, 'NO1521203244709', '四百七十章 比试', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (522, 'http://www.biquge5200.com/0_844/637231.html', NULL, 'NO1521203244709', '第四百七十一章  比试题目龙力丹', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (523, 'http://www.biquge5200.com/0_844/637232.html', NULL, 'NO1521203244709', '第四百七十二章 幻金火', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (524, 'http://www.biquge5200.com/0_844/637233.html', NULL, 'NO1521203244709', '第四百七十三章 半成品', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (525, 'http://www.biquge5200.com/0_844/637234.html', NULL, 'NO1521203244709', '第四百七十四章 幸不辱命', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (526, 'http://www.biquge5200.com/0_844/637235.html', NULL, 'NO1521203244709', '第四百七十五章 胜利', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (527, 'http://www.biquge5200.com/0_844/637236.html', NULL, 'NO1521203244709', '第四百七十六章 招纳', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (528, 'http://www.biquge5200.com/0_844/637237.html', NULL, 'NO1521203244709', '第四百七十七章 修炼，三千雷动', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (529, 'http://www.biquge5200.com/0_844/637238.html', NULL, 'NO1521203244709', '第四百七十八章 风雷之力', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (530, 'http://www.biquge5200.com/0_844/637239.html', NULL, 'NO1521203244709', '第四百七十九章 炼化成功', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (531, 'http://www.biquge5200.com/0_844/637240.html', NULL, 'NO1521203244709', '第四百八十章 雷闪', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (532, 'http://www.biquge5200.com/0_844/637241.html', NULL, 'NO1521203244709', '第四百八十一章 领悟，尺法', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (533, 'http://www.biquge5200.com/0_844/637242.html', NULL, 'NO1521203244709', '第四百八十二章 地心淬体乳', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (534, 'http://www.biquge5200.com/0_844/637243.html', NULL, 'NO1521203244709', '第四百八十三章 强榜高手', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (535, 'http://www.biquge5200.com/0_844/637244.html', NULL, 'NO1521203244709', '第四百八十四章 狂暴血脉', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (536, 'http://www.biquge5200.com/0_844/637245.html', NULL, 'NO1521203244709', '第四百八十五章 邀请', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (537, 'http://www.biquge5200.com/0_844/637246.html', NULL, 'NO1521203244709', '第四百八十六章 蟒猿相斗', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (538, 'http://www.biquge5200.com/0_844/637247.html', NULL, 'NO1521203244709', '第四百八十七章 寻宝', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (539, 'http://www.biquge5200.com/0_844/637248.html', NULL, 'NO1521203244709', '第四百八十八章 真假地心乳', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (540, 'http://www.biquge5200.com/0_844/637249.html', NULL, 'NO1521203244709', '第四百八十九章 美杜莎女王再现', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (541, 'http://www.biquge5200.com/0_844/637250.html', NULL, 'NO1521203244709', '第四百九十章 约定', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (542, 'http://www.biquge5200.com/0_844/637251.html', NULL, 'NO1521203244709', '第四百九十一章 调配药液', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (543, 'http://www.biquge5200.com/0_844/637252.html', NULL, 'NO1521203244709', '第四百九十二章 九星大斗师', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (544, 'http://www.biquge5200.com/0_844/637253.html', NULL, 'NO1521203244709', '第四百九十三章 晋阶斗灵', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (545, 'http://www.biquge5200.com/0_844/637254.html', NULL, 'NO1521203244709', '第四百九十四章 回院', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (546, 'http://www.biquge5200.com/0_844/637255.html', NULL, 'NO1521203244709', '第四百九十五章 出场', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (547, 'http://www.biquge5200.com/0_844/637256.html', NULL, 'NO1521203244709', '第四百九十六章 激战', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (548, 'http://www.biquge5200.com/0_844/637257.html', NULL, 'NO1521203244709', '第四百九十七章 对战六星斗灵', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (549, 'http://www.biquge5200.com/0_844/637258.html', NULL, 'NO1521203244709', '第四百九十八章 拼药', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (550, 'http://www.biquge5200.com/0_844/637259.html', NULL, 'NO1521203244709', '第四百九十九章 强力一击', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (551, 'http://www.biquge5200.com/0_844/637260.html', NULL, 'NO1521203244709', '第五百章 败敌', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (552, 'http://www.biquge5200.com/0_844/637261.html', NULL, 'NO1521203244709', '第五百零一章 一耳光', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (553, 'http://www.biquge5200.com/0_844/637262.html', NULL, 'NO1521203244709', '第五百零二章 强榜排名', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (554, 'http://www.biquge5200.com/0_844/637263.html', NULL, 'NO1521203244709', '第五百零三章 神秘的强榜第一', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (555, 'http://www.biquge5200.com/0_844/637264.html', NULL, 'NO1521203244709', '第五百零四章 冰火龙须果', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (556, 'http://www.biquge5200.com/0_844/637265.html', NULL, 'NO1521203244709', '第五百零五章 神秘白衣小女孩', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (557, 'http://www.biquge5200.com/0_844/637266.html', NULL, 'NO1521203244709', '第五百零六章 不是人', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (558, 'http://www.biquge5200.com/0_844/637267.html', NULL, 'NO1521203244709', '第五百零七章 最后一种材料', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (559, 'http://www.biquge5200.com/0_844/637268.html', NULL, 'NO1521203244709', '第五百零八章 交换', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (560, 'http://www.biquge5200.com/0_844/637269.html', NULL, 'NO1521203244709', '第五百零九章 到手', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (561, 'http://www.biquge5200.com/0_844/637270.html', NULL, 'NO1521203244709', '第五百一十章 炼制地灵丹', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (562, 'http://www.biquge5200.com/0_844/637271.html', NULL, 'NO1521203244709', '第五百一十一章 动静', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (563, 'http://www.biquge5200.com/0_844/637272.html', NULL, 'NO1521203244709', '第五百一十二章 药皇，韩枫！', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (564, 'http://www.biquge5200.com/0_844/637273.html', NULL, 'NO1521203244709', '第五百一十三章 丹成', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (565, 'http://www.biquge5200.com/0_844/637274.html', NULL, 'NO1521203244709', '第五百一十四章 探宝的天赋', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (566, 'http://www.biquge5200.com/0_844/637275.html', NULL, 'NO1521203244709', '第五百一十五章 急事？', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (567, 'http://www.biquge5200.com/0_844/637276.html', NULL, 'NO1521203244709', '第五百一十六章 回外院', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (568, 'http://www.biquge5200.com/0_844/637277.html', NULL, 'NO1521203244709', '第五百一十七章 族中变故', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (569, 'http://www.biquge5200.com/0_844/637278.html', NULL, 'NO1521203244709', '第五百一十八章 魂殿参与', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (570, 'http://www.biquge5200.com/0_844/637279.html', NULL, 'NO1521203244709', '第五百一十九章 萧厉的打算', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (571, 'http://www.biquge5200.com/0_844/637280.html', NULL, 'NO1521203244709', '第五百二十章 摆擂接战', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (572, 'http://www.biquge5200.com/0_844/637281.html', NULL, 'NO1521203244709', '第五百二十一章 姚盛', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (573, 'http://www.biquge5200.com/0_844/637282.html', NULL, 'NO1521203244709', '第五百二十二章 初步交锋', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (574, 'http://www.biquge5200.com/0_844/637283.html', NULL, 'NO1521203244709', '第五百二十三章 一号修炼室', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (575, 'http://www.biquge5200.com/0_844/637284.html', NULL, 'NO1521203244709', '第五百二十四章 塔中暴动', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (576, 'http://www.biquge5200.com/0_844/637285.html', NULL, 'NO1521203244709', '第五百二十五章 陨落心炎提前的暴动', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (577, 'http://www.biquge5200.com/0_844/637286.html', NULL, 'NO1521203244709', '第五百二十六章 大长老苏千', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (578, 'http://www.biquge5200.com/0_844/637287.html', NULL, 'NO1521203244709', '第五百二十七章 冤家路窄', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (579, 'http://www.biquge5200.com/0_844/637288.html', NULL, 'NO1521203244709', '第五百二十八章 针锋相对', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (580, 'http://www.biquge5200.com/0_844/637289.html', NULL, 'NO1521203244709', '第五百二十九章 定对手', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (581, 'http://www.biquge5200.com/0_844/637290.html', NULL, 'NO1521203244709', '第五百三十章 大赛开始', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (582, 'http://www.biquge5200.com/0_844/637291.html', NULL, 'NO1521203244709', '第五百三十一章 贝崌', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (583, 'http://www.biquge5200.com/0_844/637292.html', NULL, 'NO1521203244709', '第五百三十二章 尺法', NULL, b'0', '2018-03-16 20:27:33', '2018-03-16 20:27:33');
INSERT INTO `t_book_chapter` VALUES (584, 'http://www.biquge5200.com/0_844/637293.html', NULL, 'NO1521203244709', '第五百三十三章 血地八裂', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (585, 'http://www.biquge5200.com/0_844/637294.html', NULL, 'NO1521203244709', '第五百三十四章 青火盔甲', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (586, 'http://www.biquge5200.com/0_844/637295.html', NULL, 'NO1521203244709', '第五百三十五章 柳擎的出场', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (587, 'http://www.biquge5200.com/0_844/637296.html', NULL, 'NO1521203244709', '第五百三十六章 大裂劈棺爪', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (588, 'http://www.biquge5200.com/0_844/637297.html', NULL, 'NO1521203244709', '第五百三十七章 第二轮', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (589, 'http://www.biquge5200.com/0_844/637298.html', NULL, 'NO1521203244709', '第五百三十八章 对战姚盛', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (590, 'http://www.biquge5200.com/0_844/637299.html', NULL, 'NO1521203244709', '第五百三十九章 破解“黑水界”', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (591, 'http://www.biquge5200.com/0_844/637300.html', NULL, 'NO1521203244709', '第五百四十章 胜！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (592, 'http://www.biquge5200.com/0_844/637301.html', NULL, 'NO1521203244709', '第五百四十一章 耀眼', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (593, 'http://www.biquge5200.com/0_844/637302.html', NULL, 'NO1521203244709', '第五百四十二章 一缠一罡', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (594, 'http://www.biquge5200.com/0_844/637303.html', NULL, 'NO1521203244709', '第五百四十三章 卷轴', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (595, 'http://www.biquge5200.com/0_844/637304.html', NULL, 'NO1521203244709', '第五百四十四章 争夺前十', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (596, 'http://www.biquge5200.com/0_844/637305.html', NULL, 'NO1521203244709', '第五百四十五章 新人黑马与老牌强者的交锋！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (597, 'http://www.biquge5200.com/0_844/637306.html', NULL, 'NO1521203244709', '第五百四十六章 沸腾', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (598, 'http://www.biquge5200.com/0_844/637307.html', NULL, 'NO1521203244709', '第五百四十七章 八极崩与大裂劈棺爪的碰撞', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (599, 'http://www.biquge5200.com/0_844/637308.html', NULL, 'NO1521203244709', '第五百四十八章 爆！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (600, 'http://www.biquge5200.com/0_844/637309.html', NULL, 'NO1521203244709', '第五百四十九章 一招', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (601, 'http://www.biquge5200.com/0_844/637310.html', NULL, 'NO1521203244709', '第五百五十章 大裂岩与焰分噬浪尺的对碰！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (602, 'http://www.biquge5200.com/0_844/637311.html', NULL, 'NO1521203244709', '第五百五十一章 还有口气', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (603, 'http://www.biquge5200.com/0_844/637312.html', NULL, 'NO1521203244709', '第五百五十二章 落幕', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (604, 'http://www.biquge5200.com/0_844/637313.html', NULL, 'NO1521203244709', '第五百五十三章 养伤', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (605, 'http://www.biquge5200.com/0_844/637314.html', NULL, 'NO1521203244709', '第五百五十四章 实力晋升', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (606, 'http://www.biquge5200.com/0_844/637315.html', NULL, 'NO1521203244709', '第五百五十五章 黑湮军副统领，翎泉', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (607, 'http://www.biquge5200.com/0_844/637316.html', NULL, 'NO1521203244709', '第五百五十六章 分离', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (608, 'http://www.biquge5200.com/0_844/637317.html', NULL, 'NO1521203244709', '第五百五十七章 进入天焚炼气塔底层', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (609, 'http://www.biquge5200.com/0_844/637318.html', NULL, 'NO1521203244709', '第五百五十八章 本源心炎', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (610, 'http://www.biquge5200.com/0_844/637319.html', NULL, 'NO1521203244709', '第五百五十九章 锻体之痛', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (611, 'http://www.biquge5200.com/0_844/637320.html', NULL, 'NO1521203244709', '第五百六十章 陨落心炎，爆发！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (612, 'http://www.biquge5200.com/0_844/637321.html', NULL, 'NO1521203244709', '第五百六十一章 冲破封印！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (613, 'http://www.biquge5200.com/0_844/637322.html', NULL, 'NO1521203244709', '第五百六十二章 破塔！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (614, 'http://www.biquge5200.com/0_844/637323.html', NULL, 'NO1521203244709', '第五百六十三章 千层封阵！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (615, 'http://www.biquge5200.com/0_844/637324.html', NULL, 'NO1521203244709', '第五百六十四章 呼朋唤友', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (616, 'http://www.biquge5200.com/0_844/637325.html', NULL, 'NO1521203244709', '第五百六十五章 联手封印！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (617, 'http://www.biquge5200.com/0_844/637326.html', NULL, 'NO1521203244709', '第五百五十六章 残卷焚决', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (618, 'http://www.biquge5200.com/0_844/637327.html', NULL, 'NO1521203244709', '第五百六十七章 混乱大战', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (619, 'http://www.biquge5200.com/0_844/637328.html', NULL, 'NO1521203244709', '第五百六十八章 对战范痨', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (620, 'http://www.biquge5200.com/0_844/637329.html', NULL, 'NO1521203244709', '第五百六十九章 援手', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (621, 'http://www.biquge5200.com/0_844/637330.html', NULL, 'NO1521203244709', '第五百七十章 青火漫天！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (622, 'http://www.biquge5200.com/0_844/637331.html', NULL, 'NO1521203244709', '第五百七十一章 海心焰', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (623, 'http://www.biquge5200.com/0_844/637332.html', NULL, 'NO1521203244709', '第五百七十二章 惊天大爆炸', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (624, 'http://www.biquge5200.com/0_844/637333.html', NULL, 'NO1521203244709', '第五百七十三章 同样的心思', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (625, 'http://www.biquge5200.com/0_844/637334.html', NULL, 'NO1521203244709', '第五百七十四章 赶尽杀绝', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (626, 'http://www.biquge5200.com/0_844/637335.html', NULL, 'NO1521203244709', '第五百七十五章 封印无效', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (627, 'http://www.biquge5200.com/0_844/637336.html', NULL, 'NO1521203244709', '第五百七十六章 陨落心炎：修炼作弊器！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (628, 'http://www.biquge5200.com/0_844/637337.html', NULL, 'NO1521203244709', '第五百七十七章 斗火！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (629, 'http://www.biquge5200.com/0_844/637338.html', NULL, 'NO1521203244709', '第五百七十八章 现形', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (630, 'http://www.biquge5200.com/0_844/637339.html', NULL, 'NO1521203244709', '第五百七十九章 本体', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (631, 'http://www.biquge5200.com/0_844/637340.html', NULL, 'NO1521203244709', '第五百八十章 交锋', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (632, 'http://www.biquge5200.com/0_844/637341.html', NULL, 'NO1521203244709', '第五百八十一章 恐惧', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (633, 'http://www.biquge5200.com/0_844/637342.html', NULL, 'NO1521203244709', '第五百八十二章 大型佛怒火莲', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (634, 'http://www.biquge5200.com/0_844/637343.html', NULL, 'NO1521203244709', '第五百八十三章 吞噬，封印！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (635, 'http://www.biquge5200.com/0_844/637344.html', NULL, 'NO1521203244709', '第五百八十四章 绝境', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (636, 'http://www.biquge5200.com/0_844/637345.html', NULL, 'NO1521203244709', '第五百八十五章 蛇现', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (637, 'http://www.biquge5200.com/0_844/637346.html', NULL, 'NO1521203244709', '第五百八十六章 生死之段', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (638, 'http://www.biquge5200.com/0_844/637347.html', NULL, 'NO1521203244709', '第五百八十七章 缓慢的蜕变', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (639, 'http://www.biquge5200.com/0_844/637348.html', NULL, 'NO1521203244709', '第五百八十八章 晋阶斗王！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (640, 'http://www.biquge5200.com/0_844/637349.html', NULL, 'NO1521203244709', '第五百八十九章 局面反转，抓捕陨落心炎！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (641, 'http://www.biquge5200.com/0_844/637350.html', NULL, 'NO1521203244709', '第五百九十章 侵蚀，炼化，融合！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (642, 'http://www.biquge5200.com/0_844/637351.html', NULL, 'NO1521203244709', '第五百九十一章 融合成功！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (643, 'http://www.biquge5200.com/0_844/637352.html', NULL, 'NO1521203244709', '第五百九十二章 异火相融后遗症', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (644, 'http://www.biquge5200.com/0_844/637353.html', NULL, 'NO1521203244709', '第五百九十三章 破封！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (645, 'http://www.biquge5200.com/0_844/637354.html', NULL, 'NO1521203244709', '第五百九十四章 破塔而出！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (646, 'http://www.biquge5200.com/0_844/637355.html', NULL, 'NO1521203244709', '第五百九十五章 试手', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (647, 'http://www.biquge5200.com/0_844/637356.html', NULL, 'NO1521203244709', '第五百九十六章 灾星', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (648, 'http://www.biquge5200.com/0_844/637357.html', NULL, 'NO1521203244709', '第五百九十七章 解决麻烦', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (649, 'http://www.biquge5200.com/0_844/637358.html', NULL, 'NO1521203244709', '第五百九十八章 召集人手', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (650, 'http://www.biquge5200.com/0_844/637359.html', NULL, 'NO1521203244709', '第五百九十九章 生死之刻', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (651, 'http://www.biquge5200.com/0_844/637360.html', NULL, 'NO1521203244709', '第六百章 喝退', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (652, 'http://www.biquge5200.com/0_844/637361.html', NULL, 'NO1521203244709', '第六百零一章 援兵', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (653, 'http://www.biquge5200.com/0_844/637362.html', NULL, 'NO1521203244709', '第六百零二章 击杀范痨', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (654, 'http://www.biquge5200.com/0_844/637363.html', NULL, 'NO1521203244709', '第六百零三章 大开杀戒', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (655, 'http://www.biquge5200.com/0_844/637364.html', NULL, 'NO1521203244709', '第六百零四章 噬生丹', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (656, 'http://www.biquge5200.com/0_844/637365.html', NULL, 'NO1521203244709', '第六百零五章 药方', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (657, 'http://www.biquge5200.com/0_844/637366.html', NULL, 'NO1521203244709', '第六百零六章 大战来临', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (658, 'http://www.biquge5200.com/0_844/637367.html', NULL, 'NO1521203244709', '第六百零七章 强强对碰', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (659, 'http://www.biquge5200.com/0_844/637368.html', NULL, 'NO1521203244709', '第六百零八章 交锋', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (660, 'http://www.biquge5200.com/0_844/637369.html', NULL, 'NO1521203244709', '第六百零九章 火莲瓶', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (661, 'http://www.biquge5200.com/0_844/637370.html', NULL, 'NO1521203244709', '第六百一十章 同门之战', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (662, 'http://www.biquge5200.com/0_844/637371.html', NULL, 'NO1521203244709', '第六百一十一章 半只脚踏入斗宗的韩枫', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (663, 'http://www.biquge5200.com/0_844/637372.html', NULL, 'NO1521203244709', '第六百一十二章 翡翠火莲', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (664, 'http://www.biquge5200.com/0_844/637373.html', NULL, 'NO1521203244709', '第六百一十三章 你的命，是我的！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (665, 'http://www.biquge5200.com/0_844/637374.html', NULL, 'NO1521203244709', '第六百一十四章 魂殿再现', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (666, 'http://www.biquge5200.com/0_844/637375.html', NULL, 'NO1521203244709', '第六百一十五章 幽海纳戒', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (667, 'http://www.biquge5200.com/0_844/637376.html', NULL, 'NO1521203244709', '第六百一十六章 闭关疗伤', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (668, 'http://www.biquge5200.com/0_844/637377.html', NULL, 'NO1521203244709', '第六百一十七章 帝印决', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (669, 'http://www.biquge5200.com/0_844/637378.html', NULL, 'NO1521203244709', '第六百一十八章 打算', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (670, 'http://www.biquge5200.com/0_844/637379.html', NULL, 'NO1521203244709', '第六百一十九章 交易', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (671, 'http://www.biquge5200.com/0_844/637380.html', NULL, 'NO1521203244709', '第六百二十章 修炼开山印！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (672, 'http://www.biquge5200.com/0_844/637381.html', NULL, 'NO1521203244709', '第六百二十一章 萧门', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (673, 'http://www.biquge5200.com/0_844/637382.html', NULL, 'NO1521203244709', '第六百二十二章 三大势力', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (674, 'http://www.biquge5200.com/0_844/637383.html', NULL, 'NO1521203244709', '第六百二十三章 震慑', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (675, 'http://www.biquge5200.com/0_844/637384.html', NULL, 'NO1521203244709', '第六百二十四章 拉取帮手', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (676, 'http://www.biquge5200.com/0_844/637385.html', NULL, 'NO1521203244709', '第六百二十五章 收罗药材', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (677, 'http://www.biquge5200.com/0_844/637386.html', NULL, 'NO1521203244709', '第六百二十六章 炼制复紫灵丹', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (678, 'http://www.biquge5200.com/0_844/637387.html', NULL, 'NO1521203244709', '第六百二十七章 丹药拍卖会', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (679, 'http://www.biquge5200.com/0_844/637388.html', NULL, 'NO1521203244709', '第六百二十八章 震慑', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (680, 'http://www.biquge5200.com/0_844/637389.html', NULL, 'NO1521203244709', '第六百二十九章 拍卖结束', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (681, 'http://www.biquge5200.com/0_844/637390.html', NULL, 'NO1521203244709', '第六百三十章 修复药液', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (682, 'http://www.biquge5200.com/0_844/637391.html', NULL, 'NO1521203244709', '第六百三十一章 药老苏醒！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (683, 'http://www.biquge5200.com/0_844/637392.html', NULL, 'NO1521203244709', '第六百三十二章 师徒相见', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (684, 'http://www.biquge5200.com/0_844/637393.html', NULL, 'NO1521203244709', '第六百三十三章 炼制躯体的材料', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (685, 'http://www.biquge5200.com/0_844/637394.html', NULL, 'NO1521203244709', '第六百三十四章 寻解之法', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (686, 'http://www.biquge5200.com/0_844/637395.html', NULL, 'NO1521203244709', '第六百三十五章 恢复', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (687, 'http://www.biquge5200.com/0_844/637396.html', NULL, 'NO1521203244709', '第六百三十六章 姚氏三兄弟', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (688, 'http://www.biquge5200.com/0_844/637397.html', NULL, 'NO1521203244709', '第六百三十七章 照看', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (689, 'http://www.biquge5200.com/0_844/637398.html', NULL, 'NO1521203244709', '第六百三十八章 安置磐门', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (690, 'http://www.biquge5200.com/0_844/637399.html', NULL, 'NO1521203244709', '第六百三十九章 十招', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (691, 'http://www.biquge5200.com/0_844/637400.html', NULL, 'NO1521203244709', '第六百四十章 安宁', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (692, 'http://www.biquge5200.com/0_844/637401.html', NULL, 'NO1521203244709', '第六百四十一章 万事皆备', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (693, 'http://www.biquge5200.com/0_844/637402.html', NULL, 'NO1521203244709', '第六百四十二章 离开内院', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (694, 'http://www.biquge5200.com/0_844/637403.html', NULL, 'NO1521203244709', '第六百四十三章 启程：回归加玛！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (695, 'http://www.biquge5200.com/0_844/637404.html', NULL, 'NO1521203244709', '第六百四十四章 云岚宗的动静', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (696, 'http://www.biquge5200.com/0_844/637405.html', NULL, 'NO1521203244709', '第六百四十五章 万里之遥', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (697, 'http://www.biquge5200.com/0_844/637406.html', NULL, 'NO1521203244709', '第六百四十六章 镇鬼关！故人！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (698, 'http://www.biquge5200.com/0_844/637407.html', NULL, 'NO1521203244709', '第六百四十七章 木铁', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (699, 'http://www.biquge5200.com/0_844/637408.html', NULL, 'NO1521203244709', '第六百四十八章 斩杀', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (700, 'http://www.biquge5200.com/0_844/637409.html', NULL, 'NO1521203244709', '第六百四十九章 加玛情势', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (701, 'http://www.biquge5200.com/0_844/637410.html', NULL, 'NO1521203244709', '第六百五十章 宗内议事', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (702, 'http://www.biquge5200.com/0_844/637411.html', NULL, 'NO1521203244709', '第六百五十一章 米特尔之难', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (703, 'http://www.biquge5200.com/0_844/637412.html', NULL, 'NO1521203244709', '第六百五十二章 血洗', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (704, 'http://www.biquge5200.com/0_844/637413.html', NULL, 'NO1521203244709', '第六百五十三章 血战', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (705, 'http://www.biquge5200.com/0_844/637414.html', NULL, 'NO1521203244709', '第六百五十四章 赶至帝都！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (706, 'http://www.biquge5200.com/0_844/637415.html', NULL, 'NO1521203244709', '第六百五十五章 讨债！', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (707, 'http://www.biquge5200.com/0_844/637416.html', NULL, 'NO1521203244709', '第六百五十六章 其人之道还治其人之身', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (708, 'http://www.biquge5200.com/0_844/637417.html', NULL, 'NO1521203244709', '第六百五十七章 大开杀戒', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (709, 'http://www.biquge5200.com/0_844/637418.html', NULL, 'NO1521203244709', '第六百五十八章 横扫', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (710, 'http://www.biquge5200.com/0_844/637419.html', NULL, 'NO1521203244709', '第六百五十九章 云岚宗的震惊', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (711, 'http://www.biquge5200.com/0_844/637420.html', NULL, 'NO1521203244709', '第六百六十章 局势', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (712, 'http://www.biquge5200.com/0_844/637421.html', NULL, 'NO1521203244709', '第六百六十一章 势', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (713, 'http://www.biquge5200.com/0_844/637422.html', NULL, 'NO1521203244709', '第六百六十二章 商讨', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (714, 'http://www.biquge5200.com/0_844/637423.html', NULL, 'NO1521203244709', '第六百六十三章 安顿萧家', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (715, 'http://www.biquge5200.com/0_844/637424.html', NULL, 'NO1521203244709', '第六百六十四章 女人间的针锋相对', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (716, 'http://www.biquge5200.com/0_844/637425.html', NULL, 'NO1521203244709', '第六百六十五章 雪魅', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (717, 'http://www.biquge5200.com/0_844/637426.html', NULL, 'NO1521203244709', '第六百六十六章 傅岩', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (718, 'http://www.biquge5200.com/0_844/637427.html', NULL, 'NO1521203244709', '第六百六十七章 见面', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (719, 'http://www.biquge5200.com/0_844/637428.html', NULL, 'NO1521203244709', '第六百六十八章 现身', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (720, 'http://www.biquge5200.com/0_844/637429.html', NULL, 'NO1521203244709', '第六百六十九章 婚礼', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (721, 'http://www.biquge5200.com/0_844/637430.html', NULL, 'NO1521203244709', '第六百七十章  混元塑骨丹', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (722, 'http://www.biquge5200.com/0_844/637431.html', NULL, 'NO1521203244709', '第六百七十一章 萧家新府邸', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (723, 'http://www.biquge5200.com/0_844/637432.html', NULL, 'NO1521203244709', '第六百七十二章 夜谈', NULL, b'0', '2018-03-16 20:27:34', '2018-03-16 20:27:34');
INSERT INTO `t_book_chapter` VALUES (724, 'http://www.biquge5200.com/0_844/637433.html', NULL, 'NO1521203244709', '第六百七十三章 幽海蛟兽', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (725, 'http://www.biquge5200.com/0_844/637434.html', NULL, 'NO1521203244709', '第六百七十四章 治疗', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (726, 'http://www.biquge5200.com/0_844/637435.html', NULL, 'NO1521203244709', '第六百七十五章 大战来临！', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (727, 'http://www.biquge5200.com/0_844/637436.html', NULL, 'NO1521203244709', '第六百七十六章 大婚之日', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (728, 'http://www.biquge5200.com/0_844/637437.html', NULL, 'NO1521203244709', '第六百七十七章 十招之战', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (729, 'http://www.biquge5200.com/0_844/637438.html', NULL, 'NO1521203244709', '第六百七十八章 战古河', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (730, 'http://www.biquge5200.com/0_844/637439.html', NULL, 'NO1521203244709', '第六百七十九章 败！', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (731, 'http://www.biquge5200.com/0_844/637440.html', NULL, 'NO1521203244709', '第六百八十章 杀无赦！', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (732, 'http://www.biquge5200.com/0_844/637441.html', NULL, 'NO1521203244709', '第六百八十一章 决战，云岚宗！', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (733, 'http://www.biquge5200.com/0_844/637442.html', NULL, 'NO1521203244709', '第六百八十二章  决战云山！', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (734, 'http://www.biquge5200.com/0_844/637443.html', NULL, 'NO1521203244709', '第六百八十三章 大悲撕风手！', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (735, 'http://www.biquge5200.com/0_844/637444.html', NULL, 'NO1521203244709', '第六百八十四章  鹜护法', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (736, 'http://www.biquge5200.com/0_844/637445.html', NULL, 'NO1521203244709', '第六百八十五章 风刹湮罡', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (737, 'http://www.biquge5200.com/0_844/637446.html', NULL, 'NO1521203244709', '第六百八十六章 底牌，三色火莲！', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (738, 'http://www.biquge5200.com/0_844/637447.html', NULL, 'NO1521203244709', '第六百八十七章 火莲爆发', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (739, 'http://www.biquge5200.com/0_844/637448.html', NULL, 'NO1521203244709', '第六百八十八章 杀？', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (740, 'http://www.biquge5200.com/0_844/637449.html', NULL, 'NO1521203244709', '第六百八十九章  击杀云山！！', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (741, 'http://www.biquge5200.com/0_844/637450.html', NULL, 'NO1521203244709', '第六百九十章 变故', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (742, 'http://www.biquge5200.com/0_844/637451.html', NULL, 'NO1521203244709', '第六百九十一章 纳兰嫣然再现', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (743, 'http://www.biquge5200.com/0_844/637452.html', NULL, 'NO1521203244709', '第六百九十二章 药老战鹜护法', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (744, 'http://www.biquge5200.com/0_844/637453.html', NULL, 'NO1521203244709', '第六百九十三章 斗宗大战', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (745, 'http://www.biquge5200.com/0_844/637454.html', NULL, 'NO1521203244709', '第六百九十四章 被捕', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (746, 'http://www.biquge5200.com/0_844/637455.html', NULL, 'NO1521203244709', '第六百九十五章 痛苦', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (747, 'http://www.biquge5200.com/0_844/637456.html', NULL, 'NO1521203244709', '第六百九十六章 处理云岚宗', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (748, 'http://www.biquge5200.com/0_844/637457.html', NULL, 'NO1521203244709', '第六百九十七章 云岚宗结局', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (749, 'http://www.biquge5200.com/0_844/637458.html', NULL, 'NO1521203244709', '第六百九十八章 落幕', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (750, 'http://www.biquge5200.com/0_844/637459.html', NULL, 'NO1521203244709', '第六百九十九章 形势', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (751, 'http://www.biquge5200.com/0_844/637460.html', NULL, 'NO1521203244709', '第七百章 宗门大会', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (752, 'http://www.biquge5200.com/0_844/637461.html', NULL, 'NO1521203244709', '第七百零一章 毒宗', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (753, 'http://www.biquge5200.com/0_844/637462.html', NULL, 'NO1521203244709', '第七百零二章 药老所留', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (754, 'http://www.biquge5200.com/0_844/637463.html', NULL, 'NO1521203244709', '第七百零三章 势力雏形', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (755, 'http://www.biquge5200.com/0_844/637464.html', NULL, 'NO1521203244709', '第七百零四章 离别', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (756, 'http://www.biquge5200.com/0_844/637465.html', NULL, 'NO1521203244709', '第七百零五章  痊愈', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (757, 'http://www.biquge5200.com/0_844/637466.html', NULL, 'NO1521203244709', '第七百零六章 联盟', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (758, 'http://www.biquge5200.com/0_844/637467.html', NULL, 'NO1521203244709', '第七百零七章  炎盟', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (759, 'http://www.biquge5200.com/0_844/637468.html', NULL, 'NO1521203244709', '第七百零八章 事成', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (760, 'http://www.biquge5200.com/0_844/637469.html', NULL, 'NO1521203244709', '第七百零九章 炼制', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (761, 'http://www.biquge5200.com/0_844/637470.html', NULL, 'NO1521203244709', '第七百一十章 闭关之念', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (762, 'http://www.biquge5200.com/0_844/637471.html', NULL, 'NO1521203244709', '第七百一十一章 青山', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (763, 'http://www.biquge5200.com/0_844/637472.html', NULL, 'NO1521203244709', '第七百一十二章 遇故', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (764, 'http://www.biquge5200.com/0_844/637473.html', NULL, 'NO1521203244709', '第七百一十三章 古怪的山谷', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (765, 'http://www.biquge5200.com/0_844/637474.html', NULL, 'NO1521203244709', '第七百一十四章 神秘黑影', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (766, 'http://www.biquge5200.com/0_844/637475.html', NULL, 'NO1521203244709', '第七百一十五章  小医仙？', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (767, 'http://www.biquge5200.com/0_844/637476.html', NULL, 'NO1521203244709', '第七百一十六章 紫研晋阶', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (768, 'http://www.biquge5200.com/0_844/637477.html', NULL, 'NO1521203244709', '第七百一十七章 丹成', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (769, 'http://www.biquge5200.com/0_844/637478.html', NULL, 'NO1521203244709', '第七百一十八章 赫家', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (770, 'http://www.biquge5200.com/0_844/637479.html', NULL, 'NO1521203244709', '第七百一十九章 援手', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (771, 'http://www.biquge5200.com/0_844/637480.html', NULL, 'NO1521203244709', '第七百二十章 赫乾', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (772, 'http://www.biquge5200.com/0_844/637481.html', NULL, 'NO1521203244709', '第七百二十一章  闭死关', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (773, 'http://www.biquge5200.com/0_844/637482.html', NULL, 'NO1521203244709', '第七百二十二章 变动', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (774, 'http://www.biquge5200.com/0_844/637483.html', NULL, 'NO1521203244709', '第七百二十三章 灵魂窥测', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (775, 'http://www.biquge5200.com/0_844/637484.html', NULL, 'NO1521203244709', '第七百二十四章 晋阶斗皇！', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (776, 'http://www.biquge5200.com/0_844/637485.html', NULL, 'NO1521203244709', '第七百二十五章 出谷', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (777, 'http://www.biquge5200.com/0_844/637486.html', NULL, 'NO1521203244709', '第七百二十六章 大乱', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (778, 'http://www.biquge5200.com/0_844/637487.html', NULL, 'NO1521203244709', '第七百二十七章 毒宗，金雁宗，慕兰谷', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (779, 'http://www.biquge5200.com/0_844/637488.html', NULL, 'NO1521203244709', '第七百二十八章 援救', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (780, 'http://www.biquge5200.com/0_844/637489.html', NULL, 'NO1521203244709', '第七百二十九章 蛇人月媚', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (781, 'http://www.biquge5200.com/0_844/637490.html', NULL, 'NO1521203244709', '第七百三十章 情势', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (782, 'http://www.biquge5200.com/0_844/637491.html', NULL, 'NO1521203244709', '第七百三十一章  大战！', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (783, 'http://www.biquge5200.com/0_844/637492.html', NULL, 'NO1521203244709', '第七百三十二章 三兽蛮荒决', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (784, 'http://www.biquge5200.com/0_844/637493.html', NULL, 'NO1521203244709', '第七百三十三章 迎战慕兰三老', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (785, 'http://www.biquge5200.com/0_844/637494.html', NULL, 'NO1521203244709', '第七百三十四章 激战！', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (786, 'http://www.biquge5200.com/0_844/637495.html', NULL, 'NO1521203244709', '第七百三十五章 三千雷', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (787, 'http://www.biquge5200.com/0_844/637496.html', NULL, 'NO1521203244709', '第七百三十六章 骗局', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (788, 'http://www.biquge5200.com/0_844/637497.html', NULL, 'NO1521203244709', '第七百三十七章 鹰啼', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (789, 'http://www.biquge5200.com/0_844/637498.html', NULL, 'NO1521203244709', '第七百三十八章 毒宗宗主！', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (790, 'http://www.biquge5200.com/0_844/637499.html', NULL, 'NO1521203244709', '第七百三十九章 身法较技', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (791, 'http://www.biquge5200.com/0_844/637500.html', NULL, 'NO1521203244709', '第七百四十四章 爆！', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (792, 'http://www.biquge5200.com/0_844/637501.html', NULL, 'NO1521203244709', '第七百四十五章 威望！', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (793, 'http://www.biquge5200.com/0_844/637502.html', NULL, 'NO1521203244709', '第七百四十六章 物是人非', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (794, 'http://www.biquge5200.com/0_844/637503.html', NULL, 'NO1521203244709', '第七百四十七章 大战休止', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (795, 'http://www.biquge5200.com/0_844/637504.html', NULL, 'NO1521203244709', '第七百四十八章 夜见', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (796, 'http://www.biquge5200.com/0_844/637505.html', NULL, 'NO1521203244709', '第七百四十九章 毒丹之法', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (797, 'http://www.biquge5200.com/0_844/637506.html', NULL, 'NO1521203244709', '第七百五十章 蛇人族强者', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (798, 'http://www.biquge5200.com/0_844/637507.html', NULL, 'NO1521203244709', '第七百五十一章 四大长老', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (799, 'http://www.biquge5200.com/0_844/637508.html', NULL, 'NO1521203244709', '第七百五十二章 秘法三等', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (800, 'http://www.biquge5200.com/0_844/637509.html', NULL, 'NO1521203244709', '第七百五十三章 挑衅', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (801, 'http://www.biquge5200.com/0_844/637510.html', NULL, 'NO1521203244709', '第七百五十四章 调养', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (802, 'http://www.biquge5200.com/0_844/637511.html', NULL, 'NO1521203244709', '第七百五十五章 行动', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (803, 'http://www.biquge5200.com/0_844/637512.html', NULL, 'NO1521203244709', '第七百五十六章 暗杀！', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (804, 'http://www.biquge5200.com/0_844/637513.html', NULL, 'NO1521203244709', '第七百五十七章 以寡敌众', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (805, 'http://www.biquge5200.com/0_844/637514.html', NULL, 'NO1521203244709', '第七百五十八章 收获颇丰', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (806, 'http://www.biquge5200.com/0_844/637515.html', NULL, 'NO1521203244709', '第七百五十九章 天雁九行翼', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (807, 'http://www.biquge5200.com/0_844/637516.html', NULL, 'NO1521203244709', '第七百六十章 云山之上，薰儿！', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (808, 'http://www.biquge5200.com/0_844/637517.html', NULL, 'NO1521203244709', '第七百六十一章 丹塔', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (809, 'http://www.biquge5200.com/0_844/637518.html', NULL, 'NO1521203244709', '第七百六十二章 招揽古河', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (810, 'http://www.biquge5200.com/0_844/637519.html', NULL, 'NO1521203244709', '第七百六十三章 大统领', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (811, 'http://www.biquge5200.com/0_844/637520.html', NULL, 'NO1521203244709', '第七百六十四章 邀请帮手', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (812, 'http://www.biquge5200.com/0_844/637521.html', NULL, 'NO1521203244709', '第七百六十五章 赶往出云！', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (813, 'http://www.biquge5200.com/0_844/637522.html', NULL, 'NO1521203244709', '第七百六十六章 万蝎门', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (814, 'http://www.biquge5200.com/0_844/637523.html', NULL, 'NO1521203244709', '第七百六十七章 蜈崖', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (815, 'http://www.biquge5200.com/0_844/637524.html', NULL, 'NO1521203244709', '第七百六十八章 凶险', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (816, 'http://www.biquge5200.com/0_844/637525.html', NULL, 'NO1521203244709', '第七百六十九章 蝎毕岩', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (817, 'http://www.biquge5200.com/0_844/637526.html', NULL, 'NO1521203244709', '第七百七十章 门派大战', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (818, 'http://www.biquge5200.com/0_844/637527.html', NULL, 'NO1521203244709', '第七百七十一章 斩杀', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (819, 'http://www.biquge5200.com/0_844/637528.html', NULL, 'NO1521203244709', '第七百七十二章 四翼天魔蝎', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (820, 'http://www.biquge5200.com/0_844/637529.html', NULL, 'NO1521203244709', '第七百七十三章 现身', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (821, 'http://www.biquge5200.com/0_844/637530.html', NULL, 'NO1521203244709', '第七百七十四章 铁护法', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (822, 'http://www.biquge5200.com/0_844/637531.html', NULL, 'NO1521203244709', '第七百七十五章 斗宗大爆发', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (823, 'http://www.biquge5200.com/0_844/637532.html', NULL, 'NO1521203244709', '第七百七十六章 偷袭', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (824, 'http://www.biquge5200.com/0_844/637533.html', NULL, 'NO1521203244709', '第七百七十七章 蝎山身亡', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (825, 'http://www.biquge5200.com/0_844/637534.html', NULL, 'NO1521203244709', '第七百七十八章 魂袋', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (826, 'http://www.biquge5200.com/0_844/637535.html', NULL, 'NO1521203244709', '第七百七十九章 翻海印！', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (827, 'http://www.biquge5200.com/0_844/637536.html', NULL, 'NO1521203244709', '第七百八十章 狼狈的铁护法', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (828, 'http://www.biquge5200.com/0_844/637537.html', NULL, 'NO1521203244709', '第七百八十一章 擒获！', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (829, 'http://www.biquge5200.com/0_844/637538.html', NULL, 'NO1521203244709', '第七百八十二章 拼死一击', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (830, 'http://www.biquge5200.com/0_844/637539.html', NULL, 'NO1521203244709', '第七百八十三章 魔毒斑', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (831, 'http://www.biquge5200.com/0_844/637540.html', NULL, 'NO1521203244709', '第七百八十四章 石池', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (832, 'http://www.biquge5200.com/0_844/637541.html', NULL, 'NO1521203244709', '第七百八十五章 封印魔毒斑', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (833, 'http://www.biquge5200.com/0_844/637542.html', NULL, 'NO1521203244709', '第七百八十六章 二星斗皇', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (834, 'http://www.biquge5200.com/0_844/637543.html', NULL, 'NO1521203244709', '第七百八十七章 榜上第三！', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (835, 'http://www.biquge5200.com/0_844/637544.html', NULL, 'NO1521203244709', '第七百八十八章 打算', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (836, 'http://www.biquge5200.com/0_844/637545.html', NULL, 'NO1521203244709', '第七百八十九章 魂殿情报', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (837, 'http://www.biquge5200.com/0_844/637546.html', NULL, 'NO1521203244709', '第七百九十章 赶往黑角域', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (838, 'http://www.biquge5200.com/0_844/637547.html', NULL, 'NO1521203244709', '第七百九十一章 路遇', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (839, 'http://www.biquge5200.com/0_844/637548.html', NULL, 'NO1521203244709', '第七百九十二章 魔炎谷', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (840, 'http://www.biquge5200.com/0_844/637549.html', NULL, 'NO1521203244709', '第七百九十三章 血剑吴昊', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (841, 'http://www.biquge5200.com/0_844/637550.html', NULL, 'NO1521203244709', '第七百九十四章 鹰爪老人', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (842, 'http://www.biquge5200.com/0_844/637551.html', NULL, 'NO1521203244709', '第七百九十五章 萧门，萧炎', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (843, 'http://www.biquge5200.com/0_844/637552.html', NULL, 'NO1521203244709', '第七百九十六章 一个不留', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (844, 'http://www.biquge5200.com/0_844/637553.html', NULL, 'NO1521203244709', '第七百九十七章 风起云涌', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (845, 'http://www.biquge5200.com/0_844/637554.html', NULL, 'NO1521203244709', '第七百九十八章 进城', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (846, 'http://www.biquge5200.com/0_844/637555.html', NULL, 'NO1521203244709', '第七百九十九章 千药坊', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (847, 'http://www.biquge5200.com/0_844/637556.html', NULL, 'NO1521203244709', '第八百章 换丹集会', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (848, 'http://www.biquge5200.com/0_844/637557.html', NULL, 'NO1521203244709', '第八百零一章 红脸老者', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (849, 'http://www.biquge5200.com/0_844/637558.html', NULL, 'NO1521203244709', '第八百零二章  竞价', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (850, 'http://www.biquge5200.com/0_844/637559.html', NULL, 'NO1521203244709', '第八百零三章 得手', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (851, 'http://www.biquge5200.com/0_844/637560.html', NULL, 'NO1521203244709', '第八百零四章 菩提心', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (852, 'http://www.biquge5200.com/0_844/637561.html', NULL, 'NO1521203244709', '第八百零五章 黖皇阁', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (853, 'http://www.biquge5200.com/0_844/637562.html', NULL, 'NO1521203244709', '第八百零六章 找上门的麻烦', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (854, 'http://www.biquge5200.com/0_844/637563.html', NULL, 'NO1521203244709', '第八百零七章 白衣人', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (855, 'http://www.biquge5200.com/0_844/637564.html', NULL, 'NO1521203244709', '第八百零八章 莫崖', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (856, 'http://www.biquge5200.com/0_844/637565.html', NULL, 'NO1521203244709', '第八百零九章 强者云集', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (857, 'http://www.biquge5200.com/0_844/637566.html', NULL, 'NO1521203244709', '第八百一十章 二女暗手', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (858, 'http://www.biquge5200.com/0_844/637567.html', NULL, 'NO1521203244709', '第八百一十一章 破宗丹', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (859, 'http://www.biquge5200.com/0_844/637568.html', NULL, 'NO1521203244709', '第八百一十二章 动静', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (860, 'http://www.biquge5200.com/0_844/637569.html', NULL, 'NO1521203244709', '第八百一十三章 莫天行', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (861, 'http://www.biquge5200.com/0_844/637570.html', NULL, 'NO1521203244709', '第八百一十四章 拍卖开始', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (862, 'http://www.biquge5200.com/0_844/637571.html', NULL, 'NO1521203244709', '第八百一十五章 好戏开锣', NULL, b'0', '2018-03-16 20:27:35', '2018-03-16 20:27:35');
INSERT INTO `t_book_chapter` VALUES (863, 'http://www.biquge5200.com/0_844/637572.html', NULL, 'NO1521203244709', '第八百一十六章 拍卖！', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (864, 'http://www.biquge5200.com/0_844/637573.html', NULL, 'NO1521203244709', '第八百一十七章 六合游身尺', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (865, 'http://www.biquge5200.com/0_844/637574.html', NULL, 'NO1521203244709', '第八百一十八章 魔兽干尸', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (866, 'http://www.biquge5200.com/0_844/637575.html', NULL, 'NO1521203244709', '第八百一十九章 拍卖干尸', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (867, 'http://www.biquge5200.com/0_844/637576.html', NULL, 'NO1521203244709', '第八百二十章 以物换物', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (868, 'http://www.biquge5200.com/0_844/637577.html', NULL, 'NO1521203244709', '第八百二十一章 菩提化体涎', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (869, 'http://www.biquge5200.com/0_844/637578.html', NULL, 'NO1521203244709', '第八百二十二章 各显本钱', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (870, 'http://www.biquge5200.com/0_844/637579.html', NULL, 'NO1521203244709', '第八百二十三章 最终胜者', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (871, 'http://www.biquge5200.com/0_844/637580.html', NULL, 'NO1521203244709', '第八百二十四章 鹰山老人', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (872, 'http://www.biquge5200.com/0_844/637581.html', NULL, 'NO1521203244709', '第八百二十五章 见面', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (873, 'http://www.biquge5200.com/0_844/637582.html', NULL, 'NO1521203244709', '第八百二十六章 相谈', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (874, 'http://www.biquge5200.com/0_844/637583.html', NULL, 'NO1521203244709', '第八百二十七章 商议定计', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (875, 'http://www.biquge5200.com/0_844/637584.html', NULL, 'NO1521203244709', '第八百二十八章 分尸', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (876, 'http://www.biquge5200.com/0_844/637585.html', NULL, 'NO1521203244709', '第八百二十九章 青红血液', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (877, 'http://www.biquge5200.com/0_844/637586.html', NULL, 'NO1521203244709', '第八百三十章 七阶魔核', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (878, 'http://www.biquge5200.com/0_844/637587.html', NULL, 'NO1521203244709', '第八百三十一章 心火种子', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (879, 'http://www.biquge5200.com/0_844/637588.html', NULL, 'NO1521203244709', '第八百三十二章 尾随', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (880, 'http://www.biquge5200.com/0_844/637589.html', NULL, 'NO1521203244709', '第八百三十三章 探测', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (881, 'http://www.biquge5200.com/0_844/637590.html', NULL, 'NO1521203244709', '第八百三十四章 灵魂分身', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (882, 'http://www.biquge5200.com/0_844/637591.html', NULL, 'NO1521203244709', '第八百三十五章 果然是你', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (883, 'http://www.biquge5200.com/0_844/637592.html', NULL, 'NO1521203244709', '第八百三十六章 同门再见', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (884, 'http://www.biquge5200.com/0_844/637593.html', NULL, 'NO1521203244709', '第八百三十七章 五大斗宗', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (885, 'http://www.biquge5200.com/0_844/637594.html', NULL, 'NO1521203244709', '第八百三十八章 局势转变', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (886, 'http://www.biquge5200.com/0_844/637595.html', NULL, 'NO1521203244709', '第八百三十九章 大战起', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (887, 'http://www.biquge5200.com/0_844/637596.html', NULL, 'NO1521203244709', '第八百四十章 化生火', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (888, 'http://www.biquge5200.com/0_844/637597.html', NULL, 'NO1521203244709', '第八百四十一章 收服', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (889, 'http://www.biquge5200.com/0_844/637598.html', NULL, 'NO1521203244709', '第八百四十二章 击杀', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (890, 'http://www.biquge5200.com/0_844/637599.html', NULL, 'NO1521203244709', '第八百四十三章 暴走的紫研', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (891, 'http://www.biquge5200.com/0_844/637600.html', NULL, 'NO1521203244709', '第八百四十四章 喝退', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (892, 'http://www.biquge5200.com/0_844/637601.html', NULL, 'NO1521203244709', '第八百四十五章 离去', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (893, 'http://www.biquge5200.com/0_844/637602.html', NULL, 'NO1521203244709', '第八百四十六章 变故', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (894, 'http://www.biquge5200.com/0_844/637603.html', NULL, 'NO1521203244709', '第八百四十七章 消除隐患', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (895, 'http://www.biquge5200.com/0_844/637604.html', NULL, 'NO1521203244709', '第八百四十八章 抵达和平镇', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (896, 'http://www.biquge5200.com/0_844/637605.html', NULL, 'NO1521203244709', '第八百四十九章 欣蓝', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (897, 'http://www.biquge5200.com/0_844/637606.html', NULL, 'NO1521203244709', '第八百五十章 见面', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (898, 'http://www.biquge5200.com/0_844/637607.html', NULL, 'NO1521203244709', '第八百五十一章 办法', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (899, 'http://www.biquge5200.com/0_844/637608.html', NULL, 'NO1521203244709', '第八百五十二章 异火消息', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (900, 'http://www.biquge5200.com/0_844/637609.html', NULL, 'NO1521203244709', '第八百五十三章 九龙雷罡火', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (901, 'http://www.biquge5200.com/0_844/637610.html', NULL, 'NO1521203244709', '第八百五十四章 三千焱炎火', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (902, 'http://www.biquge5200.com/0_844/637611.html', NULL, 'NO1521203244709', '第八百五十五章 炼制', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (903, 'http://www.biquge5200.com/0_844/637612.html', NULL, 'NO1521203244709', '第八百五十六章 意外收获', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (904, 'http://www.biquge5200.com/0_844/637613.html', NULL, 'NO1521203244709', '第八百五十七章 霸道的骨翼', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (905, 'http://www.biquge5200.com/0_844/637614.html', NULL, 'NO1521203244709', '第八百五十八章 一劳永逸', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (906, 'http://www.biquge5200.com/0_844/637615.html', NULL, 'NO1521203244709', '第八百五十九章  到来', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (907, 'http://www.biquge5200.com/0_844/637616.html', NULL, 'NO1521203244709', '第八百六十章 地魔老鬼', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (908, 'http://www.biquge5200.com/0_844/637617.html', NULL, 'NO1521203244709', '第八百六十一章 动手', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (909, 'http://www.biquge5200.com/0_844/637618.html', NULL, 'NO1521203244709', '第八百六十二章 对战地魔老鬼', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (910, 'http://www.biquge5200.com/0_844/637619.html', NULL, 'NO1521203244709', '第八百六十三章 惊心动魄', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (911, 'http://www.biquge5200.com/0_844/637620.html', NULL, 'NO1521203244709', '第八百六十四章 疯狂', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (912, 'http://www.biquge5200.com/0_844/637621.html', NULL, 'NO1521203244709', '第八百六十五章 毁灭火莲', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (913, 'http://www.biquge5200.com/0_844/637622.html', NULL, 'NO1521203244709', '第八百六十六章  大破坏！', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (914, 'http://www.biquge5200.com/0_844/637623.html', NULL, 'NO1521203244709', '第八百六十七章 千百二老', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (915, 'http://www.biquge5200.com/0_844/637624.html', NULL, 'NO1521203244709', '第八百六十八章 希冀', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (916, 'http://www.biquge5200.com/0_844/637625.html', NULL, 'NO1521203244709', '第八百六十九章 失败', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (917, 'http://www.biquge5200.com/0_844/637626.html', NULL, 'NO1521203244709', '第八百七十章 五星斗皇', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (918, 'http://www.biquge5200.com/0_844/637627.html', NULL, 'NO1521203244709', '第八百七十一章 修炼之所', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (919, 'http://www.biquge5200.com/0_844/637628.html', NULL, 'NO1521203244709', '第八百七十二章 再进塔底', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (920, 'http://www.biquge5200.com/0_844/637629.html', NULL, 'NO1521203244709', '第八百七十三章 召唤', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (921, 'http://www.biquge5200.com/0_844/637630.html', NULL, 'NO1521203244709', '第八百七十四章 尺法小成', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (922, 'http://www.biquge5200.com/0_844/637631.html', NULL, 'NO1521203244709', '第八百七十五章 神秘骨骸', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (923, 'http://www.biquge5200.com/0_844/637632.html', NULL, 'NO1521203244709', '第八百七十六章 岩浆生物', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (924, 'http://www.biquge5200.com/0_844/637633.html', NULL, 'NO1521203244709', '第八百七十七章 火焰蜥蜴族', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (925, 'http://www.biquge5200.com/0_844/637634.html', NULL, 'NO1521203244709', '第八百七十八章 天火尊者', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (926, 'http://www.biquge5200.com/0_844/637635.html', NULL, 'NO1521203244709', '第八百七十九章 五轮离火法', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (927, 'http://www.biquge5200.com/0_844/637636.html', NULL, 'NO1521203244709', '第八百八十章 神秘存在', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (928, 'http://www.biquge5200.com/0_844/637637.html', NULL, 'NO1521203244709', '第八百八十一章 陀舍古帝玉的动静', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (929, 'http://www.biquge5200.com/0_844/637638.html', NULL, 'NO1521203244709', '第八百八十二章 再次晋级', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (930, 'http://www.biquge5200.com/0_844/637639.html', NULL, 'NO1521203244709', '第八百八十三章 情报', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (931, 'http://www.biquge5200.com/0_844/637640.html', NULL, 'NO1521203244709', '第八百八十四章 商议', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (932, 'http://www.biquge5200.com/0_844/637641.html', NULL, 'NO1521203244709', '第八百八十五章 招集', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (933, 'http://www.biquge5200.com/0_844/637642.html', NULL, 'NO1521203244709', '第八百八十六章 联盟', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (934, 'http://www.biquge5200.com/0_844/637643.html', NULL, 'NO1521203244709', '第八百八十七章 速度所造成的威慑', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (935, 'http://www.biquge5200.com/0_844/637644.html', NULL, 'NO1521203244709', '第八百八十八章 轩护法', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (936, 'http://www.biquge5200.com/0_844/637645.html', NULL, 'NO1521203244709', '第八百八十九章 火灵显威', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (937, 'http://www.biquge5200.com/0_844/637646.html', NULL, 'NO1521203244709', '第八百九十章 凶魂凝聚', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (938, 'http://www.biquge5200.com/0_844/637647.html', NULL, 'NO1521203244709', '第八百九十一章 迎战', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (939, 'http://www.biquge5200.com/0_844/637648.html', NULL, 'NO1521203244709', '第八百九十二章 收取凶魂', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (940, 'http://www.biquge5200.com/0_844/637649.html', NULL, 'NO1521203244709', '第八百九十三章 击杀', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (941, 'http://www.biquge5200.com/0_844/637650.html', NULL, 'NO1521203244709', '第八百九十四章 再度交手', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (942, 'http://www.biquge5200.com/0_844/637651.html', NULL, 'NO1521203244709', '第八百九十五章 火莲，杀！', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (943, 'http://www.biquge5200.com/0_844/637652.html', NULL, 'NO1521203244709', '第八百九十六章 最后胜者', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (944, 'http://www.biquge5200.com/0_844/637653.html', NULL, 'NO1521203244709', '第八百九十七章 寻宝', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (945, 'http://www.biquge5200.com/0_844/637654.html', NULL, 'NO1521203244709', '第八百九十八章 石洞库房', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (946, 'http://www.biquge5200.com/0_844/637655.html', NULL, 'NO1521203244709', '第八百九十九章 天妖傀', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (947, 'http://www.biquge5200.com/0_844/637656.html', NULL, 'NO1521203244709', '第九百章 养魂涎', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (948, 'http://www.biquge5200.com/0_844/637657.html', NULL, 'NO1521203244709', '第九百零一章 厄难毒体提前爆发', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (949, 'http://www.biquge5200.com/0_844/637658.html', NULL, 'NO1521203244709', '第九百零二章 封印', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (950, 'http://www.biquge5200.com/0_844/637659.html', NULL, 'NO1521203244709', '第九百零三章 炼化凶魂', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (951, 'http://www.biquge5200.com/0_844/637660.html', NULL, 'NO1521203244709', '第九百零四章 炼制天妖傀', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (952, 'http://www.biquge5200.com/0_844/637661.html', NULL, 'NO1521203244709', '第九百零五章 淬炼', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (953, 'http://www.biquge5200.com/0_844/637662.html', NULL, 'NO1521203244709', '第九百零六章 炼制成功！', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (954, 'http://www.biquge5200.com/0_844/637663.html', NULL, 'NO1521203244709', '第九百零七章 大动静', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (955, 'http://www.biquge5200.com/0_844/637664.html', NULL, 'NO1521203244709', '第九百零八章 炼制天魂融血丹', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (956, 'http://www.biquge5200.com/0_844/637665.html', NULL, 'NO1521203244709', '第九百零九章 血脉中的威压', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (957, 'http://www.biquge5200.com/0_844/637666.html', NULL, 'NO1521203244709', '第九百一十章 丹成', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (958, 'http://www.biquge5200.com/0_844/637667.html', NULL, 'NO1521203244709', '第九百一十一章 丹雷', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (959, 'http://www.biquge5200.com/0_844/637668.html', NULL, 'NO1521203244709', '第九百一十二章 地妖傀显威', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (960, 'http://www.biquge5200.com/0_844/637669.html', NULL, 'NO1521203244709', '第九百一十三章 再度突破！', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (961, 'http://www.biquge5200.com/0_844/637670.html', NULL, 'NO1521203244709', '第九百一十四章 离开', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (962, 'http://www.biquge5200.com/0_844/637671.html', NULL, 'NO1521203244709', '第九百一十五章 一殿一塔，二宗三谷，四方阁', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (963, 'http://www.biquge5200.com/0_844/637672.html', NULL, 'NO1521203244709', '第九百一十六章 关闭', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (964, 'http://www.biquge5200.com/0_844/637673.html', NULL, 'NO1521203244709', '第九百一十七章 红衣少女', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (965, 'http://www.biquge5200.com/0_844/637674.html', NULL, 'NO1521203244709', '第九百一十八章 六阶', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (966, 'http://www.biquge5200.com/0_844/637675.html', NULL, 'NO1521203244709', '第九百一十九章 修复', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (967, 'http://www.biquge5200.com/0_844/637676.html', NULL, 'NO1521203244709', '第九百二十章 空间风暴', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (968, 'http://www.biquge5200.com/0_844/637677.html', NULL, 'NO1521203244709', '第九百二十一章 通道惊魂', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (969, 'http://www.biquge5200.com/0_844/637678.html', NULL, 'NO1521203244709', '第九百二十二章 韩冲', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (970, 'http://www.biquge5200.com/0_844/637679.html', NULL, 'NO1521203244709', '第九百二十三章 韩家，韩雪', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (971, 'http://www.biquge5200.com/0_844/637680.html', NULL, 'NO1521203244709', '第九百二十四章  妖蛇夏莽', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (972, 'http://www.biquge5200.com/0_844/637681.html', NULL, 'NO1521203244709', '第九百二十五章 万蛇峡', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (973, 'http://www.biquge5200.com/0_844/637682.html', NULL, 'NO1521203244709', '第九百二十六章 神秘强者', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (974, 'http://www.biquge5200.com/0_844/637683.html', NULL, 'NO1521203244709', '第九百二十七章 空间之力', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (975, 'http://www.biquge5200.com/0_844/637684.html', NULL, 'NO1521203244709', '第九百二十八章 洪家', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (976, 'http://www.biquge5200.com/0_844/637685.html', NULL, 'NO1521203244709', '第九百二十九章 出手', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (977, 'http://www.biquge5200.com/0_844/637686.html', NULL, 'NO1521203244709', '第九百三十章 天北城', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (978, 'http://www.biquge5200.com/0_844/637687.html', NULL, 'NO1521203244709', '第九百三十一章 熟人', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (979, 'http://www.biquge5200.com/0_844/637688.html', NULL, 'NO1521203244709', '第九百三十二章 试探', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (980, 'http://www.biquge5200.com/0_844/637689.html', NULL, 'NO1521203244709', '第九百三十三章 九转风游步', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (981, 'http://www.biquge5200.com/0_844/637690.html', NULL, 'NO1521203244709', '第九百三十四章 诸乾', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (982, 'http://www.biquge5200.com/0_844/637691.html', NULL, 'NO1521203244709', '第九百三十五章  药老的关押之处', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (983, 'http://www.biquge5200.com/0_844/637692.html', NULL, 'NO1521203244709', '第九百三十六章 天石台', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (984, 'http://www.biquge5200.com/0_844/637693.html', NULL, 'NO1521203244709', '第九百三十七章 交手', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (985, 'http://www.biquge5200.com/0_844/637694.html', NULL, 'NO1521203244709', '第九百三十八章 雷神降临', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (986, 'http://www.biquge5200.com/0_844/637695.html', NULL, 'NO1521203244709', '第九百三十九章 灭雷锤！', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (987, 'http://www.biquge5200.com/0_844/637696.html', NULL, 'NO1521203244709', '第九百四十章 变故', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (988, 'http://www.biquge5200.com/0_844/637697.html', NULL, 'NO1521203244709', '第九百四十一章 不够资格', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (989, 'http://www.biquge5200.com/0_844/637698.html', NULL, 'NO1521203244709', '第九百四十二章 妖傀现身', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (990, 'http://www.biquge5200.com/0_844/637699.html', NULL, 'NO1521203244709', '第九百四十三章 洪家围剿', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (991, 'http://www.biquge5200.com/0_844/637700.html', NULL, 'NO1521203244709', '第九百四十四章 杀伐果断', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (992, 'http://www.biquge5200.com/0_844/637701.html', NULL, 'NO1521203244709', '第九百四十五章 疯狂的举动', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (993, 'http://www.biquge5200.com/0_844/637702.html', NULL, 'NO1521203244709', '第九百四十六章 三千雷幻身', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (994, 'http://www.biquge5200.com/0_844/637703.html', NULL, 'NO1521203244709', '第九百四十七章 残卷', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (995, 'http://www.biquge5200.com/0_844/637704.html', NULL, 'NO1521203244709', '第九百四十八章 滔天气势', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (996, 'http://www.biquge5200.com/0_844/637705.html', NULL, 'NO1521203244709', '第九百四十九章 击杀沈云', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (997, 'http://www.biquge5200.com/0_844/637706.html', NULL, 'NO1521203244709', '第九百五十章 斗宗强者的自爆', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (998, 'http://www.biquge5200.com/0_844/637707.html', NULL, 'NO1521203244709', '第九百五十一章 栖凤山', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (999, 'http://www.biquge5200.com/0_844/637708.html', NULL, 'NO1521203244709', '第九百五十二章 九星斗皇', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (1000, 'http://www.biquge5200.com/0_844/637709.html', NULL, 'NO1521203244709', '第九百五十三章 龙潭虎穴', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (1001, 'http://www.biquge5200.com/0_844/637710.html', NULL, 'NO1521203244709', '第九百五十四章 九天雷狱阵', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (1002, 'http://www.biquge5200.com/0_844/637711.html', NULL, 'NO1521203244709', '第九百五十五章 雷神之怒', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (1003, 'http://www.biquge5200.com/0_844/637712.html', NULL, 'NO1521203244709', '第九百五十六章 火莲之威', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (1004, 'http://www.biquge5200.com/0_844/637713.html', NULL, 'NO1521203244709', '第九百五十七章 斩杀洪天啸', NULL, b'0', '2018-03-16 20:27:36', '2018-03-16 20:27:36');
INSERT INTO `t_book_chapter` VALUES (1005, 'http://www.biquge5200.com/0_844/637714.html', NULL, 'NO1521203244709', '第九百五十八章 灵魂残印', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1006, 'http://www.biquge5200.com/0_844/637715.html', NULL, 'NO1521203244709', '第九百五十九章 天山血潭', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1007, 'http://www.biquge5200.com/0_844/637716.html', NULL, 'NO1521203244709', '第九百六十章 天雷子', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1008, 'http://www.biquge5200.com/0_844/637717.html', NULL, 'NO1521203244709', '第九百六十一章 追杀', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1009, 'http://www.biquge5200.com/0_844/637718.html', NULL, 'NO1521203244709', '第九百六十二章 破解', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1010, 'http://www.biquge5200.com/0_844/637719.html', NULL, 'NO1521203244709', '第九百六十三章 森林苦修', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1011, 'http://www.biquge5200.com/0_844/637720.html', NULL, 'NO1521203244709', '第九百六十四章 七阶苍狼王', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1012, 'http://www.biquge5200.com/0_844/637721.html', NULL, 'NO1521203244709', '第九百六十五章 魔兽界三大族群', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1013, 'http://www.biquge5200.com/0_844/637722.html', NULL, 'NO1521203244709', '第九百六十六章 锤炼分身', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1014, 'http://www.biquge5200.com/0_844/637723.html', NULL, 'NO1521203244709', '第九百六十七章 抵达天目山脉', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1015, 'http://www.biquge5200.com/0_844/637724.html', NULL, 'NO1521203244709', '第九百六十八章 凤小姐', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1016, 'http://www.biquge5200.com/0_844/637725.html', NULL, 'NO1521203244709', '第九百六十九章 初步交手', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1017, 'http://www.biquge5200.com/0_844/637726.html', NULL, 'NO1521203244709', '第九百七十章 黑衣男子平', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1018, 'http://www.biquge5200.com/0_844/637727.html', NULL, 'NO1521203244709', '第九百七十一章 故人', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1019, 'http://www.biquge5200.com/0_844/637728.html', NULL, 'NO1521203244709', '第九百七十二章 再见纳兰嫣然', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1020, 'http://www.biquge5200.com/0_844/637729.html', NULL, 'NO1521203244709', '第九百七十三章 王尘', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1021, 'http://www.biquge5200.com/0_844/637730.html', NULL, 'NO1521203244709', '第九百七十四章 迷阵', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1022, 'http://www.biquge5200.com/0_844/637731.html', NULL, 'NO1521203244709', '第九百七十五章 白狐引起的血案', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1023, 'http://www.biquge5200.com/0_844/637732.html', NULL, 'NO1521203244709', '第九百七十六章 天山台', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1024, 'http://www.biquge5200.com/0_844/637733.html', NULL, 'NO1521203244709', '第九百七十七章 鼠潮音波阵', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1025, 'http://www.biquge5200.com/0_844/637734.html', NULL, 'NO1521203244709', '第九百七十八章 闯关', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1026, 'http://www.biquge5200.com/0_844/637735.html', NULL, 'NO1521203244709', '第九百七十九章 慕青鸾', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1027, 'http://www.biquge5200.com/0_844/637736.html', NULL, 'NO1521203244709', '第九百八十章 声波对碰！', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1028, 'http://www.biquge5200.com/0_844/637737.html', NULL, 'NO1521203244709', '第九百八十一章 风尊者去向', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1029, 'http://www.biquge5200.com/0_844/637738.html', NULL, 'NO1521203244709', '第九百八十二章 交易', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1030, 'http://www.biquge5200.com/0_844/637739.html', NULL, 'NO1521203244709', '第九百八十三章 血潭之底', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1031, 'http://www.biquge5200.com/0_844/637740.html', NULL, 'NO1521203244709', '第九百八十四章 进入血潭', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1032, 'http://www.biquge5200.com/0_844/637741.html', NULL, 'NO1521203244709', '第九百八十五章 血潭潜修！', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1033, 'http://www.biquge5200.com/0_844/637742.html', NULL, 'NO1521203244709', '第九百八十六章 淬炼灵魂之效', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1034, 'http://www.biquge5200.com/0_844/637743.html', NULL, 'NO1521203244709', '第九百八十七章 突破！斗宗！', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1035, 'http://www.biquge5200.com/0_844/637744.html', NULL, 'NO1521203244709', '第九百八十八章 再见药老！', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1036, 'http://www.biquge5200.com/0_844/637745.html', NULL, 'NO1521203244709', '第九百八十九章 一星斗宗', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1037, 'http://www.biquge5200.com/0_844/637746.html', NULL, 'NO1521203244709', '第九百九十章 古界', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1038, 'http://www.biquge5200.com/0_844/637747.html', NULL, 'NO1521203244709', '第九百九十一章 炼', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1039, 'http://www.biquge5200.com/0_844/637748.html', NULL, 'NO1521203244709', '第九百九十二章 风雷山脉', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1040, 'http://www.biquge5200.com/0_844/637749.html', NULL, 'NO1521203244709', '第九百九十三章 雷山', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1041, 'http://www.biquge5200.com/0_844/637750.html', NULL, 'NO1521203244709', '第九百九十四章 四大尊者', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1042, 'http://www.biquge5200.com/0_844/637751.html', NULL, 'NO1521203244709', '第九百九十五章 大会开始', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1043, 'http://www.biquge5200.com/0_844/637752.html', NULL, 'NO1521203244709', '第九百九十六章 混乱筛选', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1044, 'http://www.biquge5200.com/0_844/637753.html', NULL, 'NO1521203244709', '第九百九十七章 来吧', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1045, 'http://www.biquge5200.com/0_844/637754.html', NULL, 'NO1521203244709', '第九百九十八章 战王尘', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1046, 'http://www.biquge5200.com/0_844/637755.html', NULL, 'NO1521203244709', '第九百九十九章 六合火', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1047, 'http://www.biquge5200.com/0_844/637756.html', NULL, 'NO1521203244709', '第一千章 识破身份', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1048, 'http://www.biquge5200.com/0_844/637757.html', NULL, 'NO1521203244709', '第一千零一章 奉陪便是', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1049, 'http://www.biquge5200.com/0_844/637758.html', NULL, 'NO1521203244709', '第一千零二章 年轻一辈的巅峰一战', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1050, 'http://www.biquge5200.com/0_844/637759.html', NULL, 'NO1521203244709', '第一千零三章 风杀指', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1051, 'http://www.biquge5200.com/0_844/637760.html', NULL, 'NO1521203244709', '第一千零四章 妖凰圣像', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1052, 'http://www.biquge5200.com/0_844/637761.html', NULL, 'NO1521203244709', '第一千零五章 圣像之力', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1053, 'http://www.biquge5200.com/0_844/637762.html', NULL, 'NO1521203244709', '第一千零六章 妖凰钟', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1054, 'http://www.biquge5200.com/0_844/637763.html', NULL, 'NO1521203244709', '第一千零七章 胜负', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1055, 'http://www.biquge5200.com/0_844/637764.html', NULL, 'NO1521203244709', '第一千零八章 古凰血精', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1056, 'http://www.biquge5200.com/0_844/637765.html', NULL, 'NO1521203244709', '第一千零九章 大会落幕', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1057, 'http://www.biquge5200.com/0_844/637766.html', NULL, 'NO1521203244709', '第一千零十一章 吸收', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1058, 'http://www.biquge5200.com/0_844/637767.html', NULL, 'NO1521203244709', '第一千零一十二章 骨翼变异', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1059, 'http://www.biquge5200.com/0_844/637768.html', NULL, 'NO1521203244709', '第一千零一十三章 当年旧事', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1060, 'http://www.biquge5200.com/0_844/637769.html', NULL, 'NO1521203244709', '第一千零一十四章 前往丹域', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1061, 'http://www.biquge5200.com/0_844/637770.html', NULL, 'NO1521203244709', '第一千零一十五章 修炼弄焰决', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1062, 'http://www.biquge5200.com/0_844/637771.html', NULL, 'NO1521203244709', '第一千零一十六章 锤炼兽火', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1063, 'http://www.biquge5200.com/0_844/637772.html', NULL, 'NO1521203244709', '第一千零一十七章 火种', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1064, 'http://www.biquge5200.com/0_844/637773.html', NULL, 'NO1521203244709', '第一千零一十八章 中域，天黄城', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1065, 'http://www.biquge5200.com/0_844/637774.html', NULL, 'NO1521203244709', '第一千零一十九章 虫洞争夺', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1066, 'http://www.biquge5200.com/0_844/637775.html', NULL, 'NO1521203244709', '第一千零二十章 故友相见', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1067, 'http://www.biquge5200.com/0_844/637776.html', NULL, 'NO1521203244709', '第一千零二十一章 一招', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1068, 'http://www.biquge5200.com/0_844/637777.html', NULL, 'NO1521203244709', '第一千零二十二章 前往焚炎谷', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1069, 'http://www.biquge5200.com/0_844/637778.html', NULL, 'NO1521203244709', '第一千零二十三章 炙火山脉', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1070, 'http://www.biquge5200.com/0_844/637779.html', NULL, 'NO1521203244709', '第一千零二十四章 唐震，九龙雷罡火！', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1071, 'http://www.biquge5200.com/0_844/637780.html', NULL, 'NO1521203244709', '第一千零二十五章 异火测试', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1072, 'http://www.biquge5200.com/0_844/637781.html', NULL, 'NO1521203244709', '第一千零二十六章 报酬', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1073, 'http://www.biquge5200.com/0_844/637782.html', NULL, 'NO1521203244709', '第一千零二十七章 争取', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1074, 'http://www.biquge5200.com/0_844/637783.html', NULL, 'NO1521203244709', '第一千零二十八章 火菩丹', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1075, 'http://www.biquge5200.com/0_844/637784.html', NULL, 'NO1521203244709', '第一千零二十九章 融合', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1076, 'http://www.biquge5200.com/0_844/637785.html', NULL, 'NO1521203244709', '第一千零三十章 变故', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1077, 'http://www.biquge5200.com/0_844/637786.html', NULL, 'NO1521203244709', '第一千零三十一章 力挽狂澜', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1078, 'http://www.biquge5200.com/0_844/637787.html', NULL, 'NO1521203244709', '第一千零三十二章 再见丹雷', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1079, 'http://www.biquge5200.com/0_844/637788.html', NULL, 'NO1521203244709', '第一千零三十三章 炼丹成功', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1080, 'http://www.biquge5200.com/0_844/637789.html', NULL, 'NO1521203244709', '第一千零三十四章 阴阳命魂丹', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1081, 'http://www.biquge5200.com/0_844/637790.html', NULL, 'NO1521203244709', '第一千零三十五章 受阻', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1082, 'http://www.biquge5200.com/0_844/637791.html', NULL, 'NO1521203244709', '第一千零三十六章 考验', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1083, 'http://www.biquge5200.com/0_844/637792.html', NULL, 'NO1521203244709', '第一千零三十七章 对战吴辰', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1084, 'http://www.biquge5200.com/0_844/637793.html', NULL, 'NO1521203244709', '第一千零三十八章 暗藏一手', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1085, 'http://www.biquge5200.com/0_844/637794.html', NULL, 'NO1521203244709', '第一千零三十九章 到手', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1086, 'http://www.biquge5200.com/0_844/637795.html', NULL, 'NO1521203244709', '第一千零四十章 传承秘法', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1087, 'http://www.biquge5200.com/0_844/637796.html', NULL, 'NO1521203244709', '第一千零四十一章 萧玄', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1088, 'http://www.biquge5200.com/0_844/637797.html', NULL, 'NO1521203244709', '第一千零四十二章 小医仙消息', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1089, 'http://www.biquge5200.com/0_844/637798.html', NULL, 'NO1521203244709', '第一千零四十三章 赶至中域', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1090, 'http://www.biquge5200.com/0_844/637799.html', NULL, 'NO1521203244709', '第一千零四十四章 叶城', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1091, 'http://www.biquge5200.com/0_844/637800.html', NULL, 'NO1521203244709', '第一千零四十五章 叶家', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1092, 'http://www.biquge5200.com/0_844/637801.html', NULL, 'NO1521203244709', '第一千零四十六章 去向', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1093, 'http://www.biquge5200.com/0_844/637802.html', NULL, 'NO1521203244709', '第一千零四十七章 落神涧', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1094, 'http://www.biquge5200.com/0_844/637803.html', NULL, 'NO1521203244709', '第一千零四十八章 冰河谷的剿杀', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1095, 'http://www.biquge5200.com/0_844/637804.html', NULL, 'NO1521203244709', '第一千零四十九章 逆转局面', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1096, 'http://www.biquge5200.com/0_844/637805.html', NULL, 'NO1521203244709', '第一千零五十章 不堪一击', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1097, 'http://www.biquge5200.com/0_844/637806.html', NULL, 'NO1521203244709', '第一千零五十一章 一个不留', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1098, 'http://www.biquge5200.com/0_844/637807.html', NULL, 'NO1521203244709', '第一千零五十二章 疗伤', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1099, 'http://www.biquge5200.com/0_844/637808.html', NULL, 'NO1521203244709', '第一千零五十三章 天毒蝎龙兽踪迹', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1100, 'http://www.biquge5200.com/0_844/637809.html', NULL, 'NO1521203244709', '第一千零五十四章 晋级', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1101, 'http://www.biquge5200.com/0_844/637810.html', NULL, 'NO1521203244709', '第一千零五十五章 天蝎毒龙兽', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1102, 'http://www.biquge5200.com/0_844/637811.html', NULL, 'NO1521203244709', '第一千零五十六章 蝎龙族', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1103, 'http://www.biquge5200.com/0_844/637812.html', NULL, 'NO1521203244709', '第一千零五十七章 剿杀天毒蝎龙兽', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1104, 'http://www.biquge5200.com/0_844/637813.html', NULL, 'NO1521203244709', '第一千零五十八章 击杀', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1105, 'http://www.biquge5200.com/0_844/637814.html', NULL, 'NO1521203244709', '第一千零五十九章 到手', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1106, 'http://www.biquge5200.com/0_844/637815.html', NULL, 'NO1521203244709', '第一千零六十章 炼制阴阳命魂丹', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1107, 'http://www.biquge5200.com/0_844/637816.html', NULL, 'NO1521203244709', '第一千零六十一章 炼制躯体', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1108, 'http://www.biquge5200.com/0_844/637817.html', NULL, 'NO1521203244709', '第一千零六十二章 斗宗巅峰', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1109, 'http://www.biquge5200.com/0_844/637818.html', NULL, 'NO1521203244709', '第一千零六十三章 以卵击石', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1110, 'http://www.biquge5200.com/0_844/637819.html', NULL, 'NO1521203244709', '第一千零六十四章 阳火', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1111, 'http://www.biquge5200.com/0_844/637820.html', NULL, 'NO1521203244709', '第一千零六十五章 黑火宗', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1112, 'http://www.biquge5200.com/0_844/637821.html', NULL, 'NO1521203244709', '第一千零六十六章 阳火古坛', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1113, 'http://www.biquge5200.com/0_844/637822.html', NULL, 'NO1521203244709', '第一千零六十七章 毒丹之法，开启！', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1114, 'http://www.biquge5200.com/0_844/637824.html', NULL, 'NO1521203244709', '第一千零六十八章 谁动你，我便杀谁！', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1115, 'http://www.biquge5200.com/0_844/637825.html', NULL, 'NO1521203244709', '第一千零六十九章 地心珠', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1116, 'http://www.biquge5200.com/0_844/637826.html', NULL, 'NO1521203244709', '第一千零七十章 夺取', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1117, 'http://www.biquge5200.com/0_844/637827.html', NULL, 'NO1521203244709', '第一千零七十一章 大战将至', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1118, 'http://www.biquge5200.com/0_844/637828.html', NULL, 'NO1521203244709', '第一千零七十二章 三变！', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1119, 'http://www.biquge5200.com/0_844/637829.html', NULL, 'NO1521203244709', '第一千零七十三章 恐怖的增幅', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1120, 'http://www.biquge5200.com/0_844/637830.html', NULL, 'NO1521203244709', '第一千零七十四章 摧枯拉朽', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1121, 'http://www.biquge5200.com/0_844/637831.html', NULL, 'NO1521203244709', '第一千零七十五章 离火焚天', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1122, 'http://www.biquge5200.com/0_844/637832.html', NULL, 'NO1521203244709', '第一千零七十六章 恐怖对轰', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1123, 'http://www.biquge5200.com/0_844/637833.html', NULL, 'NO1521203244709', '第一千零七十七章 震撼之战', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1124, 'http://www.biquge5200.com/0_844/637834.html', NULL, 'NO1521203244709', '第一千零七十八章 青海', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1125, 'http://www.biquge5200.com/0_844/637835.html', NULL, 'NO1521203244709', '第一千零七十九章 寒冰王座', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1126, 'http://www.biquge5200.com/0_844/637836.html', NULL, 'NO1521203244709', '第一千零八十章 冰尊者', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1127, 'http://www.biquge5200.com/0_844/637837.html', NULL, 'NO1521203244709', '第一千零八十一章 再见薰儿！', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1128, 'http://www.biquge5200.com/0_844/637838.html', NULL, 'NO1521203244709', '第一千零八十二章 金色火焰', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1129, 'http://www.biquge5200.com/0_844/637839.html', NULL, 'NO1521203244709', '第一千零八十三章 击溃', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1130, 'http://www.biquge5200.com/0_844/637840.html', NULL, 'NO1521203244709', '第一千零八十四章 退敌', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1131, 'http://www.biquge5200.com/0_844/637841.html', NULL, 'NO1521203244709', '第一千零八十五章 封印', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1132, 'http://www.biquge5200.com/0_844/637842.html', NULL, 'NO1521203244709', '第一千零八十六章 打算', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1133, 'http://www.biquge5200.com/0_844/637843.html', NULL, 'NO1521203244709', '第一千零八十七章 古玉之谜', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1134, 'http://www.biquge5200.com/0_844/637844.html', NULL, 'NO1521203244709', '第一千零八十八章 情迷', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1135, 'http://www.biquge5200.com/0_844/637845.html', NULL, 'NO1521203244709', '第一千零八十九章 解决魔毒斑', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1136, 'http://www.biquge5200.com/0_844/637846.html', NULL, 'NO1521203244709', '第一千零九十章 突破', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1137, 'http://www.biquge5200.com/0_844/637847.html', NULL, 'NO1521203244709', '第一千零九十一章 翎泉统领', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1138, 'http://www.biquge5200.com/0_844/637848.html', NULL, 'NO1521203244709', '第一千零九十二章 血玉令', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1139, 'http://www.biquge5200.com/0_844/637849.html', NULL, 'NO1521203244709', '第一千零九十三章 不堪一击！', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1140, 'http://www.biquge5200.com/0_844/637850.html', NULL, 'NO1521203244709', '第一千零九十四章 还债', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1141, 'http://www.biquge5200.com/0_844/637851.html', NULL, 'NO1521203244709', '第一千零九十五章 离开', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1142, 'http://www.biquge5200.com/0_844/637852.html', NULL, 'NO1521203244709', '第一千零九十六章 长老席', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1143, 'http://www.biquge5200.com/0_844/637853.html', NULL, 'NO1521203244709', '第一千零九十七章 苦修炼丹', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1144, 'http://www.biquge5200.com/0_844/637854.html', NULL, 'NO1521203244709', '第一千零九十八章 曹家', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1145, 'http://www.biquge5200.com/0_844/637855.html', NULL, 'NO1521203244709', '第一千零九十九章 打赌', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1146, 'http://www.biquge5200.com/0_844/637856.html', NULL, 'NO1521203244709', '第一千一百章 玩火', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1147, 'http://www.biquge5200.com/0_844/637857.html', NULL, 'NO1521203244709', '第一千一百零一章 曹家妖女', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1148, 'http://www.biquge5200.com/0_844/637858.html', NULL, 'NO1521203244709', '第一千一百零二章 压力', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1149, 'http://www.biquge5200.com/0_844/637859.html', NULL, 'NO1521203244709', '第一千一百零三章 灵魂境界', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1150, 'http://www.biquge5200.com/0_844/637860.html', NULL, 'NO1521203244709', '第一千一百零四章 赶往圣丹城！', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1151, 'http://www.biquge5200.com/0_844/637861.html', NULL, 'NO1521203244709', '第一千一百零五章 丹家', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1152, 'http://www.biquge5200.com/0_844/637862.html', NULL, 'NO1521203244709', '第一千一百零六章 星空夜遇', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1153, 'http://www.biquge5200.com/0_844/637863.html', NULL, 'NO1521203244709', '第一千一百零七章 分塔', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1154, 'http://www.biquge5200.com/0_844/637864.html', NULL, 'NO1521203244709', '第一千一百零八章 测试', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1155, 'http://www.biquge5200.com/0_844/637865.html', NULL, 'NO1521203244709', '第一千一百零九章 七品中级炼药师', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1156, 'http://www.biquge5200.com/0_844/637866.html', NULL, 'NO1521203244709', '第一千一百一十章 教训', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1157, 'http://www.biquge5200.com/0_844/637867.html', NULL, 'NO1521203244709', '第一千一百一十一章 炼药师交易会', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1158, 'http://www.biquge5200.com/0_844/637868.html', NULL, 'NO1521203244709', '第一千一百一十二章 血精妖果', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1159, 'http://www.biquge5200.com/0_844/637869.html', NULL, 'NO1521203244709', '第一千一百一十三章 铜片', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1160, 'http://www.biquge5200.com/0_844/637870.html', NULL, 'NO1521203244709', '第一千一百一十四章 交换', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1161, 'http://www.biquge5200.com/0_844/637871.html', NULL, 'NO1521203244709', '第一千一百一十五章 玄冥宗', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1162, 'http://www.biquge5200.com/0_844/637872.html', NULL, 'NO1521203244709', '第一千一百一十六章 孕灵粉尘', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1163, 'http://www.biquge5200.com/0_844/637873.html', NULL, 'NO1521203244709', '第一千一百一十七章 无名口诀', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1164, 'http://www.biquge5200.com/0_844/637874.html', NULL, 'NO1521203244709', '第一千一百一十八章 吸纳', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1165, 'http://www.biquge5200.com/0_844/637875.html', NULL, 'NO1521203244709', '第一千一百一十九章 五大家族齐聚', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1166, 'http://www.biquge5200.com/0_844/637876.html', NULL, 'NO1521203244709', '第一千一百二十章 考核开始！', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1167, 'http://www.biquge5200.com/0_844/637877.html', NULL, 'NO1521203244709', '第一千一百二十一章 灵魂测试', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1168, 'http://www.biquge5200.com/0_844/637878.html', NULL, 'NO1521203244709', '第一千一百二十二章 艳惊四座', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1169, 'http://www.biquge5200.com/0_844/637879.html', NULL, 'NO1521203244709', '第一千一百二十三章 灵魂操控', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1170, 'http://www.biquge5200.com/0_844/637880.html', NULL, 'NO1521203244709', '第一千一百二十四章 灵魂交手', NULL, b'0', '2018-03-16 20:27:37', '2018-03-16 20:27:37');
INSERT INTO `t_book_chapter` VALUES (1171, 'http://www.biquge5200.com/0_844/637881.html', NULL, 'NO1521203244709', '第一千一百二十五章 偷学', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1172, 'http://www.biquge5200.com/0_844/637882.html', NULL, 'NO1521203244709', '第一千一百二十六章 获胜', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1173, 'http://www.biquge5200.com/0_844/637883.html', NULL, 'NO1521203244709', '第一千一百二十七章 不速之客', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1174, 'http://www.biquge5200.com/0_844/637884.html', NULL, 'NO1521203244709', '第一千一百二十八章 神秘黑袍人', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1175, 'http://www.biquge5200.com/0_844/637885.html', NULL, 'NO1521203244709', '第一千一百二十九章 慕骨老人', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1176, 'http://www.biquge5200.com/0_844/637886.html', NULL, 'NO1521203244709', '第一千一百三十章 玄空子', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1177, 'http://www.biquge5200.com/0_844/637887.html', NULL, 'NO1521203244709', '第一千一百三十一章 宋清', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1178, 'http://www.biquge5200.com/0_844/637888.html', NULL, 'NO1521203244709', '第一千一百三十二章 魂手印', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1179, 'http://www.biquge5200.com/0_844/637889.html', NULL, 'NO1521203244709', '第一千一百三十三章 丹会开始！', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1180, 'http://www.biquge5200.com/0_844/637890.html', NULL, 'NO1521203244709', '第一千一百三十四章 两大关卡', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1181, 'http://www.biquge5200.com/0_844/637891.html', NULL, 'NO1521203244709', '第一千一百三十五章 过关', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1182, 'http://www.biquge5200.com/0_844/637892.html', NULL, 'NO1521203244709', '第一千一百三十六章 丹界入口', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1183, 'http://www.biquge5200.com/0_844/637893.html', NULL, 'NO1521203244709', '第一千一百三十七章 千年地黄精', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1184, 'http://www.biquge5200.com/0_844/637894.html', NULL, 'NO1521203244709', '第一千一百三十八章 地心魂髓', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1185, 'http://www.biquge5200.com/0_844/637895.html', NULL, 'NO1521203244709', '第一千一百三十九章 黄衣老者', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1186, 'http://www.biquge5200.com/0_844/637896.html', NULL, 'NO1521203244709', '第一千一百四十章 万药山脉', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1187, 'http://www.biquge5200.com/0_844/637897.html', NULL, 'NO1521203244709', '第一千一百四十一章 再遇', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1188, 'http://www.biquge5200.com/0_844/637898.html', NULL, 'NO1521203244709', '第一千一百四十二章 动手', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1189, 'http://www.biquge5200.com/0_844/637899.html', NULL, 'NO1521203244709', '第一千一百四十三章 下杀手', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1190, 'http://www.biquge5200.com/0_844/637900.html', NULL, 'NO1521203244709', '第一千一百四十四章 离去', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1191, 'http://www.biquge5200.com/0_844/637901.html', NULL, 'NO1521203244709', '第一千一百四十五章 丹灵浆', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1192, 'http://www.biquge5200.com/0_844/637902.html', NULL, 'NO1521203244709', '第一千一百四十六章 下药', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1193, 'http://www.biquge5200.com/0_844/637903.html', NULL, 'NO1521203244709', '第一千一百四十七章 杀戮', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1194, 'http://www.biquge5200.com/0_844/637904.html', NULL, 'NO1521203244709', '第一千一百四十八章 联手', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1195, 'http://www.biquge5200.com/0_844/637905.html', NULL, 'NO1521203244709', '第一千一百四十九章 逃命', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1196, 'http://www.biquge5200.com/0_844/637906.html', NULL, 'NO1521203244709', '第一千一百五十章 紫研，熊战', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1197, 'http://www.biquge5200.com/0_844/637907.html', NULL, 'NO1521203244709', '第一千一百五十一章 震退', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1198, 'http://www.biquge5200.com/0_844/637908.html', NULL, 'NO1521203244709', '第一千一百五十二章 药材广场', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1199, 'http://www.biquge5200.com/0_844/637909.html', NULL, 'NO1521203244709', '第一千一百五十三章 调和地心魂髓', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1200, 'http://www.biquge5200.com/0_844/637910.html', NULL, 'NO1521203244709', '第一千一百五十四章 晋入八品！', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1201, 'http://www.biquge5200.com/0_844/637911.html', NULL, 'NO1521203244709', '第一千一百五十五章 赶往出口', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1202, 'http://www.biquge5200.com/0_844/637912.html', NULL, 'NO1521203244709', '第一千一百五十六章 离开丹界', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1203, 'http://www.biquge5200.com/0_844/637913.html', NULL, 'NO1521203244709', '第一千一百五十七章 重头戏', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1204, 'http://www.biquge5200.com/0_844/637914.html', NULL, 'NO1521203244709', '第一千一百五十八章 血妖焰火', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1205, 'http://www.biquge5200.com/0_844/637915.html', NULL, 'NO1521203244709', '第一千一百五十九章 炼丹开始', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1206, 'http://www.biquge5200.com/0_844/637916.html', NULL, 'NO1521203244709', '第一千一百六十章 丹雷屡现', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1207, 'http://www.biquge5200.com/0_844/637917.html', NULL, 'NO1521203244709', '第一千一百六十一章 强者迭出', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1208, 'http://www.biquge5200.com/0_844/637918.html', NULL, 'NO1521203244709', '第一千一百六十二章 八品丹药！', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1209, 'http://www.biquge5200.com/0_844/637919.html', NULL, 'NO1521203244709', '第一千一百六十三章 三色丹雷', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1210, 'http://www.biquge5200.com/0_844/637920.html', NULL, 'NO1521203244709', '第一千一百六十四章 放手一搏', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1211, 'http://www.biquge5200.com/0_844/637921.html', NULL, 'NO1521203244709', '第一千一百六十五章 升灵', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1212, 'http://www.biquge5200.com/0_844/637922.html', NULL, 'NO1521203244709', '第一千一百六十六章 五色丹雷！', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1213, 'http://www.biquge5200.com/0_844/637923.html', NULL, 'NO1521203244709', '第一千一百六十七章 迎接丹雷', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1214, 'http://www.biquge5200.com/0_844/637924.html', NULL, 'NO1521203244709', '第一千一百六十八章 进化，天妖傀！', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1215, 'http://www.biquge5200.com/0_844/637925.html', NULL, 'NO1521203244709', '第一千一百六十九章 丹会落幕', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1216, 'http://www.biquge5200.com/0_844/637926.html', NULL, 'NO1521203244709', '第一千一百七十章 玄衣，天雷子', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1217, 'http://www.biquge5200.com/0_844/637927.html', NULL, 'NO1521203244709', '第一千一百七十一章 两女对恃', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1218, 'http://www.biquge5200.com/0_844/637928.html', NULL, 'NO1521203244709', '第一千一百七十二章 龙印', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1219, 'http://www.biquge5200.com/0_844/637929.html', NULL, 'NO1521203244709', '第一千一百七十三章 星域，三千焱炎火！', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1220, 'http://www.biquge5200.com/0_844/637930.html', NULL, 'NO1521203244709', '第一千一百七十四章 凶悍的三千焱炎火', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1221, 'http://www.biquge5200.com/0_844/637931.html', NULL, 'NO1521203244709', '第一千一百七十五章 破封', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1222, 'http://www.biquge5200.com/0_844/637932.html', NULL, 'NO1521203244709', '第一千一百七十六章 魂殿援手', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1223, 'http://www.biquge5200.com/0_844/637933.html', NULL, 'NO1521203244709', '第一千一百七十七章 激战', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1224, 'http://www.biquge5200.com/0_844/637934.html', NULL, 'NO1521203244709', '第一千一百七十八章 动手', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1225, 'http://www.biquge5200.com/0_844/637935.html', NULL, 'NO1521203244709', '第一千一百七十九章 符印', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1226, 'http://www.biquge5200.com/0_844/637936.html', NULL, 'NO1521203244709', '第一千一百八十章 灵魂交战', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1227, 'http://www.biquge5200.com/0_844/637937.html', NULL, 'NO1521203244709', '第一千一百八十一章 爆发', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1228, 'http://www.biquge5200.com/0_844/637938.html', NULL, 'NO1521203244709', '第一千一百八十二章 僵持', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1229, 'http://www.biquge5200.com/0_844/637939.html', NULL, 'NO1521203244709', '第一千一百八十三章 降服', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1230, 'http://www.biquge5200.com/0_844/637940.html', NULL, 'NO1521203244709', '第一千一百八十四章 吞噬异火，九星斗宗', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1231, 'http://www.biquge5200.com/0_844/637941.html', NULL, 'NO1521203244709', '第一千一百八十五章 火雷子', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1232, 'http://www.biquge5200.com/0_844/637942.html', NULL, 'NO1521203244709', '第一千一百八十六章 情报', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1233, 'http://www.biquge5200.com/0_844/637943.html', NULL, 'NO1521203244709', '第一千一百八十七章 茯苓青丹', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1234, 'http://www.biquge5200.com/0_844/637944.html', NULL, 'NO1521203244709', '第一千一百八十八章 易尘', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1235, 'http://www.biquge5200.com/0_844/637945.html', NULL, 'NO1521203244709', '第一千一百八十九章 交手', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1236, 'http://www.biquge5200.com/0_844/637946.html', NULL, 'NO1521203244709', '第一千一百九十章 承让', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1237, 'http://www.biquge5200.com/0_844/637947.html', NULL, 'NO1521203244709', '第一千一百九十一章 情报到手', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1238, 'http://www.biquge5200.com/0_844/637948.html', NULL, 'NO1521203244709', '第一千一百九十二章 亡魂山脉', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1239, 'http://www.biquge5200.com/0_844/637949.html', NULL, 'NO1521203244709', '第一千一百九十三章 万事具备', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1240, 'http://www.biquge5200.com/0_844/637950.html', NULL, 'NO1521203244709', '第一千一百九十四章 营救行动', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1241, 'http://www.biquge5200.com/0_844/637951.html', NULL, 'NO1521203244709', '第一千一百九十五章 噬石魔蚁', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1242, 'http://www.biquge5200.com/0_844/637952.html', NULL, 'NO1521203244709', '第一千一百九十六章 巨殿', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1243, 'http://www.biquge5200.com/0_844/637953.html', NULL, 'NO1521203244709', '第一千一百九十七章 大战', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1244, 'http://www.biquge5200.com/0_844/637954.html', NULL, 'NO1521203244709', '第一千一百九十八章 杀戮', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1245, 'http://www.biquge5200.com/0_844/637955.html', NULL, 'NO1521203244709', '第一千一百九十九章 摘星老鬼', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1246, 'http://www.biquge5200.com/0_844/637956.html', NULL, 'NO1521203244709', '第一千两百章 戏耍', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1247, 'http://www.biquge5200.com/0_844/637957.html', NULL, 'NO1521203244709', '第一千两百零一章 局势', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1248, 'http://www.biquge5200.com/0_844/637958.html', NULL, 'NO1521203244709', '第一千两百零二章 拖延', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1249, 'http://www.biquge5200.com/0_844/637959.html', NULL, 'NO1521203244709', '第一千两百零三章 两败俱伤', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1250, 'http://www.biquge5200.com/0_844/637960.html', NULL, 'NO1521203244709', '第一千两百零四章 神秘之人', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1251, 'http://www.biquge5200.com/0_844/637961.html', NULL, 'NO1521203244709', '第一千两百零五章 重伤', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1252, 'http://www.biquge5200.com/0_844/637962.html', NULL, 'NO1521203244709', '第一千两百零六章 星陨阁', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1253, 'http://www.biquge5200.com/0_844/637963.html', NULL, 'NO1521203244709', '第一千两百零七章 时间飞逝', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1254, 'http://www.biquge5200.com/0_844/637964.html', NULL, 'NO1521203244709', '第一千两百零八章 晋阶，斗尊！', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1255, 'http://www.biquge5200.com/0_844/637965.html', NULL, 'NO1521203244709', '第一千两百零九章 苏醒', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1256, 'http://www.biquge5200.com/0_844/637966.html', NULL, 'NO1521203244709', '第一千两百一十章 远古遗迹', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1257, 'http://www.biquge5200.com/0_844/637967.html', NULL, 'NO1521203244709', '第一千两百一十一章 兽域', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1258, 'http://www.biquge5200.com/0_844/637968.html', NULL, 'NO1521203244709', '第一千两百一十二章 骸骨山脉', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1259, 'http://www.biquge5200.com/0_844/637969.html', NULL, 'NO1521203244709', '第一千两百一十三章 找场子', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1260, 'http://www.biquge5200.com/0_844/637970.html', NULL, 'NO1521203244709', '第一千两百一十四章 滚下去', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1261, 'http://www.biquge5200.com/0_844/637971.html', NULL, 'NO1521203244709', '第一千两百一十五章 空间封印', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1262, 'http://www.biquge5200.com/0_844/637972.html', NULL, 'NO1521203244709', '第一千两百一十六章 黑影人', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1263, 'http://www.biquge5200.com/0_844/637973.html', NULL, 'NO1521203244709', '第一千两百一十七章 遗迹开启', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1264, 'http://www.biquge5200.com/0_844/637974.html', NULL, 'NO1521203244709', '第一千两百一十八章 对头同现', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1265, 'http://www.biquge5200.com/0_844/637975.html', NULL, 'NO1521203244709', '第一千两百一十九章 进入遗迹', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1266, 'http://www.biquge5200.com/0_844/637976.html', NULL, 'NO1521203244709', '第一千两百二十章 另有玄机的火道', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1267, 'http://www.biquge5200.com/0_844/637977.html', NULL, 'NO1521203244709', '第一千两百二十一章 收服', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1268, 'http://www.biquge5200.com/0_844/637978.html', NULL, 'NO1521203244709', '第一千两百二十二章 远古森林', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1269, 'http://www.biquge5200.com/0_844/637979.html', NULL, 'NO1521203244709', '第一千两百二十三章 魂婴妖树', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1270, 'http://www.biquge5200.com/0_844/637980.html', NULL, 'NO1521203244709', '第一千两百二十四章 龙凰本源果', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1271, 'http://www.biquge5200.com/0_844/637981.html', NULL, 'NO1521203244709', '第一千两百二十五章 凰轩', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1272, 'http://www.biquge5200.com/0_844/637982.html', NULL, 'NO1521203244709', '第一千两百二十六章 天凰祖魂', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1273, 'http://www.biquge5200.com/0_844/637983.html', NULL, 'NO1521203244709', '第一千两百二十七章 祖魂比拼', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1274, 'http://www.biquge5200.com/0_844/637984.html', NULL, 'NO1521203244709', '第一千两百二十八章  远古丹药', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1275, 'http://www.biquge5200.com/0_844/637985.html', NULL, 'NO1521203244709', '第一千两百二十九章 勾引丹兽', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1276, 'http://www.biquge5200.com/0_844/637986.html', NULL, 'NO1521203244709', '第一千两百三十章 青鳞？', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1277, 'http://www.biquge5200.com/0_844/637987.html', NULL, 'NO1521203244709', '第一千两百三十一章 故人相见', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1278, 'http://www.biquge5200.com/0_844/637988.html', NULL, 'NO1521203244709', '第一千两百三十二章 恐怖的碧蛇三花瞳', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1279, 'http://www.biquge5200.com/0_844/637989.html', NULL, 'NO1521203244709', '第一千两百三十三章 主殿', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1280, 'http://www.biquge5200.com/0_844/637990.html', NULL, 'NO1521203244709', '第一千两百三十四章 封印消失', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1281, 'http://www.biquge5200.com/0_844/637991.html', NULL, 'NO1521203244709', '第一千两百三十五章 混乱', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1282, 'http://www.biquge5200.com/0_844/637992.html', NULL, 'NO1521203244709', '第一千两百三十六章  争夺远古卷轴', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1283, 'http://www.biquge5200.com/0_844/637993.html', NULL, 'NO1521203244709', '第一千两百三十七章 斩杀', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1284, 'http://www.biquge5200.com/0_844/637994.html', NULL, 'NO1521203244709', '第一千两百三十八章 斗圣骨骸', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1285, 'http://www.biquge5200.com/0_844/637995.html', NULL, 'NO1521203244709', '第一千两百三十九章 联手', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1286, 'http://www.biquge5200.com/0_844/637996.html', NULL, 'NO1521203244709', '第一千两百四十章 大天造化掌', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1287, 'http://www.biquge5200.com/0_844/637997.html', NULL, 'NO1521203244709', '第一千两百四十一章 争抢骸骨', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1288, 'http://www.biquge5200.com/0_844/637998.html', NULL, 'NO1521203244709', '第一千两百四十二章 收集骨骸', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1289, 'http://www.biquge5200.com/0_844/637999.html', NULL, 'NO1521203244709', '第一千两百四十三章 各方插手', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1290, 'http://www.biquge5200.com/0_844/638000.html', NULL, 'NO1521203244709', '第一千两百四十四章 四印叠加', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1291, 'http://www.biquge5200.com/0_844/638001.html', NULL, 'NO1521203244709', '第一千两百四十五章 血战', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1292, 'http://www.biquge5200.com/0_844/638002.html', NULL, 'NO1521203244709', '第一千两百四十六章 援手', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1293, 'http://www.biquge5200.com/0_844/638003.html', NULL, 'NO1521203244709', '第一千两百四十七章 黑擎', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1294, 'http://www.biquge5200.com/0_844/638004.html', NULL, 'NO1521203244709', '第一千两百四十八章 分别', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1295, 'http://www.biquge5200.com/0_844/638005.html', NULL, 'NO1521203244709', '第一千两百四十九章 神秘纹身', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1296, 'http://www.biquge5200.com/0_844/638006.html', NULL, 'NO1521203244709', '第一千两百五十章 远古天蛇', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1297, 'http://www.biquge5200.com/0_844/638007.html', NULL, 'NO1521203244709', '第一千两百五十一章 天阶斗技', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1298, 'http://www.biquge5200.com/0_844/638008.html', NULL, 'NO1521203244709', '第一千两百五十二章 造化圣者', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1299, 'http://www.biquge5200.com/0_844/638009.html', NULL, 'NO1521203244709', '第一千两百五十三章 完美躯体', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1300, 'http://www.biquge5200.com/0_844/638010.html', NULL, 'NO1521203244709', '第一千两百五十四章 斗圣骨髓', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1301, 'http://www.biquge5200.com/0_844/638011.html', NULL, 'NO1521203244709', '第一千两百五十五章 大敌来临', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1302, 'http://www.biquge5200.com/0_844/638012.html', NULL, 'NO1521203244709', '第一千两百五十六章 混战', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1303, 'http://www.biquge5200.com/0_844/638013.html', NULL, 'NO1521203244709', '第一千两百五十七章 施展', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1304, 'http://www.biquge5200.com/0_844/638014.html', NULL, 'NO1521203244709', '第一千两百五十八章 凄惨下场', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1305, 'http://www.biquge5200.com/0_844/638015.html', NULL, 'NO1521203244709', '第一千两百五十九章 大展神威', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1306, 'http://www.biquge5200.com/0_844/638016.html', NULL, 'NO1521203244709', '第一千两百六十章 半圣', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1307, 'http://www.biquge5200.com/0_844/638017.html', NULL, 'NO1521203244709', '第一千两百六十一章 药圣，药尘！', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1308, 'http://www.biquge5200.com/0_844/638018.html', NULL, 'NO1521203244709', '第一千两百六十二章 熟人再见', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1309, 'http://www.biquge5200.com/0_844/638019.html', NULL, 'NO1521203244709', '第一千两百六十三章 际遇', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1310, 'http://www.biquge5200.com/0_844/638020.html', NULL, 'NO1521203244709', '第一千两百六十四章 赶往花宗', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1311, 'http://www.biquge5200.com/0_844/638021.html', NULL, 'NO1521203244709', '第一千两百六十五章 再见云韵', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1312, 'http://www.biquge5200.com/0_844/638022.html', NULL, 'NO1521203244709', '第一千两百六十六章 妖花邪君', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1313, 'http://www.biquge5200.com/0_844/638023.html', NULL, 'NO1521203244709', '第一千两百六十七章 气化天地', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1314, 'http://www.biquge5200.com/0_844/638024.html', NULL, 'NO1521203244709', '第一千两百六十八章 宗主之位', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1315, 'http://www.biquge5200.com/0_844/638025.html', NULL, 'NO1521203244709', '第一千两百六十九章 炼化', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1316, 'http://www.biquge5200.com/0_844/638026.html', NULL, 'NO1521203244709', '第一千两百七十章 天地异象', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1317, 'http://www.biquge5200.com/0_844/638027.html', NULL, 'NO1521203244709', '第一千两百七十一章 准天阶功法', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1318, 'http://www.biquge5200.com/0_844/638028.html', NULL, 'NO1521203244709', '第一千两百七十二章 撕裂封印', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1319, 'http://www.biquge5200.com/0_844/638029.html', NULL, 'NO1521203244709', '第一千两百七十三章 堵截', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1320, 'http://www.biquge5200.com/0_844/638030.html', NULL, 'NO1521203244709', '第一千两百七十四章 激战', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1321, 'http://www.biquge5200.com/0_844/638031.html', NULL, 'NO1521203244709', '第一千两百七十五章 功法显威', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1322, 'http://www.biquge5200.com/0_844/638032.html', NULL, 'NO1521203244709', '第一千两百七十六章 以一敌三', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1323, 'http://www.biquge5200.com/0_844/638033.html', NULL, 'NO1521203244709', '第一千两百七十七章 轰飞', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1324, 'http://www.biquge5200.com/0_844/638034.html', NULL, 'NO1521203244709', '第一千两百七十八章 古龙岛', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1325, 'http://www.biquge5200.com/0_844/638035.html', NULL, 'NO1521203244709', '第一千两百七十九章 分裂的太虚古龙一族', NULL, b'0', '2018-03-16 20:27:38', '2018-03-16 20:27:38');
INSERT INTO `t_book_chapter` VALUES (1326, 'http://www.biquge5200.com/0_844/638036.html', NULL, 'NO1521203244709', '第一千两百八十章 动手', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1327, 'http://www.biquge5200.com/0_844/638037.html', NULL, 'NO1521203244709', '第一千两百八十一章 炼化龙凰晶层', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1328, 'http://www.biquge5200.com/0_844/638038.html', NULL, 'NO1521203244709', '第一千两百八十二章 混乱', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1329, 'http://www.biquge5200.com/0_844/638039.html', NULL, 'NO1521203244709', '第一千两百八十三章 苏醒', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1330, 'http://www.biquge5200.com/0_844/638040.html', NULL, 'NO1521203244709', '第一千两百八十五章 龙皇，紫研', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1331, 'http://www.biquge5200.com/0_844/638041.html', NULL, 'NO1521203244709', '第一千两百八十五章 龙凰古甲', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1332, 'http://www.biquge5200.com/0_844/638042.html', NULL, 'NO1521203244709', '第一千两百八十六章 虚空雷池', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1333, 'http://www.biquge5200.com/0_844/638043.html', NULL, 'NO1521203244709', '第一千两百八十七章 晋级五星', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1334, 'http://www.biquge5200.com/0_844/638044.html', NULL, 'NO1521203244709', '第一千两百八十八章 邙姓老者', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1335, 'http://www.biquge5200.com/0_844/638045.html', NULL, 'NO1521203244709', '第一千两百八十九章 离开龙岛', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1336, 'http://www.biquge5200.com/0_844/638046.html', NULL, 'NO1521203244709', '第一千两百九十章 远古八族', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1337, 'http://www.biquge5200.com/0_844/638047.html', NULL, 'NO1521203244709', '第一千两百九十一章 玉贴', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1338, 'http://www.biquge5200.com/0_844/638048.html', NULL, 'NO1521203244709', '第一千两百九十二章 太一魂决', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1339, 'http://www.biquge5200.com/0_844/638049.html', NULL, 'NO1521203244709', '第一千两百九十三章 赶往东域', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1340, 'http://www.biquge5200.com/0_844/638050.html', NULL, 'NO1521203244709', '第一千两百九十四章 冤家路窄', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1341, 'http://www.biquge5200.com/0_844/638051.html', NULL, 'NO1521203244709', '第一千两百九十五章 喜欢', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1342, 'http://www.biquge5200.com/0_844/638052.html', NULL, 'NO1521203244709', '第一千两百九十六章 八大统领而，四大都统', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1343, 'http://www.biquge5200.com/0_844/638053.html', NULL, 'NO1521203244709', '第一千两百九十七章 三统领杨皓', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1344, 'http://www.biquge5200.com/0_844/638054.html', NULL, 'NO1521203244709', '第一千两百九十八章 炎族', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1345, 'http://www.biquge5200.com/0_844/638055.html', NULL, 'NO1521203244709', '第一千两百九十九章魂崖', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1346, 'http://www.biquge5200.com/0_844/638056.html', NULL, 'NO1521203244709', '第一千三百章古界开启', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1347, 'http://www.biquge5200.com/0_844/638057.html', NULL, 'NO1521203244709', '第一千三百零一章天墓', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1348, 'http://www.biquge5200.com/0_844/638058.html', NULL, 'NO1521203244709', '第一千三百零二章古真', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1349, 'http://www.biquge5200.com/0_844/638059.html', NULL, 'NO1521203244709', '第一千三百零三章下马威', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1350, 'http://www.biquge5200.com/0_844/638060.html', NULL, 'NO1521203244709', '第一千三百零四章 修罗都统', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1351, 'http://www.biquge5200.com/0_844/638061.html', NULL, 'NO1521203244709', '第一千三百零五章 邙天尺', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1352, 'http://www.biquge5200.com/0_844/638062.html', NULL, 'NO1521203244709', '第一千三百零六章 仪式开始', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1353, 'http://www.biquge5200.com/0_844/638063.html', NULL, 'NO1521203244709', '第一千三百零七章 血脉等级，族纹！', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1354, 'http://www.biquge5200.com/0_844/638064.html', NULL, 'NO1521203244709', '第一千三百零八章 挑战', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1355, 'http://www.biquge5200.com/0_844/638065.html', NULL, 'NO1521203244709', '第一千三百零九章 劲敌！', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1356, 'http://www.biquge5200.com/0_844/638066.html', NULL, 'NO1521203244709', '第一千三百一十章 巅峰交手', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1357, 'http://www.biquge5200.com/0_844/638067.html', NULL, 'NO1521203244709', '第一千三百一十一章 大寂灭指【第二更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1358, 'http://www.biquge5200.com/0_844/638068.html', NULL, 'NO1521203244709', '第一千三百一十二章落幕！', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1359, 'http://www.biquge5200.com/0_844/638069.html', NULL, 'NO1521203244709', '第一千三百一十三章 胜！', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1360, 'http://www.biquge5200.com/0_844/638070.html', NULL, 'NO1521203244709', '第一千三百一十四章神品血脉', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1361, 'http://www.biquge5200.com/0_844/638071.html', NULL, 'NO1521203244709', '第一千三百一十五章七彩族纹', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1362, 'http://www.biquge5200.com/0_844/638072.html', NULL, 'NO1521203244709', '第一千三百一十六章相见', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1363, 'http://www.biquge5200.com/0_844/638073.html', NULL, 'NO1521203244709', '第一千三百一十七章 交谈', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1364, 'http://www.biquge5200.com/0_844/638074.html', NULL, 'NO1521203244709', '第一千三百一十八章天墓开启', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1365, 'http://www.biquge5200.com/0_844/638075.html', NULL, 'NO1521203244709', '第一千三百一十九章 进入天墓', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1366, 'http://www.biquge5200.com/0_844/638076.html', NULL, 'NO1521203244709', '第一千三百二十章 枯燥的修行', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1367, 'http://www.biquge5200.com/0_844/638077.html', NULL, 'NO1521203244709', '第一千三百二十一章 魂崖，魂厉', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1368, 'http://www.biquge5200.com/0_844/638078.html', NULL, 'NO1521203244709', '第一千三百二十二章晋级六星', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1369, 'http://www.biquge5200.com/0_844/638079.html', NULL, 'NO1521203244709', '第一千三百二十三章追逃', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1370, 'http://www.biquge5200.com/0_844/638080.html', NULL, 'NO1521203244709', '第一千三百二十四章 九星能量体', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1371, 'http://www.biquge5200.com/0_844/638081.html', NULL, 'NO1521203244709', '第一千三百二十五章 紫色的天妖傀', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1372, 'http://www.biquge5200.com/0_844/638082.html', NULL, 'NO1521203244709', '第一千三百二十六章 大风暴', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1373, 'http://www.biquge5200.com/0_844/638083.html', NULL, 'NO1521203244709', '第一千三百二十七章 聚集', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1374, 'http://www.biquge5200.com/0_844/638084.html', NULL, 'NO1521203244709', '第一千三百二十八章远古噬虫', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1375, 'http://www.biquge5200.com/0_844/638085.html', NULL, 'NO1521203244709', '第一千三百二十九章 收取报酬', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1376, 'http://www.biquge5200.com/0_844/638086.html', NULL, 'NO1521203244709', '第一千三百三十章 开辟晶壁', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1377, 'http://www.biquge5200.com/0_844/638087.html', NULL, 'NO1521203244709', '第一千三百三十一章 第三层', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1378, 'http://www.biquge5200.com/0_844/638088.html', NULL, 'NO1521203244709', '第一千三百三十二章 半圣能量体', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1379, 'http://www.biquge5200.com/0_844/638089.html', NULL, 'NO1521203244709', '第一千三百三十三章 血刀圣者', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1380, 'http://www.biquge5200.com/0_844/638090.html', NULL, 'NO1521203244709', '第一千三百三十四章 两名斗圣', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1381, 'http://www.biquge5200.com/0_844/638091.html', NULL, 'NO1521203244709', '第一千三百三十五章 现身', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1382, 'http://www.biquge5200.com/0_844/638092.html', NULL, 'NO1521203244709', '第一千三百三十六章 萧玄！', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1383, 'http://www.biquge5200.com/0_844/638093.html', NULL, 'NO1521203244709', '第一千三百三十七章 血脉传承', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1384, 'http://www.biquge5200.com/0_844/638094.html', NULL, 'NO1521203244709', '第一千三百三十八章 换血【第一更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1385, 'http://www.biquge5200.com/0_844/638095.html', NULL, 'NO1521203244709', '第一千三百三十九章 血脉融合【第二更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1386, 'http://www.biquge5200.com/0_844/638096.html', NULL, 'NO1521203244709', '第一千三百四十章 一年半【第三更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1387, 'http://www.biquge5200.com/0_844/638097.html', NULL, 'NO1521203244709', '第一千三百四十一章 八星斗尊巅峰！【第一更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1388, 'http://www.biquge5200.com/0_844/638098.html', NULL, 'NO1521203244709', '一千三百四十二章 激活族纹', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1389, 'http://www.biquge5200.com/0_844/638099.html', NULL, 'NO1521203244709', '第一千三百四十三章 最后的历练', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1390, 'http://www.biquge5200.com/0_844/638100.html', NULL, 'NO1521203244709', '第一千三百四十四章 斩杀', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1391, 'http://www.biquge5200.com/0_844/638101.html', NULL, 'NO1521203244709', '第一千三百四十五 离开天墓', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1392, 'http://www.biquge5200.com/0_844/638102.html', NULL, 'NO1521203244709', '斗破点击破亿，拜谢所有支持斗破的兄弟姐妹！', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1393, 'http://www.biquge5200.com/0_844/638103.html', NULL, 'NO1521203244709', '第一千三百四十六章 魂林', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1394, 'http://www.biquge5200.com/0_844/638104.html', NULL, 'NO1521203244709', '第一千三百四十七章 摊牌', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1395, 'http://www.biquge5200.com/0_844/638105.html', NULL, 'NO1521203244709', '第一千三百四十八章 离开古界', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1396, 'http://www.biquge5200.com/0_844/638115.html', NULL, 'NO1521203244709', '第一千三百四十九章 焕然一新的星陨阁', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1397, 'http://www.biquge5200.com/0_844/638116.html', NULL, 'NO1521203244709', '第一千三百五十章 西北大陆的动荡', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1398, 'http://www.biquge5200.com/0_844/638117.html', NULL, 'NO1521203244709', '第一千三百五十一章 收徒幽泉', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1399, 'http://www.biquge5200.com/0_844/638118.html', NULL, 'NO1521203244709', '第一千三百五十二章 邀集帮手', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1400, 'http://www.biquge5200.com/0_844/638119.html', NULL, 'NO1521203244709', '第一千三百五十三章 玄黄要塞', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1401, 'http://www.biquge5200.com/0_844/638120.html', NULL, 'NO1521203244709', '第一千三百五十四章 小萧潇', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1402, 'http://www.biquge5200.com/0_844/638121.html', NULL, 'NO1521203244709', '第一千三百五十五章 大战始', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1403, 'http://www.biquge5200.com/0_844/638122.html', NULL, 'NO1521203244709', '第一千三百五十六章 惨烈', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1404, 'http://www.biquge5200.com/0_844/638123.html', NULL, 'NO1521203244709', '第一千三百五十七章 一掌击杀！', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1405, 'http://www.biquge5200.com/0_844/638124.html', NULL, 'NO1521203244709', '第一千三百五十八章 今非昔比', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1406, 'http://www.biquge5200.com/0_844/638125.html', NULL, 'NO1521203244709', '第一千三百五十九章 四天尊，血河！', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1407, 'http://www.biquge5200.com/0_844/638126.html', NULL, 'NO1521203244709', '第一千三百六十章 噬血术', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1408, 'http://www.biquge5200.com/0_844/638127.html', NULL, 'NO1521203244709', '持续厮杀，郑重的拜求月票！', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1409, 'http://www.biquge5200.com/0_844/638128.html', NULL, 'NO1521203244709', '第一千三百六十一章 九转成圣', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1410, 'http://www.biquge5200.com/0_844/638129.html', NULL, 'NO1521203244709', '第一千三百六十二章 落 幕', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1411, 'http://www.biquge5200.com/0_844/638130.html', NULL, 'NO1521203244709', '不想输，那便唯有拼！', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1412, 'http://www.biquge5200.com/0_844/638131.html', NULL, 'NO1521203244709', '第一千三百六十三章 毒瘤', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1413, 'http://www.biquge5200.com/0_844/638132.html', NULL, 'NO1521203244709', '第一千三百六十四章 炼丹【第二更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1414, 'http://www.biquge5200.com/0_844/638133.html', NULL, 'NO1521203244709', '第一千三百六十五章 严惩', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1415, 'http://www.biquge5200.com/0_844/638134.html', NULL, 'NO1521203244709', '第一千三百六十六章解决', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1416, 'http://www.biquge5200.com/0_844/638135.html', NULL, 'NO1521203244709', '第一千三百六十七章 动身之前', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1417, 'http://www.biquge5200.com/0_844/638136.html', NULL, 'NO1521203244709', '第一千三百六十八章  回星陨阁', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1418, 'http://www.biquge5200.com/0_844/638137.html', NULL, 'NO1521203244709', '第一千三百六十九章 空间交易会', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1419, 'http://www.biquge5200.com/0_844/638138.html', NULL, 'NO1521203244709', '第一千三百七十章 八彩原石', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1420, 'http://www.biquge5200.com/0_844/638139.html', NULL, 'NO1521203244709', '第一千三百七十一章 古殿【第一更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1421, 'http://www.biquge5200.com/0_844/638140.html', NULL, 'NO1521203244709', '第一千三百七十二章 最后一张残图【第二更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1422, 'http://www.biquge5200.com/0_844/638141.html', NULL, 'NO1521203244709', '第一千三百七十三章 情报【第三更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1423, 'http://www.biquge5200.com/0_844/638142.html', NULL, 'NO1521203244709', '第一千三百七十四章 莽荒古域【第一更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1424, 'http://www.biquge5200.com/0_844/638143.html', NULL, 'NO1521203244709', '第一千三百七十五章 蝎魔三鬼', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1425, 'http://www.biquge5200.com/0_844/638144.html', NULL, 'NO1521203244709', '第一千三百七十六章 四色半的佛怒火莲！', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1426, 'http://www.biquge5200.com/0_844/638145.html', NULL, 'NO1521203244709', '第一千三百七十七章 古图到手【第一更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1427, 'http://www.biquge5200.com/0_844/638146.html', NULL, 'NO1521203244709', '第一千三百七十八章 古图之谜【第二更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1428, 'http://www.biquge5200.com/0_844/638147.html', NULL, 'NO1521203244709', '第一千三百七十九章 净莲妖圣【第三更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1429, 'http://www.biquge5200.com/0_844/638148.html', NULL, 'NO1521203244709', '三章完成，求月票！！！', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1430, 'http://www.biquge5200.com/0_844/638149.html', NULL, 'NO1521203244709', '第一千三百八十章 修炼金刚琉璃身', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1431, 'http://www.biquge5200.com/0_844/638150.html', NULL, 'NO1521203244709', '第一千三百八十一章 莽荒镇【第二更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1432, 'http://www.biquge5200.com/0_844/638151.html', NULL, 'NO1521203244709', '第一千三百八十二章 进入莽荒古域【第三更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1433, 'http://www.biquge5200.com/0_844/638152.html', NULL, 'NO1521203244709', '第一千三百八十三章 深入【第四更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1434, 'http://www.biquge5200.com/0_844/638153.html', NULL, 'NO1521203244709', '第一千三百八十四章 再遇云韵【第五更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1435, 'http://www.biquge5200.com/0_844/638154.html', NULL, 'NO1521203244709', '第一千三百八十五章 斩杀天冥宗【第六更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1436, 'http://www.biquge5200.com/0_844/638155.html', NULL, 'NO1521203244709', '第一千三百八十六章 远古天魔蟒【第七更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1437, 'http://www.biquge5200.com/0_844/638156.html', NULL, 'NO1521203244709', '第一千三百八十七章 围剿天魔蟒【第八更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1438, 'http://www.biquge5200.com/0_844/638157.html', NULL, 'NO1521203244709', '第一千三百八十八章 天魔血池【第九更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1439, 'http://www.biquge5200.com/0_844/638158.html', NULL, 'NO1521203244709', '第一千三百八十九章 九星斗尊！【第十更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1440, 'http://www.biquge5200.com/0_844/638159.html', NULL, 'NO1521203244709', '第一千三百九十章 古域台【第十一更！！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1441, 'http://www.biquge5200.com/0_844/638160.html', NULL, 'NO1521203244709', '第一千三百九十一章 下马威【第一更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1442, 'http://www.biquge5200.com/0_844/638161.html', NULL, 'NO1521203244709', '十一更，承喏完成，谢谢大家。', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1443, 'http://www.biquge5200.com/0_844/638162.html', NULL, 'NO1521203244709', '第一千三百九十二章 两女相见【第二更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1444, 'http://www.biquge5200.com/0_844/638163.html', NULL, 'NO1521203244709', '第一千三百九十三章 魂玉，兽潮【第三更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1445, 'http://www.biquge5200.com/0_844/638164.html', NULL, 'NO1521203244709', '第一千三百九十四章冲击兽潮', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1446, 'http://www.biquge5200.com/0_844/638165.html', NULL, 'NO1521203244709', '第一千三百九十五章 突破！', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1447, 'http://www.biquge5200.com/0_844/638166.html', NULL, 'NO1521203244709', '第一千三百九十六章 五位半圣！', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1448, 'http://www.biquge5200.com/0_844/638167.html', NULL, 'NO1521203244709', '第一千三百九十七章 轰杀半圣傀儡', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1449, 'http://www.biquge5200.com/0_844/638168.html', NULL, 'NO1521203244709', '第一千三百九十八章 进入古树【第三更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1450, 'http://www.biquge5200.com/0_844/638169.html', NULL, 'NO1521203244709', '最后两个小时！', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1451, 'http://www.biquge5200.com/0_844/638170.html', NULL, 'NO1521203244709', '第一千三百九十九章 幻境【第一更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1452, 'http://www.biquge5200.com/0_844/638171.html', NULL, 'NO1521203244709', '斗破苍穹 正文 斗破苍穹 第一千四百章 斗帝的负面情绪【第二更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1453, 'http://www.biquge5200.com/0_844/638172.html', NULL, 'NO1521203244709', '第一千四百零一章 菩提三宝', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1454, 'http://www.biquge5200.com/0_844/638173.html', NULL, 'NO1521203244709', '第一千四百零二章 苏醒', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1455, 'http://www.biquge5200.com/0_844/638174.html', NULL, 'NO1521203244709', '第一千四百零三章百世轮回，九转巅峰！', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1456, 'http://www.biquge5200.com/0_844/638175.html', NULL, 'NO1521203244709', '第一千四百零四章 绝对压制【第一更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1457, 'http://www.biquge5200.com/0_844/638176.html', NULL, 'NO1521203244709', '第一千四百零五章 空间破裂', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1458, 'http://www.biquge5200.com/0_844/638177.html', NULL, 'NO1521203244709', '第一千四百零六章二天尊，骨幽圣者【第三更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1459, 'http://www.biquge5200.com/0_844/638178.html', NULL, 'NO1521203244709', '第一千四百零七章 金帝焚天炎', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1460, 'http://www.biquge5200.com/0_844/638179.html', NULL, 'NO1521203244709', '第一千四百零八章 对战半圣', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1461, 'http://www.biquge5200.com/0_844/638180.html', NULL, 'NO1521203244709', '第一千四百零九章 逼退骨幽', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1462, 'http://www.biquge5200.com/0_844/638181.html', NULL, 'NO1521203244709', '第一千四百一十章 闭关', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1463, 'http://www.biquge5200.com/0_844/638182.html', NULL, 'NO1521203244709', '感谢一下最近出现的盟主。', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1464, 'http://www.biquge5200.com/0_844/638183.html', NULL, 'NO1521203244709', '第一千四百一十一章 灵族之变', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1465, 'http://www.biquge5200.com/0_844/638184.html', NULL, 'NO1521203244709', '第一千四百一十二章 战帖', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1466, 'http://www.biquge5200.com/0_844/638185.html', NULL, 'NO1521203244709', '第一千四百一十三章 大战来临', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1467, 'http://www.biquge5200.com/0_844/638186.html', NULL, 'NO1521203244709', '第一千四百一十四章 星界大战【第二更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1468, 'http://www.biquge5200.com/0_844/638187.html', NULL, 'NO1521203244709', '第一千四百一十五章 破关而出，斗圣！【第三更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1469, 'http://www.biquge5200.com/0_844/638188.html', NULL, 'NO1521203244709', '第一千四百一十六章 佛怒轮回', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1470, 'http://www.biquge5200.com/0_844/638189.html', NULL, 'NO1521203244709', '第一千四百一十七章 大杀四方', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1471, 'http://www.biquge5200.com/0_844/638190.html', NULL, 'NO1521203244709', '周初，求推荐票', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1472, 'http://www.biquge5200.com/0_844/638191.html', NULL, 'NO1521203244709', '文中称呼出了点小错，已经改过来了。', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1473, 'http://www.biquge5200.com/0_844/638192.html', NULL, 'NO1521203244709', '第一千四百一十八章 魂族斗圣', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1474, 'http://www.biquge5200.com/0_844/638193.html', NULL, 'NO1521203244709', '第一千四百一十九章 各宗隐秘', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1475, 'http://www.biquge5200.com/0_844/638194.html', NULL, 'NO1521203244709', '第一千四百二十章 杀鸡儆猴', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1476, 'http://www.biquge5200.com/0_844/638195.html', NULL, 'NO1521203244709', '第一千四百二十一章 寻求盟友', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1477, 'http://www.biquge5200.com/0_844/638196.html', NULL, 'NO1521203244709', '第一千四百二十二章 再至丹塔', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1478, 'http://www.biquge5200.com/0_844/638197.html', NULL, 'NO1521203244709', '第一千四百二十三章 小丹塔', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1479, 'http://www.biquge5200.com/0_844/638198.html', NULL, 'NO1521203244709', '4.14，斗破两周年了。', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1480, 'http://www.biquge5200.com/0_844/638199.html', NULL, 'NO1521203244709', '第一千四百二十四章 竞选长老', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1481, 'http://www.biquge5200.com/0_844/638200.html', NULL, 'NO1521203244709', '第一千四百二十五章 小丹塔大长老【第二更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1482, 'http://www.biquge5200.com/0_844/638201.html', NULL, 'NO1521203244709', '第一千四百二十六章 那可未必【第三更！】', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1483, 'http://www.biquge5200.com/0_844/638202.html', NULL, 'NO1521203244709', '三章已更，两周年，求票！', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1484, 'http://www.biquge5200.com/0_844/638203.html', NULL, 'NO1521203244709', '第一千四百二十七章 黑魔雷', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1485, 'http://www.biquge5200.com/0_844/638204.html', NULL, 'NO1521203244709', '第一千四百二十八章 九品宝丹', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1486, 'http://www.biquge5200.com/0_844/638205.html', NULL, 'NO1521203244709', '第一千四百二十九章 联盟', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1487, 'http://www.biquge5200.com/0_844/638206.html', NULL, 'NO1521203244709', '第一千四百三十章 天府联盟', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1488, 'http://www.biquge5200.com/0_844/638207.html', NULL, 'NO1521203244709', '第一千四百三十一章 魂殿副殿主', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1489, 'http://www.biquge5200.com/0_844/638208.html', NULL, 'NO1521203244709', '第一千四百三十二章 九幽黄泉', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1490, 'http://www.biquge5200.com/0_844/638209.html', NULL, 'NO1521203244709', '第一千四百三十三章 九阴黄泉丹', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1491, 'http://www.biquge5200.com/0_844/638210.html', NULL, 'NO1521203244709', '第一千四三十四章 古龙一族情势', NULL, b'0', '2018-03-16 20:27:39', '2018-03-16 20:27:39');
INSERT INTO `t_book_chapter` VALUES (1492, 'http://www.biquge5200.com/0_844/638211.html', NULL, 'NO1521203244709', '第一千四百三十五章 冥蛇地脉', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1493, 'http://www.biquge5200.com/0_844/638212.html', NULL, 'NO1521203244709', '第一千四百三十六章 被锁之人', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1494, 'http://www.biquge5200.com/0_844/638213.html', NULL, 'NO1521203244709', '第一千四百三十七章 妖暝', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1495, 'http://www.biquge5200.com/0_844/638214.html', NULL, 'NO1521203244709', '第一千四百三十八章 妖啸天', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1496, 'http://www.biquge5200.com/0_844/638215.html', NULL, 'NO1521203244709', '明日三更，求月票', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1497, 'http://www.biquge5200.com/0_844/638216.html', NULL, 'NO1521203244709', '第一千四百三十九章 暴打【第一更！】', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1498, 'http://www.biquge5200.com/0_844/638217.html', NULL, 'NO1521203244709', '第一千四百四十章 九幽冥杖【第二更！】', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1499, 'http://www.biquge5200.com/0_844/638218.html', NULL, 'NO1521203244709', '第一千四百四十一章 大势已去', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1500, 'http://www.biquge5200.com/0_844/638219.html', NULL, 'NO1521203244709', '第一千四百四十二章黄泉妖圣', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1501, 'http://www.biquge5200.com/0_844/638220.html', NULL, 'NO1521203244709', '第一千四百四十三章 黄泉天怒', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1502, 'http://www.biquge5200.com/0_844/638221.html', NULL, 'NO1521203244709', '第一千四百四十四章 灵魂争斗', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1503, 'http://www.biquge5200.com/0_844/638222.html', NULL, 'NO1521203244709', '第一千一百四十五章 妖圣精血', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1504, 'http://www.biquge5200.com/0_844/638223.html', NULL, 'NO1521203244709', '第一千四百四十六章 寻找帮手', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1505, 'http://www.biquge5200.com/0_844/638224.html', NULL, 'NO1521203244709', '第一千四百四十七章 吸收妖圣精血', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1506, 'http://www.biquge5200.com/0_844/638225.html', NULL, 'NO1521203244709', '第一千四百四十八章 天妖三凰', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1507, 'http://www.biquge5200.com/0_844/638226.html', NULL, 'NO1521203244709', '第一千四百四十九章 伏击【第二更！】', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1508, 'http://www.biquge5200.com/0_844/638227.html', NULL, 'NO1521203244709', '第一千四百五十章 独斗双圣【第三更！】', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1509, 'http://www.biquge5200.com/0_844/638228.html', NULL, 'NO1521203244709', '第一千四百五十一章 绝对压制', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1510, 'http://www.biquge5200.com/0_844/638229.html', NULL, 'NO1521203244709', '第一千四百五十二章 人质威胁', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1511, 'http://www.biquge5200.com/0_844/638230.html', NULL, 'NO1521203244709', '明天开始，三更到本月最后一天。', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1512, 'http://www.biquge5200.com/0_844/638231.html', NULL, 'NO1521203244709', '第一千四百五十三章 路遇 天蚕土豆', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1513, 'http://www.biquge5200.com/0_844/638232.html', NULL, 'NO1521203244709', '第一千四百五十四章 解决【第二更！】', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1514, 'http://www.biquge5200.com/0_844/638233.html', NULL, 'NO1521203244709', '第一千四百五十五章 再见紫研【第三更！】', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1515, 'http://www.biquge5200.com/0_844/638234.html', NULL, 'NO1521203244709', '第一千四百五十六章 大战爆发【第一更！】', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1516, 'http://www.biquge5200.com/0_844/638235.html', NULL, 'NO1521203244709', '第一千四百五十七章 三大龙王【第二更！】', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1517, 'http://www.biquge5200.com/0_844/638236.html', NULL, 'NO1521203244709', '第一千四百五十八章 拼命【第三更！】', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1518, 'http://www.biquge5200.com/0_844/638237.html', NULL, 'NO1521203244709', '第一千四百五十九章 屠龙剑【第一更！】', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1519, 'http://www.biquge5200.com/0_844/638238.html', NULL, 'NO1521203244709', '第一千四百六十章 大战落幕', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1520, 'http://www.biquge5200.com/0_844/638239.html', NULL, 'NO1521203244709', '第一千四百六十一章 二星斗圣！【第三更！】', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1521, 'http://www.biquge5200.com/0_844/638240.html', NULL, 'NO1521203244709', '第一千四百六十二章 魂殿动静 天蚕土豆', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1522, 'http://www.biquge5200.com/0_844/638241.html', NULL, 'NO1521203244709', '第一千四百六十三章 天罡殿【第二更！】', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1523, 'http://www.biquge5200.com/0_844/638242.html', NULL, 'NO1521203244709', '第一千四百六十四章 血洗人殿 【第三更！】', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1524, 'http://www.biquge5200.com/0_844/638243.html', NULL, 'NO1521203244709', '第一千四百六十五章 大天尊', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1525, 'http://www.biquge5200.com/0_844/638244.html', NULL, 'NO1521203244709', '第一千四百六十六章 击溃', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1526, 'http://www.biquge5200.com/0_844/638245.html', NULL, 'NO1521203244709', '第一千四百六十七章 血洗', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1527, 'http://www.biquge5200.com/0_844/638246.html', NULL, 'NO1521203244709', '第一千四百六十八章 灵魂光团', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1528, 'http://www.biquge5200.com/0_844/638247.html', NULL, 'NO1521203244709', '第一千四百六十九章 吸收灵魂本源', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1529, 'http://www.biquge5200.com/0_844/638248.html', NULL, 'NO1521203244709', '第一千四百七十章 天境大圆满', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1530, 'http://www.biquge5200.com/0_844/638249.html', NULL, 'NO1521203244709', '第一千四百七十一章 魂殿殿主！', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1531, 'http://www.biquge5200.com/0_844/638250.html', NULL, 'NO1521203244709', '第一千四百七十二章 妖火降世！', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1532, 'http://www.biquge5200.com/0_844/638251.html', NULL, 'NO1521203244709', '第一千四百七十三章 四方云动', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1533, 'http://www.biquge5200.com/0_844/638252.html', NULL, 'NO1521203244709', '第一千四百七十四章 八荒破灭焱', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1534, 'http://www.biquge5200.com/0_844/638253.html', NULL, 'NO1521203244709', '第一千四百七十五章 再见薰儿', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1535, 'http://www.biquge5200.com/0_844/638254.html', NULL, 'NO1521203244709', '第一千四百七十六章 药万归', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1536, 'http://www.biquge5200.com/0_844/638255.html', NULL, 'NO1521203244709', '第一千四百七十七章 不知好歹', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1537, 'http://www.biquge5200.com/0_844/638256.html', NULL, 'NO1521203244709', '第一千四百七十八章 空间破裂', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1538, 'http://www.biquge5200.com/0_844/638257.html', NULL, 'NO1521203244709', '第一千四百七十九章 妖火空间', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1539, 'http://www.biquge5200.com/0_844/638258.html', NULL, 'NO1521203244709', '第一干四百八十章 闯关', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1540, 'http://www.biquge5200.com/0_844/638259.html', NULL, 'NO1521203244709', '第一千四百八十一章 血色巨斧', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1541, 'http://www.biquge5200.com/0_844/638260.html', NULL, 'NO1521203244709', '第一千四百八十二章 虚幻与真实', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1542, 'http://www.biquge5200.com/0_844/638261.html', NULL, 'NO1521203244709', '第一千四百八十三章 突破幻境', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1543, 'http://www.biquge5200.com/0_844/638262.html', NULL, 'NO1521203244709', '第一千四百八十四章 合作 天蚕土豆', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1544, 'http://www.biquge5200.com/0_844/638263.html', NULL, 'NO1521203244709', '第一千四百八十五章 七圣斗妖火', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1545, 'http://www.biquge5200.com/0_844/638264.html', NULL, 'NO1521203244709', '第一千四百八十六章 反控', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1546, 'http://www.biquge5200.com/0_844/638265.html', NULL, 'NO1521203244709', '第一千四百八十七章 天罗封魔阵', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1547, 'http://www.biquge5200.com/0_844/638266.html', NULL, 'NO1521203244709', '第一千四百八十八章 抢妖火本源！', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1548, 'http://www.biquge5200.com/0_844/638267.html', NULL, 'NO1521203244709', '第一千四百八十九章 惊天对撞', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1549, 'http://www.biquge5200.com/0_844/638268.html', NULL, 'NO1521203244709', '第一千四百九十章 萧晨出手', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1550, 'http://www.biquge5200.com/0_844/638269.html', NULL, 'NO1521203244709', '第一千四百九十一章 魂魔老人', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1551, 'http://www.biquge5200.com/0_844/638270.html', NULL, 'NO1521203244709', '第一千四百九十二章 青牛牧童', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1552, 'http://www.biquge5200.com/0_844/638271.html', NULL, 'NO1521203244709', '第一千四百九十三章 炼天古阵', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1553, 'http://www.biquge5200.com/0_844/638272.html', NULL, 'NO1521203244709', '第一千四百九十四章 净莲妖圣？', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1554, 'http://www.biquge5200.com/0_844/638273.html', NULL, 'NO1521203244709', '第一千四百九十五章  妖圣VS妖火', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1555, 'http://www.biquge5200.com/0_844/638274.html', NULL, 'NO1521203244709', '第一千四百九十六章 剥离 天蚕土豆', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1556, 'http://www.biquge5200.com/0_844/638275.html', NULL, 'NO1521203244709', '第一千四百九十七章 最终获利', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1557, 'http://www.biquge5200.com/0_844/638276.html', NULL, 'NO1521203244709', '第一千四百九十八章 炼化妖火！', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1558, 'http://www.biquge5200.com/0_844/638277.html', NULL, 'NO1521203244709', '第一千四百九十九章 中州事变', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1559, 'http://www.biquge5200.com/0_844/638278.html', NULL, 'NO1521203244709', '第一千五百章 破茧而出', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1560, 'http://www.biquge5200.com/0_844/638279.html', NULL, 'NO1521203244709', '第一千五百零一章 火婴', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1561, 'http://www.biquge5200.com/0_844/638280.html', NULL, 'NO1521203244709', '第一千五百零二章 妖火平原', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1562, 'http://www.biquge5200.com/0_844/638281.html', NULL, 'NO1521203244709', '第一千五百零三章 翻手灭殿', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1563, 'http://www.biquge5200.com/0_844/638282.html', NULL, 'NO1521203244709', '第一千五百零四章 风雨欲来', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1564, 'http://www.biquge5200.com/0_844/638283.html', NULL, 'NO1521203244709', '第一千五百零五章 回家', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1565, 'http://www.biquge5200.com/0_844/638284.html', NULL, 'NO1521203244709', '第一千五百零六章 战帖', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1566, 'http://www.biquge5200.com/0_844/638285.html', NULL, 'NO1521203244709', '第一千五百零七章 陨落之巅', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1567, 'http://www.biquge5200.com/0_844/638286.html', NULL, 'NO1521203244709', '第一千五百零八章 魂千陌', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1568, 'http://www.biquge5200.com/0_844/638287.html', NULL, 'NO1521203244709', '第一千五百零九章 交锋', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1569, 'http://www.biquge5200.com/0_844/638288.html', NULL, 'NO1521203244709', '第一千五百一十章 平一局', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1570, 'http://www.biquge5200.com/0_844/638289.html', NULL, 'NO1521203244709', '第一千五百一十一章 最后一局', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1571, 'http://www.biquge5200.com/0_844/638290.html', NULL, 'NO1521203244709', '第一千五百一十二章 萧炎VS魂殿殿主', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1572, 'http://www.biquge5200.com/0_844/638291.html', NULL, 'NO1521203244709', '第一千五百一十三章 虚无吞炎', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1573, 'http://www.biquge5200.com/0_844/638292.html', NULL, 'NO1521203244709', '第一千五百一十四章 小伊显威', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1574, 'http://www.biquge5200.com/0_844/638293.html', NULL, 'NO1521203244709', '第一千五百一十五章 分出胜负', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1575, 'http://www.biquge5200.com/0_844/638294.html', NULL, 'NO1521203244709', '第一千五百一十六章  下杀手', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1576, 'http://www.biquge5200.com/0_844/638295.html', NULL, 'NO1521203244709', '第一千五百一十七章 黑色珠子', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1577, 'http://www.biquge5200.com/0_844/638296.html', NULL, 'NO1521203244709', '第一千五百一十八章 传信', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1578, 'http://www.biquge5200.com/0_844/638297.html', NULL, 'NO1521203244709', '第一千五百一十九章 凰天', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1579, 'http://www.biquge5200.com/0_844/638298.html', NULL, 'NO1521203244709', '第一千五百二十章 两族对恃', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1580, 'http://www.biquge5200.com/0_844/638299.html', NULL, 'NO1521203244709', '第一千五百二十一章 交手', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1581, 'http://www.biquge5200.com/0_844/638300.html', NULL, 'NO1521203244709', '第一千五百二十二章 九色光柱', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1582, 'http://www.biquge5200.com/0_844/638301.html', NULL, 'NO1521203244709', '第一千五百二十三章 彩鳞出关', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1583, 'http://www.biquge5200.com/0_844/638302.html', NULL, 'NO1521203244709', '第一千五百二十四章 变故', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1584, 'http://www.biquge5200.com/0_844/638303.html', NULL, 'NO1521203244709', '第一千五百二十五章 化龙魔阵', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1585, 'http://www.biquge5200.com/0_844/638304.html', NULL, 'NO1521203244709', '第一千五百二十六章 毁灭火体', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1586, 'http://www.biquge5200.com/0_844/638305.html', NULL, 'NO1521203244709', '第一千五百二十七章 疯狂的北龙王', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1587, 'http://www.biquge5200.com/0_844/638306.html', NULL, 'NO1521203244709', '第一千五百二十八章 击杀', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1588, 'http://www.biquge5200.com/0_844/638307.html', NULL, 'NO1521203244709', '第一千五百二十九章 炼制傀儡', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1589, 'http://www.biquge5200.com/0_844/638308.html', NULL, 'NO1521203244709', '第一千五百三十章 北王', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1590, 'http://www.biquge5200.com/0_844/638309.html', NULL, 'NO1521203244709', '第一千五百三十一章 吞噬黑魔雷', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1591, 'http://www.biquge5200.com/0_844/638310.html', NULL, 'NO1521203244709', '第一千五百三十二章 九玄金雷', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1592, 'http://www.biquge5200.com/0_844/638311.html', NULL, 'NO1521203244709', '第一千五百三十三章 聚灵', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1593, 'http://www.biquge5200.com/0_844/638312.html', NULL, 'NO1521203244709', '第一千五百三十四章 九玄金雷的力量', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1594, 'http://www.biquge5200.com/0_844/638313.html', NULL, 'NO1521203244709', '第一千五百三十五章 变化', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1595, 'http://www.biquge5200.com/0_844/638314.html', NULL, 'NO1521203244709', '第一千五百三十六章 平静', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1596, 'http://www.biquge5200.com/0_844/638315.html', NULL, 'NO1521203244709', '第一千五百三十七章 药族药典', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1597, 'http://www.biquge5200.com/0_844/638316.html', NULL, 'NO1521203244709', '第一千五百三十八章 出手', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1598, 'http://www.biquge5200.com/0_844/638317.html', NULL, 'NO1521203244709', '第一千五百三十九章 凶悍', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1599, 'http://www.biquge5200.com/0_844/638318.html', NULL, 'NO1521203244709', '第一千五百四十章 万火长老', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1600, 'http://www.biquge5200.com/0_844/638319.html', NULL, 'NO1521203244709', '第一千五百四十一章 魂虚子', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1601, 'http://www.biquge5200.com/0_844/638320.html', NULL, 'NO1521203244709', '第一千五百四十二章 药典开始', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1602, 'http://www.biquge5200.com/0_844/638321.html', NULL, 'NO1521203244709', '第一千五百四十三章 讨教', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1603, 'http://www.biquge5200.com/0_844/638322.html', NULL, 'NO1521203244709', '第一千五百四十四章 生灵之焱', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1604, 'http://www.biquge5200.com/0_844/638323.html', NULL, 'NO1521203244709', '第一千五百四十五章 炼丹较量', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1605, 'http://www.biquge5200.com/0_844/638324.html', NULL, 'NO1521203244709', '第一千五百四十六章 抢夺能量', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1606, 'http://www.biquge5200.com/0_844/638325.html', NULL, 'NO1521203244709', '第一千五百四十七章丹雨', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1607, 'http://www.biquge5200.com/0_844/638326.html', NULL, 'NO1521203244709', '第一千五百四十八章 借玉', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1608, 'http://www.biquge5200.com/0_844/638327.html', NULL, 'NO1521203244709', '第一千五百四十九章 惊变', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1609, 'http://www.biquge5200.com/0_844/638328.html', NULL, 'NO1521203244709', '第一千五百五十章 本体', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1610, 'http://www.biquge5200.com/0_844/638329.html', NULL, 'NO1521203244709', '第一千五百五十一章 药帝残魂', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1611, 'http://www.biquge5200.com/0_844/638330.html', NULL, 'NO1521203244709', '第一千五百五十二章 吞灵', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1612, 'http://www.biquge5200.com/0_844/638331.html', NULL, 'NO1521203244709', '第一千五百五十三章  灭族之战', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1613, 'http://www.biquge5200.com/0_844/638332.html', NULL, 'NO1521203244709', '第一千五百五十四章 借火', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1614, 'http://www.biquge5200.com/0_844/638333.html', NULL, 'NO1521203244709', '第一千五百五十五章 八色火莲', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1615, 'http://www.biquge5200.com/0_844/638334.html', NULL, 'NO1521203244709', '第一千五百五十六章 逃生', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1616, 'http://www.biquge5200.com/0_844/638335.html', NULL, 'NO1521203244709', '第一千五百五十七章 吞服', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1617, 'http://www.biquge5200.com/0_844/638336.html', NULL, 'NO1521203244709', '第一千五百五十八章 逃', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1618, 'http://www.biquge5200.com/0_844/638337.html', NULL, 'NO1521203244709', '第一千五百五十九章 击', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1619, 'http://www.biquge5200.com/0_844/638338.html', NULL, 'NO1521203244709', '第一千五百六十章 再进古界', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1620, 'http://www.biquge5200.com/0_844/638339.html', NULL, 'NO1521203244709', '第一千五百六十一章 魂族之秘', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1621, 'http://www.biquge5200.com/0_844/638340.html', NULL, 'NO1521203244709', '第一千五百六十二章 六星中期', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1622, 'http://www.biquge5200.com/0_844/638341.html', NULL, 'NO1521203244709', '第一千五百六十三章 雷动', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1623, 'http://www.biquge5200.com/0_844/638342.html', NULL, 'NO1521203244709', '第一千五百六十四章 出手', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1624, 'http://www.biquge5200.com/0_844/638343.html', NULL, 'NO1521203244709', '第一千五百六十五章 赐教', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1625, 'http://www.biquge5200.com/0_844/638344.html', NULL, 'NO1521203244709', '第一千五百六十六章 窥视', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1626, 'http://www.biquge5200.com/0_844/638345.html', NULL, 'NO1521203244709', '第一千五百六十七章 古怪', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1627, 'http://www.biquge5200.com/0_844/638346.html', NULL, 'NO1521203244709', '第一千五百六十八章 搜寻', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1628, 'http://www.biquge5200.com/0_844/638347.html', NULL, 'NO1521203244709', '第一千五百六十九章夺玉', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1629, 'http://www.biquge5200.com/0_844/638348.html', NULL, 'NO1521203244709', '第一千五百七十章 失玉', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1630, 'http://www.biquge5200.com/0_844/638349.html', NULL, 'NO1521203244709', '第一十五百七十一章商议', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1631, 'http://www.biquge5200.com/0_844/638350.html', NULL, 'NO1521203244709', '第一千五百七十二章 再进天墓', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1632, 'http://www.biquge5200.com/0_844/638351.html', NULL, 'NO1521203244709', '第一千五百七十三章 本源帝气', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1633, 'http://www.biquge5200.com/0_844/638352.html', NULL, 'NO1521203244709', '第一千五百七十四章 天墓之魂', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1634, 'http://www.biquge5200.com/0_844/638353.html', NULL, 'NO1521203244709', '第一千五百七十五章 抽取灵魂本源', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1635, 'http://www.biquge5200.com/0_844/638354.html', NULL, 'NO1521203244709', '第一千五百七十六章 帝境灵魂', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1636, 'http://www.biquge5200.com/0_844/638355.html', NULL, 'NO1521203244709', '第一千五百七十七章 出天墓', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1637, 'http://www.biquge5200.com/0_844/638356.html', NULL, 'NO1521203244709', '第一千五百七十八章 大战前夕', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1638, 'http://www.biquge5200.com/0_844/638357.html', NULL, 'NO1521203244709', '第一千五百七十九章 大军出动', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1639, 'http://www.biquge5200.com/0_844/638358.html', NULL, 'NO1521203244709', '第一千五百八十章 葬天山脉', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1640, 'http://www.biquge5200.com/0_844/638359.html', NULL, 'NO1521203244709', '第一千五百八十一章 父子相见（明天请一天假）', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1641, 'http://www.biquge5200.com/0_844/638360.html', NULL, 'NO1521203244709', '第一千五百八十二章 大战', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1642, 'http://www.biquge5200.com/0_844/638361.html', NULL, 'NO1521203244709', '第一千五百八十三章 魂元天', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1643, 'http://www.biquge5200.com/0_844/638362.html', NULL, 'NO1521203244709', '第一千五百八十四章 死寂之门', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1644, 'http://www.biquge5200.com/0_844/638363.html', NULL, 'NO1521203244709', '第一千五百八十五章 据为己有', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1645, 'http://www.biquge5200.com/0_844/638364.html', NULL, 'NO1521203244709', '第一千五百八十六章 破界', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1646, 'http://www.biquge5200.com/0_844/638365.html', NULL, 'NO1521203244709', '第一千五百八十七章 退走', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1647, 'http://www.biquge5200.com/0_844/638366.html', NULL, 'NO1521203244709', '第一千五百八十八章 定计', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1648, 'http://www.biquge5200.com/0_844/638367.html', NULL, 'NO1521203244709', '第一千五百八十九章宁静', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1649, 'http://www.biquge5200.com/0_844/638368.html', NULL, 'NO1521203244709', '第一千五百九十章 雷劫丹', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1650, 'http://www.biquge5200.com/0_844/638369.html', NULL, 'NO1521203244709', '第一千五百九十一章 七星斗圣', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1651, 'http://www.biquge5200.com/0_844/638370.html', NULL, 'NO1521203244709', '新的一周，求一声推荐票！', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1652, 'http://www.biquge5200.com/0_844/638371.html', NULL, 'NO1521203244709', '第一十五百九十二章 探测古帝洞府', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1653, 'http://www.biquge5200.com/0_844/638372.html', NULL, 'NO1521203244709', '第一千五百九十三章古帝洞府所在（此章免费！）', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1654, 'http://www.biquge5200.com/0_844/638373.html', NULL, 'NO1521203244709', '第一十五百九十四章 黑角域之难', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1655, 'http://www.biquge5200.com/0_844/638374.html', NULL, 'NO1521203244709', '第一十五百九十五章 赶至', NULL, b'0', '2018-03-16 20:27:40', '2018-03-16 20:27:40');
INSERT INTO `t_book_chapter` VALUES (1656, 'http://www.biquge5200.com/0_844/638375.html', 'file:///D:/html/NO1521203244709/1656.html', 'NO1521203244709', '第一十五百九十六章 故人再见', '第一十五百九十六章\n<p> 天地寂静，一道道目光，泛着各种情绪，呆呆的望着那站立在雕像顶部的那一道黑色身影，轻风吹动，黑衫飘荡，一头黑色头扳散而开，仅仅只是这么一道平淡无奇的背影，但在那种人的注视中，那道身影，却是仿若山岳般的浩瀚沉教……\n<p> “萧炎……”\n<p> 苏千等人的视线，也是极端错愕的停留在那道黑衫身影上，片刻后，原本绝望的心中，顿时涌现激动，今日，有救了？\n<p> “真的是他……\n<p> 萧玉玉手掩着红唇，虽然已是有着十数年不见，然而那熟悉的背影，却依然是不陌生，只不过，比起当年，似乎更加的沉稳。哪里的小说更新最快？请认准\n<p> “学姐……那就是传说中的萧炎学长？他好强啊，刚才那人连千百二老都挡不住，竟然被他一手便是杀了……”\n<p> 人山人海中，在寂静了片刻后，突然爆出一道道窃窃私语，所有的目光，都是涌动着亮光的望着那一道黑衫身影，在很多迦南学员的心中，最让得人好奇的，便莫过于这位学长那种种的传闻，毕竟，在迦南学院建立以来，可还没什么人，能够达到为其修建雕像的地步……\n<p> “撤退！”\n<p> 天空上，魂幽满脸惊骇的望着那突然出现的身影，毫不犹豫的厉声喝道，虽说他并没有与萧炎直接交锋过，但连魂灭生这等强者都是陨落在了后者手中，这让他如何能有相战之心？他可不认为他的实力，能够强过魂灭生。\n<p> 听得魂幽的大喝，天空那数十位魂殿强者，也是当机立断，身形暴退。\n<p> 望着天空上那一幕，不少人再度微微有些滞然，旋即不少学员有些心血澎湃，目光狂热的望着那一道黑衫背影，这，才是真正的强者！\n<p> 不需要任何的出手，仅仅只是现身，便是能够震慑群雄！\n<p> 这等风采，足以让得这些尚还稚嫩的少年少女们，激动得浑身颤抖。\n<p> “既然来了，何必急匆匆的走？魂殿都被我毁了，你们这些残余，留着又有什么意思？”望着天空上分散而逃的魂殿强者，雕像头顶的黑衫青年，却是微微一笑，旋即其脚步轻抬，然后徐徐落下。\n<p> “砰砰砰！”\n<p> 伴随着萧炎脚步的落下……股恐怖的无形波动，顿时闪电般的蔓延而出，直接将那些逃窜的家伙追上，紧接着，天空上，那一道道黑影突然凭空爆成一团团血雾……\n<p> 不论是斗宗或者是斗尊，都是毫无征兆的爆成血雾，甚至，连他们的灵魂，都是在那一霎被生生震爆。\n<p> 一道道目光，愣愣的望着天空上扩散而开的血雾，这一幕，诡异而华丽……\n<p> 在很多学员的眼中，这些魂殿的强者，仿佛就是自己突然爆炸一般，而这之中，那雕像上的人，连身形都是未曾有所移动。\n<p> “这实力……”\n<p> 苏千与不远处的千百二老对视了一眼，眼中有着浓浓的骇然，杀斗尊就跟杀鸡一样，这实力，得多么的恐怖？\n<p> “萧炎，你保不了他们的！等我魂族大军开到，你们必死无疑！”\n<p> 唯一逃脱的，便是那魂幽，不过他也是在喷出了一口鲜血后，方才远远遁开，望着那一霎那间死得千干净净的手下，他眼睛一红，怨毒的喝道。\n<p> “恬噪！”\n<p> 萧炎眉头微皱，手掌猛的隔空一握，那魂幽头顶上空，顿时火焰涌动，一只粉红色的火焰巨掌从天而降，一巴掌便是轰在了他身体上，可怕的力道，将其打得如同断翅的鸟儿一般，带起一缕黑烟，对着遥远处掉落而去。\n<p> 随着魂幽化为黑烟落向远处……那里的方向突然有着光彩闪烁，旋即数道身影迅掠来。\n<p> “还有投军？”\n<p> 见到那些光彩，苏千等人顿时一惊。\n<p> 数道光彩在苏千等人警惕的目光中迅出现在学院上空，然后现出身来，而在这几道身影现身时，顿时有着恐怖的气息悄然蔓延而开。\n<p> “斗圣？”\n<p> 感受到这股令人毛骨悚然的恐怖气息，苏千与千百二老头皮顿时一炸，这来的人，居然全部都是传说中斗圣强者？\n<p> “嘭！”\n<p> 在他们暗自惊骇间……道黑影被从天空丢下，最后重重的砸落在下方的一道擂台上，众人目光一看，赫然便是那魂幽，只不过现在的他，貌似已然没有了生机。\n<p> 怔怔的望着那失去了生机的魂幽，不少人都是暗暗咽了一口唾沫，从此人能够指示先前那位斗圣强看来看，他的实力，显然要比后者更强，然而……即便如此，他依然是死得无比的干脆……\n<p> “哈哈，苏千，看来你很适合管理学院啊……”\n<p> 在苏千等人忍不住的抹冷汗时，突然有着一道笑声从天响起，旋即一道苍老身影徐徐落下，出现在了所有人的目光中。\n<p> “院长？”\n<p> 望着那笑眯眯踏空而来的老者，苏千等人顿时一愣，旋即失声惊叫道。\n<p> “嘿嘿。”那来人，自然便是迦南学院的院长，邙天尺，他笑眯眯的望着苏千等人，然后目光在学院中扫了扫，满意的点了点头，这里的规模，比起他当年离开时，不知道好上了多少。\n<p> “先下去说话吧。”\n<p> 印天尺倒是没将自己当失踪数十年的外人，挥了挥手，然后抬头望着天空，笑道：“萧炎小哥，这里可都是老相识了，难道还要躲藏不成？”\n<p> 闻言，天空上的萧炎倒是无奈的摇了摇头，带着薰儿以及那几位联军中的斗圣强者在那众目睽睽下落下身来，冲着苏千一抱拳，笑道：“大长老，多年不见，别来无恙啊。”\n<p> “你这小子，这么多年了无音信还以为你出了什么意外呢。”望着那张比起十数年前成熟了许多的脸庞，苏千也是忍不住的一笑，道。\n<p> “大长老。”\n<p> 在萧炎身后，薰儿也是抿嘴微微一笑，淡雅从容的风情，倒是看得周围那些学员有些眼直，如此级别的女孩，放眼整个学院，恐怕都是寻不出来。\n<p> “熏儿……”\n<p> 见到薰儿，苏千也是含笑点头不过还未说话，一道倩影便是从其身后跃出，然后就跟薰儿撞在了一起，玉手环抱间，将那纤细柳腰直接搂了起来。\n<p> “你还是这幅脾性……”\n<p> 薰儿先是一惊，待得辨清来人后，方才无奈一笑，道。\n<p> “嘿嘿，薰儿你真是越来越迷人了，不过看来你似乎还是没逃脱那个家伙的魔掌啊。”琥嘉嘻嘻笑道旋即眼睛瞟了一眼一旁的萧炎。\n<p> 一旁，吴昊也是将重剑收好，笑着望着这一幕，然后他的目光与萧炎对视了一眼，两人都是相视一笑，当年的友谊，即便是如今，依然未能忘却。\n<p> “开来你很喜欢当导师啊？”\n<p> 望着这些熟悉的面庞，萧炎心头也是忍不住的有些波动笑了笑，旋即目光转向苏千身后的那一道修长倩影，不由吹了一声口哨，那般举动，倒是一向以沉稳自居的他很罕见作出的事。\n<p> “你倒是舍得回来。”\n<p> 听得萧炎的口哨萧玉脸颊也是微红，轻剐了他一眼，现在前者的模样，倒是跟小时候在乌坦城时差不多，那时候的他，也是故意做出这般模样，将自己气得暴跳如雷。\n<p> 不过，那时候的她对于萧炎的轻挑举动会感到生气，现在，却是一点都不抗拒这种嬉闹，反而略有些期盼，然而，她心中也是明白现在的萧炎，已不再是当年那个敢偷窥她泡澡的调皮孩子……\n<p> “属下见过门主！”\n<p> 一劳，那萧门的中年男子，突然面色激动的抱拳恭声道。\n<p> “你是萧门的人吧？”闻言，萧炎也是一笑，旋即轻笑道：“做得不错。”\n<p> “是属下失职，未能保护好迦南学院。”那中年男子有些手足无措，萧炎在萧门，那就是传奇般的存在，他从来没有想到过，他竟然能够真的看见其本人。\n<p> “不关你们的事，这些敌人，萧门尚还应付不了。”萧炎摆了摆手，刚欲说话，数道身影从远处天望掠来，几个闪烁，便走出现在了身旁，正是彩鳞等人。\n<p> “其他地方的魂族余孽，也尽数驱除了。”彩鳞落下身来，冷艳的脸颊上浮现一抹笑容，道。\n<p> 苏千等人有些心惊肉跳的望着萧炎这十来人，他们现，这些人的实力，居然全部都是达到了斗圣层次，这等阵容，真的是有点把人吓傻的冲动，因此，在好半晌后，他们方才苦笑道：“这究竟是怎么回事？”\n<p> “有些麻烦事。\n<p> 萧炎与印天尺对视了一眼，面色凝重了葬多，道。\n<p> “这段时间，尽快的将学院的学员疏散，这黑角域，要生大事了……”印天尺沉声道，说实话，对于陀舍古帝洞府可能在地底岩浆世界的猜测，连他都很是有些难以接受，毕竟当初在封印陨落心炎时，他也是深入过那岩浆世界，只不过未太过深入而已，谁能想到，这传说之中的古帝洞府，竟然会与他擦身而过？\n<p> 见到印天尺与萧炎凝重的面色，苏千面色也是有些变化，旋即迟疑的道：“这么多的学生，一时半会的往哪里疏散？我们可没有那么多人手保护他们安全离开。”\n<p> 闻言，印天尺也是眉头紧皱。\n<p> “学员的事，倒不算麻烦，到时候将他们尽数收入天墓躲一阵便好。”萧炎摆了摆手，笑道：“至于生了何事，我们里面细谈……”\n<p> “嗯。”\n<p> 听得萧炎这么说，苏千也只能点了点头，吩咐一些长老安抚学员后，他便是转身带头对着学院议事厅快步而去，萧炎等人，也是迅跟上，留下身后一大群狂热般的目光。', b'1', '2018-03-16 20:27:41', '2018-03-23 21:43:14');
INSERT INTO `t_book_chapter` VALUES (1657, 'http://www.biquge5200.com/0_844/638376.html', NULL, 'NO1521203244709', '第一千五百九十七章 再入岩浆世界', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1658, 'http://www.biquge5200.com/0_844/638377.html', NULL, 'NO1521203244709', '第一千五百九十八章 岩浆之底的空间', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1659, 'http://www.biquge5200.com/0_844/638378.html', NULL, 'NO1521203244709', '第一千五百九十九章 神秘生物', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1660, 'http://www.biquge5200.com/0_844/638379.html', NULL, 'NO1521203244709', '第一十六百章对话', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1661, 'http://www.biquge5200.com/0_844/638380.html', NULL, 'NO1521203244709', '第一千六百零一章 大战来临！', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1662, 'http://www.biquge5200.com/0_844/638381.html', NULL, 'NO1521203244709', '第一千六百零二章 洞府之战！', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1663, 'http://www.biquge5200.com/0_844/638382.html', NULL, 'NO1521203244709', '第一十六百零三章 以一敌二', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1664, 'http://www.biquge5200.com/0_844/638383.html', NULL, 'NO1521203244709', '第一千六百零四章 洞府出现', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1665, 'http://www.biquge5200.com/0_844/638384.html', NULL, 'NO1521203244709', '第一十六百零五章危机关头', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1666, 'http://www.biquge5200.com/0_844/638385.html', NULL, 'NO1521203244709', '第一千六百零六章 老龙皇', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1667, 'http://www.biquge5200.com/0_844/638386.html', NULL, 'NO1521203244709', '第一千六百零七章 古帝洞府开启', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1668, 'http://www.biquge5200.com/0_844/638387.html', NULL, 'NO1521203244709', '第一千六百零八章 异火广场', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1669, 'http://www.biquge5200.com/0_844/638388.html', NULL, 'NO1521203244709', '第一千六百零九章 恐怖的帝品雏丹', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1670, 'http://www.biquge5200.com/0_844/638389.html', NULL, 'NO1521203244709', '第一千六百一十章 抢夺帝品雏丹', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1671, 'http://www.biquge5200.com/0_844/638390.html', NULL, 'NO1521203244709', '第一千六百一十一章 失手', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1672, 'http://www.biquge5200.com/0_844/638391.html', NULL, 'NO1521203244709', '第一千六百一十二章 魂天帝的野心', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1673, 'http://www.biquge5200.com/0_844/638392.html', NULL, 'NO1521203244709', '第一千六百一十三章 中州之难', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1674, 'http://www.biquge5200.com/0_844/638393.html', NULL, 'NO1521203244709', '第一千六百一十四章 异火榜第一！', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1675, 'http://www.biquge5200.com/0_844/638394.html', NULL, 'NO1521203244709', '第一千六百一十五章 帝之本源', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1676, 'http://www.biquge5200.com/0_844/638395.html', NULL, 'NO1521203244709', '第一千六百一十六章 古帝传承', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1677, 'http://www.biquge5200.com/0_844/638396.html', NULL, 'NO1521203244709', '第一千六百一十七章 源气', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1678, 'http://www.biquge5200.com/0_844/638397.html', NULL, 'NO1521203244709', '第一千六百一十八章 浩劫', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1679, 'http://www.biquge5200.com/0_844/638398.html', NULL, 'NO1521203244709', '第一千六百一十九章 魂帝，魂天帝！', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1680, 'http://www.biquge5200.com/0_844/638399.html', NULL, 'NO1521203244709', '第一千六百二十章斗帝强者的力量！', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1681, 'http://www.biquge5200.com/0_844/638400.html', NULL, 'NO1521203244709', '第一千六百二十一章出关！', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1682, 'http://www.biquge5200.com/0_844/638401.html', NULL, 'NO1521203244709', '第一千六百二十二章 双帝之战！', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1683, 'http://www.biquge5200.com/0_844/638402.html', NULL, 'NO1521203244709', '第一千六百二十三章 双帝之战！（下）', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1684, 'http://www.biquge5200.com/0_844/638403.html', NULL, 'NO1521203244709', '第一千六百二十三章结束，也是开始。（大结局）', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');
INSERT INTO `t_book_chapter` VALUES (1685, 'http://www.biquge5200.com/0_844/638404.html', NULL, 'NO1521203244709', '第一千六百二十四章结束，也是开始 大结局', NULL, b'0', '2018-03-16 20:27:41', '2018-03-16 20:27:41');

-- ----------------------------
-- Table structure for t_book_like
-- ----------------------------
DROP TABLE IF EXISTS `t_book_like`;
CREATE TABLE `t_book_like`  (
  `id` bigint(18) NOT NULL AUTO_INCREMENT COMMENT 'ID自增',
  `book_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书籍编号',
  `member_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会员编号',
  `book_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书籍名称',
  `book_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书籍类型(玄幻小说)',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `book_no`(`book_no`, `member_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '书籍点赞记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_member
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member`  (
  `member_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会员编号',
  `account_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`member_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_member
-- ----------------------------
INSERT INTO `t_member` VALUES ('1', 'battcn', 'battcn', 'ADMIN', '2018-03-23 22:10:28', '2018-03-23 22:10:28');

-- ----------------------------
-- Table structure for t_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log`;
CREATE TABLE `t_sys_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `title` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `method` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行的方法',
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息',
  `ip` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP',
  `duration` smallint(5) NULL DEFAULT NULL COMMENT '执行时间',
  `params` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_log
-- ----------------------------
INSERT INTO `t_sys_log` VALUES (1, 'admin', '日志模块', '日志list', '进入日志查询的页面', '192.168.191.1', 2, NULL, '/sys/log/list', '2018-03-13 17:04:13', '2018-03-13 17:04:13');
INSERT INTO `t_sys_log` VALUES (2, 'admin', '操作管理', '保存操作', '添加/修改操作信息', '192.168.191.1', 0, NULL, '/sys/operate/save', '2018-03-13 17:11:19', '2018-03-13 17:11:19');
INSERT INTO `t_sys_log` VALUES (3, 'admin', '操作管理', '保存操作', '添加/修改操作信息', '192.168.191.1', 0, NULL, '/sys/operate/save', '2018-03-13 17:11:30', '2018-03-13 17:11:30');
INSERT INTO `t_sys_log` VALUES (4, 'admin', '操作管理', '保存操作', '添加/修改操作信息', '192.168.191.1', 0, NULL, '/sys/operate/save', '2018-03-13 17:12:28', '2018-03-13 17:12:28');
INSERT INTO `t_sys_log` VALUES (5, 'admin', '操作管理', '保存操作', '添加/修改操作信息', '192.168.191.1', 0, NULL, '/sys/operate/save', '2018-03-13 17:12:40', '2018-03-13 17:12:40');
INSERT INTO `t_sys_log` VALUES (6, 'admin', '操作管理', '保存操作', '添加/修改操作信息', '192.168.191.1', 0, NULL, '/sys/operate/save', '2018-03-13 17:14:16', '2018-03-13 17:14:16');
INSERT INTO `t_sys_log` VALUES (7, 'admin', '操作管理', '保存操作', '添加/修改操作信息', '192.168.191.1', 0, NULL, '/sys/operate/save', '2018-03-13 17:15:16', '2018-03-13 17:15:16');
INSERT INTO `t_sys_log` VALUES (8, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 3, NULL, '/crawler/proxy/list', '2018-03-13 17:21:35', '2018-03-13 17:21:35');
INSERT INTO `t_sys_log` VALUES (9, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 2, NULL, '/crawler/proxy/list', '2018-03-13 17:23:01', '2018-03-13 17:23:01');
INSERT INTO `t_sys_log` VALUES (10, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 2, NULL, '/crawler/proxy/list', '2018-03-14 09:40:27', '2018-03-14 09:40:27');
INSERT INTO `t_sys_log` VALUES (11, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 3, NULL, '/crawler/proxy/list', '2018-03-14 09:49:13', '2018-03-14 09:49:13');
INSERT INTO `t_sys_log` VALUES (12, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 2, NULL, '/crawler/proxy/list', '2018-03-14 10:15:49', '2018-03-14 10:15:49');
INSERT INTO `t_sys_log` VALUES (13, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 2, NULL, '/crawler/proxy/list', '2018-03-14 10:25:41', '2018-03-14 10:25:41');
INSERT INTO `t_sys_log` VALUES (14, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 0, NULL, '/crawler/proxy/list', '2018-03-14 10:32:00', '2018-03-14 10:32:00');
INSERT INTO `t_sys_log` VALUES (15, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 4, NULL, '/crawler/proxy/list', '2018-03-14 16:31:59', '2018-03-14 16:31:59');
INSERT INTO `t_sys_log` VALUES (16, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 1, NULL, '/crawler/proxy/list', '2018-03-14 16:35:35', '2018-03-14 16:35:35');
INSERT INTO `t_sys_log` VALUES (17, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 0, NULL, '/crawler/proxy/list', '2018-03-14 16:36:14', '2018-03-14 16:36:14');
INSERT INTO `t_sys_log` VALUES (18, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 0, NULL, '/crawler/proxy/list', '2018-03-14 16:37:46', '2018-03-14 16:37:46');
INSERT INTO `t_sys_log` VALUES (19, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 0, NULL, '/crawler/proxy/list', '2018-03-14 16:38:20', '2018-03-14 16:38:20');
INSERT INTO `t_sys_log` VALUES (20, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 0, NULL, '/crawler/proxy/list', '2018-03-14 16:38:27', '2018-03-14 16:38:27');
INSERT INTO `t_sys_log` VALUES (21, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 0, NULL, '/crawler/proxy/list', '2018-03-14 16:38:32', '2018-03-14 16:38:32');
INSERT INTO `t_sys_log` VALUES (22, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 5, NULL, '/crawler/proxy/list', '2018-03-14 16:40:20', '2018-03-14 16:40:20');
INSERT INTO `t_sys_log` VALUES (23, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 0, NULL, '/crawler/proxy/list', '2018-03-14 16:40:33', '2018-03-14 16:40:33');
INSERT INTO `t_sys_log` VALUES (24, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 0, NULL, '/crawler/proxy/list', '2018-03-14 16:42:23', '2018-03-14 16:42:23');
INSERT INTO `t_sys_log` VALUES (25, 'admin', '代理池', '代理池', '进入代理池页', '192.168.191.1', 0, NULL, '/crawler/proxy/list', '2018-03-14 16:42:28', '2018-03-14 16:42:28');
INSERT INTO `t_sys_log` VALUES (26, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 1, NULL, '/crawler/proxy/list', '2018-03-16 19:11:23', '2018-03-16 19:11:23');
INSERT INTO `t_sys_log` VALUES (27, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 0, NULL, '/crawler/proxy/list', '2018-03-16 19:12:24', '2018-03-16 19:12:24');
INSERT INTO `t_sys_log` VALUES (28, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 0, NULL, '/crawler/proxy/list', '2018-03-16 19:12:33', '2018-03-16 19:12:33');
INSERT INTO `t_sys_log` VALUES (29, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 0, NULL, '/crawler/proxy/list', '2018-03-16 19:12:35', '2018-03-16 19:12:35');
INSERT INTO `t_sys_log` VALUES (30, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 1, NULL, '/crawler/proxies/list', '2018-03-16 19:18:11', '2018-03-16 19:18:11');
INSERT INTO `t_sys_log` VALUES (31, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 2, NULL, '/crawler/proxies/list', '2018-03-16 19:31:30', '2018-03-16 19:31:30');
INSERT INTO `t_sys_log` VALUES (32, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 0, NULL, '/crawler/proxies/list', '2018-03-16 19:34:25', '2018-03-16 19:34:25');
INSERT INTO `t_sys_log` VALUES (33, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 2, NULL, '/crawler/proxies/list', '2018-03-16 19:37:46', '2018-03-16 19:37:46');
INSERT INTO `t_sys_log` VALUES (34, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 2, NULL, '/crawler/proxies/list', '2018-03-16 19:38:23', '2018-03-16 19:38:23');
INSERT INTO `t_sys_log` VALUES (35, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 2, NULL, '/crawler/proxies/list', '2018-03-16 20:27:58', '2018-03-16 20:27:58');
INSERT INTO `t_sys_log` VALUES (36, 'admin', '日志模块', '日志list', '进入日志查询的页面', '192.168.2.109', 1, NULL, '/sys/log/list', '2018-03-16 20:35:55', '2018-03-16 20:35:55');
INSERT INTO `t_sys_log` VALUES (37, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 2, NULL, '/crawler/proxies/list', '2018-03-16 20:39:30', '2018-03-16 20:39:30');
INSERT INTO `t_sys_log` VALUES (38, 'admin', '代理池', '代理池', '进入代理池页', '192.168.2.109', 1, NULL, '/crawler/proxies/list', '2018-03-23 21:45:27', '2018-03-23 21:45:27');

-- ----------------------------
-- Table structure for t_sys_manager
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_manager`;
CREATE TABLE `t_sys_manager`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `role_id` tinyint(3) NULL DEFAULT NULL COMMENT '角色',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `credential` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '验证凭证',
  `locked` bit(1) NULL DEFAULT b'1' COMMENT '是否启用 0=禁用 1=启用',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `photo` varchar(266) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `last_login_ip` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆IP',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `AK_ACCOUNT`(`account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_manager
-- ----------------------------
INSERT INTO `t_sys_manager` VALUES (1, 'admin', '123456', 1, '秋殇', '9c6c9e22ae8c773c8f07a75b28563152', b'1', NULL, NULL, NULL, NULL, NULL, NULL, '2017-06-16 08:59:01', '2017-06-16 08:59:01');
INSERT INTO `t_sys_manager` VALUES (2, 'battcn', 'battcn', 2, '11333', NULL, b'1', NULL, NULL, NULL, NULL, NULL, NULL, '2017-06-19 09:20:14', '2017-06-21 13:49:37');

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu`  (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `channel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '渠道',
  `permissions` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  `locked` bit(1) NULL DEFAULT b'1' COMMENT '是否启用 0=禁用 1=启用',
  `parent_id` int(8) NULL DEFAULT NULL COMMENT '父级编号',
  `sequence` int(8) NULL DEFAULT NULL COMMENT '排序编号',
  `rank` int(8) NULL DEFAULT NULL COMMENT '级别',
  `sort` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'tree所需',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference`(`parent_id`) USING BTREE,
  CONSTRAINT `t_sys_menu_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `t_sys_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES (1, '系统管理', '系统管理', 'fa fa fa-home', '/sys/sys', 'sys', b'1', NULL, 1, 0, ',1,', '2017-06-16 09:00:09', '2018-03-13 15:47:31');
INSERT INTO `t_sys_menu` VALUES (2, '菜单管理', '菜单管理', 'fa fa-server', '/sys/menu', 'sys:menu', b'1', 1, 2, 0, ',1,2,', '2017-06-16 09:00:09', '2018-03-16 20:53:43');
INSERT INTO `t_sys_menu` VALUES (3, '操作管理', '操作管理', 'fa fa-hand-lizard-o', '/sys/operate', 'sys:operate', b'1', 1, 3, 0, ',1,3,', '2017-06-16 09:00:09', '2018-03-16 20:53:43');
INSERT INTO `t_sys_menu` VALUES (4, '角色管理', '角色管理', 'fa fa-sitemap', '/sys/role', 'sys:role', b'1', 1, 4, 0, ',1,4,', '2017-06-16 09:00:09', '2018-03-16 20:53:43');
INSERT INTO `t_sys_menu` VALUES (5, '帐号管理', '帐号管理', 'fa fa-sun-o', '/sys/manager', 'sys:manager', b'1', 1, 5, 0, ',1,5,', '2017-06-16 09:00:09', '2018-03-16 20:53:43');
INSERT INTO `t_sys_menu` VALUES (6, '日志管理', '日志管理', 'fa fa-comments-o', '/sys/log', 'sys:logs', b'1', 1, 6, 0, ',1,6,', '2017-06-16 09:00:09', '2018-03-16 20:53:43');
INSERT INTO `t_sys_menu` VALUES (7, '字典管理', '字典管理', 'fa fa-newspaper-o', '/sys/dict', 'sys:dict', b'0', 1, 7, 0, ',1,7,', '2017-06-16 09:00:09', '2018-03-16 20:53:43');
INSERT INTO `t_sys_menu` VALUES (8, '爬虫管理', '爬虫管理', 'fa fa-cogs', '/crawler', 'sys:crawler', b'1', NULL, 1, 0, ',8,', '2017-06-16 09:00:09', '2018-03-16 19:15:30');
INSERT INTO `t_sys_menu` VALUES (10, 'IP代理', 'IP代理', 'fa fa-cogs', '/crawler/proxies', 'crawler:proxies', b'1', 8, 2, 0, ',8,10,', '2018-03-13 15:48:14', '2018-03-16 20:53:43');
INSERT INTO `t_sys_menu` VALUES (11, '书籍管理', '书籍管理', 'fa fa-cogs', '/crawler/books', 'crawler:books', b'1', 8, 3, 0, ',8,11,', '2018-03-16 19:13:23', '2018-03-16 20:53:53');

-- ----------------------------
-- Table structure for t_sys_operate
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_operate`;
CREATE TABLE `t_sys_operate`  (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` int(8) NOT NULL COMMENT '菜单ID',
  `op` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选项',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名字',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `sequence` int(11) NULL DEFAULT NULL COMMENT '排序号',
  `display` bit(1) NOT NULL COMMENT '是否显示出来',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `menu_op`(`menu_id`, `op`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_operate
-- ----------------------------
INSERT INTO `t_sys_operate` VALUES (1, 1, 'list', '查看', 'list', NULL, 1, b'0', '2017-06-20 18:52:32', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (2, 2, 'edit', '编辑', 'edit', NULL, 3, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (3, 2, 'list', '查看', 'list', NULL, 1, b'0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (4, 2, 'remove', '删除', 'remove', NULL, 4, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (5, 2, 'save', '保存', 'save', NULL, 5, b'0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (6, 2, 'add', '新增', 'plus', NULL, 2, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (7, 3, 'edit', '编辑', 'edit', NULL, 3, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (8, 3, 'list', '查看', 'list', NULL, 1, b'0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (9, 3, 'remove', '删除', 'remove', NULL, 4, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (10, 3, 'save', '保存', 'save', NULL, 5, b'0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (11, 3, 'add', '新增', 'plus', NULL, 2, b'1', '2017-06-16 09:01:08', '2017-06-21 10:15:01');
INSERT INTO `t_sys_operate` VALUES (12, 4, 'edit', '编辑', 'edit', NULL, 3, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (13, 4, 'list', '查看', 'list', NULL, 1, b'0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (14, 4, 'remove', '删除', 'remove', NULL, 4, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (15, 4, 'save', '保存', 'save', NULL, 5, b'0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (16, 4, 'add', '新增', 'plus', NULL, 2, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (17, 5, 'edit', '编辑', 'edit', NULL, 3, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (18, 5, 'list', '查看', 'list', NULL, 1, b'0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (19, 5, 'remove', '删除', 'remove', NULL, 4, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (20, 5, 'save', '保存', 'save', NULL, 5, b'0', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (21, 5, 'add', '新增', 'plus', NULL, 2, b'1', '2017-06-16 09:01:08', '2017-06-20 19:09:41');
INSERT INTO `t_sys_operate` VALUES (22, 6, 'list', '查看', 'list', NULL, 1, b'0', '2017-06-16 09:01:08', '2017-06-21 09:29:01');
INSERT INTO `t_sys_operate` VALUES (23, 7, 'edit', '编辑', 'edit', NULL, 2, b'1', '2017-06-16 09:01:08', '2017-06-21 09:29:09');
INSERT INTO `t_sys_operate` VALUES (24, 7, 'list', '查看', 'list', NULL, 2, b'0', '2017-06-16 09:01:08', '2017-06-21 09:29:10');
INSERT INTO `t_sys_operate` VALUES (25, 7, 'remove', '删除', 'remove', NULL, 1, b'1', '2017-06-16 09:01:08', '2017-06-21 09:29:12');
INSERT INTO `t_sys_operate` VALUES (26, 7, 'save', '保存', 'save', NULL, 3, b'0', '2017-06-16 09:01:08', '2017-06-21 09:29:13');
INSERT INTO `t_sys_operate` VALUES (27, 4, 'permissions', '分配权限', 'permissions', NULL, 1, b'1', '2017-06-20 13:38:21', '2017-06-21 09:29:14');
INSERT INTO `t_sys_operate` VALUES (28, 8, 'list', '查看', 'list', NULL, 1, b'0', '2017-06-16 09:01:08', '2017-06-21 10:09:23');
INSERT INTO `t_sys_operate` VALUES (29, 6, 'export', '导出', 'file-excel-o', '导出Excel', 1, b'1', '2018-01-17 12:25:43', '2018-01-17 12:36:50');
INSERT INTO `t_sys_operate` VALUES (30, 9, 'list', '查看', 'list', '查看代理', 1, b'0', '2018-03-13 15:49:49', '2018-03-13 15:49:49');
INSERT INTO `t_sys_operate` VALUES (31, 10, 'list', '查看', 'list', '', NULL, b'0', '2018-03-13 17:18:01', '2018-03-13 17:18:01');
INSERT INTO `t_sys_operate` VALUES (32, 10, 'crawler', '爬虫', 'crawler', '更新代理', 1, b'1', '2018-03-14 09:42:46', '2018-03-14 09:42:46');
INSERT INTO `t_sys_operate` VALUES (33, 11, 'list', '查看', 'list', '书籍信息', 1, b'0', '2018-03-16 19:16:48', '2018-03-16 19:31:39');
INSERT INTO `t_sys_operate` VALUES (34, 12, 'list', '查看', 'list', '书籍信息', 1, b'0', '2018-03-16 19:17:08', '2018-03-16 19:17:08');

-- ----------------------------
-- Table structure for t_sys_proxy_pool
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_proxy_pool`;
CREATE TABLE `t_sys_proxy_pool`  (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT 'ID自增',
  `host` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代理IP',
  `port` int(8) NULL DEFAULT NULL COMMENT '代理端口',
  `anonymity` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '匿名度/匿名等级',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP类型（HTTP,HTTPS）',
  `location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归属地',
  `validate_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '验证时间',
  `source` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP来源',
  `locked` bit(1) NULL DEFAULT b'1' COMMENT '是否启用 0=禁用 1=启用',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2994 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代理IP池' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES (1, '超级管理员', 'manager', '超级管理员,不听话就封号', '2017-06-16 09:01:23', '2017-06-16 09:01:23');
INSERT INTO `t_sys_role` VALUES (2, '测试管理员', 'test', '专门测试没有权限的时候1', '2017-06-16 09:01:23', '2017-06-16 09:01:23');
INSERT INTO `t_sys_role` VALUES (3, '开发管理员', 'code', '我是开发不服删数据库', '2017-06-16 09:01:23', '2017-06-20 19:11:31');

-- ----------------------------
-- Table structure for t_sys_role_operate
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_operate`;
CREATE TABLE `t_sys_role_operate`  (
  `role_id` int(8) NOT NULL COMMENT '角色ID',
  `operate_id` int(8) NOT NULL COMMENT '操作表ID',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`role_id`, `operate_id`) USING BTREE,
  INDEX `operate_id`(`operate_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role_operate
-- ----------------------------
INSERT INTO `t_sys_role_operate` VALUES (1, 1, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 2, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 3, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 4, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 5, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 6, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 7, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 8, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 9, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 10, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 11, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 12, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 13, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 14, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 15, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 16, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 17, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 18, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 19, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 20, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 21, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 22, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 23, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 24, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 25, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 26, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 27, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 28, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 29, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 31, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 32, '2018-03-14 10:15:47', '2018-03-14 10:15:47');
INSERT INTO `t_sys_role_operate` VALUES (1, 33, '2018-03-16 19:17:28', '2018-03-16 19:17:28');
INSERT INTO `t_sys_role_operate` VALUES (1, 34, '2018-03-16 19:17:31', '2018-03-16 19:17:31');
INSERT INTO `t_sys_role_operate` VALUES (2, 1, '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES (2, 2, '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES (2, 3, '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES (2, 4, '2017-06-16 09:01:39', '2017-06-16 09:01:39');
INSERT INTO `t_sys_role_operate` VALUES (2, 5, '2017-06-16 09:01:39', '2017-06-16 09:01:39');

-- ----------------------------
-- Procedure structure for refreshTreeNodes
-- ----------------------------
DROP PROCEDURE IF EXISTS `refreshTreeNodes`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `refreshTreeNodes`( )
BEGIN
	DECLARE
		LEVEL INT;

	SET LEVEL = 0;
	UPDATE t_sys_menu a
	INNER JOIN ( SELECT id, rank, concat( ',', ID, ',' ) sort FROM t_sys_menu WHERE parent_id IS NULL ) b ON a.id = b.id
	SET a.rank = b.rank,
	a.sort = b.sort;
	WHILE
			FOUND_ROWS( ) > 0 DO

			SET LEVEL = LEVEL + 1;
		UPDATE t_sys_menu a
		INNER JOIN ( SELECT ID, rank, sort FROM t_sys_menu WHERE rank = LEVEL - 1 ) b ON a.parent_id = b.id
		SET a.rank = b.rank,
		a.sort = concat( b.sort, a.ID, ',' );

	END WHILE;

END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
