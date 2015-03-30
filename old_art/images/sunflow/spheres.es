set maxdepth 17

{ y -2.5  s 100 0.1 100 } box

R1

rule R1 {
3 * { ry 120 h 20 } endsphere
{ s 2} sphere
}

rule endsphere {
{ z 1 y -1 ry 30 s 0.6 } R1
}