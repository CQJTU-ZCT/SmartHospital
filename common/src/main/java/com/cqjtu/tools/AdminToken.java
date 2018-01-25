package com.cqjtu.tools;

/**
 * @author zjhfyq
 * @Desc 用户保存管理员的动态令牌
 * @date 2018/1/25.
 */
public class AdminToken {
    public static  String token = null;
    public static  String adminMail =null;
    public static  Long sendTime =-1L;
    public static  Long keepTime = -1L;





    public static  void reduction(){
        token = null;
        adminMail = null;
        sendTime = -1L;
        keepTime = -1L;
    }


    public static class AdminTokenLife extends Thread{
        @Override
        public void run() {
            while (true) {
                if (keepTime >0){
                    //存在保存时间
                    if (sendTime > 0){
                        if (System.currentTimeMillis() > keepTime + sendTime){
                            //超出时效
                            LoggerTool.getLogger(AdminTokenLife.class).info("清除临时令牌："+token);
                            reduction();
                            break;
                        }else {
                            LoggerTool.getLogger(AdminTokenLife.class).info("临时令牌："+token+"  生成时间："+sendTime+"  保存时效："+keepTime);
                        }
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        break;
                    }
                }else {
                    break;
                }
            }
            LoggerTool.getLogger(AdminTokenLife.class).info("管理员临时令牌时效监督线程执行结束");
        }
    }



}
