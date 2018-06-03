package demo;

import org.apache.log4j.Logger;

/**
 * 实例程序入口
 * 远程服务
 * @author cyd
 * 2016年1月11日
 *
 */
public class RemoteAkkaServer {

    private static final Logger logger = Logger.getLogger(AkkaService.class);

    public static void main(String[] args) {
        AkkaService remoteService = AkkaService.getInstance(10002, "remoteServer", "remoteActor");
        remoteService.init();
        logger.info("remoteServer启动成功");
    }
}
