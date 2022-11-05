import structure5.*;
import java.util.Comparator;

//augmented vector, has a sort method
public class MyVector<E> extends Vector<E> {
    
    public MyVector() {
        super();
    }
    /**
     * bubblesort algo
     * @param c the Comparator that will compare the Elements in the vector
     * @pre Vector isn't null
     * @post, sorts the Vector based on how the Comparator compares the two elements
     * 
     */
    public void sort(Comparator<E> c) {
        assert this != null: "this object is null";
        for(int index = size()-1; index > 0; index--) { //when index = 0, it is already the smallest value by default
            for(int i = 0; i < index; i++) {
                if(c.compare(get(i), get(i+1)) > 0) { //value of index i value is greater than index i+1 value
                    swap(i, i+1);
                }
            }
        }
    }
    //helper for above
    public void swap(int index1, int index2) {
        E temp = get(index1);
        set(index1, get(index2));
        set(index2, temp);
    }
}
