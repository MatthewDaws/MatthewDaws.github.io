---
layout: post
title: Thoughts about C#
---

On the basis that knowing a modern OO language would be good, and on the basis that everyone and their granny knows JAVA, I thought I'd look at C#.  Getting going with C# was a bit tedious: I end up looking things up endlessly, and puzzling over things for a while before they "click" and seem obvious (which is always annoying, as then I'm left wondering why it wasn't obvious from the start).  You wait for muscle memory to build...  

Example: A small mistake lead to some musings on how sort algorithms are implemented: [StackOverFlow Question](http://stackoverflow.com/questions/30865510/why-does-failing-to-recognise-equality-mess-up-c-sharp-listt-sort) (the actual problem came from thinking about orderings in Python and C++ too much, where you specify a [strict weak ordering](https://en.wikipedia.org/wiki/Weak_ordering#Strict_weak_orderings), basically "less than", and then porting that to a `CompareTo` method and missing a corner-case.  This came up in a [CodeJam problem]({{ baseurl }}/20152.html).)

(Aside: Stackoverflow never ceases to amaze me: I asked a kind-of-silly question, which probably I could have thought about myself, and I get a wonderful, long, complete answer...)

### LINQ ###

I do really like LINQ, and the way it reminds one of list comprehensions.  Turning this...

{% highlight c# %}
var allEdges = new List<Tuple<T,T>>()
foreach(var edge in edges)
{
	foreach(var e in graph.AsDirectedEdges(edge.Item1, edge.Item2))
	{
		allEdges.Add(e);
	}
}
{% endhighlight %}

into this...

{% highlight c# %}
allEdges.Add(from edge in edges
	from e in graph.AsDirectedEdges(edge.Item1, edge.Item2)
	select e);
{% endhighlight %}

is rather nice.  (The 2nd snippet uses a simple extension method to `Add`; again, extension methods seem pretty neat.)

### Generics ###

However, give me templates from C++ anyday!  I really (really) like the duck-typing nature of templates: it's sort of like using a dynamic language, but with compile time checking.  (Of course, one also has to enjoy page long error messages...  Maybe "concepts" will arrive one day.)  That C# just doesn't let you do

{% highlight c# %}
public static MyMethod<T>(T one, T two)
{
   return one + two;
}
{% endhighlight %}

is very tedious!  Similary, the limitations of `using` (i.e. it's lack of being a replacement for `typedef`) in C# are annoying: I typically like setting up private typedefs in a class, to save typing out long class names.  Or, giving `Tuple<T,S>` a more domain-specific name: `using DirectedEdge = Tuple<T,T>` for example.

An analogue of `namedtuple` from Python would be neat: a simple way to setup a struct or class which just needs some fields, and default ordering / hashing etc.  C# does do this with anonymous types, but the result is, well, anonymous...

### Const ###

It would also be nice to have something similar to C++ `const`.  `readonly` is too limited, and class only.  A `readonly` `List<T>` can still have its items changed, and that's not my definition of "readonly"!  For a statically typed, compile time checked language, decorating methods with `const` is, I think, a wonderful thing: it's another way to signal to the compiler (and other people reading your code) your intentions.

### Async ###

Is next up to be toyed with...!