---
layout: post
title: LuxRender of my profile picture
---

![Profile Picture]({{ baseurl }}/public/profile.jpg)

As a change, here is a puzzle I never quite got to the bottom of a few years ago.  Just some flattened disks arranged in a circle and then rendered using LuxRender.  I rather like the effect.  There are two ways to make a cylinder shape: either use the inbuilt primitive objects (so a cylinder with disks top and bottom) or export a cylinder from Blender as a mesh.  Actually, I rolled my own using a python script:

<!--more-->

![Flat faces]({{ baseurl }}/public/new_mesh.jpg)

Ah, yes, all the faces are flat which isn't what we want.  If we specify normals, then these are interpolated across the fact of each triangle, as we want.  However, then we get a difference between using a mesh, and using intrinsics.

![Flat faces]({{ baseurl }}/public/new_mesh_normals.jpg)

![Flat faces]({{ baseurl }}/public/new_intrinsics.jpg)

The version with intrinsics in on the bottom.  The picture on the bottom is with intrinsics, and shows many more reflections.  I wonder if the difference is because a triangle mesh is assumed to be a "closed" object, whereas the cylinder primitive is not (it is like an infinitely thin toilet-roll tube), so I don't know if there are "missing" internal reflections / refractions.

See [more and bigger pictures]({{ baseurl }}/old_art/index.html#work).