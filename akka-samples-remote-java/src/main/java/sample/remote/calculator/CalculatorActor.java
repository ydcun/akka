package sample.remote.calculator;

import akka.actor.AbstractActor;

import java.util.Random;

public class CalculatorActor extends AbstractActor {
  @Override
  public Receive createReceive() {
    return receiveBuilder()
      .match(Op.Message.class, multiply -> {
        System.out.println("Calculating "+multiply.message);
        String result = multiply.message+"111111111111111";
          final Random r = new Random();
        Thread.sleep(r.nextInt(10)*1000);
        sender().tell(new Op.MessageResult(result), self());
      })
      .build();
  }
}
