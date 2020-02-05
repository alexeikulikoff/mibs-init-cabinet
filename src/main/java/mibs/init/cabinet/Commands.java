package mibs.init.cabinet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.function.Consumer;

import javax.swing.JLabel;

import org.apache.commons.lang3.SerializationUtils;

import com.rabbitmq.client.Channel;

interface Commands {
	String CMD_INIT_CABINET = "INIT";
	String CMD_INITIALIZED = "INITIALIZED";
	String CMD_ADD_CONCLUSION = "ADD_CONCLUSION";
	String CMD_PROLONG_CABINET = "PROLONG";
	String CMD_BLOCK_CABINET = "BLOCK";
	String CMD_PING = "PING";
	
	String FIRST_NAME  = "FIRST_NAME";
	String SECOND_NAME = "SECOND_NAME";
	String LAST_NAME   = "LAST_NAME";
	String BIRTHDAY    = "BIRTHDAY";
	String EMAIL		= "EMAIL";
	
	String RESP_CAB_INITIALIZED = "RESP_CAB_INITIALIZED";
	
	String BUTTON_INIT_CABINET = "BUTTON_INIT_CABINET";
	String BUTTON_ADD_CONCLUSION = "BUTTON_ADD_CONCLUSION";
	
	String MAIN_CAPTION 	= "MAIN_CAPTION";
	
	String host = "172.16.29.255";
	String login = "admin2";
	String password="kukla";
	
	ResourceBundle bundle = ResourceBundle.getBundle("application");;
	
	Map< String, Consumer< RabbitmqCommandMessage< Person > > > responceCommands = new TreeMap<>() ;
	Map< String, Consumer< RabbitmqCommandMessage< Person > > > actionCommands = new TreeMap<>() ;
	
	default void initActionCommands( Channel channel, JLabel label ) {
		actionCommands.put(CMD_INIT_CABINET, ( u )->{
			try {
				channel.basicPublish("", "localin", null, SerializationUtils.serialize( u ) );
			} catch (IOException e) {
				label.setText("Error while publishing data to queue");
			}
		});
		actionCommands.put(CMD_ADD_CONCLUSION, ( u )->{
			System.out.println(u);
		});

		
	}
	default void initResponceCommands( JLabel label) {
	
		
		responceCommands.put(CMD_INITIALIZED, ( p )->{
			Person person = p.getContent();
			String s = person.getFirstName() + "  " + person.getSerName() + "  " + person.getSerName();
			String text = MessageFormat.format(bundle.getString(RESP_CAB_INITIALIZED), s);
			label.setText( text );
		});
		
	}
	
	
	
}
