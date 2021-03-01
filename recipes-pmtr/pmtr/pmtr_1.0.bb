#
# meta-pmtr 
#
# A Yocto layer for pmtr process manager
#

DESCRIPTION = "pmtr process manager"
HOMEPAGE = "https://troydhanson.github.io/pmtr"
SECTION = "base"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5c18fe814bfff6ca20c9acca6e32f9e4"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRCREV = "38a221cedb4276e33b7d326b58e4177f1d359c20"
SRC_URI = "git://github.com/troydhanson/pmtr.git"

S = "${WORKDIR}/git"

##################################################################
# sysvinit based init
#
# Place a sysvinit script in /etc/init.d/pmtr.
# The inherited update-rc.d along with the INITSCRIPT_NAME variable
# causes a build-time "update-rc.d pmtr defaults" to occur. It does
# a proper out of tree setup, since the image is formed out of tree.
#
# Also create an initially-empty /etc/pmtr.conf
#

##################################################################
inherit autotools update-rc.d
INITSCRIPT_NAME = "pmtr"

do_install_append() {
  install -d ${D}${sysconfdir}/init.d/
  ${S}/initscripts/setup-initscript --initsys sysvinit --install-service ${D}${sysconfdir}/init.d/pmtr --bindir ${bindir}
  touch ${D}${sysconfdir}/pmtr.conf
}

##################################################################
# systemd based init
#
# To use, comment out the sysvinit lines above and uncomment below.
#
# See the Yocto mega manuel section 26.134 systemd.bbclass.
# We install the systemd unit file in ${D}${systemd_unitdir}/system
# We set SYSTEMD_SERVICE_${PN} to the service file basename.
#
# Also create an initially-empty /etc/pmtr.conf
#
##################################################################
#inherit autotools systemd
#SYSTEMD_SERVICE_${PN} = "pmtr.service"
#
#do_install_append() {
#  install -d ${D}${sysconfdir} ${D}${systemd_unitdir}/system/
#  ${S}/initscripts/setup-initscript --initsys systemd --install-service ${D}${systemd_unitdir}/system/pmtr.service --bindir ${bindir}
#  touch ${D}${sysconfdir}/pmtr.conf
#}

PARALLEL_MAKE = ""

