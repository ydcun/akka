package sample.remote.calculator;

import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.Random;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import com.typesafe.config.ConfigFactory;

public class CreationApplication {

  public static void main(String[] args) {
    if (args.length == 0 || args[0].equals("Worker"))
      startRemoteWorkerSystem();
    if (args.length == 0 || args[0].equals("Creation"))
      startRemoteCreationSystem();
  }

  public static void startRemoteWorkerSystem() {
    ActorSystem system = ActorSystem.create("WorkerSystem",
            ConfigFactory.parseString(("akka {\n" +
                    "\n" +
                    "  actor {\n" +
                    "    provider = remote\n" +
                    "    warn-about-java-serializer-usage = off\n" +
                    "  }\n" +
                    "\n" +
                    "  remote {\n" +
                    "    netty.tcp {\n" +
                    "      hostname = \"127.0.0.1\"\n" +
                    "    }\n" +
                    "  }\n" +
                    "\n" +
                    "  remote.netty.tcp.port = 2552\n" +
                    "}")));
  }

  public static void startRemoteCreationSystem() {
    final ActorSystem system = ActorSystem.create("CreationSystem",
        ConfigFactory.parseString("akka {\n" +
                "  actor {\n" +
                "    provider = remote\n" +
                "    deployment {\n" +
                "      \"/creationActor/*\" {\n" +
                "        remote = \"akka.tcp://WorkerSystem@127.0.0.1:2552\"\n" +
                "      }\n" +
                "    }\n" +
                "    warn-about-java-serializer-usage = off\n" +
                "  }\n" +
                "  remote {\n" +
                "    netty.tcp {\n" +
                "      hostname = \"127.0.0.1\"\n" +
                "    }\n" +
                "  }\n" +
                "  remote.netty.tcp.port = 2554\n" +
                "}"));
    final ActorRef actor = system.actorOf(Props.create(CreationActor.class),
        "creationActor");

    final Random r = new Random();
    for(int i=0; i<10;i++){
      actor.tell(new Op.Message("hi server!"+i), null);
    }
    system.dispatcher();
  }

}
