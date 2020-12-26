package com.monkeyladder.ui.mainscreen;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.monkeyladder.R;
import com.monkeyladder.game.GameLevel;
import com.monkeyladder.game.Location;
import com.monkeyladder.game.MonkeyLadderGame;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MainActivityViewContract, View.OnClickListener {

    private static final CellLocationMapping locationMapping = new CellLocationMapping();

    private static final GameLevel STARTING_LEVEL = GameLevel.LevelEleven;

    private MainActivityPresenter presenter = null;

    private ProgressBar progressBar;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        presenter = new MainActivityPresenter( this,
                new MainScreenModel( new MonkeyLadderGame( STARTING_LEVEL ) ),
                CountDownTimerParameter.of( 5000, 100 ) );

        setContentView( R.layout.activity_main );

        progressBar = findViewById( R.id.progressBar );

        presenter.readyForDisplay();
    }

    @Override
    public void display( List<LocationData> locationsThatAreSet ) {
        locationsThatAreSet
                .stream()
                .forEach( locationData -> {
                    Integer resourceId = locationMapping
                            .resourceIdForLocation( locationData.getLocation() )
                            .orElseThrow( ( ) -> new RuntimeException( "" ) );

                    TextView textView = findViewById( resourceId );
                    textView.setText( locationData.getData() + "" );
                    textView.setBackgroundColor( getResources().getColor( R.color.colorPrimaryDark ));
                } );

        presenter.startDisplayTimer();
    }

    @Override
    public void updateDisplayBoardProgressBar( int progress ) {
        progressBar.setProgress( progress );
    }

    @Override
    public void clearScreen( ) {
        locationMapping
                .getMapping()
                .keySet()
                .stream()
                .forEach( resourceId -> {
                    TextView textView = findViewById( resourceId );
                    textView.setText( " " );
                } );
    }

    @Override
    public void onClick( View v ) {
        int id = v.getId();

        Location location = locationMapping.locationForResource( id )
                .orElseThrow( ( ) -> new IllegalStateException( "Unable to find the mapping for resource " +
                        "identified by id " + id ) );

        presenter.addSelectedLocation( location );
    }
}