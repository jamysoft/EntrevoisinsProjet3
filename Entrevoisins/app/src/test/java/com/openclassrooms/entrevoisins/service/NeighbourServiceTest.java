package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {

        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getNeighbourByIdWithSuccess()
    {
        Neighbour neighbour= service.getNeighboursById((long)1);
        Neighbour expectedNeighbour=DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
        assertEquals(neighbour,expectedNeighbour);
    }
    @Test
    public void createFavorisNeighbourWithSuccess() {
        Neighbour n= DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
        service.createFavorisNeighbour(n);
        Neighbour expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
        assertEquals(service.getfavorisNeighbours().get(0),expectedNeighbours);
    }
    @Test
    public void getFavorisNeighboursWithSuccess()
    {
        Neighbour n= DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
        service.createFavorisNeighbour(n);
        Neighbour neighbour = service.getfavorisNeighbours().get(0);
        Neighbour expectedNeighbour = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
        assertEquals(neighbour,expectedNeighbour);
    }

    @Test
    public void deleteFavorisNeighbourWithSuccess()
    {
        Neighbour n= DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
        service.createFavorisNeighbour(n);
        Neighbour neighbourToDelete = service.getfavorisNeighbours().get(0);
        service.deleteFavorisNeighbour(neighbourToDelete);
        assertFalse(service.getfavorisNeighbours().contains(neighbourToDelete));
    }

}
