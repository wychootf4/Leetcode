// http://stackoverflow.com/questions/4581576/%20implementing-an-iterator-over-a-binary-search-tree
/*
Is there a way to design an iterator over a binary search tree with the following properties?

Elements are visited in ascending order (i.e. an inorder traversal)
next() and hasNext() queries run in O(1) time.
Memory usage is O(1)
*/

The simplest possible iterator stores the last seen key, and then on the next iteration,
searches the tree for the least upper bound for that key. Iteration is O(log n). This has
the advantage of being very simple. If keys are small then the iterators are also small.
of course it has the disadvantage of being a relatively slow way of iterating through the tree.
It also won't work for non-unique sequences.

Some trees use exactly the implementation you already use, because it's important for their
specific use that scanning is very fast. If the number of keys in each node is large, then
the penalty of storing sibling pointers isn't too onerous. Most B-Trees use this method.

many search tree implementations keep a parent pointer on each node to simplify other operations.
If you have that, then you can use a simple pointer to the last seen node as your iterator's state.
at each iteration, you look for the next child in the last seen node's parent. if there are no more
siblings, then you go up one more level.

If none of these techniques suit you, you can use a stack of nodes, stored in the iterator.
This serves a the same function as the function call stack when iterating through the search
tree as normal, but instead of looping through siblings and recursing on children, you push
children onto the stack and return each successive sibling.
