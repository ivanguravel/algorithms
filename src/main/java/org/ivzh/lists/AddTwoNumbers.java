package org.ivzh.lists;

import java.util.*;

 // https://leetcode.com/problems/add-two-numbers/
class AddTwoNumbers {
   public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum=l1.val+l2.val;
        int temp=0;
        if(sum>9){
            sum=sum-10;
            temp=1;
        }
        ListNode head=new ListNode();
        ListNode l3=new ListNode(sum);
        head=l3;
        l1=l1.next;l2=l2.next;
        while(l1!=null && l2!=null){ 
            sum=l1.val+l2.val+temp;
            if(sum>9){
                sum=sum-10;
                temp=1;
                 ListNode tempLink=new ListNode(sum);
                l3.next=tempLink;
                l3=l3.next;
            }else{
                 ListNode tempLink=new ListNode(sum);
                temp=0;
                l3.next=tempLink;
                l3=l3.next;
            }
            l1=l1.next;l2=l2.next;
        }
        if(l1!=null){
            while(l1!=null){
                sum=l1.val+temp;
                 if(sum>9){
                sum=sum-10;
                temp=1;
                 ListNode tempLink=new ListNode(sum);
                l3.next=tempLink;
                l3=l3.next;
            }else{
                ListNode tempLink=new ListNode(sum);
                temp=0;
                l3.next=tempLink;
                l3=l3.next;
            }
                l1=l1.next;
            }
        }else{
              while(l2!=null){
                sum=l2.val+temp;
                      if(sum>9){
                sum=sum-10;
                temp=1;
                 ListNode tempLink=new ListNode(sum);
                l3.next=tempLink;
                l3=l3.next;
            }else{
                 ListNode tempLink=new ListNode(sum);
                temp=0;
                l3.next=tempLink;
                l3=l3.next;
            }
                  l2=l2.next;
            }
        }
        if(temp==1){
            ListNode tempLink=new ListNode(1);
            l3.next=tempLink;
            l3=l3.next;
                                       
        }
        return head;
    }
}
