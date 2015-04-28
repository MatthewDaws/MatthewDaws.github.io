---
layout: post
title: Codingame
---

I've spent some time on [Codingame](http://codingame.com/) which is an on-line [Competitive programming](https://en.wikipedia.org/wiki/Competitive_programming) site, based around computer games.  (Though only loosely: some puzzles are just puzzles like Google Code Jam, and the "games" are often pretty obviously graph or search based puzzles).  But it's kind of fun: certainly the more interactive puzzles, where your solution has to respond to unknown, almost real-time, inputs, is entertaining.

<!--more-->

Of note is that it features an inbuilt IDE with support for a lot of different languages.  However, the IDE is lacking any sort of debugger, or interactivity, so debugging is reduced to printing things to the error log.  To be honest, I mostly write the code offline on self constructed test cases.

Another annoying feature is that the compiled languages are compiled without optimisation.  I sort of think this hugely reduces the point of using something like C++.  The beauty of this language is that you can write close to the hardware (a la C) if you wish, or you can write much higher level code, and let the compiler work its magic.  Even more frustrating is that many of the games require your programme to give a response in a fixed, short time (e.g. < 100ms.)

By way of an example, I recently had cause to implement [Berlkamp's algorithm](https://en.wikipedia.org/wiki/Berlekamp%27s_algorithm) (following Knuth's presentation) over the field with 2 elements.  I shan't give a spoiler for saying _why_.  On the Codingame server, my test case took between 7.3 and 6.4 ms, while my 2013 era laptop came in at 7.4 ms, this all for C++ with no optimisations (GCC 4.9.2).  With `-O2` optimisations, my laptop turns in 0.87 ms, eight and a half times faster.  By comparison, Python3 turns in a time of 300ms, 40 times slower!

(I apologise for the rather minimal [implementation](https://github.com/MatthewDaws/CPP_Learning/tree/master/berlekamp).  In particular, I fail to follow the best practice of defining `operator+` in terms of `operator+=`.)
