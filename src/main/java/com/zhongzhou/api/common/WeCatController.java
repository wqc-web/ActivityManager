package com.zhongzhou.api.common;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.zhongzhou.common.base.BaseController;
import com.zhongzhou.common.bean.ReturnEntity;
import com.zhongzhou.common.bean.ReturnEntityError;
import com.zhongzhou.common.bean.ReturnEntitySuccess;
import com.zhongzhou.common.utils.WeCatConstants;
import com.zhongzhou.common.utils.WecatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @ClassName WeCatController
 * @Description 微信公众号Controller
 * @Date 2020/3/12 10:08
 * @Author wj
 */
@RequestMapping("/weCat")
@RestController
@Slf4j
public class WeCatController extends BaseController {
    private static final long serialVersionUID = 8683055812631943848L;

    /**
     * 验证微信服务器
     *
     * @param request HttpServletRequest
     * @return String
     */
    @GetMapping("/connect")
    public String connect(HttpServletRequest request) {
        try {
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
            if (WecatUtil.checkSignature(signature, timestamp, nonce)) {
                log.info("signature:{}", signature);
                log.info("echostr:{}", echostr);
                return echostr;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取access_token
     *
     * @return ReturnEntity
     */
    @GetMapping("/getToken")
    public ReturnEntity getToken() {
        try {
            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("grant_type", WeCatConstants.WX_GRANT_TYPE);
            paramMap.put("appid", WeCatConstants.WX_APP_ID);
            paramMap.put("secret", WeCatConstants.WX_APP_SECRET);
            String result = HttpUtil.get(WeCatConstants.WX_TOKEN_URL, paramMap);
            JSONObject json = JSONObject.parseObject(result);
            if (null != json) {
                if (json.containsKey("access_token")) {
                    String accessToken = json.getString("access_token");
                    Long expiresTime = json.getLong("expires_in");
                    log.info("accessToken:{}, expiresTime:{}", accessToken, expiresTime);
                    return new ReturnEntitySuccess("get access_token success!", json);
                } else {
                    return new ReturnEntitySuccess("get access_token error!", json);
                }
            } else {
                return new ReturnEntityError("url result is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return new ReturnEntityError("get access_token error!");
        }
    }
}
