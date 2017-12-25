package com.cqjtu.tools;


import com.cqjtu.domain.VerificationCodeBufferedImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/25.
 */

public class VerificationCodeTest {

    public static void main(String [] args) throws IOException {
        VerificationCodeBufferedImage verificationCodeBufferedImage = VerificationCode.getVerificationCodeBufferedImage();
        System.out.println(verificationCodeBufferedImage.getVerificationCode());
        BufferedImage bufferedImage = verificationCodeBufferedImage.getBufferedImage();
        ImageIO.write(bufferedImage,"jpg",new File("/Users/zjhfyq/test.jpg"));
    }

}
