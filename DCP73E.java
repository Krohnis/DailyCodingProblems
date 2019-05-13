package DCP;

import java.util.Stack;

class Node
{
	int value;
	Node next;
	Node(int value) { this.value = value; }
}

public class DCP73E 
{
	/*
	 * Given the head of a singly linked list, reverse it in-place.
	 */
	public static void main(String[] args)
	{
		DCP73E cp = new DCP73E();
		Node root = new Node(1);
		root.next = new Node(2);
		root.next.next = new Node(3);
		cp.printList(root);
		cp.reverseA(root);
		cp.printList(root);
		root = cp.reverseB(root);
		cp.printList(root);
	}
	
	public Node reverseA(Node node)
	{
		if (node == null) { return null; }
		Stack<Integer> stack = new Stack();
		Node curr = node;
		
		while (curr != null)
		{
			stack.push(curr.value);
			curr = curr.next;
		}
		
		curr = node;
		
		while (!stack.isEmpty())
		{
			curr.value = stack.pop();
			curr = curr.next;
		}
		
		return node;
	}
	
	public Node reverseB(Node node)
	{
		if (node == null) { return null; }
		Node curr = node, prev = null, head;
		
		while (curr != null)
		{
			Node temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
		}
		head = prev;
		
		return head;
	}
	
	public void printList(Node node)
	{
		System.out.print("{" + node.value);
		node = node.next;
		while (node != null)
		{
			System.out.print(", " + node.value);
			node = node.next;
		}
		System.out.print("}\n");
	}
}
