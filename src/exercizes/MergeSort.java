package exercizes;

import lists.SinglyNodeList;
import lists.SNode;

import lists.DoublyNodeList;
import lists.DNode;

// Υλοποίηση του Αλγορίθμου Ταξινόμησης
public class MergeSort {
    public static void main(String argv[]){
        System.out.println("Running MergeSort with Single linked list.");
        SinglyNodeList s1 = new SinglyNodeList();

        s1.insertFirst(5);
        s1.insertFirst(2);
        s1.insertFirst(1);
        s1.insertFirst(5);
        s1.insertFirst(6);
        s1.insertFirst(9);
        s1.insertFirst(4);
        s1.insertFirst(3);
        
        s1.display();
        System.out.println();

        System.out.println("Added All the Elements");
        System.out.println("Beginning MergeSort");
        MergeSort sorter = new MergeSort();

        SinglyNodeList sortedS1 = sorter.mergeSort(s1);
        sortedS1.display();
        System.out.println();
        System.out.println("Finished MergeSort");

        System.out.println("Running MergeSort with Single linked list.");
        DoublyNodeList s2 = new DoublyNodeList();
        DNode n1 = s2.insertAfter(s2.first(), 5);
        DNode n2 = s2.insertBefore(n1, 2);
        DNode n3 = s2.insertBefore(n2, 1);
        DNode n4 = s2.insertBefore(n3, 5);
        DNode n5 = s2.insertBefore(n4, 6);
        DNode n6 = s2.insertBefore(n5, 9);
        DNode n7 = s2.insertBefore(n6, 4);
        DNode n8 = s2.insertBefore(n7, 3);
        System.out.println("Added All the Elements");
        s2.display();
        
        System.out.println("Beginning MergeSort");
        
        DoublyNodeList sortedS2 = sorter.mergeSort(s2);
        sortedS2.display();

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

    // Συγχώνευση των υπολιστών σε νέες λίστες
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

    // Αναδρομική Υλοποίηση της Merge Sort πάνω στις διπλά συνδεδεμένες λίστες 
    // με ακέραιους αριθμούς (SinglyNodeList). 
    public DoublyNodeList mergeSort(DoublyNodeList s){
        // Το χειρόφρενο της αναδρομής
        if (s.size() <= 1){
            return s;
        }

        // Χωρίζουμε την λίστα σε δύο κομμάτια.
        int nMiddle = s.size() / 2;

        DoublyNodeList leftHalf = new DoublyNodeList();
        DNode leftHalfPtr = s.first(); // Επιστρέφη τον επόμενο Κόμβο από αυτόν του `Head`
        for (int i = 0; i < nMiddle; i++){
            leftHalf.insertAfter(leftHalf.last(), leftHalfPtr.getElement());
            leftHalfPtr = leftHalfPtr.getNext();
        }
        
        leftHalf = mergeSort(leftHalf);

        DoublyNodeList rightHalf = new DoublyNodeList();
        DNode rightHalfPtr = leftHalfPtr;
        for (int i = nMiddle; i < s.size(); i++){
            rightHalf.insertAfter(rightHalf.last(), rightHalfPtr.getElement());
            rightHalfPtr = rightHalfPtr.getNext();
        }

        rightHalf = mergeSort(rightHalf);
        
        return merge(leftHalf, rightHalf);
    }

    public DoublyNodeList merge(DoublyNodeList a, DoublyNodeList b){
        DoublyNodeList s = new DoublyNodeList();
        DNode aPtr = a.first();
        DNode bPtr = b.first();

        while (a.size() >= 1 && b.size() >= 1) {
            int aValue = Integer.parseInt(aPtr.toString());
            int bValue = Integer.parseInt(bPtr.toString());


            // Προσθέτουμε το μικρότερο αριθμό κάθε φορά. 
            if ( bValue < aValue ){
                s.insertAfter(s.last(), bValue);
                bPtr = bPtr.getNext();
                b.remove(b.first());
            } else {
                s.insertAfter(s.last(), aValue);
                aPtr = aPtr.getNext();
                a.remove(a.first());
            }
        }        

        // Άδειασμα της μια από της δυο λίστες.
        for (int i = 0; i < a.size(); i++) {
            s.insertAfter(s.last(), aPtr.getElement());
            aPtr = aPtr.getNext();
        }

        for (int i = 0; i < b.size(); i++) {
            s.insertAfter(s.last(), bPtr.getElement());
            bPtr = bPtr.getNext();
        }

        return s;
    }
}
