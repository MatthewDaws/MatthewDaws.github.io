---
layout: post
title: More formal working
---

I am a big fan of [Jupyter notebooks](http://jupyter.org/) and similar (e.g. [R Markdown](http://rmarkdown.rstudio.com/)) systems which allow you to mix code and documentation, preferably in a browser (which allows sharing).

However, I've found that it's quite easy to fall into a "hacking" work pattern of developing quite a lot of code, and mixing it up with substantial data processing.  This leads to a number of [anti-pattern](https://en.wikipedia.org/wiki/Anti-pattern)s:

- The code begins to completely dominate, vs the documentation, or overview, big picture view.
- I fall into the habit of restarting the notebook, wasting time on reloading data, and then making small changes to an analysis.
- Constant minor editing and then "shift-return"ing through a load of cells.

This leads to wasting time; to getting lost (have I tried this minor variation before?) and general frustration.

A better working pattern seems to be the following:

- Prototype quickly in a notebook
- But before long, start to formally develop code in a formal package.  A good directory layout is:

        |-- my_package
            |-- __init__.py
            |-- load.py
            |-- analysis.py
        |-- tests
            |-- __init__.py
            |-- load_test.py
            |-- analysis_tets.py
        |-- notebooks
            |-- Clean Data.ipynb

- A good trick to import data without worrying about `setup.py` and installing is to start each notebook with

        import os, sys
        sys.path.insert(0, os.abspath("..))

With the above directory layout, this adds the base directory to the python search path, so that

        import my_package

will work; and will load the working version (and not a version which you might have installed).

- Then I move code out of the notebooks into the package
- I write tests as I go along.  I tend not to go full [TDD](https://en.wikipedia.org/wiki/Test-driven_development), but with Python especially, having some basic tests which load the package and run most of the code is a great way to catch silly errors which an IDE will struggle to find (e.g. namespace related issues).

I call this working a bit more "formally".  As with many processes in software development, it slows you down initially, but in the long-run you win.

- By writing formal functions and classes, it forces me to confront design issues, algorithm choice,and scientific/research questions properly.  It's all too easy to hack away in a notebook, think "this is probably okay, but I should think more closely about it later", and then never revisit the decision.
- The code naturally ends up being documented and [well-factored](https://en.wikipedia.org/wiki/Decomposition_(computer_science)).
- With the code packaged away, the notebooks become much cleaner, allowing you to concentrate on presentation.
- Comparing parameters and different algorithms becomes a lot easier.
- From a [Reproducible](http://reproducibleresearch.net/) [research](https://en.wikipedia.org/wiki/Reproducibility#Reproducible_research) perspective, this is a big win.

I'm currently using this process with the notebook here: [Comparison methods](https://github.com/QuantCrimAtLeeds/PredictCode/blob/master/evaluation/Comparison%20with%20synthetic%20data.ipynb) in the day job.
