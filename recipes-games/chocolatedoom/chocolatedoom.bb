DESCRIPTION = "A Doom Clone based on SDL"
SECTION = "games"
DEPENDS = "virtual/libsdl2 libsdl2-mixer libsdl2-net pkgconfig"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

RRECOMMENDS_${PN} = "freedoom"

PV = "3.0.1"
PR = "r0"
# FIXME seems upstream should be: https://www.chocolate-doom.org/downloads/3.0.1/chocolate-doom-${PV}.tar.gz ? Same thing?
SRC_URI = "https://github.com/chocolate-doom/chocolate-doom/archive/chocolate-doom-${PV}.tar.gz;name=engine \
           http://www.doomworld.com/3ddownloads/ports/shareware_doom_iwad.zip;name=shareware \
           file://0001-Fix-a-variable-redaclaration-in-hexen.patch \
           file://0001-SetSDLVideoDriver-list-detected-video-drivers.patch \
           file://chocolate-doom.cfg \
           file://default.cfg \
           file://0001-Nonsense.patch \
           "

SRC_URI[engine.sha256sum] = "a54383beef6a52babc5b00d58fcf53a454f012ced7b1936ba359b13f1f10ac66"
SRC_URI[shareware.sha256sum] = "845f4f3a449343b068a4e178f9cb018cb1f5b7d5ef09db292864ed554f612276"

inherit autotools-brokensep gettext pkgconfig

S = "${WORKDIR}/chocolate-doom-chocolate-doom-${PV}"

do_install:append() {
	install -d ${D}/${datadir}/games/doom
	install -m 0644 ${WORKDIR}/DOOM1.WAD ${D}/${datadir}/games/doom/doom1.wad
}

pkg_postinst_ontarget:${PN}() {
    # XXX note: mpv recipe does this too
    chown -R weston: /home/weston/
}

FILES:${PN} = "\ 
  /home/weston/.local/share/chocolate-doom/chocolate-doom.cfg \
  /home/weston/.local/share/chocolate-doom/default.cfg \
  ${datadir}/games/doom/doom1.wad \
  ${datadir}/applications/* \
  ${datadir}/icons/* \
  ${datadir}/appdata/* \
  ${bindir}/* \
"
