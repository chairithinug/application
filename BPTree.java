
/**
 * Filename:   BPTree.java
 * Project:    Team Project
 * Authors:    TODO: Zening Fang, Effy Chu, Brock Thern, Anapat Chairithinugull (LEC 001)
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * 
 * Due Date:   TODO: 12/12 at 10pm
 * Version:    1.0
 * 
 * Credits:    TODO: name individuals and sources outside of course staff
 * 
 * Bugs:       TODO: add any known bugs, or unsolved problems here
 */
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
 * @param <K> key - expect a string that is the type of id for each item
 * @param <V> value - expect a user-defined type that stores all data for a food
 *        item
 */
public class BPTree<K extends Comparable<K>, V> implements BPTreeADT<K, V> {

	// Root of the tree
	private Node root;

	// Branching factor is the number of children nodes
	// for internal nodes of the tree
	private int branchingFactor;

	private int numOfKeys = 0;// used so know when to split leaf root at start

	/**
	 * Public constructor
	 * 
	 * @param branchingFactor
	 */
	public BPTree(int branchingFactor) {
		if (branchingFactor <= 2) {
			throw new IllegalArgumentException("Illegal branching factor: " + branchingFactor);
		}

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

			keys = new ArrayList<K>();
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
			children = new ArrayList<Node>();
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see BPTree.Node#getFirstLeafKey()
		 */
		K getFirstLeafKey() {

			return getFirstLeafKeyHelper(this.children.get(0));
		}

		/**
		 * helps find left key in the leftmost internal node's leaf in the sub tree
		 * 
		 * @param n node for traversal
		 * @return left most key in leaf
		 */
		K getFirstLeafKeyHelper(Node n) {
			if (n instanceof BPTree.LeafNode) {
				return n.getFirstLeafKey();
			}
			InternalNode curr = (BPTree<K, V>.InternalNode) n;// allows access to children

			return getFirstLeafKeyHelper(curr.children.get(0));
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see BPTree.Node#isOverflow()
		 */
		boolean isOverflow() {

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

			Node childReturned = insertHelper(key, value, root);// update tree and return child from the first traversal
																// starting at the root
			if (root.isOverflow()) {// balance root if needed
				Node child2 = root.split();
				Node child1 = root;
				InternalNode parent = new InternalNode();
				parent.keys.add(child2.keys.get(0));
				child2.keys.remove(child2.keys.get(0));
				parent.children.add(0, child1);
				parent.children.add(1, child2);
				root = parent;

			} else if (childReturned.isOverflow()) {// fix child if overflow

				InternalNode temp = (BPTree<K, V>.InternalNode) root;// allows access to children
				int childIndex = temp.children.indexOf(childReturned);
				Node sibling = temp.children.get(childIndex).split();
				K poppedKey = sibling.getFirstLeafKey();
				sibling.keys.remove(poppedKey);
				temp.keys.add(childIndex, poppedKey);
				temp.children.add(childIndex + 1, sibling);
				root = temp;
			}
			if (root.isOverflow()) {// make sure fixing the child didn't cause overflow in the root
				Node child2 = root.split();

				Node child1 = root;
				InternalNode parent = new InternalNode();
				parent.keys.add(child2.getFirstLeafKey());
				child2.keys.remove(child2.getFirstLeafKey());
				parent.children.add(0, child1);
				parent.children.add(1, child2);
				root = parent;

			}

		}

		/**
		 * inserts a key value pair in correct location
		 * 
		 * @param key   used to traverse through tree
		 * @param value used with range search
		 * @param curr  current node
		 * @return a child in the correct traversal direction
		 */
		InternalNode insertHelper(K key, V value, Node curr) {
			InternalNode internalCurr = (BPTree<K, V>.InternalNode) curr;// allows access to children
			InternalNode child = new InternalNode();// returned child from insertHelper

			if (internalCurr.children.get(0) instanceof BPTree.LeafNode) {// stop right before leaf nodes
				int keyIndex = 0;

				while (keyIndex < internalCurr.keys.size()) {
					if (key.compareTo(internalCurr.keys.get(keyIndex)) <= 0) {// go left

						internalCurr.children.get(keyIndex).insert(key, value);// leaf node insert
						if (internalCurr.children.get(keyIndex).isOverflow()) {// handle child's overflow
							Node sibling = internalCurr.children.get(keyIndex).split();
							K poppedKey = sibling.getFirstLeafKey();
							internalCurr.keys.add(keyIndex, poppedKey);
							internalCurr.children.add(keyIndex + 1, sibling);
						}
						break;
					} else {// key larger than current key, go right
						keyIndex++;
						if (keyIndex == internalCurr.keys.size()) {
							internalCurr.children.get(keyIndex).insert(key, value);
							if (internalCurr.children.get(keyIndex).isOverflow()) {// handle child's overflow
								Node sibling = internalCurr.children.get(keyIndex).split();
								K poppedKey = sibling.getFirstLeafKey();
								internalCurr.keys.add(keyIndex, poppedKey);
								internalCurr.children.add(keyIndex + 1, sibling);
							}
							break;

						}
					}
				}
				return internalCurr;

			}

			int keyIndex = 0;

			while (keyIndex < internalCurr.keys.size()) {
				if (key.compareTo(internalCurr.keys.get(keyIndex)) <= 0) {// go left
					child = insertHelper(key, value, internalCurr.children.get(keyIndex));
					if (child.isOverflow()) {// handle child's overflow
						Node sibling = child.split();
						K poppedKey = sibling.getFirstLeafKey();
						sibling.keys.remove(poppedKey);
						internalCurr.keys.add(keyIndex, poppedKey);
						internalCurr.children.add(keyIndex + 1, sibling);
					}
					break;
				} else {// key larger than current key
					keyIndex++;
					if (keyIndex == internalCurr.keys.size()) {// go right
						child = insertHelper(key, value, internalCurr.children.get(keyIndex));
						if (child.isOverflow()) {// handle overflow
							Node sibling = child.split();
							K poppedKey = sibling.getFirstLeafKey();
							sibling.keys.remove(poppedKey);
							internalCurr.keys.add(keyIndex, poppedKey);
							internalCurr.children.add(keyIndex + 1, sibling);
						}
						break;

					}
				}
			}
			return internalCurr;

		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see BPTree.Node#split()
		 */
		Node split() {

			InternalNode sibling = new InternalNode();// sibling to the current leafnode
			int keyMidIndex = (this.keys.size() / 2);
			int childrenMidIndex = (this.children.size() / 2);
			sibling.keys.addAll(this.keys.subList(keyMidIndex, keys.size()));
			this.keys.subList(keyMidIndex, keys.size()).clear();

			if ((branchingFactor % 2) == 1) {// odd number of children

				sibling.children.addAll(this.children.subList(childrenMidIndex, children.size()));
				this.children.subList(childrenMidIndex, children.size()).clear();
			} else {// even number of children

				sibling.children.addAll(this.children.subList(childrenMidIndex + 1, children.size()));
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

			int keyIndex = 0;
			InternalNode curr = new InternalNode();
			curr = (BPTree<K, V>.InternalNode) root;// allows access to children
			boolean foundLeaf = false;
			while (!foundLeaf) {
				if (key.compareTo(curr.keys.get(keyIndex)) >= 0) {
					if (curr.keys.size() > 1 && keyIndex < curr.keys.size() - 1
							&& key.compareTo(curr.keys.get(keyIndex)) > 0
							&& key.compareTo(curr.keys.get(keyIndex + 1)) >= 0) {
						keyIndex++;
						continue;
					} else {
						if (curr.children.get(0) instanceof BPTree.LeafNode) {
							foundLeaf = true;
							break;//
						}
						curr = (BPTree<K, V>.InternalNode) curr.children.get(keyIndex + 1);// go right
						keyIndex = 0;
					}

				} else {
					if (curr.children.get(0) instanceof BPTree.LeafNode) {
						foundLeaf = true;
						break;//
					}
					curr = (BPTree<K, V>.InternalNode) curr.children.get(0);// go left
					keyIndex = 0;
				}
			}

			int childIndex = 0;
			while (childIndex < curr.children.size() - 1) {
				if (key.compareTo(curr.keys.get(childIndex)) < 0) {
					return curr.children.get(childIndex).rangeSearch(key, comparator);
				} else {// greater than or equal
					childIndex++;
					if (childIndex < curr.keys.size() - 1 && key.compareTo(curr.keys.get(childIndex)) > 0) {
						childIndex++;
						break;
					}

				}
			}
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

			values = new ArrayList<V>();
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see BPTree.Node#getFirstLeafKey()
		 */
		K getFirstLeafKey() {

			return keys.get(0);
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see BPTree.Node#isOverflow()
		 */
		boolean isOverflow() {

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

			numOfKeys++;

			int keyIndex = 0;
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

			if (numOfKeys == branchingFactor) {// only leaf root exists

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

			LeafNode sibling = new LeafNode();// sibling to the current leafnode
			int midIndex = (this.keys.size() / 2);
			sibling.keys.addAll(this.keys.subList(midIndex, keys.size()));
			sibling.values.addAll(this.values.subList(midIndex, values.size()));
			this.keys.subList(midIndex, keys.size()).clear();
			this.values.subList(midIndex, values.size()).clear();

			if (this.next != null) {
				this.next.previous = sibling;
			}

			sibling.next = this.next;
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
			List<V> valsInRange = new ArrayList<V>();
			int keyIndex = 0;
			boolean keyExist = false;

			while (keyIndex < this.keys.size()) {// find key index in list of current keys
				if (key.compareTo(this.keys.get(keyIndex)) == 0) {
					keyExist = true;
					break;
				}
				keyIndex++;
			}
			if (!keyExist && comparator.equals("<=")) {
				keyIndex = this.keys.size() - 1;
			} else if (!keyExist) {
				keyIndex = 0;
			}

			int storedIndex = keyIndex;// to restart from beginning when changing traversal direction
			LeafNode storedNode = this;// to restart from beginning when changing traversal direction
			LeafNode curr = this;// to traverse in one direction
			int tempIndex = storedIndex;// to traverse in the other direction
			LeafNode tempNode = storedNode;// to traverse in the other direction
			List<V> listForOneDirection = new ArrayList<V>();

			if (comparator.equals(">=")) {// add all values at or to right of key that are >=

				do {
					while (keyIndex < curr.keys.size()) {
						if (curr.keys.get(keyIndex).compareTo(key) >= 0) {
							valsInRange.add(curr.values.get(keyIndex));
						}

						keyIndex++;
					}
					keyIndex = 0;
					if (curr.next == null) {
						break;
					}
					curr = curr.next;
				} while (curr != null);
				if (valsInRange.size() == 0) {
					return valsInRange;
				}
				listForOneDirection.addAll(valsInRange);
				do {// add values where key is equal to left of current key

					while (tempIndex >= 0
							&& tempNode.keys.get(tempIndex).compareTo(storedNode.keys.get(storedIndex)) == 0) {

						valsInRange.add(0, tempNode.values.get(tempIndex));
						tempIndex--;
					}
					if (tempNode.previous == null) {
						break;
					}
					tempNode = tempNode.previous;
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
				if (valsInRange.size() != listForOneDirection.size()) {// take out the starting key since it was added
																		// once for each direction
					valsInRange.remove(storedNode.values.get(storedIndex));
				}

			} else if (comparator.equals("<=")) {
				do {// add all values at or to the left of current key if they are <=
					while (keyIndex >= 0) {
						if (curr.keys.get(keyIndex).compareTo(key) <= 0) {
							valsInRange.add(0, curr.values.get(keyIndex));
						}

						keyIndex--;
					}
					if (curr.previous == null) {
						break;
					}
					curr = curr.previous;
					keyIndex = curr.keys.size() - 1;

				} while (curr != null);
				if (valsInRange.size() == 0) {
					return valsInRange;
				}
				listForOneDirection.addAll(valsInRange);
				do {// add values where key is equal to right of current key
					while (tempIndex < tempNode.keys.size()
							&& tempNode.keys.get(tempIndex).compareTo(storedNode.keys.get(storedIndex)) == 0) {

						valsInRange.add(tempNode.values.get(tempIndex));
						tempIndex++;
					}
					if (tempNode.next == null) {
						break;
					}
					tempNode = tempNode.next;
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

				if (valsInRange.size() != listForOneDirection.size()) {// take out the starting key since it was added
																		// once for each direction
					valsInRange.remove(storedNode.values.get(storedIndex));
				}

			} else {// "=="
				if (!keyExist) {
					valsInRange.clear();
					return valsInRange;
				}
				do {// go right and add values for keys equal to specified key
					while (keyIndex < curr.keys.size()
							&& curr.keys.get(keyIndex).compareTo(storedNode.keys.get(storedIndex)) == 0) {

						valsInRange.add(curr.values.get(keyIndex));
						keyIndex++;
					}
					if (curr.next == null) {
						break;
					}
					curr = curr.next;

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
				do {// go left and add values for keys equal to specified key

					while (tempIndex >= 0
							&& tempNode.keys.get(tempIndex).compareTo(storedNode.keys.get(storedIndex)) == 0) {

						valsInRange.add(0, tempNode.values.get(tempIndex));
						tempIndex--;
					}
					if (tempNode.previous == null) {
						break;
					}
					tempNode = tempNode.previous;

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

				if (valsInRange.size() != listForOneDirection.size()) {// take out the starting key since it was added
																		// once for each direction
					valsInRange.remove(storedNode.values.get(storedIndex));
				}
			}

			return valsInRange;

		}

	} // End of class LeafNode

	/**
	 * Contains a basic test scenario for a BPTree instance. It shows a simple
	 * example of the use of this class and its related types.
	 * 
	 * @param args
	 */
	// public static void main(String[] args) {
	// // create empty BPTree with branching factor of 3
	// BPTree<Double, Double> bpTree = new BPTree<>(3);
	//
	// // create a pseudo random number generator
	// Random rnd1 = new Random();
	//
	// // some value to add to the BPTree
	// Double[] dd = { 0.0d, 0.5d, 0.2d, 0.8d };
	//
	// // build an ArrayList of those value and add to BPTree also
	// // allows for comparing the contents of the ArrayList
	// // against the contents and functionality of the BPTree
	// // does not ensure BPTree is implemented correctly
	// // just that it functions as a data structure with
	// // insert, rangeSearch, and toString() working.
	// List<Double> list = new ArrayList<>();
	// for (int i = 0; i < 400; i++) {
	// Double j = dd[rnd1.nextInt(4)];
	// System.out.println("THIS NEEDS TO BE DELTED: inserting:"+j);
	// list.add(j);
	// bpTree.insert(j, j);
	// System.out.println("\n\nTree structure:\n" + bpTree.toString());
	// }
	//
	// List<Double> filteredValues = bpTree.rangeSearch(0.2d, ">=");
	// System.out.println("Filtered values: " + filteredValues.toString());
	// }

} // End of class BPTree
