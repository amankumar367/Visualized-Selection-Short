package kumar.aman.visualizedselectionsort.sorter;

import java.util.ArrayList;

public interface SortObserver {
    void onError(String message);
    void onValidatingElements(String message,Boolean isNewIteraton);
    void onFinishSorting(ArrayList<Integer> sortedElements);
    void onIterrationCompleted(ArrayList<Integer> sortedElements,int positionOnCompleted);
}
