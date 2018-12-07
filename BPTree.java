package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Implementation of a B+ tree to allow efficient access to many different
 * indexes of a large data set. BPTree objects are created for each type of
 * index needed by the program. BPTrees provide an efficient range search as
 * compared to other types of data structures due to the ability to perform
 * log_m N lookups and linear in-order traversals of the data items.
 * 
 * @author sapan (sapan@cs.wisc.edu)
 *
 * @param <K>
 *            key - expect a string that is the type of id for each item
 * @param <V>
 *            value - expect a user-defined type that stores all data for a food
 *            item
 */
public class BPTree<K extends Comparable<K>, V> implements BPTreeADT<K, V> {

	// Root of the tree
	private Node root;

	// Branching factor is the number of children nodes
	// for internal nodes of the tree
	private int branchingFactor;

	private int numOfKeys = 0;

	/**
	 * Public constructor
	 * 
	 * @param branchingFactor
	 */
	public BPTree(int branchingFactor) {
		if (branchingFactor <= 2) {
			throw new IllegalArgumentException("Illegal branching factor: " + branchingFactor);
		}

		// TODO : Complete
		root = new LeafNode();
		this.branchingFactor = branchingFactor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BPTreeADT#insert(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void insert(K key, V value) {
		// TODO : Complete
		root.insert(key, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BPTreeADT#rangeSearch(java.lang.Object, java.lang.String)
	 */
	@Override
	public List<V> rangeSearch(K key, String comparator) {
		if (key == null || comparator == null || comparator.equals(""))
			return new ArrayList<V>();
		
		if (!comparator.contentEquals(">=") && !comparator.contentEquals("==") && !comparator.contentEquals("<="))
			return new ArrayList<V>();
		// TODO : Complete
		
		// REVIEW CASES WHERE NEED TO RETURN EMPTY LIST
		// If key is null or not found, return empty list.
		// * If comparator is null, empty, or not according
		// * to required form, return empty list.
		
//		if (key == null || comparator == null || comparator.equals(""))
//			return new ArrayList<V>();

		return root.rangeSearch(key, comparator);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Queue<List<Node>> queue = new LinkedList<List<Node>>();
		queue.add(Arrays.asList(root));
		StringBuilder sb = new StringBuilder();
		while (!queue.isEmpty()) {
			Queue<List<Node>> nextQueue = new LinkedList<List<Node>>();
			while (!queue.isEmpty()) {
				List<Node> nodes = queue.remove();
				sb.append('{');
				Iterator<Node> it = nodes.iterator();
				while (it.hasNext()) {
					Node node = it.next();
					sb.append(node.toString());
					if (it.hasNext())
						sb.append(", ");
					if (node instanceof BPTree.InternalNode)
						nextQueue.add(((InternalNode) node).children);
				}
				sb.append('}');
				if (!queue.isEmpty())
					sb.append(", ");
				else {
					sb.append('\n');
				}
			}
			queue = nextQueue;
		}
		return sb.toString();
	}

	/**
	 * This abstract class represents any type of node in the tree This class is a
	 * super class of the LeafNode and InternalNode types.
	 * 
	 * @author sapan
	 */
	private abstract class Node {

		// List of keys
		List<K> keys;

		/**
		 * Package constructor
		 */
		Node() {
			// TODO : Complete
			keys = new ArrayList<K>();// MOST EFFICIENT???
		}

		/**
		 * Inserts key and value in the appropriate leaf node and balances the tree if
		 * required by splitting
		 * 
		 * @param key
		 * @param value
		 */
		abstract void insert(K key, V value);

		/**
		 * Gets the first leaf key of the tree
		 * 
		 * @return key
		 */
		abstract K getFirstLeafKey();

		/**
		 * Gets the new sibling created after splitting the node
		 * 
		 * @return Node
		 */
		abstract Node split();

		/*
		 * (non-Javadoc)
		 * 
		 * @see BPTree#rangeSearch(java.lang.Object, java.lang.String)
		 */
		abstract List<V> rangeSearch(K key, String comparator);

		/**
		 * 
		 * @return boolean
		 */
		abstract boolean isOverflow();

		public String toString() {
			return keys.toString();
		}

	} // End of abstract class Node

	/**
	 * This class represents an internal node of the tree. This class is a concrete
	 * sub class of the abstract Node class and provides implementation of the
	 * operations required for internal (non-leaf) nodes.
	 * 
	 * @author sapan
	 */
	private class InternalNode extends Node {

		// List of children nodes
		List<Node> children;

		/**
		 * Package constructor
		 */
		InternalNode() {
			super();
			// TODO : Complete
			children = new ArrayList<Node>();// MOST EFFICIENT???
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see BPTree.Node#getFirstLeafKey()
		 */
		K getFirstLeafKey() {
			// TODO : Complete
			// children.get(0).getClass().isInstance(LeafNode);
			// LeafNode.class.isInstance(children.get(0));
			// if(children.get(0) instanceof BPTree.LeafNode)//NOT NEEDED it will go to
			// respective method for internal or leaf
			return getFirstLeafKeyHelper(this.children.get(0));
		}

		K getFirstLeafKeyHelper(Node n) {
			if (n instanceof BPTree.LeafNode) {
				return n.getFirstLeafKey();
			}
			InternalNode curr = (BPTree<K, V>.InternalNode) n;// INCORRECT??

			return getFirstLeafKeyHelper(curr.children.get(0));
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see BPTree.Node#isOverflow()
		 */
		boolean isOverflow() {
			// TODO : Complete
			if (keys.size() >= branchingFactor) {
				return true;
			}
			return false;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see BPTree.Node#insert(java.lang.Comparable, java.lang.Object)
		 */
		void insert(K key, V value) {
			// TODO : Complete
			// WRONG! split still has the pushed value as a key in a child node

			Node childReturned = insertHelper(key, value, root);
			if (root.isOverflow()) {
				Node child2 = root.split();

				Node child1 = root;
				InternalNode parent = new InternalNode();
				// parent.keys.add(child2.getFirstLeafKey());
				parent.keys.add(child2.keys.get(0));
//				System.out.println(
//						"child1 keys:" + child1.keys.toString() + "    child 2 keys:" + child2.keys.toString());
//				System.out.println("parent keys?:" + parent.keys.toString());
				// child2.keys.remove(child2.getFirstLeafKey());
				child2.keys.remove(child2.keys.get(0));
				parent.children.add(0, child1);
				parent.children.add(1, child2);
				root = parent;

			} else if (childReturned.isOverflow()) {
//				System.out.println("Overflow child:"+childReturned);
//				System.out.println("root:"+root.toString());
				
				InternalNode temp = (BPTree<K, V>.InternalNode) root;
				int childIndex = temp.children.indexOf(childReturned);// get index of the overflowed child
				//System.out.println("children:"+temp.children.toString()+"   childIndex:"+childIndex);
				Node sibling = temp.children.get(childIndex).split();
				K poppedKey = sibling.getFirstLeafKey();
				sibling.keys.remove(poppedKey);
				// internalCurr.children.add(keyIndex,poppedKey);
				temp.keys.add(childIndex, poppedKey);
				temp.children.add(childIndex + 1, sibling);
				root = temp;
			}
			if (root.isOverflow()) {
				Node child2 = root.split();

				Node child1 = root;
				InternalNode parent = new InternalNode();
				parent.keys.add(child2.getFirstLeafKey());
				//System.out.println("parent keys?:" + parent.keys.toString());
				child2.keys.remove(child2.getFirstLeafKey());
				parent.children.add(0, child1);
				parent.children.add(1, child2);
				root = parent;

			}
			
		}

		InternalNode insertHelper(K key, V value, Node curr) {
			//System.out.println("Curr at start of insert:"+curr);
			InternalNode internalCurr = (BPTree<K, V>.InternalNode) curr;// CORRECT??
			InternalNode child=new InternalNode();

			if (internalCurr.children.get(0) instanceof BPTree.LeafNode) {
				// do something make new internal node and assign getfirstleaf key from the
				// split to the internal node keys

				// the key-value pair will get inserted at the appropriate index, followed by a
				// check for overflow and a split (if needed).

				//
				// InternalNode parent = new InternalNode();
				// parent.keys.add(curr.getFirstLeafKey());
				// return
				int keyIndex = 0;

				while (keyIndex < internalCurr.keys.size()) {
					if (key.compareTo(internalCurr.keys.get(keyIndex)) <= 0) {

						// internalCurr=insertHelper(key,value,internalCurr.children.get(keyIndex));//store
						// it??
						internalCurr.children.get(keyIndex).insert(key, value);
						if (internalCurr.children.get(keyIndex).isOverflow()) {
							Node sibling = internalCurr.children.get(keyIndex).split();
							K poppedKey = sibling.getFirstLeafKey();
							// sibling.keys.remove(poppedKey);
							// internalCurr.children.add(keyIndex,poppedKey);
							internalCurr.keys.add(keyIndex, poppedKey);
							internalCurr.children.add(keyIndex + 1, sibling);
 
							// insertPoppedKey(poppedKey);
							// insert the value into the current node
							// check again if overflow??
						}
						// return internalCurr;//??
						break;
					}
					// else if() {//how handle equal keys
					//
					// }
					else {// key larger than current key
						keyIndex++;
						//System.out.println("Key Index when inserting at leaf greater than current key:" + keyIndex);
						if (keyIndex == internalCurr.keys.size()) {
							//System.out.println("Children before insert:" + internalCurr.children.toString());
							internalCurr.children.get(keyIndex).insert(key, value);
							//System.out.println("Children before overflow:" + internalCurr.children.toString());
							if (internalCurr.children.get(keyIndex).isOverflow()) {
								Node sibling = internalCurr.children.get(keyIndex).split();
								K poppedKey = sibling.getFirstLeafKey();
								// sibling.keys.remove(poppedKey);
								// internalCurr.children.add(keyIndex,poppedKey);
								internalCurr.keys.add(keyIndex, poppedKey);
								internalCurr.children.add(keyIndex + 1, sibling);
								//System.out.println("Children after overflow:" + internalCurr.children.toString());
								// insertPoppedKey(poppedKey);
								// insert the value into the current node
								// check again if overflow??
							}
							break;
							// return internalCurr; //??

						}
					}
				}
				//System.out.println("children are leaves, internalCurr is "+internalCurr+"   children:"+internalCurr.children);
				//if(internalCurr==root) {
					return internalCurr;
				//}
				
			}
			// int keyIndex=0;
			// if(keyIndex<branchingFactor) {
			// while(key.compareTo(curr.keys.get(keyIndex))>=0) {
			// keyIndex++;
			//
			// if(keyIndex<)
			// childIndex=keyIndex;
			// root=insertHelper(key,value,curr.children.get(childIndex));//??
			//
			// if(curr.isOverflow()) {//??
			//
			// Node sibling=curr.split();
			// K poppedKey=sibling.getFirstLeafKey();
			// insertChildKeyToInternal();
			// //root.keys.add(keyIndex,root.split().getFirstLeafKey());
			// }
			//
			// }
			// }
			// else {//went through all keys in the node and greater than or equal to last
			// key, will go to rightmost child of the node
			//
			// }
			//
			// root.insert(key, value);
			// return null;
			int keyIndex = 0;

			while (keyIndex < internalCurr.keys.size()) {
				if (key.compareTo(internalCurr.keys.get(keyIndex)) <= 0) {

					//internalCurr = insertHelper(key, value, internalCurr.children.get(keyIndex));// store it??
					child = insertHelper(key, value, internalCurr.children.get(keyIndex));// store it??
					//System.out.println("RETURNED AFTER LEAVES1 internalCurr is "+internalCurr+"   children:"+internalCurr.children);
					//if (internalCurr.children.get(keyIndex).isOverflow()) {
					if (child.isOverflow()) {
						//System.out.println("hi1");
						//Node sibling = internalCurr.children.get(keyIndex).split();
						Node sibling = child.split();
						K poppedKey = sibling.getFirstLeafKey();
						sibling.keys.remove(poppedKey);
						// internalCurr.children.add(keyIndex,poppedKey);
						internalCurr.keys.add(keyIndex, poppedKey);
						internalCurr.children.add(keyIndex + 1, sibling);

						// insertPoppedKey(poppedKey);
						// insert the value into the current node
						// check again if overflow??
					}
					// return internalCurr;//??
					//System.out.println("hi3");
					break;
				}
				// else if() {//how handle equal keys
				//
				// }
				else {// key larger than current key
					keyIndex++;
					if (keyIndex == internalCurr.keys.size()) {

						//internalCurr = insertHelper(key, value, internalCurr.children.get(keyIndex));// store it??
						child = insertHelper(key, value, internalCurr.children.get(keyIndex));// store it??
//						System.out.println("RETURNED AFTER LEAVES2 internalCurr is "+internalCurr+"   children:"+internalCurr.children);
//						System.out.println("Testing Keys:" + internalCurr.keys.toString());
						//if (internalCurr.children.get(keyIndex).isOverflow()) {
						if (child.isOverflow()) {
							//System.out.println("hi2");
							//Node sibling = internalCurr.children.get(keyIndex).split();
							Node sibling = child.split();
							K poppedKey = sibling.getFirstLeafKey();
							sibling.keys.remove(poppedKey);
							// internalCurr.children.add(keyIndex,poppedKey);
							internalCurr.keys.add(keyIndex, poppedKey);
							internalCurr.children.add(keyIndex + 1, sibling);

							// insertPoppedKey(poppedKey);
							// insert the value into the current node
							// check again if overflow??
						}
						//System.out.println("hi4");
						break;
						// return internalCurr; //??

					}
				}
			}
			//System.out.println("FINALLY DONE inserting internalCurr:"+internalCurr);
			return internalCurr;

		}
		// insertChildKeyToInternal(){
		//
		// }

		/**
		 * (non-Javadoc)
		 * 
		 * @see BPTree.Node#split()
		 */
		Node split() {
			// TODO : Complete
			InternalNode sibling = new InternalNode();// sibling to the current leafnode

			// children shouldn't have the parent node key??

			int keyMidIndex = (this.keys.size() / 2);

			int childrenMidIndex = (this.children.size() / 2);

			// this.keys=this.keys.subList(0, keyMidIndex);

			if ((branchingFactor % 2) == 1) {// odd number of children

				sibling.keys.addAll(this.keys.subList(keyMidIndex, keys.size()));// don't store mid val in child
				this.keys.subList(keyMidIndex, keys.size()).clear();
				//System.out.println("this keys:" + this.keys.toString() + "    sibling keys:" + sibling.keys.toString());
				sibling.children.addAll(this.children.subList(childrenMidIndex, children.size()));// CORRECT?
				// this.children=this.children.subList(0, childrenMidIndex+1);
				this.children.subList(childrenMidIndex, children.size()).clear();
				//System.out.println("this children:" + this.children.toString() + "    sibling children:"
						//+ sibling.children.toString());
			} else {// even number of children
				sibling.keys.addAll(this.keys.subList(keyMidIndex, keys.size()));// don't store mid val in child
				this.keys.subList(keyMidIndex, keys.size()).clear();
				sibling.children.addAll(this.children.subList(childrenMidIndex + 1, children.size()));// CORRECT?
				// this.children=this.children.subList(0, childrenMidIndex);
				this.children.subList(childrenMidIndex + 1, children.size()).clear();
			}

			return sibling;

		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see BPTree.Node#rangeSearch(java.lang.Comparable, java.lang.String)
		 */
		List<V> rangeSearch(K key, String comparator) {
			// TODO : Complete
			// REVIEW CASES WHERE NEED TO RETURN EMPTY LIST in ADT

			int keyIndex = 0;
			InternalNode curr = new InternalNode();
			curr = (BPTree<K, V>.InternalNode) root;
			boolean foundLeaf = false;
			while (!foundLeaf) {

				if (key.compareTo(curr.keys.get(keyIndex)) >= 0) {
					if (curr.keys.size() > 1 && keyIndex < curr.keys.size() - 1
							&& key.compareTo(curr.keys.get(keyIndex)) > 0) {
						keyIndex++;
						continue;
					} else {
						if (curr.children.get(0) instanceof BPTree.LeafNode) {
							foundLeaf = true;// redundant??
							break;//
						}
						curr = (BPTree<K, V>.InternalNode) curr.children.get(keyIndex + 1);
						keyIndex = 0;
					}

				} else {
					if (curr.children.get(0) instanceof BPTree.LeafNode) {
						foundLeaf = true;// redundant??
						break;//
					}
					curr = (BPTree<K, V>.InternalNode) curr.children.get(0);
					keyIndex = 0;
				}
			}

			// chidlren are leaves
			int childIndex = 0;
			while (childIndex < curr.children.size() - 1) {
				if (key.compareTo(curr.keys.get(childIndex)) < 0) {
//					System.out.println("Internal rangeSearch curr keys:" + curr.keys.toString());
//					System.out.println("Internal rangeSearch curr:" + curr.toString());
//					System.out.println("Internal rangeSearch Children:" + curr.children.toString());
//					System.out.println("Internal rangeSearch childIndex:" + childIndex);
					return curr.children.get(childIndex).rangeSearch(key, comparator);
				} else {// greater than or equal
					childIndex++;
					if (childIndex < curr.keys.size() - 1 && key.compareTo(curr.keys.get(childIndex)) > 0) {
						childIndex++;
						break;
					}

				}
			}
//			System.out.println("Internal rangeSearch curr keys:" + curr.keys.toString());
//			System.out.println("Internal rangeSearch curr:" + curr.toString());
//			System.out.println("Internal rangeSearch Children:" + curr.children.toString());
//			System.out.println("Internal rangeSearch childIndex:" + childIndex);
			return curr.children.get(childIndex).rangeSearch(key, comparator);

		}

	} // End of class InternalNode

	/**
	 * This class represents a leaf node of the tree. This class is a concrete sub
	 * class of the abstract Node class and provides implementation of the
	 * operations that required for leaf nodes.
	 * 
	 * @author sapan
	 */
	private class LeafNode extends Node {

		// List of values
		List<V> values;

		// Reference to the next leaf node
		LeafNode next;

		// Reference to the previous leaf node
		LeafNode previous;

		/**
		 * Package constructor
		 */
		LeafNode() {
			super();
			// TODO : Complete
			values = new ArrayList<V>();// MOST EFFICIENT?
			// next = new LeafNode();
			// previous = new LeafNode();
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see BPTree.Node#getFirstLeafKey()
		 */
		K getFirstLeafKey() {
			// TODO : Complete
			// LeafNode curr = this;// assign current node to a new var, NEEDED??
			//
			// return getFirstLeafKeyHelper(curr);
			return keys.get(0);
		}

		// K getFirstLeafKeyHelper(LeafNode curr) {
		// if (previous == null) {
		// return curr.keys.get(0);// find
		// }
		//
		// curr = previous;
		// return getFirstLeafKeyHelper(curr);
		//
		// }

		/**
		 * (non-Javadoc)
		 * 
		 * @see BPTree.Node#isOverflow()
		 */
		boolean isOverflow() {
			// TODO : Complete
			if (keys.size() >= branchingFactor) {
				return true;
			}
			return false;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see BPTree.Node#insert(Comparable, Object)
		 */
		void insert(K key, V value) {// adds key and value to correct spot, does not handle overflow
			// TODO : Complete
			numOfKeys++;

			int keyIndex = 0;
			//System.out.println("keys:" + keys.size());
			if (keys.size() == 0) {
				keys.add(key);
				values.add(value);
			} else {
				while (keyIndex < this.keys.size()) {
					if (key.compareTo(this.keys.get(keyIndex)) <= 0) {// new key less than or equal to current key
						this.keys.add(keyIndex, key);
						this.values.add(keyIndex, value);
						break;
					}

					else {// key larger than current key
						keyIndex++;
						if (keyIndex == this.keys.size()) {
							this.keys.add(keyIndex, key);
							this.values.add(keyIndex, value);
							break;
						}
					}
				}
			}

			if (numOfKeys == branchingFactor) {

				LeafNode child2 = (BPTree<K, V>.LeafNode) root.split();

				LeafNode child1 = (BPTree<K, V>.LeafNode) root;
				InternalNode parent = new InternalNode();
				parent.keys.add(child2.getFirstLeafKey());

				parent.children.add(0, child1);
				parent.children.add(1, child2);
				root = parent;

			}
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see BPTree.Node#split()
		 */
		Node split() {
			// TODO : Complete

			// group of leaf nodes, set middle to be an internal node
			// make all of the leaf nodes become (sorted?) children of the internal node,
			// including itself as a leaf
			//

			// InternalNode parent = new InternalNode();//NEED A NEW INTERNAL NODE?? what it
			// split and new internal node becomes part of an exisiting internal node??
			// LeafNode child2=new LeafNode();//sibling to the current leafnode
			// int midIndex;
			// List<K> leftList=new ArrayList<K>();//used to hold keys of the split node
			// less than the middle key
			// List<K> rightList=new ArrayList<K>();//used to hold keys of the split node
			// greater than the middle key
			//
			// if(branchingFactor%2==0) {//odd number keys allowed at each node
			// midIndex=(this.keys.size()/2);//choose value to the right that is the closest
			// to the middle since4
			// //during a split there are an even number of keys at the node
			//
			// parent.keys.add(this.keys.get(midIndex));//add at beginning always??
			//
			// leftList=this.keys.subList(0, midIndex);
			// rightList=this.keys.subList(midIndex,keys.size());
			// this.keys=leftList;
			// child2.keys=rightList;
			//
			// }
			// else {//odd branchingFactor,
			//
			// midIndex=(this.keys.size()/2);//choose middle value because during a split
			// there is an odd number of
			// //keys at the node
			//
			// parent.keys.add(this.keys.get(midIndex));//add at beginning always??
			//
			// leftList=this.keys.subList(0, midIndex);//left smaller than right?? okay??
			// rightList=this.keys.subList(midIndex,keys.size());
			// this.keys=leftList;
			// child2.keys=rightList;
			//
			//
			// }//DON'T NEED the if else???
			//
			// parent.children.add(0, this);
			// parent.children.add(1, child2);

			LeafNode sibling = new LeafNode();// sibling to the current leafnode

			// List<K> leftList=new ArrayList<K>();//used to hold keys of the split node
			// less than the middle key
			// List<K> rightList=new ArrayList<K>();//used to hold keys of the split node
			// greater than the middle key

			int midIndex = (this.keys.size() / 2);// values and keys should be same amount??

			// leftList=this.keys.subList(0, midIndex);
			// rightList=this.keys.subList(midIndex,keys.size());
			// this.keys=leftList;

			sibling.keys.addAll(this.keys.subList(midIndex, keys.size()));

			sibling.values.addAll(this.values.subList(midIndex, values.size()));

			this.keys.subList(midIndex, keys.size()).clear();
			this.values.subList(midIndex, values.size()).clear();
			// this.keys=this.keys.subList(0, midIndex);
			// this.values=this.values.subList(0, midIndex);
			
			if(this.next!=null) {
				this.next.previous=sibling;
			}
			
			sibling.next=this.next;
			this.next = sibling;
			sibling.previous = this;
			return sibling;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see BPTree.Node#rangeSearch(Comparable, String)
		 */
		List<V> rangeSearch(K key, String comparator) {
			// TODO : Complete
			// REVIEW CASES WHERE NEED TO RETURN EMPTY LIST in ADT

			// I am assuming I am at the node with the correct key at the beginning of the
			// keys list??
			List<V> valsInRange = new ArrayList<V>();
			// valsInRange=rangeSearchHelper(key,comparator,this, valsInRange);

			int keyIndex = 0;
			boolean keyExist = false;

			while (keyIndex < this.keys.size()) {// find key index in list of current keys
				if (key.compareTo(this.keys.get(keyIndex)) == 0) {
					keyExist = true;
					break;
				}
				keyIndex++;
			}
			if(!keyExist&&comparator.equals("<=")) {
				keyIndex=this.keys.size()-1;
			}
			else if (!keyExist) {
				keyIndex = 0;
				// valsInRange.clear();
				// return valsInRange;
			}

			int storedIndex = keyIndex;// to restart from beginning when changing traversal direction
			LeafNode storedNode = this;// to restart from beginning when changing traversal direction
			LeafNode curr = this;// to traverse in one direction

			int tempIndex = storedIndex;// to traverse in the other direction
			LeafNode tempNode = storedNode;// to traverse in the other direction
			List<V> listForOneDirection=new ArrayList<V>();
			
			if (comparator.equals(">=")) {
//				System.out.println("Next Test Leaf searchRange:"+curr.next);
//				System.out.println("Next Test Leaf searchRange:"+curr.next.next);
//				System.out.println("Next Test Leaf searchRange:"+curr.next.next.next);
//				System.out.println("Next Test Leaf searchRange:"+curr.next.next.next.next);
//				System.out.println("Next Test Leaf searchRange:"+curr.next.next.next.next.next);
//				System.out.println("Next Test Leaf searchRange:"+curr.next.next.next.next.next.next);
//				System.out.println("Next Test Leaf searchRange:"+curr.next.next.next.next.next.next.next);
//				System.out.println("Next Test Leaf searchRange:"+curr.next.next.next.next.next.next.next.next);
				do {
					while (keyIndex < curr.keys.size()) {
						if(curr.keys.get(keyIndex).compareTo(key)>=0) {
							valsInRange.add(curr.values.get(keyIndex));
							//System.out.println("Leaf search range <= valsInRange:"+valsInRange.toString());
							//System.out.println("Curr in the loop:"+curr.toString());
						}
						
						keyIndex++;
					}
					keyIndex = 0;
					if(curr.next==null) {
						break;
					}
					//if(curr.next!=null) {
						
						curr = curr.next;
						///System.out.println("Leaf search range after curr=curr.next:"+curr.toString());
					//}
					

				} while (curr != null);
				if(valsInRange.size()==0) {
					return valsInRange;
				}
				listForOneDirection.addAll(valsInRange);
				//System.out.println("listForOneDirection:"+listForOneDirection.toString());
				do {// add values where key is equal to left of current key

					while (tempIndex >= 0
							&& tempNode.keys.get(tempIndex).compareTo(storedNode.keys.get(storedIndex)) == 0) {

						valsInRange.add(0, tempNode.values.get(tempIndex));
						//System.out.println("Leaf rangeSearch >= valsInRange 2nd direction:"+valsInRange.toString());
						tempIndex--;
					}
					if(tempNode.previous==null) {
						break;
					}
					//if(tempNode.previous!=null) {
						tempNode = tempNode.previous;
					//}
					
					tempIndex = tempNode.keys.size() - 1;
					if (tempNode.keys.get(tempIndex).compareTo(storedNode.keys.get(storedIndex)) != 0) {// end early if
																										// key at end of
																										// new leaf is
																										// not equal to
																										// the key of
																										// interest
						break;
					}

				} while (tempNode != null);
				//System.out.println("valsInRange size:"+valsInRange.toString()+"    listForOneDirection size:"+listForOneDirection.toString());
				if(valsInRange.size()!=listForOneDirection.size()) {
					valsInRange.remove(storedNode.values.get(storedIndex));
				}
				
			} else if (comparator.equals("<=")) {
				do {
//					System.out.println("KeyIndex <=:"+keyIndex);
//					System.out.println("Previous Test Leaf searchRange:"+curr.previous);
//					System.out.println("Previous Test Leaf searchRange:"+curr.previous.previous);
//					System.out.println("Previous Test Leaf searchRange:"+curr.previous.previous.previous);
//					System.out.println("Previous Test Leaf searchRange:"+curr.previous.previous.previous.previous);
//					System.out.println("Previous Test Leaf searchRange:"+curr.previous.previous.previous.previous.previous);
//					System.out.println("Previous Test Leaf searchRange:"+curr.previous.previous.previous.previous.previous.previous);
//					System.out.println("Previous Test Leaf searchRange:"+curr.previous.previous.previous.previous.previous.previous.previous);
//					System.out.println("Previous Test Leaf searchRange:"+curr.previous.previous.previous.previous.previous.previous.previous.previous);
					while (keyIndex >= 0) {
						if(curr.keys.get(keyIndex).compareTo(key)<=0) {
							valsInRange.add(0, curr.values.get(keyIndex));
							//System.out.println("Leaf search range >= valsInRange:"+valsInRange.toString());
							//System.out.println("Curr in the loop:"+curr.toString());
						}
						
						keyIndex--;
					}
					if(curr.previous==null) {
						break;
					}
					//if(curr.previous!=null) {
						curr = curr.previous;
					//}
					
					keyIndex = curr.keys.size() - 1;

				} while (curr != null);
				if(valsInRange.size()==0) {
					return valsInRange;
				}
				listForOneDirection.addAll(valsInRange);
				do {
					while (tempIndex < tempNode.keys.size()
							&& tempNode.keys.get(tempIndex).compareTo(storedNode.keys.get(storedIndex)) == 0) {

						valsInRange.add(tempNode.values.get(tempIndex));
						tempIndex++;
					}
					if(tempNode.next==null) {
						break;
					}
					//if(tempNode.next!=null) {
						tempNode =tempNode.next;
					//}
					
					tempIndex = 0;
					if (tempNode.keys.get(tempIndex).compareTo(storedNode.keys.get(storedIndex)) != 0) {// end early if
																										// key at end of
																										// new leaf is
																										// not equal to
																										// the key of
																										// interest
						break;
					}

				} while (tempNode != null);
				
				if(valsInRange.size()!=listForOneDirection.size()) {
					valsInRange.remove(storedNode.values.get(storedIndex));
				}
				
			} else {// "=="
				if (!keyExist) {
					valsInRange.clear();
					return valsInRange;
				}
				do {
					while (keyIndex < curr.keys.size()
							&& curr.keys.get(keyIndex).compareTo(storedNode.keys.get(storedIndex)) == 0) {

						valsInRange.add(curr.values.get(keyIndex));
						keyIndex++;
					}
					if(curr.next==null) {
						break;
					}
					//if(curr.next!=null) {
						curr = curr.next;
					//}
					
					keyIndex = 0;
					if (curr.keys.get(keyIndex).compareTo(storedNode.keys.get(storedIndex)) != 0) {// end early if
																										// key at end of
																										// new leaf is
																										// not equal to
																										// the key of
																										// interest
						break;
					}

				} while (curr != null);
				listForOneDirection.addAll(valsInRange);
				// valsInRange.remove(storedNode.values.get(storedIndex));
				do {// add values where key is equal to left of current key

					while (tempIndex >=0
							&& tempNode.keys.get(tempIndex).compareTo(storedNode.keys.get(storedIndex)) == 0) {

						valsInRange.add(0, tempNode.values.get(tempIndex));
						tempIndex--;
					}
					if(tempNode.previous==null) {
						break;
					}
					//if(tempNode.previous!=null) {
						tempNode = tempNode.previous;
					//}
					
					tempIndex = tempNode.keys.size() - 1;
					if (tempNode.keys.get(tempIndex).compareTo(storedNode.keys.get(storedIndex)) != 0) {// end early if
																										// key at end of
																										// new leaf is
																										// not equal to
																										// the key of
																										// interest
						break;
					}

				} while (tempNode != null);
				
				if(valsInRange.size()!=listForOneDirection.size()) {
					valsInRange.remove(storedNode.values.get(storedIndex));
				}
			}

			return valsInRange;

		}
		// List<V> rangeSearchHelper(K key, String comparator, LeafNode leaf, List<V>
		// valsInRange){
		//
		//
		// if(comparator.equals(">=")) {
		//
		// for(int i=0;i<values.size();i++) {
		// valsInRange.add(values.get(i));
		// }
		// if(next!=null) {
		// valsInRange=rangeSearchHelper(key,comparator,next, valsInRange);
		// }
		//
		//
		// }else if(comparator.equals("<=")) {
		//
		//
		// }else {//comparator is "=="
		//
		// }else if(comparator.equals("<=")) {
		// return valsInRange;
		// }

	} // End of class LeafNode

	/**
	 * Contains a basic test scenario for a BPTree instance. It shows a simple
	 * example of the use of this class and its related types.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// create empty BPTree with branching factor of 3
		BPTree<Double, Double> bpTree = new BPTree<>(3);

		// create a pseudo random number generator
		Random rnd1 = new Random();

		// some value to add to the BPTree
		Double[] dd = { 0.0d, 0.5d, 0.2d, 0.8d };

		// build an ArrayList of those value and add to BPTree also
		// allows for comparing the contents of the ArrayList
		// against the contents and functionality of the BPTree
		// does not ensure BPTree is implemented correctly
		// just that it functions as a data structure with
		// insert, rangeSearch, and toString() working.
		List<Double> list = new ArrayList<>();
		// UNCOMMENT WHEN DONE!!!!!
		 for (int i = 0; i < 400; i++) {// UNCOMMENT WHEN DONE!!!!!
		 Double j = dd[rnd1.nextInt(4)];
		System.out.println("THIS NEEDS TO BE DELTED: inserting:"+j);
		 list.add(j);
		 bpTree.insert(j, j);
		 System.out.println("\n\nTree structure:\n" + bpTree.toString());
		 }
//		bpTree.insert(30.0, 30.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(25.0, 25.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(11.0, 11.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(10.0, 10.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(9.0, 9.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(7.0, 7.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(40.0, 40.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(10.0, 10.0);// MY TESTS
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(11.0, 11.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(12.0, 12.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(13.0, 13.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(14.0, 14.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(15.0, 15.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(1.0, 1.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(11.0, 11.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(10.0, 10.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(12.0, 12.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(19.0, 19.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(20.0, 20.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(21.0, 21.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(10.0, 10.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(1.0, 1.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
//		bpTree.insert(40.0, 40.0);
//		System.out.println("\n\nTree structure:\n" + bpTree.toString());
		
//		List<Double> filteredValues = bpTree.rangeSearch(10.0, "=="); 
//		System.out.println("Filtered values: " + filteredValues.toString());
		// UNCOMMENT WHEN DONE!!!!!
		 List<Double> filteredValues = bpTree.rangeSearch(0.2d, ">="); //UNCOMMENT
		// WHEN DONE!!!!!
		 System.out.println("Filtered values: " + filteredValues.toString());
	}

} // End of class BPTree
