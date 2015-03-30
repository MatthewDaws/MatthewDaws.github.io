---
layout: default
title: C++ stuff
---

<h1>C++ stuff</h1>

**Mingw64**

<p>This is a very idiosyncratic guide to getting G++ working on Windows, using Mingw and the mingw-w64
project.  Works on my machine, Windows 7, October 2014.</p>

<ul>
<li><a href="http://mingw-w64.sourceforge.net/">MinGW-w64</a> -- Offical project page.</li>
<li>I want the lastest G++ version, which means I have no choice but to use the "Mingw-builds project".
Sadly, the installer fails on the download part with a very cryptic error code.</li>
<li>You can manually download the correct file: the best place to start looking seems to be
<a href="http://sourceforge.net/projects/mingw-w64/files/Toolchains%20targetting%20Win64/Personal%20Builds/mingw-builds/">SourceForge link</a>.</li>
<li>The works, but the &quot;threads-win32&quot; seems not to do what I want: provide a working
version of the C++11 &lt;future&gt; header, etc.</li>
<li>However, the &quot;threads-posix&quot; does work!  At least, for a console application not using
anything fancy like 3rd party DLLs.  That's enough for me for now...</li>
<li>A tip of the hat to <a href="http://scrupulousabstractions.tumblr.com/post/36441490955/eclipse-mingw-builds">Oldish post on Eclipse and Mingw</a></li>
<li>The MinGW-w64 distribution doesn't by default come with MSYS, which is useful for building linux
based projects.  You can download a pre-built copy from <a href="http://sourceforge.net/projects/mingw-w64/files/External%20binary%20packages%20%28Win64%20hosted%29/">SourceForge, MinGW-w64</a>.</li>
</ul>

<p>Did again at a later date with SEH exception handling.</p>


**Notes on threads**

<ul>
<li><a href="http://mingw-users.1079350.n2.nabble.com/pthread-vs-mthreads-td7114500.html">pthread vs mthreads</a>
-- Some discussion.  Should probably use -mthreads.</li>
<li><a href="http://osdir.com/ml/MinGW-users/2013-03/msg00171.html">async() weirdness</a> -- Some discussion
about how the current g++ implementation of async() doesn't start a separate thread.  You need to pass
<code>std::launch::async</code> manually.</li>
</ul>


**Building GMP**

<ul>
<li>On Windows 7, Mingw-w64.  Firstly install MSYS, so we have a working copy of bash and its friends.</li>
<li>Download and unpack the gmp source, navigate to this directory, and then run bash.</li>
<li><code>./configure --enable-cxx</code></li>
<li>Wait...</li>
<li><code>make</code></li>
<li>Wait...</li>
<li>To avoid errors, make sure the absolute path has no spaces in...</li>
<li>Copy <code>gmp.h</code> and <code>gmpxx.h</code> to <code>mingw/x86_64-w64-mingw32/include/</code>
where <code>mingw</code> is the base directory for your MINGW install.</li>
<li>Copy <code>libgmp.a</code> (found in the <code>.libs</code> directory) to
<code>mingw/x86_64-w64-mingw32/lib/</code></li>
<li>Now you can compiler gmp code; remember to link with <code>-lgmp</code> for C files, and with
<code>-lgmpxx -lgmp</code> for C++ files.</li>
</ul>
