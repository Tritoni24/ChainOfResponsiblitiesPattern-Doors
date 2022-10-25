import java.time.LocalDateTime;

public class Log extends CodeHandler{
  public Log(CodeHandler next) {
    super(next);
  }

  @Override
  protected void handleCode(String code, Door door) {
    System.out.println("handle Log");
    System.out.println("Input code " + code + " at door " + door.getId() + " at " + LocalDateTime.now());
    if(this.next !=null){
      super.handleCode(code,door);
    }
  }
}
