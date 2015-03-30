// Write EisenScript code here...

set maxdepth 8

{y -9 s 200 0.1 200 } box

R1

rule R1 {
  {s 10 } drawbox
  {x -4.5 y -4.5 z -4.5 s 0.5} R1
  {x 4.5 y -4.5 z -4.5 s 0.5} R1
  {x -4.5 y -4.5 z 4.5 s 0.5} R1
  {x 4.5 y -4.5 z 4.5 s 0.5} R1
}

rule drawbox {
  {x -0.45 y -0.45 s 0.1 0.1 1 } box
  {x 0.45 y 0.45 s 0.1 0.1 1 } box
  {x -0.45 y 0.45 s 0.1 0.1 1 } box
  {x 0.45 y -0.45 s 0.1 0.1 1 } box
  {x -0.45 z -0.45 rx 90 s 0.1 0.1 1} box
  {x -0.45 z 0.45 rx 90 s 0.1 0.1 1} box
  {x 0.45 z 0.45 rx 90 s 0.1 0.1 1} box
  {x 0.45 z -0.45 rx 90 s 0.1 0.1 1} box
  {z 0.45 y 0.45 ry 90 s 0.1 0.1 1} box
  {z 0.45 y -0.45 ry 90 s 0.1 0.1 1} box
  {z -0.45 y 0.45 ry 90 s 0.1 0.1 1} box
  {z -0.45 y -0.45 ry 90 s 0.1 0.1 1} box
}