//{ Driver Code Starts
import java.io.*;
import java.util.*;

class GFG {
    
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //testcases
        int t = Integer.parseInt(br.readLine().trim()); 
        while(t-->0){
            
            //size of array
            int N = Integer.parseInt(br.readLine().trim()); 
            int arr[] = new int[N];
            String inputLine[] = br.readLine().trim().split(" ");

            //adding elements to the array
            for(int i = 0 ; i < N; i++){
                arr[i]=Integer.parseInt(inputLine[i]); 
            }
            int P= Integer.parseInt(br.readLine().trim());
            //calling frequncycount() function
            Solution ob = new Solution();
            ob.frequencyCount(arr, N, P); 
            
            //printing array elements
            for(int i = 0; i < N ; i++)
                System.out.print(arr[i] + " " );
            System.out.println();
        }
    }
}

// } Driver Code Ends

// Time n
// Space n 
class Solution{
    //Function to count the frequency of all elements from 1 to N in the array.
    public static void frequencyCount(int arr[], int N, int P)
    {
        // code here
        int[] ar = new int[N];
        for(int val: arr){
            if(val<=N){
                ar[val-1]=ar[val-1]+1;
            }
        }
        for(int i=0;i<ar.length;i++){
            arr[i]=ar[i];
        }
    }
}

// Time n
// Space 1
class Solution{
    //Function to count the frequency of all elements from 1 to N in the array.
    public static void frequencyCount(int arr[], int N, int P)
    {
        // code here
        P++;
        for(int i=0; i<arr.length; i++){
            int idx = arr[i]%P-1;
            if(idx>=0 && idx<N){
                arr[idx]=arr[idx]+P;
            }
        }
        for(int i=0;i<arr.length;i++){
            arr[i]=arr[i]/P;
        }
    }
}
