package com.cqjtu.domain;

import java.awt.image.BufferedImage;

/**
 * @author zjhfyq
 * @Desc 图片验证码返回实体对象
 * @date 2017/12/25.
 */
public class VerificationCodeBufferedImage {

    private BufferedImage bufferedImage;
    private String verificationCode;


    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
