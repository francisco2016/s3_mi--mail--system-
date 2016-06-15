
/**
 * Write a description of class MailClient here.
 *Septiembre 2016.
 */
public class MailClient
{
    // representa el servidor asociado con ese cliente
    private MailServer server;
    // representa la dirección de correo del usuario que usa ese cliente.
    private String user;
    // para almacenar el últimoa email recibido.
    private MailItem ultimoEmail;
    // para facilitar la detección de Spam.
    private boolean spam; 
    //contadores para los emails recibidos, enviados y con spam.
    private int enviados;
    private int recibidos;
    private int conSpam;
    // at para almacenar la dirección del mensaje más largo.
    private String direccionMeilLargo;
    private String meilLargo;

    /**
     * Constructor for objects of class MailClient
     */
    public MailClient(MailServer server, String user)
    { 
        this.server = server; 
        this.user = user; //representa la dirección de correo del usuario que usa ese cliente.
        ultimoEmail = null;
        spam = false;
        enviados = 0;
        recibidos = 0;
        conSpam = 0;
         meilLargo = "";
        direccionMeilLargo ="";
        
    }

    /**
     *  recupera del servidor el siguiente correo (un objetoMailItem) que tenga el
     * usuario y devuelva dicho objeto
     */
    public MailItem getNextMailItem (){
        MailItem email = server.getNextMailItem(user);
        if(email != null){ 
            String mens = email.getMessage();
            if(mens.length() > meilLargo.length()){
                meilLargo = mens;
                direccionMeilLargo = email.getFrom();
            }
            
            if(mens.contains("regalo") || mens.contains("promocion")){
                // spam = true;
                if(mens.contains("trabajo")){
                    spam = false;
                    ultimoEmail = email;
                }
                else {
                    spam = true;
                    email = null;
                    conSpam  += 1;
                }
            }
            else{
                spam = false;
                ultimoEmail = email;
            }                
            recibidos += 1;
        }   

        return email;
    }

    /**
     * recupera del servidor el siguiente correo (un objetoMailItem) que tenga el
     * usuario e imprima por pantalla los datos de dicho mensaje. Si no hay ningun mensaje, que muestre un 
     * mensaje por pantalla informando de ello.
     */
    public void printNextMailItem(){
        MailItem mensaje = getNextMailItem ();
        if(mensaje != null){
            ultimoEmail = mensaje;
            mensaje.print();
        }
        else{
            if(spam == true){
                System.out.println("Solo tienes Spam");
                System.out.println("--                           --");
            }
            else{
                // Si no imprimimos que no hay ningun mensaje en la bandeja de entrada.
                System.out.println("No hay ningun mensaje en la bandeja");
                System.out.println("--                           --");
            }
        }
    }

    /**
     *Muestra por pantalla el nº de emails recibidos. 
     */
    public void cuantosEmailsTenemos(){
        if(server.howManyMailItems(user) == 0){
            System.out.println("No tiene ningún mensaje.");
        }
        else{
            System.out.println("Tiene: " +server.howManyMailItems(user)+ " mensajes sin leer.");
        }
    }

    /**
     *  reciba 2 parámetros (un String indicando para quién es el mensaje y otro String indicando el 
     *  contenido del mensaje), cree un email (objeto MailItem) basándose en la información de dichos 
     *  parámetros y lo envíe al servidor asociado a ese cliente.
     */
    public void sendMailItem(String para, String asunto, String mensaje){
        MailItem email = new MailItem(user, para, asunto, mensaje);
        server.post(email);
        enviados += 1;
    }  

    /**
     *  que obtenga del servidor el siguiente mensaje del usuario y responda automáticamente al emisor indicando que estamos 
     *  fuera de la oficina
     */
    public void getNextMailItemAndSendAutomaticRespond(){
        MailItem email = getNextMailItem( );
        ultimoEmail = email;
        if(email != null && spam == false){
            sendMailItem( email.getFrom(),"RE "  +email.getSubject(), "  No estamos en la oficina. \n "  +email.getMessage());
        }
        enviados += 1;
    }

    /**
     * Finalmente, consigue que desde los clientes de correo electrónico podamos ver por pantalla cuantas veces 
     * queramos los datos del último mensaje recibido. Si no se hubiera recibido aun ningún mensaje, entonces se debe 
     * informar de ello por pantalla.
     */
    public void seeLatestMail(){
        if(ultimoEmail != null){
            ultimoEmail.print();
        }       
    }

    /**
     * 0 150-- 2.   El cliente de correo electrónico a través de un método llamado showStats sea capaz de mostrar por pantalla 
     * unas estadísticas que incluyan el número de mensajes enviados, el número de mensajes recibidos, el número de mensajes que
     * son spam, el porcentaje de spam y la dirección de la persona que nos envío el mensaje más largo junto con cuantos
     * caracteres tenía ese mensaje.
     */ 
    public void showStats(){
        System.out.println("--                           --");
        System.out.println("Ha recivido " +recibidos+ " mensajes.");
        System.out.println("Ha enviado " +enviados+ " mensajes.");
        System.out.println("Ha recivido " +conSpam+ " mensajes con spam.");
        if(conSpam != 0){
            System.out.println("Porcentaje de Spam: " +100*conSpam/recibidos);
            System.out.println("--                           --");
        }
        else{
             System.out.println("Porcentaje de Spam: 0%"  );
             System.out.println("--                           --");
        }
        //la dirección de la persona que nos envío el mensaje más largo junto con cuantos caracteres
        //tenía ese mensaje. 
         if(direccionMeilLargo != null){
             System.out.println("El mensaje más largo tiene: " +meilLargo.length()+ " caracteres.\nla direccion es: " +
                                        direccionMeilLargo   );     
        }
    
     }
}







