package com.cqjtu.tools;

import com.cqjtu.domain.VerificationCodeBufferedImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;



/**
 * @author zjhfyq
 * @Desc 获取图片验证码的工具
 * @date 2017/12/25.
 */
public class VerificationCode {

    private static final int WIDTH=120;
    private static final int HEIGHT=40;


    public static VerificationCodeBufferedImage getVerificationCodeBufferedImage(){
        VerificationCodeBufferedImage verificationCodeBufferedImage = new VerificationCodeBufferedImage();

        BufferedImage ima=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics g =ima.getGraphics();

        //设置背景颜色
        setBackGround(g);
        //设置边框
        setBorder(g);
        //画干扰线
        drawRandomLine(g);
        //画验证码
        String randomNum = drawRandomNum((Graphics2D) g);

        verificationCodeBufferedImage.setBufferedImage(ima);
        verificationCodeBufferedImage.setVerificationCode(randomNum);
        return verificationCodeBufferedImage;
    }



    private static String drawRandomNum(java.awt.Graphics2D g) {
        g.setColor(Color.BLUE);
        g.setFont(new Font("黑体", Font.BOLD, 20));
        String base="ABCDEFGHIGKLMNOPQRATUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int x=5;
        String yan="";
        for(int i=0;i<4;i++){
            String num=base.charAt(new Random().nextInt(base.length()))+"";
            yan+=num;
            int degree=new Random().nextInt()%30;
            g.rotate(degree*Math.PI/180,x,20);
            g.drawString(num, x, 25);
            g.rotate(-degree*Math.PI/180,x,20);
            x+=30;
        }
        return yan;
    }

    private  static void drawRandomLine(java.awt.Graphics g) {
        g.setColor(Color.GREEN);
        for(int i=0;i<5;i++){
            int x1=new Random().nextInt(120);
            int y1=new Random().nextInt(35);
            int x2=new Random().nextInt(120);
            int y2=new Random().nextInt(35);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    private static void setBorder(java.awt.Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect(1, 1, WIDTH-2, HEIGHT-2);
    }

    private static void setBackGround(java.awt.Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

}
