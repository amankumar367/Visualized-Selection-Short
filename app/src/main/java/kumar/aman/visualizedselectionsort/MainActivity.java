package kumar.aman.visualizedselectionsort;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText inputData;
    Button randomSortButton, sortButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onClicks();
    }

    private void init() {
        inputData = findViewById(R.id.elements_entered);
        randomSortButton = findViewById(R.id.btn_random_sort);
        sortButton = findViewById(R.id.btn_sort);
    }

    public void onClicks() {
        randomSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSortActivity("random");
            }
        });

        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputData.getEditableText().toString().equals("")) {
                    startSortActivity(inputData.getEditableText().toString());
                } else {
                    Toast.makeText(MainActivity.this, "Please Enter Elements", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startSortActivity(String random) {
        Intent intent = new Intent(this, SortVizActivity.class);
        intent.putExtra("elements", random);
        startActivity(intent);
    }

}
