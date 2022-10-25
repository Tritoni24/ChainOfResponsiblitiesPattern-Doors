public class Unlock extends CodeHandler{
  String codeUnlock;
  public Unlock(String code, CodeHandler next) {
    super(next);
    codeUnlock = code;
  }

  @Override
  protected void handleCode(String code, Door door) {
    System.out.println("handel Unlock");
    if(door.isLocked() && codeUnlock.equals(code)){
        door.resetState();
        door.setLocked(false);
        System.out.println("Door "+door.getId()+" has been unlocked");
    }else{
      if(this.next !=null){
        super.handleCode(code,door);
      }
    }
  }
}
