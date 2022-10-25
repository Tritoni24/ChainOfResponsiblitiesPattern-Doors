public class Door {
  private String id;
  private CodeHandler codeHandler;
  private int numTrials;
  private boolean locked;
  public Door(String ident, CodeHandler ch) {
    id = ident;
    codeHandler = ch;
    locked = false;
  }

  public boolean isLocked(){return locked;}

  public void setLocked(boolean locked){this.locked = locked;}
  public int getNumTrials(){return numTrials;}
  public void incrementNumTrials(){numTrials++;}
  public void processCode(String code) { codeHandler.handleCode(code, this); }
  public void open() { System.out.println("door " + id + " opened"); }

  public String getId() { return id; }
  public void setCodeHandler(CodeHandler ch) { codeHandler = ch; }
  public void resetState(){numTrials = 0;}
}
