package edu.jsu.mcis.cs408.project1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.beans.PropertyChangeEvent;

public class TicTacToeView extends AppCompatActivity {

    private TicTacToeController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        controller = new TicTacToeController(this);

        resetView();

    }

    public void resetView() {

        //
        // This method resets the Activity to its default state.  It should display the welcome
        // message in the result TextView, and clear any text in the grid TextViews.  (Hint: the
        // grid TextViews are all named according to the pattern "SquareYX", with Y being the row
        // and X being the column.  Iterate through them with a nested for-loop and use the helper
        // function "getSquareId()" to acquire the TextView IDs.
        //

        int size = controller.getGridSize();

        setResult( getResources().getString(R.string.welcome) );

        //
        // INSERT YOUR CODE HERE
           // getSquareId();

        for (int i = 0 ; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String squareId = "square_" + i + j;
                int resourceId = getResources().getIdentifier(squareId, "id", getPackageName());
                //grid[i][j] = findViewById(resourceId);
                //grid[i][j].setOnClickListener(this);
            }
        }

        //

    }

    public void modelPropertyChange(final PropertyChangeEvent e) {

        //
        // This method is called by the Controller when a property change in the Model must be
        // reflected in the View.  It should: get the name and value of the updated property, check
        // the property name/event that was changed, and if it matches a property that is contained
        // in this View, update the View to the new value.  (In this case, the model changes are X
        // or O marks that must be shown in the grid; the property value is the target square.

        // Cast this object back to a TicTacToeSquare, then use the "getMarkAsString()" method of
        // the Controller to get the mark.)
        //

        String propertyName = e.getPropertyName();
        Object propertyValue = e.getNewValue();

        if ( (propertyName.equals(TicTacToeController.SET_SQUARE_X)) ||
             (propertyName.equals(TicTacToeController.SET_SQUARE_O)) ) {

            if (propertyValue instanceof TicTacToeSquare) {

                //
                // INSERT YOUR CODE HERE
              // propertyValue
               // controller.getMarkAsString();



                //

            }

        }

    }

    private int getSquareId(TicTacToeSquare square) {

        //
        // This "helper method" accepts a TicTacToeSquare object, containing the row and column of
        // the target square, and returns the ID of the corresponding TextView in the grid.  This
        // ID can then be used with "findViewById()" to acquire a reference to the TextView.
        //

        String name = "Square" + square.getRow() + square.getCol();
        int id = getResources().getIdentifier(name, "id", getPackageName());
        return id;

    }

    private String getViewName(View v) {

        //
        // This "helper method" accepts a View as an argument and returns its name as a string.
        //

        String fullName = getResources().getResourceName(v.getId());
        String name = fullName.substring(fullName.lastIndexOf("/") + 1);

        return name;

    }

    public void onClick(View v) {


        // This is the "onClick()" method shared by all TextViews in the grid.  It should get the
        // name of the clicked TextView, derive the row and column, encapsulate the corresponding
        // square as a TicTacToeSquare object, then hand it off to the Controller for processing.
        //

        String name = getViewName(v);
        Toast.makeText(getBaseContext(), name, Toast.LENGTH_SHORT).show(); // disable this later


        // YOUR CODE BELOW

        for (int i = 0 ; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String squareId = "square_" + i + j;
                int resourceId = getResources().getIdentifier(squareId, "id", getPackageName());
                //grid[i][j] = findViewById(resourceId);
                //grid[i][j].setOnClickListener(this);
            }
        }


        //

    }

    public void setResult(String text) {

        //
        // This method displays the argument string in the Result TextView
        //

        TextView result = (TextView)findViewById(R.id.result);
        result.setText(text);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will automatically handle clicks on
        // the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        if (id == R.id.action_reset) {
            controller.resetGame();
        }

        return super.onOptionsItemSelected(item);

    }

}