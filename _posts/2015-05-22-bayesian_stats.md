---
layout: post
title: Dabbling in Bayesian Statistics
---

Bayesian statistics appeals to me both because it seems more "philosophically correct" than frequentist arguments (I have this data, and want to make an inference from it, rather than worrying about data which "might" exist, given other circumstances).  Also, Bayesian approaches lead to some interesting computational issues, which are interesting in their own right.

TL;DR: In the process of thinking about MCMC methods in sampling from posterior distributions, I became interested in the choice of prior distributions.  Emerging from a lot of reading, you can view my [IPython Notebook](http://nbviewer.ipython.org/github/MatthewDaws/Python_bits/blob/master/monte_carlo/Priors%20and%20transformations.ipynb) on finding an "invariant" prior: if you have a natural family of transformations on your "data space" which is reflected in transformations in the parameter space, then I argue there's a natural reason to expect certain invariance in the prior.

For a simple example, consider normally distributed data with known variance.  If you translate your data by, say, 5 units, then you would expect your inference about the (unknown) mean to also be exactly translated by 5 units (but to otherwise be the same).  This leads to a uniform (improper) prior.  I treat the case of unknown mean and variance, which leads to the Jeffreys prior, which is regarded as not so great in the 2 parameter case.  Hey ho.  This was also a good excuse to learn and play with [SymPy](http://www.sympy.org/).

<!--more-->

# Priors #

The main problem with "basic" Bayesian Statistics is the choice of prior (don't talk about [nonparametric](https://en.wikipedia.org/wiki/Nonparametric_statistics) ideas, which scare me in a Bayesian setting, but are of course of huge interest in machine learning which itself intersects hugely with big data issues).  Here there seems to be endless and amusing debate.

From a Mathematics background, I'm unused to such debate: in [pure maths](https://en.wikipedia.org/wiki/Pure_mathematics) there is really no debate over what is "correct" (people make mistakes, but, once pointed out, there is no debate; and there are [debates](https://en.wikipedia.org/wiki/Shinichi_Mochizuki) over things which seems essentially "uncheckable", but that's a difference issue).  Of course, statistics with no real data is just probability theory, and statistics about actual data is science, a different and arguably more interesting matter.

Amusing example 1 is the following classical thought experiment on "experiment design".  I have a coin, and with the estimate the probability that it comes up heads.  I toss the coin m times and see r heads.  The posterior is

\\[ \begin{pmatrix} m \\\\ r \end{pmatrix} \theta^r (1-\theta)^{m-r} p(\theta) \\]

Alternatively, perhaps I had actually decided to stop once I'd seen r heads.  So now n is the random variable, and the posterior becomes

\\[ \begin{pmatrix} m-1 \\\\ r-1 \end{pmatrix} \theta^r (1-\theta)^{m-r} p(\theta) \\]

   - MacKay [page 463](http://www.inference.phy.cam.ac.uk/itprnn/book.pdf) uses this example to support a Bayesian approach.  Once you've fixed the prior \\(p(\theta)\\) we see that the posteriors are the same (once normalised) and so the experiment design is irrelevant to our inference.
   - Jordan [page 3, lecture 10](http://www.cs.berkeley.edu/~jordan/courses/260-spring10/lectures/lecture10.pdf) says almost the exact opposite: experiment design does matter.  Of course, Jordan is here taking an "objective" line, and using the Jeffreys prior, which being computed from the likelihood does change (we ultimately take the expectation over m, not over r, in the 2nd case).
   
There's no paradox here, but there is a certain irony in using the same thought experiment to make almost contradictory points.

I also want to think about simple linear regression, but that's a post for another day...
