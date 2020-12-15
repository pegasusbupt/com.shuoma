"""
Given two arrays of length m and n with digits 0-9 representing two numbers.
Create the maximum number of length k <= m + n from digits of the two.
The relative order of the digits from the same array must be preserved.
Return an array of the k digits. You should try to optimize your time and space complexity.

Example 1:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
return [9, 8, 6, 5, 3]

Example 2:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
return [6, 7, 6, 0, 4]

Example 3:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
return [9, 8, 9]
"""

from alg.label import Label

Label(Label.Array, Label.Sorting, Label.Stack, Label.LeetCode)

class CreateMaxNumber(object):
  def maxNumber(self, nums1, nums2, k):
    def getMax(nums, t):
      ans = []
      size = len(nums)
      for x in range(size):
        while ans and len(ans) + size - x > t and ans[-1] < nums[x]:
          ans.pop()
        if len(ans) < t:
          ans += nums[x],
      return ans

    def merge(nums1, nums2):
      return [max(nums1, nums2).pop(0) for _ in nums1 + nums2]

    len1, len2 = len(nums1), len(nums2)
    res = []
    for x in range(max(0, k - len2), min(k, len1) + 1):
      tmp = merge(getMax(nums1, x), getMax(nums2, k - x))
      res = max(tmp, res)
    return res

from unittest import TestCase
class CreateMaxNumberTest(TestCase):
  def test_maxNumber(self):
    ins = CreateMaxNumber()
    cases = [
      ([3, 4, 6, 5], [9, 1, 2, 5, 8, 3], 5, [9, 8, 6, 5, 3]),
      ([6, 7], [6, 0, 4], 5, [6, 7, 6, 0, 4]),
      ([3, 9], [8, 9], 3, [9, 8, 9]),
    ]
    for case in cases:
      self.assertEqual(case[-1], ins.maxNumber(case[0], case[1], case[2]))
