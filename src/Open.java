public class Open extends CodeHandler {
  private String codeOpen;
  public Open(String code, CodeHandler nextHandler) {
    super(nextHandler);
    codeOpen = code;
  }
  @Override
  public void handleCode(String code, Door door) {
    System.out.println("handle Open");
    if (!doorLocked) {
      if (codeOpen.equals(code)) {
        resetDoorState();
        door.open();
      } else {
        numTrials++;
        System.out.println(numTrials + " trials");
        super.handleCode(code, door);
      }
    } else { /* void, can't open until unlocked */ }
  }
}