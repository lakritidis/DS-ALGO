package exercizes;

import lists.SinglyNodeList;
import lists.SNode;


// Υλοποίηση του Αλγορίθμου Ταξινόμησης
public class MergeSort {
    public static void main(String argv[]){
        System.out.println("Running MergeSort with Single linked list.");
        SinglyNodeList s = new SinglyNodeList();

        s.insertFirst(5);
        s.insertFirst(2);
        s.insertFirst(1);
        s.insertFirst(5);
        s.insertFirst(6);
        s.insertFirst(9);
        s.insertFirst(4);
        s.insertFirst(3);
        
        s.display();
        System.out.println();

        System.out.println("Added All the Elements");
        System.out.println("Beginning MergeSort");
        MergeSort sorter = new MergeSort();

        SinglyNodeList sortedS = sorter.mergeSort(s);
        sortedS.display();
        System.out.println();
        System.out.println("Finished MergeSort");
    }

    // Αναδρομική Υλοποίηση της Merge Sort πάνω στις μονά συνδεδεμένες λίστες 
    // με ακέραιους αριθμούς (SinglyNodeList).
    public SinglyNodeList mergeSort(SinglyNodeList s){
        // Το χειρόφρενο της αναδρομής
        if (s.size() <= 1){
            return s;
        }

        // Χωρίζουμε την λίστα σε δύο κομμάτια.
        int nMiddle = s.size() / 2;

        SinglyNodeList leftHalf = new SinglyNodeList();
        SNode leftHalfPtr = s.first();
        for (int i = 0; i < nMiddle; i++){
            leftHalf.insertLast(leftHalfPtr);
            leftHalfPtr = leftHalfPtr.getNext();    
        }
        
        leftHalf = mergeSort(leftHalf);

        SinglyNodeList rightHalf = new SinglyNodeList();
        SNode rightHalfPtr = leftHalfPtr;                
        for (int i = nMiddle; i < s.size(); i++){
            rightHalf.insertLast(rightHalfPtr);
            rightHalfPtr = rightHalfPtr.getNext();
        }
        
        rightHalf = mergeSort(rightHalf);
        
        // Στο τέλος επιστρέφουμε την συγχωνευμένει λίστα.
        return merge(leftHalf, rightHalf);
    }

    // Συγνώνευση των υπολιστών σε νέες λίστες
    public SinglyNodeList merge(SinglyNodeList a, SinglyNodeList b) {
        SinglyNodeList s = new SinglyNodeList();
        SNode aPtr = a.first();
        SNode bPtr = b.first();
        
        // 
        while (a.size() >= 1 && b.size() >= 1) {
            int aValue = Integer.parseInt(aPtr.toString());
            int bValue = Integer.parseInt(bPtr.toString());

            // Προσθέτουμε το μικρότερο αριθμό κάθε φορά. 
            if ( bValue < aValue ){
                s.insertLast(bPtr);
                bPtr = bPtr.getNext();
                b.removeFirst();                
            } else {
                s.insertLast(aPtr);
                aPtr = aPtr.getNext();
                a.removeFirst();                
            }            
        }

        // Άδειασμα της μια από της δυο λίστες.
        for (int i = 0; i < a.size(); i++) {
            s.insertLast(aPtr);
            aPtr = aPtr.getNext();
        }

        for (int i = 0; i < b.size(); i++) {
            s.insertLast(bPtr);
            bPtr = bPtr.getNext();
        }

        return s;
    }
}
