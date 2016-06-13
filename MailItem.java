
/**
 * Write a description of class MailClient here.
 * 
 * @author Francisco
 */
public class MailItem
{
    // instance variables - replace the example below with your own
    private String from;
    private String to;
    private String subject;
    private String message;


    /**
     * Constructor for objects of class MailClient
     */
    public MailItem(String from, String to, String subject, String message) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
    
     public String  subject() {
        return subject;
    } 

    public String getMessage() {
        return message;
    }
    
    public void print(){
        System.out.println(" Mensaje de: " +from+ "\n Para: " +to
        + "\n Asunto: " +subject+ "\n Mensaje: " +message);
    }
}
































