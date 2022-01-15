package com.openclassrooms.entrevoisins.ui.neighbour_list;
import static com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator.DUMMY_NEIGHBOURS;


import android.app.ActionBar;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
public class DetailActivity extends AppCompatActivity {
    public static final String KEY_ID_ITEM="ID_ITEM";
@BindView(R.id.avatar)
ImageView avatar;
@BindView(R.id.nom)
TextView nom;
@BindView(R.id.nom2)
TextView nom2;
@BindView(R.id.address)
TextView adr;
@BindView(R.id.tel)
TextView tel;
@BindView(R.id.facebook)
TextView facebook;
@BindView(R.id.apropos)
TextView about;
@BindView(R.id.favoris)
FloatingActionButton favoris;
//@BindView(R.id.tool)
 //  Toolbar mToolbar;
@BindView(R.id.toolbar)
Toolbar mToolbar;
    private NeighbourApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       //Cette directive enlève la barre de titre
  //      this.requestWindowFeature(Window.FEATURE_NO_TITLE);
// Cette directive permet d'enlever la barre de notifications pour afficher l'application en plein écran
// this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       //getSupportActionBar().hide();
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);


        /*
         TextView nom=findViewById(R.id.nom);
         TextView nom2=findViewById(R.id.nom2);
        TextView adr=findViewById(R.id.address);
        TextView tel=findViewById(R.id.tel);
        TextView facebook=findViewById(R.id.facebook);
        TextView about=findViewById(R.id.apropos);*/
        mApiService = DI.getNeighbourApiService();
        Long id_item=getIntent().getExtras().getLong(KEY_ID_ITEM);
        Neighbour neighbour_item=mApiService.getNeighboursById(id_item);
        Uri imgUri=Uri.parse(neighbour_item.getAvatarUrl());
        Picasso.with(this)
                .load(imgUri)
                 .into(avatar);

       //Toolbar mToolbar= (Toolbar) findViewById(R.id.toolbar);
     //   setSupportActionBar(mToolbar)
     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().hide();

      //  Log.e("NAME_NEIGHBOUR_ITEM", "onCreate: "+neighbour_item.getName());
      //  Log.e("POSITION ITEM", "onCreate: "+id_item);
        System.out.println("neighbour item"+neighbour_item.getName());
        nom.setText( neighbour_item.getName());
        nom2.setText(neighbour_item.getName());
        adr.setText( neighbour_item.getAddress());
        tel.setText(neighbour_item.getPhoneNumber());
        facebook.setText("www.facebook.fr/"+neighbour_item.getName());
        about.setText(neighbour_item.getAboutMe());

        if(mApiService.isFavorite_neighbour(neighbour_item))
        {
            favoris.setImageResource(R.drawable.ic_baseline_star_24);
            System.out.println("favoris");
        }
        else
        {
            favoris.setImageResource(R.drawable.ic_baseline_star_border_24);
            System.out.println("Pas favoris");
        }
             favoris.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     if(mApiService.isFavorite_neighbour(neighbour_item))
                     {
                         mApiService.deleteFavorisNeighbour(neighbour_item);
                         favoris.setImageResource(R.drawable.ic_baseline_star_border_24);
                        //// favoris.setEnabled(false);
                        // favoris.setClickable(false);
                     }
                     else
                     {
                         // mApiService=new DummyNeighbourApiService();
                         mApiService.createFavorisNeighbour(neighbour_item);
                         // finish();
                         favoris.setImageResource(R.drawable.ic_baseline_star_24);
                       //  favoris.setEnabled(false);
                       //  favoris.setClickable(false);
                     }

                 }
             });

    }

}