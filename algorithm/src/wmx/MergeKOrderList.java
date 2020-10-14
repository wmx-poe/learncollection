package wmx;

/**
 * 排序k个有序链表（基于最小堆的k路归并算法）
 */
public class MergeKOrderList {

    public ListNode mergeKLists(ListNode[] lists) {

        if(lists.length==0){
            return null;
        }

        ListNode head=null;

        buildMaxHeap(lists);

        ListNode ln=getminValue(lists);

        while(ln != null){
             ln.next=head;
             head=ln;
             maxheapify(lists,0);
             ln=getminValue(lists);
        }

        if(head==null){
            return null;
        }

        return reverse(head);
    }

    ListNode Sentinel=new ListNode(Integer.MAX_VALUE);

    public void maxheapify(ListNode[] lists,int i){
        int left=left(i);
        int right=right(i);
        int min=i;
        if(lists[i]==null){
            lists[i]=Sentinel;
        }
        if(left<lists.length&&lists[left]==null){
            lists[left]=Sentinel;
        }

        if(right<lists.length&&lists[right]==null){
            lists[right]=Sentinel;
        }

        if(left<lists.length&&lists[left].val<lists[min].val){
            min=left;
        }
        if(right<lists.length&&lists[right].val<lists[min].val){
            min=right;
        }
        if(min!=i){
            swapListNode(lists,min,i);
            maxheapify(lists,min);
        }
    }

    private void buildMaxHeap(ListNode[] lists){
        int leafNode=lists.length/2;
        for(int i=leafNode;i>=0;i--){
            maxheapify(lists,i);
        }
    }

    private void swapListNode(ListNode[] lists,int i,int j){
        ListNode tmp=lists[i];
        lists[i]=lists[j];
        lists[j]=tmp;
    }

    private int left(int i){
        return 2*i+1;
    }

    private int right(int i){
        return 2*i+2;
    }

    private ListNode getminValue(ListNode[] lists){
        ListNode minNode=null;
        if(lists[0]!=Sentinel){
            minNode=lists[0];
            lists[0]=lists[0].next;
            minNode.next=null;
        }
        return minNode;
    }

    private ListNode reverse(ListNode ln){
        ListNode head=null;
        while(ln!=null){
            ListNode tmp=ln;
            ln=ln.next;
            tmp.next=head;
            head=tmp;

        }
        return head;
    }
    public ListNode structure(int i){
        ListNode head=null;
        for(int k=i;k>0;k--){
            ListNode tmp=head;
            head=new ListNode(k);
            head.next=tmp;
        }
        return  head;
    }


    public static void main(String[] args) {

        MergeKOrderList mkol=new MergeKOrderList();
        ListNode[] ln=new ListNode[0];
       /* ln[0]= mkol.structure(5);
        ln[1]= mkol.structure(5);
        ln[2]= null;
        ln[3]= null;
        ln[4]= mkol.structure(5);*/
        ListNode fhead=mkol.mergeKLists(ln);
        System.out.print("xyz");
    }

      class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

    }
}
