---
layout: post
title: Hello World
---

I had a need to update and move my old academic website so thought I would take the plunge and host a site on GitHub.  Here is an aide-memoire for myself as to how I did this:

* How to get a website for your GitHub project: [Pages](https://pages.github.com/)

This blog is built using [Jekyll]():

<!--more-->

* How to use Jekyll with pages: [Jekyll with pages](https://help.github.com/articles/using-jekyll-with-pages/)
* Some Jekyll documentation to the same effect: [Jekyll docs](http://jekyllrb.com/docs/github-pages/)

Jekyll is what you might call a "template engine".  If you have the sort of lack of time and artistic skills which I do, you'll need to find a template:

* Useful article: [Smashing Magazine](http://www.smashingmagazine.com/2014/08/01/build-blog-jekyll-github-pages/)
* GitHub page associated with this: [GitHub site](https://github.com/barryclark/jekyll-now)
* Template this site uses: [Hyde](https://github.com/poole/hyde)
* Markdown: [GitHub cheatsheet](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)
* Visual Design: [VisualMess](http://www.visualmess.com/)

You can use \\(\LaTeX \\), but remember to markup inline as `\\(\frac{a+b}{c}\\)` for example, and:

* Load the MathJAX Javascript renderer.  You can do this by inserting the following into `_includes/head.html`:

{% highlight html %}
<script type="text/javascript"
  src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML">
</script>
{% endhighlight %}

I'm converted to Python 3, and mainly use Windows right now, a combination which seems problematic for using pygments.  So this site use Rouge, but YMMV:

* Jekyll on Windows: [Syntax highlighting](https://github.com/juthilo/run-jekyll-on-windows/blob/gh-pages/3-syntax-highlighting.md)

* This all worked absolutely fine locally, but wouldn't work when I uploaded it to GitHub.  After a massive amount of trial and error, and some cryptic error messages, I finally got a useful error message: `rouge` isn't supported by GitHub at present.  So we comment this out in the `_config.yml` file and it all works!

* A final wrickle is that, either locally, or on GitHub, I couldn't get automatic syntax highlighting to work.  So, for now, we'll do it manually with the {% raw %} `{% highlight html %} ... {% endhighlight %}` {% endraw %} form.
