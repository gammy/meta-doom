DESCRIPTION = "An SDL2-based engine for Doom, Doom II, Hexen, Heretic & Strife"
SECTION = "games"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING.md;md5=60d644347832d2dd9534761f6919e2a6"

DEPENDS = "virtual/libsdl2 pkgconfig"
DEPENDS += "${@bb.utils.contains_any('DISTRO_FEATURES', 'ipv4 ipv6', 'libsdl2-net', '', d)}"
DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'alsa', 'libsdl2-mixer', '', d)}"

RRECOMMENDS:${PN} = "freedoom doom-episode-1"

PV = "3.1.1"
PR = "r0"
SRC_URI = "\
    https://github.com/chocolate-doom/chocolate-doom/archive/refs/tags/chocolate-doom-${PV}.tar.gz;name=engine \
"

# 3.1.1
SRC_URI[engine.sha256sum] = "1edcc41254bdc194beb0d33e267fae306556c4d24110a1d3d3f865717f25da23"

inherit autotools-brokensep gettext pkgconfig

S = "${WORKDIR}/chocolate-doom-chocolate-doom-${PV}"

do_install:append() {

    install -m 644 -d "${D}/${datadir}/games/chocolate-doom/"
    install -m 644 "COPYING.md" "${D}/${datadir}/games/chocolate-doom/"

    # Exclude superfluous 'default' install files.
    # TODO: How can we provide a mechanism to enable bundling (or even
    #       compiling/not compiling support for) hexen, heretic, strife &
    #       the network daemon?
    # Can we use PACKAGECONFIG for this? TBD.
    for extra_bin in \
        hexen hexen-setup \
        heretic heretic-setup \
        strife strife-setup \
        server
    do
        rm "${D}/${bindir}/chocolate-${extra_bin}"
    done

    # Exclude bash completion scripts for the binaries, xml files containing
    # nerd-info, screensavers (in applications/screensavers) and desktop icons.
    # TODO: make these optional (enabled by default)
    for share_subdir in bash-completion metainfo applications icons
    do
        rm -r "${D}/${datadir}/${share_subdir}"
    done
}

FILES:${PN} = "\
  ${bindir}/chocolate* \
  ${datadir}/games/chocolate-doom/COPYING.md \
"

# A complete (proper) install should also bundle these:
#  ${datadir}/bash-completion/*
#  ${datadir}/metainfo/*
#  ${datadir}/applications/*
#  ${datadir}/icons/*
#  ${datadir}/appdata/*

# Yocto wants us to reformat the engine URL with `git://`.
# But we're not pulling a git checkout from github; we're pulling an
# archived tarball, which isn't related to git at all.
# The QA Warning is thus nonsensical.
# FIXME this doesnt' actually make the QA warning go away though. Why?
INSANE_SKIP:${PN} += "src-uri-bad"
