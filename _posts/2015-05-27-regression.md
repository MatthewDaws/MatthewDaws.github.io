---
layout: post
title: Regression from a (simple) Bayesian perspective
---

How might we tackle simple regression from a Bayesian perpsective?  Our model will be that we are given points \\( (x\_i)\_\{i=1\}^n \\) (which we assume we _know_, at least to a very high accuracy) and dependent points \\( y_i^{re} = \alpha + \beta x_i \\), but we only observe \\( y_i \\) where \\( y_i = y_i^{re} + e_i \\) where the \\( e_i \\) are our "uncertainties" (I like the line: "if they were errors, we would have corrected them!", see footnote 10 of [arXiv:1008.4686](http://arxiv.org/abs/1008.4686)) usually modelled as iid \\( N(0,\sigma^2) \\).  The likelihood is then

\\[ f(y|\alpha,\beta,\sigma) = \prod (2\pi\sigma^2)^{-1/2}
\exp\Big( -\frac{1}{2\sigma^2} (y_i - (\alpha + \beta x_i))^2 \Big) \\]

Finding the MLE leads to [Least Squares Regression](https://en.wikipedia.org/wiki/Ordinary_least_squares).  A simple Bayesian approach would be to stick some prior on $\alpha, \beta, \sigma$, but of course, this raises the question of _how_ to do this!

Anyway, another [Ipython notebook](http://nbviewer.ipython.org/github/MatthewDaws/Python_bits/blob/master/monte_carlo/Bayesian%20Statistics.ipynb) which develops some of the basic maths, and then uses [emcee](http://dan.iel.fm/emcee/current/) and the [Triangle Plot](https://github.com/dfm/triangle.py) to make some simulations and draw some plots of posterior distributions.

<!--more-->

The approach taken at [Pythonic Perambulations](https://jakevdp.github.io/blog/2014/06/14/frequentism-and-bayesianism-4-bayesian-in-python/) is to consider transformations, motivated by the fact we can "swap the roles of \\( (x_i) \\) and \\( (y_i) \\)".  I'm afraid I think this violates our modelling assumptions (see, for example, footnote 5 in [arXiv:1008.4686](http://arxiv.org/abs/1008.4686)).  Similarly, the PP Python code adds "noise" to the \\(x_i\\) as well, which violates our modelling.  We have \\(x_i^{re}\\) but only observe \\(x_i = x_i^{re}+f_i\\) with again \\(f_i\\) iid \\(N(0,\sigma_1^2)\\) then \\[ y_i = \alpha + \beta (x_i - f_i) + e_i = \alpha + \beta x_i + (e_i - \beta f_i) = \alpha + \beta x_i + g_i \\] where now \\(g_i\\) are iid \\( N(0,\sigma^2 + \beta^2 \sigma_1^2) \\).  So while the uncertainties are still independent and normal, the variance depends on \\( \beta \\).  I think one should really include this in the model.

For a classical Bayesian approach to line fitting, I found [Michael Jordan's lectures](http://www.cs.berkeley.edu/~jordan/courses/260-spring10/lectures/) to be a nice reference.  In the case of linear regression using standard Hierarchical models, it's not really necessary to use MCMC methods.