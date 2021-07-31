package io.hari.springstatemachine;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccessor;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Service;

//@RequiredArgsConstructor
@Service
public class MyService {
    @Autowired
    StateMachineFactory<States, Events> factory;

    public void changeEntityEvent() {
        SampleEntity entity = SampleEntity.builder().name("sample1").currentState(States.STATE_MID).build();

        //stop state machine
        //send my entity STATE to state machine
        //start state machine

        StateMachine<States, Events> stateMachine = factory.getStateMachine(entity.getName());
        System.out.println("28 CURRENT STATE = " + stateMachine.getState().getId());//STATE_START
        stateMachine.stop();
        System.out.println("30 CURRENT STATE = " + stateMachine.getState().getId());//--"--

        StateMachineAccessor<States, Events> accessor = stateMachine.getStateMachineAccessor();
        System.out.println("33 CURRENT STATE = " + stateMachine.getState().getId());//--"--
        accessor.doWithAllRegions(stateMachineAccess -> {

            //add interceptor/callback/handler : m1
            stateMachineAccess.addStateMachineInterceptor(new StateMachineInterceptorAdapter<States, Events>(){
                @Override
                public void preStateChange(State<States, Events> state, Message<Events> message, Transition<States, Events> transition, StateMachine<States, Events> stateMachine) {
                    System.out.println("preStateChange BODY ----- ");
                    //::save new entity state into db
                    //get entity object (use repository instance)
                    //set new state
                    //save back to db
                    super.preStateChange(state, message, transition, stateMachine);
                }
            });
            //add interceptor : m2: use bean of type SM Interceptor Adapter, for this create a class + extends StateMachineInterceptorAdapter + add above body
//            stateMachineAccess.addStateMachineInterceptor(interceptor bean);

            System.out.println("35 CURRENT STATE = " + stateMachine.getState().getId());//--"--
            System.out.println("--");
            System.out.println("37 CURRENT STATE = " + stateMachine.getState().getId());//--"--
            DefaultStateMachineContext newMachineContext = new DefaultStateMachineContext<>(entity.currentState, null, null, null);
            System.out.println("39 CURRENT STATE = " + stateMachine.getState().getId());//--"--
            stateMachineAccess.resetStateMachine(newMachineContext);
            System.out.println("41 CURRENT STATE = " + stateMachine.getState().getId());//STATE_MID, and above all STATE_START
            System.out.println("---");
        });

        System.out.println("45 CURRENT STATE = " + stateMachine.getState().getId());//--"--
        stateMachine.start();
        System.out.println("47 CURRENT STATE = " + stateMachine.getState().getId());//--"--

        //send event to state machine
        stateMachine.sendEvent(Events.EVENT_MID_TO_END);
        System.out.println("50 CURRENT STATE = " + stateMachine.getState().getId());//STATE_END and above all STATE_MID
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
class SampleEntity {
    String name;
    States currentState;
}
