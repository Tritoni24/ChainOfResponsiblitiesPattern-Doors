public class FireAlarm extends CodeHandler{
  String codeFireAlarm;
  public FireAlarm(String code, CodeHandler next) {
    super(next);
    codeFireAlarm = code;
  }

  @Override
  protected void handleCode(String code, Door door) {
    System.out.println("handel FireAlarm");
    if(!door.isLocked()){
      if(codeFireAlarm.equals(code)){
        door.resetState();
        door.open();
        System.out.println("Fire alarm has been activated!");
      }else{
        if(this.next !=null){
          super.handleCode(code,door);
        }
      }
    }else {
      System.out.println("You are trying to open a locked door, please first unlock it");
    }
  }
}
