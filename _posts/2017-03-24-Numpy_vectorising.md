---
layout: post
title: Numpy vectorising
---

I asked [this question](http://stackoverflow.com/questions/43000309/vectorise-numpy-code-on-demand) on Stackoverflow, and got a nice answer, but one which I needed to think through a little more.  Here's my conclusions.

My aim was to understand how to write robust code which could take scalars, but which would also do "as expected" on arrays.  Let me expand a little on this, by using a slightly easier example than in the original question.  Suppose `f(x)` is a function which takes a scalar and returns a scalar.  I then want that if `x` is actually an array, of any shape, then `f(x)` will return an array of the same shape as `x`, namely the array obtained by applying `f` to every entry.

You can do this with the `np.frompyfunc` method, but we cannot expect to take advantage of the speed of numpy.  Furthermore, we also obtain an array of objects.

Instead, let me present the "numpy way" and then explain why and how it works.  Firstly, the starting function:

    fixed_array = np.arange(10)
	def f(x):
        return np.sum((x - fixed_array) ** 2)

Here `fixed_array` is global for simplicity.  So what `f` does is to subtract `x` from each entry of `fixed_array`, square, and then sum.  Equivalent to `sum( (x-t)**2 for t in fixed_array )`.  Passing an array into `f` yields an error as `numpy` cannot work out how to compute `x - fixed_array`.  Indeed, we _do not want_ to form this pointwise.  Rather, I think what we really want to do is something like the following:

- For simplicity, suppose `x` is also a 1D array (though in the end `x` should be allowed to be any shape.)   
- Form an array `xx` so that `xx[i][j] = x[i]` for all `i,j`
- Form an array `yy` so that `yy[i][j] = fixed_array[j]` for all `i,j`
- `xx` and `yy` have the same shape.
- Compute `zz = (xx - yy)**2` (pointwise) so that ``zz[i][j] = (x[i] - fixed_array[j])**2` for each `i,j`.
- Sum over the final dimension, thus giving `f(x[i])` in the `i`th position, as required.  

Okay, so here's the answer:

    def fff(x):
       return np.sum((np.asarray(x)[...,None] - fixed_array)**2, axis=-1)

How does this work?

- `np.asarray(x)` returns, if `x` is scalar, an array of shape `(1)` containing `x` as it's entry; and if `x` is already "array like", we get a genuine array.
- Then for an array `y`, the slice `y[...,None]` does the same as (the perhaps more clear) `y[...,np.newaxis]`.  The `...` means the same as `:,:,:` however many times required, and `np.newaxis` gives you a new axis.  If `y` has shape `(2,3,4)` then `y[...,None]` has shape `(2,3,4,1)`.  See [Indexing docs](https://docs.scipy.org/doc/numpy/reference/arrays.indexing.html). 
- Keep working with `yy` and consider how [Broadcasting](https://docs.scipy.org/doc/numpy/reference/ufuncs.html#broadcasting) applies to `yy - fixed_array`
   1. We _prepend_ 1s to the shape of `fixed_array` so it has the same `ndim` as `yy`
   2. We will get an output of size `(2,3,4,n)` where `fixed_array` is of length `n`
   3. If an input has dimension 1 then the single entry in that dimension will be used.
- This gives us exactly what we want, because the entries of `y` will be used in the first dimension(s), and the entries of `fixes_array` in the last dimension.
- We then square entry wise, and sum over the final dimensional, the `axis=-1` command.

See [Notebook](https://github.com/MatthewDaws/Python_bits/blob/master/ipython_notebooks/Numpy%20vectorising.ipynb) for a quick demo and some code.