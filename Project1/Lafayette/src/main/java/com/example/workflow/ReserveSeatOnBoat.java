package com.example.workflow;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;

@Named
//@Slf4j
public class ReserveSeatOnBoat implements JavaDelegate {
//logging by Andreas
    private static final Logger log = LoggerFactory.getLogger(ReserveSeatOnBoat.class);


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {


        String money = "0.0";
        String ticketType = "Coach";
        money = (String) delegateExecution.getVariable("money");
        double moneyDouble = Double.parseDouble(money);
        if (moneyDouble >= 10000) {
            ticketType = "First class";

        } else if (moneyDouble >= 5000) {
            ticketType = "Business Class";
        } else if (moneyDouble <= 10) {
            ticketType = "Stowaway CLass";
            throw new BpmnError("Fall_Overboard", "A cheap ticket has lead to a small wave throwing you overboard");
        }

        delegateExecution.setVariable("tickettype", ticketType);
        log.info("Our ticket type is {} . We wish you a good journey.", ticketType);
    }
}


