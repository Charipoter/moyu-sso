package com.moyu.sso.controller;

import com.moyu.sso.consts.Const;
import com.moyu.sso.model.SsoUser;
import com.moyu.sso.model.SsoUserTO;
import com.moyu.sso.service.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登陆中心的欢迎界面
     */
    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        // 获取登录的用户信息
        SsoUser existingUser = loginService.getUserInfo(request);

        if (existingUser == null) {
            // 转到登录页，标记未登录，不要重复检查
            return "forward:/login?notLogin=1";
        } else {
            // 返回主页
            model.addAttribute("user", existingUser);
            return "index";
        }
    }

    /**
     * 登录界面
     */
    @RequestMapping(path = "/login", method = { RequestMethod.GET, RequestMethod.POST })
    public String login(@RequestParam String redirectUrl,
                        @RequestParam(required = false) String notLogin,
                        @RequestParam(required = false) String errorMsg,
                        HttpServletRequest request,
                        Model model) {

        SsoUser existingUser = null;

        // 未被检查过，查询信息
        if (notLogin == null) {

            existingUser = loginService.getUserInfo(request);
        }

        // 已登录，重定向到回调地址（已登录为何会请求该接口->缓存还在，但客户端自己存的令牌意外不见了）
        if (existingUser != null) {

            if (StringUtils.hasLength(redirectUrl)) {
                // 令牌发一份回去
                return "redirect:" + redirectUrl + "?cacheId=" + loginService.getCacheIdIfPresent(request);
            } else {
                throw new RuntimeException("给了空的回调地址，考虑业务的问题");
            }
        }

        // 未登录，返回登录界面
        model.addAttribute("redirectUrl", redirectUrl);
        model.addAttribute("errorMsg", errorMsg);
        return "login";
    }

    /**
     * 登录功能
     */
    @PostMapping("/doLogin")
    public String doLogin(HttpServletRequest request,
                          @RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String redirectUrl) {

        // 判断账号密码是否正确
        SsoUserTO result = loginService.findUser(username, password);
        // 账号密码错误
        if (!result.getCode().equals(Const.SUCCESS_CODE)) {

            return "forward:/login?notLogin=1&errorMsg=" + result.getMsg();
        }

        // 数据转移
        SsoUser userToStore = new SsoUser();
        BeanUtils.copyProperties(result, userToStore);

        // 将用户信息缓存，返回 id
        String cacheId = loginService.storeUserInfo(userToStore, request);

        if (StringUtils.hasLength(redirectUrl)) {

            return "redirect:" + redirectUrl + "?cacheId=" + cacheId;
        } else {
            throw new RuntimeException("给了空的回调地址，考虑业务的问题");
        }

    }

    /**
     * 登出
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,
                         @RequestParam String redirectUrl) {

        loginService.removeUserInfo(request);

        return "redirect:" + redirectUrl;
    }
}
