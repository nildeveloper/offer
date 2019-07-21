package com.lhl.boot.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-04-14
 * Time: 19:56
 * Description:
 */
@Component
@ConfigurationProperties("app.jwt")
public class JWTConfiguration {

    private Logger logger = LoggerFactory.getLogger(JWTConfiguration.class);

    // 加密密钥
    private String secret;
    
    // 有效时间
    private long expire;
    
    // 用户凭证
    private String header;

    
    //获取:加密秘钥
    public String getSecret() {
        return secret;
    }
    
    // 设置:加密秘钥
    public void setSecret(String secret) {
        this.secret = secret;
    }


    // 获取:有效期(s)
    public long getExpire() {
        return expire;
    }

    // 设置:有效期(s)
    public void setExpire(long expire) {
        this.expire = expire;
    }
    
    // 获取:凭证
    public String getHeader() {
        return header;
    }
    
    // 设置:凭证
    public void setHeader(String header) {
        this.header = header;
    }

    
    /**
     * 生成Token签名
     * @param userId 用户ID
     * @return 签名字符串
     * @date 2018-05-18 16:35
     */
    public String generateToken(long userId) {
        logger.info("header=" + getHeader() + ", expire=" + getExpire() + ", secret=" + getSecret());
        Date nowDate = new Date();
        // 过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        return Jwts.builder().setHeaderParam("typ", "JWT").setSubject(String.valueOf(userId)).setIssuedAt(nowDate)
                .setExpiration(expireDate).signWith(SignatureAlgorithm.HS512, getSecret()).compact();
        // 注意: JDK版本高于1.8, 缺少 javax.xml.bind.DatatypeConverter jar包,编译出错
    }

    /**
     * 生成验证码签名
     * @param code
     * @return
     */
    public String generateVerifyCodeToken(String code) {
        return Jwts.builder().setHeaderParam("typ", "JWT").setSubject(code)
                .signWith(SignatureAlgorithm.HS512, getSecret()).compact();
    }

    /**
     * 获取签名信息
     * @param token
     * @date 2018-05-18 16:47
     */
    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            logger.debug("validate is token error ", e);
            return null;
        }
    }

    /**
     * 判断Token是否过期
     * @param expiration
     * @return true 过期
     * @date 2018-05-18 16:41
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}
