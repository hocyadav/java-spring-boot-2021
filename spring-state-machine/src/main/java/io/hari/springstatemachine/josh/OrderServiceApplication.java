package io.hari.springstatemachine.josh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}

enum OrderEvents {
    FULFILL,
    PAY,
    CANCEL
}

enum OrderStates {
    SUBMITTED,
    PAID,
    FULFILLED,
    CANCELLED

}

@Service
class OrderService {
    private final OrderRepository orderRepository;
    private final StateMachineFactory<OrderStates, OrderEvents> factory;
    private static final String ORDER_ID_HEADER = "orderId";

    OrderService(OrderRepository orderRepository, StateMachineFactory<OrderStates, OrderEvents> factory) {
        this.orderRepository = orderRepository;
        this.factory = factory;
    }

    Order byId(Long id) {
        return this.orderRepository.findById(id).get();
    }

    Order create(Date when) {
        return this.orderRepository.save(new Order(when, OrderStates.SUBMITTED));
    }

    StateMachine<OrderStates, OrderEvents> pay(Long orderId, String paymentConfirmationNumber) {
        StateMachine<OrderStates, OrderEvents> sm = this.build(orderId);

        Message<OrderEvents> paymentMessage = MessageBuilder.withPayload(OrderEvents.PAY)
                .setHeader(ORDER_ID_HEADER, orderId)
                .setHeader("paymentConfirmationNumber", paymentConfirmationNumber)
                .build();

        sm.sendEvent(paymentMessage);
        // todo
        return sm;
    }

    StateMachine<OrderStates, OrderEvents> fulfill(Long orderId) {
        StateMachine<OrderStates, OrderEvents> sm = this.build(orderId);
        Message<OrderEvents> fulfillmentMessage = MessageBuilder.withPayload(OrderEvents.FULFILL)
                .setHeader(ORDER_ID_HEADER, orderId)
                .build();
        sm.sendEvent(fulfillmentMessage);
        return sm;
    }

    private StateMachine<OrderStates, OrderEvents> build(Long orderId) {
        Order order = this.orderRepository.findById(orderId).get();
        String orderIdKey = Long.toString(order.getId());

        StateMachine<OrderStates, OrderEvents> sm = this.factory.getStateMachine(orderIdKey);
        sm.stop();
        sm.getStateMachineAccessor()
                .doWithAllRegions(sma -> {

                    sma.addStateMachineInterceptor(new StateMachineInterceptorAdapter<OrderStates, OrderEvents>() {

                        @Override
                        public void preStateChange(State<OrderStates, OrderEvents> state, Message<OrderEvents> message, Transition<OrderStates, OrderEvents> transition, StateMachine<OrderStates, OrderEvents> stateMachine) {

                            Optional.ofNullable(message).ifPresent(msg -> {

                                Optional.ofNullable(Long.class.cast(msg.getHeaders().getOrDefault(ORDER_ID_HEADER, -1L)))
                                        .ifPresent(orderId1 -> {
                                            Order order1 = orderRepository.findById(orderId1).get();
                                            order1.setOrderState(state.getId());
                                            orderRepository.save(order1);
                                        });
                            });

                        }
                    });
                    sma.resetStateMachine(new DefaultStateMachineContext<>(order.getOrderState(), null, null, null));
                });
        sm.start();
        return sm;
    }
}

@Log
@Component
class Runner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Order order = this.orderService.create(new Date());

        StateMachine<OrderStates, OrderEvents> paymentStateMachine = orderService.pay(order.getId(), UUID.randomUUID().toString());
        log.info("after calling pay(): " + paymentStateMachine.getState().getId().name());
        log.info("order: " + orderService.byId(order.getId()));

        StateMachine<OrderStates, OrderEvents> fulfilledStateMachine = orderService.fulfill(order.getId());
        log.info("after calling fulfill(): " + fulfilledStateMachine.getState().getId().name());
        log.info("order: " + orderService.byId(order.getId()));


    }

    private final OrderService orderService;

    Runner(OrderService orderService) {
        this.orderService = orderService;
    }
}

interface OrderRepository extends JpaRepository<Order, Long> {
}

@Entity(name = "ORDERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
class Order {
    @Id
    @GeneratedValue
    private Long id;
    private Date datetime;

    private String state;

    public Order(Date d, OrderStates os) {
        this.datetime = d;
        this.setOrderState(os);
    }

    public OrderStates getOrderState() {
        return OrderStates.valueOf(this.state);
    }

    public void setOrderState(OrderStates s) {
        this.state = s.name();
    }
}

@Log
@Configuration
@EnableStateMachineFactory
class SimpleEnumStatemachineConfiguration extends StateMachineConfigurerAdapter<OrderStates, OrderEvents> {

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStates, OrderEvents> transitions) throws Exception {
        transitions
                .withExternal().source(OrderStates.SUBMITTED).target(OrderStates.PAID).event(OrderEvents.PAY)
                .and()
                .withExternal().source(OrderStates.PAID).target(OrderStates.FULFILLED).event(OrderEvents.FULFILL)
                .and()
                .withExternal().source(OrderStates.SUBMITTED).target(OrderStates.CANCELLED).event(OrderEvents.CANCEL)
                .and()
                .withExternal().source(OrderStates.PAID).target(OrderStates.CANCELLED).event(OrderEvents.CANCEL);
    }

    @Override
    public void configure(StateMachineStateConfigurer<OrderStates, OrderEvents> states) throws Exception {
        states
                .withStates()
                .initial(OrderStates.SUBMITTED)
                /*.stateEntry(OrderStates.SUBMITTED, context -> {
                    Long orderId = Long.class.cast(context.getExtendedState().getVariables().getOrDefault("orderId", -1L));
                    log.info("orderId is " + orderId + ".");
                    log.info("entering submitted state!");
                })*/
                .state(OrderStates.PAID)
                .end(OrderStates.FULFILLED)
                .end(OrderStates.CANCELLED);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderStates, OrderEvents> config) throws Exception {

        StateMachineListenerAdapter<OrderStates, OrderEvents> adapter = new StateMachineListenerAdapter<OrderStates, OrderEvents>() {
            @Override
            public void stateChanged(State<OrderStates, OrderEvents> from, State<OrderStates, OrderEvents> to) {
                log.info(String.format("stateChanged(from: %s, to: %s)", from + "", to + ""));
            }
        };
        config.withConfiguration()
                .autoStartup(false)
                .listener(adapter);
    }
}
/**
 *

 2021-07-31 18:10:30.845  INFO 52652 --- [           main] o.s.b.web.embedded.netty.NettyWebServer  : Netty started on port 8080
 2021-07-31 18:10:30.853  INFO 52652 --- [           main] i.h.s.josh.OrderServiceApplication       : Started OrderServiceApplication in 3.489 seconds (JVM running for 3.878)
 2021-07-31 18:10:30.923  INFO 52652 --- [           main] o.s.s.support.LifecycleObjectSupport     : started ObjectState [getIds()=[SUBMITTED], getClass()=class org.springframework.statemachine.state.ObjectState, hashCode()=1275670724, toString()=AbstractState [id=SUBMITTED, pseudoState=org.springframework.statemachine.state.DefaultPseudoState@75c0cd39, deferred=[], entryActions=[], exitActions=[], stateActions=[], regions=[], submachine=null]]
 2021-07-31 18:10:30.924  INFO 52652 --- [           main] o.s.s.support.LifecycleObjectSupport     : started org.springframework.statemachine.support.DefaultStateMachineExecutor@7455204c
 2021-07-31 18:10:30.926  INFO 52652 --- [           main] o.s.s.support.LifecycleObjectSupport     : started SUBMITTED CANCELLED FULFILLED PAID  / SUBMITTED / uuid=c4ecf6a2-0efe-4b83-876e-0e9aedb0c6d6 / id=null
 2021-07-31 18:10:30.942  INFO 52652 --- [           main] .s.j.SimpleEnumStatemachineConfiguration : stateChanged(from: ObjectState [getIds()=[SUBMITTED], getClass()=class org.springframework.statemachine.state.ObjectState, hashCode()=1275670724, toString()=AbstractState [id=SUBMITTED, pseudoState=org.springframework.statemachine.state.DefaultPseudoState@75c0cd39, deferred=[], entryActions=[], exitActions=[], stateActions=[], regions=[], submachine=null]], to: ObjectState [getIds()=[PAID], getClass()=class org.springframework.statemachine.state.ObjectState, hashCode()=280226418, toString()=AbstractState [id=PAID, pseudoState=null, deferred=[], entryActions=[], exitActions=[], stateActions=[], regions=[], submachine=null]])
 2021-07-31 18:10:30.943  INFO 52652 --- [           main] io.hari.springstatemachine.josh.Runner   : after calling pay(): PAID
 2021-07-31 18:10:30.944  INFO 52652 --- [           main] io.hari.springstatemachine.josh.Runner   : order: Order(id=1, datetime=2021-07-31 18:10:30.855, state=PAID)
 2021-07-31 18:10:30.945  INFO 52652 --- [           main] o.s.s.support.LifecycleObjectSupport     : started ObjectState [getIds()=[PAID], getClass()=class org.springframework.statemachine.state.ObjectState, hashCode()=710973468, toString()=AbstractState [id=PAID, pseudoState=null, deferred=[], entryActions=[], exitActions=[], stateActions=[], regions=[], submachine=null]]
 2021-07-31 18:10:30.946  INFO 52652 --- [           main] o.s.s.support.LifecycleObjectSupport     : started org.springframework.statemachine.support.DefaultStateMachineExecutor@3f1fb139
 2021-07-31 18:10:30.946  INFO 52652 --- [           main] o.s.s.support.LifecycleObjectSupport     : started SUBMITTED CANCELLED FULFILLED PAID  / PAID / uuid=7f36d914-57df-4051-9afe-cd620f5b3bc6 / id=null
 2021-07-31 18:10:30.949  INFO 52652 --- [           main] .s.j.SimpleEnumStatemachineConfiguration : stateChanged(from: ObjectState [getIds()=[PAID], getClass()=class org.springframework.statemachine.state.ObjectState, hashCode()=710973468, toString()=AbstractState [id=PAID, pseudoState=null, deferred=[], entryActions=[], exitActions=[], stateActions=[], regions=[], submachine=null]], to: ObjectState [getIds()=[FULFILLED], getClass()=class org.springframework.statemachine.state.ObjectState, hashCode()=2046578329, toString()=AbstractState [id=FULFILLED, pseudoState=org.springframework.statemachine.state.DefaultPseudoState@6e7fa4b0, deferred=[], entryActions=[], exitActions=[], stateActions=[], regions=[], submachine=null]])
 2021-07-31 18:10:30.949  INFO 52652 --- [           main] o.s.s.support.LifecycleObjectSupport     : stopped org.springframework.statemachine.support.DefaultStateMachineExecutor@3f1fb139
 2021-07-31 18:10:30.949  INFO 52652 --- [           main] o.s.s.support.LifecycleObjectSupport     : stopped SUBMITTED CANCELLED FULFILLED PAID  /  / uuid=7f36d914-57df-4051-9afe-cd620f5b3bc6 / id=null
 2021-07-31 18:10:30.949  INFO 52652 --- [           main] io.hari.springstatemachine.josh.Runner   : after calling fulfill(): FULFILLED
 2021-07-31 18:10:30.950  INFO 52652 --- [           main] io.hari.springstatemachine.josh.Runner   : order: Order(id=1, datetime=2021-07-31 18:10:30.855, state=FULFILLED)

 */