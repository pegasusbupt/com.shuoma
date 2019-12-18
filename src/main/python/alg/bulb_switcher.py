"""
There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the nth round, you only toggle the last bulb.
Find how many bulbs are on after n rounds.
"""
from alg.label import Label

Label(Label.Arithmetic, Label.LeetCode)

def bulb_switch(n):
  return int(n ** 0.5)
