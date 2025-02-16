import java.util.Scanner;// import Scanner
import java.util.ArrayList; // import the ArrayList class
import java.util.Random;


class Conversation implements Chatbot {
  //stores the conversation transcript
  ArrayList<String> transcript;
      
  //array of canned responses for when it doesn't know what to say
  private static final String[] cannedResponses = {
    "Mhm.", "Interesting!","Tell me more.", "I see.", "Go on.","I'm glad"
  };
      
  public Conversation() {
    this.transcript = new ArrayList<String>();
  }
   
  /**
  * initializes and runs the conversation with the user
  */
  public void chat() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("How many rounds? ");
           
    // Read the number of conversation rounds from the user
    int rounds = scanner.nextInt();
    scanner.nextLine(); // take the newline character
           
    System.out.println("Hi there! What's on your mind?");
       transcript.add("Hi there! What's on your mind?");
           
    // loop through the given number of rounds
    for (int i = 0; i < rounds; i++) {
      String userInput = scanner.nextLine(); // get user input
      transcript.add(userInput);
               
      // Generate a response based on user input
      String response = respond(userInput);
      System.out.println(response);
      transcript.add(response);
    }
           
    System.out.println("Thank you for chatting with me!");
    transcript.add("Thank you for chatting with me!");
    scanner.close(); // Display the full conversation transcript
  }

  /**
  * Prints the conversation transcript
  */
  public void printTranscript() {
    System.out.println("--------------------");
    System.out.println("TRANSCRIPT:");
    System.out.println("--------------------");
    for (String line : transcript) {
      System.out.println(line);
    }
  }

  /**
  * Gives appropriate response (mirrored or canned) to user input
  * @param inputString the users last line of input
  * @return mirrored or canned response to user input  
  */
  public String respond(String inputString) {
    String[] words = inputString.split(" "); // split input into it's words
    boolean mirrored = false;
           
    // iterate through words and replace if they match the mirror list
    for (int i = 0; i < words.length; i++) {
      words[i] = this.mirrorWord(words[i]);
      // If any word was replaced, mark mirrored as true
      if (!words[i].equalsIgnoreCase(inputString.split(" ")[i])) {
        mirrored = true;
      }
    }
           
    // Return mirrored sentence if applicable, otherwise return a canned response
    if (mirrored) {
      return String.join(" ", words) + "?";
    } else {
      return this.getRandomCannedResponse();
    }
  }
       
  /**
  * Mirrors specific words.
  * This method checks if a given word matches one from the mirror list and replaces it.
  * @param word the word to check
  * @return the mirrored word if applicable, otherwise the original word
  */
  private String mirrorWord(String word) {
    switch (word.toLowerCase()) {
      case "i": return "you";
      case "me": return "you";
      case "am": return "are";
      case "you": return "I";
      case "my": return "your";
      case "your": return "my";
      case "i'm": return "you're";
      case "you're": return "I'm";
      case "yourself": return "myself";
      case "myself": return "yourself";
      case "mine": return "yours";
      default: return word;
    }
  }
       
  /**
  * Selects a random canned response from the predefined list.
  * If no mirror words are detected, this method provides a fallback response.
  * @return a random canned response string
  */
  private String getRandomCannedResponse() {
    Random rand = new Random();
    return cannedResponses[rand.nextInt(cannedResponses.length)];
  }
       
   
  public static void main(String[] arguments) {
    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();
  }
}