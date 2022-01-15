package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DeleteFavorisNeighbourEvent
{
    public Neighbour neighbour;

    public DeleteFavorisNeighbourEvent(Neighbour neighbour)
    {
        this.neighbour = neighbour;
    }
}
