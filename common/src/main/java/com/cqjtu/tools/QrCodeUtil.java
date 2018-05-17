package com.cqjtu.tools;


import com.cqjtu.messages.Message;
import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/29.
 */
public class QrCodeUtil {

    /**
     * 二维码 添加 logo图标 处理的方法,
     * 模仿微信自动生成二维码效果，有圆角边框，logo和二维码间有空白区，logo带有灰色边框
     * @author zjh
     */
    private class LogoConfig{
        /**
         * 设置 logo
         * @param matrixImage 源二维码图片
         * @return 返回带有logo的二维码图片
         * @throws IOException
         * @author Administrator sangwenhao
         */
        public BufferedImage logoMatrix(BufferedImage matrixImage,String logoPath) throws IOException{
            /**
             * 读取二维码图片，并构建绘图对象
             */
            Graphics2D g2 = matrixImage.createGraphics();

            int matrixWidth = matrixImage.getWidth();
            int matrixHeigh = matrixImage.getHeight();

            /**
             * 读取Logo图片
             */
            BufferedImage logo = ImageIO.read(new File(logoPath));
            //开始绘制图片
            g2.drawImage(logo,matrixWidth/5*2,matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5, null);

            BasicStroke stroke = new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
            // 设置笔画对象
            g2.setStroke(stroke);
            //指定弧度的圆角矩形
            RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth/5*2, matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5,20,20);
            g2.setColor(Color.white);
            // 绘制圆弧矩形
            g2.draw(round);
            //设置logo 有一道灰色边框
            BasicStroke stroke2 = new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
            // 设置笔画对象
            g2.setStroke(stroke2);
            RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth/5*2+2, matrixHeigh/5*2+2, matrixWidth/5-4, matrixHeigh/5-4,20,20);
            g2.setColor(new Color(128,128,128));
            // 绘制圆弧矩形
            g2.draw(round2);
            g2.dispose();
            matrixImage.flush() ;
            return matrixImage ;
        }

    }


    /**
     * 二维码的生成需要借助MatrixToImageWriter类，该类是由Google提供的，
     * 可以将该类直接拷贝到源码中使用
     */
    private class MatrixToImageWriter{
        /**
         * 用于设置图案的颜色
         */
        private static final int BLACK = 0xFF000000;
        /**
         * 用于背景色
         */
        private static final int WHITE = 0xFFFFFFFF;

        public  BufferedImage toBufferedImage(BitMatrix matrix) {
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y,  (matrix.get(x, y) ? BLACK : WHITE));
                }
            }
            return image;
        }

        public  void writeToFile(BitMatrix matrix, String format, String outPath,String logoPath) throws IOException {
            BufferedImage image = toBufferedImage(matrix);

            if (logoPath != null){
                //设置logo图标
                LogoConfig logoConfig = new LogoConfig();
                image = logoConfig.logoMatrix(image,logoPath);
            }
            if (!ImageIO.write(image, format, new File(outPath))) {
                throw new IOException("Could not write an image of format " + format);
            }
        }
        public  void writeToStream(BitMatrix matrix, String format, OutputStream stream,String logoPath) throws IOException {
            BufferedImage image = toBufferedImage(matrix);
            if (logoPath != null){
                //设置logo图标
                LogoConfig logoConfig = new LogoConfig();
                image = logoConfig.logoMatrix(image,logoPath);
            }
            if (!ImageIO.write(image, format, stream)) {
                throw new IOException("Could not write an image of format " + format);
            }
        }
    }


    public  void createQrCodeToFile(String content,String outPath,String logoPath,String outFormat,int width,int height) throws IOException, WriterException{
        if (width <= 0 || height <=0){
            // 二维码图片宽度 300
             width = 430;
            // 二维码图片高度300
             height = 430;
        }
        // 二维码的图片格式 jpg
        String format =outFormat == null ? "jpg":outFormat;

        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 内容所使用字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //hints.put(EncodeHintType.MAX_SIZE, 350);//设置图片的最大值
        //hints.put(EncodeHintType.MIN_SIZE, 100);//设置图片的最小值
        //设置二维码边的空度，非负数
        hints.put(EncodeHintType.MARGIN, 1);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(
                //要编码的内容
                content,
                //编码类型，目前zxing支持：Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,
                //Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,
                //MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E 1D,UPC/EAN extension,UPC_EAN_EXTENSION
                BarcodeFormat.QR_CODE,
                //条形码的宽度
                width,
                //条形码的高度
                height,
                //生成条形码时的一些配置,此项可选
                hints);
        new MatrixToImageWriter().writeToFile(bitMatrix, format, outPath,logoPath);
    }



    public  void createQrCodeToStream(String content,OutputStream stream,String logoPath,String outFormat,int width,int height) throws IOException, WriterException{
        if (width <= 0 || height <=0){
            // 二维码图片宽度 300
            width = 430;
            // 二维码图片高度300
            height = 430;
        }
        // 二维码的图片格式 jpg
        String format =outFormat == null ? "jpg":outFormat;

        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 内容所使用字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //hints.put(EncodeHintType.MAX_SIZE, 350);//设置图片的最大值
        //hints.put(EncodeHintType.MIN_SIZE, 100);//设置图片的最小值
        //设置二维码边的空度，非负数
        hints.put(EncodeHintType.MARGIN, 1);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(
                //要编码的内容
                content,
                //编码类型，目前zxing支持：Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,
                //Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,
                //MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E 1D,UPC/EAN extension,UPC_EAN_EXTENSION
                BarcodeFormat.QR_CODE,
                //条形码的宽度
                width,
                //条形码的高度
                height,
                //生成条形码时的一些配置,此项可选
                hints);
        new MatrixToImageWriter().writeToStream(bitMatrix,outFormat,stream,logoPath);
    }


    /*
    public static void main(String [] args) throws IOException, WriterException {
        QrCodeUtil util = new QrCodeUtil();
        Message message = new Message();
        User user = new User();
        user.setUsername("陈雷");
        user.setPassword("复活甲为何我会发了");
        message.setCode(1);
        message.setInfo("OK");
        message.put("user",user);

        util.createQrCodeToFile(JsonUtil.praseBeanToJson(message),"/Users/zjhfyq/code.jpg",null,"jpg",430,430);
    }*/



}
