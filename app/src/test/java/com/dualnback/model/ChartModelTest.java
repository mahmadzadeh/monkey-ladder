package com.dualnback.model;

import com.dualnback.data.filesystem.dao.DataPoint;
import com.dualnback.game.NBackVersion;
import com.dualnback.ui.chartscreen.ChartModel;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Date;


public class ChartModelTest {

    private static final String TEST_RESOURCES_DIR = "src/test/resources";
    private ChartModel chartModel;

    @Before
    public void setUp( ) {
        chartModel = new ChartModel( new File( TEST_RESOURCES_DIR ) );
    }

    @Test
    public void getDataPoints( ) {
        assertThat( chartModel.getDataPoints().size() ).isEqualTo( 2 );
    }

    @Test
    public void addingSingleDataPoint( ) {
        DataPoint dataPoint = new DataPoint( new Date(), 30, NBackVersion.TwoBack );

        assertThat( chartModel.getDataPoints().size() ).isEqualTo( 2 );

        chartModel.addDataPoint( dataPoint );

        assertThat( chartModel.getDataPoints().size() ).isEqualTo( 3 );
    }

    @Test
    public void chartData( ) {
    }

    @Test
    public void saveData( ) throws Exception {
    }

}