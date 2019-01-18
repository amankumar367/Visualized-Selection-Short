package kumar.aman.visualizedselectionsort;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import kumar.aman.visualizedselectionsort.sorter.SortObserver;
import kumar.aman.visualizedselectionsort.sorter.Sorter;

public class SortVizActivity extends AppCompatActivity implements SortObserver {

    Sorter sorter;
    ArrayList<Integer> elements = new ArrayList<>();
    TextView one, two, three, four, five;
    LinearLayout logContainer;
    ImageView backImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_viz);
        init();
        handleIntentData();
        onClicks();
    }

    private void onClicks() {
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void init() {
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        backImage = findViewById(R.id.back);
        logContainer = findViewById(R.id.log_container);
    }

    private void handleIntentData() {
        String elements = getIntent().getStringExtra("elements");
        if (elements != null) {
            sorter = new Sorter(getIntElementsFromString(elements), this);
        }
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onValidatingElements(String message,Boolean isNewIteration) {
        TextView newLine = new TextView(this);
        LinearLayout.LayoutParams params = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5,5,5,10);
        newLine.setLayoutParams(params);
        newLine.setText(message);
        newLine.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_star_black_24dp,0,0,0);
        newLine.setCompoundDrawablePadding(10);
        if(isNewIteration)
            newLine.setTypeface(null, Typeface.BOLD);
        logContainer.addView(newLine);
    }

    ArrayList<Integer> getIntElementsFromString(String elementsString) {
        if (elementsString.equals("random")) {
            Random r = new Random();
            for (int i = 0; i < 5; i++) {
                int random = r.nextInt(100 - 28) - 30;
                setElementsToTextView(i, random);
                elements.add(random);
            }
        } else {
            int i = 0;
            for (String s : elementsString.split(",")) {
                setElementsToTextView(i, Integer.parseInt(s));
                elements.add(Integer.parseInt(s));
                i++;
            }
        }
        return elements;

    }

    private void setElementsToTextView(int position, int value) {
        switch (position) {
            case 0: one.setText(String.valueOf(value));
                break;
            case 1: two.setText(String.valueOf(value));
                break;
            case 2: three.setText(String.valueOf(value));
                break;
            case 3: four.setText(String.valueOf(value));
                break;
            case 4: five.setText(String.valueOf(value));
                break;
            default: break;
        }
    }

    @Override
    public void onFinishSorting(ArrayList<Integer> sortedElements) {

    }

    @Override
    public void onSelectCurrentMinimum(int position) {
        switch (position) {
            case 0: one.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                    one.setTextColor(getResources().getColor(android.R.color.white));
                break;
            case 1: two.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                two.setTextColor(getResources().getColor(android.R.color.white));
                break;
            case 2: three.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                three.setTextColor(getResources().getColor(android.R.color.white));
                break;
            case 3: four.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                four.setTextColor(getResources().getColor(android.R.color.white));
                break;
            case 4: five.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                five.setTextColor(getResources().getColor(android.R.color.white));
                break;
            default: break;
        }
    }

    @Override
    public void onSelectedSmallerThanCurrentMinimum(int position) {
        switch (position) {
            case 0: one.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                one.setTextColor(getResources().getColor(android.R.color.white));
                break;
            case 1: two.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                two.setTextColor(getResources().getColor(android.R.color.white));
                break;
            case 2: three.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                three.setTextColor(getResources().getColor(android.R.color.white));
                break;
            case 3: four.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                four.setTextColor(getResources().getColor(android.R.color.white));
                break;
            case 4: five.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                five.setTextColor(getResources().getColor(android.R.color.white));
                break;
            default: break;
        }
    }

    @Override
    public void onSwap(int item1Position, int item2Position) {

    }
}
