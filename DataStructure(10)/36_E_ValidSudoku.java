/*
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
*/
// Tag: Hash Table

// 用一个哈希表存储在检查某个行，列或者块时某个元素是否已经出现过。分别检查行，列和块，检查块的时候注意坐标横除竖模
