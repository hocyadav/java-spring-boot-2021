package io.hari.springstatemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;
import java.util.UUID;

//@RequiredArgsConstructor //not working with command line runner
@SpringBootApplication
class SpringStateMachineApplication implements CommandLineRunner {
//	@Autowired
//	StateMachine<States, Events> stateMachine;
	@Autowired
	StateMachineFactory<States, Events> factory;

	public static void main(String[] args) {
		SpringApplication.run(SpringStateMachineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		StateMachine<States, Events> stateMachine = factory.getStateMachine(UUID.randomUUID());
		System.out.println("id = " + stateMachine.getState().getId());

		stateMachine.sendEvent(Events.EVENT_START_TO_MID);
		System.out.println("id = " + stateMachine.getState().getId());

		stateMachine.sendEvent(Events.EVENT_MID_TO_END);
		System.out.println("id = " + stateMachine.getState().getId());
	}
}
//@EnableStateMachine //working
@EnableStateMachineFactory //working
@Configuration
class MyStateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
	@Override
	public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {

		//m1 create listener object
		StateMachineListenerAdapter<States, Events> listener = new StateMachineListenerAdapter<>() {
			@Override
			public void stateChanged(State<States, Events> from, State<States, Events> to) {
				System.out.println("LISTENER : State change to " + to.getId());
			}
		};

		config.withConfiguration()
				.autoStartup(true)
//				.listener(listener);//m1 : working
				.listener(listenerBean());//m2 : working
	}

	@Override
	public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
		states.withStates()
				.initial(States.STATE_START)
//				.end(States.STATE_END)//optional
				.states(EnumSet.allOf(States.class));
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
		transitions.withExternal()
				.source(States.STATE_START).target(States.STATE_MID).event(Events.EVENT_START_TO_MID)
				.and()
				.withExternal()
				.source(States.STATE_MID).target(States.STATE_END).event(Events.EVENT_MID_TO_END);
	}

	@Bean//m2 : listener bean
	public StateMachineListener<States, Events> listenerBean() {
		return new StateMachineListenerAdapter<States, Events>() {
			@Override
			public void stateChanged(State<States, Events> from, State<States, Events> to) {
				System.out.println("LISTENER : State change to " + to.getId());
			}
		};
	}
}
enum States {STATE_START, STATE_MID, STATE_END;}
enum Events {EVENT_START_TO_MID, EVENT_MID_TO_END;}

//below are not part of state machine config - this is required to listen event and do some processing (act like Handler/Inerceptor)
//@RequiredArgsConstructor
//@Component
//class ListenerHandler extends StateMachineInterceptorAdapter<States, Events> {
//	@Override
//	public void preStateChange(State<States, Events> state,
//							   Message<Events> message,
//							   Transition<States, Events> transition,
//							   StateMachine<States, Events> stateMachine) {
//
//		System.out.println("ListenerHandler.preStateChange");
//		Optional.ofNullable(message).ifPresent(eventsMessage -> {
//			Events payload = message.getPayload();
//			System.out.println("payload = " + payload);
//		});
//	}
//}
