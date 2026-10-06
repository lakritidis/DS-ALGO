package exercizes;
import lists.SinglyNodeList;
import Dictionaries.Comparator;
import lists.SNode;

// Στατική Υλοποίηση του Αλγορίθμου Ταξινόμησης
public class MergeSort {
    static public void main(String argv[]){
        System.out.println("Running MergeSort with Single linked list.");
        SinglyNodeList s = new SinglyNodeList();

        s.insertFirst(5);
        s.insertFirst(2);
        s.insertFirst(1);
        s.insertFirst(6);
        s.insertFirst(9);
        s.insertFirst(4);
        s.insertFirst(3);
        
        System.out.println("Added All the Elements");
        
        SinglyNodeList sortedS = mergeSort(s);
        System.out.println();
        // sortedS.display();
    }

    // Αναδρομική Υλοποίηση της Merge Sort πάνω στις μονά συνδεδεμένες λίστες (SinglyNodeList).
    static public SinglyNodeList mergeSort(SinglyNodeList s){
        int nMiddle = s.size() / 2;

        // SNode nextFromMiddleNode = middleNode.getNext();

        SinglyNodeList leftHalf = new SinglyNodeList();
        SNode leftHalfPtr = s.first();
        for (int i = 0; i < nMiddle; i++){
            leftHalf.insertLast(leftHalfPtr);
            leftHalfPtr = leftHalfPtr.getNext();    
        }
        System.out.println("Printing Left Half of the List");
        leftHalf.display();
        // leftHalf = mergeSort(leftHalf);

        SinglyNodeList rightHalf = new SinglyNodeList();
        SNode rightHalfPtr = leftHalfPtr;                
        for (int i = nMiddle; i < s.size(); i++){
            rightHalf.insertLast(rightHalfPtr);
            rightHalfPtr = rightHalfPtr.getNext();
        }
        System.out.println("Printing Right Half of the List");
        rightHalf.display();
        
        // rightHalf = mergeSort(rightHalf);
        
        
        return merge(leftHalf, rightHalf);
    }

    static public SinglyNodeList merge(SinglyNodeList a, SinglyNodeList b) {
        SinglyNodeList s = new SinglyNodeList();

        return s;
    }

}
