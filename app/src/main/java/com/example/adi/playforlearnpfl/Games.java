package com.example.adi.playforlearnpfl;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adi.playforlearnpfl.Alunno.HomeAlunno;
import com.example.adi.playforlearnpfl.Giochi.ActivityGiocoStoGioIng;
import com.example.adi.playforlearnpfl.Giochi.DolceMatematica;
import com.example.adi.playforlearnpfl.Giochi.QuizActivityItaliano;

import java.util.List;

import static com.example.adi.playforlearnpfl.Alunno.HomeAlunno.GEOGRAFIA;
import static com.example.adi.playforlearnpfl.Alunno.HomeAlunno.INGLESE;
import static com.example.adi.playforlearnpfl.Alunno.HomeAlunno.ITALIANO;
import static com.example.adi.playforlearnpfl.Alunno.HomeAlunno.MATEMATICA;
import static com.example.adi.playforlearnpfl.Alunno.HomeAlunno.MATERIA;
import static com.example.adi.playforlearnpfl.Alunno.HomeAlunno.STORIA;

public class Games extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public static String materia;
    static int page = -1;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    // TODO dichiarare tutti gli array per i giochi
    //todo settare le icone

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        materia = getIntent().getStringExtra(MATERIA);

        //per la stampa del nome della materia sulla toolbar
        // NOTA: Ho tolto la toolbar dal file di layout perché ne uscivano due
        String title  = materia; // Per non perdere il valore reale di materia
        getSupportActionBar().setTitle(title.charAt(0) + title.substring(1).toLowerCase());

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        // TODO caricare tutti gli array in questo metodo
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_games, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            int page = getArguments().getInt(ARG_SECTION_NUMBER);
            page--;
           // View rootView = inflater.inflate(R.layout.account,null);
            View rootView = inflater.inflate(R.layout.fragment_games,null);
            ImageView image = (ImageView)new ImageView(getContext());
            Log.d("DEBUG","[Placeholder - onCreateView] materia = " + materia);
            switch (page) {
                case 0:
                    if(materia.compareTo(ITALIANO) == 0) {
                        ImageView image0 = (ImageView) rootView.findViewById(R.id.image0);
                        image0.setBackgroundColor(Color.GREEN);
                        image0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i= new Intent(getContext(), QuizActivityItaliano.class);
                                startActivity(i);
                            }
                        });
                        ImageView image1 = (ImageView) rootView.findViewById(R.id.image1);
                        image1.setBackgroundColor(Color.RED);
                        ImageView image2 = (ImageView) rootView.findViewById(R.id.image2);
                        image2.setBackgroundColor(Color.BLUE);
                        ImageView image3 = (ImageView) rootView.findViewById(R.id.image3);
                        image3.setBackgroundColor(Color.YELLOW);
                    }else if((materia.compareTo(STORIA)==0)||(materia.compareTo(INGLESE)==0)||(materia.compareTo(GEOGRAFIA)==0)){
                        ImageView image0 = (ImageView) rootView.findViewById(R.id.image0);
                        image0.setBackgroundColor(Color.RED);
                        image0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i= new Intent(getContext(), ActivityGiocoStoGioIng.class);
                                i.putExtra(MATERIA, materia);
                                startActivity(i);
                            }
                        });
                        ImageView image1 = (ImageView) rootView.findViewById(R.id.image1);
                        image1.setBackgroundColor(Color.BLACK);
                        ImageView image2 = (ImageView) rootView.findViewById(R.id.image2);
                        image2.setBackgroundColor(Color.GRAY);
                        ImageView image3 = (ImageView) rootView.findViewById(R.id.image3);
                        image3.setBackgroundColor(Color.WHITE);
                    }else if (materia.compareTo(MATEMATICA)==0){
                        ImageView image0 = (ImageView) rootView.findViewById(R.id.image0);
                        image0.setBackgroundColor(Color.RED);
                        image0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i= new Intent(getContext(),ActivityGiocoStoGioIng.class);
                                i.putExtra(MATERIA, materia);
                                startActivity(i);

                            }
                        });
                        ImageView image1 = (ImageView) rootView.findViewById(R.id.image1);
                        image1.setBackgroundColor(Color.BLACK);
                        ImageView image2 = (ImageView) rootView.findViewById(R.id.image2);
                        image2.setBackgroundColor(Color.GRAY);
                        ImageView image3 = (ImageView) rootView.findViewById(R.id.image3);
                        image3.setBackgroundColor(Color.WHITE);
                    }
                    break;
                case 1:
                    if(materia.compareTo(ITALIANO) == 0) {
                        ImageView image0 = (ImageView) rootView.findViewById(R.id.image0);
                        image0.setBackgroundColor(Color.GREEN);
                        image0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Intent i= new Intent(getContext(), QuizActivity.class);
                               // startActivity(i);
                                Toast.makeText(getContext(), "Work in progress", Toast.LENGTH_LONG);
                            }
                        });
                        ImageView image1 = (ImageView) rootView.findViewById(R.id.image1);
                        image1.setBackgroundColor(Color.RED);
                        ImageView image2 = (ImageView) rootView.findViewById(R.id.image2);
                        image2.setBackgroundColor(Color.BLUE);
                        ImageView image3 = (ImageView) rootView.findViewById(R.id.image3);
                        image3.setBackgroundColor(Color.YELLOW);
                    }else if((materia.compareTo(STORIA)==0)||(materia.compareTo(INGLESE)==0)||(materia.compareTo(GEOGRAFIA)==0)){
                        ImageView image0 = (ImageView) rootView.findViewById(R.id.image0);
                        image0.setBackgroundColor(Color.RED);
                        image0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               // Intent i= new Intent(getContext(), ActivityGiocoStoGioIng.class);
                               // i.putExtra(MATERIA, materia);
                               // startActivity(i);
                                Toast.makeText(getContext(), "Work in progress", Toast.LENGTH_LONG);
                            }
                        });
                        ImageView image1 = (ImageView) rootView.findViewById(R.id.image1);
                        image1.setBackgroundColor(Color.BLACK);
                        ImageView image2 = (ImageView) rootView.findViewById(R.id.image2);
                        image2.setBackgroundColor(Color.GRAY);
                        ImageView image3 = (ImageView) rootView.findViewById(R.id.image3);
                        image3.setBackgroundColor(Color.WHITE);
                    }else if (materia.compareTo(MATEMATICA)==0){
                        ImageView image0 = (ImageView) rootView.findViewById(R.id.image0);
                        image0.setBackgroundColor(Color.RED);
                        image0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i= new Intent(getContext(),DolceMatematica.class);
                                i.putExtra(MATERIA, materia);
                                startActivity(i);

                            }
                        });
                        ImageView image1 = (ImageView) rootView.findViewById(R.id.image1);
                        image1.setBackgroundColor(Color.BLACK);
                        ImageView image2 = (ImageView) rootView.findViewById(R.id.image2);
                        image2.setBackgroundColor(Color.GRAY);
                        ImageView image3 = (ImageView) rootView.findViewById(R.id.image3);
                        image3.setBackgroundColor(Color.WHITE);
                    }
                    break;
                case 2:break;
            }

            /*GridView gvGiochi = (GridView) rootView.findViewById(R.id.gvGiochi);
            gvGiochi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getContext(), "Ciao", Toast.LENGTH_SHORT).show();
                    ImageView v = new ImageView(getContext());
                }
            });

            ArrayList<OggettoProva> objs = new ArrayList<>();
            ArrayList<Gioco> qui= new ArrayList<>();
           // qui.addAll(Creatore.giochiDaLanciare);
            objs.add(new OggettoProva("Oggetto 1"));
            objs.add(new OggettoProva("Oggetto 2"));
            Gioco gioco1= new Gioco_Storia(getResources().getDrawable(R.drawable.icona_storia),"Riconoscimento","STORIA","Prima",0, R.drawable.darenome_casa_tavolo, "tavolo", "casa", "pigiama", "stella");
            ArrayAdapterGiochi arrayAdapterGiochi = new ArrayAdapterGiochi(getContext(), R.layout.fragment_games, objs);

            gvGiochi.setAdapter(arrayAdapterGiochi);
            switch (page) {
                // Carica gli array in base alla pagina
                case 0:
                    ((TextView) rootView.findViewById(R.id.tvProva)).setText("Pagina 0  Giochi Prima elementare");
                    if(materia.compareTo(MATEMATICA) == 0) {
                        //for(int i=0; i<3; i++){
                        ImageView v =null;
                        v = (ImageView) v.findViewById(R.id.gioco1);
                        v.setBackgroundResource(gioco1.getIcona().getAlpha());

                    }else if(materia.compareTo(INGLESE) == 0){
                        // arrayAdapterGiochi.addAll();
                    }else if(materia.compareTo(ITALIANO) == 0){
                        // arrayAdapterGiochi.addAll();
                    }else if(materia.compareTo(STORIA) == 0){
                        ((TextView) rootView.findViewById(R.id.tvProva)).setText("Storia");
                    }else if(materia.compareTo(GEOGRAFIA) == 0){
                        // arrayAdapterGiochi.addAll();
                    }
                    //arrayAdapterGiochi.clear();
                    // arrayAdapterGiochi.addAll(qui ci va l'array che carico su'); //
                    // cambiare in array in base alla materia e classe
                    // fare per ogni pagina

                        //carica tutti gli array nell'oncreate della classe Games
                        break;
                case 1:
                    ((TextView) rootView.findViewById(R.id.tvProva)).setText("Pagina 1");
                    break;
                case 2:
                    ((TextView) rootView.findViewById(R.id.tvProva)).setText("Pagina 2");
                    break;
            }*/
            /*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*/
            return rootView;
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //Log.d("DEBUG","Pagina " + position);
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "1";
                case 1:
                    return "2";
                case 2:
                    return "3";
                case 3:
                    return "4";
                case 4:
                    return "5";
            }
            return null;
        }
    }

    private static class ArrayAdapterGiochi extends ArrayAdapter {
        private Context mContext;

        public ArrayAdapterGiochi(Context context, int resource, List objects) {
            super(context, resource, objects);
            mContext = context;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = View.inflate(mContext, R.layout.fragment_games, null);
            //((TextView) v.findViewById(R.id.tvProva)).setText(((OggettoProva) this.getItem(position)).getStr());
            //in mancanza di un Array List dinamico creo oggetti statici
            ImageView image0 =(ImageView) v.findViewById(R.id.image0);
            ImageView image1 =(ImageView) v.findViewById(R.id.image1);
            ImageView image2 =(ImageView) v.findViewById(R.id.image2);
            ImageView image3 =(ImageView) v.findViewById(R.id.image3);

            // TODO impostare icone e testo per la lista di giochi
            return v;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), HomeAlunno.class));
        finish();
    }

//    private static class OggettoProva {
//        private String str = "";
//
//        public OggettoProva(String str) {
//            this.str = str;
//        }
//
//        public String getStr() {
//            return str;
//        }
//    }
}

//todo javadoc