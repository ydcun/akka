package sample.remote.calculator;

import akka.actor.AbstractActor;

public class CalculatorActor extends AbstractActor {
  @Override
  public Receive createReceive() {
    return receiveBuilder()
      .match(Op.Message.class, multiply -> {
        System.out.println("Calculating "+multiply.message);
        String result = multiply.message+"111111111111111";
        sender().tell(new Op.MessageResult(result), self());
      })
      .build();
  }
}
