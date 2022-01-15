package com.openclassrooms.entrevoisins.ui.neighbour_list;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.DetailActivity.KEY_ID_ITEM;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteFavorisNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DetailNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link list_favoris_neigbour#newInstance} factory method to
 * create an instance of this fragment.
 */
public class list_favoris_neigbour extends Fragment {
    private NeighbourApiService mApiService;
    private List<Neighbour>  mFavorisNeighbours;
    private RecyclerView mRecyclerView;



    public static list_favoris_neigbour newInstance() {
        list_favoris_neigbour fragment = new list_favoris_neigbour();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
    //    return inflater.inflate(R.layout.fragment_list_favoris_neigbour,
        //    container,false);

        View view = inflater.inflate(R.layout.fragment_list_favoris_neigbour, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_favoris_neighbours)   ;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        return view;

    }

    @Override
    public void onStart() {

        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mFavorisNeighbours = mApiService.getfavorisNeighbours();
        mRecyclerView.setAdapter(new MyFavorisNeighbourRecyclerViewAdapter( mFavorisNeighbours));
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);

    }
    @Subscribe
    public void onDeleteFavorisNeighbour(DeleteFavorisNeighbourEvent event) {
        mApiService.deleteFavorisNeighbour(event.neighbour);
        initList();
    }

    @Subscribe
    public void onDetailNeighbour(DetailNeighbourEvent event)
    {
        Intent monIntent = new Intent(getContext(), DetailActivity .class);
        monIntent.putExtra(KEY_ID_ITEM,event.id);
        getContext().startActivity(monIntent);
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        mFavorisNeighbours = mApiService.getfavorisNeighbours();
        mRecyclerView.setAdapter(new MyFavorisNeighbourRecyclerViewAdapter(mFavorisNeighbours));
    }
}