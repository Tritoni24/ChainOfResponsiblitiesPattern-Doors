import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Client {
  public static final int MAX_ATTEMPTS = 3;
  public static void main(String[] args) throws IOException {
    String openCode = "1111";
    String fireAlarmCode = "2222";
    String unlockCode = "3333";
    CodeHandler chain1 =
        new Log(
            new Unlock(unlockCode,
              new FireAlarm(fireAlarmCode,
                  new Open(openCode,
                      new Lock(null)))));
    CodeHandler chain2 = new Log(new Open(openCode, null));
    CodeHandler chain3 = new Log(
            new FireAlarm(fireAlarmCode,
                new Open(openCode, null)));
    Door d1 = new Door("d1", chain1);
    Door d2 = new Door("d2", chain2);
    Door d3 = new Door("d3", chain3);
    List<Door> doors = new LinkedList<>();
    doors.add(d1);
    doors.add(d2);
    doors.add(d3);
    for (int i=1; i <= 3; i++){
      System.out.println("TEST CHAIN "+ i + "------------------------------------------------------");
      d1.processCode("1111"); // opens
      d1.processCode("2222"); // opens and fires alarm
      d1.processCode("1234"); // first trial
      d1.processCode("4321"); // second trial
      d1.processCode("5555"); // third trial, gets locked
      d1.processCode("6666"); // invalid unlock code
      d1.processCode("7777"); // invalid unlock code
      d1.processCode("1111"); // invalid unlock code
      d1.processCode("3333"); // valid unlock code, now can be opened or fire alarm
      d1.processCode("2222"); // opens and fires alarm
      d1.setCodeHandler(chain2);
      d1.resetState();
      d1.setLocked(false);
    }

// change behaviour of door d1 at run time
//    d1.setCodeHandler(chain2);
//    BufferedReader stdin = new BufferedReader(
//        new InputStreamReader(System.in));
//    while (true) {
//      System.out.print("Input code : ");
//      String inputCode = stdin.readLine();
//      d1.processCode(inputCode);
//    }
  }
}