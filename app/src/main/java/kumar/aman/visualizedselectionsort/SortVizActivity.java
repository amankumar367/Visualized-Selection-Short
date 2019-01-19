package kumar.aman.visualizedselectionsort;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import kumar.aman.visualizedselectionsort.sorter.SortObserver;
import kumar.aman.visualizedselectionsort.sorter.Sorter;

public class SortVizActivity extends AppCompatActivity implements SortObserver {

    Sorter sorter;
    ArrayList<Integer> elements = new ArrayList<>();
    LinearLayout logContainer, elementContainer;
    ImageView backImage;
    TextView headerTitle;
    ScrollView logScrollView;
    HorizontalScrollView textBoxScrollView;

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
        backImage = findViewById(R.id.back);
        logContainer = findViewById(R.id.log_container);
        elementContainer = findViewById(R.id.elements_container);
        headerTitle = findViewById(R.id.header_title);
        logScrollView = findViewById(R.id.log_scrollview);
        textBoxScrollView = findViewById(R.id.text_box_scrollview);
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
    public void onValidatingElements(String message, Boolean isNewIteration) {
        TextView newLine = new TextView(this);
        LinearLayout.LayoutParams params = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 5, 5, 10);
        newLine.setLayoutParams(params);
        newLine.setText(message);
        newLine.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_star_black_24dp, 0, 0, 0);
        newLine.setCompoundDrawablePadding(10);
        if (isNewIteration)
            newLine.setTypeface(null, Typeface.BOLD);
        logContainer.addView(newLine);
    }

    ArrayList<Integer> getIntElementsFromString(String inputString) {
        String elementsString = inputString.replace(" ","");
        if (elementsString.equals("random")) {
            Random r = new Random();
            for (int i = 0; i < 15; i++) {
                int random = r.nextInt(100 - 28) - 30;
                drawTextBox(i, String.valueOf(random));
                setElementsToTextView(i, random);
                elements.add(random);
            }
        } else {
            int i = 0;
            for (String s : elementsString.split(",")) {
                try {
                    drawTextBox(i, s);
                    setElementsToTextView(i, Integer.parseInt(s));
                    elements.add(Integer.parseInt(s));
                    i++;
                } catch (Exception e) {
                    onError("Invalid Input Elements.....");
                    return elements;
                }
            }
        }
        setHeaderTitle(elements.size());
        return elements;

    }

    private void drawTextBox(int position, String elementValue) {
        TextView newBoxElement = new TextView(this);
        Resources r = getResources();
        int width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, r.getDisplayMetrics());
        int height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, r.getDisplayMetrics());
        LinearLayout.LayoutParams params = new
                LinearLayout.LayoutParams(width, height);
        params.setMargins(5, 5, 5, 5);
        newBoxElement.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        newBoxElement.setLayoutParams(params);
        newBoxElement.setGravity(Gravity.CENTER);
        newBoxElement.setText(elementValue);
        newBoxElement.setId(position);
        newBoxElement.setBackgroundDrawable(getResources().getDrawable(R.drawable.text_square_box));
        newBoxElement.setFocusable(true);
        elementContainer.addView(newBoxElement);
    }

    private void setElementsToTextView(int position, int value) {
        ((TextView) findViewById(position)).setText(String.valueOf(value));

    }

    @Override
    public void onFinishSorting(ArrayList<Integer> sortedElements) {

    }

    @Override
    public void onIterationCompleted(ArrayList<Integer> sortedElements, int positionOnCompleted) {
        for (int i = 0; i < sortedElements.size(); i++) {
            setElementsToTextView(i, sortedElements.get(i));
            setCompletedElement(positionOnCompleted);

        }
    }

    void setCompletedElement(int position) {
        findViewById(position).setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        ((TextView) findViewById(position)).setTextColor(getResources().getColor(android.R.color.white));
        if(position%4==0)
            scrollBoxElementTo(position);
        logScrollView.fullScroll(View.FOCUS_DOWN);
    }

    void setHeaderTitle(int size){
        headerTitle.setText("Total Elements = "+size +"\n\n Sorting Steps....");
    }

    void scrollBoxElementTo(final int position){
        textBoxScrollView.post(new Runnable() {
            public void run() {
                if(position>=4)
                    textBoxScrollView.smoothScrollTo(elementContainer.getChildAt(position-2).getRight(), 0);
            }
        });
    }

}
