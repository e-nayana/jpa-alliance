package com.java.order.state.engine;


import com.java.order.model.CustomerOrder;
import com.java.order.state.*;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

public class OrderStateFactory {

    public static OrderState factory(@NotNull CustomerOrder order, @Nullable String stringState){

        OrderState state;

        if(stringState == null){
            state = new InitStatus(order);
        }
        else {
            switch (stringState){
                case "":
                    state = new InitStatus(order);
                    break;

                case STATES.PENDING:
                    state = new PendingState(order);
                    break;

                case STATES.CONFIRMED:
                    state = new ConfirmedState(order);
                    break;

                case STATES.PACKAGING:
                    state = new PackingState(order);
                    break;

                case STATES.DELIVERYINPROGRESS:
                    state = new DeliveryProcessingState(order);
                    break;

                case STATES.DELIVERED:
                    state = new DeliveredState(order);
                    break;

                case STATES.CANCEL:
                    state = new CancelState(order);
                    break;

                case STATES.RETURNED:
                    state = new ReturnedState(order);
                    break;

                case STATES.RESCHEDULED:
                    state = new RescheduledState(order);
                    break;
                default:
                    state = new NotFoundState(order);

            }
        }
        return state;
    }

}
