package com.troll.algorithm.链表.n203;import com.troll.algorithm.链表.ListNode;/** * author : TangPeng * date : 5/20/22 08:36 * description : */public class 移除链表元素 {    public ListNode removeElements(ListNode head, int val) {        // 如果是头结点，删除头结点        while (head != null && head.val == val) {            head = head.next;        }        if (head == null) return head;        ListNode pre = head;        ListNode cur = head.next;        while (cur != null) {            if (cur.val == val) {                pre.next = cur.next;            } else {                pre = cur;            }            cur = cur.next;        }        return head;    }    /**     * 使用虚拟节点的方式     */    public ListNode removeEle(ListNode head, int val) {        if (head == null) {            return head;        }        ListNode dummy = new ListNode(-1, head);        ListNode pre = dummy;        ListNode cur = head;        while (cur != null) {            if (cur.val == val) {                pre.next = cur.next;            } else {                pre = cur;            }            cur = cur.next;        }        return dummy.next;    }}