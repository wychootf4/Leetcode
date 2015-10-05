/*
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A .
means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.

You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree)
first.
*/
// Tag: Backtracking, Data Structure, Trie

/*
分析：
可以用Trie树来实现，搜索的时候只需要注意如果为.要考虑所有可能的path。
*/
// 加入TrieNode class
class TrieNode{
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isLeaf;
    public TrieNode(){}
    public TrieNode(char c){
        this.c = c;
    }
}

public class WordDictionary {
    private TrieNode root;

    public WordDictionary(){
        root = new TrieNode();
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
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

            children = t.children;
            // 别忘如果到单词的末尾标记叶子节点
            if (i == word.length() - 1){
                t.isLeaf = true;
            }
        }
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    // 如果已经到单词末尾并且为叶子节点就返回true，另如果某个字符为.的时候，如果已经到了单词末尾且为叶子节点也返回true，
    // 否则dfs遍历当前node的父节点的所有孩子。
    public boolean search(String word) {
        if (word == null || word.length() == 0){
            return false;
        }
        return dfs(root.children, word, 0);
    }

    private boolean dfs(HashMap<Character, TrieNode> children, String word, int index){
        // 对于待匹配单词为TrieNode path的一部分但是没有到达叶子节点的情况
        if (index == word.length()){
            if (children.size() == 0){
                return true;
            }else{
                return false;
            }
        }
        char c = word.charAt(index);
        // 如果当前节点的父节点的孩子里包含当前的字符
        if (children.containsKey(c)){
            if (index == word.length() - 1 && children.get(c).isLeaf){
                return true;
            }
            return dfs(children.get(c).children, word, index + 1);
        // 如果当前字符为.
        }else if (c == '.'){
            // 遍历当前节点的父节点的每个孩子
            for (Map.Entry<Character, TrieNode> child : children.entrySet()){
                if (index == word.length() - 1 && child.getValue().isLeaf){
                    return true;
                }
                // 如果任意一条path正确也为true
                if (dfs(child.getValue().children, word, index + 1)){
                    return true;
                }
            }

            return false;
        }

        return false;
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
