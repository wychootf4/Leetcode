/*
Calculate the a^n % b where a, b and n are all 32bit integers.

Example
For 2^31 % 3 = 2

For 100^1000 % 1000 = 0

Challenge
O(logn)
*/

// 思路：用到的主要思路是a^n = (a^(n / 2)) ^ 2
