package kumar.aman.visualizedselectionsort.sorter;

import android.os.Handler;

import java.util.ArrayList;

public class Sorter implements SelectionSorter {

    private SortObserver sortObserver;
    private ArrayList<Integer> elements;

    public Sorter(ArrayList<Integer> elements, SortObserver sortObserver) {
        this.elements = elements;
        this.sortObserver = sortObserver;
        if (elements.size() == 1)
            sortObserver.onError("1 Element is always Sorted.");
        else
            sort();
    }

    @Override
    public void sort() {

        final int n = elements.size();
        Handler handler1 = new Handler();
        for (int i = 0; i < n; i++) {
            final int finalI = i;
            final int finalI1 = i;
            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int min_idx = finalI;
                    sortObserver.onValidatingElements("Iteration " + (finalI + 1) + " :  Set (" + elements.get(finalI) + ")as the current minimum, then iterate through the remaining unsorted elements to find the true minimum.", true);
                    for (int j = finalI + 1; j < n; j++) {
                        sortObserver.onValidatingElements("Check if (" + elements.get(j) + ") is smaller than the current minimum (" + elements.get(min_idx) + ").", false);
                        if (elements.get(j) < elements.get(min_idx)) {
                            sortObserver.onValidatingElements("set (" + elements.get(j) + ") as the new minimum", false);
                            min_idx = j;

                        }
                    }
                    sortObserver.onValidatingElements("Swap the minimum (" + elements.get(min_idx) + ") with the first unsorted element (" + elements.get(finalI) + ").", false);
                    int temp = elements.get(min_idx);
                    elements.set(min_idx, elements.get(finalI));
                    elements.set(finalI, temp);
                    sortObserver.onIterationCompleted(elements, finalI1);
                }
            }, 1000 * i);

        }
        sortObserver.onFinishSorting(elements);
    }
}
