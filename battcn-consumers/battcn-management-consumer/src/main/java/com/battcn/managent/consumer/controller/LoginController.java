package com.battcn.managent.consumer.controller;

import com.battcn.system.pojo.po.Manager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author Levin
 */
@Api(value = "登陆接口", description = "登陆接口", tags = "1.1")
@Controller
public class LoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @ApiIgnore
    @GetMapping(value = {"/", "/login"})
    public String login() {
        return PATH_LOGIN;
    }

    @ApiIgnore
    @GetMapping("logout")
    public String logout() {
        return PATH_REDIRECT_LOGOUT;
    }

    @ApiOperation(value = "根据账号密码登陆")
    @PostMapping(value = "/login")
    public String login(Manager manager, RedirectAttributes model) {
        // 想要得到 SecurityUtils.getSubject() 的对象．．访问地址必须跟shiro的拦截地址内．不然后会报空指针
        Subject sub = SecurityUtils.getSubject();
        // 用户输入的账号和密码,,存到UsernamePasswordToken对象中..然后由shiro内部认证对比,
        // 认证执行者交由ShiroDbRealm中doGetAuthenticationInfo处理
        // 当以上认证成功后会向下执行,认证失败会抛出异常
        UsernamePasswordToken token = new UsernamePasswordToken(manager.getAccount(), manager.getPassword());
        try {
            sub.login(token);
        } catch (UnknownAccountException e) {
            logger.error("对用户[{}]进行登录验证,验证未通过,用户不存在", manager.getAccount());
            model.addFlashAttribute("msg", "验证未通过,用户不存在");
            token.clear();
            return "redirect:/login";
        } catch (LockedAccountException lae) {
            logger.error("对用户[{}]进行登录验证,验证未通过,账户已锁定", manager.getAccount());
            model.addFlashAttribute("msg", "验证未通过,账户已锁定");
            token.clear();
            return "redirect:/login";
        } catch (ExcessiveAttemptsException e) {
            logger.error("对用户[{}]进行登录验证,验证未通过,错误次数过多", manager.getAccount());
            model.addFlashAttribute("msg", "验证未通过,错误次数过多");
            token.clear();
            return "redirect:/login";
        } catch (AuthenticationException e) {
            logger.error("对用户[{}]进行登录验证,验证未通过,堆栈轨迹如下", manager.getAccount(), e);
            model.addFlashAttribute("msg", "验证未通过,請检查用户名和密码信息");
            token.clear();
            return "redirect:/login";
        }
        return "redirect:/index";
    }

}
