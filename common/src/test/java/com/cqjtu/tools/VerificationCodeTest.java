package com.cqjtu.tools;


import com.cqjtu.domain.VerificationCodeBufferedImage;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/25.
 */

public class VerificationCodeTest {

    public static void main(String [] args) throws Exception {
        /*
        VerificationCodeBufferedImage verificationCodeBufferedImage = VerificationCode.getVerificationCodeBufferedImage();
        System.out.println(verificationCodeBufferedImage.getVerificationCode());
        BufferedImage bufferedImage = verificationCodeBufferedImage.getBufferedImage();
        ImageIO.write(bufferedImage,"jpg",new File("/Users/zjhfyq/test.jpg"));
        */	List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File(MybatisGenerator.class.getResource("/").getPath() + "mybatisGenerator.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        System.out.println("mybatis逆向工程完成");

    }




}
