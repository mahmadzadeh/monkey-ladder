package com.monkeyladder.ui.mainscreen;

import android.os.Bundle;
import android.util.Log;
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

    private static final GameLevel STARTING_LEVEL = GameLevel.LevelThree;
    private final Timer gameUpdateTimer = new Timer( false );
    private MainActivityPresenter presenter = null;
    private ProgressBar progressBar;
    private ImageView resultImage;
    private ImageView livesImage;
    private TextView livesText;

    private boolean isReadyForUserInput = false;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        presenter = new MainActivityPresenter( this,
                new MainScreenModel( new MonkeyLadderGame( STARTING_LEVEL ) ),
                CountDownTimerParameter.of( 2500, 100 ) );

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

                    TextView textView = findViewById( resourceId );
                    textView.setText( locationData.getData() + "" );
                    textView.setBackgroundColor( getResources().getColor( R.color.colorPrimaryDark ) );
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
                .forEach( resourceId -> {
                    TextView textView = findViewById( resourceId );
                    textView.setText( " " );
                } );
    }

    @Override
    public void clearHighlightedCells( ) {
        locationMapping
                .getMapping()
                .keySet()
                .stream()
                .forEach( resourceId -> {
                    TextView textView = findViewById( resourceId );
                    textView.setBackgroundColor( getResources().getColor( R.color.colorPrimary ) );
                } );
    }

    @Override
    public void displayUserSelectionCorrectFeedback( ) {
        resultImage.setImageResource( R.drawable.good_mood );
        updateUserInputFeedBackImage();
    }

    @Override
    public void displayUserSelectionIncorrectFeedback( ) {
        resultImage.setImageResource( R.drawable.bad_mood );
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
                                  },
                500 );
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
        livesText = findViewById( R.id.livesLeftText );
        int lifeCount = lives.getLifeCount();
        Log.e( "Main Activity ", ">>>>>>Updating life count in UI lifeCount: " + lifeCount );
        livesText.setText( getResources().getString( R.string.livesText, lifeCount ) );
        switch ( lives.getHealth() ) {
            case Danger:
                this.livesImage.setImageResource( R.drawable.heart_danger );
                break;
            case WarningState:
                this.livesImage.setImageResource( R.drawable.heart_warning );
                break;
            case Healthy:
                this.livesImage.setImageResource( R.drawable.heart_healthy );
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
        livesImage.setImageResource( R.drawable.heart_healthy );
        livesText = findViewById( R.id.livesLeftText );

        livesText.setText( getResources().getString( R.string.livesText,
                presenter.getCurrentGameState().getLives().getLifeCount() ) );

        resultImage.setImageResource( R.drawable.expecting_input );
    }
}