---
layout: default
title: Nexus 7
---

<h1>Nexus 7 notes</h1>

<h2>Video encoding</h2>

<p>You cannot play mpeg files copied directly from a DVD.  However, MPEG4 and h264 files play
without issue.</p>

<ul><li>ffmpeg default's work fine: <code>ffmpeg -i inputfile output.mp4</code> automatically uses
h264.</li>
<li>To get some manual control over bitrates, use <code>-b:v 670k</code> for video, and
<code>-b:a 128k</code> for audio.  These values are approximately the defaults.</li>
<li>To do two pass, you need to specify a bitrate, and on the first run add <code>-pass 1</code> and on
the second <code>-pass 2</code>.</li>
<li>My old version of VLC doesn't work-- maybe the audio is wrong?</li>
</ul>
