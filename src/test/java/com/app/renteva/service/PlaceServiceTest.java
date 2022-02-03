package com.app.renteva.service;

import com.app.renteva.document.demand.DocumentOfferDemand;
import com.app.renteva.document.demand.DocumentOfferDemandRepository;
import com.app.renteva.document.demand.resource.DocumentOfferDemandResource;
import com.app.renteva.place.Place;
import com.app.renteva.place.PlaceRepository;
import com.app.renteva.place.PlaceService;
import com.app.renteva.place.PlaceServiceImpl;
import com.app.renteva.place.resource.AddDemandPlaceResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.app.renteva.dataset.DocumentOfferDemandDataset.getAnotherOfferDemand;
import static com.app.renteva.dataset.DocumentOfferDemandDataset.getOfferDemand;
import static com.app.renteva.dataset.PlaceDataset.getExistingPlace;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PlaceServiceTest {

    @Mock
    private PlaceRepository placeRepository;

    @Mock
    private DocumentOfferDemandRepository offerDemandRepository;

    private PlaceService placeService;

    @BeforeEach
    void before() {
        placeService = new PlaceServiceImpl(placeRepository, offerDemandRepository);
    }

    @Test
    @DisplayName("Should add a document demand on a existing place")
    void should_add_document_demand_when_place_exist_then_return_place_with_demands() {

        Place existingPlace = getExistingPlace();
        DocumentOfferDemand firstOfferDemand = getOfferDemand();
        DocumentOfferDemand secondOfferDemand = getAnotherOfferDemand();

        Mockito.when(placeRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(existingPlace));
        Mockito.when(offerDemandRepository.getById(firstOfferDemand.getId()))
                .thenReturn(firstOfferDemand);
        Mockito.when(offerDemandRepository.getById(secondOfferDemand.getId()))
                .thenReturn(secondOfferDemand);

        placeService.addDocumentOfferDemands(existingPlace.getId(), AddDemandPlaceResource.builder()
                .offerDemandResources(
                        List.of(
                                DocumentOfferDemandResource.builder().id(firstOfferDemand.getId()).build(),
                                DocumentOfferDemandResource.builder().id(secondOfferDemand.getId()).build()
                        )
                )
                .build());

        ArgumentCaptor<Place> placeArgumentCaptor = ArgumentCaptor.forClass(Place.class);
        Mockito.verify(placeRepository).save(placeArgumentCaptor.capture());
        final Place captorValue = placeArgumentCaptor.getValue();

        assertThat(captorValue.getDocumentOfferDemands()).hasSize(2);
    }
}
