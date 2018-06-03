package demo;

import org.apache.log4j.Logger;

/**
 * 实例程序入口 
 * 本地服务 
 * @author cyd 
 * 2016年1月11日 
 * 
 */  
public class LocalAkkaServer {  
  
    private static final Logger logger = Logger.getLogger(LocalAkkaServer.class);
      
    public static void main(String[] args) {  
        AkkaService localService = AkkaService.getInstance(10001, "localServer", "localActor");  
        localService.init();  
        logger.info("localServer启动成功");  
        //由于在同一台机器上测试，所以直接取localService的ip  
        String str = localService.visitService("remoteServer", localService.getHost(), 10002, "remoteActor", "Hello I'm local!");  
        logger.info("reply:" + str);  
    }  
}  