/*
Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.
*/
// Tag: Data Structure, Trie

/*
分析:
一个Trie Node应该包括一个character，它的children，还有是否为叶子节点。
插入操作时遍历待插入单词的每个字符，Trie树的根节点是空的，如果当前节点的孩子中不包含此时正在遍历的字符，将其加入孩子集中。通过字符找到对应
的TrieNode。
搜索的时候遍历单词，如果遍历到最后是叶子节点则匹配。
*/
class TrieNode {
    // Initialize your data structure here.
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isLeaf;
    public TrieNode() {
    }
    public TrieNode(char c){
        this.c = c;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        HashMap<Character, TrieNode> children = root.children;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            TrieNode t = null;
            if (children.containsKey(c)){
                t = children.get(c);
            }else{
                t = new TrieNode(c);
                children.put(c, t);
            }
            // 继续向下扫
            children = t.children;
            // 如果已经是叶子节点标记为true
            if (i == word.length() - 1){
                t.isLeaf = true;
            }
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode t = searchNode(word);
        if (t != null && t.isLeaf){
            return true;
        }
        return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if (searchNode(prefix) == null){
            return false;
        }
        return true;
    }

    private TrieNode searchNode(String str){
        TrieNode t = null;
        HashMap<Character, TrieNode> children = root.children;
        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if (children.containsKey(c)){
                t = children.get(c);
                children = t.children;
            }else{
                return null;
            }
        }

        return t;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
