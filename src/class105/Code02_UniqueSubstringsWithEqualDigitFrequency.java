package class105;

import java.util.Arrays;
import java.util.HashSet;

// 每个数字的频率都相同的独特子字符串的数量
// 给你一个由数字组成的字符串s
// 返回s中独特子字符串数量
// 其中的每一个数字出现的频率都相同
// 测试链接 : https://leetcode.cn/problems/unique-substrings-with-equal-digit-frequency/
public class Code02_UniqueSubstringsWithEqualDigitFrequency {

	public static int equalDigitFrequency(String str) {
		long base = 499;
		char[] s = str.toCharArray();
		int n = s.length;
		HashSet<Long> set = new HashSet<>();
		int[] cnt = new int[10];
		for (int l = 0; l < n; l++) {
			Arrays.fill(cnt, 0);
			long hashCode = 0;
			int curVal = 0, maxCnt = 0, maxKinds = 0, allKinds = 0;
			for (int r = l; r < n; r++) {
				curVal = s[r] - '0';
				hashCode = hashCode * base + curVal + 1;
				cnt[curVal]++;
				if (cnt[curVal] == 1) {
					allKinds++;
				}
				if (cnt[curVal] > maxCnt) {
					maxCnt = cnt[curVal];
					maxKinds = 1;
				} else if (cnt[curVal] == maxCnt) {
					maxKinds++;
				}
				if (maxKinds == allKinds) {
					set.add(hashCode);
				}
			}
		}
		return set.size();
	}

}
