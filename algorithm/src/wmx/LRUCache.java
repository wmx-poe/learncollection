package leeCode.challengeStr;

import java.util.HashMap;

/**
 * @ProjectName: Algorithm
 * @Package: leeCode.challengeStr
 * @Author: wmx
 * @Description:
 * 设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。当缓存被填满时，它应该删除最近最少使用的项目。
 *
 * 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 * 示例:
 *
 * LRUCache cache = new LRUCache( 2 (缓存容量 ) );
 *  cache.put(1,1);
 *  cache.put(1,1);
 *  cache.put(2,2);
 *  cache.get(1);       // 返回  1
 *  cache.put(3,3);    // 该操作会使得密钥 2 作废
 *  cache.get(2);       // 返回 -1 (未找到)
 *  cache.put(4,4);    // 该操作会使得密钥 1 作废
 *  cache.get(1);       // 返回 -1 (未找到)
 *  cache.get(3);       // 返回  3
 *  cache.get(4);       // 返回  4
 * @Date: 2020/2/14 23:25
 * @Version: 1.0
 */
public class LRUCache {

    class ListNode {
        int key;
        int val;
        ListNode pre;
        ListNode next;
        ListNode(int key,int val){
            this.key=key;
            this.val=val;
        }
    }

    ListNode head=new ListNode(-1,-1);
    HashMap<Integer,ListNode> map=new HashMap<>();

    int capacity=0;

    int cacah_max=0;

    public LRUCache(int capacity) {
        head.pre=head;
        head.next=head;
        this.capacity=capacity;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        ListNode cur=map.get(key);
        removeNode(cur);
        movetoTail(cur);
        return cur.val;
    }

    public void put(int key, int value) {
        if(get(key)!=-1){
            map.get(key).val=value;
           return;
        }
        if(cacah_max>=capacity){
            map.remove(head.next.key);
            removeNode(head.next);
            cacah_max--;
        }
        cacah_max++;
        ListNode new_node=new ListNode(key,value);
        map.put(key,new_node);
        movetoTail(new_node);
    }

    private void removeNode(ListNode ln){
        ln.pre.next=ln.next;
        ln.next.pre=ln.pre;
    }

    private void movetoTail(ListNode ln){
        ln.next=head;
        head.pre.next=ln;
        ln.pre=head.pre;
        head.pre=ln;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("预期值为 1 ====="+cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println("预期值为 -1 ====="+cache.get(2));      // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println("预期值为 -1 ====="+cache.get(1));      // 返回 -1 (未找到)
        System.out.println("预期值为 3 ====="+cache.get(3));       // 返回  3
        System.out.println("预期值为 4 ====="+cache.get(4));       // 返回  4
    }

}
