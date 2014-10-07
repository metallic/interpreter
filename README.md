interpreter
===========

-- Part 1 --

The challenge is to write an interpreter for a simple declarative 
graphics language. Your program should read from a filename supplied
on the command line; it should display as an image on the screen the
picture described by that file. Here is a sample file:

DIMENSIONS 600 600;
SET_COLOR 0 0 255;
RECTANGLE 100 100 500 500;
RECTANGLE 150 150 450 450;
SET_COLOR 255 0 0;
CIRCLE 100 300 80;
SET_COLOR 0 128 128;
CIRCLE 450 300 30;
LINE 300 200 500 400;
PIXEL 340 290;

Each line of the file consists of a command followed by some number of
arguments, all of which are integers. The first line always uses the
command DIMENSIONS which takes two arguments that specify the width
and the height of the window in which the drawing takes place. The
background color of the window should be black.

The following five commands should be supported:

SET_COLOR [red] [green] [blue];
  Set the drawing colour to be used by all other commands. The
  arguments specify the red, green and blue proportions and are all
  between 0 and 255 (Eg. SET_COLOR 78 0 255 corresponds to the colour
  code #4E00FF in HTML.)

PIXEL [x] [y];
  Color the pixel at coordinate (x,y).

LINE [x1] [y1] [x2] [y2]; 
  Draw a straight line from (x1,y1) to (x2,y2).

RECTANGLE [x1] [y1] [x2] [y2];
  Draw a rectangle with opposite corners (x1,y1) and (x2,y2).

CIRCLE [x] [y] [radius];
  Draw a circle centered on (x,y) with the given radius.

