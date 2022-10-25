public abstract class CodeHandler {
  CodeHandler next;
  public CodeHandler(CodeHandler next){
    this.next= next;
  }
  protected void handleCode(String code, Door door){
    next.handleCode(code,door);
  }

}
