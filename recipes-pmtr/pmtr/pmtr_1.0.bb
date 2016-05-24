#
# meta-pmtr 
#
# A Yocto layer for pmtr process manager
#

DESCRIPTION = "pmtr process manager"
HOMEPAGE = "https://troydhanson.github.com/pmtr"
SECTION = "base"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=014148094f8c384ce40dac7900b965c4"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRCREV = "4ee30322a288929aa03f82275ee74df3841b4a81"
SRC_URI = "git://github.com/troydhanson/pmtr.git"

S = "${WORKDIR}/git"

inherit autotools update-rc.d

INITSCRIPT_NAME = "pmtr"

#
# Place a sysvinit script in /etc/init.d/pmtr.
# The inherited update-rc.d along with the INITSCRIPT_NAME variable
# causes a build-time "update-rc.d pmtr defaults" to occur. It does
# a proper out of tree setup, since the image is formed out of tree.
#
# Also create an initially-empty /etc/pmtr.conf
#

do_install_append() {
  install -d ${D}${sysconfdir}/init.d/
  ${S}/initscripts/setup-initscript --initsys sysvinit --install-service ${D}${sysconfdir}/init.d/pmtr --bindir ${bindir}
  touch ${D}${sysconfdir}/pmtr.conf
}

# The autotools configuration I am basing this on seems to have a problem with a race condition when parallel make is enabled
PARALLEL_MAKE = ""

