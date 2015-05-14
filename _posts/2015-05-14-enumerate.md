---
layout: post
title: Enumerate in C++
---

One of the fun things about [C++11](https://en.wikipedia.org/wiki/C%2B%2B11) is that minor changes allow you to write somewhat more expressive (aka [Pythonic](http://preshing.com/20141202/cpp-has-become-more-pythonic/)) code.

A common pattern is to iterate over an array while also knowing the index you are at.  Python does this with the `enumerate` keyword:

{% highlight python %}
for index, x in enumerate(container):
   print("At {} is {}".format(index, x))
{% endhighlight %}

In C++ the idiomatic way to do this is perhaps:

{% highlight c++ %}
std::vector<int> container;
...
for (int i = 0; i < container.size(); ++i) {
   std::cout << "At " << i << " is " << container[i] << "\n";
}
{% endhighlight %}

This has its flaws though (and is tedious to type).  Using g++, if you have warnings on, `-Wall`, then you'll see complaint, as the type of `container.size()` is, typically, of size `std::size_t` which on my machine is `unsigned long long` (aka an unsigned 64-bit number).  Warnings aside, as `int` is on my machine a 32-bit unsigned number, we could potentially have problems if the size of container is more than around 2 billion (unlikely, but possible these days).

<!--more-->

We could write:

{% highlight c++ %}
for (std::size_t i = 0; i < container.size(); ++i) {
{% endhighlight %}

or more tediously, but more showy:

{% highlight c++ %}
for (decltype(container.size()) i = 0; i < container.size(); ++i) {
{% endhighlight %}

Surely we can do better?  So I sat down and wrote a little template function `enumerate(T container)` which returns a class implementing the methods `begin()` and `end()`, these in turn returning custom iterators which count up from `0` to `container.size() - 1`.  You can thus write:

{% highlight c++ %}
foreach(auto index : enumerate(container)) {
   std::cout << "At " << index << " is " << container[index] << "\n";
}
{% endhighlight %}


## Subtle issues 1 ##

What makes C++ interesting, and frustrating, is certain subtle issues.  Our iterator returns a number, for which we implement `operator*`:

{% highlight c++ %}
	SizeType operator*() { return index; }
{% endhighlight %}

That's great, but what if the user tries to capture the index by reference:

{% highlight c++ %}
foreach (auto& i : eneumerate(container))
{% endhighlight %}

We get the cryptic error "error: invalid initialization of non-const reference of type 'long long unsigned int&' from an rvalue of type 'long long unsigned int'"  ([Explanation from StackOverflow](http://stackoverflow.com/questions/8293426/error-invalid-initialization-of-non-const-reference-of-type-int-from-an-rval).  Basically, when we `return index;` we are _not_ returning `index`, but rather a _temporary copy_ (aka r-value) and, as you might hope, you're not allowed to take the reference of a temporary copy...)  We can "fix" this by:

{% highlight c++ %}
	SizeType& operator*() { return index; }
{% endhighlight %}

However, we've now giving the user access to the index, so this

{% highlight c++ %}
foreach (auto& i : eneumerate(container)) {
   std::cout << container[i] << "\n"; ++i; }
{% endhighlight %}

prints every second term of container.  Is that what we want?  If not, then we can use a `const`:

{% highlight c++ %}
	const SizeType& operator*() { return index; }
{% endhighlight %}

The command `++i;` now gives an error "error: increment of read-only reference 'i'" which is, to my mind, (a) not cryptic; and (b) makes it clear to the user what the issue is.


## Subtle issues 2 ##

Let's get ambitious, and use `enumerate` for something else:

{% highlight c++ %}
struct range {
   int size()const { return 8; }
};
...
for (auto n : enumerate(range{}))) {
   std::cout << n << " ";
}
{% endhighlight %}

This seems to work, so try something more ambitious:

{% highlight c++ %}
class range {
private:
   int limit;
public:
   range(const int l) : limit{l} {}
   int size()const { return limit; }
};
...
for (auto n : enumerate(range(8)))) {
   std::cout << n << " ";
}
{% endhighlight %}

This again seems to work, but it may not...  Suppose we add:

{% highlight c++ %}
   ~range() { std::cout << "~range() called." << std::endl }
{% endhighlight %}

Then the output is:

    range::~range() called.
    0 1 2 3 4 5 6 7
    
Erm...  Well, a `range` object is created with initial value `8`, this is passed to the `enumerate` function which takes a const reference of the range object which is passed to a class which implements `begin()` and `end()`; the class also holds a reference.  Then the `range` object ends its life, and the destructor is called.  This occurs before the `for` loop, and with more checking, actually before the call to `end` which uses the `size` method.  Why?  Because `range(8)` creates an object, whose lifespan is just during the call to the function `enumerate`.  That it works at all is a fluke...

To fix this, let's examine some design assumptions:

   - All we need to know about our "container" is its size.
   - If you explore things, then the `for (type i : container)` expression calls both `container.begin` and `container.end` at the start, and _caches_ the two resulting iterators.  It's equivalent to:
{% highlight c++ %}
auto it = container.begin();
auto itend = container.end();
for (; it != itend; ++it) { ... }
{% endhighlight %}
   and not, as perhaps you might think, to:
{% highlight c++ %}
for (auto it = container.begin(); it != conatiner.end(); ++it) { ... }
{% endhighlight %}
   - Thus, _even if we wanted to_ we cannot support the container length changing in the loop (not quite true: the `end` iterator could "know" that is was the end iterator, and each time `operator!=` or `operator==` was called on the iterators, we could query the container.)
   - So why not just read `container.size()` at the start?  This will simplify things, and fixed our bug.

 
## Complexity ##

Of course, we've written quite a lot of code!  What does this do?

{% highlight c++ %}
for (auto i : enumerate(container)) { test_func(conatiner[i]); }
{% endhighlight %}

It calls `test_func` on each entry of `container`.  With `-O1` this compiles to this inner loop:

{% highlight asm %}
.L4:
	movq	(%rsi), %rax
	movl	(%rax,%rbx,4), %ecx
	call	_Z9test_funci
	addq	$1, %rbx
	cmpq	%rdi, %rbx
	jne	.L4
{% endhighlight %}

As we might hope, the compiler works its magic, and we pay no overhead for our mucking around.


## Links ##

I did some playing around with the idea of "generators" in C++ some time ago (you can't do this properly, except maybe with some fooling with lambda expressions, but the idea was to use range based for loops to process data being delivered by a class.)

   - [GitHub link](https://github.com/MatthewDaws/CPP_Learning/tree/master/generators)
   - For 1st attempt at the "enumerate" idea, [On GitHub](https://github.com/MatthewDaws/CPP_Learning/tree/master/generators/enumerate.cpp)
   - The 2nd attempt, [On GitHub](https://github.com/MatthewDaws/CPP_Learning/tree/master/generators/enumerate2.cpp)
