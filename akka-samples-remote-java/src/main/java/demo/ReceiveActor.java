package demo;

import akka.actor.UntypedActor;
import org.apache.log4j.Logger;

/**
 * akka消息接收类
 * @author cyd
 * 2016年1月11日
 *
 */
public class ReceiveActor extends UntypedActor {

    private static final Logger logger = Logger.getLogger(ReceiveActor.class);

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof String) {
            try {
                logger.info("收到消息 msg:" + msg.toString());
                this.getSender().tell("Hello I'm " + this.getSelf().path().name(), getSelf());
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage(), e);
                this.getSender().tell("Error!", getSelf());
            }
        } else {
            logger.info(msg.toString());
        }
    }
}