% Mondrian

% Lighter01.png
%   All defaults
% Lighter02.png
%   Trace-depths all set to 16
% Lighter03.png
%   GI: IGI, 64, 1 0.00003, 0
% Lighter04.png
%   Caustics 1000000 kd 200 1.0
% Lighter05.png
%   Caustics 10000000 kd 200 1.0
% Lighter06.png
%   GI
%   Caustics 10000000 kd 200 1.0
% Lighter07.png
%   Trace-depts 8
%   Caustics 10000000 kd 200 1.0
% Lighter08.png
%   Trace-depts 8, GI
%   Caustics 10000000 kd 200 1.0

photons {
	caustics 10000000 kd 200 1.0
}


%%%%% Global illumination

gi {
type igi
samples 64
sets 1
b 0.00003
bias-samples 0
}

trace-depths {
diff 8
refl 8
refr 8
}

%% common settings
image {
	resolution 200 200
	aa 1 1
	samples 4
	filter blackman-harris
}

%% camera
camera {
	type	pinhole
	eye 30 -45 31
	target 0 0 -5
	up 0 0 1
	fov 20
	aspect	1

 	%type	thinlens	
	%eye 30 -45 31
	%target 0 0 -5
	%up 0 0 1
	%fov 20
	%aspect	1
	%fdist	60
	%lensr	1.0
}

light {
  %type point
  type spherical
  color { "sRGB nonlinear" 1 1 1 }
  %power 1000
  %p 6 6 6
  radiance 70
  center 10 10 7
  radius 2
  samples 20
}

%light {
%   type point
%  color { "sRGB nonlinear" 1 1 1 }
%  power 1200
%  p -10 -10 10
%}


%% scene background - comment out if not needed
%background {
%   color  { "sRGB nonlinear" 0 0 0 }
%}


%% Geometry

%% Geometry for box::sphere
object {
	shader none
	transform col 0.001 0 0 0	0 0.001 0 0 0 0 0.001 0	 0 0 0 1
	type sphere
	name "Sphere_1"
}

%% Geometry for box
object {
	shader none
	transform col 0.001 0 0 0	0 0.001 0 0	 0 0 0.001 0	0 0 0 1
	type generic-mesh
	name "Box"
	points 8
		1	1	 1
		1	0	 1
		0	0	 1
		0	1	 1
		0	1	 0
		0	0	 0
		1	0	 0
		1	1	 0

	triangles 12
	  0 3 2
	  0 2 1
	  2 3 4
	  2 4 5
	  3 0 7
	  3 7 4
	  0 1 6
	  0 6 7
	  1 2 5
	  1 5 6
	  5 4 7
	  5 7 6
	normals none
	uvs none
}
		
%%%%% Ground box

shader {
	name "shaderBox0"
	type diffuse
	diff { "sRGB nonlinear" 1 1 1 }
}

instance {
	name "Box0"
	geometry "Box"
	transform col 100 0 0 0 0 100 0 0 0 0 0.1 0 -49.5 -49.5 -9.55 1
	shader "shaderBox0"
}

% Group the glass shaders here

shader {
	name "Yellow"
	type glass
	eta 1.6
	%color { "sRGB nonlinear" 1 0.986667 0.2 }
	color { "sRGB nonlinear" 1 1 0.8 }
	absorbtion.distance 100
	%absorbtion.color { "sRGB nonlinear" 1.0 1.0 1.0 }
}

shader {
	name "Red"
	type glass
	eta 1.6
	%color { "sRGB nonlinear" 1 0 0 }
	color { "sRGB nonlinear" 1 0.75 0.75 }
	absorbtion.distance 100
	%absorbtion.color { "sRGB nonlinear" 1.0 1.0 1.0 }
}

shader {
	name "Blue"
	type glass
	eta 1.6
	%color { "sRGB nonlinear" 0.0149481 0.0149481 0.635294 }
	color { "sRGB nonlinear" 0.75 0.75 0.91 }
	absorbtion.distance 100
	%absorbtion.color { "sRGB nonlinear" 1.0 1.0 1.0 }
}

shader {
	name "White"
	type glass
	eta 1.6
	color { "sRGB nonlinear" 1 1 1 }
	absorbtion.distance 100
	%absorbtion.color { "sRGB nonlinear" 1.0 1.0 1.0 }
}





% Geometry

instance {
	name "Box1"
	geometry "Box"
	transform col -4.76837e-07 0 -9.5 0 0 3.1635 0 0 0.24 0 -1.49012e-08 0 -4.62 -4.41175 0.25 1
	shader "Yellow"
}
		
instance {
	name "Box2"
	geometry "Box"
	transform col -4.76837e-07 0 -9.5 0 0 4.21378 0 0 0.24 0 -1.49012e-08 0 -4.62 1.27795 0.25 1
	shader "Red"
}
		
instance {
	name "Box3"
	geometry "Box"
	transform col 0 0 -3.1635 0 0 9.5 0 0 0.24 0 0 0 5.38 -4.25 0.41175 1
	shader "Red"
}
		
instance {
	name "Box4"
	geometry "Box"
	transform col 0 0 -2.10689 0 0 9.5 0 0 0.24 0 0 0 5.38 -4.25 -2.96037 1
	shader "Red"
}
		
instance {
	name "Box5"
	geometry "Box"
	transform col 0 0 -4.21378 0 0 9.5 0 0 0.24 0 0 0 5.38 -4.25 -5.27795 1
	shader "Yellow"
}
		
instance {
	name "Box6"
	geometry "Box"
	transform col 2.10689 0 0 0 0 9.5 0 0 0 0 0.24 0 -3.30682 -4.25 -9.62 1
	shader "Blue"
}
		
instance {
	name "Box7"
	geometry "Box"
	transform col 2.10689 0 0 0 0 9.5 0 0 0 0 0.24 0 -1.03963 -4.25 -9.62 1
	shader "Red"
}

instance {
	name "Box8"
	geometry "Box"
	transform col 9.5 0 0 0 0 0 2.10689 0 0 -0.24 0 0 -4.25 5.62 -6.03963 1
	shader "Red"
}
		
instance {
	name "Box9"
	geometry "Box"
	transform col 9.5 0 0 0 0 0 4.21378 0 0 -0.24 0 0 -4.25 5.62 -3.72205 1
	shader "Blue"
}

instance {
	name "Box10"
	geometry "Box"
	transform col 3.1635 0 0 0 0 1.05345 0 0 0 0 0.24 0 -4.41175 -4.46561 0.38 1
	shader "Red"
}
		
instance {
	name "Box11"
	geometry "Box"
	transform col 2.10689 0 0 0 0 3.1635 0 0 0 0 0.24 0 -1.03963 -4.41175 0.38 1
	shader "Yellow"
}

instance {
	name "Box12"
	geometry "Box"
	transform col 4.21378 0 0 0 0 3.1635 0 0 0 0 0.24 0 1.27795 -4.41175 0.38 1
	shader "Red"
}
		
instance {
	name "Box13"
	geometry "Box"
	transform col 3.1635 0 0 0 0 2.10689 0 0 0 0 0.24 0 -4.41175 -1.03963 0.38 1
	shader "Red"
}
		
instance {
	name "Box14"
	geometry "Box"
	transform col 6.327 0 0 0 0 6.327 0 0 0 0 0.24 0 -0.9319 -0.9319 0.38 1
	shader "Blue"
}
		
instance {
	name "Box15"
	geometry "Box"
	transform col -4.76837e-07 0 -6.327 0 0 2.10689 0 0 0.24 0 0 0 -4.62 -1.03963 -3.0681 1
	shader "Red"
}
		
instance {
	name "Box16"
	geometry "Box"
	transform col 4.21378 0 0 0 0 3.1635 0 0 0 0 0.24 0 1.27795 -4.41175 -9.62 1
	shader "Red"
}
		
instance {
	name "Box17"
	geometry "Box"
	transform col 4.21378 0 0 0 0 6.327 0 0 0 0 0.24 0 1.27795 -0.9319 -9.62 1
	shader "White"
}
		
instance {
	name "Box18"
	geometry "Box"
	transform col 6.327 0 0 0 0 0 2.10689 0 0 -0.24 0 0 -0.9319 -4.38 -8.30682 1
	shader "Yellow"
}
		
instance {
	name "Box19"
	geometry "Box"
	transform col 2.10689 0 0 0 0 -4.76837e-07 6.327 0 0 -0.24 0 0 -1.03963 -4.38 -5.9319 1
	shader "Red"
}
		
instance {
	name "Box20"
	geometry "Box"
	transform col 1.05345 0 0 0 0 2.10689 0 0 0 0 0.24 0 -4.46561 -3.30682 0.38 1
	shader "White"
}
		
instance {
	name "Box21"
	geometry "Box"
	transform col 2.10689 0 0 0 0 2.10689 0 0 0 0 0.24 0 -3.30682 -3.30682 0.38 1
	shader "Red"
}
		
instance {
	name "Box22"
	geometry "Box"
	transform col 3.1635 0 0 0 0 4.21378 0 0 0 0 0.24 0 -4.41175 1.27795 0.38 1
	shader "White"
}
		
instance {
	name "Box23"
	geometry "Box"
	transform col 0 0 -1.05345 0 0 2.10689 0 0 0.24 0 0 0 -4.62 -1.03963 0.465613 1
	shader "Blue"
}
		
instance {
	name "Box24"
	geometry "Box"
	transform col 0 0 -2.10689 0 0 2.10689 0 0 0.24 0 0 0 -4.62 -1.03963 -0.693178 1
	shader "White"
}
		
instance {
	name "Box25"
	geometry "Box"
	transform col 1.05345 0 0 0 0 6.327 0 0 0 0 0.24 0 -4.46561 -0.9319 -9.62 1
	shader "Yellow"
}

instance {
	name "Box26"
	geometry "Box"
	transform col 3.1635 0 0 0 0 0 1.05345 0 0 -0.24 0 0 -4.41175 5.62 -9.46561 1
	shader "Blue"
}
		
instance {
	name "Box27"
	geometry "Box"
	transform col 1.05345 0 0 0 0 0 2.10689 0 0 -0.24 0 0 -4.46561 5.62 -8.30682 1
	shader "White"
}
		
instance {
	name "Box28"
	geometry "Box"
	transform col 2.10689 0 0 0 0 0 2.10689 0 0 -0.24 0 0 -3.30682 5.62 -8.30682 1
	shader "Red"
}
		
instance {
	name "Box29"
	geometry "Box"
	transform col 2.10689 0 0 0 0 0 2.10689 0 0 -0.24 0 0 -1.03963 5.62 -8.30682 1
	shader "Red"
}
		
instance {
	name "Box30"
	geometry "Box"
	transform col 4.21378 0 0 0 0 0 3.1635 0 0 -0.24 0 0 1.27795 5.62 -9.41175 1
	shader "White"
}
		
instance {
	name "Box31"
	geometry "Box"
	transform col 1.05345 0 0 0 0 0 2.10689 0 0 -0.24 0 0 -4.46561 -4.38 -8.30682 1
	shader "White"
}

instance {
	name "Box32"
	geometry "Box"
	transform col 2.10689 0 0 0 0 0 2.10689 0 0 -0.24 0 0 -3.30682 -4.38 -8.30682 1
	shader "Yellow"
}
		
instance {
	name "Box33"
	geometry "Box"
	transform col 2.10689 0 0 0 0 0 1.05345 0 0 -0.24 0 0 -1.03963 -4.38 -9.46561 1
	shader "White"
}
		
instance {
	name "Box34"
	geometry "Box"
	transform col 4.21378 0 0 0 0 0 1.05345 0 0 -0.24 0 0 1.27795 -4.38 -9.46561 1
	shader "Blue"
}

instance {
	name "Box35"
	geometry "Box"
	transform col 3.1635 0 0 0 0 0 2.10689 0 0 -0.24 0 0 -4.41175 -4.38 -6.03963 1
	shader "Yellow"
}
		
instance {
	name "Box36"
	geometry "Box"
	transform col 1.05345 0 0 0 0 0 4.21378 0 0 -0.24 0 0 -4.46561 -4.38 -3.72205 1
	shader "Red"
}
		
instance {
	name "Box37"
	geometry "Box"
	transform col 2.10689 0 0 0 0 0 4.21378 0 0 -0.24 0 0 -3.30682 -4.38 -3.72205 1
	shader "White"
}
		
instance {
	name "Box38"
	geometry "Box"
	transform col 4.21378 0 0 0 0 0 2.10689 0 0 -0.24 0 0 1.27795 -4.38 -6.03963 1
	shader "Red"
}
		
instance {
	name "Box39"
	geometry "Box"
	transform col 4.21378 0 0 0 0 0 4.21378 0 0 -0.24 0 0 1.27795 -4.38 -3.72205 1
	shader "Yellow"
}
		
instance {
	name "Box40"
	geometry "Box"
	transform col 1.05345 0 0 0 0 1.05345 0 0 0 0 0.24 0 -4.46561 -4.46561 -9.62 1
	shader "White"
}
		
instance {
	name "Box41"
	geometry "Box"
	transform col 1.05345 0 0 0 0 2.10689 0 0 0 0 0.24 0 -4.46561 -3.30682 -9.62 1
	shader "Red"
}
		
instance {
	name "Box42"
	geometry "Box"
	transform col 2.10689 0 0 0 0 0 1.05345 0 0 -0.24 0 0 -1.03963 5.62 -9.46561 1
	shader "Blue"
}
		
instance {
	name "Box43"
	geometry "Box"
	transform col 1.05345 0 0 0 0 0 1.05345 0 0 -0.24 0 0 -4.46561 -4.38 -9.46561 1
	shader "Red"
}
		
instance {
	name "Box44"
	geometry "Box"
	transform col 2.10689 0 0 0 0 0 1.05345 0 0 -0.24 0 0 -3.30682 -4.38 -9.46561 1
	shader "Blue"
}
		