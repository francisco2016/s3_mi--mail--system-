
/**
 * Write a description of class MailClient here.
 *Septiembre 2016.
 */
public class MailClient
{
    // representa el servidor asociado con ese cliente
    private MailServer server;
    // que representa la dirección de correo del usuario que usa ese cliente.
    private String user;

    /**
     * Constructor for objects of class MailClient
     */
    public MailClient(MailServer server, String user)
    {
        //server = new MailServer();
        this.server = server; 
        this.user = user; //representa la dirección de correo del usuario que usa ese cliente
    }
    
    /**
     *  recupera del servidor el siguiente correo (un objetoMailItem) que tenga el
     * usuario y devuelva dicho objeto
     */
    public MailItem getNextMailItem (){
        return server.getNextMailItem(user);
    }
    
    /**
     * recupera del servidor el siguiente correo (un objetoMailItem) que tenga el
     * usuario e imprima por pantalla los datos de dicho mensaje. Si no hay ningun mensaje, que muestre un 
     * mensaje por pantalla informando de ello.
     */
    public void printNextMailItem(){
        MailItem email = server.getNextMailItem(user);
        if(email == null){
             System.out.println("No new mail.");
        }
        else{
              email.print();
        }
    }
    
    /**
     *  reciba 2 parámetros (un String indicando para quién es el mensaje y otro String indicando el 
     *  contenido del mensaje), cree un email (objeto MailItem) basándose en la información de dichos 
     *  parámetros y lo envíe al servidor asociado a ese cliente.
     */
    public void sendMailItem(String para, String mensaje){
        MailItem email = new MailItem(user, para, mensaje);
        server.post(email);
    }  
}



















