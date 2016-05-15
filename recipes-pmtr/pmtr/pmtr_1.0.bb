#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#

DESCRIPTION = "pmtr process manager"
HOMEPAGE = "https://troydhanson.github.com/pmtr"
SECTION = "base"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=014148094f8c384ce40dac7900b965c4"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRCREV = "8597825de0e00b66317f4e99b1831db781485ac3"
SRC_URI = "git://github.com/troydhanson/pmtr.git"

S = "${WORKDIR}/git"

inherit autotools systemd

# See the Yocto mega manuel section 26.134 systemd.bbclass.
# We install the systemd unit file in ${D}${systemd_unitdir}/system
# We set SYSTEMD_SERVICE_${PN} to the service file basename.
SYSTEMD_SERVICE_${PN} = "pmtr.service"
do_install_append() {
  ${S}/initscripts/setup-initscript --initsys systemd --install-service ${D}${systemd_unitdir}/system/pmtr.service --bindir ${bindir}
  install -d ${D}${sysconfdir}
  touch ${D}${sysconfdir}/pmtr.conf
}

# The autotools configuration I am basing this on seems to have a problem with a race condition when parallel make is enabled
PARALLEL_MAKE = ""

