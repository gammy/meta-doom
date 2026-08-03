This README file contains information on the contents of the
meta-doom layer.

Please see the corresponding sections below for details.

Gammies Fork
==========================================
This is gammies fork of [geoffrey-vl's meta-doom layer](https://github.com/geoffrey-vl/meta-doom).

Changes include:
- Switch sdl-mixer and sdl-net dependencies to their sdl2 equivalents, which
  are already available in oe-core
- Remove the dependency on the freescale layer; it's no longer needed
  (and I'm not on freescale hardware)
- Add a recipe for the shareware episode of Doom, episode 1: Knee deep in the dead

Dependencies
============

This layer depends on:

  URI: git://git.openembedded.org/bitbake
  URI: git://git.openembedded.org/openembedded-core

Library-wise it mainly depends on the SDL2 (recipe `libsdl2`) library for
handling video rendering and user input.

The optional ancillary libraries SDL2-Mixer (recipe `libsdl2-mixer`) and
SDL2-Net (recipe `libsdl2-net`) are required for sound and network support.

Patches
=======

Please submit any patches against the meta-doom layer through https://github.com/gammy/meta-doom
You might also want to inform the original layer author, [geoffrey-vl](https://github.com/geoffrey-vl) if it's relevant.

(That being said, I should probably let Geoffrey know about this silly fork :P)

Table of Contents
=================

  I. Adding the meta-doom layer to your build
 II. Misc

## I. Adding the meta-doom layer to your build

In order to use this layer, you need to make the build system aware of
it by adding this layer to your BBLAYERS environment variable, e.g

```
BBLAYERS += " /path/to/yocto/meta-doom"
```

## II. Misc

This layer provides the Chocolate-doom game engine, a fork of the original
doom engine used for the 90's game. The meta-layer also currently provides two
sources for game assets: Freedoom, and Doom: Episode 1.

Freedoom is a fully featured alternative to the original game, with its own
unique levels, graphics and music.

![Freedoom Screenshot](freedoom.png)

Doom, Episode 1: Knee deep in the dead is the original shareware episode Doom
was distributed with.

You may also want to add your own WAD files through your own recipes.

