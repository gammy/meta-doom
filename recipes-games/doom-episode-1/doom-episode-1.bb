DESCRIPTION = "DooM, Episode 1: Knee Deep in the Dead (shareware episode assets)"
SECTION = "games"
LICENSE = "Shareware"

RRECOMMENDS:${PN} = "chocolatedoom"

PV = "1.9"
PR = "r0"

SRC_URI = "https://archive.org/download/DoomsharewareEpisode/DoomV${PV}sw1995idSoftwareInc.action.zip;name=bundle \
           file://LICENSE.TXT"

LICENSE = "Proprietary"
LICENSE_FLAGS = "commercial"
LICENSE_FLAGS_DETAILS[commercial] = "\
Although we pull the entire (shareware) game and its DOS binaries,           \
we only extract the episode 1 WAD file containing the game assets.           \
                                                                             \
This original 1.9 release never included a license document; it was however, \
from what I gather, under the same license as v1.666 which _did_ come with   \
a license document, which is the one I've copied in here by hand.            \
                                                                             \
It is also worth noting that this license doesn't just cover the WAD file,   \
but discusses the license for the entire runnable game; executables and all. \
                                                                             \
In 1997 (3 years after v1.666 was released), id software released the Doom 1 \
source code (without game assets) under their own DSL (Doom Source License). \
In 1999, they re-released it (without game assets) under the GPL.            \
Since the DSL can't be retracted, it's therefore dual-licensed - DSL / GPL.  \
                                                                             \
Where does that leave the explicitly named shareware release game asset file?\
                                                                             \
In lack of a better answer and not seeing a direct conflict with the 1.666   \
license, that's what I'm including.                                          \
                                                                             \
Further resources:                                                           \
https://en.wikipedia.org/wiki/Doom_(1993_video_game)#Ports                   \
https://doom.fandom.com/wiki/Doom_Source_License                             \
https://doomwiki.org/wiki/Versions_of_Doom_and_Doom_II#Release_builds        \
https://archive.org/details/DoomsharewareEpisode                             \
"

SRC_URI[bundle.sha256sum] = "63ad7609f2e951fb2198f682e1226f003946c75c00b9785fa967ffb12c6745f7"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=7f9359cba77a4181efc2e6dbacaa619a"

S = "${WORKDIR}"

do_install:append() {
    install -d ${D}/${datadir}/games/doom
    install -m 0644 "${WORKDIR}/DOOM1.WAD" "${D}/${datadir}/games/doom/doom1.wad"
    install -m 0644 "${WORKDIR}/LICENSE.TXT" "${D}/${datadir}/games/doom/"
}

FILES:${PN} = "${datadir}/games/doom/doom1.wad \
               ${datadir}/games/doom/LICENSE.TXT"

