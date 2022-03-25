package com.jiangxia.security.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.jiangxia.commonutils.MD5;

/**
 * @author jiangxia
 * @date 2022年03月25日 16:52
 * desc:token密码的处理方法类型
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder(int strength){

    }
    public DefaultPasswordEncoder(){
        this(-1);
    }

    /** 
     * @description: 密码解密
     * @param: charSequence 
     * @return:  
     * @author:江夏
     * @date: 2022/3/25 16:58
     */ 
    @Override
    public String encode(CharSequence charSequence) {
        return MD5.encrypt(charSequence.toString());
    }

    /** 
     * @description: 密码是否匹配
     * @param: charSequence
encodedPassword 
     * @return:  
     * @author:江夏
     * @date: 2022/3/25 20:25
     */ 
    @Override
    public boolean matches(CharSequence charSequence, String encodedPassword) {
        return encodedPassword.equals(MD5.encrypt(charSequence.toString()));
    }
}
