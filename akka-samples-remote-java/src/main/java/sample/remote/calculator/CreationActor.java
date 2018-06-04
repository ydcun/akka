package sample.remote.calculator;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.AbstractActor;

public class CreationActor extends AbstractActor {

  @Override
  public Receive createReceive() {
    return receiveBuilder()
      .match(Op.MathOp.class, message -> {
        ActorRef calculator = getContext().actorOf(Props.create(CalculatorActor.class));
        calculator.tell(message, self());
      })
      .match(Op.MessageResult.class, result -> {
        System.out.println("Mul result: "+result.message);
        getContext().stop(sender());
      })
      .build();
  }
}
