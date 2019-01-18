package kumar.aman.visualizedselectionsort.sorter;

import android.os.Handler;

import java.util.ArrayList;

public class Sorter implements SelectionSorter {

    private SortObserver sortObserver;
    private ArrayList<Integer> elements;

    public Sorter(ArrayList<Integer> elements, SortObserver sortObserver) {
        this.elements = elements;
        this.sortObserver = sortObserver;
        sort();
    }

    @Override
    public void sort() {

        final int n = elements.size();
        Handler handler1 = new Handler();
        for (int i = 0; i < n - 1; i++) {

            // for Current Minimum
            final int finalI = i;
            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    final int[] min_idx = {finalI};
                    sortObserver.onSelectCurrentMinimum(min_idx[0]);
                    sortObserver.onValidatingElements("Iteration "+(finalI +1)+" :  Set (" + elements.get(finalI) + ")as the current minimum, then iterate through the remaining unsorted elements to find the true minimum.",true);



                    for (int j = finalI + 1; j < n; j++) {

                        Handler handler =  new Handler();
                        final int finalJ = j;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                sortObserver.onSelectedSmallerThanCurrentMinimum(finalJ);
                                sortObserver.onValidatingElements("Check if (" + elements.get(finalJ) + ") is smaller than the current minimum (" + elements.get(min_idx[0]) + ").",false);
                                if (elements.get(finalJ) < elements.get(min_idx[0])) {
                                    sortObserver.onValidatingElements("set (" + elements.get(finalJ) + ") as the new minimum",false);
                                    min_idx[0] = finalJ;

                                }
                            }
                        },1000*j);

                    }


                    sortObserver.onValidatingElements("Swap the minimum ("+elements.get(min_idx[0])+ ") with the first unsorted element ("+elements.get(finalI)+").",false);
                    int temp = elements.get(min_idx[0]);
                    elements.set(min_idx[0], elements.get(finalI));
                    elements.set(finalI, temp);
                }
            },1000*i);


        }
        sortObserver.onFinishSorting(elements);
    }
}
