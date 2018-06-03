package sample.remote.calculator;

import java.io.Serializable;

public class Op {

  public interface MathOp extends Serializable {
  }

  public interface MathResult extends Serializable {
  }

  static class Message implements MathOp {
    String message;

    public Message(String message) {
      this.message = message;
    }
  }

  static class MessageResult implements MathResult {
    String message;

    public MessageResult(String message) {
      this.message = message;
    }
  }
}
