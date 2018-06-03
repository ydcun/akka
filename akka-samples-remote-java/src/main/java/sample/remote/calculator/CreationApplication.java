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
            ConfigFactory.load(("calculator")));
  }

  public static void startRemoteCreationSystem() {
    final ActorSystem system = ActorSystem.create("CreationSystem",
        ConfigFactory.load("remotecreation"));
    final ActorRef actor = system.actorOf(Props.create(CreationActor.class),
        "creationActor");

    final Random r = new Random();
    actor.tell(new Op.Message("hi server!"), null);
    system.dispatcher();
  }

}
