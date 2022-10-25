public class Lock extends CodeHandler {
  public Lock(CodeHandler next) {
    super(next);
  }

  @Override
  protected void handleCode(String code, Door door) {
    System.out.println("handle Lock");
    if(door.getNumTrials()>=Client.MAX_ATTEMPTS){
      door.setLocked(true);
      System.out.println("Door "+ door.getId()+" has been locked");
    }
    if(this.next !=null){
      super.handleCode(code,door);
    }
  }
}
