package mibs.init.cabinet;

import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.swing.JComponent;
import javax.swing.JLabel;

import org.apache.commons.lang3.SerializationUtils;

import com.rabbitmq.client.Channel;

interface QueueHandler {
	
	String LOCALIN 						= "localin";
	
	String CMD_INIT_CABINET 			= "INIT";
	String CMD_ADD_CONCLUSION 			= "ADD_CONCLUSION";
	String CMD_ADD_EXPLORATION			= "ADD_EXPLORATION";
	String CMD_PROLONG_CABINET 			= "PROLONG";
	String CMD_BLOCK_CABINET 			= "BLOCK";
	
	
	String CMD_INITIALIZED 				= "INITIALIZED";
	String CMD_NOT_INITIALIZED 			= "NOT_INITIALIZED";
	String CMD_CONCLUSION_ADDED			= "CONCLUSION_ADDED";
	String CMD_CONCLUSION_NOT_ADDED 	= "CONCLUSION_NOT_ADDED";
	String CMD_EXPLORATION_ADDED		= "EXPLORATION_ADDED";
	
	
	
	String FIRST_NAME 					= "FIRST_NAME";
	String PATRONYMIC 					= "PATRONYMIC";
	String LAST_NAME  					= "LAST_NAME";
	String BIRTHDAY   					= "BIRTHDAY";
	String EMAIL						= "EMAIL";
	
	String RESP_CAB_INITIALIZED 		= "RESP_CAB_INITIALIZED";
	
	String BUTTON_INIT_CABINET			= "BUTTON_INIT_CABINET";
	String BUTTON_ADD_CONCLUSION 		= "BUTTON_ADD_CONCLUSION";
	String BUTTON_SEND1					= "BUTTON_SEND1";
	String BUTTON_SEND2					= "BUTTON_SEND2";
	String BUTTON_PROLONG  				= "BUTTON_PROLONG";
	String BUTTON_ADD_EXPLORATION		= "BUTTON_ADD_EXPLORATION";
	String BUTTON_PROLONG_CABINET		= "BUTTON_PROLONG_CABINET";
	String BUTTON_BLOCK_CABINET			= "BUTTON_BLOCK_CABINET";
	String CONCLUSION					= "CONCLUSION";
	String EXPLORATION					= "EXPLORATION";
	String EXPLORATIONID				= "EXPLORATIONID";
	String DATE							= "DATE";
	
	String CONNECTION_ATTEMPT      = "CONNECTION_ATTEMPT";
	String CONNECTION_ACTIVATED			= "CONNECTION_ACTIVATED";
	String ERROR_CONNECTION				= "ERROR_CONNECTION";
	String ERROR_PUBLISH				= "ERROR_PUBLISH";
	String ERROR_EMAIL					= "ERROR_EMAIL";
	String ERROR_TITLE 					= "ERROR_TITLE";
	String RESP_CONCLUSION_ADDEE		= "RESP_CONCLUSION_ADDEE";
	String RESP_EXPLORATION_ADDED		= "RESP_EXPLORATION_ADDED";
	
	Locale locale = Locale.getDefault();
	
	ResourceBundle bundle = ResourceBundleUTF8.getBundle("application");;
	
	//ResourceBundle config = ResourceBundle.getBundle("config");;
	
	String PROG_CAPTION = bundle.getString("MAIN_CAPTION");
	
	
	
	
	Map< String, Consumer< RabbitmqCommandMessage< ? extends Serializable > > > responceCommands = new TreeMap<>() ;
	
	default  void publisToLocalIN( Channel channel,RabbitmqCommandMessage< ? extends Serializable> u, BiConsumer<String, String> handleError, String s, String v ){
		try {
			channel.basicPublish("", LOCALIN, null, SerializationUtils.serialize( u ) );
		} catch (IOException e) {
			handleError.accept( s, v );
		}
	}
	
	default void initResponceCommands(  Consumer<String> messageHandler ) {
		
		responceCommands.put(CMD_INITIALIZED, ( p )->{
			Person person = (Person) p.getContent();
			String s = person.getFirstName() + "  " +  person.getPatronymic() + "  " + person.getLastName() ;
			String message = MessageFormat.format(bundle.getString(RESP_CAB_INITIALIZED), s);
			messageHandler.accept( message );
			
		});
		responceCommands.put(CMD_CONCLUSION_ADDED, ( p )->{
			Conclusion conclusion = ( Conclusion ) p.getContent();
			
			String s = conclusion.getConclusionName() ;
			
			String message = MessageFormat.format(bundle.getString(RESP_CONCLUSION_ADDEE), s);
			
			messageHandler.accept( message );
			
		});
		responceCommands.put( CMD_EXPLORATION_ADDED, ( p )->{
			Exploration exploration = ( Exploration ) p.getContent();
			
			String s = exploration.getFolderName() ;
			
			String message = MessageFormat.format(bundle.getString( RESP_EXPLORATION_ADDED ), s);
			
			messageHandler.accept( message );
			
		});
		
		
		
	}
	
	
	
}
