---
layout: post
title: TileMapBase
---

I've published my first python package on [PyPi](https://pypi.python.org)  (See also the [New PyPi](https://pypi.org) which seems to have finally synced.)

Get it here: [TileMapBase](https://pypi.python.org/pypi/tilemapbase/0.4.3) or [TileMapBase on new PyPi](https://pypi.org/project/tilemapbase/):

> Uses OpenStreetMap tiles, or other tile servers, to produce “basemaps” for use with matplotlib. Uses a SQLite database to cache the tiles, so you can experiment with map production without re-downloading the same tiles. Supports Open Data tiles from the UK Ordnance Survey.

My original aim was to produce a simple, high-level way to use OpenStreetMap style tiles as a "basemap" with MatPlotLib in Jupyter Python notebooks.  Since then, I've also been working on [TileWindow](https://github.com/MatthewDaws/TileWindow) which uses this library to cache tiles, and provides a `tkinter` widget which displays a map-- sort of like GoogleMaps but in Python.  Ultimately for use in my current job: [PredictCode](https://github.com/QuantCrimAtLeeds/PredictCode).
