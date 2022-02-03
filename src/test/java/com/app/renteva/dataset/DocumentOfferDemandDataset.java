package com.app.renteva.dataset;

import com.app.renteva.document.demand.DocumentOfferDemand;

import java.util.Random;

public class DocumentOfferDemandDataset {

    public static DocumentOfferDemand getOfferDemand() {
        return DocumentOfferDemand.builder()
                .id(new Random().nextLong())
                .active(true)
                .name("Bank Statements")
                .build();
    }

    public static DocumentOfferDemand getAnotherOfferDemand() {
        return getOfferDemand().toBuilder()
                .name("Payroll Statement")
                .build();
    }
}
