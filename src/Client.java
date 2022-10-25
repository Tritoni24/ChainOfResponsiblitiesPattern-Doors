import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Client {
  public static final int MAX_ATTEMPTS = 3;
  public static void main(String[] args) throws IOException {
    String openCode = "1111";
    String fireAlarmCode = "2222";
    String unlockCode = "3333";
    CodeHandler chain1 = new Log(new Unlock(unlockCode, new FireAlarm(fireAlarmCode, new Open(openCode, new Lock(null)))));
    CodeHandler chain2 = new Log(new Open(openCode, null));
    CodeHandler chain3 = new Log(new FireAlarm(fireAlarmCode, new Open(openCode, null)));
    Door door = new Door("d1", chain1);
    List<CodeHandler> chains = new ArrayList<>();
    chains.add(chain1);
    chains.add(chain2);
    chains.add(chain3);
    doorsTest(door,chains);

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
  public static void doorsTest(Door door, List<CodeHandler> chains){
    for (int i=0; i < chains.size(); i++){
      System.out.println("TEST CHAIN "+ (i+1) + " #################################################");
      door.setCodeHandler(chains.get(i));
      door.resetState();
      door.setLocked(false);
      if(i == 0){
        System.out.println("Using code 1111, this code should open-------------------------");
        door.processCode("1111"); // opens
        System.out.println("Using code 2222, this code should open and fire alarm----------");
        door.processCode("2222"); // opens and fires alarm
        System.out.println("Using code 1234, this code should fail-------------------------");
        door.processCode("1234"); // first trial
        System.out.println("Using code 4321, this code should fail-------------------------");
        door.processCode("4321"); // second trial
        System.out.println("Using code 5555, this code should fail and lock----------------");
        door.processCode("5555"); // third trial, gets locked
        System.out.println("Using code 6666, this code should fail-------------------------");
        door.processCode("6666"); // invalid unlock code
        System.out.println("Using code 7777, this code should fail-------------------------");
        door.processCode("7777"); // invalid unlock code
        System.out.println("Using code 1111, this code shouldn't open, door blocked--------");
        door.processCode("1111"); // invalid unlock code
        System.out.println("Using code 3333, this code should unlock-----------------------");
        door.processCode("3333"); // valid unlock code, now can be opened or fire alarm
        System.out.println("Using code 2222, this code should open and fire alarm----------");
        door.processCode("2222"); // opens and fires alarm
      } else if (i == 1) {
        System.out.println("Using code 1111, this code should open-------------------------");
        door.processCode("1111"); // opens
        System.out.println("Using code 2222, this code should fail-------------------------");
        door.processCode("2222"); // opens and fires alarm
        System.out.println("Using code 3333, this code should fail-------------------------");
        door.processCode("3333"); // first trial
        System.out.println("Using code 4444, this code should fail but shouldn't block-----");
        door.processCode("4444"); // second trial
        System.out.println("Using code 1111, this code should open-------------------------");
        door.processCode("1111"); // third trial, gets locked
      } else {
        System.out.println("Using code 1111, this code should open-------------------------");
        door.processCode("1111"); // opens
        System.out.println("Using code 2222, this code should open and fire alarm----------");
        door.processCode("2222"); // opens and fires alarm
        System.out.println("Using code 3333, this code should fail-------------------------");
        door.processCode("3333"); // first trial
        System.out.println("Using code 4444, this code should fail-------------------------");
        door.processCode("4444"); // second trial
        System.out.println("Using code 5555, this code should fail but shouldn't lock------");
        door.processCode("5555"); // third trial, gets locked
        System.out.println("Using code 1111, this code should open-------------------------");
        door.processCode("1111"); // invalid unlock code
        System.out.println("Using code 2222, this code should open and fire alarm----------");
        door.processCode("2222"); // invalid unlock code

      }

    }
  }
}

