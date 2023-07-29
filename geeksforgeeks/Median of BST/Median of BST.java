//{ Driver Code Starts
//Initial Template for Java

import java.util.LinkedList; 
import java.util.Queue; 
import java.io.*;
import java.util.*;

class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left=null;
        right=null;
    }
}

class GfG {
    
    static Node buildTree(String str){
        
        if(str.length()==0 || str.charAt(0)=='N'){
            return null;
        }
        
        String ip[] = str.split(" ");
        // Create the root of the tree
        Node root = new Node(Integer.parseInt(ip[0]));
        // Push the root to the queue
        
        Queue<Node> queue = new LinkedList<>(); 
        
        queue.add(root);
        // Starting from the second element
        
        int i = 1;
        while(queue.size()>0 && i < ip.length) {
            
            // Get and remove the front of the queue
            Node currNode = queue.peek();
            queue.remove();
                
            // Get the current node's value from the string
            String currVal = ip[i];
                
            // If the left child is not null
            if(!currVal.equals("N")) {
                    
                // Create the left child for the current node
                currNode.left = new Node(Integer.parseInt(currVal));
                // Push it to the queue
                queue.add(currNode.left);
            }
                
            // For the right child
            i++;
            if(i >= ip.length)
                break;
                
            currVal = ip[i];
                
            // If the right child is not null
            if(!currVal.equals("N")) {
                    
                // Create the right child for the current node
                currNode.right = new Node(Integer.parseInt(currVal));
                    
                // Push it to the queue
                queue.add(currNode.right);
            }
            i++;
        }
        
        return root;
    }
    static void printInorder(Node root)
    {
        if(root == null)
            return;
            
        printInorder(root.left);
        System.out.print(root.data+" ");
        
        printInorder(root.right);
    }
    
	public static void main (String[] args) throws IOException{
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        
	        int t=Integer.parseInt(br.readLine());
    
	        while(t > 0){
	            String s = br.readLine();
    	    	Node root = buildTree(s);
    	    	
                Tree g = new Tree();
                float answer = g.findMedian(root);
                if(answer-(int)answer == 0)
        		    System.out.println((int)answer);
        		else
        		    System.out.println(answer);
                t--;
            
        }
    }
}

// } Driver Code Ends

//User function Template for Java

// Approach 1
class Tree
{
    public static float findMedian(Node root)
    {
        // code here.
        ArrayList<Integer> list=new ArrayList<>();
        inorder(root, list);
        int n=list.size();
        if(n%2!=0){
            return list.get((n+1)/2-1);
        }else{
            int mid1=list.get(n/2-1);
            int mid2=list.get(n/2+1-1);
            return ((float)(mid1+mid2))/2;
        }
    }
    
    public static void inorder(Node root, ArrayList<Integer> list){
        if(root==null)
            return;
        inorder(root.left, list);
        list.add(root.data);
        inorder(root.right, list);
    }
}

// Approach 2
class Tree
{
    public static float findMedian(Node root)
    {
        // code here.
        int x=0,y=0;
        int n = countNodes(root);
        if(n%2!=0){
             int ans[] = new int[1];
            ans[0] = 0;
             find(root, 1+ n/2, new int[]{0}, ans);
             return (ans[0]);
            
        }
        else
        {
        int ans1[] = new int[1];
        int ans2[] = new int[1];
            find(root, n/2, new int[]{0}, ans1);
              find(root, 1 + n/2, new int[]{0},ans2);
        return (ans1[0]+ans2[0]) / 2.0f;
        }
    }
  static int countNodes(Node n)
    {
        // this function returns number of nodes in tree
        if(n==null) return 0;
        return 1 + countNodes(n.left) + countNodes(n.right);
    }
    // static count=0;
    static void find(Node node, int k, int[] count, int arr[])
    {
    if (node == null)
            return;

     find(node.left, k, count,arr);

        count[0]++;
        if (count[0] == k) {
            arr[0] = node.data;
            return;
        }

      find(node.right, k, count, arr);

    }
    
}

// Approach 3
class Tree
{
    public static float findMedian(Node root)
    {
        int cnt =  morris(root);
         Node curr = root, preptr = null, prev = null;
        int currcnt=0;
        while(curr!=null)
        {
            if(curr.left==null)
            {
                // if(k==1) return curr;
                // k--;
                //odd
                // Odd case
                  currcnt++;
                if (cnt % 2 != 0 && currcnt == (cnt + 1) / 2)
                    return curr.data;
                    
                    //even case
                 else if (cnt % 2 == 0 && currcnt == (cnt / 2) + 1)
                    return (float) (preptr.data + curr.data) / 2;
                    
                    // Update prev for even no. of nodes
                preptr = curr;
                
                curr=curr.right;
            }
            
           
            else
            {
                //move as right as poss
                 prev = curr.left;
                while(prev.right!=null && prev.right!=curr)
                prev= prev.right;
                
                if(prev.right==null)
                {
                    prev.right=curr; // make thread
                    curr=curr.left; //do the same for remaining lst
                    
                }
                else //prev.right!=null // thread alreadt there
                {
                    prev.right=null;
                    //access the node since inorder
                    // if(k==1) return curr;
                    //  k--; 
                    // cnt++;
                    currcnt++;
                if (cnt % 2 != 0 && currcnt == (cnt + 1) / 2)
                    return curr.data;
                    
                    //even case
                 else if (cnt % 2 == 0 && currcnt == (cnt / 2) + 1)
                    return (float) (preptr.data + curr.data) / 2;
                    
                      preptr = curr;
                      
                    curr= curr.right;
                }
            }
        }
          return -1;
    }
     static int morris(Node curr)
    {
        int cnt=0;
         while(curr!=null)
        {
            if(curr.left==null)
            {
                // if(k==1) return curr;
                // k--;
                cnt++;
                curr=curr.right;
            }
            
           
            else
            {
                //move as right as poss
                 Node prev = curr.left;
                while(prev.right!=null && prev.right!=curr)
                prev= prev.right;
                
                if(prev.right==null)
                {
                    prev.right=curr; // make thread
                    curr=curr.left; //do the same for remaining lst
                    
                }
                else //prev.right!=null // thread alreadt there
                {
                    prev.right=null;
                    //access the node since inorder
                    // if(k==1) return curr;
                    //  k--; 
                    cnt++;
                    curr= curr.right;
                }
            }
        }
        return cnt;
    }
}