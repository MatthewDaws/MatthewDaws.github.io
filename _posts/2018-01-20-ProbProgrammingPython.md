---
layout: post
title: Probabilistic programming in Python
---

Later in the week I will [give a talk](https://csapblog.leeds.ac.uk/an-introduction-to-bayesian-statistics-and-linear-regression-in-python/)
to the [Centre for Spatial Analysis &amp; Policy](https://www.geog.leeds.ac.uk/research/csap/) group in Geography, at Leeds Uni.  See the [GitHub Repo](https://github.com/MatthewDaws/Python_bits/tree/master/pymc_etc/Talk) 
for details.

I had a few aims:

- Show that it's very possible to perform standard statistical analysis in Python, using tools like
  [pandas](http://pandas.pydata.org/) and [statsmodels](http://www.statsmodels.org/stable/index.html).
  - I prefer Python over R because, well, I know Python well, and I don't know much R.  But Python is
  a multipurpose programming language, and I like the flexibility to work in a "notebook" style, using
  tools like pandas, but also the ability to develop (and test, etc.) modules and packages.
  - I am also extremely sympathetic to the argument made by e.g. [Richard McElreath](http://xcelab.net/rm/)
  in his book [Statistical Rethinking](http://xcelab.net/rm/statistical-rethinking/):

    > That is the reason that this book insists on working with the computational nuts and bolts...  This requires knowing the statistical model in greater detail than is customary, and it requires doing the computations the hard way, at least until you are wise enough to use the push-button solutions.

    (See page 4).  That is, maybe being forced to think about how to perform statistical analysis in a slightly more verbose environment is no bad thing.
  - I don't want to get into an R vs Python argument; just to point out that you _can_ use Python.
- Another big aim was to evangelise about Bayesian methods.  Or at least being _explicit_ about statistical models, parameter fitting, and, if you must, what hypotheses you are actually testing.  To my, self-taught, mind, a Bayesian approach is rather natural.
- Finally, to have some discussion of [Probabilistic Programming](https://en.wikipedia.org/wiki/Probabilistic_programming_language), here using
[pymc3](http://docs.pymc.io/).  That is, the way we code with `pymc3` is such that certain variables are actually _random variables_ from which we typically later take samples from, using MCMC techniques.

I would have liked to have time to discuss [seaborn](https://seaborn.pydata.org/) but, time is finite.

For a dataset, I extracted the main data from the article [Expectations of brilliance underlie gender distributions across academic disciplines](http://science.sciencemag.org/content/347/6219/262).  I want to [blog more]() about this later.  I had hoped to get a discussion going with the audience (as I am not a statistician by training or trade) but unfortunately turnout from senior colleagues was rather low.

I did have an interesting chat with my colleague [Roger Beecham](https://www.geog.leeds.ac.uk/people/r.beecham) (and [homepage](http://www.roger-beecham.com/)) who is speaking about [data visualisation](https://csapblog.leeds.ac.uk/meeting-schedule-201718/) soon.  He pointed me to a [great paper](http://www.mjskay.com/papers/infovis2015-ranking-correlation.pdf) by Matthew Kay and Jeffrey Heer.  This allows me to bang my reproducible research drum. The Kay-Heer paper says:

  > This paper would not have been possible without the public release of 
data from Harrison et  al.  [1]. That release of data contributes to a 
broader conversation not only about the results of any particular study, 
but the analysis of data, and the accumulation of datasets and shared knowledge.

Here the authors perform a re-analysis of data which has been made freely and easily available; made available with the original R scripts which performed the original analysis.  This can be contrasted with the data I used, which was available (yay!) but only in a PDF file, which required some work on my part to get into a PDF file, and with no trace of the software stack and _precise_ procedures used to analyse the data.  We should all publish our data and methods.
