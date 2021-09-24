package com.monkeyladder.ui.mainscreen;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.monkeyladder.R;
import com.monkeyladder.game.GameLevel;
import com.monkeyladder.game.GameState;
import com.monkeyladder.game.Location;
import com.monkeyladder.game.MonkeyLadderGame;
import com.monkeyladder.game.PlayerLives;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MainActivityViewContract, View.OnClickListener {

    private static final CellLocationMapping locationMapping = new CellLocationMapping();
    private static final CellDataMapping dataMapping = new CellDataMapping();

    private static final GameLevel STARTING_LEVEL = GameLevel.LevelThree;
    private final Timer gameUpdateTimer = new Timer( false );
    private MainActivityPresenter presenter = null;
    private ProgressBar progressBar;
    private ImageView resultImage;
    private ImageView livesImage;

    private boolean isReadyForUserInput = false;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        presenter = new MainActivityPresenter( this,
                new MainScreenModel( new MonkeyLadderGame( STARTING_LEVEL ) ),
                CountDownTimerParameter.of( 2500, 50 ) );

        setContentView( R.layout.activity_main );

        initUserInterfaceElements();

        presenter.startOneRound();
    }

    @Override
    public void displayBoard( List<LocationData> locationsThatAreSet ) {

        locationsThatAreSet
                .stream()
                .forEach( locationData -> {
                    Integer resourceId = locationMapping
                            .resourceIdForLocation( locationData.getLocation() )
                            .orElseThrow( ( ) -> new RuntimeException( "" ) );

                    ImageView imageView = findViewById( resourceId );
                    imageView.setBackgroundColor( getResources().getColor( R.color.colorPrimaryDark ) );
                    imageView.setImageResource(
                            dataMapping.drawableResourceIdFor( locationData.getData() )
                                    .orElseThrow( ( ) -> new RuntimeException( "" ) ) );

                } );
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
                .forEach( resourceId -> clearResource( resourceId ) );

    }

    @Override
    public void clearHighlightedCells( ) {
        locationMapping
                .getMapping()
                .keySet()
                .stream()
                .forEach( resourceId -> clearHighlightedCell( resourceId ) );
    }


    @Override
    public void displayUserSelectionCorrectFeedback( ) {
        resultImage.setImageResource( R.drawable.check );
        updateUserInputFeedBackImage();
    }

    @Override
    public void displayUserSelectionIncorrectFeedback( ) {
        resultImage.setImageResource( R.drawable.x_error );
        updateUserInputFeedBackImage();
    }

    @Override
    public void updateUserInputFeedBackImage( ) {
        gameUpdateTimer.schedule( new TimerTask() {
            @Override
            public void run( ) {
                runOnUiThread( ( ) ->
                        resultImage.setImageResource( R.drawable.expecting_input ) );
            }
        }, 500 );
    }

    @Override
    public void onClick( View v ) {

        if ( !isReadyForUserInput ) {
            return;
        }

        int id = v.getId();

        Location location = locationMapping.locationForResource( id )
                .orElseThrow( ( ) -> new IllegalStateException( "Unable to find the mapping for resource " +
                        "identified by id " + id ) );

        Integer resourceId = locationMapping.resourceIdForLocation( location )
                .orElseThrow( ( ) -> new IllegalStateException( "Invalid location " + location ) );

        clearHighlightedCell( resourceId );

        presenter.collectSelectedLocation( location );
    }

    /**
     * Update
     * 1) score
     * 2) player lives if different
     * 3) reset progress
     *
     * @param gameState
     */
    @Override
    public void updateGameStateInUI( GameState gameState ) {
        TextView scoreText = findViewById( R.id.score );
        scoreText.setText( gameState.getScore() + "" );

        // TODO update lives
        //progressBar.setProgress( 0 );
    }

    @Override
    public void setReadyToTakeUserInput( boolean isReady ) {
        isReadyForUserInput = isReady;
    }

    @Override
    public void onGameEnd( ) {
        //
    }

    @Override
    public void updateLivesInUI( PlayerLives lives ) {
        switch ( lives.getHealth() ) {
            case Danger:
                this.livesImage.setImageResource( R.drawable.one_life );
                break;
            case WarningState:
                this.livesImage.setImageResource( R.drawable.two_lives );
                break;
            case Healthy:
                this.livesImage.setImageResource( R.drawable.three_lives );
                break;
            default:
                throw new RuntimeException( "Unable to determine the health image to be used " +
                        "for lives " + lives );
        }
    }

    private void initUserInterfaceElements( ) {
        progressBar = findViewById( R.id.progressBar );
        resultImage = findViewById( R.id.userSelectionResult );
        livesImage = findViewById( R.id.livesImage );
        livesImage.setImageResource( R.drawable.three_lives );
        resultImage.setImageResource( R.drawable.expecting_input );
    }


    private void clearResource( Integer resourceId ) {
        ImageView imageView = findViewById( resourceId );

        imageView.setImageResource( R.drawable.transparent );
    }

    private void clearHighlightedCell( Integer resourceId ) {

        ImageView imageView = findViewById( resourceId );

        imageView.setBackgroundColor( getResources().getColor( R.color.colorPrimary ) );
    }

}