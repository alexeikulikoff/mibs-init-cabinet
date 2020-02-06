package mibs.init.cabinet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.function.Consumer;

import javax.swing.JLabel;

import org.apache.commons.lang3.SerializationUtils;

import com.rabbitmq.client.Channel;

interface Commands {
	String CMD_INIT_CABINET 			= "INIT";
	String CMD_INITIALIZED 				= "INITIALIZED";
	String CMD_ADD_CONCLUSION 			= "ADD_CONCLUSION";
	String CMD_PROLONG_CABINET 			= "PROLONG";
	String CMD_BLOCK_CABINET 			= "BLOCK";
	String CMD_PING 					= "PING";
	
	String FIRST_NAME 					= "FIRST_NAME";
	String SECOND_NAME 					= "SECOND_NAME";
	String LAST_NAME  					= "LAST_NAME";
	String BIRTHDAY   					= "BIRTHDAY";
	String EMAIL						= "EMAIL";
	
	String RESP_CAB_INITIALIZED 		= "RESP_CAB_INITIALIZED";
	
	String BUTTON_INIT_CABINET			= "BUTTON_INIT_CABINET";
	String BUTTON_ADD_CONCLUSION 		= "BUTTON_ADD_CONCLUSION";
	
	String CONNECTION_ACTIVATED			= "CONNECTION_ACTIVATED";
	String ERROR_CONNECTION				= "ERROR_CONNECTION";
	String ERROR_PUBLISH				= "ERROR_PUBLISH";
	
	Locale locale = Locale.getDefault();
	
	ResourceBundle bundle = ResourceBundleUTF8.getBundle("application");;
	
	ResourceBundle config = ResourceBundle.getBundle("config");;
	
	String PROG_CAPTION = bundle.getString("MAIN_CAPTION");
	
	String host = config.getString("rabbitmq-host");
	String login =config.getString("rabbitmq-login");
	String password =config.getString("rabbitmq-password");
	
	
	Map< String, Consumer< RabbitmqCommandMessage< Person > > > responceCommands = new TreeMap<>() ;
	Map< String, Consumer< RabbitmqCommandMessage< Person > > > actionCommands = new TreeMap<>() ;
	

	default void initActionCommands( Channel channel, JLabel label ) {
		actionCommands.put(CMD_INIT_CABINET, ( u )-> {
		
			try {
				channel.basicPublish("", "localin", null, SerializationUtils.serialize( u ) );
			} catch (IOException e) {
				label.setText( MessageFormat.format(bundle.getString(ERROR_PUBLISH), channel.getConnection().toString()) );
			}
		});
		actionCommands.put(CMD_ADD_CONCLUSION, ( u )->{
			System.out.println(u);
		});

		
	}
	default void initResponceCommands( JLabel label) {
		
		responceCommands.put(CMD_INITIALIZED, ( p )->{
			Person person = p.getContent();
			String s = person.getFirstName() + "  " + person.getLastName() + "  " + person.getSerName();
			String text = MessageFormat.format(bundle.getString(RESP_CAB_INITIALIZED), s);
			label.setText( text );
			
		});
		
	}
	
	
	
}
