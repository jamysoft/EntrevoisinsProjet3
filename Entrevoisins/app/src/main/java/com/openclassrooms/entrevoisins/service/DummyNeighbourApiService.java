package com.openclassrooms.entrevoisins.service;

import static com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator.DUMMY_NEIGHBOURS;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favoris_neighbours=new ArrayList<>();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        System.out.println(this +"  FROM delete neighbour");
        neighbours.remove(neighbour);
       /* for (int i=0;i<neighbours.size();i++)
        {
            System.out.println("list neighbour aprés supp "+neighbours.get(i).getName());
        }*/

    }
    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }


    @Override
    public List<Neighbour> getfavorisNeighbours() {
        return favoris_neighbours;
    }

    @Override
    public void deleteFavorisNeighbour(Neighbour neighbour) {
        System.out.println("FROM delete favoris Neighbour");
        favoris_neighbours.remove(neighbour);

    }

    @Override
    public void createFavorisNeighbour(Neighbour neighbour) {
        favoris_neighbours.add(neighbour);

    }

    @Override
    public  Neighbour getNeighboursById(Long Id)
    {
        Neighbour n=null;
        for (Neighbour item : DUMMY_NEIGHBOURS)
        {
            if (item.getId()==Id) {
                n=item;
                break;
            }
        }
        return n;
    }

    @Override
    public Boolean isFavorite_neighbour(Neighbour neighbour)
    {
        if(favoris_neighbours.contains(neighbour))
        {
            return true;
        }
        else
        {
            return false;

        }

    }
}
