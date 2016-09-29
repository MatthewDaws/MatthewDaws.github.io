---
layout: post
title: Random sampling
---

This post, very tangentially, relates to a quiz we set job candidates.  If you are applying for a job at my current company, and somehow work out I work there, and find this, then you probably half deserve a job anyway.

Suppose you have a population \\( P \\) and some test as to whether a member of the population is good or bad.  We want to find a "random good member".  There are two methods that come to mind:

   - Random sampling: pick a member at random, test it, if it passes return it, otherwise try again.
   - Randomly order \\(P\\) and work through the whole set, returning the first good member.
   
The first method has the virtue of being simple.  The second method uses a lot of memory, if \\(P\\) is large.  But on closer thought, what if the proportion of "good" members is rather small.  The 2nd method is guaranteed to find a good member in $$ O(\vert P \vert) $$.  How slow can the first method be?

<!--more-->

Let \\(B\subseteq P\\) be the set of bad members.  The first method fails to find a good member in \\(n\\) terms with probability \\[ \left( \frac{\vert B\vert }{\vert P\vert} \right)^n \\]
(The chance of repeatedly choosing a bad member).

   - So, if half your members are bad, then you need just 7 goes to be 99% sure of finding a good member.
   - If only 1% of your population is good then you need 459 trials to be 99% sure of find a good member.
   
For the 2nd method, by "random ordering" I mean picking a member of the symmetric group of order $$ N = \vert P \vert $$ at random and applying it to the set.  We can do this [in time proportional to $$N$$](https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle).  The algorithm is simple: choose an unpicked member of $$ P $$ at random and take it as the next member of the random ordering, and then repeat.  How long does it take to find a good member?  The chance of choosing only bad members for the first $$n$$ goes is
\\[ \frac{\vert B \vert  (\vert B\vert -1) (\vert B\vert-2) \cdots (\vert B\vert - n+1)}
{\vert P \vert  (\vert P\vert -1) (\vert P\vert-2) \cdots (\vert P\vert - n+1)} \\]

   - So this will be quicker than method one, always.
   - But as $$P$$ becomes large, the limit is the same.
   
I'm not sure I've come to any conclusion.  Method 1 is simple and fast if the good population is not too small.  Method 2 needs some storage space, but is more predictable if $$P$$ is not too large and the proportion of good members is very small.  If $$P$$ is very large and the proportion of good members very small, you probably need a better idea than simple sampling.

A more mathematical question presents itself.  Suppose we do away with the good and bad members and simply ask:

   > Given a population $$P$$ and sampling at random ("with replacement") what's the expected number of samples I need to see 50% (or any fixed proportion) of the population.