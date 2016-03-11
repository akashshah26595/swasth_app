package com.example.mastek.blue.deep.swasthtesting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    String TITLES[] = {"Home", "Barcode Image", "Tutorial", "About"};
    int ICONS[] = {R.drawable.rsz_2ic_home_black_24dp, R.drawable.rsz_ic_barcode_scan_black_24dp, R.drawable.rsz_ic_desktop_mac_black_24dp, R.drawable.rsz_ic_information_outline_black_24dp};

    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view

    String NAME = "Jay Shah";
    String CARD_NO = "123456";
    int PROFILE = R.drawable.ic_account_circle_black_24dp;

    private Toolbar toolbar;                              // Declaring the Toolbar Object

    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle

    // Declaring Action Bar Drawer Toggle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        ImageButton imageButton_helpline = (ImageButton) findViewById(R.id.helpline_image);
        imageButton_helpline.setOnClickListener(this);

        ImageButton imageButton_centers = (ImageButton) findViewById(R.id.centers_image);
        imageButton_centers.setOnClickListener(this);

        TextView textView_helpline = (TextView) findViewById(R.id.helpline_text);
        textView_helpline.setOnClickListener(this);

        TextView textView_centers = (TextView) findViewById(R.id.centers_text);
        textView_centers.setOnClickListener(this);


//        if ( ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ) {
//
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
//                    LocationService.MY_PERMISSION_ACCESS_COURSE_LOCATION);
//        }

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new MyAdapter(TITLES, ICONS, NAME, CARD_NO, PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager


        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.app_name, R.string.app_name) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }


        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i) {
            case R.id.helpline_image:
                Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "9833151491"));
                startActivity(intent1);
                break;

            case R.id.helpline_text:
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "9833151491"));
                startActivity(intent2);
                break;

            case R.id.centers_image:
                String map1 = "http://maps.google.co.in/maps?q=" + "Swasth Foundation";
                Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse(map1));
                startActivity(intent3);
                break;

            case R.id.centers_text:
                String map2 = "http://maps.google.co.in/maps?q=" + "Swasth Foundation";
                Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse(map2));
                startActivity(intent4);
                break;
        }

    }
}