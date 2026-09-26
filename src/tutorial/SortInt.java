package tutorial;

import java.util.*;


public class SortInt

{
    static int ctnc = 0;
    static int ctne = 0;
    

    public static void SelectionSort(int[] a) {
        int N = a.length;
        System.out.println("a");
        for (int k = 0; k < a.length; ++k ){
            System.out.print(a[k]+" ");
        }
        System.out.println("\n\n");
       
        for (int i = 0; i < N-1; i++) {
            int min = i;
            System.out.println("i="+ i);
            System.out.print("j=");
            for( int j = i+1; j < N; j++ ){
                int v = a[j];
                ctnc++;
                if( v < a[min] ) {
                    min = j;
                }
                System.out.print(" "+j);
            }
            System.out.println("");
            System.out.println("a[i]="+a[i]+" pos_min= "+min+" a[min]="+a[min]);
            int tmp = a[i];
            a[i] = a[min];
            a[min] = tmp;
            ctne++;
            
            for (int k = 0; k < a.length; ++k ){
                System.out.print(a[k]+" ");
            }
            System.out.println("\n");
            
        }
    }

    public static void InsertionSort(int[] a) {
        int N = a.length;
        
        for (int k = 0; k < a.length; ++k ){
            System.out.print(a[k]+" ");
        }
        System.out.println("\n");
        
        for (int i = 1; i < N; i++) {
            int j = i;
            int v = a[i];
            System.out.println("i=j="+i);
            System.out.println("v=a["+i+"]="+v+" "+" a["+(j-1)+"]="+a[j-1]);
            ctnc++;
            while (j > 0 && v < a[j-1]  ){
                a[j] = a[j-1];
                System.out.println(" == j-1="+(j-1)+" a["+(j-1)+"]="+a[j-1]+" --> j="+j+" a["+j+"]="+a[j]);
                j--;
                ctne++;
                ctnc++;
            }
            a[j] = v;
            
            System.out.println("j="+j+" a["+j+"]="+a[j]);
            for (int k = 0; k < a.length; ++k ){
                System.out.print(a[k]+" ");
            }
            
            System.out.println("\n--------------");
            

        }
    }

    public static void ImprovedBubbleSort(int[] a) {
        int N = a.length;
        
        for (int k = 0; k < a.length; ++k ){
            System.out.print(a[k]+" ");
        }
        System.out.println("\n");
        
        boolean swapped;
        for (int i = 0; i < N-1; i++) {

            swapped = false;

            System.out.println("i="+i+" ");
            for (int k = 0; k < a.length; ++k ){
                System.out.print(a[k]+" ");
            }
            System.out.println("");
            for( int j = N-1; j > i; j-- ){
                System.out.println("j="+j+" a["+j+"]="+a[j]+" a["+(j-1)+"]="+a[j-1]);
                int v = a[j];
                ctnc++;
                if( v < a[j-1] ){
                    a[j] = a[j-1];
                    a[j-1] = v;
                    swapped = true;
                    ctne++;
                }
                
            }
            
            
            for (int k = 0; k < a.length; ++k ){
                System.out.print(a[k]+" ");
            }
            System.out.println("\n--------------");
            
            if(swapped==false){
                break;
            }
        }

    }

    public static void BubbleSort(int[] a) {
        int N = a.length;
        
        for (int k = 0; k < a.length; ++k ){
            System.out.print(a[k]+" ");
        }
        System.out.println("\n");
        

        for (int i = 0; i < N-1; i++) {
            System.out.println("i="+i+" ");
            for (int k = 0; k < a.length; ++k ){
                System.out.print(a[k]+" ");
            }
            System.out.println("");
            for( int j = N-1; j > i; j-- ){
                System.out.println("j="+j+" a["+j+"]="+a[j]+" a["+(j-1)+"]="+a[j-1]);
                int v = a[j];
                ctnc++;
                if( v < a[j-1] ){
                    a[j] = a[j-1];
                    a[j-1] = v;
                    ctne++;
                }
                
            }
            
            
            for (int k = 0; k < a.length; ++k ){
                System.out.print(a[k]+" ");
            }
            System.out.println("\n--------------");
            
        }

    }

    public static void QuickSort(int[] a, int left, int right) {
        int N = a.length;
        int i = left - 1;
        int j = right;
        int v = a[right]; // το στοιχείο διαχωρισμού
        
        System.out.print("Το αριστερό στοιχείο: "+a[left]+" left="+left);
        System.out.println(" Το στοιχείο διαχωρισμού (δεξιό): "+a[right]+" right="+right);
        
        int temp = 0;
        while(true){
            // όσο το στοιχείο στην θέση i είναι μικρότερο από το v (το στοιχείο διαχωρισμού) το i προωθείται
            // θα σταματήσει όταν ανακαλύψει στοιχείο a[i] μεγαλύτερο του v
            // ή όταν φτάσει στην θέση right
            int tmp = 0;
            while( a[++i] < v ){
               
                tmp =1;
                if (i == right){
                    break;
                }
            }
            
            // όσο το στοιχείο στην θέση j είναι μεγαλύτερο του v (το στοιχείο διαχωρισμού) το j μειώνεται
            // θα σταματήσει όταν ανακαλύψει στοιχείο a[j] μικρότερο του v
            while( v < a[--j]){
                
                tmp=1;
                if (j == left){
                    break;
                }
            }
            

            if (i >= j){
                break;
            }

            System.out.println(" Στοιχεία που ανταλλάσουν θέσεις : "+a[i]+" και "+a[j]+" i="+i+" j="+j);
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;

            System.out.print("---");
            for (int k = 0; k < a.length; ++k ){
                System.out.print(a[k]+" ");
            }
            System.out.println(" ");


        }
        System.out.println(" Τοποθέτηση του στοιχείου διαχωρισμού: "+a[i]+" και "+a[right]+" i="+i+" right="+right);
        System.out.println(" ");
        temp = a[i];
        a[i] = a[right];
        a[right] = temp;


        
        for (int k = 0; k < a.length; ++k ){
            System.out.print(a[k]+" ");
        }
        System.out.println(" ");
        
        
        if (left < i-1){
            QuickSort(a, left, i-1);
        } /*else{
            System.out.println("-- left="+left+" i-1="+(i-1));
        }*/

        if (i+1 < right){
            QuickSort(a,i+1,right);
        } /* else{
            System.out.println("-- right="+right+" i-1="+(i+1));
        }*/
        
    }

    public static void mergeSort(int[] a, int left, int right, int[] b){

        int l, r;
        
        int middle = (left + right)/2;
        System.out.println("left: "+left+" right: "+right+" middle:"+middle);
        if (left < middle)// αναδρομική ταξινόμηση του αριστερού μέρους, εάν υφίσταται
            mergeSort(a, left, middle, b);
        if (middle+1 < right)   // αναδρομική ταξινόμηση του δεξιού μέρους, εάν υφίσταται
            mergeSort(a, middle+1, right, b);
        // και βασίλευε (conquer )...συγχώνευση των δύο ήδη διατεταγμένων ακολουθιών
        for (l = left; l < middle+1; l++)// αντιγραφή του αριστερού διατεταγμένου μέρους
            b[l] = a[l];
        l=left;
        for (r = middle; r < right; r++) // αντιγραφή του δεξιού διατεταγμένου μέρους
            b[right+middle-r] = a[r+1];



        // συγχώνευση των δύο τμημάτων πίσω στον a[ ]
        System.out.println("merging.....");
        System.out.println("b - array");
        for (int k = 0; k < a.length; ++k ){
            System.out.print(b[k]+" ");
        }
        System.out.println("");
        for (int k = left; k <= right; k++){
            System.out.println("l= "+l+" r= "+r);
            if (b[r] < b[l]){
                a[k] = b[r--];    // η αρχική τιμή του r είναι right

            } else {
                a[k] = b[l++];   // η αρχική τιμή του l είναι left

            }

        }
        
        System.out.print("a = ");
        for (int k = 0; k < a.length; ++k ){
            System.out.print(a[k]+" ");
        }
       
        System.out.println(" ");
        System.out.println("end recursion");
        System.out.println(" ");
        
    }


    public static void main(String[] args) {

        
        Scanner myObj = new Scanner(System.in);  // Δημιουργία ενός Scanner object
   
        //int a[] = new int[12];
       // int [] a = {63,	30,	36,	31,	12,	50,	35,	5};
        int [] a = {82,	30,	36,	31,	79,	50,	35,	25,	75,	59,	43,	17};
       // int [] a = {82,30,36,31,79,50,35,25,75,59,43,17};
        //int [] a = {30,36,22,11};
       // int [] a = {36,30,22,11};
       //int [] a = {11,22,30,36,55,66,88,99,900,999};
       //int [] a = {999,900,99,88,66,55,36,30,22,11};
       //int [] a = {1,1,1,1,1,1,1,1,1,1};
        int b[] = new int[12];

        /*
        Random rd=new Random();
	
        for(int i=0;i<a.length;i++)
	      a[i]=rd.nextInt(100);
          */
    
        System.out.println("Array to sort\n");
        for(int i=0;i<a.length;i++){
          System.out.print(a[i]+" ");
        }
     

        System.out.println("\n");

        System.out.println("Choose Sorting Algorithm");
        System.out.println("1. Selection sort");
        System.out.println("2. Bubble sort");
        System.out.println("3. Improved Bubble sort");
        System.out.println("4. Insertion sort");
        System.out.println("5. Quick sort");
        System.out.println("6. Merge sort");
        System.out.println("Make your selection: 1-6");
        System.out.println(" ");
        int selection = 0;
        selection= myObj.nextInt();
        switch (selection) {
            case 1:
                System.out.println("Selection sort");
                SelectionSort(a);
                break;
            case 2:
                System.out.println("Bubble sort");
                BubbleSort(a);
                break;
            case 3:
                System.out.println("Bubble sort");
                ImprovedBubbleSort(a);
                break;
            case 4:
                System.out.println("Insertion sort");
                InsertionSort(a);
                break;
            case 5:
                System.out.println("Quick sort");
                QuickSort(a,0,11);
                break;
            case 6:
                System.out.println("Merge sort");
                mergeSort(a,0,11, b);
                break;
            default:
                System.out.println("Quick sort");
                QuickSort(a,0,11);
        }

        System.out.println(" ");
        for (int j = 0; j < a.length; ++j ){
            System.out.print(a[j]+" ");
        }
        
    }
}

